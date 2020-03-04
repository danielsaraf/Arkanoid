package geometric;

import java.util.List;
import sprites.Ball;

/**
 * The type Line.
 *
 * @author Daniel Saraf.
 */
public class Line {
    /**
     * The constant EPSILON.
     */
    private static final double EPSILON = 0.01;
    private Point start;
    private Point end;

    /**
     * This is the first constructor of the class.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * This is the second constructor of the class.
     *
     * @param x1 the x value of the start point
     * @param y1 the y value of the start point
     * @param x2 the x value of the end point
     * @param y2 the y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Return the length of the line from start to end.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Calculate the middle point of the line.
     *
     * @return middle point of the line.
     */
    public Point middle() {
        // Calculate the average of the two points
        double midX = ((this.start.getX() + this.end.getX()) / 2);
        double midY = ((this.start.getY() + this.end.getY()) / 2);
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Sets start.
     *
     * @param x the x
     * @param y the y
     */
    public void setStart(double x, double y) {
        this.start.setX(x);
        this.start.setY(y);
    }

    /**
     * Sets end.
     *
     * @param x the x
     * @param y the y
     */
    public void setEnd(double x, double y) {
        this.end.setX(x);
        this.end.setY(y);
    }

    /**
     * change the line by the ball velocity and radius(calculate its trajectory).
     *
     * @param ball the ball
     */
    public void setByBallTraj(Ball ball) {
        this.start.setX(ball.getX());
        this.start.setY(ball.getY());
        double endX = this.start.getX() + ball.getVelocity().getDX();
        double endY = this.start.getY() + ball.getVelocity().getDY();
        if (ball.getVelocity().getDX() > 0) {
            endX += ball.getSize();
        }
        if (ball.getVelocity().getDX() < 0) {
            endX -= ball.getSize();
        }
        if (ball.getVelocity().getDY() > 0) {
            endY += ball.getSize();
        }
        if (ball.getVelocity().getDY() < 0) {
            endY -= ball.getSize();
        }

        this.setEnd(endX, endY);
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return (intersectionWith(other) != null);
    }

    /**
     * Check if a point is on both lines.
     *
     * @param meetingPoint the meeting point of the lines
     * @param other        the other line
     * @return true if the point is on the line, false otherwise
     */
    private boolean checkPointContains(Point meetingPoint, Line other) {
        return (this.containsPoint(meetingPoint) && other.containsPoint(meetingPoint));
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other the other line
     * @return the Meeting point
     */
    public Point intersectionWith(Line other) {
        double thisXDiff = this.start.getX() - this.end.getX();
        double thisYDiff = this.start.getY() - this.end.getY();
        double thisM;
        double otherXDiff = other.start().getX() - other.end().getX();
        double otherYDiff = other.start().getY() - other.end().getY();
        double otherM;
        double xMeetingPoint;
        double yMeetingPoint;
        // If both of the line are vertical return false (parallel lines)
        if (otherXDiff == 0 && thisXDiff == 0) {
            return null;
        }
        if (thisXDiff != 0) {
            // find this line incline
            thisM = thisYDiff / thisXDiff;
        } else {
            // this line is vertical, the other is`nt, find the meeting point
            // find the other line incline
            otherM = otherYDiff / otherXDiff;
            // find the (x,y) coordinates of the meeting point
            xMeetingPoint = this.start.getX();
            yMeetingPoint = ((otherM * xMeetingPoint) - (otherM * other.start().getX()) + other.start().getY());
            // check if the meeting point is on both lines
            Point meetingPoint = new Point(xMeetingPoint, yMeetingPoint);
            if (checkPointContains(meetingPoint, other)) {
                return meetingPoint;
            } else {
                return null;
            }
        }
        if (otherXDiff != 0) {
            // find the other line incline
            otherM = otherYDiff / otherXDiff;
        } else {
            // other line is vertical, this line is`nt, find the meeting point
            // find the (x,y) coordinates of the meeting point
            xMeetingPoint = other.start().getX();
            yMeetingPoint = ((thisM * xMeetingPoint) - (thisM * this.start.getX()) + this.start.getY());
            // check if the meeting point is on both lines
            Point meetingPoint = new Point(xMeetingPoint, yMeetingPoint);
            if (checkPointContains(meetingPoint, other)) {
                return meetingPoint;
            } else {
                return null;
            }
        }
        // in this case the lines are parallel and cant intersect with each other
        if (thisM == otherM) {
            return null;
        }
        //non of the lines are vertical, calculate the (x,y) coordinate of the meeting point
        xMeetingPoint = ((thisM * this.start.getX()) - this.start.getY()
                - (otherM * other.start().getX()) + other.start().getY()) / (thisM - otherM);
        yMeetingPoint = ((thisM * xMeetingPoint) - (thisM * this.start.getX()) + this.start.getY());
        Point meetingPoint = new Point(xMeetingPoint, yMeetingPoint);
        if (checkPointContains(meetingPoint, other)) {
            return meetingPoint;
        } else {
            return null;
        }
    }

    /**
     * Return true is the lines are equal, false otherwise.
     *
     * @param other the other line
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start == other.start() && this.end == other.end())
                || (this.start == other.end() && this.end == other.start());
    }

    /**
     * check if the line contains a point, use Triangle inequality equation.
     *
     * @param other the Point
     * @return true if the point is in the line, false otherwise
     */
    public boolean containsPoint(Point other) {
        // use EPSILON to ignore the small deviation.
        return ((this.start.distance(other) + other.distance(this.end)) - (this.start.distance(this.end)) < EPSILON);
    }

    /**
     * return the closest intersection point (with the rectangle) to the start of the line.
     *
     * @param rect the rectangle
     * @return the closest intersect point to the start of the line, if there is no intersect return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // get a list with all the intersection points with the rectangle
        List<Point> list = rect.intersectionPoints(new Line(this.start, this.end));
        if (list.size() == 0) {
            return null;
        }
        // assume the closest point is the first point on the list
        Point closestPoint = (Point) list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (this.start.distance((Point) list.get(i)) < this.start.distance(closestPoint)) {
                closestPoint = (Point) list.get(i);
            }
        }
        return closestPoint;
    }
}
