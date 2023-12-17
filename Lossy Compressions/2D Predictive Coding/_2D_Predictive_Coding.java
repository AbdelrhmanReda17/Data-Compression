import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class _2D_Predictive_Coding {
    public static void compress(int levels, String fileName) throws Exception {
        int[][][] img = readImg(new File(fileName));
        int height = img.length;
        int width = img[0].length;
        //                                  compression
        // step 1 predict
        int[][][] predicted = predictor(img);
        // step 2 calculate  original - prediction as difference
        int[][][] difference = calculateDifference(img, predicted);
        // step 3 quantize the calculated difference
        Vector<int[][][]> diffVector = new Vector<>();
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                int[][][] pixel = new int[1][1][3];
                for (int k = 0; k < 3; k++) {
                    pixel[0][0][k] = difference[i][j][k];
                }
                diffVector.add(pixel);
            }
        }
        // quantize using non-uniform quantization
        Vector<int[][][]> quantized = new Vector<>();
        quantize(diffVector, quantized, levels);
        //                                  decompression
        // dequantize the difference
        int[] indices = getIndices(diffVector, quantized);
        int c = 0;
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                for (int k = 0; k < 3; k++) {
                    difference[i][j][k] = quantized.get(indices[c])[0][0][k];
                }
                c++;
            }
        }
        // now difference is dequantized and has first row and col
        // the decompressed image is the predicted + dequantized difference
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                for (int k = 0; k < 3; k++) {
                    img[i][j][k] = predicted[i][j][k] + difference[i][j][k];
                    // if the value is larger than the max
                    if (img[i][j][k] > 255) img[i][j][k] = 255;
                    // if the value is lower than the min
                    if (img[i][j][k] < 0) img[i][j][k] = 0;
                }
            }
        }
        // write the image
        writeImg(img, fileName);
    }

    public static int[][][] predictor(int[][][] img) {
        int[][][] predicition = new int[img.length][img[0].length][3];
        for (int i = 0; i < img.length; i++) {
            for (int k = 0; k < 3; k++) {
                predicition[i][0][k] = img[i][0][k];
            }
        }
        for (int j = 0; j < img[0].length; j++) {
            for (int k = 0; k < 3; k++) {
                predicition[0][j][k] = img[0][j][k];
            }
        }
        for (int i = 1; i < img.length; i++) {
            for (int j = 1; j < img[0].length; j++) {
                for (int k = 0; k < 3; k++) {
                    int A = predicition[i - 1][j][k];
                    int B = predicition[i - 1][j - 1][k];
                    int C = predicition[i][j - 1][k];
                    if (B <= Math.min(A, C))
                        predicition[i][j][k] = Math.max(A, C);
                    else if (B >= Math.max(A, C))
                        predicition[i][j][k] = Math.min(A, C);
                    else
                        predicition[i][j][k] = A + C - B;
                }
            }
        }
        return predicition;
    }

    public static int[][][] calculateDifference(int[][][] img, int[][][] predicted) {
        int[][][] diff = new int[img.length][img[0].length][3];
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                for (int k = 0; k < 3; k++) {
                    diff[i][j][k] = img[i][j][k] - predicted[i][j][k];
                }
            }
        }
        return diff;
    }

    public static void quantize(Vector<int[][][]> pixels, Vector<int[][][]> quantized, int levels) {
        if (levels == 1 || pixels.isEmpty()) {
            if (!pixels.isEmpty())
                quantized.add(getAveragePixel(pixels));
            return;
        }
        int[][][] avgPixel = getAveragePixel(pixels);
        int[][][] avgPlusOne = new int[1][1][3];
        int[][][] avgMinusOne = new int[1][1][3];
        Vector<int[][][]> right = new Vector<>();
        Vector<int[][][]> left = new Vector<>();
        for (int k = 0; k < 3; k++) {
            avgPlusOne[0][0][k] = avgPixel[0][0][k] + 1;
            avgMinusOne[0][0][k] = avgPixel[0][0][k] - 1;
        }
        for (int[][][] arr : pixels) {
            int distanceRight = getDistance(arr, avgPlusOne);
            int distanceLeft = getDistance(arr, avgMinusOne);
            if (distanceLeft <= distanceRight) {
                left.add(arr);
            } else {
                right.add(arr);
            }
        }
        quantize(right, quantized, levels / 2);
        quantize(left, quantized, levels / 2);
    }

    public static int[][][] getAveragePixel(Vector<int[][][]> pixels) {
        int[][][] result = new int[1][1][3];
        for (int[][][] arr : pixels) {
            for (int k = 0; k < 3; k++) {
                result[0][0][k] += arr[0][0][k];
            }
        }
        for (int k = 0; k < 3; k++) {
            result[0][0][k] /= pixels.size();
        }
        return result;
    }

    public static int getDistance(int[][][] x, int[][][] y) {
        int distance = 0;
        for (int k = 0; k < 3; k++) {
            distance += (int) Math.pow(x[0][0][k] - y[0][0][k], 2);
        }
        return (int) Math.sqrt(distance);
    }

    public static int[] getIndices(Vector<int[][][]> diff, Vector<int[][][]> quantized) {
        int[] indices = new int[diff.size()];
        int j = 0;
        for (int[][][] currentVec : diff) {
            int minDistance = getDistance(currentVec, quantized.get(0));
            int minIndex = 0;
            for (int i = 1; i < quantized.size(); i++) {
                int distance = getDistance(currentVec, quantized.get(i));
                int index = i;
                if (distance < minDistance) {
                    minDistance = distance;
                    minIndex = index;
                }
            }
            indices[j] = minIndex;
            j++;
        }
        return indices;
    }

    public static int[][][] readImg(File selectedFile) throws Exception {
        try {
            BufferedImage img = ImageIO.read(selectedFile);
            int[][][] image = new int[img.getHeight()][img.getWidth()][3];
            for (int i = 0; i < img.getHeight(); i++) {
                for (int j = 0; j < img.getWidth(); j++) {
                    int pixel = img.getRGB(j, i);
                    int red = (pixel & 0x00ff0000) >> 16;
                    int green = (pixel & 0x0000ff00) >> 8;
                    int blue = pixel & 0x000000ff;
                    image[i][j][0] = red;
                    image[i][j][1] = green;
                    image[i][j][2] = blue;
                }
            }
            return image;
        } catch (IOException e) {
            throw new Exception("File Doesn't Exist");
        }
    }

    public static BufferedImage writeImg(int[][][] img, String fileName) throws Exception {
        try {
            String[] tokens = fileName.split("\\.");
            int width = img[0].length;
            int height = img.length;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int red = img[i][j][0];
                    int green = img[i][j][1];
                    int blue = img[i][j][2];
                    int rgb = (red << 16) | (green << 8) | blue;
                    image.setRGB(j, i, rgb);
                }
            }
            ImageIO.write(image, "png", new File(tokens[0] + "_decompressed.png"));
            return image;
        } catch (Exception e) {
            throw new Exception("Exception message");
        }
    }
}
