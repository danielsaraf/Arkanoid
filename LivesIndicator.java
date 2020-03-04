package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Counter;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param live the lives counter
     */
    public LivesIndicator(Counter live) {
        this.lives = live;
    }

    /**
     * Draw the object on the surface.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        // set the position for the text.
        int textX = GameLevel.SW / 7;
        int textY = 20;
        d.setColor(Color.BLACK);
        d.drawText(textX, textY, "Lives: " + this.lives.getValue(), 20);
    }

    /**
     * notify the object that the time had passed.
     */
    public void timePassed() {
    }
}
