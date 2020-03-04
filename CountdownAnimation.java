package animation;

import sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.GameLevel;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection sprites;
    private long sleepEachCount;
    private boolean firstFrame;
    private Sleeper sleeper;
    private int countFrom;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds to count
     * @param countNumber  the count number to count from
     * @param gameScreen   the sprite collection
     */
    public CountdownAnimation(double numOfSeconds, int countNumber, SpriteCollection gameScreen) {
        this.sleepEachCount = (long) ((numOfSeconds / (double) countNumber) * 1000);
        this.sleeper = new Sleeper();
        this.countFrom = countNumber;
        this.sprites = gameScreen;
        firstFrame = true;
    }

    /**
     * Do one frame.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        // paint the counting in a black circle
        sprites.drawAllOn(d);
        d.setColor(Color.black);
        d.fillCircle(400, 300, 40);
        d.setColor(Color.white);
        d.drawCircle(400, 300, 40);
        d.drawText((GameLevel.SW / 2) - 13, (d.getHeight() / 2) + 18, Integer.toString(countFrom), 50);
        countFrom--;
        // do not delay in the first frame
        if (!firstFrame) {
            sleeper.sleepFor(sleepEachCount);
        } else {
            firstFrame = false;
        }
    }

    /**
     * tells if the animation should stop.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        // if the count ends, stop the animation
        return countFrom == -1;
    }
}