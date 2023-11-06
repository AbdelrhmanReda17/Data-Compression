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
public interface Compression<T> {
        public String compress(JFrame Main , String result);
        public String decompress(JFrame Main ,String data);
        public Vector<T> compressHandler(String data);
        public String decompressHandler (Vector <T> data);
}
