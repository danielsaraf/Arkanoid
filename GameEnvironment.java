package collidables;

import geometric.Line;
import geometric.Point;
import geometric.Rectangle;

import java.util.List;
import java.util.ArrayList;

/**
 * The type Game environment.
 *
 * @author Daniel Saraf.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Instantiates a new Game environment, initialize a new list of Collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Add collidable to the list.
     *
     * @param c the collidable
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Remove collidable from the collidables list.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Gets closest collision to the ball.
     *
     * @param trajectory the trajectory of the ball
     * @return the closest collision to the ball, return the collision point and object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        boolean collisionAppear = false;
        Rectangle rec;
        Collidable closestObj = null;
        Point closestPoint = trajectory.end();
        // Make a copy of the hitListeners before iterating over them.
        List<Collidable> collidablesCopy = new ArrayList<Collidable>(this.collidables);
        // scan the list
        for (Collidable c : collidablesCopy) {
            rec = c.getCollisionRectangle();
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(rec);
            if (collisionPoint != null) {
                // look for the closest point to the start
                if (trajectory.start().distance(collisionPoint) < trajectory.start().distance(closestPoint)) {
                    collisionAppear = true;
                    closestPoint = collisionPoint;
                    closestObj = c;
                }
            }
        }
        if (collisionAppear) {
            return new CollisionInfo(closestPoint, closestObj);
        } else {
            return null;
        }

    }
}