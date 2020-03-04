package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LevelNameIndicator implements Sprite {
    private String lvName;

    /**
     * Instantiates a new Level name indicator.
     *
     * @param lvl the lvl
     */
    public LevelNameIndicator(String lvl) {
        this.lvName = lvl;
    }

    /**
     * Draw the object on the surface.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        // set the position for the text.
        int textX = (int) (GameLevel.SW / 1.7);
        int textY = 20;
        d.setColor(Color.BLACK);
        d.drawText(textX, textY, "Level Name: " + lvName, 20);
    }

    /**
     * notify the object that the time had passed.
     */
    public void timePassed() {
    }
}
