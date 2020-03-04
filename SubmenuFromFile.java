package animation;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import score.HighScoresTable;
import sprites.MenuBackground;
import tasks.LevelSetRunner;
import tasks.Task;

import java.io.IOException;
import java.io.LineNumberReader;


/**
 * The type Submenu from file - in charge of convert levelSet file to a menu animation.
 */
public class SubmenuFromFile {
    private LineNumberReader reader;

    /**
     * read the file and create the menu.
     *
     * @param r        the LineNumberReader
     * @param keyboard the keyboard
     * @param gui      the gui
     * @param t        the table
     * @return the menu
     */
    public static Menu<Task<Void>> getMenu(LineNumberReader r, KeyboardSensor keyboard, GUI gui, HighScoresTable t) {
        MenuAnimation<Task<Void>> menu = new MenuAnimation<>("Choose level", keyboard);
        menu.setBackground(new MenuBackground());
        String line = null;
        try {
            line = r.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            String key = null;
            String message = null;
            assert line != null;
            key = line.substring(0, 1);
            message = line.substring(line.indexOf(":") + 1);
            // Creating the Task
            try {
                line = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            menu.addSelection(key, message, new LevelSetRunner(line, gui, keyboard, t));
            try {
                line = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (line != null);
        return menu;
    }
}
