/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package di.uniba.map.ilsestosenso.ui;

import di.uniba.map.ilsestosenso.StopWatch;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Camil
 */
public class IlSestoSenso extends javax.swing.JDialog {
    
    
    private static Clip clip;
    private static final String SOUNDTRACK = "./resources/soundTrack.wav";
    private StopWatch time;
    
    private static void playMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates new form IlSestoSenso
     */
    public IlSestoSenso(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        audio.setSelected(true);
        playMusic(SOUNDTRACK);
        time = new StopWatch(Stopwatch);
        time.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        audio = new javax.swing.JToggleButton();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Stopwatch = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        audio.setText("AUDIO");
        audio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                audioActionPerformed(evt);
            }
        });

        jButton1.setText("INVIO");

        Stopwatch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Stopwatch.setText("00:00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(Stopwatch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(audio))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton1))
                            .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(audio)
                    .addComponent(Stopwatch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void audioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_audioActionPerformed
        if(audio.isSelected())
            playMusic(SOUNDTRACK);
        else
            clip.stop();
                
    }//GEN-LAST:event_audioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(IlSestoSenso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(IlSestoSenso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(IlSestoSenso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(IlSestoSenso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IlSestoSenso dialog = new IlSestoSenso(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Stopwatch;
    private javax.swing.JToggleButton audio;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
