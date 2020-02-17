package scene;

import controllers.ImageResourceController;
import controllers.MusicController;
import controllers.SceneController;
import gameobject.Button;
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

public class GamePlotScene extends Scene {

    private Color color1;
    private double paintY, prY;

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
    private String text;
    private String hint;
    private DelayCounter delay;
    private ArrayList<String> arrayList;

    public GamePlotScene(SceneController sceneController) {
        super(sceneController);
        color1 = Color.ORANGE;
        hint = "press \"space\" to skip";
        /////////////////////////////////////////////////////////////////////
        arrayList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("story" + ".txt"));
            while (br.ready()) {
                arrayList.add(br.readLine());
            }
            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ////////////////////////////////////////////////////////////////////

        musicController = new MusicController("/resources/BGM/manuMusic.wav");
        keyCommandListener = new CommandSolver.KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long time) {
                switch (commandCode) {
                    case Global.SPACE:
                        sceneController.changeScene(new TeachScene(sceneController));
                        break;
                }
            }

            @Override
            public void keyReleased(int CommandCode, long time) {
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
        
        irc = irc.getInstance();
        prY = 750;
        backGround = irc.tryGetImage("/resources/spaceshooter/Backgrounds/storyBackground.png");
        
        delay = new DelayCounter(60);
        musicController.play();

    }

    @Override
    public void sceneUpdate() {
        prY -= 0.7;
        if (prY <= -arrayList.size() * 50) {
            if (delay.update()) {
                sceneController.changeScene(new TeachScene(sceneController));
            }
        }
    }
    

    @Override
    public void sceneEnd() {
        stopMusic();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backGround, 0, 0, Global.WINDOWS_X_SIZE, Global.WINDOWS_Y_SIZE, null);

        for (int i = 0; i < arrayList.size(); i++) {
            paintY = prY + i * 50;
            if (paintY < Global.WINDOWS_Y_SIZE - 100 && paintY > 100) {
                Writer.writeWord(arrayList.get(i), Writer.wFont, color1, 300, (int)paintY, g);
            }
        }
        Writer.writeWord(hint, Writer.wFont, Color.gray, 500, Global.WINDOWS_Y_SIZE - 50, g);
//        if (delay.update()) {

//        }
//        skipButton.paint(g);
//        Writer.writeWord("SKIP", Writer.wFont, color1, 700, 680, g);
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
