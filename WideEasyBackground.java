package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;


/**
 * The Wide easy background.
 */
public class WideEasyBackground implements Sprite {
    /**
     * Instantiates a new Wide easy background.
     */
    public WideEasyBackground() {
    }

    /**
     * Draw the object on the surface.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 30, GameLevel.SW, GameLevel.SH);

        d.setColor(new Color(255, 255, 180));
        d.fillCircle(150, 150, 60);

        for (int i = 10; i < 790; i += 4) {
            d.drawLine(150, 150, i, 250);
        }

        d.setColor(new Color(255, 255, 120));
        d.fillCircle(150, 150, 50);
        d.setColor(new Color(255, 204, 51));
        d.fillCircle(150, 150, 40);
    }

    /**
     * notify the object that the time had passed.
     */
    public void timePassed() {
    }
}
