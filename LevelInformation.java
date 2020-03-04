package levels;

import sprites.Velocity;
import sprites.Sprite;
import sprites.Block;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * get the number of balls in this level.
     *
     * @return the number
     */
    int numberOfBalls();

    /**
     * Initial and get the balls velocities.
     *
     * @return a list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * get the paddle speed.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * get the paddle width.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * get the Level name string.
     *
     * @return the level name string
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return the background of this level
     */
    Sprite getBackground();

    /**
     * initialize and return the blocks list of this level.
     *
     * @return the blocks list
     */

    List<Block> blocks();

    /**
     * get the number of blocks in this stage that should be removed.
     *
     * @return the number of blocks
     */

    int numberOfBlocksToRemove();
}