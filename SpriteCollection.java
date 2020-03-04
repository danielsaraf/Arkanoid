package sprites;

import biuoop.DrawSurface;

import java.util.List;
import java.util.ArrayList;

/**
 * The type Sprite collection.
 *
 * @author Daniel Saraf.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        // Initialize a list of spirits
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Add sprite to the sprites list.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Remove sprite from the sprites list.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Notify all time passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.sprites);
        for (Sprite c : spritesCopy) {
            c.timePassed();
        }
    }

    /**
     * call drawOn to all the spirits on the list.
     *
     * @param d the surface
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.sprites);
        for (Sprite c : spritesCopy) {
            c.drawOn(d);
        }
    }
}