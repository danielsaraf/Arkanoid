package animation;

import biuoop.DrawSurface;
import sprites.MenuBackground;
import sprites.Sprite;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private Sprite background = new MenuBackground();

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
    }

    /**
     * Do one frame of the animation.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        background.drawOn(d);
        d.drawText(d.getWidth() / 5, d.getHeight() / 2 - 40, "paused", 32);
        d.drawText(d.getWidth() / 5, d.getHeight() / 2, "press space to continue", 32);
    }

    /**
     * tells if the animation should stop.
     * this animation never stops by itself.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return false;
    }
}