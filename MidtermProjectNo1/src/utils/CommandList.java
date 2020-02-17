
package utils;

import java.awt.event.KeyEvent;

public class CommandList {
    public static interface KeyHandler {
        public void keyPressed(int code);
        public void keyReleased(int code);
    }
    private boolean up;
    private boolean left;
    private boolean down;
    private boolean right;
    private boolean enter;

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setEnter(boolean enter) {
        this.enter = enter;
    }

    public void action(KeyHandler keyHandler) {
    }

    public void clear() {
        up = false;
        left = false;
        down = false;
        right = false;
        enter = false;
    }
}
