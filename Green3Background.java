package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;


/**
 * The  green 3 level background.
 */
public class Green3Background implements Sprite {
    /**
     * Instantiates a new green 3 background.
     */
    public Green3Background() {
    }

    /**
     * Draw the object on the surface.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0, 140, 0));
        d.fillRectangle(0, 30, GameLevel.SW, GameLevel.SH);

        d.setColor(Color.GRAY);
        d.fillRectangle(95, 250, 10, 250);
        d.setColor(Color.orange);
        d.fillCircle(100, 250, 12);
        d.setColor(Color.red);
        d.fillCircle(100, 250, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(100, 250, 3);
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(85, 400, 30, 250);
        d.setColor(Color.BLACK);
        d.fillRectangle(50, 450, 100, 250);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(55 + (j * 20), 455 + (i * 30), 10, 20);
            }
        }
    }

    /**
     * notify the object that the time had passed.
     */
    public void timePassed() {
    }
}
