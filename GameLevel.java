package game;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import collidables.Collidable;
import collidables.GameEnvironment;
import geometric.Point;
import geometric.Rectangle;
import sprites.LivesIndicator;
import sprites.ScoreIndicator;
import sprites.LevelNameIndicator;
import sprites.Block;
import sprites.Ball;
import sprites.Paddle;
import sprites.Velocity;
import sprites.Sprite;
import sprites.SpriteCollection;
import observer.BlockRemover;
import observer.ScoreTrackingListener;
import observer.HitListener;
import observer.BallRemover;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.Animation;
import animation.CountdownAnimation;
import animation.PauseScreen;
import levels.LevelInformation;

import java.util.List;
import java.awt.Color;

/**
 * The Class GameLevel.
 */
public class GameLevel implements Animation {

    /**
     * The screen width.
     */
    public static final int SW = 800;
    /**
     * The screen high.
     */
    public static final int SH = 600;
    /**
     * The borders high/width.
     */
    public static final int BH = 10;

    private GameEnvironment environment;
    private SpriteCollection sprites;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private LevelInformation level;
    private Counter blocksNumber;
    private Counter ballsNumber;
    private Counter livesNumber;
    private boolean running;
    private Counter score;
    private Paddle paddle;

    /**
     * Constructor of a GameLevel.
     *
     * @param lv the Level Information
     * @param aR the Animation Runner
     * @param k  the Keyboard Sensor
     * @param s  the Score Counter
     * @param l  the Lives Counter
     */
    public GameLevel(LevelInformation lv, AnimationRunner aR, KeyboardSensor k, Counter s, Counter l) {
        this.environment = new GameEnvironment();
        this.blocksNumber = new Counter(0);
        this.ballsNumber = new Counter(0);
        this.sprites = new SpriteCollection();
        this.livesNumber = l;
        this.keyboard = k;
        this.runner = aR;
        this.level = lv;
        this.score = s;
    }

    /**
     * Add collidable to the list.
     *
     * @param c the collidable object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * remove collidable from the list.
     *
     * @param c the collidable object
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Add sprite to the list.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove sprite from the list.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game with blocks, paddle, observers, indicators and more.
     */
    public void initialize() {
        // set the background
        LevelNameIndicator levelNameIndicator = new LevelNameIndicator(level.levelName());
        sprites.addSprite(levelNameIndicator);
        sprites.addSprite(level.getBackground());
        // create the paddle
        paddle = new Paddle(keyboard, level.paddleSpeed(), level.paddleWidth());
        paddle.addToGame(this);
        // add score and lives indicators
        ScoreIndicator scoreIndicate = new ScoreIndicator(score);
        sprites.addSprite(scoreIndicate);
        LivesIndicator livesIndicator = new LivesIndicator(livesNumber);
        sprites.addSprite(livesIndicator);
        HitListener blockRemover = new BlockRemover(this, blocksNumber);
        HitListener scoreTracking = new ScoreTrackingListener(score);
        // create the border blocks
        Block leftB = new Block(new Rectangle(new Point(0, 25), BH, SH), 0);
        Block rightB = new Block(new Rectangle(new Point(SW - BH, 25), BH, SH - 25), 0);
        Block upperB = new Block(new Rectangle(new Point(0, 25), SW, BH), 0);
        // death block sits below the screen and remove balls that touches it
        Block deathBlock = new Block(new Rectangle(new Point(-10, SH), SW + 20, BH), 0);
        leftB.addToGame(this);
        rightB.addToGame(this);
        upperB.addToGame(this);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(new BallRemover(this, this.ballsNumber));
        blocksNumber.increase(level.numberOfBlocksToRemove());
        for (Block b : level.blocks()) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTracking);
            b.addToGame(this);
        }
    }

    /**
     * Add the balls to the game.
     *
     * @param numOfBalls the number of balls it create
     * @param velocities the velocities
     */
    private void createBallsOnTopOfPaddle(int numOfBalls, List<Velocity> velocities) {
        paddle.getCollisionRectangle().setXPosition((double) (SW - level.paddleWidth()) / 2);
        for (int i = 0; i < numOfBalls; i++) {
            Velocity v = velocities.get(i);
            Point startP = new Point((double) (SW / 2), SH - Paddle.SFB - Paddle.PH);
            Ball ball = new Ball(startP, 5, Color.white, environment, v);
            ball.addToGame(this);
            ballsNumber.increase(1);
        }
    }

    /**
     * play one turn of the game, return when there is no more blocks or balls.
     */
    public void playOneTurn() {
        // create the balls
        this.createBallsOnTopOfPaddle(level.numberOfBalls(), level.initialBallVelocities());
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        // use our runner to run the current animation  which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * tells if the animation should stop.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Do one frame.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        // check if the player press on "p", in this case play the pause animation
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (blocksNumber.getValue() == 0) {
            // player win
            score.increase(100);
            this.running = false;
        }
        if (ballsNumber.getValue() == 0) {
            // player lose
            livesNumber.decrease(1);
            this.running = false;
        }
    }

    /**
     * tells if this level is done.
     *
     * @return the true if we should stop this stage, false otherwise
     */
    public boolean levelFinished() {
        return (blocksNumber.getValue() == 0 || livesNumber.getValue() == 0);

    }


}