package scene;

import controllers.ImageResourceController;
import controllers.MusicController;
import controllers.PathBuilder;
import controllers.SceneController;
import gameobject.Button;
import gameobject.Button.ButtonListener;
import gameobject.MovableGameObject;
import gameobject.SpecialMotionState;
import gameobject.actor.AircraftMain;
import gameobject.actor.EnemyLight;
import io.CommandSolver;
import io.CommandSolver.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import utils.Global;
import values.ImagePath;

public class MenuScene extends Scene {

    private Color color1;
    
    private BufferedImage sButton, eButton, rButton, sB1, eB1, rB1;
    
    public static class Writer {
        
        public static Font jFont = new Font("KenVector Future Thin", Font.ITALIC, Global.WINDOWS_X_SIZE / 12);
        public static Font wFont = new Font("KenVector Future Thin", Font.BOLD, Global.WINDOWS_Y_SIZE / 24);////

        public static void writeWord(String word, Font font, Color color, int x, int y, Graphics g) {
            g.setColor(color);
            g.setFont(font);
            g.drawString(word, x, y);
        }
    }
    private MusicController musicController;
    private KeyCommandListener keyCommandListener;
    private MouseCommandListener mouseCommandListener;
    private TypedListener typedListener;
    private BufferedImage backGround;
    private ImageResourceController irc;
    private EnemyLight enemy;
    private Button startButton;
    private Button rankButton;
    private Button exitButton;
    private AircraftMain aircraft;
    private boolean startISHover, exitISHover, rankISHover;
    private int chooseButton;
    
    
    public MenuScene(SceneController sceneController) {
        super(sceneController);
        enemy = new EnemyLight(150, 150, 1);
        color1 = new Color(255, 255, 251);
        chooseButton = 0;
        keyCommandListener = new CommandSolver.KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long time) {

                

                switch(commandCode){
                }

            }
            
            @Override
            public void keyReleased(int CommandCode, long time) {
                
                switch(CommandCode){
                    case Global.ENTER:
                        if (chooseButton == 0) {
                            startButton.click(0, 0);
                        } else if (chooseButton == 1) {
                            rankButton.click(0, 0);
                        } else if (chooseButton == 2) {
                            exitButton.click(0, 0);
                        }
                        break;
                   case Global.UP:
                       if(chooseButton > 0){
                           chooseButton--;
                        }
                        break;
                   case Global.DOWN:
                         if(chooseButton < 2){
                           chooseButton++;
                        }
                        break;
                }
            }
        };
        
        mouseCommandListener = new CommandSolver.MouseCommandListener() {///////////************

            @Override
            public void mouseTrig(MouseEvent e, MouseState state, long trigTime) {
                if (state == MouseState.CLICKED) {
                } else if (state == MouseState.MOVED) {//hover
                }
            }
        };
        
        typedListener = new CommandSolver.TypedListener() {
            @Override
            public void keyTyped(char c, long trigTime) {
            }
        };
    }
    
    @Override
    public void sceneBegin() {
        enemy.setMotionState(new MovableGameObject.MotionState.TurnLeftMotion());
        enemy.setRateLight(20);
        
        irc = irc.getInstance();
        
        backGround = irc.tryGetImage(PathBuilder.getBackGrounds(ImagePath.Backgrounds.BACKGROUNDS_MENU));
        sButton = irc.tryGetImage("/resources/button/1x/startButton0.png");
        eButton = irc.tryGetImage("/resources/button/1x/exitButton0.png");
        rButton = irc.tryGetImage("/resources/button/1x/rankButton0.png");
        //sB1 , eB1 , rB1
        sB1 = irc.tryGetImage("/resources/button/1x/startButton1.png");
        eB1 = irc.tryGetImage("/resources/button/1x/exitButton1.png");
        rB1 = irc.tryGetImage("/resources/button/1x/rankButton1.png");
        
        startButton = new Button(Global.WINDOWS_X_SIZE / 2 - 236 / 2,
                 Global.WINDOWS_Y_SIZE / 2 - 50 - 61 / 2,
                 236, 61, new Color(159, 53, 58, 0));
        
        startButton.setButtonListener(new ButtonListener() {
            @Override
            public void onClick(int x, int y) {
                
                sceneController.changeScene(new InputSceneX(sceneController));
            }
            
            @Override
            public void hover(int x, int y) {
                startISHover = true;
            }
        });
        
        rankButton = new Button(Global.WINDOWS_X_SIZE / 2 - 236 / 2,
                 Global.WINDOWS_Y_SIZE / 2 + 50 - 61 / 2,
                 236, 61, new Color(159, 53, 58, 0));
        
        rankButton.setButtonListener(new ButtonListener() {
            @Override
            public void onClick(int x, int y) {
                sceneController.changeScene(new RankScene(sceneController));
            }
            
            @Override
            public void hover(int x, int y) {
                rankISHover = true;
            }
        });
        
        exitButton = new Button(Global.WINDOWS_X_SIZE / 2 - 236 / 2,
                 Global.WINDOWS_Y_SIZE / 2 + 150 - 61 / 2,
                 236, 61, new Color(159, 53, 58, 0));
        
        exitButton.setButtonListener(new ButtonListener() {
            @Override
            public void onClick(int x, int y) {
                System.exit(0);//exit the game
            }
            
            @Override
            public void hover(int x, int y) {
                exitISHover = true;
            }
        });
        
    }
    
    @Override
    public void sceneUpdate() {
        for (int i = 0; i < 5; i++) {
            enemy.move();
            enemy.updateLight();
        }
        
        if (chooseButton == 0) {
            startButton.playStuckMusic();
            startButton.hover(0, 0);
            startISHover = true;
            exitISHover = false;
            rankISHover = false;
        } 
        else if (chooseButton == 1) {
            rankButton.playStuckMusic();
            rankButton.hover(0, 0);
            startISHover = false;
            exitISHover = false;
            rankISHover = true;
        } 
        else if (chooseButton == 2) {
            exitButton.playStuckMusic();
            exitButton.hover(0, 0);
            startISHover = false;
            exitISHover = true;
            rankISHover = false;
        } else {

        }
        
    }
    
    @Override
    public void sceneEnd() {
    }
    
    @Override
    public void paint(Graphics g) {
       
        g.drawImage(backGround, 0, 0, Global.WINDOWS_X_SIZE, Global.WINDOWS_Y_SIZE, null);
        
        enemy.paint(g);
        if (startISHover) {
            g.drawImage(sB1, Global.WINDOWS_X_SIZE / 2 - 236 / 2,
                     Global.WINDOWS_Y_SIZE / 2 - 50 - 61 / 2,
                     236, 61, null);
        } else {
            g.drawImage(sButton, Global.WINDOWS_X_SIZE / 2 - 236 / 2,
                     Global.WINDOWS_Y_SIZE / 2 - 50 - 61 / 2,
                     236, 61, null);
        }
        if (exitISHover) {
            g.drawImage(eB1, Global.WINDOWS_X_SIZE / 2 - 236 / 2,
                     Global.WINDOWS_Y_SIZE / 2 + 150 - 61 / 2,
                     236, 61, null);
        } else {
            g.drawImage(eButton, Global.WINDOWS_X_SIZE / 2 - 236 / 2,
                     Global.WINDOWS_Y_SIZE / 2 + 150 - 61 / 2,
                     236, 61, null);
        }
        if (rankISHover) {
            g.drawImage(rB1, Global.WINDOWS_X_SIZE / 2 - 236 / 2,
                     Global.WINDOWS_Y_SIZE / 2 + 50 - 61 / 2,
                     236, 61, null);
        } else {
            g.drawImage(rButton, Global.WINDOWS_X_SIZE / 2 - 236 / 2,
                     Global.WINDOWS_Y_SIZE / 2 + 50 - 61 / 2,
                     236, 61, null);
            
        }
        
    }
    
    @Override
    public KeyCommandListener getKeyCommandListener() {
        return keyCommandListener;
    }
    
    @Override
    public MouseCommandListener getMouseCommandListener() {
        return mouseCommandListener;
    }
    
    @Override
    public TypedListener getTypedListener() {
        return typedListener;
    }
    
    public void stopMusic() {
        musicController.stop();
    }
    
    
    
}
