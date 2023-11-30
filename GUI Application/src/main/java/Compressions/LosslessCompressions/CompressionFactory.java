/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Compressions.LosslessCompressions;

import Compressions.LosslessCompressions.LZ77.LZ77;
import Compressions.LosslessCompressions.LZW.LZW;
import Compressions.LosslessCompressions.StandardHuffman.Huffman;


/**
 *
 * @author abdelrahman
 */

public class CompressionFactory {
    public static Compression createCompression(String algorithm){
            if (null != algorithm) switch (algorithm) {
            case "LZ77" -> {
                return new LZ77();
            }
            case "LZW" -> {
                return new LZW();
            }
            case "Standard Huffman" -> {
                return new Huffman();
            }
            default -> {
                   return null; 
            }
        }
        return null;
    }
}
