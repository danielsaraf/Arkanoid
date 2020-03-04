package collidables;

import geometric.Point;
import geometric.Rectangle;
import sprites.Ball;
import sprites.Velocity;

/**
 * The interface Collidable.
 *
 * @author Daniel Saraf.
 */
public interface Collidable {
    /**
     * Gets collision rectangle shape.
     *
     * @return the collision rectangle shape
     */
    Rectangle getCollisionRectangle();

    /**
     * in case of collision - return new velocity depending on the current velocity and the collision point.
     *
     * @param hitter          the hitter ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}