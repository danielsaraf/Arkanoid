package geometric;

/**
 * @author Daniel Saraf.
 */
public class Point {
    private double x;
    private double y;

    /**
     * This is the constructor of the class.
     *
     * @param x the x value of the point.
     * @param y the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other the point we want the measure its distance.
     * @return the distance.
     */
    public double distance(Point other) {
        double xSquare = ((this.x - other.getX()) * (this.x - other.getX()));
        double ySquare = ((this.y - other.getY()) * (this.y - other.getY()));
        return Math.sqrt(xSquare + ySquare);
    }

    /**
     * return true if the points are equal, false otherwise.
     *
     * @param other the point we want the compare.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * Return the x value of this point.
     *
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y value of this point.
     *
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets x.
     *
     * @param x1 the new X
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * Sets y.
     *
     * @param y1 the new y
     */
    public void setY(double y1) {
        this.y = y1;
    }
}