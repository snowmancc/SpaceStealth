package gameobject;

import utils.DelayCounter;
import utils.Global;
import utils.Vector;

public abstract class MovableGameObject extends GameObject {

    private static double degreeR = Global.ROTATE_DEGREE;

    public interface MotionState {

        public abstract void move(MovableGameObject obj);

        public static class TurnRightMotion implements MotionState {

            @Override
            public void move(MovableGameObject obj) {

                obj.rotatePoints(degreeR);
                obj.direction.setX(obj.headPoint.getX() - obj.center.getX()).setY(obj.headPoint.getY() - obj.center.getY());

//                System.out.println("!");
            }
        }

        public static class ForwardMotion implements MotionState {

            @Override
            public void move(MovableGameObject obj) {

                obj.changeX = obj.getSpeed() * Math.sin((Math.PI / 180) * obj.getDegree());
                obj.changeY = -obj.getSpeed() * Math.cos((Math.PI / 180) * obj.getDegree());
                obj.changeXY(obj.changeX, obj.changeY);
                obj.setDir(obj.changeX, obj.changeY);
                for (int i = 0; i < obj.vectors.length; i++) {
                    obj.vectors[i].setX(obj.vectors[i].getX() + obj.changeX);
                    obj.vectors[i].setY(obj.vectors[i].getY() + obj.changeY);
                }

                obj.center.setX(obj.center.getX() + obj.changeX);
                obj.center.setY(obj.center.getY() + obj.changeY);
                obj.headPoint.setX(obj.headPoint.getX() + obj.changeX).setY(obj.headPoint.getY() + obj.changeY);
                obj.direction.setX(obj.changeX).setY(obj.changeY);
            }

        }

        public static class BackWardMotion implements MotionState {

            @Override
            public void move(MovableGameObject obj) {
                obj.changeX = -obj.getSpeed() * Math.sin((Math.PI / 180) * obj.getDegree());
                obj.changeY = obj.getSpeed() * Math.cos((Math.PI / 180) * obj.getDegree());
                obj.changeXY(obj.changeX, obj.changeY);
                obj.setDir(obj.changeX, obj.changeY);
                for (int i = 0; i < obj.vectors.length; i++) {
                    obj.vectors[i].setX(obj.vectors[i].getX() + obj.changeX);
                    obj.vectors[i].setY(obj.vectors[i].getY() + obj.changeY);
                }
                obj.center.setX(obj.center.getX() + obj.changeX);
                obj.center.setY(obj.center.getY() + obj.changeY);

                obj.headPoint.setX(obj.headPoint.getX() + obj.changeX).setY(obj.headPoint.getY() + obj.changeY);
                obj.direction.setX(obj.changeX).setY(obj.changeY);
            }

        }

        public static class TurnLeftMotion implements MotionState {

            @Override
            public void move(MovableGameObject obj) {
                obj.rotatePoints(-degreeR);
                obj.direction.setX(obj.headPoint.getX() - obj.center.getX()).setY(obj.headPoint.getY() - obj.center.getY());

            }

        }

        public static class StopMotion implements MotionState {

            @Override
            public void move(MovableGameObject obj) {

            }

        }

    }

    protected double maxSpeed, speed, sp;
    protected Vector headPoint;
    protected Vector headDirection;
    protected Vector direction;
    protected double changeX, changeY;
    protected MotionState motionState;
    protected DelayCounter movedDelay;
    private Vector vC;
    private Vector dir;

    public MovableGameObject(int x, int y, int width, int height, double degree, double speed) {
        super(x, y, width, height, degree);
        headPoint = new Vector(x + width / 2, y + height / 4 * 3);
        headDirection = new Vector(0, 0);
        headPoint.rotateRaf(degree * Math.PI / 180, center.getX(), center.getY());
        headDirection = headDirection.setX(headPoint.getX() - center.getX()).setY(headPoint.getY() - center.getY());

        motionState = new MotionState.StopMotion();
        movedDelay = new DelayCounter(5);
        direction = new Vector(0, 0);
        setSpeed(speed);
        dir = new Vector(0, 0);

    }

    public void CollisionMotion(Vector vector) {
        double cX = -vector.getX();
        double cY = -vector.getY();

        this.changeXY(cX, cY);

        for (int i = 0; i < this.vectors.length; i++) {
            this.vectors[i].setX(this.vectors[i].getX() + cX);
            this.vectors[i].setY(this.vectors[i].getY() + cY);
        }
        this.center.setX(this.center.getX() + cX);
        this.center.setY(this.center.getY() + cY);

        this.headPoint.setX(this.headPoint.getX() + cX).setY(this.headPoint.getY() + cY);
        this.direction.setX(0).setY(0);
//        this.dir.setX(0).setY(0);
    }

    public final double setSpeed(double speed) {
        this.speed = speed * Global.ACT_SPEED;
        return speed;
    }

    public void setDir(double x, double y) {
        dir.setX(x).setY(y);
    }

    public Vector getDir() {
        return dir;
    }

    public final double getSpeed() {
        return speed;
    }

    public abstract void move();

    public MotionState getMotionState() {

        return motionState;
    }

    public void setDegreeR(double d) {
        degreeR = d;
    }

    public abstract void setMotionState(MotionState state);

    public final Vector rotatePoints(double degree) {
        vC = changeDegree(degree);

        for (int i = 0; i < vectors.length; i++) {
            vectors[i].rotateRaf(Math.PI / 180 * degree, center.getX(), center.getY());
        }

        headPoint.rotateRaf(degree * Math.PI / 180, center.getX(), center.getY());
        headDirection.rotate0(degree * Math.PI / 180);
        //OK
        return vC;
    }

}
