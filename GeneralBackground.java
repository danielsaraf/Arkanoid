package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import geometric.Point;
import geometric.Rectangle;


/**
 * The Final Four level background.
 */
public class GeneralBackground implements Sprite {
    /**
     * Instantiates a new Final Four background.
     */
    private Block bg;

    /**
     * Instantiates a new General background.
     *
     * @param f the fill
     */
    public GeneralBackground(Fill f) {
        this.bg = new Block(new Rectangle(new Point(0, 30), GameLevel.SW, GameLevel.SH), 1);
        bg.addFill(f);
    }

    /**
     * Draw the object on the surface.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        bg.drawOn(d);
    }

    /**
     * notify the object that the time had passed.
     */
    public void timePassed() {
    }
}
