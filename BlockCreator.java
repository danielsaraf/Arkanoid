package levels;

import sprites.Block;

/**
 * The interface Block creator.
 */
public interface BlockCreator {
    /**
     * Create block.
     *
     * @param xpos the x position
     * @param ypos the y position
     * @return the block
     */
// Create a block at the specified location.
    Block create(int xpos, int ypos);
}