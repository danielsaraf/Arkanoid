package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;


/**
 * The Final Four level background.
 */
public class MenuBackground implements Sprite {
    /**
     * Instantiates a new  Final Four background.
     */
    public MenuBackground() {
    }

    /**
     * Draw the object on the surface.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(23, 136, 208));
        d.fillRectangle(0, 0, GameLevel.SW, GameLevel.SH);
        d.setColor(new Color(255, 255, 180));
        d.fillCircle(770, 20, 60);
        d.setColor(new Color(255, 255, 120));
        d.fillCircle(770, 20, 50);
        d.setColor(new Color(255, 204, 51));
        d.fillCircle(770, 20, 40);
        d.setColor(Color.WHITE);
        d.setColor(new Color(200, 200, 200));
        d.fillCircle(90, 360, 40);
        d.fillCircle(100, 340, 40);
        d.setColor(new Color(200, 205, 200));
        d.fillCircle(122, 350, 35);
        d.setColor(new Color(200, 205, 205));
        d.fillCircle(120, 385, 40);
        d.setColor(new Color(205, 205, 205));
        d.fillCircle(145, 360, 40);
        d.setColor(new Color(210, 205, 205));
        d.fillCircle(180, 360, 50);
        d.setColor(new Color(210, 205, 210));
        d.setColor(new Color(190, 190, 190));
        d.fillCircle(610, 460, 40);
        d.fillCircle(620, 440, 40);
        d.setColor(new Color(195, 195, 190));
        d.fillCircle(640, 450, 35);
        d.setColor(new Color(190, 190, 190));
        d.fillCircle(620, 485, 40);
        d.setColor(new Color(193, 195, 191));
        d.fillCircle(665, 460, 40);
        d.setColor(new Color(190, 190, 190));
        d.fillCircle(700, 460, 50);
        d.setColor(Color.black);

    }

    /**
     * notify the object that the time had passed.
     */
    public void timePassed() {
    }
}
