package geometric;

import java.util.List;
import java.util.ArrayList;

/**
 * The type Rectangle.
 *
 * @author Daniel Saraf.
 */
public class Rectangle {

    private Point upperLeftPoint;
    private double width;
    private double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param newUpperLeft the new upper left
     * @param newWidth     the new width
     * @param newHeight    the new height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point newUpperLeft, double newWidth, double newHeight) {
        upperLeftPoint = newUpperLeft;
        width = newWidth;
        height = newHeight;
    }

    /**
     * Gets the width.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets the height.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets the upper left point.
     *
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeftPoint;
    }

    /**
     * Gets the line of the rectangle that intersect with a point.
     *
     * @param other the point
     * @return the line that intersect with the point or null if there is no such line
     */
    public Line getLineByPoint(Point other) {
        double x = upperLeftPoint.getX();
        double y = upperLeftPoint.getY();
        Line[] lineArr = new Line[4];
        // generate all the lines
        lineArr[0] = new Line(x, y, x + this.width, y);
        lineArr[1] = new Line(x + this.width, y, x + this.width, y + this.height);
        lineArr[2] = new Line(x, y + this.height, x + this.width, y + this.height);
        lineArr[3] = new Line(x, y, x, y + this.height);
        // check if some of them intersect with the point
        for (int i = 0; i < 4; i++) {
            if (lineArr[i].containsPoint(other)) {
                return lineArr[i];
            }
        }
        return null;
    }

    /**
     * return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line
     * @return a (possibly empty) List of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // generate the list
        List<Point> pointsList = new ArrayList<Point>();
        double x = upperLeftPoint.getX();
        double y = upperLeftPoint.getY();
        Line[] lineArr = new Line[4];
        // generate the lines
        lineArr[0] = new Line(x, y, x + this.width, y);
        lineArr[1] = new Line(x + this.width, y, x + this.width, y + this.height);
        lineArr[2] = new Line(x, y + this.height, x + this.width, y + this.height);
        lineArr[3] = new Line(x, y, x, y + this.height);
        // look for intersect points with all the lines
        for (int i = 0; i < 4; i++) {
            if (lineArr[i].isIntersecting(line)) {
                pointsList.add(lineArr[i].intersectionWith(line));
            }
        }
        return pointsList;
    }

    /**
     * Sets a new x coordinate position.
     *
     * @param newX the new x position
     */
    public void setXPosition(double newX) {
        this.upperLeftPoint.setX(newX);
    }

    /**
     * Move the rectangle position.
     *
     * @param dx the amount of point to move each time in the X coordinate
     */
    public void move(double dx) {
        this.setXPosition(this.getUpperLeft().getX() + dx);
    }

    /**
     * Gets clone.
     *
     * @return the clone
     */
    public Rectangle getClone() {
        Point p = new Point(upperLeftPoint.getX(), upperLeftPoint.getY());
        return new Rectangle(p, this.width, this.height);
    }
}