/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Compressions.LossyCompressions.PredictiveCoding;

import javax.swing.*;
import java.io.*;



public class PredictiveCodingData  {
    private int[][][] quantizedDifference;
    private  int[][] indices;
    public int[][][] getQuantizedDifference() {
        return quantizedDifference;
    }

    public int[][] getIndices() {
        return indices;
    }

    public PredictiveCodingData(int[][][] quantizedDifference, int[][] indices) {
        this.quantizedDifference = quantizedDifference;
        this.indices = indices;
    }
    public static PredictiveCodingData deserialize(JFrame f , String filePath) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            PredictiveCodingData data = new PredictiveCodingData(null , null);
            data.quantizedDifference = (int[][][]) objectInputStream.readObject();
            data.indices = (int[][]) objectInputStream.readObject();
            return data;
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(f, "Error in deserialization of file ");
            return null;
        }
    }
    public void serialize(String filePath) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(quantizedDifference);
            objectOutputStream.writeObject(indices);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
