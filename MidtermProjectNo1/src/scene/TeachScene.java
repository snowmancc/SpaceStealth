package scene;

import controllers.ImageResourceController;
import controllers.MusicController;
import controllers.PathBuilder;
import controllers.SceneController;
import gameobject.Assignment;
import gameobject.Button;
import gameobject.Button.ButtonListener;
import gameobject.GameObject;
import gameobject.MovableGameObject;
import gameobject.ProgressBarX;
import gameobject.SpecialMotionState;
import gameobject.Target;
import gameobject.actor.AircraftMain;
import gameobject.actor.EnemyLight;
import gameobject.actor.RockX;
import io.CommandSolver;
import io.CommandSolver.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import utils.DelayCounter;
import utils.Global;
import values.ImagePath;

public class TeachScene extends Scene {

    private Color color1;
    private int paintY, prY;

    public static class Writer {

        public static Font jFont = new Font("華文黑體", Font.ITALIC, Global.WINDOWS_X_SIZE / 12);
        public static Font wFont = new Font("華文黑體", Font.BOLD, Global.WINDOWS_Y_SIZE / 24);////

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

    private Button skipButton;
    private DelayCounter delay;
    private boolean isClickUp;
    private boolean isClickDown;
    private boolean isClickLeft;
    private boolean isClickRight;
    private boolean isClickSpace;

    private boolean isFinishTask1;
    private boolean isFinishTask2;
    private boolean isFinishTask3;
    private boolean isFinishTask4;

    private ProgressBarX progressBar;
    private AircraftMain aircraftMain;
    private Assignment assigment;
    private Target target;
    private EnemyLight enemyLight;
    private RockX rock;

    private static final Color COLOR_GREEN = new Color(0, 255, 127);
    private static final Color COLOR_RED = new Color(255, 127, 80);
    private static final Color COLOR_WHITE = new Color(248, 248, 255, 125);
    private static final Font FONT_KEN = new Font("KenVector Future Thin", Font.BOLD, 56);
    private static final Font FONT_KEN1 = new Font("KenVector Future Thin", Font.BOLD, 36);

    public TeachScene(SceneController sceneController) {
        super(sceneController);
        delay = new DelayCounter(5);

        keyCommandListener = new CommandSolver.KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long time) {
                switch (commandCode) {
                    case Global.UP:
                        aircraftMain.setMotionState(new MovableGameObject.MotionState.ForwardMotion());
                        isClickUp = true;
                        break;
                    case Global.LEFT:
                        aircraftMain.rotatePoints(-Global.ROTATE_DEGREE);
                        isClickLeft = true;
                        break;
                    case Global.DOWN:
                        aircraftMain.setMotionState(new MovableGameObject.MotionState.BackWardMotion());
                        isClickDown = true;
                        break;
                    case Global.RIGHT:
                        aircraftMain.rotatePoints(Global.ROTATE_DEGREE);
                        isClickRight = true;
                        break;
                    case Global.SPACE:
                        isClickSpace = true;
                        if (assigment.isCollision(aircraftMain)) {
                            assigment.triggerListener();
                        }
                        break;
                    
                }
            }

            @Override
            public void keyReleased(int commandCode, long time) {
                switch (commandCode) {
                    case Global.UP:
                        aircraftMain.setMotionState(new MovableGameObject.MotionState.StopMotion());
                        isClickUp = false;
                        break;
                    case Global.LEFT:
                        isClickLeft = false;
                        break;
                    case Global.DOWN:
                        aircraftMain.setMotionState(new MovableGameObject.MotionState.StopMotion());
                        isClickDown = false;
                        break;
                    case Global.RIGHT:
                        isClickRight = false;
                        break;
                    case Global.SPACE:
                        isClickSpace = false;
                        break;
                    case Global.ENTER:
                        sceneController.changeScene(new MainSceneX(sceneController));
                        break;
                }
            }
        };

        mouseCommandListener = new CommandSolver.MouseCommandListener() {///////////************

            @Override
            public void mouseTrig(MouseEvent e, MouseState state, long trigTime) {

            }

        };

    }

    @Override
    public void sceneBegin() {
        ImageResourceController irc = ImageResourceController.getInstance();
        backGround = irc.tryGetImage(PathBuilder.getBackGrounds(ImagePath.Backgrounds.BACKGROUNDS_BLACK));
        aircraftMain = new AircraftMain(200, 300, 2);

        target = new Target(1300, 300, 64, 64);
        target.setListener(new GameObject.GameObjectLinstener() {
            @Override
            public void onCollision() {
                if (progressBar.isFinish()) {
                    sceneController.changeScene(new MainSceneX(sceneController));
                }
            }
        });

        assigment = new Assignment(900, 300, 64, 64);
        assigment.setListener(new GameObject.GameObjectLinstener() {
            @Override
            public void onCollision() {
                progressBar.setProgress();
            }
        });

        progressBar = new ProgressBarX(5, 50, 50, Global.WINDOWS_Y_SIZE * 7 / 8);

        enemyLight = new EnemyLight(1100, 150, 2);
        enemyLight.setMotionState(new SpecialMotionState.StopMotion());

        rock = new RockX(570, 300, 2);

        isClickUp = false;
        isClickDown = false;
        isClickLeft = false;
        isClickRight = false;
        isClickSpace = false;

    }

    @Override
    public void sceneUpdate() {
        aircraftMain.move();

        if (enemyLight.isCollisionLight(aircraftMain)) {

            enemyLight.setBoolean(true);
            if (delay.update()) {
                sceneController.changeScene(new TeachScene(sceneController));
            }
        }
        if (aircraftMain.isCollision(enemyLight) || aircraftMain.isCollision(rock)) {
            aircraftMain.CollisionMotion(aircraftMain.getPolygon().getMTV());
        }
        if (target.isCollision(aircraftMain)) {
            target.triggerListener();
        }
    }

    @Override
    public void sceneEnd() {
        
    }

    @Override
    public void paint(Graphics g) {
        
        g.drawImage(backGround, 0, 0, Global.WINDOWS_X_SIZE, Global.WINDOWS_Y_SIZE, null);
        
        
    

        g.setColor(Color.GREEN);

        if (isClickUp) {
            g.setColor(COLOR_GREEN);
            g.fillRoundRect(300, 500, 80, 80, 10, 10);
            g.fillRoundRect(1100, 500, 80, 80, 10, 10);
        } else {
            g.drawRoundRect(300, 500, 80, 80, 10, 10);
            g.drawRoundRect(1100, 500, 80, 80, 10, 10);
        }

        if (isClickDown) {
            g.setColor(COLOR_GREEN);
            g.fillRoundRect(300, 600, 80, 80, 10, 10);
            g.fillRoundRect(1100, 600, 80, 80, 10, 10);
        } else {
            g.drawRoundRect(300, 600, 80, 80, 10, 10);
            g.drawRoundRect(1100, 600, 80, 80, 10, 10);
        }

        if (isClickLeft) {
            g.setColor(COLOR_GREEN);
            g.fillRoundRect(200, 600, 80, 80, 10, 10);
            g.fillRoundRect(1000, 600, 80, 80, 10, 10);
        } else {
            g.drawRoundRect(200, 600, 80, 80, 10, 10);
            g.drawRoundRect(1000, 600, 80, 80, 10, 10);
        }

        if (isClickRight) {
            g.setColor(COLOR_GREEN);
            g.fillRoundRect(400, 600, 80, 80, 10, 10);
            g.fillRoundRect(1200, 600, 80, 80, 10, 10);
        } else {
            g.drawRoundRect(400, 600, 80, 80, 10, 10);
            g.drawRoundRect(1200, 600, 80, 80, 10, 10);
        }

        if (isClickSpace) {
            g.setColor(COLOR_GREEN);
            g.fillRoundRect(700, 600, 200, 80, 10, 10);
        } else {
            g.drawRoundRect(700, 600, 200, 80, 10, 10);
        }

        g.setColor(COLOR_RED);
        g.setFont(FONT_KEN);
        g.drawString("W", 316, 560); //300 500
        g.drawString("S", 320, 660); //300 600
        g.drawString("A", 220, 660);//200 600
        g.drawString("D", 420, 660);// 400 600
        g.drawString("SPACE", 705, 658);//500 600
        char c = 104;
        g.drawString("▲", 1116, 560);
        g.drawString("▼", 1116, 660);
        g.drawString("◀", 1010, 660);
        g.drawString("▶", 1220, 660);
        //g.setColor(COLOR_WHITE);
        //g.fillRoundRect(350, 80, 800, 80, 10, 10);
        g.setFont(FONT_KEN1);
        g.setColor(COLOR_WHITE);
        g.drawString("=====遊戲教學=====", 500, 100);
        g.drawString("前進", 300, 470);
        g.drawString("後退", 300, 730);
        g.drawString("逆時鐘轉", 50, 660);
        g.drawString("順時鐘轉", 490, 660);
        g.drawString("長按採集能量", 690, 590);
        g.drawString("集滿能量條", 60, 100);
        g.drawString("能量採集區", 860, 280);
        g.drawString("繞過搜查光線", 1050, 450);
        g.drawString("穿越蟲洞", 1230, 280);
        g.drawString("press \" enter \" to skip", 500, 800);
            aircraftMain.paint(g);
        
        progressBar.paint(g);
        target.paint(g);
        enemyLight.paint(g);
        assigment.paint(g);
        rock.paint(g);
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
