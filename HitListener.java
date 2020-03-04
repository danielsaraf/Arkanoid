package observer;

import sprites.Ball;
import sprites.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * the listener do something whenever there is a hit.
     *
     * @param beingHit the block that being hit
     * @param hitter   the hitter ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}