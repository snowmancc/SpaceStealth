/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.actor;

import gameobject.MovableGameObject;
import gameobject.Polygon;
import java.awt.Graphics;
import utils.Vector;

/**
 *
 * @author muheng
 */
public class Test extends MovableGameObject{
    private ActorHelper actorHelper;
    public Test(int x, int y, int width, int height, double degree, double speed) {
        super(x, y, width, height, degree, speed);
        vectors = new Vector[4];
        for(int i = 0 ; i < vectors.length ; i++){
            vectors[i] = new Vector(0 , 0 );
            
        }
        vectors[0].setX(100).setY(100);
        vectors[1].setX(500).setY(100);
        vectors[2].setX(500).setY(500);
        vectors[3].setX(100).setY(400);
        poly = new Polygon(vectors , 100 ,100);
        
        /////待做
    }

    @Override
    public void move() {
        
    }

    @Override
    public void setMotionState(MotionState state) {
    }

    @Override
    public void paint(Graphics g) {
        g.drawPolygon(poly.getXArr(), poly.getYArr(), 4);
    }
    
}
