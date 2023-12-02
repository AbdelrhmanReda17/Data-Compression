import java.io.*;
import java.util.Collections;
import java.util.Vector;

public class VectorQuantization {

    public int vectorHeight = 2;
    public int vectorWidth = 2;
    public int codeBookSize = 64;

    public Vector<Vector<Integer>> divideIntoVectors(int[][] scaledImg, int scaledHeight, int scaledWidth){
        Vector<Vector<Integer>> blocks = new Vector<>();
        for (int i = 0; i < scaledHeight; i+= vectorHeight) {
            for (int j = 0; j < scaledWidth; j+= vectorWidth) {
                Vector<Integer> tmp = new Vector<>();
                for (int x = i; x < i + vectorHeight; x++) {
                    for (int y = j; y < j + vectorWidth; y++) {
                        tmp.add(scaledImg[x][y]);
                    }
                }
                blocks.add(tmp);
            }
        }
        return blocks;
    }

    public Vector<Integer> calculateAverage(Vector<Vector<Integer>> Vectors){
        int[] sum = new int[Vectors.get(0).size()];
        for (Vector<Integer> vector : Vectors ) {
            for (int i = 0; i < vector.size(); i++) {
                sum[i] += vector.get(i);
            }
        }
        Vector<Integer> avg = new Vector<>();
        for (int i = 0; i < sum.length; i++) {
            avg.add(sum[i] / Vectors.size());
        }
        return avg;
    }
    public Vector<Vector<Integer>> splitAverage(Vector<Integer> average){
        Vector<Vector<Integer>> returnVec = new Vector<>();

        Vector<Integer> v1 = new Vector<>();
        Vector<Integer> v2 = new Vector<>();
        for(int i = 0;i<average.size();i++){
            // split into 2 vectors
            v1.add(average.get(i) + 1);
            v2.add(average.get(i) - 1);
        }
        returnVec.add(v1);
        returnVec.add(v2);

        return returnVec;
    }
    public int calculateDistance(Vector<Integer> vec1, Vector<Integer> vec2){
        int sum = 0;
        // calculate the euclidean distance between 2 vectors
        for(int i = 0;i<vec1.size();i++) {
            sum += (int) Math.pow(vec1.get(i) - vec2.get(i), 2);
        }
        return sum;
    }
    public void quantize(int codeBookSize,  Vector<Vector<Integer>> Vectors, Vector<Vector<Integer>> quantized){
        if(codeBookSize == 1 || Vectors.size() == 0){
            if(Vectors.size() > 0)
                quantized.add(calculateAverage(Vectors));
            return;
        }

        Vector<Integer> avg = calculateAverage(Vectors);
        Vector<Vector<Integer>> splitVectors = splitAverage(avg);

        Vector<Vector<Integer>> left = new Vector<>();
        Vector<Vector<Integer>> right =  new Vector<>();

        for (Vector<Integer> vec: Vectors) {
            int dis1 = calculateDistance(vec, splitVectors.get(0));
            int dis2 = calculateDistance(vec, splitVectors.get(1));

            if(dis1 <= dis2)
                left.add(vec);
            else
                right.add(vec);
        }
        quantize(codeBookSize/2, left, quantized);
        quantize(codeBookSize/2, right, quantized);
    }
    public Vector<Integer> encodeImage(Vector<Vector<Integer>> Vectors, Vector<Vector<Integer>> quantized) {
        Vector<Integer> indices = new Vector<>();

        Vector<Integer> sums = new Vector<>();
        Vector<Integer> sortedSums = new Vector<>();

        for (Vector<Integer> vec : Vectors) {
            for (int i = 0; i < quantized.size(); i++) {
                // calculate the distance between it and each quantized vector
               int distance = calculateDistance(vec, quantized.get(i));
               sums.add(distance);
               sortedSums.add(distance);
            }
            // sort the distance
            Collections.sort(sortedSums);
            // pick the min distance and add its index
            int index = sums.indexOf(sortedSums.get(0));
            indices.add(index);
            // reset vectors
            sums.clear();
            sortedSums.clear();
        }
        return indices;
    }
    public boolean compress(String file) throws IOException, ClassNotFoundException {
        // Read image
        int[][] image = RWImage.readImage(file);

        int height = RWImage.height;
        int width  = RWImage.width;
        int scaledHeight, scaledWidth;

        // get the scaled height and scaled width
        if(height % vectorHeight == 0)
            scaledHeight = height;
        else
            scaledHeight = ((height / vectorHeight) + 1) * vectorHeight;

        if(width % vectorWidth == 0)
            scaledWidth = width;
        else
            scaledWidth = ((width / vectorWidth) + 1) * vectorWidth;


        // Scale image
        int[][] scaledImage = new int[scaledHeight][scaledWidth];
        int x, y;
        for (int i = 0; i < scaledHeight; i++) {
            if(i >= height)
                x = height - 1;
            else x = i;
            for (int j = 0; j < scaledWidth; j++) {
                if(j >= width)
                    y = width - 1;
                else y = j;
                scaledImage[i][j] = image[x][y];
            }
        }

        // Divide image into Vectors
        Vector<Vector<Integer>> Vectors = divideIntoVectors(scaledImage,scaledHeight,scaledWidth);
        // construct codebooks
        Vector<Vector<Integer>> quantized = new Vector<>();
        quantize(codeBookSize, Vectors, quantized);
        // assign every vector to its nearest codebook by its index
        Vector<Integer> output = encodeImage(Vectors, quantized);

        FileOutputStream fileOutputStream = new FileOutputStream(getCompressedPath(file));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        // Write To Compressed File
        objectOutputStream.writeObject(width);
        objectOutputStream.writeObject(height);
        objectOutputStream.writeObject(scaledWidth);
        objectOutputStream.writeObject(scaledHeight);
        objectOutputStream.writeObject(vectorWidth);
        objectOutputStream.writeObject(vectorHeight);
        objectOutputStream.writeObject(output);
        objectOutputStream.writeObject(quantized);
        objectOutputStream.close();

        decompress(getCompressedPath(file));

        return true;
    }
    public boolean decompress(String fileName) throws IOException, ClassNotFoundException {

        InputStream file = new FileInputStream(fileName);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // read from file
        int width = (int) input.readObject();
        int height = (int) input.readObject();
        int scaledWidth = (int) input.readObject();
        int scaledHeight = (int) input.readObject();
        int vectorWidth = (int) input.readObject();
        int vectorHeight = (int) input.readObject();
        Vector<Integer> indices = (Vector<Integer>)input.readObject();
        Vector<Vector<Integer>> quantized = (Vector<Vector<Integer>>) input.readObject();

        int[][] newImg = new int[scaledHeight][scaledWidth];


        for (int i = 0; i < indices.size(); i++) {
            int x = i / (scaledWidth / vectorWidth) * vectorHeight;
            int y = i % (scaledWidth / vectorWidth) * vectorWidth;
            int v = 0;
            for (int j = x; j < x + vectorHeight; j++) {
                for (int k = y; k < y + vectorWidth; k++) {
                    newImg[j][k] = quantized.get(indices.get(i)).get(v++);
                }
            }
        }

        // Write image
        RWImage.writeImage(newImg, width, height, getDecompressedPath(fileName));

        return true;
    }
    public String getCompressedPath(String path) {
        return path.substring(0, path.lastIndexOf('.')) + ".VQ";
    }
    public String getDecompressedPath(String path)    {
        return path.substring(0,path.lastIndexOf('.')) + "_compressed.jpg";
    }
}
