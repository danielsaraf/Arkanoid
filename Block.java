package sprites;

import geometric.Line;
import geometric.Point;
import geometric.Rectangle;
import biuoop.DrawSurface;
import game.GameLevel;
import collidables.Collidable;
import observer.HitNotifier;
import observer.HitListener;


import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * The type Block.
 *
 * @author Daniel Saraf.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle blockRec;
    private int blockPower;
    private List<HitListener> hitListeners;
    private int hitPoints;
    private List<Fill> fillsList;
    private Color stroke = null;
    private int powerRemain;

    /**
     * Instantiates a new Block.
     *
     * @param rect     the rectangle shape if the block
     * @param blockPow the block power (how much hits its take to kill it)
     */
    public Block(Rectangle rect, int blockPow) {
        blockRec = rect;
        blockPower = blockPow;
        powerRemain = blockPower;
        fillsList = new ArrayList<>();
        for (int i = 0; i < blockPow; i++) {
            fillsList.add(new Fill(Color.white, 0));
        }
        // initialize the color array
        hitListeners = new ArrayList<>();
        hitPoints = 0;
    }

    /**
     * Gets block power.
     *
     * @return the block power
     */
    public int getPowerRemain() {
        return powerRemain;
    }

    /**
     * Gets the rectangle object of this block.
     *
     * @return the block rectangle
     */
    public Rectangle getCollisionRectangle() {
        return blockRec;
    }

    /**
     * in case of collision - return new velocity depending on the current velocity and the collision point.
     *
     * @param hitter          the hitter ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.hitPoints++;
        // decrease blockPower only if its value is more than 0
        if (blockPower > 0) {
            powerRemain--;
        }
        this.notifyHit(hitter);
        // generate the 4 line of the rectangle
        double x = blockRec.getUpperLeft().getX();
        double y = blockRec.getUpperLeft().getY();
        double width = blockRec.getWidth();
        double height = blockRec.getHeight();
        Line upperLine = new Line(x, y, x + width, y);
        Line rightLine = new Line(x + width, y, x + width, y + height);
        Line lowerLine = new Line(x, y + height, x + width, y + height);
        Line leftLine = new Line(x, y, x, y + height);
        // check where is the collision point and change the velocity according to it.
        // upper and lower lines - switch DY direction, right and left lines - switch DX direction,
        if ((upperLine.containsPoint(collisionPoint)) && (currentVelocity.getDY() > 0)) {
            return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
        } else if ((lowerLine.containsPoint(collisionPoint)) && (currentVelocity.getDY() < 0)) {
            return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
        } else if ((rightLine.containsPoint(collisionPoint)) && (currentVelocity.getDX() < 0)) {
            return new Velocity(-currentVelocity.getDX(), currentVelocity.getDY());
        } else {
            return new Velocity(-currentVelocity.getDX(), currentVelocity.getDY());
        }
    }

    /**
     * draw the block and its power on the surface.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        // set the position for the text to be at the middle of the block
        // set the upper left point of the block rectangle
        Point p1 = new Point(blockRec.getUpperLeft().getX(), blockRec.getUpperLeft().getY());
        // print the rectangle frame in black
        if (stroke != null) {
            d.setColor(stroke);
            d.drawRectangle((int) p1.getX() - 1, (int) p1.getY() - 1,
                    (int) blockRec.getWidth() + 1, (int) blockRec.getHeight() + 1);
        }
        if (blockPower == 0) {
            d.setColor(Color.black);
            d.fillRectangle((int) p1.getX(), (int) p1.getY(), (int) blockRec.getWidth(), (int) blockRec.getHeight());
            return;
        }
        if (fillsList.get(powerRemain - 1).isImage()) {
            d.drawImage((int) p1.getX(), (int) p1.getY(), fillsList.get(powerRemain - 1).getImageFill());
        } else {
            d.setColor(fillsList.get(powerRemain - 1).getColorFill());
            d.fillRectangle((int) p1.getX(), (int) p1.getY(), (int) blockRec.getWidth(), (int) blockRec.getHeight());
        }
    }

    /**
     * notify the block that the time had passed, do nothing for blocks.
     */
    public void timePassed() {
    }

    /**
     * Add the block to the game.
     *
     * @param game the current game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }


    /**
     * Remove this block from game.
     *
     * @param game the current game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the hit listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the hit listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notify all listeners that hit accrued.
     *
     * @param hitter the hitter ball
     */

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public String getHitPoints() {
        return Integer.toString(this.hitPoints);
    }

    /**
     * Add fill to the block.
     *
     * @param f the fill
     */
    public void addFill(Fill f) {
        // check the priority, in case if 1 change all the fills with priority 0 to it.
        if (f.getFillPriority() == 1) {
            for (int i = 0; i < blockPower; i++) {
                if (fillsList.get(i).getFillPriority() == 0) {
                    fillsList.set(i, f);
                }
            }
        }
        fillsList.set(f.getFillPriority() - 1, f);
    }


    /**
     * Sets stroke color.
     *
     * @param c the color
     */
    public void setStrokeColor(Color c) {
        this.stroke = c;
    }
}