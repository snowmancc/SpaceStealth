
package gameobject;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import java.applet.AudioClip;

public class Button extends GameObject {
    private Color color , color0;
    private URL file;
    private AudioClip sound;
    public interface ButtonListener {
        public void onClick(int x, int y);
        public void hover(int x, int y);
    }

    private ButtonListener buttonListener;

    public Button(int x, int y, int width, int height , Color color) {
        super(x, y, width, height,0);
        this.color = color;
        this.color0 = color;
        file = getClass().getResource("/resources/BGM/buttomSlide.wav");
        sound = Applet.newAudioClip(file);
        
    }

    public void setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }
    public void playStuckMusic() {
        sound.play();
    }

    public void stop() {
        sound.stop();
    }
    public void setColor(int R  , int G , int B){
        this. color = new Color(R , G,B);
        
    }
    public void setColor(Color color){
        this.color = color;
    }
    public void resetColor(){
        color = color0;
    }

    public boolean isCollision(int x, int y) {
        if (x < this.x || x > this.x + this.width) {
            return false;
        }
        if (y < this.y || y > this.y + this.height) {
            return false;
        }
        return true;
    }

    public void click(int x, int y) {
        if (buttonListener == null) {
            return;
        }
        buttonListener.onClick(x, y);
    }

    public void hover(int x, int y) {
        if (buttonListener == null) {
            return;
        }
        buttonListener.hover(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)width, (int)height );
//        System.out.println("in button");
    }

}