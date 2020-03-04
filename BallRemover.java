package observer;

import sprites.Ball;
import sprites.Block;
import game.Counter;
import game.GameLevel;

/**
 * The type Ball remover.
 */

public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover listener.
     *
     * @param game1       the main game
     * @param ballsNumber the balls number counter
     */
    public BallRemover(GameLevel game1, Counter ballsNumber) {
        this.remainingBalls = ballsNumber;
        this.game = game1;
    }

    /**
     * Hit event take place, remove the ball that hits the block.
     *
     * @param beingHit the block that being hit
     * @param hitter   the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}