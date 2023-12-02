package Compressions.LossyCompressions.VectorQuantization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class Data implements Serializable {
    private int[] originalDimensions;
    private int[] scaledDimensions;
    private int labelSize;
    private int[] vectorsToQuantizedIndices;
    private Vector<int[][]> quantized;
    
    // Static deserialization method
    public static Data deserialize(String filePath) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Data) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void serialize(String filePath) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getOriginalDimensions() {
        return originalDimensions;
    }

    public void setOriginalDimensions(int[] originalDimensions) {
        this.originalDimensions = originalDimensions;
    }

    public int[] getScaledDimensions() {
        return scaledDimensions;
    }

    public void setScaledDimensions(int[] scaledDimensions) {
        this.scaledDimensions = scaledDimensions;
    }

    public int getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(int labelSize) {
        this.labelSize = labelSize;
    }

    public int[] getVectorsToQuantizedIndices() {
        return vectorsToQuantizedIndices;
    }

    public void setVectorsToQuantizedIndices(int[] vectorsToQuantizedIndices) {
        this.vectorsToQuantizedIndices = vectorsToQuantizedIndices;
    }

    public Vector<int[][]> getQuantized() {
        return quantized;
    }

    public void setQuantized(Vector<int[][]> quantized) {
        this.quantized = quantized;
    }


}
