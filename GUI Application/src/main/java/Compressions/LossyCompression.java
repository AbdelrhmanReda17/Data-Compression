/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package Compressions;

import Compressions.LossyCompressions.Compression;
import Compressions.LossyCompressions.CompressionFactory;
import Compressions.LossyCompressions.VectorQuantization.VectorQuantization;
import Compressions.Utilities.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class LossyCompression extends javax.swing.JFrame {
    private Compression CP = new VectorQuantization();
    BufferedImage compressedImage;
    BufferedImage originalImage;
    File selectedFile;
    boolean isCompressedFile = false;
    
    /** Creates new form LosslessCompression */
    public LossyCompression() {
        initComponents();
        this.setLocationRelativeTo(null);
        Off();
    }
    public final void Off(){
        this.jCompressButton.setEnabled(false); 
        this.jDecompressButton.setEnabled(false);
        this.jCodeblockSpinner.setEnabled(false);
        this.jChooseCompression.setEnabled(false);
        this.jLabelSpinner.setEnabled(false);
        this.jSaveButton.setEnabled(false);
        selectedFile = null;
        jImage.setIcon(null);
        this.jOriginalButton.setEnabled(false);
        this.jGenericButton.setText("Browse");
        this.jFileName.setText("");
    }
    public final void NormalOpen(){
        this.jCompressButton.setEnabled(true);
        this.jDecompressButton.setEnabled(true);
        this.jCodeblockSpinner.setEnabled(true);
        this.jChooseCompression.setEnabled(true);
        this.jLabelSpinner.setEnabled(true);
        this.jSaveButton.setEnabled(true);
        this.jOriginalButton.setEnabled(true);
        this.jGenericButton.setText("Close");
        isCompressedFile = false;
    }
    public final void CompressedOpen(){
        this.jDecompressButton.setEnabled(true);
        this.jSaveButton.setEnabled(true);
        this.jGenericButton.setEnabled(true);
        isCompressedFile = true;
        this.jChooseCompression.setEnabled(true);
        this.jGenericButton.setText("Close");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jCodeblockSpinner = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabelSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jCompressButton = new javax.swing.JButton();
        jDecompressButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jSaveButton = new javax.swing.JButton();
        jChooseCompression = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jOriginalButton = new javax.swing.JButton();
        jImagePane = new javax.swing.JScrollPane();
        jImage = new javax.swing.JLabel();
        jGenericButton = new javax.swing.JButton();
        jFileName = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lossy Compressions");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Label Size :");

        jCodeblockSpinner.setModel(new javax.swing.SpinnerNumberModel(2, 2, null, 1));
        jCodeblockSpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jCodeblockSpinner.setValue(2);
        jCodeblockSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCodeblockSpinnerStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Codeblock Size:");

        jLabelSpinner.setName(""); // NOI18N
        jLabelSpinner.setValue(1);
        jLabelSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jLabelSpinnerStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel4.setText("Note : ( Width = Height )");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCodeblockSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSpinner))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCodeblockSpinner))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelSpinner))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jCompressButton.setText("Compress");
        jCompressButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCompressButtonActionPerformed(evt);
            }
        });

        jDecompressButton.setText("Decompress");
        jDecompressButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDecompressButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCompressButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDecompressButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCompressButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDecompressButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSaveButton.setText("Save");
        jSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        jChooseCompression.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vector Quantization", "Predictive Coding" }));
        jChooseCompression.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jChooseCompressionItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jChooseCompression, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel3, jPanel5});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jChooseCompression, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jOriginalButton.setText(" Compressed / Original Image");
        jOriginalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOriginalButtonActionPerformed(evt);
            }
        });

        jImage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jImageComponentResized(evt);
            }
        });
        jImagePane.setViewportView(jImage);

        jGenericButton.setText("Browse/Close");
        jGenericButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGenericButtonActionPerformed(evt);
            }
        });

        jFileName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFileName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jFileName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jImagePane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jFileName, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jGenericButton, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jOriginalButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jImagePane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jOriginalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jGenericButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jFileName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        DataCompression.StartGUI();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        DataCompression.StartGUI();
    }//GEN-LAST:event_formWindowClosing

    private void jGenericButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGenericButtonActionPerformed
        if(selectedFile != null){
            Off();
            return;
        }
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new ImageFilter());
            fileChooser.setAcceptAllFileFilterUsed(false);
            int option = fileChooser.showOpenDialog(this);
            if(option == JFileChooser.APPROVE_OPTION){
                this.selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                this.jFileName.setText(path);
                if(".bin".equals(path.substring(path.lastIndexOf('.') , path.length()))){
                    CompressedOpen();
                }else{
                    ImageIcon img = new ImageIcon(selectedFile.toString());
                    originalImage = ImageIO.read(selectedFile);
                    ImageScaling(img);
                    NormalOpen();
                }
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot Read the Image !" + e.getMessage() , "Error" , 0);
            Off();
        }    
    }//GEN-LAST:event_jGenericButtonActionPerformed
    public static boolean  isPressed = false;
    private void jOriginalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOriginalButtonActionPerformed
          if(isPressed){
            if(compressedImage == null) return;
            ImageIcon img = new ImageIcon(compressedImage);
            ImageScaling(img); 
            isPressed = false;
          }else{
            if(originalImage == null) return;
            ImageIcon img = new ImageIcon(originalImage);
            ImageScaling(img); 
            isPressed = true;
          }
    }//GEN-LAST:event_jOriginalButtonActionPerformed

    private void jCodeblockSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCodeblockSpinnerStateChanged
          if( Integer.parseInt( this.jCodeblockSpinner.getValue().toString())  <= 0){
              JOptionPane.showMessageDialog(this, "Can't assign zero or  negative values !" , "Error" , 0);
              jCodeblockSpinner.setValue(1);
          }else{
             this.CP.setCodeBlockSize((int) this.jCodeblockSpinner.getValue());
          }
    }//GEN-LAST:event_jCodeblockSpinnerStateChanged

    private void jLabelSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jLabelSpinnerStateChanged
          if( Integer.parseInt( this.jLabelSpinner.getValue().toString()) <= 0 ){
              JOptionPane.showMessageDialog(this, "Can't assign zero or  negative values !" , "Error" , 0);
              jLabelSpinner.setValue(1);
          }else{
              this.CP.setLabelSize((int) this.jLabelSpinner.getValue());
          }
    }//GEN-LAST:event_jLabelSpinnerStateChanged

    private void jCompressButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCompressButtonActionPerformed
        try {
             CP.compress( CP.readImg(selectedFile) , selectedFile.getAbsolutePath());
        } catch (Exception ex) {
            Logger.getLogger(LossyCompression.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Image Compressed Successfully " + selectedFile.getAbsolutePath().substring(0,  selectedFile.getAbsolutePath().lastIndexOf('.'))+".bin" , "Success" , 1);
    }//GEN-LAST:event_jCompressButtonActionPerformed

    private void jDecompressButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDecompressButtonActionPerformed
        try {
            if(isCompressedFile){
                compressedImage = (BufferedImage) CP.decompress(this , selectedFile.getAbsoluteFile().toString());

            }else{
                compressedImage = (BufferedImage) CP.decompress(this , selectedFile.getAbsoluteFile().toString().substring(0, selectedFile.getAbsoluteFile().toString().lastIndexOf('.'))+".bin");
            }
            if(compressedImage == null) return;
            ImageIcon img = new ImageIcon(compressedImage);
            ImageScaling(img);
        } catch (Exception ex) {
            Logger.getLogger(LossyCompression.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jDecompressButtonActionPerformed
    
    private void ImageScaling(ImageIcon img){
        Image image = img.getImage().getScaledInstance(this.jImage.getWidth(), this.jImage.getHeight() ,2);
        this.jImage.setIcon(new ImageIcon(image));
    }
    private void jImageComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jImageComponentResized
        ImageIcon img = (ImageIcon) this.jImage.getIcon();
        if(img == null) return;
        ImageScaling(img);
    }//GEN-LAST:event_jImageComponentResized
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1).toLowerCase();
    }
    
    private void jSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveButtonActionPerformed
       try {
            String Extension = getFileExtension(selectedFile);
            if("bin".equals(Extension)){
                Extension = "png";
            }
            File outputfile = new File(selectedFile.getAbsoluteFile().toString().substring(0, selectedFile.getAbsoluteFile().toString().lastIndexOf('.')) + "Compressed." + Extension);
           
            ImageIO.write(compressedImage, Extension, outputfile);
            JOptionPane.showMessageDialog(this, "Image saved successfully" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jSaveButtonActionPerformed

    private void jChooseCompressionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jChooseCompressionItemStateChanged
        CP = CompressionFactory.createCompression(evt.getItem().toString());
        CP.setLabelSize(Integer.parseInt(this.jLabelSpinner.getValue().toString()));
        CP.setCodeBlockSize(Integer.parseInt(this.jCodeblockSpinner.getValue().toString()));
        if(evt.getItem().toString() == "Vector Quantization"){
            this.jLabelSpinner.setVisible(true);
            this.jLabel3.setVisible(true);
            this.jLabel4.setVisible(true);
            this.jLabel2.setText("Codeblock Size");
        }else{
            this.jLabelSpinner.setVisible(false);
            this.jLabel3.setVisible(false);
            this.jLabel4.setVisible(false);
            this.jLabel2.setText("Levels");
        }
    }//GEN-LAST:event_jChooseCompressionItemStateChanged
    public static void StartGUI() {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LossyCompression().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jChooseCompression;
    private javax.swing.JSpinner jCodeblockSpinner;
    private javax.swing.JButton jCompressButton;
    private javax.swing.JButton jDecompressButton;
    private javax.swing.JLabel jFileName;
    private javax.swing.JButton jGenericButton;
    private javax.swing.JLabel jImage;
    private javax.swing.JScrollPane jImagePane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner jLabelSpinner;
    private javax.swing.JButton jOriginalButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton jSaveButton;
    // End of variables declaration//GEN-END:variables
}
