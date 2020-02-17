package gameobject;

import java.awt.Graphics;
import utils.Global;

import utils.Vector;

public abstract class GameObject {

    public interface GameObjectLinstener {

        public void onCollision();
    }
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double degree;
    protected GameObjectLinstener listener;
    protected Circle circle;
    protected Vector center;
    protected Vector centerVector;
    protected Vector[] vectors;
    protected Polygon poly;
    private Double d1;
    protected Vector direction;
    private Vector vC;
    protected boolean isCollision;

    public GameObject(double x, double y, double width, double height, double degree) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.degree = degree;
        center = new Vector(x + width / 2, y + height / 2);
        circle = new Circle(center, Math.max(width * 3 / 4, height * 3 / 4));
        vectors = new Vector[4];
        double w = width / 8;
        double h = height / 8;
        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new Vector(0, 0);
        }
        this.setVectors(0, x + w, y + h);
        this.setVectors(1, x + width - w, y + h);
        this.setVectors(2, x + width - w, y + height - h);
        this.setVectors(3, x + w, y + height - h);
        isCollision = false;
        poly = new Polygon(vectors, center.getX(), center.getY());
        poly.polyRotate(degree - 0);
    }

    public void setVectors(int index, double x, double y) {
        this.vectors[index].setX(x).setY(y);
    }

    // coordinate start
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDegree(double degree) {

        d1 = Math.abs(degree);

        if (d1.compareTo(360.0) > 0) {
            degree %= 360;
        }

    }

    public Vector changeDegree(double changeDegree) {
        d1 = Math.abs(changeDegree);

        if (d1.compareTo(360.0) > 0) {
            changeDegree %= 360;
        }
        poly.polyRotate(changeDegree);

        this.degree += changeDegree;
        degree = this.getDegree();
        return vC;

    }

    public double getDegree() {
        d1 = Math.abs(degree);
        if (d1.compareTo(360.0) > 0) {
            degree %= 360;
        }

        return degree;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Polygon getPolygon() {
        return poly;
    }

    public Vector getCenter() {
        return center;
    }

    public Circle getCircle() {
        return circle;
    }

    public void changeXY(double x, double y) {
        this.x += x;
        this.y += y;
        poly.polyMove(x, y);
    }

    // coordinate end
    // Bound start
    public double getBottom() {
        return y + height;
    }

    public double getTop() {
        return y;
    }

    public double getLeft() {
        return x;
    }

    public double getRight() {
        return x + width;
    }

    public Vector[] getPoints() {
        return vectors;
    }

    // Bound end
    public boolean isCollision(GameObject obj) {

        if (poly.SATCollision(this.getPolygon(), obj.getPolygon())) {
            isCollision = true;
            return true;
        } else {
            isCollision = false;
        }

        return false;
    }

    public boolean getISC() {
        return isCollision;
    }

    public boolean isCollisionCircle(GameObject obj) {
        if (poly.SATCollision(this.circle, obj.getPolygon())) {

            return true;
        }
        return false;
    }

    public void setListener(GameObjectLinstener listener) {
        this.listener = listener;
    }

    public void triggerListener() {
        this.listener.onCollision();
    }

    public abstract void paint(Graphics g);

}
