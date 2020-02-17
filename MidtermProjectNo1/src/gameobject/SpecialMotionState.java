
package gameobject;

import gameobject.MovableGameObject.MotionState;
import gameobject.SpecialMotionState.Enemy1;
import gameobject.actor.EnemyLight;

public abstract class SpecialMotionState implements MotionState{
    protected TurnRightMotion TR;
    protected ForwardMotion FW;
    protected BackWardMotion BW;
    protected TurnLeftMotion TL;
    protected StopMotion STOP;
    
    public SpecialMotionState(){
        TR = new TurnRightMotion();
        FW = new ForwardMotion();
        BW = new BackWardMotion();
        TL = new TurnLeftMotion();
        STOP = new StopMotion();
    }
    
    public static class TurnRightOfQuarter extends SpecialMotionState{
        private int count ;
        private int state;
        private boolean shouldReviseDegree;
        
        
        public TurnRightOfQuarter(){
            count = 0;
            state = 0;
            shouldReviseDegree =true;
        }
        @Override
        public void move(MovableGameObject obj) {
            for(int i = 0; i < 1; i++){
                if(state == 0){
                    STOP.move(obj);
                    if(count ++ == 20){
                        state = 1;
                        count = 0;
                    }
                }
                else{
                    TR.move(obj);
                    if(count++ == 17){
                        state = 0;
                        count = 0;
                    }
                }
            }
            
        }
    }    
    
    public static class TurnRightOfHalfBall extends SpecialMotionState{
        private int count ;
        private int state;
        private boolean shouldReviseDegree;
        
        
        public TurnRightOfHalfBall(){
            count = 0;
            state = 0;
            shouldReviseDegree =true;
        }
        @Override
        public void move(MovableGameObject obj) {
            for(int i = 0; i < 1; i++){
                if(state == 0){
                    STOP.move(obj);
                    if(count ++ == 20){
                        state = 1;
                        count = 0;
                    }
                }
                else{
                    TR.move(obj);
                    if(count++ == 35){
                        state = 0;
                        count = 0;
                    }
                }
            }
        }
    }    
    
    public static class TurnLeftOfHalfBall extends SpecialMotionState{
        private int count ;
        private int state;
        private boolean shouldReviseDegree;
        
        
        public TurnLeftOfHalfBall(){
            count = 0;
            state = 0;
            shouldReviseDegree =true;
        }
        @Override
        public void move(MovableGameObject obj) {
            for(int i = 0; i < 1; i++){
                if(state == 0){
                    STOP.move(obj);
                    if(count ++ == 20){
                        state = 1;
                        count = 0;
                    }
                }
                else{
                    TL.move(obj);
                    if(count++ == 35){
                        state = 0;
                        count = 0;
                    }
                }
            }
        }
    }
    
    public static class Enemy1 extends SpecialMotionState{
        private MotionState[] acts = {TL, STOP,TR,STOP,TR,STOP,TL,STOP};
        private int count ;
        private int state;
        private boolean shouldReviseDegree;
        
        public Enemy1(){
            count = 0;
            state = 0;
            shouldReviseDegree =true;
        }
        @Override
        public void move(MovableGameObject obj) {
           if(count++ < 15){
               acts[state % 8].move(obj);
           }
           else{
               count = 0;
               state ++;
           }
        }
    }
    
    public static class Enemy2 extends SpecialMotionState{
        private int count;
        public Enemy2 (){
            count = -15;
        }
        @Override
        public void move(MovableGameObject obj) {
            if(count++ < 0){
               
            }
            else if (count < 20){
                BW.move(obj);
            }
            else if(count < 37){
                TL.move(obj);
            }
            else if(count < 47){
                BW.move(obj);
            }
            else if(count < 65){
                TR.move(obj);
            }
            else if(count < 74){
                TL.move(obj);
            }
            else if(count < 94){
                BW.move(obj);
            }
            else if(count < 165){
                TL.move(obj);
            }
            else if(count < 180){
                BW.move(obj);
            }
            else if(count < 190){
                TL.move(obj);
            }
            else if(count < 200){
                STOP.move(obj);
            }
            else if(count < 215){
                BW.move(obj);
            }
            else if(count < 230){
                TL.move(obj);
            }
            else{
                obj.setMotionState(new SpecialMotionState.Enemy1());
            }
        }
        
    }
    
    public static class Enemy3 extends SpecialMotionState{
        private int count;
        public Enemy3(){
            count = -15;
        }
        @Override
        public void move(MovableGameObject obj) {
            if(count++ <0 ){
                
            }
            else if(count  < 45){
                TL.move(obj);
            }
            else if(count < 80){
                BW.move(obj);
            }
            else if(count < 150){
                TR.move(obj);
            }
            else if(count < 180){
                BW.move(obj);
            }
            else if(count < 190){
                TR.move(obj);
            }
            else if (count < 215){
                BW.move(obj);
            }
            else if(count < 240){
                TR.move(obj);
            }
            else{
                obj.setMotionState(new SpecialMotionState.Enemy1());
            }
        }
        
    }
    
    public static class Enemy4 extends SpecialMotionState{
        private int count;
        public Enemy4 (){
            count = -5;
        }
        @Override
        public void move(MovableGameObject obj) {
            if(count++ < 0){
                STOP.move(obj);
            }
            else if(count < 28){
                TR.move(obj);
            }
            else if(count < 65){
                BW.move(obj);
            }
            else if(count < 85){
                TR.move(obj);
            }
            else if(count < 120){
                BW.move(obj);
            }
            else if (count < 135){
                TR.move(obj);
            }
            else if(count < 170){
                BW.move(obj);
            }
            else if(count < 190){
                TR.move(obj);
            }
            else if(count < 227){
                BW.move(obj);
            }
            else if(count < 290){
                TR.move(obj);
            }
            else {
                TR.move(obj);
            }
        }
        
    }
    
    public static class Enemy5 extends SpecialMotionState{
        private int count;
        private boolean back;
        public Enemy5(){
            count = -45;
            back = false;
        }
        @Override
        public void move(MovableGameObject obj) {
            if(count++ < 0){
                
            }
            else if(obj.getY() != 534 && obj.getX() == 330 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -90 && obj.getX() == 330 && !back){
                TL.move(obj);
            }
            else if(obj.getX() != 882&& !back){
                BW.move(obj);
            }
            else if(obj.getDegree()!= -270 && obj.getY() == 534 && !back){
                TL.move(obj);
                if( obj.getDegree() == -270){
                    back = true;
                }
            }
            else if(obj.getX() != 330&& back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -180 && obj.getY() == 534 && back){
                TR.move(obj);
            }
            else if(obj.getY() != 150&& back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 0 && obj.getX() == 330 && back){
                TR.move(obj);
                if(obj.getDegree() == 0){
                    back =false;
                }
            }
            else{
                
            }
        }
    }
    
    public static class Enemy6 extends SpecialMotionState{
        private int count;
        private boolean back;
        public Enemy6(){
            count = -10;
            back = false;
        }
        @Override
        public void move(MovableGameObject obj) {
            if(count++ < 0){
                
            }
            else if(obj.getDegree() != -180 && obj.getY() == 700 && !back){
                TL.move(obj);
            }
            else if(obj.getY() != 160 && obj.getX() == 530 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -90 && obj.getY() == 160 && obj.getX() == 530 && !back){
                TR.move(obj);
            }
            else if(obj.getX() != 854 && obj.getY() == 160 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 90 && obj.getX() == 854 && !back){
                TR.move(obj);
                 if( obj.getDegree() == 90){
                    back = true;
                }
            }
            else if(obj.getX() != 530 && obj.getY() == 160 && back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 0 && obj.getY() == 160 && back){
                TL.move(obj);
            }
             else if(obj.getY() != 700 && obj.getX() == 530 && back){
                BW.move(obj);
                if(obj.getY() == 700){
                    back = false;
                }
            }
        }
    }
    
    public static class Enemy7 extends SpecialMotionState{
        private int count;
        private boolean back;
        public Enemy7(){
            count = -45;
            back = false;
        }
        @Override
        public void move(MovableGameObject obj) {
            if(count++ < 0){
            }
            else if(obj.getY() != 702 && obj.getX() == 880 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 90 && obj.getX() == 880 && !back){
                TR.move(obj);
            }
            else if(obj.getX() != 472 && obj.getY() == 702 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree()!= 270 && obj.getY() == 702 && !back){
                TR.move(obj);
                if( obj.getDegree() == 270){
                    back = true;
                }
            }
            else if(obj.getX() != 880 && obj.getY() == 702 && back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 180 && obj.getY() == 702 && back){
                TL.move(obj);
            }
            else if(obj.getY() != 150 && back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 0 && obj.getY() == 150 && back){
                TL.move(obj);
                if(obj.getDegree() == 0){
                    back = false;
                }
            }
        }
    }
    
    public static class Enemy8 extends SpecialMotionState{
        private int count;
        private boolean back;
        public Enemy8(){
            count = -10;
            back = false;
        }
        @Override
        public void move(MovableGameObject obj) {
            if(count++ < 0){
            }
            else if(obj.getDegree() != -180 && obj.getY() == 700 && !back){
                TL.move(obj);
            }
            else if(obj.getY() != 316 && obj.getX() == 1120 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -270 && obj.getY() == 316 && obj.getX() == 1120 && !back){
                TL.move(obj);
            }
            else if(obj.getX() != 544 && obj.getY() == 316 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -90 && obj.getX() == 544 && !back){
                TR.move(obj);
                 if( obj.getDegree() == -90){
                    back = true;
                }
            }
            else if(obj.getX() != 1120 && back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 0 && obj.getY() == 316 && back){
                TR.move(obj);
            }
            else if(obj.getY() != 700 && back){
                BW.move(obj);
                
                if(obj.getY() == 700 ){
                    back = false;
                }
            }
        }
    }
    
     public static class Enemy9 extends SpecialMotionState{
        private int count;
        private boolean back;
        public Enemy9(){
            count = -10;
            back = false;
        }
        @Override
        public void move(MovableGameObject obj) {
            if(count++ < 0){
                
            }
            else if(obj.getDegree() != -180 && obj.getY() == 700 && !back){
                TL.move(obj);
            }
            else if(obj.getY() != 316 && obj.getX() == 330 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -90 && obj.getY() == 316 && obj.getX() == 330 && !back){
                TR.move(obj);
            }
            else if(obj.getX() != 882 && obj.getY() == 316 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -270 && obj.getX() == 882 && !back){
                TL.move(obj);
                 if( obj.getDegree() == -270){
                    back = true;
                }
            }
            else if(obj.getX() != 330 && obj.getY() == 316 && back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -360 && obj.getY() == 316 && back){
                TL.move(obj);
            }
             else if(obj.getY() != 700 && obj.getX() == 330 && back){
                BW.move(obj);
                if(obj.getY() == 700){
                    back = false;
                }
            }
        }
    }
    
    public static class Enemy10 extends SpecialMotionState{
        private int count;
        private boolean back;
        public Enemy10(){
            count = -45;
            back = false;
        }
        @Override
        public void move(MovableGameObject obj) {
            if(count++ < 0){
            }
            else if(obj.getY() != 702 && obj.getX() == 530 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -90 && obj.getX() == 530 && !back){
                TL.move(obj);
            }
            else if(obj.getX() != 878&& !back){
                BW.move(obj);
            }
            else if(obj.getDegree()!= -270 && obj.getY() == 702 && !back){
                TL.move(obj);
                if( obj.getDegree() == -270){
                    back = true;
                }
            }
            else if(obj.getX() != 530&& back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -180 && obj.getY() == 702 && back){
                TR.move(obj);
            }
            else if(obj.getY() != 150 && back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 0 && obj.getX() == 530 && back){
                TR.move(obj);
                if(obj.getDegree() == 0){
                    back =false;
                }
            }
        }
    }
    
    public static class Enemy11 extends SpecialMotionState{
        private int count;
        private boolean back;
        public Enemy11(){
            count = -10;
            back = false;
        }
        @Override
        public void move(MovableGameObject obj) {
            if(count++ < 0){
            }
            else if(obj.getDegree() != -180 && obj.getY() == 700 && !back){
                TL.move(obj);
            }
            else if(obj.getY() != 160 && obj.getX() == 880 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -270 && obj.getY() == 160 && obj.getX() == 880 && !back){
                TL.move(obj);
            }
            else if(obj.getX() != 484 && obj.getY() == 160 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != -90 && obj.getX() == 484 && !back){
                TR.move(obj);
                 if( obj.getDegree() == -90){
                    back = true;
                }
            }
            else if(obj.getX() != 880 && obj.getY() == 160 && back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 0 && obj.getY() == 160 && back){
                TR.move(obj);
            }
             else if(obj.getY() != 700 && obj.getX() == 880 && back){
                BW.move(obj);
                if(obj.getY() == 700){
                    back = false;
                }
            }
        }
    }
    
    public static class Enemy12 extends SpecialMotionState{
        private int count;
        private boolean back;
        public Enemy12(){
            count = -45;
            back = false;
        }
        @Override
        public void move(MovableGameObject obj) {
            if(count++ < 0){
//                System.out.println(obj.getX() + "   " + obj.getY());
//                System.out.println(obj.getDegree());
            }
            else if(obj.getY() != 546 && obj.getX() == 1120 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 90 && obj.getX() == 1120 && !back){
                TR.move(obj);
            }
            else if(obj.getX() != 520 && obj.getY() == 546 && !back){
                BW.move(obj);
            }
            else if(obj.getDegree()!= 270 && obj.getY() == 546 && !back){
                TR.move(obj);
                if( obj.getDegree() == 270){
                    back = true;
                }
            }
            else if(obj.getX() != 1120 && obj.getY() == 546 && back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 180 && obj.getY() == 546 && back){
                TL.move(obj);
//                System.out.println(obj.getDegree());
            }
            else if(obj.getY() != 150 && back){
                BW.move(obj);
            }
            else if(obj.getDegree() != 0 && obj.getY() == 150 && back){
                TL.move(obj);
                if(obj.getDegree() == 0){
                    back = false;
                }
            }
        }
    }
    
    public static class SweepingRobot extends SpecialMotionState{ //需要雷達或是有警覺功能
        private int count;
        @Override
        public void move(MovableGameObject obj) {
            if(count ++ < 20){
                BW.move(obj);
            }
            else{
                count = 0;
                obj.setMotionState(new SpecialMotionState.RandomAct());
            }
            
        }
    }
    
    public static class RandomAct extends SpecialMotionState{
            private MotionState[] acts = {TR,TL,TL,TR};
            private int count ;
            private int index;
        
        @Override
        public void move(MovableGameObject obj) {
            if(count++ < 4){
                FW.move(obj);
            }
            else if( count < 6){
                index = (int)(Math.random() * 4);
            }
            else if(count < 26){
                acts[index].move(obj);
            }
            else{
                count = 0;
                index = 0;
                obj.setMotionState(new SpecialMotionState.SweepingRobot());
            }
        }
        
    }
    
    public static class TotalAct extends SpecialMotionState{
            private MotionState[] acts = {new SweepingRobot(),new Enemy1(),new Enemy2(),new Enemy3(),
                new Enemy4(),new TurnRightOfQuarter(),new TurnRightOfHalfBall(),new TurnLeftOfHalfBall(),new SweepingRobot()};
            private boolean haveAct;
            private int index;
            
            public TotalAct(){
                index = (int)(Math.random() * 9);
            }
        
        @Override
        public void move(MovableGameObject obj) {
            acts[index].move(obj);
        }
    }
    
}
