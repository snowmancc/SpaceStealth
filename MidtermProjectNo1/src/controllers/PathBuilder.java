
package controllers;

public class PathBuilder {
    private static final String ROOT = "/resources/spaceshooter";
    
    private static final String BACKGROUNDS_ROOT = "/Backgrounds";
    public static String getBackGrounds(String path){
        return ROOT + BACKGROUNDS_ROOT + path;
    }
    
    private static final String PNG_ROOT = "/PNG";
    public static String getPNG(String path){
        return ROOT + PNG_ROOT + path;
    }
   
}
