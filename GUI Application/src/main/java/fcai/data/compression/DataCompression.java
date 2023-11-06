/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fcai.data.compression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class DataCompression extends javax.swing.JFrame {
    private File selectedFile;
    
    private Compression CP;

    public DataCompression() {
        initComponents();
        selectedFile = null;
        jGenericButton.setText("Browse");
        jSaveButton.setVisible(false);
        jCompressionChoice.add("LZ77");
        jCompressionChoice.add("LZW");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(727, 510));

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
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))
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
                    .addComponent(jSaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jGenericButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

        javax.swing.GroupLayout jCompressPanelLayout = new javax.swing.GroupLayout(jCompressPanel);
        jCompressPanel.setLayout(jCompressPanelLayout);
        jCompressPanelLayout.setHorizontalGroup(
            jCompressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCompressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jCompressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCompressButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDecompressButton, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(jCompressionChoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jCompressPanelLayout.setVerticalGroup(
            jCompressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCompressPanelLayout.createSequentialGroup()
                .addContainerGap()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(jButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jGenericButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGenericButtonActionPerformed
        if(selectedFile != null){
            selectedFile = null;
            jGenericButton.setText("Browse");
            jTextArea.setText("");
            jSaveButton.setVisible(false);
        }else{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                jGenericButton.setText("Close");
                jSaveButton.setVisible(true);

                // Get the selected file
                selectedFile = fileChooser.getSelectedFile();

                // Now you can read the selected file and display its contents
                String fileContent = ReadFile(selectedFile.getAbsolutePath());

                // Display the file content in the JTextArea
                jTextArea.setText(fileContent);
            }
        }
    }//GEN-LAST:event_jGenericButtonActionPerformed

    private void jCompressButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCompressButtonActionPerformed
       if(selectedFile == null){
            JOptionPane.showMessageDialog(this, "Please Select a File First ");
            return;
       }
       String selectedItem = jCompressionChoice.getSelectedItem();
       CP = createCompressionAlgorithm(selectedItem);
       String CompressedData = CP.compress(this , jTextArea.getText());
       if(CompressedData != null){
            jTextArea.setText(CompressedData);
       }
    }//GEN-LAST:event_jCompressButtonActionPerformed

    private void jDecompressButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDecompressButtonActionPerformed
        if(selectedFile == null){
            JOptionPane.showMessageDialog(this, "Please Select a File First " , "Info" , 2);
            return;
       }
        String selectedItem = jCompressionChoice.getSelectedItem();
        CP = createCompressionAlgorithm(selectedItem);
        String DecompressedData = CP.decompress(this , jTextArea.getText());
        if(DecompressedData != null){
            jTextArea.setText(DecompressedData);
        }
    }//GEN-LAST:event_jDecompressButtonActionPerformed
    
    public Compression createCompressionAlgorithm(String algorithm) {
        if ("LZ77".equals(algorithm)) {
            return new LZ77();
        }else if("LZW".equals(algorithm)) {
            return new LZW();
        }
        return null;
    }
    
    private void jSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveButtonActionPerformed
        String data = jTextArea.getText();
        try (FileWriter writer = new FileWriter(selectedFile)) {
            writer.write(data);
            JOptionPane.showMessageDialog(this, "File Saved Successfully" , "Success" , 1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error on save File :" + e.getMessage() , "Error" , 0);
        }
    }//GEN-LAST:event_jSaveButtonActionPerformed

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
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new DataCompression().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jButtonPanel;
    private javax.swing.JButton jCompressButton;
    private javax.swing.JPanel jCompressPanel;
    private java.awt.Choice jCompressionChoice;
    private javax.swing.JButton jDecompressButton;
    private javax.swing.JButton jGenericButton;
    private javax.swing.JButton jSaveButton;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel jSideMenuPanel;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JPanel jTextAreaPanel;
    // End of variables declaration//GEN-END:variables
}
