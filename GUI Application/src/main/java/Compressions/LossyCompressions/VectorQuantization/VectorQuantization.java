package Compressions.LossyCompressions.VectorQuantization;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import javax.imageio.ImageIO;

public class VectorQuantization  {
    public static int[] originalDimensions;  // {Height , Width}
    public static int[] scaledDimensions; // {Height , Width}
    public static int[][] compress(int labelSize, int codeblockSize, int[][] image , String path) throws IOException {
        // vector<int[][]> 
        scaledDimensions = createScaledDimensions(labelSize);
        int[][] scaledImage = new int[scaledDimensions[0]][scaledDimensions[1]];
        for (int i = 0; i < scaledDimensions[0]; i++) {
            int x = i >= originalDimensions[0] ? originalDimensions[0] - 1 : i;
            for (int j = 0; j < scaledDimensions[1]; j++) {
                int y = j >= originalDimensions[1] ? originalDimensions[1] - 1 : j;
                scaledImage[i][j] = image[x][y];
            }
        }

        Vector<int[][]> vectors = new Vector<>();
        for (int i = 0; i < scaledDimensions[0]; i+= labelSize) {
            for (int j = 0; j < scaledDimensions[1]; j+= labelSize) {
                vectors.add(new int[labelSize][labelSize]);
                for (int x = i, z = 0 ; x < i + labelSize && z < labelSize ; x++ , z++) {
                    for (int y = j, c = 0; y < j + labelSize && c < labelSize  ;  y++ , c++) {
                        vectors.lastElement()[z][c]=(scaledImage[x][y]);
                    }
                }
            }
        }
        
        Vector<int[][]> quantized = new Vector<>();
        makeQuantized(vectors, quantized ,codeblockSize);
        int[] VectorsToQuantizedIndices = encodeImage(vectors , quantized);
        //Write To Compressed File
        Data data = new Data() {};
        data.setOriginalDimensions(originalDimensions);
        data.setScaledDimensions(scaledDimensions);
        data.setLabelSize(labelSize);
        data.setVectorsToQuantizedIndices(VectorsToQuantizedIndices);
        data.setQuantized(quantized);
        data.serialize(path.substring(0, path.lastIndexOf('.'))+".vqc");

        return scaledImage;
    }
    public static BufferedImage Decompress(String path) throws IOException, ClassNotFoundException, Exception {

            Data readData = Data.deserialize(path);
            if(readData == null) return null;
            originalDimensions = readData.getOriginalDimensions();
            scaledDimensions = readData.getScaledDimensions();
            int labelSize = readData.getLabelSize();
            int[] VectorsToOptimizeIndices = readData.getVectorsToQuantizedIndices();
            Vector<int[][]> quantized = readData.getQuantized();
            
            int[][] newImg = new int[scaledDimensions[0]][scaledDimensions[1]];
  
            for (int i = 0; i < VectorsToOptimizeIndices.length; i++) {
                int x = ( i / (scaledDimensions[1] / labelSize) ) * labelSize;
                int y = ( i % (scaledDimensions[1] / labelSize) ) * labelSize;
                int[][] quantizedArray = quantized.get(VectorsToOptimizeIndices[i]);
                for (int j = x, m = 0; j < x + labelSize; j++, m++) {
                    for (int k = y, n = 0; k < y + labelSize; k++, n++) {
                        newImg[j][k] = quantizedArray[m][n];
                    }
                }
            }
            return writeImg(newImg);       
    }

//   public static int[] closestVector(Vector<int[][]> quantized, Vector<int[][]> blocks) {
//        int[] closestVector = new int[blocks.size()];
//        int j = 0;
//        for (int[][] currentVec : blocks) {
//            int minDistance = euclideanDistance(currentVec, quantized.get(0));
//            int minIndex = 0;
//            for (int i = 1; i < quantized.size(); i++) {
//                int distance = euclideanDistance(currentVec, quantized.get(i));
//                int index = i;
//                if (distance < minDistance) {
//                    minDistance = distance;
//                    minIndex = index;
//                }
//            }
//            closestVector[j] = minIndex;
//            j++;
//        }
//        return closestVector;
//    }
   
   public static int[] encodeImage(Vector<int[][]> blocks, Vector<int[][]> quantized) {
        int[] indices = new int[blocks.size()];
        int j = 0;
        Vector<Integer> sums = new Vector<>();
        Vector<Integer> sortedSums = new Vector<>();
        for (int[][] vec : blocks) {
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
   
   
   
   
    private static int[][] getAverageVector(Vector<int[][]> vectors){
        int[][] summation = new int[vectors.get(0).length][vectors.get(0).length];
        
        for (int[][] vector : vectors )
            for (int i = 0; i < vector.length; i++)
                for(int j = 0 ; j < vector.length ; j++)
                    summation[i][j] += vector[i][j];

        int[][] returnVector = new int[vectors.get(0).length][vectors.get(0).length];
        for (int i = 0; i < summation.length; i++)
            for(int j = 0 ; j < summation.length ; j++)
                returnVector[i][j] = summation[i][j] / vectors.size();
        
        return returnVector;
    }
    
    private static int euclideanDistance(int[][] x, int[][] y)
    {
        int distance = 0;
        for (int i = 0; i < x.length; i++)
            for(int j = 0 ; j < x.length; j++)
                distance += Math.pow(x[i][j] - y[i][j] , 2);
        return (int) Math.sqrt(distance);
    }
        
    private static void makeQuantized(Vector<int[][]> blocks, Vector<int[][]> quantized ,int bits ){
        if (bits == 1 || blocks.isEmpty()) {
            if (!blocks.isEmpty())
                quantized.add(getAverageVector(blocks));
            return;
        }
        Vector<int[][]> left = new Vector<>();
        Vector<int[][]> right = new Vector<>();

        int[][] currentAvg = getAverageVector(blocks);
        int[][] avgPlusOne = new int[currentAvg.length][currentAvg[0].length];
        int[][] avgMinusOne = new int[currentAvg.length][currentAvg[0].length];

        for (int i = 0; i < currentAvg.length; i++) {
            for (int j = 0; j < currentAvg[0].length; j++) {
                avgPlusOne[i][j] = currentAvg[i][j] + 1;
                avgMinusOne[i][j] = currentAvg[i][j] - 1;
            }
        }
        for (int[][] currVector : blocks) {
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

    private static int[] createScaledDimensions(int labelSize) {
        int scaledHeight = originalDimensions[0] % labelSize == 0 ? originalDimensions[0] : ((originalDimensions[0] / labelSize) + 1) * labelSize;
        int scaledWidth = originalDimensions[1] % labelSize == 0 ? originalDimensions[1] : ((originalDimensions[1] / labelSize) + 1) * labelSize;
        return new int[]{scaledHeight, scaledWidth};
    }
    
    public static int[][] readImg (File selectedFile) throws Exception {
        try{
            BufferedImage img = ImageIO.read(selectedFile);
            originalDimensions = new int[]{img.getHeight(), img.getWidth()};
            int[][] image = new int [originalDimensions[0]][originalDimensions[1]];
            for (int i = 0; i < originalDimensions[0]; i++){
                for (int j = 0; j < originalDimensions[1]; j++){
                    int pixel = img.getRGB(j, i);
                    int red = (pixel & 0x00ff0000) >> 16;
                    image[i][j] = red;
                }
            }
            return image;
        }catch(IOException e){
           throw new Exception("Exception message");
        }
    }
    
    public static BufferedImage writeImg(int[][] img) throws Exception {
        try{
            int width = img[0].length;
            int height = img.length;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int rgb = (img[i][j] << 16) | (img[i][j] << 8) | img[i][j];
                    image.setRGB(j, i, rgb);
                }
            }
            return image;
        }catch(Exception e){
           throw new Exception("Exception message");
        }
    }


}
