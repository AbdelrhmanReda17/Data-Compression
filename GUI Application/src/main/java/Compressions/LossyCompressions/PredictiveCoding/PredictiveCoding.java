/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Compressions.LossyCompressions.PredictiveCoding;

import Compressions.LossyCompressions.Compression;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author abdelrahman
 */
public class PredictiveCoding extends Compression<int[][][]> {
    @Override
    public void compress(int[][][] image, String path) {

        int[][][] predicted = new int[image.length][image[0].length][3];
        for(int i =0 ; i < predicted[0].length ; i++){
             predicted[0][i] = image[0][i];
        }
        for(int i =0 ; i < predicted.length ; i++){
             predicted[i][0] = image[i][0];
        }         
        getPredicted(image, predicted);
        int[][][] difference = getDifference(image, predicted);
        Vector<Vector<UniformQuantize>> quantized = makeQuantized(difference, codeblockSize);
        int [][] indices = new int[quantized.get(0).size()][3];
        int[][][] quantizedDifference = encodeImage(difference, indices,  quantized);
        PredictiveCodingData data = new PredictiveCodingData(quantizedDifference, indices);
        data.serialize(path.substring(0, path.lastIndexOf('.')) + ".bin");
    }
    private int[][][] encodeImage(int[][][] difference , int[][] indices, Vector<Vector<UniformQuantize>> quantized) {
        int[][][] encoded = new int[difference.length][difference[0].length][3];
        for (int i = 0; i < difference.length; i++) {
            for (int j = 0; j < difference[0].length; j++) {
                for (int k = 0 ; k < 3 ; k++){
                    if (i == 0 || j == 0) {
                        encoded[i][j][k] = difference[i][j][k];
                        continue;
                    }
                    for (UniformQuantize uniformQuantize : quantized.get(k)) {
                        if (difference[i][j][k] >= uniformQuantize.start && difference[i][j][k] <= uniformQuantize.end) {
                            encoded[i][j][k] = uniformQuantize.Q;
                        }
                    }
                }
            }
        }
        for(int k = 0 ; k < 3 ; k++){
            for(UniformQuantize l : quantized.get(k)){
                System.out.println(l.start + " " + l.end + " " + l.Q + " " + l.Qe);
                indices[l.Q][k] = l.Qe;
            }
        }
        return encoded;
    }

    private int[][][] getDifference(int[][][] image, int[][][] predicted) {
        int[][][] diff = new int[image.length][image[0].length][3];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                for (int k = 0 ; k < 3 ; k++){
                    if (i == 0 || j == 0) {
                        diff[i][j][k] = image[i][j][k];
                        continue;
                    }
                    diff[i][j][k] = image[i][j][k] - predicted[i][j][k];
                }
            }
        }
        return diff;
    }
     public int getPixel(int A , int B , int C){
        if (B <= Math.min(A, C))
            return Math.max(A, C);
        else if (B >= Math.max(A, C))
            return Math.min(A, C);
        else
            return A + C - B;
    }
    private void getPredicted(int[][][] image , int[][][] prediction ) {
        for (int i = 1; i < image.length; i++) {
            for (int j = 1; j < image[0].length; j++) {
                for (int k = 0 ; k < 3 ; k++) {
                    int A = prediction[i][j-1][k];
                    int B = prediction[i - 1][j - 1][k];
                    int C = prediction[i-1][j ][k];
                    prediction[i][j][k] = getPixel(A,B,C);
                }
            }
        }

    }

    public Vector<Vector<UniformQuantize>> makeQuantized(int[][][] difference, int levels) {
        Vector<Vector<UniformQuantize>> quantized = new Vector<>();
        for (int k = 0 ; k < 3 ; k++){
            Map.Entry<Integer, Integer> maxAndMin = getMaxAndMin(difference , k);
            System.out.println(maxAndMin.getKey() + " " + maxAndMin.getValue());
            Vector<UniformQuantize> quantize = new Vector<>();
            int step = (int) Math.ceil((double) (maxAndMin.getKey() - maxAndMin.getValue() - 1) / (double) levels);
            for (int i = 0, start = maxAndMin.getValue(); i < levels; i++, start += step) {
                if(i + 1 == levels){
                    quantize.add(new UniformQuantize(start, maxAndMin.getKey(), i, (int) Math.ceil((double) (start + (maxAndMin.getKey())) / (double) 2)));
                    break;
                }
                quantize.add(new UniformQuantize(start, (start + step - 1), i, (int) Math.ceil((double) (start + (start + step - 1)) / (double) 2)));
            }
            quantized.add(quantize);
        }
        
        return quantized;
    }
    private Map.Entry<Integer,Integer> getMaxAndMin(int[][][] image , int dimension) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < image.length; i++) {
            for (int j = 1; j < image[0].length; j++) {
                int value = image[i][j][dimension];
                max = Math.max(max, value);
                min = Math.min(min, value);
            }
        }
        return new AbstractMap.SimpleEntry<Integer , Integer>(max, min);
    }

    @Override
    public BufferedImage decompress(JFrame f , String path) {
        PredictiveCodingData data = PredictiveCodingData.deserialize(f, path);
        if (data == null) return null;
        int[][][] quantizedDifference = data.getQuantizedDifference();
        int[][] indices= data.getIndices();
        int[][][] DeQuantizedDifference = getDequantized(quantizedDifference, indices);
        int[][][] image = new int[DeQuantizedDifference.length][DeQuantizedDifference[0].length][3];
        for(int i =0 ; i < image[0].length ; i++){
            image[0][i] = DeQuantizedDifference[0][i];
        }
        for(int i =0 ; i < image.length ; i++){
            image[i][0] = DeQuantizedDifference[i][0];
        }
        getPredicted(image, image);
        for (int i = 1; i < DeQuantizedDifference.length; i++) {
            for (int j = 1; j < DeQuantizedDifference[0].length; j++) {
                for (int k = 0 ; k < 3 ; k++){
                    image[i][j][k] += DeQuantizedDifference[i][j][k];
                    if (image[i][j][k] > 255) image[i][j][k] = 255;
                    else if (image[i][j][k] < 0) image[i][j][k] = 0;
                }

            }
        }
        try {
            return writeImg(image);
        } catch (Exception ex) {
            Logger.getLogger(PredictiveCoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private int[][][] getDequantized(int[][][] quantizedDifference, int[][] indices) {
        int[][][] decoded = new int[quantizedDifference.length][quantizedDifference[0].length][3];
        for (int i = 0; i < quantizedDifference.length; i++) {
            for (int j = 0; j < quantizedDifference[0].length; j++) {
                for (int k = 0 ; k < 3 ; k++){
                    if (i == 0 || j == 0) {
                        decoded[i][j][k] = quantizedDifference[i][j][k];
                        continue;
                    }
                    decoded[i][j][k] = indices[quantizedDifference[i][j][k]][k];
                }

            }
        }
        return decoded;
    }

    @Override
    public int[][][] readImg(File selectedFile) throws Exception {
        try {
            BufferedImage img = ImageIO.read(selectedFile);
            int[] originalDimensions = new int[]{img.getHeight(), img.getWidth()};
            int[][][] image = new int[originalDimensions[0]][originalDimensions[1]][3];
            for (int i = 0; i < originalDimensions[0]; i++) {
                for (int j = 0; j < originalDimensions[1]; j++) {
                    int pixel = img.getRGB(j, i);
                    image[i][j][0] = (pixel & 0x00ff0000) >> 16;
                    image[i][j][1] = (pixel & 0x0000ff00) >> 8;
                    image[i][j][2] = pixel & 0x000000ff;
                }
            }
            return image;
        } catch (IOException e) {
            throw new Exception("Exception message");
        }
    }

    @Override
    public BufferedImage writeImg(int[][][] img) throws Exception {
        try {
            int width = img[0].length;
            int height = img.length;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int red = img[i][j][0];
                    int green = img[i][j][1];
                    int blue = img[i][j][2];
                    int rgb = (red << 16) | (green << 8) | blue ;
                    image.setRGB(j, i, rgb);
                }
            }
            return image;
        } catch (Exception e) {
            throw new Exception("Exception message");
        }
    }
}
