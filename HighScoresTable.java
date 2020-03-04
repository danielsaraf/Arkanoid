package score;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * The type High scores table.
 */
public class HighScoresTable implements Serializable {

    private List<ScoreInfo> scoresList;
    private int size;

    /**
     * Instantiates a new High scores table.
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scoresList = new ArrayList<>();
    }

    /**
     * Add score to the table.
     *
     * @param score the score
     */
    public void add(ScoreInfo score) {
        // check if the score is able to enter the list
        if (this.getRank(score.getScore()) > size) {
            return;
        }
        scoresList.add(score);
        if (scoresList.size() > size) {
            Collections.sort(scoresList);
            scoresList.remove(size);
        }
        try {
            this.save(new File("highscores"));
        } catch (Exception e) {
            System.out.println("Cant save file");
        }
    }

    /**
     * get the size of the table.
     *
     * @return the size of the table
     */
    public int size() {
        return size;
    }

    /**
     * Gets high scores table.
     *
     * @return the high scores
     */

    public List<ScoreInfo> getHighScores() {
        Collections.sort(scoresList);
        return scoresList;
    }

    /**
     * return the rank of the current score in the table.
     *
     * @param score the score
     * @return the rank
     */
    public int getRank(int score) {
        int rank = 1;
        for (ScoreInfo value : scoresList) {
            if (value.getScore() >= score) {
                rank++;
            }
        }
        return rank;
    }

    /**
     * Clears the table.
     */

    public void clear() {
        scoresList.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename the filename
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */

    public void load(File filename) throws IOException, ClassNotFoundException {
        clear();
        ScoreInfo s = null;
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        while ((s = (ScoreInfo) objectInputStream.readObject()) != null) {
            scoresList.add(s);
        }
        objectInputStream.close();
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
        for (ScoreInfo sc : scoresList) {
            objectOutputStream.writeObject(sc);
        }

        objectOutputStream.close();
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename the filename
     * @return the high scores table
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = new HighScoresTable(10);
        try {
            highScoresTable.load(filename);
            return highScoresTable;
        } catch (Exception e) {
            return highScoresTable;
        }
    }
}