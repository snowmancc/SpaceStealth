package scene;

import controllers.ImageResourceController;
import controllers.SceneController;
import gameobject.Button;
import io.CommandSolver;
import io.CommandSolver.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import scene.MenuScene.Writer;

import utils.DelayCounter;
import utils.Global;

/**
 *
 * @author muheng
 */
public class InputSceneX extends Scene {

    private KeyCommandListener keyCommandListener;
    private MouseCommandListener mouseCommandListener;
    private TypedListener typedListener;
    private BufferedImage backGround;
    private ImageResourceController irc;
    private Button inputArea;
    private Button startButton;

    private LinkedList<String> nameList;
    private DelayCounter backspaceDelay;

    public InputSceneX(SceneController sceneController) {
        super(sceneController);
        nameList = new LinkedList();
        backspaceDelay = new DelayCounter(1);//>>>>>?whats this?
        ///////////////////////////////////////////////////////
        keyCommandListener = new KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {
                switch (commandCode) {
                    case Global.ENTER:
                        startButton.click(0, 0);
                        break;
                }
            }

            @Override

            public void keyReleased(int commandCode, long trigTime) {

            }
        };

        mouseCommandListener = new MouseCommandListener() {
            @Override
            public void mouseTrig(MouseEvent e, MouseState state, long trigTime) {
                if (state == MouseState.CLICKED) {

                    if (startButton.isCollision(e.getX(), e.getY())) {
                        startButton.click(0, 0);
                    }
                }

                if (state == MouseState.MOVED) {
                    if (inputArea.isCollision(e.getX(), e.getY())) {
                        inputArea.hover(0, 0);
                    }
                    if (startButton.isCollision(e.getX(), e.getY())) {
                        startButton.hover(0, 0);
                    }
                }
            }
        };

        typedListener = new TypedListener() {
            @Override
            public void keyTyped(char c, long trigTime) {
                if (nameList.size() <= 10 && c >= 65 && c <= 90) {
                    nameList.add(String.valueOf(c));
                }
                if (c == KeyEvent.VK_BACK_SPACE && nameList.size() != 0) {
                    nameList.removeLast();
                }
            }
        };

    }

    @Override

    public void sceneBegin() {
        irc = irc.getInstance();
        backGround = irc.tryGetImage("/resources/spaceshooter/Backgrounds/inputBackground.png");
        //////////////////////////////////////////////////////////
        inputArea = new Button(Global.WINDOWS_X_SIZE / 2 - 200,
                Global.WINDOWS_Y_SIZE / 2 - 50, 400, 100,
                Color.yellow);//set color latter

        inputArea.setButtonListener(new Button.ButtonListener() {
            @Override
            public void onClick(int x, int y) {
                inputArea.setColor(Color.PINK);

            }

            @Override
            public void hover(int x, int y) {
                inputArea.setColor(Color.PINK);
            }
        });

        startButton = new Button(Global.WINDOWS_X_SIZE / 2 - 50,
                Global.WINDOWS_Y_SIZE / 2 + 50, 100, 30, Color.ORANGE);
        startButton.setButtonListener(new Button.ButtonListener() {
            @Override
            public void onClick(int x, int y) {
                String name = "";
                for (int i = 0; i < nameList.size(); i++) {
                    name = name + nameList.get(i);
                }
                sceneController.saveCurrentPlayerMessage(name);
                sceneController.changeScene(new GamePlotScene(sceneController));
            }

            @Override
            public void hover(int x, int y) {
                inputArea.setColor(Color.PINK);
            }
        });
        //////////////////////////////////////////////////////////

    }

    @Override
    public void sceneUpdate() {
      

    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(backGround, 0, 0, Global.WINDOWS_X_SIZE, Global.WINDOWS_Y_SIZE, null);

//        inputArea.paint(g);
//        startButton.paint(g);
        for (int i = 0; i < nameList.size(); i++) {
            Writer.writeWord(nameList.get(i), MenuScene.Writer.wFont, Color.WHITE,
                    Global.WINDOWS_X_SIZE / 2 - 200 + 20 + (i * 25),
                    Global.WINDOWS_Y_SIZE / 2 - 50 + 50, g);
        }

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
    public CommandSolver.TypedListener getTypedListener() {
        return typedListener;
    }

}
