/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fcai.data.compression;

import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * @author abdelrahman
 * @param <T>
 */
public interface Compression<T , S> {
        public String compress(JFrame Main , String result);
        public String decompress(JFrame Main ,String data);
        public T compressHandler(String data);
        public String decompressHandler (S data);
}
