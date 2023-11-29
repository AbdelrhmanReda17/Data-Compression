/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fcai.data.compression.LosslessCompressions;

import fcai.data.compression.LosslessCompressions.StandardHuffman.Huffman;
import fcai.data.compression.LosslessCompressions.LZ77.LZ77;
import fcai.data.compression.LosslessCompressions.LZW.LZW;


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
