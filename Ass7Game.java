
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.Menu;
import animation.MenuAnimation;
import animation.SubmenuFromFile;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameLevel;
import score.HighScoresTable;
import sprites.MenuBackground;
import tasks.Task;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @author Daniel Saraf.
 */
// the main class
public class Ass7Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkaniod", GameLevel.SW, GameLevel.SH);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        HighScoresTable table = HighScoresTable.loadFromFile(new File("highscores"));
        HighScoresAnimation tableAnimation = new HighScoresAnimation(table);
        MenuAnimation<Task<Void>> menu = new MenuAnimation<>("Arkanoid", keyboard);
        menu.setBackground(new MenuBackground());

        LineNumberReader buffer = null;
        if (args.length > 0) {
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
                assert is != null;
                buffer = new LineNumberReader(new InputStreamReader(is));
            } catch (Exception e) {
                System.out.println("error");
            }
        } else {
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(
                        "levelset.txt");
                assert is != null;
                buffer = new LineNumberReader(new InputStreamReader(is));
            } catch (Exception e) {
                e.getMessage();
            }
        }
        menu.addSubMenu("s", "Start a new game", SubmenuFromFile.getMenu(buffer, keyboard, gui, table));
        menu.addSelection("s", "Start a new game", new Task<Void>() {
            @Override
            public Void run() {
                Menu menu2 = menu.getSubMenu("s");
                runner.run(menu2);
                Task result = (Task) menu2.getStatus();
                result.run();
                return null;
            }
        });
        menu.addSelection("h", "High scores table", new Task<Void>() {
            public Void run() {
                runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, tableAnimation));
                return null;
            }
        });

        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                gui.close();
                System.exit(0);
                return null;
            }
        });
        while (true) {
            runner.run(menu);
            Task<Void> result = menu.getStatus();
            result.run();
        }
    }
}

