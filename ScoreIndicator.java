package sprites;

import biuoop.DrawSurface;
import game.Counter;
import game.GameLevel;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score1 the score counter
     */
    public ScoreIndicator(Counter score1) {
        this.score = score1;
    }

    /**
     * Draw the object on the surface.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        // set the position for the text.
        int textX = (int) (GameLevel.SW / 2.7);
        int textY = 20;
        d.setColor(Color.BLACK);
        d.drawText(textX, textY, "Score: " + String.valueOf(this.score.getValue()), 20);
    }

    /**
     * notify the object that the time had passed.
     */
    public void timePassed() {
    }
}
