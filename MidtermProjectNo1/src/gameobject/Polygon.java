package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import utils.Global;
import utils.Vector;

/**
 *
 * @author muheng
 */
public class Polygon {

    private class MTV {

        private Vector smallest;
        private double length;

        private MTV() {
            smallest = new Vector(0, 0);
        }

        private void standardSmallest(Vector smallest) {
            length = smallest.getLength();
            smallest.setX(smallest.getX() / length).setY(smallest.getY() / length);
        }

        private Vector setMTV(double overlap, Vector smallest) {
            this.standardSmallest(smallest);
            this.smallest.setX(smallest.getX() * overlap).setY(smallest.getY() * overlap);
            return this.smallest;
        }
    }

    private class VectorList {

        private Vector[] arr;
        private int count;

        public VectorList() {
            arr = new Vector[2];

            count = 0;
        }

        private void dbArr() {
            Vector[] tmp = new Vector[arr.length * 2];
            for (int i = 0; i < arr.length; i++) {
                tmp[i] = arr[i];
            }
            arr = tmp;
        }

        public void add(Vector vector) {
            if (count == arr.length) {
                dbArr();
            }
            arr[count++] = vector;
        }

        public void set(Vector vector, int index) {
            arr[index] = vector;
        }

        public Vector get(int index) {
            return arr[index];
        }

        public void clear() {

            count = 0;
        }

        public int length() {
            return count;
        }

    }
    private Vector center, n;

    private Double minA, maxA, minB, maxB, min, max, tmp;
    private VectorList vertices, corners;/////
    private VectorList norms, normB, normA;

    private Vector p1, p2, t;
    private int b1, b2;
    private int[] xArr, yArr;
    private double x0, y0;
    private Vector smallest;
    private double overlap;
    private double o;
    private MTV mtv;

    public Polygon(Vector center, double radius) {
        this.center = center;
        smallest = new Vector(0, 0);
        overlap = 900000000;
        o = overlap;
        mtv = new MTV();
    }

    public Polygon(Vector[] vectors, double centerX, double centerY) {
        center = new Vector(centerX, centerY);
        xArr = new int[vectors.length];
        yArr = new int[vectors.length];
        overlap = 900000000;
        mtv = new MTV();
        o = overlap;
        norms = new VectorList();
        vertices = new VectorList();
        n = new Vector(0, 0);
        for (int i = 0; i < vectors.length; i++) {
            vertices.add(new Vector(vectors[i].getX(), vectors[i].getY()));///
        }
        smallest = new Vector(0, 0);

    }

    public void polyMove(double changeX, double changeY) {
        for (int i = 0; i < vertices.length(); i++) {
            x0 = vertices.get(i).getX();
            y0 = vertices.get(i).getY();
            vertices.get(i).setX(x0 + changeX);///
            vertices.get(i).setY(y0 + changeY);///
        }
        setCenter(center.getX() + changeX, center.getY() + changeY);

    }

    public void polyRotate(double degreeChangee) {
        for (int i = 0; i < vertices.length(); i++) {
            vertices.get(i).rotateRaf((Math.PI / 180) * degreeChangee, center.getX(), center.getY());

        }///
    }

    public void setPoly(Vector[] vectors, double centerX, double centerY) {

        this.setCenter(centerX, centerY);

        for (int i = 0; i < vectors.length; i++) {
            vertices.get(i).setX(vectors[i].getX()).setY(vectors[i].getY());
        }

    }

    public int[] getXArr() {

        for (int i = 0; i < vertices.length(); i++) {
            xArr[i] = (int) vertices.get(i).getX();
        }
        return xArr;
    }

    public int[] getYArr() {
        for (int i = 0; i < vertices.length(); i++) {
            yArr[i] = (int) vertices.get(i).getY();
        }
        return yArr;
    }

    public void setCenter(double x, double y) {
        center.setX(x).setY(y);
    }

    public double getMin(Vector axis) {

        min = vertices.get(0).getLengthOnVector(axis);

        for (int i = 1; i < vertices.length(); i++) {
            tmp = vertices.get(i).getLengthOnVector(axis);

            if (min.compareTo(tmp) > 0) {
                min = tmp;
            }
        }
        return min;
    }

    public double getMax(Vector axis) {

        max = vertices.get(0).getLengthOnVector(axis);

        for (int i = 1; i < vertices.length(); i++) {
            tmp = vertices.get(i).getLengthOnVector(axis);

            if (max.compareTo(tmp) < 0) {
                max = tmp;
            }
        }
        return max;
    }

    public VectorList getVertices() {
        return vertices;
    }

    public VectorList getNorms() {
//        getVertices();
        norms.clear();

        for (int i = 1; i < vertices.length(); i++) {
            p1 = vertices.get(i - 1);
            p2 = vertices.get(i);

            n = n.setX(p2.getX() - p1.getX())
                    .setY(p2.getY() - p1.getY());

            norms.add(n.getNormalL());

            n = n.setX(p1.getX() - p2.getX())
                    .setY(p1.getY() - p2.getY());
            norms.add(n.getNormalL());

        }
        p1 = vertices.get(vertices.length() - 1);
        p2 = vertices.get(0);

        n = n.setX(p2.getX() - p1.getX())
                .setY(p2.getY() - p1.getY());

        norms.add(n.getNormalL());
        n = n.setX(p1.getX() - p2.getX())
                .setY(p1.getY() - p2.getY());
        norms.add(n.getNormalL());

        return norms;
    }

    public boolean SATCollision(Polygon polyA, Polygon polyB) {
        normA = polyA.getNorms();
        normB = polyB.getNorms();
        overlap = 900000000;
        if (normA.length() >= normB.length()) {
//            System.out.println("ca");

            for (int i = 0; i < normA.length(); i++) {
                minA = polyA.getMin(normA.get(i));
                maxA = polyA.getMax(normA.get(i));
                minB = polyB.getMin(normA.get(i));
                maxB = polyB.getMax(normA.get(i));

                b1 = minB.compareTo(maxA);
                b2 = minA.compareTo(maxB);
                if (b1 > 0 || b2 > 0) {
                    return false;
                } else {
                    o = b1 > 0 ? maxB - minA : maxA - minB;
                    if (o < overlap) {
                        overlap = o;
                        smallest = normA.get(i);
                    }
                }
                if (i >= normB.length()) {
                    continue;
                } else {
                    minA = polyA.getMin(normB.get(i));
                    maxA = polyA.getMax(normB.get(i));
                    minB = polyB.getMin(normB.get(i));
                    maxB = polyB.getMax(normB.get(i));

                    b1 = minB.compareTo(maxA);
                    b2 = minA.compareTo(maxB);

                    if (b1 > 0 || b2 > 0) {
                        return false;
                    } else {
                        o = b1 > 0 ? maxB - minA : maxA - minB;

                        if (o < overlap) {
                            overlap = o;
                            smallest = normB.get(i);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < normB.length(); i++) {
                minA = polyA.getMin(normB.get(i));
                maxA = polyA.getMax(normB.get(i));
                minB = polyB.getMin(normB.get(i));
                maxB = polyB.getMax(normB.get(i));

                b1 = minB.compareTo(maxA);
                b2 = minA.compareTo(maxB);

                if (b1 > 0 || b2 > 0) {

                    return false;
                } else {
                    o = b1 > 0 ? maxB - minA : maxA - minB;
                    if (o < overlap) {

                        overlap = o;
                        smallest = normB.get(i);
                    }
                }
                if (i >= normA.length()) {
                    continue;
                } else {
                    minA = polyA.getMin(normA.get(i));
                    maxA = polyA.getMax(normA.get(i));
                    minB = polyB.getMin(normA.get(i));
                    maxB = polyB.getMax(normA.get(i));

                    b1 = minB.compareTo(maxA);
                    b2 = minA.compareTo(maxB);

                    if (b1 > 0 || b2 > 0) {

                        return false;
                    } else {
                        o = b1 > 0 ? maxB - minA : maxA - minB;
                        if (o < overlap) {

                            overlap = o;
                            smallest = normA.get(i);//
                        }
                    }
                }
            }
        }

        return true;
    }

    public Vector getMTV() {
        return mtv.setMTV(overlap, smallest);
    }

//    public Vector getDeepness() {
//        deepness.setX(deepX).setY(deepY);
//        return deepness;
//    }
    public boolean SATCollision(Circle circle, Polygon poly) {
        normB = poly.getNorms();
        overlap = 900000000;

        for (int i = 0; i < normB.length(); i++) {
            minB = poly.getMin(normB.get(i));
            maxB = poly.getMax(normB.get(i));
            double pC = circle.getCenter().getLengthOnVector(normB.get(i));
            minA = pC - circle.getRadius() / 2;
            maxA = pC + circle.getRadius() / 2;
            if (minA > maxB || minB > maxA) {
                return false;
            } else {
                o = b1 > 0 ? maxB - minA : maxA - minB;
                if (o < overlap) {
                    overlap = o;
                    smallest = normB.get(i);
                }
            }
        }
        return true;
    }

    public void paint(Graphics g) {

        g.setColor(Color.white);
        g.drawPolygon(this.getXArr(), this.getYArr(), vertices.length());
//            g.setColor(Color.green);
//            for(int i = 0 ; i < vertices.length() -1 ; i++){
//                g.drawLine((int)vertices.get(i).getX(),(int) vertices.get(i).getY(), 
//                        (int)vertices.get(i + 1).getX(), (int) vertices.get(i + 1).getY());
//            }

    }

}
