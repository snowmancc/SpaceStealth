package scene;

import controllers.GameLevelControllerX;
import controllers.SceneController;
import gameobject.Assignment;
import gameobject.Boundary;
import gameobject.GameObject;
import gameobject.GameObject.GameObjectLinstener;
import gameobject.MovableGameObject;
import gameobject.MovableGameObject.MotionState.ForwardMotion;
import gameobject.ProgressBarX;
import gameobject.SpecialMotionState;
import gameobject.SpecialMotionState.RandomAct;
import gameobject.Target;
import gameobject.Timmer;
import gameobject.actor.AircraftMain;
import gameobject.actor.EnemyLight;
import gameobject.actor.RockX;
import io.CommandSolver;
import io.CommandSolver.KeyCommandListener;
import io.CommandSolver.MouseCommandListener;
import io.CommandSolver.TypedListener;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

import java.util.ArrayList;

import utils.DelayCounter;
import utils.Global;

public class MainSceneX extends Scene {

    private CommandSolver.KeyCommandListener keyCommandListener;
    private CommandSolver.MouseCommandListener mouseCommandListener;
    private TypedListener typedListener;
    private GameLevelControllerX glc;
    private int currentLevel;
    private Timmer timmer;
    private Boundary boundary;

    private AircraftMain[] aircraftMains;
    private EnemyLight[] enemyLights;
    private RockX[] rocks;
    private MovableGameObject[] movableObjs;
    private Assignment assigment;
    private Target target;
    private BufferedImage[] backGrounds;
    private ProgressBarX progressBar;
    private ArrayList<GameObject> objs;
    private boolean mainCollision, lightCollision, bCollision;
    private boolean isGameWin;
    private boolean isGameOver;
    private boolean isGameStop;
    private boolean isPass;
    private static final Font FONT_KEN1 = new Font("KenVector Future Thin", Font.BOLD, 60);
    private static final Color COLOR_WHITE = new Color(248, 248, 255, 125);
    private DelayCounter endDelay;
    private DelayCounter scenesDelay;
    private URL file, file2;
    private AudioClip sound, sound2;
    boolean isStuck;

    public MainSceneX(SceneController sceneController) {
        super(sceneController);
        isStuck = false;
        isPass = false;
        file = getClass().getResource("/resources/BGM/stuck.wav");
//        file2 = getClass().getResource("/resources/BGM/catched.wav");
        sound = Applet.newAudioClip(file);
//        sound2  = Applet.newAudioClip(file2);
        glc = new GameLevelControllerX();

        currentLevel = 4;
        timmer = new Timmer(800, 60, 100, 100);
        boundary = new Boundary(Global.WINDOWS_X_SIZE, Global.WINDOWS_Y_SIZE);//邊緣測試
        timmer.timeStart();
        keyCommandListener = new KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {
                switch (commandCode) {
                    case Global.UP:
                        aircraftMains[0].setMotionState(new MovableGameObject.MotionState.ForwardMotion());
                        break;
                    case Global.LEFT:
                        if (!mainCollision) {
                            aircraftMains[0].rotatePoints(-Global.ROTATE_DEGREE);
                        } else {
//                            aircraftMains[0].setMotionState(new MovableGameObject.MotionState.TurnLeftMotion());

                        }
                        break;
                    case Global.DOWN:
                        aircraftMains[0].setMotionState(new MovableGameObject.MotionState.BackWardMotion());
                        break;
                    case Global.RIGHT:
                        if (!mainCollision) {
                            aircraftMains[0].rotatePoints(Global.ROTATE_DEGREE);

                        } else {
//                            aircraftMains[0].setMotionState(new MovableGameObject.MotionState.TurnRightMotion());

                        }
                        break;
                    case Global.SPACE:
                        for (int i = 0; i < aircraftMains.length; i++) {
                            if (assigment.isCollision(aircraftMains[i])) {
                                assigment.triggerListener();
                            }
                        }
                        break;

//                    case Global.NUM_1:
//                        if(Global.TEST_OPTION){
//                            Global.TEST_OPTION = false;
//                        }else{
//                            Global.TEST_OPTION = true;
//                        }
//                        //select window
//                        break;
                }
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {

                switch (commandCode) {
                    case Global.UP:
                        aircraftMains[0].setMotionState(new MovableGameObject.MotionState.StopMotion());
                        break;
                    case Global.DOWN:
                        aircraftMains[0].setMotionState(new MovableGameObject.MotionState.StopMotion());
                        break;
                    case Global.NUM_1:
                        if (Global.TEST_OPTION) {
                            Global.TEST_OPTION = false;
                        } else {
                            Global.TEST_OPTION = true;
                        }
                        //select window
                        break;
                    case Global.P_PASS:
//                            if(!isPass){
//                                isPass = true; 
//                                timmer.passTime();
//                            }
//                            else {
//                                isPass = false;
//                                timmer.continueTime();
//                            }
                        break;
                    case Global.END:
                        System.out.println("END");
                        //sceneController.changeScene(new MenuScene(sceneController));
                        break;
                }
            }
        };
        mouseCommandListener = new MouseCommandListener() {
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.CLICKED) {

                } else if (state == CommandSolver.MouseState.MOVED) {

                }
            }
        };

        typedListener = new CommandSolver.TypedListener() {
            @Override
            public void keyTyped(char c, long trigTime) {
            }
        };

        timmer.timeStart();
        objs = new ArrayList<>();
        //muc.play();

        objs = new ArrayList<>();

    }

    @Override
    public void sceneBegin() {
//        gameWin();
        glc.setLevel(currentLevel);
        backGrounds = glc.getBackgrounds();

        aircraftMains = glc.getAircrafts();
        enemyLights = glc.getEnemyTests();
        for (int i = 0; i < enemyLights.length; i++) {
            int index = i;
            enemyLights[i].setListener(new GameObjectLinstener() {
                @Override
                public void onCollision() {
                    if (currentLevel >= 3) {
                        enemyLights[index].setMotionState(new SpecialMotionState.RandomAct());
//                        System.out.println(enemyLights[index].getMotionState() instanceof RandomAct);
//                        if(enemyLights[index].getMotionState() instanceof RandomAct){
//                            enemyLights[index].setMotionState(new SpecialMotionState.SweepingRobot());
//                        }
//                        else{
//                            enemyLights[index].setMotionState(new SpecialMotionState.RandomAct());
//                        }
                    }
                }
            });
        }
        rocks = glc.getRocks();

        assigment = glc.getAssignment();
        assigment.setListener(new GameObjectLinstener() {
            @Override
            public void onCollision() {
                progressBar.setProgress();
            }
        });
        target = glc.getTarger();
        target.setListener(new GameObjectLinstener() {
            @Override
            public void onCollision() {
                if (progressBar.isFinish()) {
//                    System.out.println("任務完成");
                    progressBar.reset();
                    isGameWin = true;
//                    timmer.timeStop();
//                    System.out.println(timmer.getTotalSecond());
                }
            }
        });

        progressBar = glc.getProgressBarX();
        progressBar.setListener(new GameObjectLinstener() {
            @Override
            public void onCollision() {
            }
        });

        movableObjs = glc.getCollisionObjects(); //collision feedback total...

        isGameWin = false;
        isGameOver = false;
        isGameStop = false;

        endDelay = new DelayCounter(5);

        for (int i = 0; i < rocks.length; i++) {

            scenesDelay = new DelayCounter(7);
        }

        for (int i = 0; i < rocks.length; i++) {
            objs.add(rocks[i]);
        }
        objs.add(boundary.boundaryDown);
        objs.add(boundary.boundaryUp);
        objs.add(boundary.boundaryLeft);
        objs.add(boundary.boundaryRight);
        for (int i = 0; i < enemyLights.length; i++) {
            objs.add(enemyLights[i]);
        }///set objs arrayList
//        timmer.timeStart();
    }

    @Override
    public void sceneUpdate() {

        if (!isGameStop) {
            gameContinue();
        } else {
            gameStop();
        }
        if (isGameWin) {

            if (scenesDelay.update()) {
                gameWin();
            }
        }
        if (isGameOver) {
            gameOver();
        }
        timmer.timeUpDate();//timmerUpDate
    }

    private void gameContinue() {
        lightCollision = false;
        for (int i = 0; i < rocks.length; i++) {
            for (int j = 0; j < 5; j++) {
                rocks[i].move();
            }
        }
        for (int i = 0; i < aircraftMains.length; i++) {
            for (int j = 0; j < enemyLights.length; j++) {
                if (enemyLights[j].isCollisionLight(aircraftMains[i])) {
                    lightCollision = true;
                    isGameOver = true;
                } else {
                    lightCollision = false;
//                    isGameOver = false;

                }
                enemyLights[j].setBoolean(lightCollision);
                for (int k = 0; k < 5; k++) {
                    enemyLights[j].move();
                }
            }
            for (int j = 0; j < objs.size(); j++) {
                if (aircraftMains[i].isCollision(objs.get(j))) {
                    isStuck = true;
                    if (aircraftMains[i].isCollision(objs.get(j))) {
                        mainCollision = true;
                        sound.play();
                        aircraftMains[i].CollisionMotion(aircraftMains[i].getPolygon().getMTV());
                    }
                } else {
                    mainCollision = false;
                    isStuck = false;
                }
            }
            aircraftMains[i].move();
            if (target.isCollision(aircraftMains[i])) {
                target.triggerListener();
            }
        }

        for (int i = 0; i < enemyLights.length; i++) {

            if (currentLevel > 2) {
                for (RockX rock : rocks) {
                    if (enemyLights[i].isCollision(rock)) {
                        enemyLights[i].CollisionMotion(enemyLights[i].getPolygon().getMTV());
                        //                        enemyLights[i].triggerListener();
                    }
                }

                for (int j = 0; j < enemyLights.length; j++) {
                    if (i != j) {
                        if (enemyLights[i].isCollision(enemyLights[j])) {
                            enemyLights[i].CollisionMotion(enemyLights[i].getPolygon().getMTV());
                            //                        enemyLights[i].triggerListener();
                        }
                    }
                }
            }

            if (boundary.isCollision(enemyLights[i])) {
                if (boundary.isCollision(enemyLights[i])) {
                    enemyLights[i].triggerListener();
                } else {
                    enemyLights[i].setMotionState(new MovableGameObject.MotionState.StopMotion());

                }
            }
        }

    }

    private void gameOver() {
        if (scenesDelay.update()) {
//            System.out.println("重新開始");

            objs.clear();
            sceneController.getPlayerRecord().playerDieCount();
            sceneBegin();
        }
    }

    private void gameWin() {
        currentLevel++;
        if (currentLevel < 5) {
            objs.clear();
            sceneBegin();
        } else {
            timmer.timeStop();
            sceneController.saveCurrentPlayerRecord(currentLevel + "," + timmer.getTotalSecond());
            objs.clear();
            sceneController.changeScene(new EndScene(sceneController));
        }
    }

    private void gameStop() {
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {

        for (int i = 0; i < backGrounds.length; i++) {
            for (int j = 0; j < (Global.WINDOWS_Y_SIZE / 256) + 1; j++) {
                for (int k = 0; k < (Global.WINDOWS_X_SIZE / 256) + 1; k++) {
                    g.drawImage(backGrounds[i], k * 256, j * 256, null);
                }
            }
        }
        for (int i = 0; i < aircraftMains.length; i++) {
            aircraftMains[i].paint(g);
        }

        for (int i = 0; i < objs.size(); i++) {
            objs.get(i).paint(g);
        }

        assigment.paint(g);

        if (progressBar.isFinish()) {
            target.paint(g);
        }
        progressBar.paint(g);

        timmer.paint(g);

        g.setFont(FONT_KEN1);
        g.setColor(COLOR_WHITE);
        g.drawString("TIME : ", 500, 60);
        g.drawString("LEVEL : " + (currentLevel + 1), 150, 60);
        g.drawString("DEATH : " + sceneController.getPlayerRecord().getDieCount(), 1000, 60);
    }

    @Override
    public CommandSolver.KeyCommandListener getKeyCommandListener() {
        return keyCommandListener;
    }

    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener() {
        return mouseCommandListener;
    }

    @Override
    public TypedListener getTypedListener() {
        return typedListener;
    }

}
