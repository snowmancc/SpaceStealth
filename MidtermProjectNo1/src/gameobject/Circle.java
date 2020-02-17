/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import utils.Vector;

/**
 *
 * @author muheng
 */
public class Circle  extends Polygon{
    private Vector center;
    private double radius;
    public Circle(Vector center , double radius){
        super(center , radius);
        this.center = center;
        this.radius = radius;
    }
    public Vector getCenter(){
        return center;
    }
    public double getRadius(){
        return radius;
    }
    public void setCenter(Vector center){
        this.center = center;
    }
    public void setRadius(double radius){
        this.radius = radius;
    }
}
