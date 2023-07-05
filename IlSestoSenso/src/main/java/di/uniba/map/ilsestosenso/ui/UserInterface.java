/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package di.uniba.map.ilsestosenso.ui;

import di.uniba.map.ilsestosenso.database.DBScore;
import di.uniba.map.ilsestosenso.database.UserScore;
import java.awt.Color;
import java.awt.Image;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Camil
 */
public class UserInterface extends javax.swing.JFrame {
    
    Settings settings = null;
    UserScore userScore = null;

    /**
     * Creates new form UserInterface
     */
    public UserInterface() {
        initComponents();
        ImageIcon icon = new ImageIcon("./resources/settings2.png");
        Image originalImage = icon.getImage();
        Image resizedImage = originalImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH); // Imposta le dimensioni desiderate
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        settingsButton.setIcon(resizedIcon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wallpaper = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        scoreButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IL SESTO SENSO");
        setLocation(new java.awt.Point(450, 170));

        wallpaper.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Chiller", 0, 100)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("IL SESTO SENSO");

        startButton.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 20)); // NOI18N
        startButton.setText("INIZIA");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        scoreButton.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 20)); // NOI18N
        scoreButton.setText("PUNTEGGI");
        scoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreButtonActionPerformed(evt);
            }
        });

        helpButton.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 20)); // NOI18N
        helpButton.setText("HELP");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout wallpaperLayout = new javax.swing.GroupLayout(wallpaper);
        wallpaper.setLayout(wallpaperLayout);
        wallpaperLayout.setHorizontalGroup(
            wallpaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wallpaperLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(wallpaperLayout.createSequentialGroup()
                .addGroup(wallpaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(wallpaperLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1))
                    .addGroup(wallpaperLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(scoreButton)
                        .addGap(64, 64, 64)
                        .addComponent(helpButton))
                    .addGroup(wallpaperLayout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(startButton)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        wallpaperLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {helpButton, scoreButton, startButton});

        wallpaperLayout.setVerticalGroup(
            wallpaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wallpaperLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startButton)
                .addGap(30, 30, 30)
                .addGroup(wallpaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scoreButton)
                    .addComponent(helpButton))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        wallpaperLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {helpButton, scoreButton, startButton});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wallpaper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wallpaper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        
        UsernameInput inputUsername = new UsernameInput(this, true);
        this.setVisible(false);
        inputUsername.setVisible(true);
        this.setVisible(true);
        
        if(!inputUsername.getUsername().equals("-1")) {
            IlSestoSenso gameWindow = new IlSestoSenso(this, true);
            this.setVisible(false);
            gameWindow.setVisible(true);
            this.setVisible(true);
            userScore = new UserScore(inputUsername.getUsername(), LocalDate.now().toString());
            
            if(gameWindow.getTimeRecorded() != -1)
            {
                userScore.setTime(gameWindow.getTimeRecorded());
                try
                {
                    DBScore.connect();
                    DBScore.insertScore(userScore);
                    DBScore.disconnect();
                } catch (SQLException ex)
                {
                    Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
        
        
    }//GEN-LAST:event_startButtonActionPerformed

    private void scoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreButtonActionPerformed
        Scores scoresWindow = new Scores(this, true);
        this.setVisible(false);
        scoresWindow.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_scoreButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        Help helpWindow = new Help(this, true);
        this.setVisible(false);
        helpWindow.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_helpButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed

        settings = new Settings(this, true);

        //Settings.setThemeButtonValue();
        this.setVisible(false);
        settings.setVisible(true);
        this.setVisible(true);
        
        if (Settings.isDark())
        {
            wallpaper.setBackground(new Color(51, 51, 51));
        } else
        {
            wallpaper.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_settingsButtonActionPerformed

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
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton scoreButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JButton startButton;
    private javax.swing.JPanel wallpaper;
    // End of variables declaration//GEN-END:variables
}
