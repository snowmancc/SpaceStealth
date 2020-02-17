/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import io.CommandSolver;
import java.awt.Cursor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import utils.Global;

/**
 *
 * @author user1
 */
public class main {

    public static void main(String args[]) {
        JFrame j = new JFrame();
        j.setTitle("Space Stealth");
        j.setLocation(0, 0);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(Global.WINDOWS_X_SIZE, Global.WINDOWS_Y_SIZE);
        
        GameJPanel jp = new GameJPanel();
        j.add(jp);
   
        j.setVisible(true);

        CommandSolver cs = new CommandSolver.Builder(Global.MILLISEC_PER_UPDATE, new int[][]{
            {KeyEvent.VK_W, Global.UP},
            {KeyEvent.VK_A, Global.LEFT},
            {KeyEvent.VK_S, Global.DOWN},
            {KeyEvent.VK_D, Global.RIGHT},
            {KeyEvent.VK_UP, Global.UP},
            {KeyEvent.VK_LEFT, Global.LEFT},
            {KeyEvent.VK_DOWN, Global.DOWN},
            {KeyEvent.VK_RIGHT, Global.RIGHT},
            {KeyEvent.VK_SPACE, Global.SPACE},
            {KeyEvent.VK_BACK_SPACE, Global.BACKSPACE},
            {KeyEvent.VK_1, Global.NUM_1},
            {KeyEvent.VK_ENTER, Global.ENTER},
            {KeyEvent.VK_B, Global.B},
            {KeyEvent.VK_P, Global.P_PASS},
            {KeyEvent.VK_ESCAPE, Global.END},
            }).keyCleanMode().enableMouseTrack(jp).trackChar().gen();
        
        addKeyListener(j, cs);
        cs.start();

        long startTime = System.currentTimeMillis();
        long lastRepaintTime = System.currentTimeMillis();
        long passedFrames = 0;
        while (true) {
            long currentTime = System.currentTimeMillis();
            long totalTime = currentTime - startTime;
            long targetTotalFrames = totalTime / Global.MILLISEC_PER_UPDATE;
            // input
            // input end
            while (passedFrames < targetTotalFrames) {
                jp.update(cs.update());
                passedFrames++;

            }

            if (Global.LIMIT_DELTA_TIME <= currentTime - lastRepaintTime) {
                lastRepaintTime = currentTime;
                j.repaint();
            }
        }
    }

    public static void addKeyListener(JFrame j, CommandSolver cs) {
        j.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                cs.trig(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                cs.trig(e.getKeyCode(), false);
            }
        });
        j.setFocusable(true);
    }
}
