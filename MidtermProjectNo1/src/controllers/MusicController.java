/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author muheng
 */
public class MusicController {
    // 只能播放短音樂

//	URL file = getClass().getResource(PathBuilder.getImg(ImagePath.Music.BGM.B1));
//    AudioClip sound = Applet.newAudioClip(file); 
//	 public void playBgMusic(){
//		 sound.loop();
//	 }
//	public void stop() {
//		sound.stop();
//	}
    ////////////////////////////////////////////////////////////////////////////
    AudioInputStream audioInputStream;
    private String filename;
    private Thread thread;

    public MusicController(String wavfile) {
        filename = wavfile;
    }

    public void play() {
        if (thread == null) {
            thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        while (true) {
                            audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filename));
                            AudioFormat format = audioInputStream.getFormat();
                            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
                            line.open();
                            line.start();
                            int length = 0;
                            byte[] buffer = new byte[512];
                            while ((length = audioInputStream.read(buffer)) != -1) {
                                NumberFormat numberFormat = NumberFormat.getInstance();
                                numberFormat.setMaximumFractionDigits(2);
                                line.write(buffer, 0, length);
                            }
                            line.drain();
                            line.close();
                            audioInputStream.close();
                        }
                    } catch (UnsupportedAudioFileException e) {
//                        e.printStackTrace();
                    } catch (IOException e) {
//                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
//                        e.printStackTrace();
                    }
                }

            });
            thread.start();
        } else {
            try {
                thread.join();
                thread.start();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        try {
            audioInputStream.close();
        } catch (IOException ex) {
//            System.out.println("!!");
//            ex.printStackTrace();
        }
//        thread.interrupt();
        thread = null;
//        thread.stop();

    }

}
