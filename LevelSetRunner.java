package tasks;

import animation.AnimationRunner;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levels.LevelInformation;
import levels.LevelSpecificationReader;
import score.HighScoresTable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The type Level set runner.
 */
public class LevelSetRunner implements Task<Void> {

    private String definitions;
    private GUI gui;
    private KeyboardSensor keyboard;
    private HighScoresTable table;

    /**
     * Instantiates a new Level set runner.
     *
     * @param d the definitions
     * @param g the gui
     * @param k the keyboard
     * @param t the table
     */
    public LevelSetRunner(String d, GUI g, KeyboardSensor k, HighScoresTable t) {
        this.definitions = d;
        this.gui = g;
        this.keyboard = k;
        this.table = t;
    }


    @Override
    public Void run() {
        LevelSpecificationReader ls = new LevelSpecificationReader();
        List<LevelInformation> levelsList;
        BufferedReader buffer = null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(definitions);
            assert is != null;
            buffer = new BufferedReader(new InputStreamReader(is));
        } catch (Exception e) {
            System.out.println("error");
        }

        levelsList = ls.fromReader(buffer);
        DialogManager dialog = gui.getDialogManager();
        // create a GameFlow that will get a AnimationRunner and keyboardSensor
        GameFlow game = new GameFlow(new AnimationRunner(gui, 60), keyboard, table, dialog);
        // run the levels list
        game.runLevels(levelsList);
        return null;
    }
}
