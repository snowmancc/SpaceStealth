
package controllers;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import gameobject.Assignment;
import gameobject.GameObject;
import gameobject.MovableGameObject;
import gameobject.ProgressBarX;
import gameobject.SpecialMotionState;
import gameobject.Target;
import gameobject.actor.AircraftMain;
import gameobject.actor.EnemyLight;
import gameobject.actor.RockX;
import java.awt.image.BufferedImage;
import values.ImagePath;

public  class GameLevelControllerX {
        private GameLevelEditorX gle;
    private int currentLevel;
    
    public GameLevelControllerX(){
        gle = new GameLevelEditorX();
    }
    
    public void setLevel(int level){
        this.currentLevel = level;
        
    }
    
    public AircraftMain[] getAircrafts(){
        return  gle.getLevel(currentLevel).getAircrafts();
    }
    
    public EnemyLight[] getEnemyTests(){
        return gle.getLevel(currentLevel).getEnemyTests();
    }
    
    public BufferedImage[] getBackgrounds(){
        return gle.getLevel(currentLevel).getBackgrounds();
    }
    
    public Target getTarger(){
        return gle.getLevel(currentLevel).getTarget();
    }
    
    public Assignment getAssignment(){
        return gle.getLevel(currentLevel).getAssignment();
    }
    
    public RockX[] getRocks(){
        return gle.getLevel(currentLevel).getRocKs();
    }
    
    public MovableGameObject[] getCollisionObjects(){
        return gle.getLevel(currentLevel).getMovableGameObjects();
    }
    
    public ProgressBarX getProgressBarX(){
        return gle.getLevel(currentLevel).getProgressBarX();
    }
    
}
