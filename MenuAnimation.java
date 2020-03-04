package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    /**
     * The type Selection.
     *
     * @param <K> the type parameter
     */
    public class Selection<K> {
        private String keyToPress;
        private String stringToShow;
        private K optionToReturn;

        /**
         * Instantiates a new Selection.
         *
         * @param key     the key
         * @param massage the massage
         * @param option  the option
         */
        public Selection(String key, String massage, K option) {
            this.keyToPress = key;
            this.stringToShow = massage;
            this.optionToReturn = option;
        }

        /**
         * Gets key to press.
         *
         * @return the key to press
         */
        public String getKeyToPress() {
            return keyToPress;
        }

        /**
         * Gets string to show.
         *
         * @return the string to show
         */
        public String getStringToShow() {
            return stringToShow;
        }

        /**
         * Gets option to return.
         *
         * @return the option to return
         */
        public K getOptionToReturn() {
            return optionToReturn;
        }
    }

    private String menuTitle;
    private List<Selection<T>> selectionsList;
    private List<Selection<Menu<T>>> subMenuList;
    private T result = null;
    private KeyboardSensor keyboard;
    private Sprite background = null;

    /**
     * Instantiates a new Menu animation.
     *
     * @param menuT    the menu t
     * @param keyboard the keyboard
     */
    public MenuAnimation(String menuT, KeyboardSensor keyboard) {
        this.menuTitle = menuT;
        this.keyboard = keyboard;
        this.selectionsList = new ArrayList<>();
        this.subMenuList = new ArrayList<>();
    }

    /**
     * Do one frame.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        if (background != null) {
            background.drawOn(d);
        }
        int y = 150;
        d.drawText(150, 100, menuTitle, 40);
        for (Selection<T> s : selectionsList) {
            d.drawText(150, y, s.getStringToShow(), 20);
            y = y + 50;
        }
    }

    /**
     * tells if the animation should stop.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        boolean doNothing = true;
        for (Selection<T> s : selectionsList) {
            if (keyboard.isPressed(s.getKeyToPress())) {
                result = s.getOptionToReturn();
                while (keyboard.isPressed("s")) {
                    doNothing = true;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Add selection.
     *
     * @param key       the key to press
     * @param message   the message to present
     * @param returnVal the return value
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        selectionsList.add(new Selection<T>(key, message, returnVal));
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public T getStatus() {
        return result;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        subMenuList.add(new Selection<Menu<T>>(key, message, subMenu));
    }

    /**
     * Gets sub menu.
     *
     * @param s the key that represent the menu
     * @return the sub menu
     */
    public Menu getSubMenu(String s) {
        for (Selection<Menu<T>> selection : subMenuList) {
            if (selection.keyToPress.equals(s)) {
                return (Menu) selection.optionToReturn;
            }
        }
        return null;
    }

    /**
     * Sets background.
     *
     * @param bg the background
     */
    public void setBackground(Sprite bg) {
        this.background = bg;
    }
}
