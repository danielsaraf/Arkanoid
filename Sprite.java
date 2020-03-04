package sprites;

import biuoop.DrawSurface;
/**
 * The interface Sprite, all the spirits implement it.
 */
public interface Sprite {
    /**
     * Draw the object on the surface.
     *
     * @param d the surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the object that the time had passed.
     */
    void timePassed();
}