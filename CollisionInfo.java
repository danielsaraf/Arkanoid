package collidables;

import geometric.Point;

/**
 * The type Collision info.
 *
 * @author Daniel Saraf.
 */
public class CollisionInfo {

    private Point collisionP;
    private Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param cp the collision point
     * @param co the collision object
     */
    public CollisionInfo(Point cp, Collidable co) {
        collisionP = cp;
        collisionObject = co;
    }

    /**
     * get the collision point.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionP;
    }

    /**
     * get the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}