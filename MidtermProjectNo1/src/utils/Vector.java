/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author muheng
 */
public class Vector {
    private double x;
    private double y;
    private double dot;
    private double len;
    private double length;
    private Vector normalize;
    public Vector(double x , double y){
        this.x = x;
        this.y = y;
      
    }
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    
    public Vector setX(double x){
        this.x = x;
        return this;
    }
    public Vector setY(double y){
        this.y = y;
        return this;
    }
    public double getLength(){
        length = Math.sqrt(this.x * this.x + this.y * this.y);
        return length;
    }//向量長度
    public Vector getNormalizes(){
        length = this.getLength();
        normalize.setX(this.x / length).setY(this.y / length);
        return normalize;
    }
    
    public double getDot(Vector vector){
        return this.x * vector.x + this.y * vector.y;
    }//內積
    public double getLengthOnVector(Vector vector){
        dot = this.getDot(vector);
        len = vector.getLength();
        return dot / len;
    }//在vector2的正射影長度
   
    public Vector getNormalL(){
        return new Vector(-this.y , this.x);
    }//法向量left
    public Vector getNormalR(){
        return new Vector(this.y , -this.x);
    }//法向量right
    
    public void rotate0(double angle){
        double newX = (this.x * Math.cos(angle) - this.y * Math.sin(angle));
        double newY =(this.x * Math.sin(angle) + this.y * Math.cos(angle));
        x = newX;
        y = newY;
    }
    public Vector rotateRaf(double angle , double x , double y){
        double newX = (this.x - x) * Math.cos(angle) - (this.y - y) * Math.sin(angle) + x;
        double newY = (this.y - y) * Math.cos(angle) + (this.x - x) * Math.sin(angle) + y;
        this.x = newX;
        this.y = newY;
        return this;
    }
    
    public String toString(){
        return "x: " + x + "  y: " + y ; 
    }
    
    
}
