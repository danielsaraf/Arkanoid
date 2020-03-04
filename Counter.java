package game;

/**
 * The Class Counter.
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     *
     * @param number the first number to set.
     */
    public Counter(int number) {
        counter = number;
    }

    /**
     * add number to current count.
     *
     * @param number the number to increase
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number to decrease
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * get current count.
     *
     * @return the value
     */
    public int getValue() {
        return counter;
    }
}