package gameobject.actor;

import gameobject.GameObject;
import gameobject.MovableGameObject;
import gameobject.Polygon;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import utils.DelayCounter;
import utils.Global;
import utils.Vector;

/**
 *
 * @author muheng
 */
public class EnemyLight extends MovableGameObject {

    private class PolyList {

        private Polygon[] arr;
        private int count;

        private PolyList() {
            arr = new Polygon[2];
            count = 0;
        }

        private void dbArr() {
            Polygon[] tmp = new Polygon[arr.length * 2];
            for (int i = 0; i < arr.length; i++) {
                tmp[i] = arr[i];
            }
            arr = tmp;
        }

        private void add(Polygon poly) {
            if (count == arr.length) {
                dbArr();
            }
            arr[count++] = poly;
        }

        private Polygon get(int index) {
            return arr[index];
        }

        private void clear() {
            count = 0;
        }

    }

    private Polygon polyLight;
    private Vector[] verticesLight, vectorsLight;
    private int lightRate;
    private double lightAngle;
    private int[] xArrLight, yArrLight;
    private Color lightColor1, lightColor2, lightColor3, lightColor4;
    private boolean isCollisionLight;
    private GradientPaint gradient;
    private ActorHelper actorHelper;
    /////////////////////////////////////

    private DelayCounter movedDelay;
    private static final double WH_RATE = 0.8;
    private static final int[] WIDTH = {60, 90, 110};
    private static final int[] HEIGHT = {(int) (WIDTH[0] * WH_RATE), (int) (WIDTH[1] * WH_RATE), (int) (WIDTH[2] * WH_RATE)};
    private int width, height;

    public EnemyLight(int x, int y, int size) {
        super(x, y, WIDTH[size], HEIGHT[size], 0, 2);
        width = WIDTH[size];
        height = HEIGHT[size];
        movedDelay = new DelayCounter(5);
        actorHelper = new ActorHelper(1);

        this.setMotionState(new MotionState.StopMotion());

        verticesLight = new Vector[3];
        vectorsLight = new Vector[3];
        lightRate = (int) (25 * 0.618);
        lightAngle = 30;
        for (int i = 0; i < verticesLight.length; i++) {
            verticesLight[i] = new Vector(0, 0);
            vectorsLight[i] = new Vector(0, 0);
        }
        setLightColor();
        isCollisionLight = false;
        setLight();
        xArrLight = new int[3];
        yArrLight = new int[3];
        for (int i = 0; i < xArrLight.length; i++) {
            xArrLight[i] = (int) verticesLight[i].getX();
            yArrLight[i] = (int) verticesLight[i].getY();
            vectors[i].setX(verticesLight[i].getX()).setY(verticesLight[i].getY());
        }
        polyLight = new Polygon(verticesLight, this.center.getX(), this.center.getY());

    }

    private void setLightColor() {
        lightColor1 = new Color(255, 235, 205, 255);
        lightColor2 = new Color(255, 235, 205, 10);
        lightColor3 = new Color(240, 128, 128, 255);
        lightColor4 = new Color(240, 128, 128, 0);
    }

    public void setRateLight(int rate) {
        lightRate = rate;
    }

    private void setLight() {
        verticesLight[0] = headPoint;

        verticesLight[1].setX(headPoint.getX() + lightRate * headDirection.getX())
                .setY(headPoint.getY() + lightRate * headDirection.getY());
        verticesLight[1].rotateRaf(lightAngle / 2 * Math.PI / 180, headPoint.getX(), headPoint.getY());

        verticesLight[2].setX(headPoint.getX() + lightRate * headDirection.getX())
                .setY(headPoint.getY() + lightRate * headDirection.getY());

        verticesLight[2].rotateRaf(-lightAngle / 2 * Math.PI / 180, headPoint.getX(), headPoint.getY());
        if (isCollisionLight) {
            gradient = new GradientPaint((float) headPoint.getX(), (float) headPoint.getY(), lightColor3,
                    (float) (headPoint.getX() + lightRate * headDirection.getX()), (float) (headPoint.getY() + lightRate * headDirection.getY()),
                    lightColor4);
        } else {
            gradient = new GradientPaint((float) headPoint.getX(), (float) headPoint.getY(), lightColor1,
                    (float) (headPoint.getX() + lightRate * headDirection.getX()), (float) (headPoint.getY() + lightRate * headDirection.getY()),
                    lightColor2);
        }
    }

    public int[] getXArrLight() {
        for (int i = 0; i < xArrLight.length; i++) {
            xArrLight[i] = (int) verticesLight[i].getX();
        }
        return xArrLight;
    }

    public int[] getYArrLight() {
        for (int i = 0; i < yArrLight.length; i++) {
            yArrLight[i] = (int) verticesLight[i].getY();
        }
        return yArrLight;

    }

    public void setBoolean(boolean cillip) {
        isCollisionLight = cillip;
    }

    public void updateLight() {
        setLight();
        getXArrLight();
        getYArrLight();
        polyLight.setPoly(verticesLight, this.center.getX(), this.center.getY());

    }

    @Override
    public boolean isCollision(GameObject obj) {

        if (poly.SATCollision(this.getPolygon(), obj.getPolygon())) {

            return true;
        }
        return false;
    }

    public boolean isCollisionLight(GameObject obj) {
        if (polyLight.SATCollision(polyLight, obj.getPolygon())) {

            return true;
        }
        return false;
    }

    @Override
    public void move() {

        if (movedDelay.update()) {
            motionState.move(this);
            this.updateLight();

        }
    }

    public Polygon getLight() {
        return polyLight;
    }

    @Override
    public void paint(Graphics g) {
//        poly.paint(g , "enemy");
        if (Global.TEST_OPTION) {
            g.drawPolygon(poly.getXArr(), poly.getYArr(), 4);
        }else{
            actorHelper.paint(g, (int) x, (int) y, width, height, degree, poly);
        }
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(gradient);
        g2.fillPolygon(xArrLight, yArrLight, 3);
       
//        Graphics g1 = g.create();
//        light.paint(g);
//        poly.paint(g);

    }

    @Override
    public void setMotionState(MotionState state) {
        super.motionState = state;
    }

}
