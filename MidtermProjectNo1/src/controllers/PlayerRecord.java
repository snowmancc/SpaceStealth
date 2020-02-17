/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerRecord implements Serializable{
    private String retStrFormatNowDate;
    private String name;
    private int score;
    private int dieCount;
    private String level;
    
    public PlayerRecord(String name){
        this.name = name;
        recordDate();
        dieCount = 0;
        score = 0;
        level = "";
    }
    
    public PlayerRecord(String retStrFormatNowDate, String name, String dieCount, String score){
        this.name = name;
        this.retStrFormatNowDate = retStrFormatNowDate;
        
        if(!dieCount.equals(" ")){
            this.dieCount = Integer.valueOf(dieCount);
        }
        
        if(!score.equals(" ")){
            this.score = Integer.valueOf(score);
        }
        
        level = "";
    }
    
    public String getName(){
        return this.name;
    }
    
    
    private void recordDate(){
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
        retStrFormatNowDate = sdFormatter.format(nowTime);
    }
    
    public String getDate(){
        return this.retStrFormatNowDate;
    }
    
    public void setLevel(String level){
        this.level = level;
    }
    
    public String getLevel(){
        return level;
    }
    
    public void setScore(String score){
        this.score = Integer.valueOf(score);
    }
    
    public int getScore(){
        return this.score;
    }
    
    public void playerDieCount(){
        this.dieCount++;
    }
    
    public int getDieCount(){
        return this.dieCount;
    }
    
    public String getRecord(){
        
        return retStrFormatNowDate + "," + name + "," + dieCount + "," + score;
    }
    
    public boolean isBetter(PlayerRecord pr){
        
        return (pr.score == 0) ? true :  this.score < pr.score ;
    }
}
