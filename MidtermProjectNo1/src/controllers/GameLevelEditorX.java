package controllers;

import controllers.GameLevelEditorX.LevelX.LevelX0;
import controllers.GameLevelEditorX.LevelX.LevelX1;
import controllers.GameLevelEditorX.LevelX.LevelX2;
import controllers.GameLevelEditorX.LevelX.LevelX4;
import controllers.GameLevelEditorX.LevelX.LevelX5;
import gameobject.Assignment;
import gameobject.MovableGameObject;
import gameobject.ProgressBarX;
import gameobject.SpecialMotionState;
import gameobject.Target;
import gameobject.actor.AircraftMain;
import gameobject.actor.EnemyLight;
import gameobject.actor.RockX;
import java.awt.image.BufferedImage;
import utils.Global;
import values.ImagePath;

public class GameLevelEditorX {
    
    public  static abstract class LevelX{  //繼承這個Level父類別 來擴充關卡   新增完成要在GameLevelEditor人工載入
        public abstract AircraftMain[] getAircrafts();
        public abstract EnemyLight[] getEnemyTests();
        public abstract BufferedImage[] getBackgrounds();
        public abstract Target getTarget();
        public abstract Assignment getAssignment();
        public abstract RockX[] getRocKs();
        public abstract MovableGameObject[] getMovableGameObjects();
        public abstract ProgressBarX getProgressBarX();
        
        public static class LevelX0 extends LevelX{

            @Override
            public AircraftMain[] getAircrafts() {
                 AircraftMain[]  aircraftMains = new  AircraftMain[1];
                 aircraftMains[0] =new AircraftMain(100,400,2);
                 return aircraftMains;
            }

            @Override
            public EnemyLight[] getEnemyTests() {
                 EnemyLight[]  enemyLights = new  EnemyLight[1];
                 enemyLights[0] = new EnemyLight(768,150,2);
                 enemyLights[0].setMotionState(new SpecialMotionState.Enemy1());
                 return enemyLights;
            }

            @Override
            public BufferedImage[] getBackgrounds() {
                 ImageResourceController irc = ImageResourceController.getInstance();
                 BufferedImage[] bufferedImages =  new BufferedImage[1];
                 bufferedImages[0] = irc.tryGetImage(PathBuilder.getBackGrounds(ImagePath.Backgrounds.BACKGROUNDS_BLUE));
                 return bufferedImages;
            }

            @Override
            public Target getTarget() {
                 Target  target = new  Target(1100, 700, 64, 64);
                 return target;
            }

            @Override
            public Assignment getAssignment() {
                 Assignment  assignment = new Assignment(740, 500, 64, 64);
                 return assignment;
            }

            @Override
            public RockX[] getRocKs() {
                 RockX[]  rockBigBrowns = new  RockX[10];
                    rockBigBrowns[0] = new RockX(200, 600 , 2);
                    
                    rockBigBrowns[1] = new RockX(400, 420 , 2);
                    rockBigBrowns[2] = new RockX(340, 300 , 2);
                    rockBigBrowns[3] = new RockX(450, 200 , 2);
                    rockBigBrowns[4] = new RockX(700, 300 , 2);
                    rockBigBrowns[5] = new RockX(900, 280 , 2);
                    rockBigBrowns[6] = new RockX(1000, 230 , 2);
                    rockBigBrowns[7] = new RockX(850, 700 , 2);
                    rockBigBrowns[8] = new RockX(900, 550 , 2);
                    rockBigBrowns[9] = new RockX(1050, 550 , 2);
                    
                    
                 return rockBigBrowns;
            }

            @Override
            public MovableGameObject[] getMovableGameObjects() {
                 MovableGameObject[]  movableGameObjects = new  MovableGameObject[1];
                 return movableGameObjects;
            }
    
            @Override
            public ProgressBarX getProgressBarX() {
                 ProgressBarX  progressBarX =new ProgressBarX(5, 50, 50, Global.WINDOWS_Y_SIZE * 7 / 8 );
                 
                 return progressBarX;
            }
            
        }  //原地探巡
        
        public static class LevelX1 extends LevelX{

            @Override
            public AircraftMain[] getAircrafts() {
                 AircraftMain[]  aircraftMains = new  AircraftMain[1];
                 aircraftMains[0] =new AircraftMain(100,400,1);
                 return aircraftMains;
            }

            @Override
            public EnemyLight[] getEnemyTests() {
                 EnemyLight[]  enemyLights = new  EnemyLight[2];
                 enemyLights[0] = new EnemyLight(450,200,1);
                 enemyLights[0].setMotionState(new SpecialMotionState.Enemy2());
                 enemyLights[1] = new EnemyLight(1250,600,1);
                 enemyLights[1].setMotionState(new SpecialMotionState.Enemy3());
                 return enemyLights;
            }

            @Override
            public BufferedImage[] getBackgrounds() {
                 ImageResourceController irc = ImageResourceController.getInstance();
                 BufferedImage[] bufferedImages =  new BufferedImage[1];
                 bufferedImages[0] = irc.tryGetImage(PathBuilder.getBackGrounds(ImagePath.Backgrounds.BACKGROUNDS_BLUE));
                 return bufferedImages;
            }

            @Override
            public Target getTarget() {
                 Target  target = new  Target(1100, 150, 64, 64);
                 return target;
            }

            @Override
            public Assignment getAssignment() {
                 Assignment  assignment = new Assignment(830, 460, 64, 64);
                 return assignment;
            }

            @Override
            public RockX[] getRocKs() {
                 RockX[]  rockX = new  RockX[14];
                    rockX[0] = new RockX(300, 250 , 1);
                    rockX[1] = new RockX(350, 370 , 1);
                    rockX[2] = new RockX(400, 480 , 1);
                    rockX[3] = new RockX(550, 650 , 1);
                    rockX[4] = new RockX(600, 100 , 1);
                    rockX[5] = new RockX(650, 250 , 1);
                    rockX[6] = new RockX(700, 400 , 1);
                    rockX[7] = new RockX(850, 500 , 1);
                    rockX[8] = new RockX(1000, 560 , 1);
                    rockX[9] = new RockX(750, 350 , 1);
                    rockX[10] = new RockX(1000, 80 , 1);
                    rockX[11] = new RockX(980, 250 , 1);
                    rockX[12] = new RockX(1150, 220 , 1);
                    rockX[13] = new RockX(1250, 340 , 1);
                 return rockX;
            }

            @Override
            public MovableGameObject[] getMovableGameObjects() {
                 MovableGameObject[]  movableGameObjects = new  MovableGameObject[1];
                 return movableGameObjects;
            }
    
            @Override
            public ProgressBarX getProgressBarX() {
                 ProgressBarX  progressBarX =new ProgressBarX(5, 50, 50, Global.WINDOWS_Y_SIZE * 7 / 8 );
                 return progressBarX;
            }
            
        }  //兩道巡邏
        
        public static class LevelX2 extends LevelX{

            @Override
            public AircraftMain[] getAircrafts() {
                 AircraftMain[]  aircraftMains = new  AircraftMain[1];
                 aircraftMains[0] =new AircraftMain(200,600,0);
                 return aircraftMains;
            }

            @Override
            public EnemyLight[] getEnemyTests() {
                 EnemyLight[]  enemyLights = new  EnemyLight[8];
                 
                 enemyLights[0] = new EnemyLight(330,150,0);
                 enemyLights[0].setMotionState(new SpecialMotionState.Enemy5());
                 enemyLights[0].setSpeed(3);
                 
                 enemyLights[1] = new EnemyLight(530,700,0);
                 enemyLights[1].setMotionState(new SpecialMotionState.Enemy6());
                 enemyLights[1].setSpeed(3);
                 
                 enemyLights[2] = new EnemyLight(880,150,0);
                 enemyLights[2].setMotionState(new SpecialMotionState.Enemy7());
                 enemyLights[2].setSpeed(3);
                 
                 enemyLights[3] = new EnemyLight(1120,700,0);
                 enemyLights[3].setMotionState(new SpecialMotionState.Enemy8());
                 enemyLights[3].setSpeed(3);
                 
                 enemyLights[4] = new EnemyLight(330,700,0);
                 enemyLights[4].setMotionState(new SpecialMotionState.Enemy9());
                 enemyLights[4].setSpeed(3);
                 
                 enemyLights[5] = new EnemyLight(530,150,0);
                 enemyLights[5].setMotionState(new SpecialMotionState.Enemy10());
                 enemyLights[5].setSpeed(3);
                 
                 enemyLights[6] = new EnemyLight(880,700,0);
                 enemyLights[6].setMotionState(new SpecialMotionState.Enemy11());
                 enemyLights[6].setSpeed(3);
                 
                 enemyLights[7] = new EnemyLight(1120,150,0);
                 enemyLights[7].setMotionState(new SpecialMotionState.Enemy12());
                 enemyLights[7].setSpeed(3);
                 
                 return enemyLights;
            }

            @Override
            public BufferedImage[] getBackgrounds() {
                 ImageResourceController irc = ImageResourceController.getInstance();
                 BufferedImage[] bufferedImages =  new BufferedImage[1];
                 bufferedImages[0] = irc.tryGetImage(PathBuilder.getBackGrounds(ImagePath.Backgrounds.BACKGROUNDS_BLUE));
                 return bufferedImages;
            }

            @Override
            public Target getTarget() {
                 Target  target = new  Target(500, 500, 64, 64);
                 return target;
            }

            @Override
            public Assignment getAssignment() {
                 Assignment  assignment = new Assignment(1200, 150, 64, 64);
                 return assignment;
            }

            @Override
            public RockX[] getRocKs() {
                 RockX[]  rockBigBrowns = new  RockX[9];

                 rockBigBrowns[0] = new RockX(400 ,  400 , 1);
                 rockBigBrowns[1] = new RockX(200 ,  400 , 1);
                 rockBigBrowns[2] = new RockX(1000 ,  400  , 1);
                 rockBigBrowns[3] = new RockX(1200 ,  400  , 1);
                 rockBigBrowns[4] = new RockX(700 ,  400  , 2);
                 rockBigBrowns[5] = new RockX(700 ,  200 , 1);
                 rockBigBrowns[6] = new RockX(700 ,  40 , 1);
                 rockBigBrowns[7] = new RockX(700 ,  580  , 1);
                 rockBigBrowns[8] = new RockX(700 ,  750  , 1);
                 
                 return rockBigBrowns;
            }

            @Override
            public MovableGameObject[] getMovableGameObjects() {
                 MovableGameObject[]  movableGameObjects = new  MovableGameObject[1];
                 return movableGameObjects;
            }
    
            @Override
            public ProgressBarX getProgressBarX() {
                 ProgressBarX  progressBarX =new ProgressBarX(5, 50, 50, Global.WINDOWS_Y_SIZE * 7 / 8 );
                 return progressBarX;
            }
            
        } //
         
        public static class LevelX4 extends LevelX{

            @Override
            public AircraftMain[] getAircrafts() {
                 AircraftMain[]  aircraftMains = new  AircraftMain[1];
                 aircraftMains[0] =new AircraftMain(200,600,0);
                 return aircraftMains;
            }

            @Override
            public EnemyLight[] getEnemyTests() {
                 EnemyLight[]  enemyLights = new  EnemyLight[8];
                 
                 enemyLights[0] = new EnemyLight(200,150,0);
                 enemyLights[0].setMotionState(new SpecialMotionState.SweepingRobot());
                 
                 enemyLights[1] = new EnemyLight(400,300,0);
                 enemyLights[1].setMotionState(new SpecialMotionState.SweepingRobot());
                
                 enemyLights[2] = new EnemyLight(600,300,0);
                 enemyLights[2].setMotionState(new SpecialMotionState.SweepingRobot());
                 
                 enemyLights[3] = new EnemyLight(750,300,0);
                 enemyLights[3].setMotionState(new SpecialMotionState.SweepingRobot());
                 
                 enemyLights[4] = new EnemyLight(900,300,0);
                 enemyLights[4].setMotionState(new SpecialMotionState.SweepingRobot());
                 
                 enemyLights[5] = new EnemyLight(1200,200,0);
                 enemyLights[5].setMotionState(new SpecialMotionState.SweepingRobot());
                 
                 enemyLights[6] = new EnemyLight(500,500,0);
                 enemyLights[6].setMotionState(new SpecialMotionState.SweepingRobot());
                 
                 enemyLights[7] = new EnemyLight(700,200,0);
                 enemyLights[7].setMotionState(new SpecialMotionState.SweepingRobot());
                 return enemyLights;
            }

            @Override
            public BufferedImage[] getBackgrounds() {
                 ImageResourceController irc = ImageResourceController.getInstance();
                 BufferedImage[] bufferedImages =  new BufferedImage[1];
                 bufferedImages[0] = irc.tryGetImage(PathBuilder.getBackGrounds(ImagePath.Backgrounds.BACKGROUNDS_BLUE));
                 return bufferedImages;
            }

            @Override
            public Target getTarget() {
                 Target  target = new  Target(500, 500, 64, 64);
                 return target;
            }

            @Override
            public Assignment getAssignment() {
                 Assignment  assignment = new Assignment(1200, 150, 64, 64);
                 return assignment;
            }

            @Override
            public RockX[] getRocKs() {
                 RockX[]  rockBigBrowns = new  RockX[64];

                int index = 0;


                for(int i = 0; i < 20; i++){
                     rockBigBrowns[index ++] = new RockX(50 + 80 * i,  50 , 1);
                }
                 for(int i = 0; i < 20; i++){
                     rockBigBrowns[index ++] = new RockX(50 + 80 * i,  700 , 1);
                }
                 for(int i = 0; i < 8; i++){
                     rockBigBrowns[index ++] = new RockX(50 ,  100 + 80 * i , 1);
                 }
                 for(int i = 0; i < 8; i++){
                     rockBigBrowns[index ++] = new RockX(1300 ,  100 + 80 * i , 1);
                 }
                 rockBigBrowns[index ++] = new RockX(600 ,  200, 0);
                 rockBigBrowns[index ++] = new RockX(800 ,  300 , 1);
                 rockBigBrowns[index ++] = new RockX(400 ,  500 , 0);
                 rockBigBrowns[index ++] = new RockX(1000 ,  600  , 1);
                 rockBigBrowns[index ++] = new RockX(300 ,  600, 2);
                 rockBigBrowns[index ++] = new RockX(400 ,  300 , 1);
                 rockBigBrowns[index ++] = new RockX(500 ,  600 , 0);
                 rockBigBrowns[index ++] = new RockX(1000 ,  300  , 1);
                 return rockBigBrowns;
            }

            @Override
            public MovableGameObject[] getMovableGameObjects() {
                 MovableGameObject[]  movableGameObjects = new  MovableGameObject[1];
                 return movableGameObjects;
            }
    
            @Override
            public ProgressBarX getProgressBarX() {
                 ProgressBarX  progressBarX =new ProgressBarX(5, 50, 50, Global.WINDOWS_Y_SIZE * 7 / 8 );
                 return progressBarX;
            }
            
        }  //掃地機器人
          
        public static class LevelX5 extends LevelX{

            @Override
            public AircraftMain[] getAircrafts() {
                 AircraftMain[]  aircraftMains = new  AircraftMain[1];
                 aircraftMains[0] =new AircraftMain(200,600,0);
                 return aircraftMains;
            }

            @Override
            public EnemyLight[] getEnemyTests() {
                 EnemyLight[]  enemyLights = new  EnemyLight[8];
                 
                 for(int i = 0; i < 8; i++){
                    enemyLights[i] = new EnemyLight((int)(Math.random()* 900 + 300),(int)(Math.random()* 700 + 50),(int)(Math.random()* 3));
                    enemyLights[i].setMotionState(new SpecialMotionState.TotalAct());
                    enemyLights[i].setSpeed((int)(Math.random()* 3 + 2));
                 }
                 
                 return enemyLights;
            }

            @Override
            public BufferedImage[] getBackgrounds() {
                 ImageResourceController irc = ImageResourceController.getInstance();
                 BufferedImage[] bufferedImages =  new BufferedImage[1];
                 bufferedImages[0] = irc.tryGetImage(PathBuilder.getBackGrounds(ImagePath.Backgrounds.BACKGROUNDS_BLUE));
                 return bufferedImages;
            }

            @Override
            public Target getTarget() {
                 Target  target = new  Target(100, 700, 64, 64);
                 return target;
            }

            @Override
            public Assignment getAssignment() {
                 Assignment  assignment = new Assignment(1350, 150, 64, 64);
                 return assignment;
            }

            @Override
            public RockX[] getRocKs() {
                 RockX[]  rockBigBrowns = new  RockX[20];
                 
                for(int i = 0; i < 20; i++){
                    rockBigBrowns[i] = new RockX((int)(Math.random()* 1000 + 200),(int)(Math.random()* 800 + 50),(int)(Math.random()* 3));
                 }
                 return rockBigBrowns;
            }

            @Override
            public MovableGameObject[] getMovableGameObjects() {
                 MovableGameObject[]  movableGameObjects = new  MovableGameObject[1];
                 return movableGameObjects;
            }
    
            @Override
            public ProgressBarX getProgressBarX() {
                 ProgressBarX  progressBarX =new ProgressBarX(5, 50, 50, Global.WINDOWS_Y_SIZE * 7 / 8 );
                 return progressBarX;
            }
            
        } // 隨機生成關卡
        
        public static class LevelX6 extends LevelX{

            @Override
            public AircraftMain[] getAircrafts() {
                 AircraftMain[]  aircraftMains = new  AircraftMain[1];
                 aircraftMains[0] =new AircraftMain(600,600,0);
                 return aircraftMains;
            }

            @Override
            public EnemyLight[] getEnemyTests() {
                 EnemyLight[]  enemyLights = new  EnemyLight[8];
                 
                 for(int i = 0; i < 8; i++){
                    enemyLights[i] = new EnemyLight((int)(Math.random()* 900 + 300),(int)(Math.random()* 700 + 50),(int)(Math.random()* 3));
                    enemyLights[i].setMotionState(new SpecialMotionState.TotalAct());
                    enemyLights[i].setSpeed((int)(Math.random()* 3 + 2));
                 }
                 
                 return enemyLights;
            }

            @Override
            public BufferedImage[] getBackgrounds() {
                 ImageResourceController irc = ImageResourceController.getInstance();
                 BufferedImage[] bufferedImages =  new BufferedImage[1];
                 bufferedImages[0] = irc.tryGetImage(PathBuilder.getBackGrounds(ImagePath.Backgrounds.BACKGROUNDS_BLUE));
                 return bufferedImages;
            }

            @Override
            public Target getTarget() {
                 Target  target = new  Target(600, 450, 64, 64);
                 return target;
            }

            @Override
            public Assignment getAssignment() {
                 Assignment  assignment = new Assignment(600, 750, 64, 64);
                 return assignment;
            }

            @Override
            public RockX[] getRocKs() {
                 RockX[]  rockBigBrowns = new  RockX[20];
                 
                for(int i = 0; i < 20; i++){
                    rockBigBrowns[i] = new RockX((int)(Math.random()* 1000 + 200),(int)(Math.random()* 800 + 50),(int)(Math.random()* 3));
                 }
                 return rockBigBrowns;
            }

            @Override
            public MovableGameObject[] getMovableGameObjects() {
                 MovableGameObject[]  movableGameObjects = new  MovableGameObject[1];
                 return movableGameObjects;
            }
    
            @Override
            public ProgressBarX getProgressBarX() {
                 ProgressBarX  progressBarX =new ProgressBarX(5, 50, 50, Global.WINDOWS_Y_SIZE * 7 / 8 );
                 return progressBarX;
            }
            
        } 
    }
    
    
    private  LevelX[] levels;
    
    public GameLevelEditorX(){
        levels = new LevelX[5]; //目前只能做手工調整關卡數量和載入
        loadLevels();
    }
    
    private void loadLevels(){ //人工載入關卡
        levels[0] = new LevelX0();
        levels[1] = new LevelX1();
        levels[2] = new LevelX2();
        levels[3] = new LevelX4();
        levels[4] = new LevelX5();
        
    }
    
    public LevelX getLevel(int level){
        return levels[level];
    }
}
