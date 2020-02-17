package scene;

import controllers.ImageResourceController;
import controllers.SceneController;

import io.CommandSolver;
import io.CommandSolver.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import utils.DelayCounter;
import utils.Global;

public class EndScene extends Scene {

    private KeyCommandListener keyCommandListener;
    private MouseCommandListener mouseCommandListener;
    private TypedListener typedListener;
    private BufferedImage backGround;
    private ImageResourceController irc;
    private DelayCounter delay;
    private SceneController sceneController;
    private int count ;
    public EndScene(SceneController sceneController) {
        super(sceneController);
        delay = new DelayCounter(30);
//        color3 = new ColorUIResource(page);
        keyCommandListener = new CommandSolver.KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long time) {
               
                switch (commandCode) {
                    case Global.SPACE:
                        sceneController.changeScene(new RankScene(sceneController));

                        break;
                }

            }

            @Override
            public void keyReleased(int CommandCode, long time) {
            }
        };

        mouseCommandListener = new CommandSolver.MouseCommandListener() {
            @Override
            public void mouseTrig(MouseEvent e, MouseState state, long trigTime) {
                if (state == MouseState.CLICKED) {

                } else if (state == MouseState.MOVED) {

                } else {

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
        irc = irc.getInstance();
        backGround = irc.tryGetImage("/resources/spaceshooter/Backgrounds/endingBackground.png");
        count = 0;
    }

    @Override
    public void sceneUpdate() {
        count ++;
    }
    
    @Override
    public void sceneEnd() {
        
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(backGround, 0, 0, Global.WINDOWS_X_SIZE, Global.WINDOWS_Y_SIZE, null);

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
}
