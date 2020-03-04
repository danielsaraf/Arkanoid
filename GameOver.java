package animation;

import biuoop.DrawSurface;
import sprites.MenuBackground;
import sprites.Sprite;

/**
 * The type Game over.
 */
public class GameOver implements Animation {
    private int score;
    private Sprite background = new MenuBackground();

    /**
     * Instantiates a new Game over.
     *
     * @param score the score when the game finished.
     */
    public GameOver(int score) {
        this.score = score;
    }

    /**
     * Do one frame of the animation.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        background.drawOn(d);
        // draw a game over massage and the score
        d.drawText(150, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
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