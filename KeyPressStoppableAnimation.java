package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed;
    private KeyboardSensor keyboard;
    private Animation animation;
    private boolean stop;
    private String key;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param s the keyboard sensor
     * @param k the key that should be pressed to stop
     * @param a the animation we want to run
     */
    public KeyPressStoppableAnimation(KeyboardSensor s, String k, Animation a) {
        this.isAlreadyPressed = true;
        this.animation = a;
        this.keyboard = s;
        this.key = k;
    }

    /**
     * Do one frame of the animation.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        // if the key was pressed before, do not do anything.
        if (!this.keyboard.isPressed(key)) {
            isAlreadyPressed = false;
        }
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(key) && !isAlreadyPressed) {
            this.stop = true;
        }
    }

    /**
     * tells if the animation should stop.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
