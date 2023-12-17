package Compressions.LossyCompressions.VectorQuantization;

import Compressions.LossyCompressions.Compression;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;

public class VectorQuantization extends Compression<int[][][]>  {
    public int[] originalDimensions;  // {Height , Width}
    public int[] scaledDimensions; // {Height , Width}
    
    @Override
    public void compress(int[][][] image , String path) {
        // vector<int[][]> 
        scaledDimensions = createScaledDimensions(labelSize);
        
        int[][][] scaledImage = new int[scaledDimensions[0]][scaledDimensions[1]][4];
        for (int i = 0; i < scaledDimensions[0]; i++) {
            int x = i >= originalDimensions[0] ? originalDimensions[0] - 1 : i;
            for (int j = 0; j < scaledDimensions[1]; j++) {
                int y = j >= originalDimensions[1] ? originalDimensions[1] - 1 : j;
                
                scaledImage[i][j] = image[x][y];
            }
        }
        
        Vector<int[][][]> vectors = new Vector<>();
        for (int i = 0; i < scaledDimensions[0]; i+= labelSize) {
            for (int j = 0; j < scaledDimensions[1]; j+= labelSize) {
                vectors.add(new int[labelSize][labelSize][4]);
                for (int x = i, z = 0 ; x < i + labelSize && z < labelSize ; x++ , z++) {
                    for (int y = j, c = 0; y < j + labelSize && c < labelSize  ;  y++ , c++) {
                        vectors.lastElement()[z][c]=(scaledImage[x][y]);
                    }
                }
            }
        }
        
        Vector<int[][][]> quantized = new Vector<>();
        makeQuantized(vectors, quantized ,codeblockSize);
        int[] VectorsToQuantizedIndices = encodeImage(vectors , quantized);
        //Write To Compressed File
        Data data = new Data();
        data.setOriginalDimensions(originalDimensions);
        data.setScaledDimensions(scaledDimensions);
        data.setLabelSize(labelSize);
        data.setVectorsToQuantizedIndices(VectorsToQuantizedIndices);
        data.setQuantized(quantized);
        data.serialize(path.substring(0, path.lastIndexOf('.'))+".bin");
    }
    @Override
    public  BufferedImage decompress(JFrame f , String path) throws IOException, ClassNotFoundException, Exception {
            Data readData = Data.deserialize(f , path);
            if(readData == null) return null;
            originalDimensions = readData.getOriginalDimensions();
            scaledDimensions = readData.getScaledDimensions();
            int labelSize = readData.getLabelSize();
            int[] VectorsToOptimizeIndices = readData.getVectorsToQuantizedIndices();
            Vector<int[][][]> quantized = readData.getQuantized();
            
            int[][][] newImg = new int[scaledDimensions[0]][scaledDimensions[1]][4];
  
            for (int i = 0; i < VectorsToOptimizeIndices.length; i++) {
                int x = ( i / (scaledDimensions[1] / labelSize) ) * labelSize;
                int y = ( i % (scaledDimensions[1] / labelSize) ) * labelSize;
                int[][][] quantizedArray = quantized.get(VectorsToOptimizeIndices[i]);
                for (int j = x, m = 0; j < x + labelSize; j++, m++) {
                    for (int k = y, n = 0; k < y + labelSize; k++, n++) {
                        for(int v = 0 ; v < 4 ; v++){
                           newImg[j][k][v] = quantizedArray[m][n][v]; 
                        }
                        
                    }
                }
            }
            return writeImg(newImg);       
    }

  
    public int[] encodeImage(Vector<int[][][]> blocks, Vector<int[][][]> quantized) {
        int[] indices = new int[blocks.size()];
        int j = 0;
        Vector<Integer> sums = new Vector<>();
        Vector<Integer> sortedSums = new Vector<>();
        for (int[][][] vec : blocks) {
            for (int i = 0; i < quantized.size(); i++) {
                // calculate the distance between it and each quantized vector
               int distance = euclideanDistance(vec, quantized.get(i));
               sums.add(distance);
               sortedSums.add(distance);
            }
            // sort the distance
            Collections.sort(sortedSums);
            // pick the min distance and add its index
            int index = sums.indexOf(sortedSums.get(0));
            indices[j++] = (index);
            // reset vectors
            sums.clear();
            sortedSums.clear();
        }
        return indices;
    }
 
    private int[][][] getAverageVector(Vector<int[][][]> vectors) {
      int height = vectors.get(0).length;
      int width = vectors.get(0)[0].length;

      int[][][] summation = new int[height][width][4];

      for (int[][][] vector : vectors) {
          for (int i = 0; i < height; i++) {
              for (int j = 0; j < width; j++) {
                  for (int k = 0; k < 4; k++) {
                      summation[i][j][k] += vector[i][j][k];
                  }
              }
          }
      }

      int[][][] returnVector = new int[height][width][4];
      for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
              for (int k = 0; k < 4; k++) {
                  returnVector[i][j][k] = summation[i][j][k] / vectors.size();
              }
          }
      }

      return returnVector;
    }
    private  int euclideanDistance(int[][][] x, int[][][] y) {
        int distance = 0;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                for (int k = 0; k < x[i][j].length; k++) {
                    distance += Math.pow(x[i][j][k] - y[i][j][k], 2);
                }
            }
        }
        return (int) Math.sqrt(distance);
    }
    
    private void makeQuantized(Vector<int[][][]> blocks, Vector<int[][][]> quantized ,int bits ){
        if (bits == 1 || blocks.isEmpty()) {
            if (!blocks.isEmpty())
                quantized.add(getAverageVector(blocks));
            return;
        }
        Vector<int[][][]> left = new Vector<>();
        Vector<int[][][]> right = new Vector<>();

        int[][][] currentAvg = getAverageVector(blocks);
        int[][][] avgPlusOne = new int[currentAvg.length][currentAvg[0].length][4];
        int[][][] avgMinusOne = new int[currentAvg.length][currentAvg[0].length][4];

        for (int i = 0; i < currentAvg.length; i++) {
            for (int j = 0; j < currentAvg[0].length; j++) {
                for(int k = 0 ; k < 4 ; k++){
                    avgPlusOne[i][j][k] = currentAvg[i][j][k] + 1;
                    avgMinusOne[i][j][k] = currentAvg[i][j][k] - 1;
                }
            }
        }
        
        for (int[][][] currVector : blocks) {
            int d1 = euclideanDistance(currVector, avgMinusOne);
            int d2 = euclideanDistance(currVector, avgPlusOne);
            if (d1 >= d2) {
                left.add(currVector);
            } else {
                right.add(currVector);
            }
        }
        makeQuantized(left, quantized, bits / 2);
        makeQuantized(right, quantized, bits / 2);
    }

    private int[] createScaledDimensions(int labelSize) {
        int scaledHeight = originalDimensions[0] % labelSize == 0 ? originalDimensions[0] : ((originalDimensions[0] / labelSize) + 1) * labelSize;
        int scaledWidth = originalDimensions[1] % labelSize == 0 ? originalDimensions[1] : ((originalDimensions[1] / labelSize) + 1) * labelSize;
        return new int[]{scaledHeight, scaledWidth};
    }
    @Override
    public int[][][] readImg (File selectedFile) throws Exception {
        try{
            BufferedImage img = ImageIO.read(selectedFile);
            originalDimensions = new int[]{img.getHeight(), img.getWidth()};
            int[][][] image = new int [originalDimensions[0]][originalDimensions[1]][4];
            for (int i = 0; i < originalDimensions[0]; i++){
                for (int j = 0; j < originalDimensions[1]; j++){
                    int pixel = img.getRGB(j, i);
                    int red = (pixel & 0x00ff0000) >> 16;
                    int green = (pixel & 0x0000ff00) >> 8;
                    int alpha = (pixel & 0xff000000) >> 24;
                    int blue = pixel & 0x000000ff;
                    image[i][j][0] = red;
                    image[i][j][1] = green;
                    image[i][j][2] = blue;
                    image[i][j][3] = alpha;
                }
            }
            return image;
        }catch(IOException e){
           throw new Exception("Exception message");
        }
    }
    @Override
    public BufferedImage writeImg(int[][][] img) throws Exception {
        try{
            int width = img[0].length;
            int height = img.length;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int red = img[i][j][0];
                    int green = img[i][j][1];
                    int blue = img[i][j][2];
                    int alpha = img[i][j][3];
                    int rgb = (alpha << 24) | (red << 16) | (green << 8) | blue ;
                    image.setRGB(j, i, rgb);
                }
            }
            return image;
        }catch(Exception e){
           throw new Exception("Exception message");
        }
    }

}
