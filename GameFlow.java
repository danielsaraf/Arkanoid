package game;

import animation.KeyPressStoppableAnimation;
import animation.AnimationRunner;
import animation.GameOver;
import animation.YouWin;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import score.HighScoresTable;
import animation.HighScoresAnimation;
import biuoop.DialogManager;
import score.ScoreInfo;

import java.io.File;
import java.util.List;

/**
 * The Class Game flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter livesNumber;
    private Counter score;
    private HighScoresTable highScoresTable;
    private DialogManager dialog;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the Animation Runner
     * @param ks  the Keyboard Sensor
     * @param hst the HighScoresTable
     * @param d   the DialogManager
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable hst, DialogManager d) {
        this.livesNumber = new Counter(7);
        this.score = new Counter(0);
        this.dialog = d;
        this.highScoresTable = hst;
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * Run the levels list.
     *
     * @param levels the levels list
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            // create a new GameLevel
            GameLevel lv = new GameLevel(levelInfo, this.animationRunner,
                    this.keyboardSensor, this.score, this.livesNumber);

            lv.initialize();
            lv.playOneTurn();
            // play until the level should stop
            while (!lv.levelFinished()) {
                lv.playOneTurn();
            }

            if (livesNumber.getValue() == 0) {
                // player loses, play the "GAME OVER" screen
                this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                        KeyboardSensor.SPACE_KEY, new GameOver(score.getValue())));
                if (highScoresTable.getRank(score.getValue()) <= highScoresTable.size()) {
                    addScore();
                }
                this.animationRunner.run((new KeyPressStoppableAnimation(keyboardSensor,
                        KeyboardSensor.SPACE_KEY, new HighScoresAnimation(highScoresTable))));
                break;
            }

            if (levels.get(levels.size() - 1) == levelInfo) {
                this.animationRunner.run((new KeyPressStoppableAnimation(keyboardSensor,
                        KeyboardSensor.SPACE_KEY, new YouWin(score.getValue()))));
                // player wins, play the "YOU WIN" screen
                if (highScoresTable.getRank(score.getValue()) <= highScoresTable.size()) {
                    addScore();
                }
                this.animationRunner.run((new KeyPressStoppableAnimation(keyboardSensor,
                        KeyboardSensor.SPACE_KEY, new HighScoresAnimation(highScoresTable))));
            }
        }
    }

    /**
     * Add score to the table and save it.
     */
    private void addScore() {
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        highScoresTable.add(new ScoreInfo(name, score.getValue()));
        try {
            highScoresTable.save(new File("highscores"));
        } catch (Exception e) {
            System.out.println("Cant save file");
        }
    }
}