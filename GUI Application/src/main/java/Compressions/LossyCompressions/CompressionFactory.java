/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Compressions.LossyCompressions;


import Compressions.LossyCompressions.PredictiveCoding.PredictiveCoding;
import Compressions.LossyCompressions.VectorQuantization.VectorQuantization;

/**
 *
 * @author abdelrahman
 */

public class CompressionFactory {
    public static Compression createCompression(String algorithm){
        return switch (algorithm) {
            case "Vector Quantization" -> new VectorQuantization();
            case "Predictive Coding" -> new PredictiveCoding();
            default -> null;
        };
    }
}
