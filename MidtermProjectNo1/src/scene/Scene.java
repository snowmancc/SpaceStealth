package scene;

import controllers.SceneController;
import io.CommandSolver.KeyCommandListener;
import io.CommandSolver.MouseCommandListener;
import io.CommandSolver.TypedListener;
import java.awt.Graphics;
public abstract class Scene{
    protected SceneController sceneController;
    
    public Scene(SceneController sceneController){
        this.sceneController = sceneController;
    }
    
    public abstract void sceneBegin();
    public abstract void sceneUpdate();
    public abstract void sceneEnd();
    public abstract void paint(Graphics g);
    public abstract KeyCommandListener getKeyCommandListener();
    public abstract MouseCommandListener getMouseCommandListener();
    public abstract TypedListener getTypedListener();
}
