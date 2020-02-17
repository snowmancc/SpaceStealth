/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import utils.DelayCounter;

/**
 *
 * @author muheng
 */
public class ProgressBarX extends GameObject {//能量條

  
    private int[] xArr, caseX, yArr, caseY;
    private Graphics2D g1;
    private Color color1, color2;
    private int current;
    private DelayCounter delayCounter;

    public ProgressBarX(double x, double y, double width, double height) {
        super(x, y, width, height, 0);
        current = 0;
        delayCounter = new DelayCounter(2);
        xArr = new int[4];
        yArr = new int[4];
        caseX = new int[4];
        caseY = new int[4];

        color1 = new Color(165, 222, 228 , 100);
        color2 = new Color(215, 84, 85 , 100);

        caseX[0] = (int) x ;
        caseX[1] = (int) (x + width );
        caseX[2] = (int) (x + width ) ;
        caseX[3] = (int) x ;

        caseY[0] = (int) y;
        caseY[1] = (int) y;
        caseY[2] = (int) (y + height);
        caseY[3] = (int) (y + height);
        //////
        xArr[0] = (int) x + 5;
        xArr[1] = (int) (x + width -5);
        xArr[2] = (int) (x + width -5);
        xArr[3] = (int) x + 5;

        yArr[0] = (int) (y + height);
        yArr[1] = (int) (y + height);
        yArr[2] = (int) (y + height);
        yArr[3] = (int) (y + height);
        
    }

    public void setProgress() {
        if(delayCounter.update()){
            current += 5;
        }
        if(current <= 100){
            yArr[3] = yArr[2] = (int) ( y + height  - height  / 100 * current);
        }
    }
    
    public void reset(){
        current = 0;
         yArr[3] = yArr[2] = (int)(y + height);
    }
    
    public boolean isFinish(){
        return current >= 100;
    }

    @Override
    public void paint(Graphics g) {
        g1 = (Graphics2D) g.create();
        g1.setStroke(new BasicStroke(5.0f));
        g1.setColor(color2);
        g1.fillPolygon(xArr, yArr, 4);
        
        g1.setColor(color1);
        g1.drawPolygon(caseX, caseY, 4);
        
//        System.out.println("!!");

    }

}
