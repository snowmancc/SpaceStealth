package gameobject.actor;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import gameobject.Polygon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import utils.Global;

import values.ImagePath;

public class ActorHelper {

    private BufferedImage img;
    private String[] str;
    private int rand;
    private String[] rock1, rock2;

    public ActorHelper(int actor) {
        str = new String[4];
        str[0] = ImagePath.PNG.ROCK_BROWN2;
        str[1] = ImagePath.PNG.ROCK_BROWN1;
        str[2] = ImagePath.PNG.ROCK_GREY1;
        str[3] = ImagePath.PNG.ROCK_GREY2;

        rock2 = new String[]{ImagePath.PNG.ROCK_GREYMED, ImagePath.PNG.ROCK_BROWNMED};
        rock1 = new String[]{ImagePath.PNG.ROCK_BROWN1, ImagePath.PNG.ROCK_GREY1};

        img = getActor(actor);
    }

    private BufferedImage getActor(int actor) {
        ImageResourceController irc = ImageResourceController.getInstance();
        switch (actor) {
            case 0:
                return irc.tryGetImage(PathBuilder.getPNG(ImagePath.PNG.PLAYER_SHIP_RED));
            case 1:
                return irc.tryGetImage(PathBuilder.getPNG(ImagePath.PNG.ENEMY_SHIP_BLACK2));
            case 2:
                rand = (int) (Math.random() * 4);
                return irc.tryGetImage(PathBuilder.getPNG(str[rand]));
            case 3:
                return irc.tryGetImage(PathBuilder.getPNG(ImagePath.PNG.ASSIGNMENT));
            case 4:
                return irc.tryGetImage(PathBuilder.getPNG(ImagePath.PNG.ASSIGNMENT_GREEN));
            case 5:
                return irc.tryGetImage(PathBuilder.getPNG(ImagePath.PNG.ASSIGNMENT_GREEN2));
            case 6:
                return irc.tryGetImage(PathBuilder.getPNG(ImagePath.PNG.TARGET));

            case 7:
                return irc.tryGetImage(PathBuilder.getPNG(ImagePath.PNG.Effects.Fire_SHORT_BLUE));
            case 8:
                return irc.tryGetImage(PathBuilder.getPNG(ImagePath.PNG.Effects.Fire_LONG_BLUE));
            case 9:
                rand = (int) (Math.random() * 2) + 1;
                return irc.tryGetImage(PathBuilder.getPNG(rock2[rand -1]));
            case 10:
               rand = (int) (Math.random() * 2)  + 1;
                return irc.tryGetImage(PathBuilder.getPNG(rock2[rand - 1]));
            case 11:
                rand = (int) (Math.random() * 2) + 1 ;
                return irc.tryGetImage(PathBuilder.getPNG(rock1[rand - 1]));/////////////

        }
        return null;
    }

    public void paint(Graphics g, int x, int y, int width, int height, double degree, Polygon poly) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.rotate((Math.PI / 180) * (degree % 360), x + width / 2, y + height / 2);
        g2.drawImage(img, x, y, width, height, null);
//        g2.drawString(degree+ "", x, y);

        if (Global.TEST_OPTION) {
            g2.setColor(Color.pink);
            g2.drawRect(x, y, width, height);

            g2.setColor(Color.yellow);

//            g2.drawPolygon(poly.getXArr(), poly.getYArr(), poly.getXArr().length);
//            g2.drawString(degree+ "",poly.getXArr()[0] , poly.getYArr()[0]);
        }
    }
}
