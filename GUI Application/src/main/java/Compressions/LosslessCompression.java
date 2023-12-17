/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Compressions;

import Compressions.LosslessCompressions.LZ77.LZ77;
import Compressions.LosslessCompressions.CompressionFactory;
import Compressions.LosslessCompressions.Compression;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class LosslessCompression extends javax.swing.JFrame {
    private File selectedFile;
    private Compression CP = new LZ77();

    public LosslessCompression() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.jCompressionChoice.add("LZ77");
        this.jCompressionChoice.add("LZW");
        this.jCompressionChoice.add("Standard Huffman");
        this.Reset();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextAreaPanel = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jSideMenuPanel = new javax.swing.JPanel();
        jButtonPanel = new javax.swing.JPanel();
        jSaveButton = new javax.swing.JButton();
        jGenericButton = new javax.swing.JButton();
        jCompressPanel = new javax.swing.JPanel();
        jCompressButton = new javax.swing.JButton();
        jDecompressButton = new javax.swing.JButton();
        jCompressionChoice = new java.awt.Choice();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane.setToolTipText("");
        jScrollPane.setAutoscrolls(true);
        jScrollPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane.setRowHeaderView(null);

        jTextArea.setEditable(false);
        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jTextArea.setMaximumSize(new java.awt.Dimension(500, 500));
        jTextArea.setName(""); // NOI18N
        jScrollPane.setViewportView(jTextArea);

        javax.swing.GroupLayout jTextAreaPanelLayout = new javax.swing.GroupLayout(jTextAreaPanel);
        jTextAreaPanel.setLayout(jTextAreaPanelLayout);
        jTextAreaPanelLayout.setHorizontalGroup(
            jTextAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTextAreaPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
        );
        jTextAreaPanelLayout.setVerticalGroup(
            jTextAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTextAreaPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane)
                .addGap(0, 0, 0))
        );

        jSaveButton.setText("Save");
        jSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveButtonActionPerformed(evt);
            }
        });

        jGenericButton.setText("Browse/Close");
        jGenericButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGenericButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jButtonPanelLayout = new javax.swing.GroupLayout(jButtonPanel);
        jButtonPanel.setLayout(jButtonPanelLayout);
        jButtonPanelLayout.setHorizontalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jGenericButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSaveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jButtonPanelLayout.setVerticalGroup(
            jButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jButtonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jGenericButton)
                .addContainerGap())
        );

        jButtonPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jGenericButton, jSaveButton});

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

        jCompressionChoice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCompressionChoiceItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lossless Compressions");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jCompressPanelLayout = new javax.swing.GroupLayout(jCompressPanel);
        jCompressPanel.setLayout(jCompressPanelLayout);
        jCompressPanelLayout.setHorizontalGroup(
            jCompressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCompressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jCompressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCompressButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDecompressButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCompressionChoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jCompressPanelLayout.setVerticalGroup(
            jCompressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCompressPanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCompressionChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCompressButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDecompressButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout jSideMenuPanelLayout = new javax.swing.GroupLayout(jSideMenuPanel);
        jSideMenuPanel.setLayout(jSideMenuPanelLayout);
        jSideMenuPanelLayout.setHorizontalGroup(
            jSideMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jCompressPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jSideMenuPanelLayout.setVerticalGroup(
            jSideMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jSideMenuPanelLayout.createSequentialGroup()
                .addComponent(jCompressPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(183, 183, 183)
                .addComponent(jButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSideMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextAreaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSideMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextAreaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCompressButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCompressButtonActionPerformed
       String CompressedData = CP.compress(this , jTextArea.getText());
       if(CompressedData != null){
            this.jTextArea.setText(CompressedData);
       }
    }//GEN-LAST:event_jCompressButtonActionPerformed

    private void jDecompressButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDecompressButtonActionPerformed
        String DecompressedData = CP.decompress(this , jTextArea.getText());
        if(DecompressedData != null){
            this.jTextArea.setText(DecompressedData);
        }
    }//GEN-LAST:event_jDecompressButtonActionPerformed
    public final void Reset(){
        selectedFile = null;
        this.jGenericButton.setText("Browse");
        this.jSaveButton.setVisible(false);
        this.jCompressButton.setEnabled(false);
        this.jDecompressButton.setEnabled(false);
        this.jCompressionChoice.setEnabled(false);
        this.jTextArea.setText("");
    }
    public final void Start(){
        this.jGenericButton.setText("Close");
        this.jSaveButton.setVisible(true);
        this.jCompressButton.setEnabled(true);
        this.jDecompressButton.setEnabled(true);
        this.jCompressionChoice.setEnabled(true);
    }
    private void jGenericButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGenericButtonActionPerformed
        if(selectedFile != null){
              Reset();
        }else{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                this.Start();
                this.selectedFile = fileChooser.getSelectedFile();
                String fileContent = ReadFile(selectedFile.getAbsolutePath());
                this.jTextArea.setText(fileContent);
            }
        }
    }//GEN-LAST:event_jGenericButtonActionPerformed

    private void jSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveButtonActionPerformed
        String data = this.jTextArea.getText();
        try (FileWriter writer = new FileWriter(selectedFile)) {
            writer.write(data);
            JOptionPane.showMessageDialog(this, "File Saved Successfully" , "Success" , 1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error on save File :" + e.getMessage() , "Error" , 0);
        }
    }//GEN-LAST:event_jSaveButtonActionPerformed

    private void jCompressionChoiceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCompressionChoiceItemStateChanged
        this.CP = CompressionFactory.createCompression(evt.getItem().toString());
    }//GEN-LAST:event_jCompressionChoiceItemStateChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        DataCompression.StartGUI();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        DataCompression.StartGUI();
    }//GEN-LAST:event_formWindowClosing
    private String ReadFile(String fileName){
     try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder result = new StringBuilder();
    
            while ((line = reader.readLine()) != null) {
                result.append(line);
                if (reader.ready()) {
                    result.append("\n");
                }
            }
            return result.toString();
        }catch(Exception e){
            System.out.println("Error occured");
     }
        return null;
    }
    public static void StartGUI() {
        java.awt.EventQueue.invokeLater(() -> {
            new LosslessCompression().setVisible(true);
        });
    } 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jButtonPanel;
    private javax.swing.JButton jCompressButton;
    private javax.swing.JPanel jCompressPanel;
    private java.awt.Choice jCompressionChoice;
    private javax.swing.JButton jDecompressButton;
    private javax.swing.JButton jGenericButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jSaveButton;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel jSideMenuPanel;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JPanel jTextAreaPanel;
    // End of variables declaration//GEN-END:variables
}
