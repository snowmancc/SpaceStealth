/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author muheng
 */
public class Timmer {
    private long startTime;
    private long totalMilliSecond;
    private int targetSecond;
    private long currentTime;
    private int totalSecond;
    private long passStart;
    private long passEnd;
    private long passTime;
    private boolean isPass;
    
    private int secondDigits;
    private int secondTenDigits;
    private int minuteDigits;
    private int minuteTenDigits;
    
    private int x;
    private int y;
    private int width;
    private int height;
    private static final Font FONT_KEN1 = new Font("KenVector Future Thin", Font.BOLD, 60);
    private static final Color COLOR_WHITE = new Color(248, 248, 255, 125);
    public Timmer(int x,int y, int width,  int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isPass = false;
    }
    
    public Timmer(int x, int y, int width,  int height, int targetSecond){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.targetSecond = targetSecond;
    }
    
    
    public void timeStart(){
        startTime = System.currentTimeMillis();
    }
    
    public void timeUpDate(){
        if(!isPass){
            currentTime = System.currentTimeMillis() - this.startTime;
        }
        else{
           this.startTime = passTime; 
        }
        
         convertTime();
    }
    
    public void timeStop(){
        
        totalSecond = (int)(currentTime / 1000);
       
//        System.out.println(totalSecond);
    }
    
    private void convertTime(){
        secondDigits = (int)(currentTime / 1000) % 60 % 10;
        secondTenDigits = (int)(currentTime / 1000) % 60 / 10;
        
        minuteDigits = (int)(currentTime / 1000) / 60 % 10;
        minuteTenDigits = (int)(currentTime / 1000) / 60 / 10;
    }
    
    public int getTotalSecond(){
        return totalSecond;
    }
    
    
    
    public boolean isTimeUp(){
        return currentTime / 1000 >= targetSecond;
    }
    
    public void passTime(){
        this.passTime = System.currentTimeMillis();
        isPass = true;
    }
    
    public void continueTime(){
        isPass = false;
        
    }
    
    
    
    public void paint(Graphics g) {
        
        g.setColor(COLOR_WHITE);
        g.setFont(FONT_KEN1);
        g.drawString("" + minuteTenDigits, x- 100, y);
        g.drawString("" + ( minuteDigits), x - 50, y);
        
        g.drawString(":", x, y);
        
        g.drawString("" + (secondTenDigits), x + 50, y);
        g.drawString("" + ( secondDigits), x + 100, y);
    }
    
    
}
