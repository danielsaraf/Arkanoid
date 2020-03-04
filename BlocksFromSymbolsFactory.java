package levels;

import sprites.Block;

import java.util.Map;


/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, BlockCreator> blocksMap;
    private Map<String, String> spaceMap;

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param blocksMap the block creators map
     * @param spaceMap  the space map
     */
    public BlocksFromSymbolsFactory(Map<String, BlockCreator> blocksMap, Map<String, String> spaceMap) {
        this.blocksMap = blocksMap;
        this.spaceMap = spaceMap;
    }


    /**
     * check if the symbol represent a space.
     *
     * @param s the symbol
     * @return the true if it is
     */
// returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        return spaceMap.containsKey(s);
    }

    /**
     * check if the symbol represent a block.
     *
     * @param s the symbol
     * @return the true if it is
     */
// returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        return blocksMap.containsKey(s);
    }

    /**
     * Gets block.
     *
     * @param s    the symbol
     * @param xpos the x position
     * @param ypos the y position
     * @return the block according to the symbol and the position
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return blocksMap.get(s).create(xpos, ypos);
    }

    /**
     * Gets space width according to the symbol.
     *
     * @param s the symbol
     * @return the space width
     */
    public int getSpaceWidth(String s) {
        try {
            return Integer.parseInt(spaceMap.get(s));
        } catch (Exception e) {
            return 0;
        }

    }
}