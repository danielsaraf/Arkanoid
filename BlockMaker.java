package levels;

import geometric.Point;
import geometric.Rectangle;
import sprites.Block;
import sprites.Fill;

import java.awt.Color;
import java.util.List;

/**
 * The type Block maker.
 */
public class BlockMaker implements BlockCreator {
    private int blockPower;
    private int width;
    private int height;
    private List<Fill> fills;
    private Color stroke;

    /**
     * Instantiates a new Block maker.
     *
     * @param bp the blockPower
     * @param w  the width
     * @param h  the height
     * @param f  the fills
     * @param s  the stroke
     */
    public BlockMaker(int bp, int w, int h, List<Fill> f, Color s) {
        this.blockPower = bp;
        this.width = w;
        this.height = h;
        this.fills = f;
        this.stroke = s;
    }

    /**
     * Create block.
     *
     * @param xpos the x position
     * @param ypos the y position
     * @return the block
     */
    @Override
    public Block create(int xpos, int ypos) {
        Block b = new Block(new Rectangle(new Point(xpos, ypos), width, height), blockPower);
        for (Fill f : fills) {
            b.addFill(f);
        }
        if (stroke != null) {
            b.setStrokeColor(stroke);
        }
        return b;
    }
}
