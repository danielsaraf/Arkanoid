package observer;

import sprites.Ball;
import sprites.Block;
import game.GameLevel;
import game.Counter;

/**
 * The type Block remover.
 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover listener.
     *
     * @param game1       the game 1
     * @param blockNumber the block number
     */
    public BlockRemover(GameLevel game1, Counter blockNumber) {
        this.remainingBlocks = blockNumber;
        this.game = game1;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game.
     *
     * @param beingHit the block that being hit
     * @param hitter   the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getPowerRemain() <= 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
            remainingBlocks.decrease(1);
        }
    }
}