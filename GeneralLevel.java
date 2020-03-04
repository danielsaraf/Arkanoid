package levels;

import sprites.Block;
import sprites.Sprite;
import sprites.Velocity;

import java.util.List;

/**
 * The type General level - can be set dynamically.
 */
public class GeneralLevel implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;


    /**
     * Instantiates a new General level.
     */
    public GeneralLevel() {
    }

    /**
     * Sets number of balls.
     *
     * @param numbOfBalls the number of balls
     */
    public void setNumberOfBalls(int numbOfBalls) {
        this.numberOfBalls = numbOfBalls;
    }

    /**
     * Sets velocities.
     *
     * @param vList the velocities
     */
    public void setVelocities(List<Velocity> vList) {
        this.velocities = vList;
    }

    /**
     * Sets paddle speed.
     *
     * @param ps the paddle speed
     */
    public void setPaddleSpeed(int ps) {
        this.paddleSpeed = ps;
    }

    /**
     * Sets paddle width.
     *
     * @param pw the paddle width
     */
    public void setPaddleWidth(int pw) {
        this.paddleWidth = pw;
    }

    /**
     * Sets level name.
     *
     * @param lvName the level name
     */
    public void setLevelName(String lvName) {
        this.levelName = lvName;
    }

    /**
     * Sets background.
     *
     * @param bg the background
     */
    public void setBackground(Sprite bg) {
        this.background = bg;
    }

    /**
     * Sets blocks.
     *
     * @param bList the blocks
     */
    public void setBlocks(List<Block> bList) {
        this.blocks = bList;
    }

    /**
     * Sets number of blocks to remove.
     *
     * @param blocksToRemove the number of blocks to remove
     */
    public void setNumberOfBlocksToRemove(int blocksToRemove) {
        this.numberOfBlocksToRemove = blocksToRemove;
    }

    /**
     * get the number of balls in this level.
     *
     * @return the number
     */
    public int numberOfBalls() {
        return numberOfBalls;
    }

    /**
     *  get the balls velocities.
     *
     * @return a list of velocities
     */
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }

    /**
     * get the paddle speed.
     *
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return paddleSpeed;
    }

    /**
     * get the paddle width.
     *
     * @return the paddle width
     */
    public int paddleWidth() {
        return paddleWidth;
    }

    /**
     * get the Level name string.
     *
     * @return the level name string
     */
    public String levelName() {
        return levelName;
    }

    /**
     * Gets background.
     *
     * @return the background of this level
     */
    public Sprite getBackground() {
        return background;
    }

    /**
     * initialize and return the blocks list of this level.
     *
     * @return the blocks list
     */
    public List<Block> blocks() {
        return blocks;
    }

    /**
     * get the number of blocks in this stage that should be removed.
     *
     * @return the number of blocks
     */
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }
}
