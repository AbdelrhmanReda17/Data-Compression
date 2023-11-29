/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fcai.data.compression.LosslessCompressions;

import javax.swing.JFrame;

/**
 *
 * @author abdelrahman
 * @param <T>
 * @param <S>
 */
public interface Compression<T , S> {
        public String compress(JFrame Main , String result);
        public String decompress(JFrame Main ,String data);
        public T compressHandler(String data);
        public String decompressHandler (S data);
}
