package observer;

import sprites.Block;
import sprites.Ball;
import game.Counter;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener with the score counter.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }


    /**
     * Hit event take place, change the score.
     * increase 5 point in case of a hit, and 10 additional point if the block are broken.
     *
     * @param beingHit the block that being hit
     * @param hitter   the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        if (beingHit.getPowerRemain() == 0) {
            currentScore.increase(10);
        }
    }
}