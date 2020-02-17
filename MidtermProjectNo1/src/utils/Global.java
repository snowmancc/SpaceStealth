package utils;

public class Global {

    //測資
    public static  boolean TEST_OPTION = false;
    // 資料刷新時間
    public static final int UPDATE_TIMES_PER_SEC = 60;
    public static final int MILLISEC_PER_UPDATE = 1000 / UPDATE_TIMES_PER_SEC;
    // 畫面更新時間
    public static final int FRAME_LIMIT = 60;
    public static final int LIMIT_DELTA_TIME = 1000 / FRAME_LIMIT;
    // 物件速度計算

    public static final int ACT_SPEED = 240 / UPDATE_TIMES_PER_SEC;
    public static final int ANIMA_DELAY = UPDATE_TIMES_PER_SEC / 15;

    // 圖片大小制定
    public static final int IMG_X_OFFSET = 32;
    public static final int IMG_Y_OFFSET = 32;

    //視窗大小設定
    public static final int WINDOWS_X_SIZE = 1440;
    public static final int WINDOWS_Y_SIZE = 870;

    // 上下左右常數制定
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int TEST = 5;
    public static final int SPACE = 6;
    public static final int BACKSPACE = 7;
    public static final int NUM_1 = 11;
    public static final int ENTER = 100;
    public static final int B = 8;
    public static final int P_PASS = 80;
     public static final int END = 110;

    //數學常數
    public static final int ROTATE_DEGREE = 300 / UPDATE_TIMES_PER_SEC;
}
