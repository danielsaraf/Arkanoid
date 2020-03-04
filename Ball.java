package sprites;

import geometric.Line;
import geometric.Point;
import biuoop.DrawSurface;
import game.GameLevel;
import collidables.GameEnvironment;
import collidables.CollisionInfo;

import java.awt.Color;


/**
 * The type Ball.
 *
 * @author Daniel Saraf.
 */
public class Ball implements Sprite {
    public static final int DEFAULT_SPEED = 5;
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private Line trajectory;

    /**
     * constructor of a new Ball.
     *
     * @param center the center of the new ball
     * @param r      the radius of the new ball
     * @param color  the color of the new ball
     * @param game   the game the new ball will join
     * @param v1     the velocity of the new ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game, Velocity v1) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = game;
        if (v1 != null) {
            this.v = v1;
        } else {
            this.v = new Velocity(DEFAULT_SPEED, DEFAULT_SPEED);
        }
        // set start trajectory
        this.trajectory = new Line(0, 0, 0, 0);
    }


    /**
     * Gets x coordinate.
     *
     * @return the x coordinate of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y coordinate.
     *
     * @return the y coordinate of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets radius.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Sets the color of the ball based on 3 primary colors.
     *
     * @param r the red value
     * @param g the green value
     * @param b the blue value
     */
    public void setColor(float r, float g, float b) {
        this.color = new Color(r, g, b);
    }

    /**
     * Draw the ball on the paint board.
     *
     * @param surface the paint board
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * notice the ball that the time had passed, so it will move.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Sets velocity.
     *
     * @param v1 the setting velocity
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }

    /**
     * Sets velocity.
     *
     * @param dx the number of moving points of the ball in the x coordinate per frame
     * @param dy the number of moving points of the ball in the y coordinate per frame
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the velocity of this ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Move the ball one step based on the velocity, take care of collisions.
     */
    public void moveOneStep() {
        /*
            check every move if the next move will make a collision between the ball and collidable object.
            if there is no collision, move one step based on the velocity, otherwise change the position
            of the ball to be close to the object and change the velocity in the right direction.
        */
        // get the trajectory from the center of the ball to the place it will be next step
        trajectory.setByBallTraj(this);
        // get CollisionInfo about the next collision
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        // check if there is a collision
        if (collisionInfo != null) {
            Point collisionPoint = collisionInfo.collisionPoint();
            // take care of the ball position for a "real looking" collision
            // set the center on the collision point and than move the ball by its radius depending on its direction
            // vertical line hit
            if (v.getDX() > 0) {
                center.setX(collisionPoint.getX() - radius);
            }
            if (v.getDX() < 0) {
                center.setX(collisionPoint.getX() + radius);
            }
            if (v.getDY() > 0) {
                center.setY(collisionPoint.getY() - radius);
            }
            if (v.getDY() < 0) {
                center.setY(collisionPoint.getY() + radius);
            }

            // get the appropriate new velocity for the ball
            v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), v);
            return;
        }
        // if the ball did`nt about to hit a collidable, change the ball position according to its velocity
        this.center = this.v.applyToPoint(this.center);
    }

    /**
     * Add the ball to the game.
     *
     * @param game the current game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Remove the ball from game.
     *
     * @param game the current game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
