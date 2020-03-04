package levels;

import geometric.Rectangle;
import geometric.Point;
import sprites.DirectHitBackground;
import sprites.Velocity;
import sprites.Sprite;
import sprites.Block;

import java.util.LinkedList;
import java.util.List;

/**
 * The Level Direct hit.
 */
public class DirectHit implements LevelInformation {

    /**
     * Instantiates a new Direct hit level.
     */
    public DirectHit() {
    }

    /**
     * get the number of balls in this level.
     *
     * @return the number
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Initial and get the balls velocities.
     *
     * @return a list of velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new LinkedList<>();
        list.add(Velocity.fromAngleAndSpeed(0, 10));
        return list;
    }

    /**
     * get the paddle speed.
     *
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * get the paddle width.
     *
     * @return the paddle width
     */
    public int paddleWidth() {
        return 120;
    }

    /**
     * get the Level name string.
     *
     * @return the level name string
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Gets background.
     *
     * @return the background of this level
     */
    public Sprite getBackground() {
        return new DirectHitBackground();
    }

    /**
     * initialize and return the blocks list of this level.
     *
     * @return the blocks list
     */
    public List<Block> blocks() {
        List<Block> list = new LinkedList<>();
        list.add(new Block(new Rectangle(new Point(385, 149), 30, 30), 1));
        return list;
    }

    /**
     * get the number of blocks in this stage that should be removed.
     *
     * @return the number of blocks
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
