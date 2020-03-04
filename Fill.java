package sprites;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type Fill.
 */
public class Fill {
    private Color colorFill;
    private Image imageFill;
    private Boolean isImage;
    private int fillPriority;

    /**
     * Instantiates a new Fill with a color.
     *
     * @param c        the color
     * @param priority the priority
     */
    public Fill(Color c, int priority) {
        colorFill = c;
        isImage = false;
        fillPriority = priority;
    }


    /**
     * Instantiates a new Fill with a image.
     *
     * @param i        the image file
     * @param priority the priority
     */
    public Fill(String i, int priority) {

        ClassLoader c = ClassLoader.getSystemClassLoader();
        InputStream is = c.getResourceAsStream(i);
        try {
            if (is != null) {
                imageFill = ImageIO.read(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        isImage = true;
        fillPriority = priority;

    }

    /**
     * Gets fill priority.
     *
     * @return the fill priority
     */
    public int getFillPriority() {
        return fillPriority;
    }

    /**
     * Gets color fill.
     *
     * @return the color fill
     */
    public Color getColorFill() {
        return colorFill;
    }

    /**
     * Gets image fill.
     *
     * @return the image fill
     */
    public Image getImageFill() {
        return imageFill;
    }

    /**
     * check if the fill is a image or a color.
     *
     * @return the boolean
     */
    public Boolean isImage() {
        return isImage;
    }
}
