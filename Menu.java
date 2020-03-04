package animation;

/**
 * The interface Menu.
 *
 * @param <T> the type parameter
 */
public interface Menu<T> extends Animation {

    /**
     * Add selection.
     *
     * @param key       the key to press
     * @param message   the message to present
     * @param returnVal the return value
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Gets status.
     *
     * @return the status
     */
    T getStatus();

    /**
     * Add sub menu.
     *
     * @param key     the key
     * @param message the message
     * @param subMenu the sub menu to add
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}