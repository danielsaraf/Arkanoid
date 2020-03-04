package levels;

import geometric.Point;
import geometric.Rectangle;
import sprites.Velocity;
import sprites.Sprite;
import sprites.Block;
import sprites.WideEasyBackground;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Wide easy level.
 */
public class WideEasy implements LevelInformation {

    /**
     * Instantiates a new Wide easy level.
     */
    public WideEasy() {
    }

    /**
     * get the number of balls in this level.
     *
     * @return the number
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * Initial and get the balls velocities.
     *
     * @return a list of velocities
     */
    public List<Velocity> initialBallVelocities() {
        double angel = 12.85;
        List<Velocity> list = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(Velocity.fromAngleAndSpeed(-(angel * i), 6));
        }
        for (int i = 1; i <= 5; i++) {
            list.add(Velocity.fromAngleAndSpeed((angel * i), 6));
        }
        return list;
    }

    /**
     * get the paddle speed.
     *
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return 1;
    }

    /**
     * get the paddle width.
     *
     * @return the paddle width
     */
    public int paddleWidth() {
        return 650;
    }

    /**
     * get the Level name string.
     *
     * @return the level name string
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Gets background.
     *
     * @return the background of this level
     */
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    /**
     * initialize and return the blocks list of this level.
     *
     * @return the blocks list
     */
    public List<Block> blocks() {
        List<Block> list = new LinkedList<>();
        int y = 250;
        list.add(new Block(new Rectangle(new Point(11, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(63, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(115, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(167, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(219, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(271, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(323, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(375, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(427, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(479, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(531, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(583, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(635, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(687, y), 51, 25), 1));
        list.add(new Block(new Rectangle(new Point(739, y), 50, 25), 1));
        return list;
    }

    /**
     * get the number of blocks in this stage that should be removed.
     *
     * @return the number of blocks
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
