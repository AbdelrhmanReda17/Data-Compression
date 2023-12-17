/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Compressions.LossyCompressions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * @author abdelrahman
 * @param <T>
 */
public abstract class Compression<T > {
        protected int labelSize = 1;
        protected int codeblockSize = 2;
        public abstract void compress(T image , String path );
        public abstract BufferedImage decompress(JFrame f ,String data) throws IOException, ClassNotFoundException, Exception;
        public abstract BufferedImage writeImg(T img) throws Exception;
        public abstract T readImg (File selectedFile) throws Exception;
        public void setLabelSize(int lb){
            this.labelSize = lb;
        }
        
        public void setCodeBlockSize(int cb){
            this.codeblockSize = cb;
        }
}
