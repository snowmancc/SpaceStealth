
package main;

import controllers.SceneController;
import io.CommandSolver.CommandWrapper;
import java.awt.*;
import javax.swing.JPanel;

import scene.MenuScene;
import utils.Global;

/**
 *
 * @author user1
 */
public class GameJPanel extends JPanel {
    private SceneController sceneController;
    
    public GameJPanel() {
        sceneController = new SceneController();
        
        sceneController.changeScene(new MenuScene(sceneController));
        
    }
    
    public void update(CommandWrapper commands) {
        sceneController.sceneUpdate(commands);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        sceneController.paint(g);
        MenuScene.Writer.writeWord("", MenuScene.Writer.wFont, Color.WHITE,
                    Global.WINDOWS_X_SIZE / 2 - 200 + 20 ,
                    Global.WINDOWS_Y_SIZE / 2 - 50 + 10, g);
    }
}
