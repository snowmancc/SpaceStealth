package gameobject.actor;

import gameobject.MovableGameObject;
import gameobject.Polygon;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import utils.Global;
import utils.Vector;

/**
 *
 * @author muheng
 */
public class AircraftMain extends MovableGameObject {

    private static final double WH_RATE = 0.618;
    private static final int[] WIDTH = {49, 73, 98};
    private static final int[] HEIGHT = {(int) (WIDTH[0] * WH_RATE), (int) (WIDTH[1] * WH_RATE), (int) (WIDTH[2] * WH_RATE)};
    private double cX, cY;
    private final Color color = new Color(255, 255, 255, 100);
    private final ActorHelper actorHelper;
    private int width, height;

    public AircraftMain(int x, int y, int size) {//[ 0 : min ; 1 : mid ; 2 : max]

        super(x, y, WIDTH[size], HEIGHT[size], 90, 1);
        super.vectors = null;
        super.vectors = new Vector[3];
        width = WIDTH[size];
        height = HEIGHT[size];
        actorHelper = new ActorHelper(0);
        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new Vector(0, 0);
        }////
        super.setVectors(0, x + width / 2, y);
        super.setVectors(1, x + width, y + height);
        super.setVectors(2, x, y + height);

        for (int i = 0; i < vectors.length; i++) {
            cX += vectors[i].getX();
            cY += vectors[i].getY();
        }
        center.setX(circle.getCenter().getX());
        center.setY(circle.getCenter().getY());
        poly = new Polygon(vectors, center.getX(), center.getY());

        poly.setPoly(super.vectors, center.getX(), center.getY());
        poly.polyRotate(degree);

    }

    @Override
    public void setMotionState(MotionState state) {
        this.motionState = state;
    }

    @Override
    public void move() {

        motionState.move(this);
    }

    @Override
    public void paint(Graphics g) {
//        poly.paint(g);

        if (Global.TEST_OPTION) {
            g.setColor(color);
            g.fillPolygon(poly.getXArr(), poly.getYArr(), 3);
        } else {
            actorHelper.paint(g, (int) super.x, (int) super.y, width, height, this.getDegree(), poly);
        }
    }

}
