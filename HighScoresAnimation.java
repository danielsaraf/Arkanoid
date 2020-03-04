package animation;

import score.HighScoresTable;
import score.ScoreInfo;
import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;


/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable table;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.table = scores;
    }

    /**
     * Do one frame.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        int y = 200;
        d.setColor(new Color(23, 136, 208));
        d.fillRectangle(0, 0, GameLevel.SW, GameLevel.SH);
        d.setColor(new Color(255, 255, 180));
        d.fillCircle(770, 20, 60);
        d.setColor(new Color(255, 255, 120));
        d.fillCircle(770, 20, 50);
        d.setColor(new Color(255, 204, 51));
        d.fillCircle(770, 20, 40);
        d.setColor(Color.black);
        d.drawText(250, 100, "High scores table:", 40);
        d.setColor(Color.black);
        d.drawLine(400, 150, 400, 500);
        for (ScoreInfo si : table.getHighScores()) {
            d.drawText(250, y, si.getName(), 25);
            d.drawText(500, y, Integer.toString(si.getScore()), 25);
            y = y + 30;
        }
        d.drawText(250, 550, "Press space to continue", 30);
    }

    /**
     * tells if the animation should stop.
     * this animation never stops by itself.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return false;
    }
}

