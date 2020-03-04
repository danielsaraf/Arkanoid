package levels;

import geometric.Point;
import geometric.Rectangle;

import sprites.FinalFourBackground;
import sprites.Velocity;
import sprites.Sprite;
import sprites.Block;
import java.util.LinkedList;
import java.util.List;

/**
 * The level Final four.
 */
public class FinalFour implements LevelInformation {

    /**
     * Instantiates a new Final four level.
     */
    public FinalFour() {
    }

    /**
     * get the number of balls in this level.
     *
     * @return the number
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * Initial and get the balls velocities.
     *
     * @return a list of velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new LinkedList<>();
        list.add(Velocity.fromAngleAndSpeed(45, 10));
        list.add(Velocity.fromAngleAndSpeed(0, 10));
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
        return "Final Four";
    }

    /**
     * Gets background.
     *
     * @return the background of this level
     */
    public Sprite getBackground() {
        return new FinalFourBackground();
    }

    /**
     * initialize and return the blocks list of this level.
     *
     * @return the blocks list
     */
    public List<Block> blocks() {
        List<Block> list = new LinkedList<>();
        int[] colorIndex = {1, 8, 2, 9, 10, 3, 4};
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                for (int j = 0; j < 15; j++) {
                    Point p = new Point((737) - (j * 51.8), 100 + (i * (26)));
                    Rectangle rec = new Rectangle(p, 51.8, 25);
                    Block b = new Block(rec, 2);
                    list.add(b);
                }
            } else {
                for (int j = 0; j < 15; j++) {
                    Point p = new Point((737) - (j * 51.8), 100 + (i * (26)));
                    Rectangle rec = new Rectangle(p, 51.8, 25);
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
        return 105;
    }
}
