/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.ilsestosenso;

/**
 * @author IlSestoSenso
 */
public class StopWatch extends Thread {

    private javax.swing.JLabel timerLabel;
    private boolean isRunning = true;
    private int seconds = 0;

    public StopWatch(javax.swing.JLabel label) {
        this.timerLabel = label;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(1000);
                seconds++;
                String second = " ";
                String minute = " ";
                if (seconds % 60 < 10) {
                    second = "0" + seconds % 60;
                } else {
                    second = "" + (seconds % 60);
                }

                if (seconds / 60 < 10) {
                    minute = "0" + seconds / 60;
                } else {
                    minute = "" + (seconds / 60);
                }

                timerLabel.setText(minute + ":" + second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void arrestStopWatch() {
        isRunning = false;
    }

    public int getTime() {
        return seconds;
    }

}
