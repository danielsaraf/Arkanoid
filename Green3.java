package levels;

import geometric.Point;
import geometric.Rectangle;
import sprites.Velocity;
import sprites.Sprite;
import sprites.Block;
import sprites.Green3Background;

import java.util.LinkedList;
import java.util.List;

/**
 * The level Green 3.
 */
public class Green3 implements LevelInformation {

    /**
     * Instantiates a new Green 3 level.
     */
    public Green3() {
    }

    /**
     * get the number of balls in this level.
     *
     * @return the number
     */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * Initial and get the balls velocities.
     *
     * @return a list of velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new LinkedList<>();
        list.add(Velocity.fromAngleAndSpeed(45, 10));
        list.add(Velocity.fromAngleAndSpeed(-45, 10));
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
        return "Green3";
    }

    /**
     * Gets background.
     *
     * @return the background of this level
     */
    public Sprite getBackground() {
        return new Green3Background();
    }

    /**
     * initialize and return the blocks list of this level.
     *
     * @return the blocks list
     */
    public List<Block> blocks() {
        List<Block> list = new LinkedList<>();
        int[] colorIndex = {1, 8, 2, 7, 10};
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                for (int j = 0; j < 10 - i; j++) {
                    Point p = new Point((740) - (j * 50), 100 + (i * (26)));
                    Rectangle rec = new Rectangle(p, 50, 25);
                    Block b = new Block(rec, 2);
                    list.add(b);
                }
            } else {
                for (int j = 0; j < 10 - i; j++) {
                    Point p = new Point((740) - (j * 50), 100 + (i * (26)));
                    Rectangle rec = new Rectangle(p, 50, 25);
                    Block b = new Block(rec, 1);
                    list.add(b);
                }
            }
        }
        return list;
    }

    /**
     * get the number of blocks in this stage that should be removed.
     *
     * @return the number of blocks
     */
    public int numberOfBlocksToRemove() {
        return 40;
    }
}


