package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;


/**
 * The Direct hit background.
 */
public class DirectHitBackground implements Sprite {
    /**
     * Instantiates a new Direct hit background.
     */
    public DirectHitBackground() {
    }

    /**
     * Draw the object on the surface.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 30, GameLevel.SW, GameLevel.SH);
        d.setColor(Color.BLUE);
        d.drawLine(400, 190, 400, 290);
        d.drawLine(400, 140, 400, 30);
        d.drawLine(420, 160, 530, 160);
        d.drawLine(380, 160, 270, 160);
        d.drawCircle(400, 160, 60);
        d.drawCircle(400, 160, 120);
        d.drawCircle(400, 160, 90);
    }

    /**
     * notify the object that the time had passed.
     */
    public void timePassed() {
    }
}
