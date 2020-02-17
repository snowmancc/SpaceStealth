/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import utils.Vector;

/**
 *
 * @author muheng
 */
public class Boundary  {
    public Line boundaryLeft;
    public Line boundaryRight;
    public Line boundaryUp;
    public Line boundaryDown;
    public boolean left , right , up , down;
    
    public Boundary(int sizeX , int sizeY){
        boundaryLeft = new Line(0 , 0 , 1 , sizeY , 0);
        boundaryRight = new Line( sizeX  , 0 , 1 , sizeY , 0);
        boundaryUp = new Line(0 , 0 , sizeX , 1 , 0);
        boundaryDown = new Line(0 , sizeY , sizeX  , 1 , 0);
        
        left = right = up =  down  = false;
    }
    public boolean isCollision(GameObject obj){
        left = right = up =  down  = false;
        left =  boundaryLeft.isCollision(obj);
        right = boundaryRight.isCollision(obj);
        up = boundaryUp.isCollision(obj);
        down = boundaryDown.isCollision(obj);
        if(left || right || up || down){
            return true;
        }
        return false;
    }
    
    public void paint(Graphics g){//paint 只有在測試的時候才會用
        g.setColor(Color.ORANGE);
        
        
        if(left || right || up || down){
            g.setColor(Color.green);
        }
        boundaryLeft.paint(g);
        boundaryRight.paint(g);
        boundaryUp.paint(g);
        boundaryDown.paint(g);
    }
    


    
    
    
    public class Line extends GameObject{

        public Line(double x, double y, double width, double height, double degree) {
            super(x, y, width , height , degree);
            super.vectors[0]  = new Vector(x, y);
            super.vectors[1] = new Vector(x + width, y);
            super.vectors[2] = new Vector(x + width, y + height);
            super.vectors[3] = new Vector(x  , y + height );
            poly = new Polygon(vectors, center.getX(), center.getY());
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(Color.green);
            this.poly.paint(g);
            
            
        }
        
    }

    
    


    
}
