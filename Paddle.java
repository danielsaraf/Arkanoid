package sprites;

import geometric.Point;
import geometric.Rectangle;
import biuoop.DrawSurface;
import collidables.Collidable;
import game.GameLevel;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle.
 *
 * @author Daniel Saraf.
 */
public class Paddle implements Sprite, Collidable {

    /**
     * The paddle high.
     */
    public static final int PH = 9;
    /**
     * The paddle space from bottom.
     */
    public static final int SFB = 30;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRec;
    private int paddleSpeed;
    private int paddleWidth;

    /**
     * Instantiates a new Paddle.
     *
     * @param key         a keyboard sensor
     * @param paddleSpeed the paddle speed
     * @param paddleWidth the paddle width
     */
    public Paddle(KeyboardSensor key, int paddleSpeed, int paddleWidth) {
        this.keyboard = key;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        //set the position of the paddle
        Point upperLeft = new Point((double) GameLevel.SW / 2, GameLevel.SH - SFB);
        // set the paddle rectangles size and position
        paddleRec = new Rectangle(upperLeft, paddleWidth, PH);
    }

    /**
     * move paddle to left side when left button pressed.
     */
    private void moveLeft() {
        // don`t move more left when the paddle hits the borders
        if (this.paddleRec.getUpperLeft().getX() > GameLevel.BH) {
            this.paddleRec.move(-paddleSpeed);
        }
    }

    /**
     * move paddle to left side when right button pressed.
     */
    private void moveRight() {
        //don`t move more right when the paddle hits the borders
        if (this.paddleRec.getUpperLeft().getX() < GameLevel.SW - GameLevel.BH - paddleWidth) {
            this.paddleRec.move(paddleSpeed);
        }
    }

    /**
     * Indicate the paddle that the time had passed.
     */
    public void timePassed() {
        // move the paddle to the chosen direction.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
                moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draw the paddle.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        // set the new upper left point and draw the frame (black) and the rectangles (white)
        Point p1 = new Point(this.paddleRec.getUpperLeft().getX(), this.paddleRec.getUpperLeft().getY());
        d.setColor(Color.black);
        d.drawRectangle((int) p1.getX(), (int) p1.getY(), (int) paddleRec.getWidth(), (int) paddleRec.getHeight());
        d.setColor(Color.orange);
        d.fillRectangle((int) p1.getX(), (int) p1.getY(), (int) paddleRec.getWidth(), (int) paddleRec.getHeight());
    }

    /**
     * return the paddle rectangle.
     *
     * @return the paddle rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRec;
    }

    /**
     * in case of collision - return new velocity depending on the collision point.
     *
     * if the ball hit region 1, it back with an angle of 300 degrees
     * if the ball hit region 2, it back with an angle of 330 degrees
     * If the ball hit region 3,  it keep its horizontal direction and only change its vertical one.
     * if the ball hit region 4, it back with an angle of 30 degrees
     * if the ball hit region 5, it back with an angle of 60 degrees
     *
     * @param hitter          the hitter ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double leftX = this.paddleRec.getUpperLeft().getX();
        double rightX = leftX + this.getCollisionRectangle().getWidth();
        double collisionX = collisionPoint.getX();
        double lineLenDiv = (rightX - leftX) / 5;
        // calculate the current speed of the ball to use save it for the new velocity by angel
        double speed = Math.sqrt(Math.pow(currentVelocity.getDX(), 2) + Math.pow(currentVelocity.getDY(), 2));
        // check which region the ball hits
        if ((collisionX >= leftX + (0 * lineLenDiv)) && (collisionX < leftX + (1 * lineLenDiv))) {
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if ((collisionX >= leftX + (1 * lineLenDiv)) && (collisionX < leftX + (2 * lineLenDiv))) {
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if ((collisionX >= leftX + (2 * lineLenDiv)) && (collisionX < leftX + (3 * lineLenDiv))) {
            return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
        } else if ((collisionX >= leftX + (3 * lineLenDiv)) && (collisionX < leftX + (4 * lineLenDiv))) {
            return Velocity.fromAngleAndSpeed(30, speed);
        } else {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param game the current game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}