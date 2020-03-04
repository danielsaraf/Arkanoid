package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The Class Animation runner.
 */
public class AnimationRunner {
    private int framesPerSecond;
    private Sleeper sleeper;
    private GUI gui;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui1 the graphical user interface
     * @param fps  the frame per second number
     */
    public AnimationRunner(GUI gui1, int fps) {
        this.sleeper = new Sleeper();
        this.framesPerSecond = fps;
        this.gui = gui1;
    }

    /**
     * Run the animation that given as a argument, depend on the fps, until it should stop.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}