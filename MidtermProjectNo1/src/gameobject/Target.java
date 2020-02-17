
package gameobject;

import gameobject.actor.ActorHelper;
import java.awt.Graphics;

public class Target extends GameObject{
    private ActorHelper actorHelper;
    private int  w ,h;
    public Target(double x, double y, double width, double height){
        super(x, y, width , height , 0);
        actorHelper = new ActorHelper(6);
        w = (int)width;
        h = (int) height;
    }

    @Override
    public void paint(Graphics g) {
        actorHelper.paint(g, (int)x, (int)y, (int)w / 2, (int)h / 2, degree, poly);
//        g.setColor(Color.RED);
//        g.drawRect((int)x, (int)y, (int)width, (int)height);
//        g.drawString("TARGET", (int)x, (int)y);
    }
    
}
