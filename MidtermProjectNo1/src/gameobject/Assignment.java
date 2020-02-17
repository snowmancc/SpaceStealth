
package gameobject;

import gameobject.actor.ActorHelper;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Assignment extends GameObject{
//    private Color color1 , color2 , color3 , color4 , color5 , color6;
    private Graphics2D g1;
    private ActorHelper actorHelper;
    private int w ,h;
    
    public Assignment(double x, double y, double width, double height){
        super(x, y, width /2, height / 2, 0);
        actorHelper = new ActorHelper(4);
       
        w = (int)width;
        h = (int)height;

        
        degree = 0;
    }

    @Override
    public void paint(Graphics g) {

       
       
        actorHelper.paint(g, (int)x, (int)y  , w / 2, h / 2, degree, poly);
 
      
        
        

        

        
//        g.drawRect((int)x, (int)y, (int)width, (int)height);
//        g.drawString("ASSIGNMENT", (int)x, (int)y);
    }
    
}
