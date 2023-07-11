/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package di.uniba.map.ilsestosenso.ui;

import di.uniba.map.ilsestosenso.Engine;
import di.uniba.map.ilsestosenso.StopWatch;
import di.uniba.map.ilsestosenso.games.IlSestoSensoGame;
import di.uniba.map.ilsestosenso.type.AdvObject;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import javax.swing.ListModel;

/**
 *
 * @author Camil
 */
public class IlSestoSenso extends javax.swing.JDialog {

    private static Clip clip;
    private static final String SOUNDTRACK = "./resources/soundTrack.wav";
    private StopWatch time;
    private int timeRecorded = -1;
    Engine engine = new Engine(new IlSestoSensoGame());
    private DefaultListModel<AdvObject> model;
    
    public int getTimeRecorded(){
        return timeRecorded;
    }

    private static void playMusic(String filePath) {
        try
        {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void stopMusic(){
        if(clip!=null)
            clip.stop();
    }

    public class TextAreaOutputStream extends OutputStream {

        private JTextArea textArea;

        public TextAreaOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            // Scrivi il singolo byte nell'area di testo
            textArea.append(String.valueOf((char) b));
            // Scrolla automaticamente l'area di testo alla fine
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
    
    public void initSettings(){
        if (Settings.isDark()){
            input.setBackground(new Color(51, 51, 51));
            input.setForeground(new Color(255, 255, 255));
            output.setBackground(new Color(51, 51, 51));
            output.setForeground(new Color(255, 255, 255));
            inventory.setBackground(new Color(51, 51, 51));
            inventory.setForeground(new Color(255, 255, 255));
        }

        if (Settings.isWithoutAudio()){
            audio.setSelected(true);
        } else{
            audio.setSelected(false);
            playMusic(SOUNDTRACK);
        }

    }

    /**
     * Creates new form IlSestoSenso
     */
    public IlSestoSenso(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        initSettings();
        

        TextAreaOutputStream outputStream = new TextAreaOutputStream(output);

        // Reindirizza System.out a TextAreaOutputStream
        System.setOut(new PrintStream(outputStream));

        time = new StopWatch(Stopwatch);
        time.start();
        engine.start();
        
    }
    
    private void updateInventory(){
        model = new DefaultListModel<>();
        inventory.setModel((ListModel) model);
        
        List<AdvObject> objects = engine.getGame().getInventory();

        model.clear();
        for (AdvObject o : objects)
        {
            model.addElement(o);
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        input = new javax.swing.JTextField();
        send = new javax.swing.JButton();
        Stopwatch = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inventory = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Partita");
        setLocation(new java.awt.Point(450, 170));
        setResizable(false);

        audio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        audio.setText("AUDIO");
        audio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                audioActionPerformed(evt);
            }
        });

        output.setEditable(false);
        output.setColumns(20);
        output.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        output.setRows(5);
        jScrollPane1.setViewportView(output);

        input.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 15)); // NOI18N
        input.setForeground(new java.awt.Color(0, 0, 0));
        input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputKeyPressed(evt);
            }
        });

        send.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        send.setText("INVIO");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        Stopwatch.setFont(new java.awt.Font("Stencil", 0, 18)); // NOI18N
        Stopwatch.setText("00:00");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        inventory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(inventory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(Stopwatch)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                    .addComponent(input))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(audio, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Stopwatch)
                    .addComponent(audio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(input, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(send, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void audioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_audioActionPerformed
        if (!audio.isSelected())
        {
            playMusic(SOUNDTRACK);
        } else
        {
            stopMusic();
        }
    }//GEN-LAST:event_audioActionPerformed

    private void inputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            engine.setCommand(input.getText());
            output.append("[" + input.getText() + "]" + "\n\n");
            input.setText("");
            
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(IlSestoSenso.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            updateInventory();
            
            if (!engine.isInGame()){
                time.arrestStopWatch();

                if (engine.getExitCode() == 1){
                    timeRecorded = time.getTime();
                } else {
                    timeRecorded = -1;
                }
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_inputKeyPressed

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        engine.setCommand(input.getText());
        input.setText("");
    
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(IlSestoSenso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        updateInventory();

        if (!engine.isInGame()){
            time.arrestStopWatch();

            if (engine.getExitCode() == 1) {
                timeRecorded = time.getTime();
            } else {
                timeRecorded = -1;
            }
            this.setVisible(false);
        }
    }//GEN-LAST:event_sendActionPerformed

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
    private javax.swing.JTextField input;
    private javax.swing.JList<String> inventory;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea output;
    private javax.swing.JButton send;
    // End of variables declaration//GEN-END:variables
}
