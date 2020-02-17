package gameobject.actor;

import gameobject.MovableGameObject;
import gameobject.Polygon;
import java.awt.Color;
import java.awt.Graphics;
import utils.Global;
import utils.Vector;

/**
 *
 * @author muheng
 */
public class RockX extends MovableGameObject {

    private static final double RATE = 105 / 35;
    private static final double r = 2;
    private static final double[] WIDTH = {(43 * 2 / 3) * r, 43 * r, 101 * 1.1};
    private static final double[] HEIGHT = {43 * 2 / 3 * r, 43 * r, 84 * 1.1};
    private double cX, cY;
    private final Color color = new Color(245, 150, 170);
    private ActorHelper actorHelper;
    private int rand, size;

    public RockX(int x, int y, int size) {//0 , 1 , 2

        super(x, y, (int) WIDTH[size], (int) HEIGHT[size], 0, 1);
        this.size = size;
        //////////////////////////////////////////////////
        actorHelper = new ActorHelper(size + 9);////???????????
        switch (size) {
            case 0:
                super.vectors = null;
                super.vectors = new Vector[6];

                for (int i = 0; i < vectors.length; i++) {
                    vectors[i] = new Vector(0, 0);
                }
                super.setVectors(0, x + 2 * RATE * 2 / 3 * r, y + 2 * RATE * 2 / 3 * r);
                super.setVectors(1, x + 11 * RATE * 2 / 3 * r, y);
                super.setVectors(2, x + 16 * RATE * 2 / 3 * r, y + 6.5 * RATE * 2 / 3 * r);
                super.setVectors(3, x + 10 * RATE * 2 / 3 * r, y + 14 * RATE * 2 / 3 * r);
                super.setVectors(4, x + 5 * RATE * 2 / 3 * r, y + 13 * RATE * 2 / 3 * r);
                super.setVectors(5, x, y + 8.5 * RATE * 2 / 3 * r);
                for (int i = 0; i < vectors.length; i++) {
                    vectors[i].rotateRaf(Math.toRadians(-45), center.getX(), center.getY());
                }
                break;
            case 1:
                super.vectors = null;
                super.vectors = new Vector[6];

                for (int i = 0; i < vectors.length; i++) {
                    vectors[i] = new Vector(0, 0);
                }
                super.setVectors(0, x + 2 * RATE * r, y + 2 * RATE * r);
                super.setVectors(1, x + 11 * RATE * r, y);
                super.setVectors(2, x + 16 * RATE * r, y + 6.5 * RATE * r);
                super.setVectors(3, x + 10 * RATE * r, y + 14 * RATE * r);
                super.setVectors(4, x + 5 * RATE * r, y + 13 * RATE * r);
                super.setVectors(5, x, y + 8.5 * RATE * r);
                for (int i = 0; i < vectors.length; i++) {
                    vectors[i].rotateRaf(Math.toRadians(-45), center.getX(), center.getY());
                }
                break;

            case 2:
                super.vectors = null;
                super.vectors = new Vector[6];
                for (int i = 0; i < vectors.length; i++) {
                    vectors[i] = new Vector(0, 0);
                }
                super.setVectors(0, x + 6 * RATE * 1.1, y);
                super.setVectors(1, x + 26 * RATE * 1.1, y);
                super.setVectors(2, x + 35 * RATE * 1.1, y + 15 * RATE * 1.1);
                super.setVectors(3, x + 30 * RATE * 1.1, y + 27 * RATE * 1.1);
                super.setVectors(4, x + 10 * RATE * 1.1, y + 29 * RATE * 1.1);
                super.setVectors(5, x, y + 18 * RATE * 1.1);
                break;
        }

        ///////////////////////////////////////////////////
//        for (int i = 0; i < vectors.length; i++) {
//            cX += vectors[i].getX();
//            cY += vectors[i].getY();
//        }
//        center.setX(cX /= vectors.length);
//        center.setY(cY /= vectors.length);
//        center.setX(circle.getCenter().getX());
//        center.setY(circle.getCenter().getY());
        poly = new Polygon(super.vectors, center.getX(), center.getY());

        poly.setPoly(super.vectors, center.getX(), center.getY());
        poly.polyRotate(degree);
        rand = (int) (Math.random() * (2)) + 1;

    }

    @Override
    public void move() {
        if (movedDelay.update()) {
            switch (rand) {
                case 1:
                    this.changeDegree(((int) (Math.random() * 10)));
                    break;
                case 2:
                    this.changeDegree(-((int) (Math.random() * 10)));
                    break;
            }
        }
    }

    @Override
    public void setMotionState(MotionState state) {
        this.motionState = state;
    }

    @Override
    public void paint(Graphics g) {

       
        if (Global.TEST_OPTION) {
            g.setColor(color.darker());

            g.drawPolygon(poly.getXArr(), poly.getYArr(), 6);
        }else{
             actorHelper.paint(g, (int) x, (int) y, (int) WIDTH[size], (int) HEIGHT[size], degree, poly);
        }

    }

}
