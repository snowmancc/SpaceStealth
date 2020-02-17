
package scene;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import controllers.SceneController;
import gameobject.Button;
import gameobject.Button.ButtonListener;
import io.CommandSolver;
import io.CommandSolver.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import scene.MenuScene.Writer;
import utils.Global;

import values.ImagePath;

public class GameRuleScene extends Scene {
    
    private KeyCommandListener keyCommandListener;
    private MouseCommandListener mouseCommandListener;
     private TypedListener typedListener;
    private BufferedImage backGround;
    private ImageResourceController irc;
    private Button backButton;
    private Button ruleButton;
    private Button settingButton;
    private int page;
    private int b1X , b1Y , b2X , b2Y , b3X , b3Y;
    private int b1Width , b1Height , b2Width , b2Height , b3Width , b3Height , d;
    private Color color1 , color2 , color3;

    public GameRuleScene(SceneController sceneController) {
        super(sceneController);
        b1Width = b2Width = b3Width = 250;
        b1Height = b2Height = b3Height = 60;
        d = 100;
        
        b1X = Global.WINDOWS_X_SIZE / 2 - b1Width / 2 - d - b1Width;
        b1Y = Global.WINDOWS_Y_SIZE * 3/ 4;
        b2X = b1X + b1Width + d;
        b2Y = Global.WINDOWS_Y_SIZE * 3 / 4;
        b3X = b2X + b2Width + d;
        b3Y = Global.WINDOWS_Y_SIZE * 3 / 4;
        color1 =  new Color(238 , 169, 169);
        color2 = new Color(159, 53, 58);
        color3 = new Color(255,255,251);
//        color3 = new ColorUIResource(page);
        keyCommandListener = new CommandSolver.KeyCommandListener(){
            @Override
            public void keyPressed(int commandCode, long time) {
                switch(commandCode){
                    case Global.UP:
                    case Global.LEFT:
                    case Global.DOWN:
                    case Global.RIGHT:
                        break;
                }
            }

            @Override
            public void keyReleased(int CommandCode, long time) {
            }
        };
        
        mouseCommandListener = new CommandSolver.MouseCommandListener(){
            @Override
            public void mouseTrig(MouseEvent e, MouseState state, long trigTime) {
                if(state == MouseState.CLICKED){
//                    System.out.println("clicked");
                    if(backButton.isCollision(e.getX(), e.getY())){
                        backButton.click(e.getX(), e.getY());
                    }
                    else if(settingButton.isCollision(e.getX(), e.getY())){
                        settingButton.click(e.getX(), e.getY());
                    }
                    else if(ruleButton.isCollision(e.getX(), e.getY())){
                        ruleButton.click(e.getX(), e.getY());
                    }
                }else if(state == MouseState.MOVED){
                    if(backButton.isCollision(e.getX(), e.getY())){
                        backButton.hover(e.getX(), e.getY());
                    }
                    else if(settingButton.isCollision(e.getX(), e.getY())){
                        settingButton.hover(e.getX(), e.getY());
                    }
                    else if(ruleButton.isCollision(e.getX(), e.getY())){
                        ruleButton.hover(e.getX(), e.getY());
                    }else{
                        backButton.resetColor();
                        settingButton.resetColor();
                        ruleButton.resetColor();
                    }
                }
            }
        };
        
        typedListener = new CommandSolver.TypedListener() {
            @Override
            public void keyTyped(char c, long trigTime) {
                System.out.println(c);
            }
        };
        page = 0;
    }

    @Override
    public void sceneBegin() {
        irc = irc.getInstance();
        backGround = irc.tryGetImage(PathBuilder.getBackGrounds(ImagePath.Backgrounds.BACKGROUNDS_BLACK));
        
        backButton = new Button(b1X , b1Y, b1Width, b1Height , color2);
        backButton.setButtonListener(new ButtonListener() {
            @Override
            public void onClick(int x, int y) {
                sceneController.changeScene(new MenuScene(sceneController));
            
      
            }
            @Override
            public void hover(int x, int y) {
                backButton.setColor(color1);
            }
        });
        
        settingButton = new Button(b2X , b2Y , b2Width, b2Height  ,color2);
        settingButton.setButtonListener(new ButtonListener() {
            @Override
            public void onClick(int x, int y) {
                if(page > 0){
                    page--;
                }
            }
            @Override
            public void hover(int x, int y) {
                 settingButton.setColor(color1);
            }
        });
        
        ruleButton = new Button(b3X , b3Y , b3Width, b3Height , color2);
        ruleButton.setButtonListener(new ButtonListener() {
            @Override
            public void onClick(int x, int y) {
                     page++;
            }     
            @Override
            public void hover(int x, int y) {
                ruleButton.setColor(color1);
            }
        });
        
        
    }

    @Override
    public void sceneUpdate() {
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 8; j++){
                g.drawImage(backGround, 256 * j, 256 * i, 256, 256, null);
            }
        }
        backButton.paint(g);
        settingButton.paint(g);
        ruleButton.paint(g);
        Writer.writeWord("Back" , Writer.wFont, color3, b1X + b1Width / 4 + 15, b1Y + b1Height / 2 + 10 , g);
        
        
        Writer.writeWord("rule" , Writer.wFont, color3, b2X + b2Width / 4 + 15,  b2Y + b2Height / 2 + 10, g);
        
        
        Writer.writeWord("setting" , Writer.wFont, color3,  b3X + b3Width / 5, b3Y + b3Height / 2 + 10, g);
        
        
        Writer.writeWord("PAGE" + page, Writer.jFont, color3, Global.WINDOWS_X_SIZE / 5 , Global.WINDOWS_Y_SIZE / 5, g);
    }
    
    @Override
    public KeyCommandListener getKeyCommandListener(){return keyCommandListener;}
    
    @Override
    public MouseCommandListener getMouseCommandListener(){return mouseCommandListener;}
    
    @Override
    public TypedListener getTypedListener(){return typedListener;}
}
