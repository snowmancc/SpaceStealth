package scene;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import controllers.PlayerRecord;
import controllers.SceneController;
import gameobject.Button;
import gameobject.Button.ButtonListener;
import io.CommandSolver;
import io.CommandSolver.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utils.Global;
import values.ImagePath;

public class RankScene extends Scene {

    private Color color1;
    private Color color2;

    public static class Writer {

        public static Font jFont = new Font("KenVector Future Thin", Font.ITALIC, Global.WINDOWS_X_SIZE / 8);
        public static Font wFont = new Font("KenVector Future Thin", Font.BOLD, Global.WINDOWS_Y_SIZE / 24);////

        public static void writeWord(String word, Font font, Color color, int x, int y, Graphics g) {
            g.setColor(color);
            g.setFont(font);
            g.drawString(word, x, y);
        }
    }
    private KeyCommandListener keyCommandListener;
    private MouseCommandListener mouseCommandListener;
    private TypedListener typedListener;

    private BufferedImage backGround, backButton;
    private ImageResourceController irc;

     private ArrayList<PlayerRecord>list;

    public RankScene(SceneController sceneController) {
        super(sceneController);
        color1 = new Color(188, 159, 119);
        color2 = new Color(189, 192, 186);
        keyCommandListener = new CommandSolver.KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long time) {
                switch (commandCode) {
                    case Global.B:
                        sceneController.changeScene(new MenuScene(sceneController));
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

        typedListener = new CommandSolver.TypedListener() {
            @Override
            public void keyTyped(char c, long trigTime) {
            }
        };
    }

    @Override
    public void sceneBegin() {

        irc = irc.getInstance();
        list = sceneController.loadRankRecords();

//        ranks = getRanks(rankFile);
//        for(int i = 0; i < ranks.length; i++){
//            System.out.println(ranks[i]);
//        }
        backGround = irc.tryGetImage(PathBuilder.getBackGrounds(ImagePath.Backgrounds.BACKGROUNDS_BLACK));
        backButton = irc.tryGetImage("/resources/spaceshooter/PNG/backButton.png");

    }


    @Override
    public void sceneUpdate() {

    }

    @Override
    public void sceneEnd() {
        stopMusic();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backGround, 0, 0, null);
        for (int i = 0; i < Global.WINDOWS_Y_SIZE / 256 + 1; i++) {
            for (int j = 0; j < Global.WINDOWS_X_SIZE / 256 + 1; j++) {
                g.drawImage(backGround, j * 256, i * 256, null);
            }
        }
//        g.drawImage(backGround, 0, 0, null);
//        g.drawImage(backGround, 0, 0, Global.WINDOWS_X_SIZE, Global.WINDOWS_Y_SIZE, null);

        Writer.writeWord("RANK", Writer.wFont, color1, 230, 200, g);
        Writer.writeWord("DATE", Writer.wFont, color1, 430, 200, g);
        Writer.writeWord("NAME", Writer.wFont, color1, 680, 200, g);
        Writer.writeWord("DIE", Writer.wFont, color1, 920, 200, g);
        Writer.writeWord("SECOND", Writer.wFont, color1, 1030, 200, g);

        Writer.writeWord("1.", Writer.wFont, color2, 280, 300, g);
        Writer.writeWord("2.", Writer.wFont, color2, 280, 380, g);
        Writer.writeWord("3.", Writer.wFont, color2, 280, 460, g);
        Writer.writeWord("4.", Writer.wFont, color2, 280, 540, g);
        Writer.writeWord("5.", Writer.wFont, color2, 280, 620, g);
        
        for (int i = 0; i < list.size(); i++) {
            Writer.writeWord(list.get(i).getDate(), Writer.wFont, color2, 380, 300 + (i * 80), g);
            Writer.writeWord(list.get(i).getName(), Writer.wFont, color2, 680, 300 + (i * 80), g);
            Writer.writeWord("" +list.get(i).getDieCount(), Writer.wFont, color2, 930, 300 + (i * 80), g);
            Writer.writeWord("" + list.get(i).getScore(), Writer.wFont, color2, 1080, 300 + (i * 80), g);
        }
        Writer.writeWord("press \"b\" return", Writer.wFont, Color.pink, 100,Global.WINDOWS_Y_SIZE - 70, g);
        g.setColor(color2);
        g.drawRect(100,
                100,
                Global.WINDOWS_X_SIZE - 200,
                Global.WINDOWS_Y_SIZE - 200);

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
    }

}
