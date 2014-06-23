/* Point.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a point in a plane.
 * Dependencies: none
 */

public class Point implements Comparable<Point> {
    private final double x;
    private final double y;
    
    // construct the point (x, y)
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // x-coordinate
    public double x() {
        return x;
    }

    // y-coordinate
    public double y() {
        return y;
    }

    // Euclidean distance between two points
    public double distanceTo(Point that) {
        return Math.sqrt(distance2To(that));
    }

    // square of Euclidean distance between two points
    public double distance2To(Point that) {
        double xDist = that.x - this.x;
        double yDist = that.y - this.y;
        return xDist * xDist + yDist * yDist;
    }

    // for use in a symbol table
    public int compareTo(Point that) {
        double yDiff = this.y - that.y;
        double xDiff = this.x - that.x;
        // Compare by y values
        if (yDiff != 0) {
            return (int) Math.signum(yDiff);
        }
        // Use x values as a tie-breaker
        else {
            return (int) Math.signum(xDiff);
        }
    }

    // does this point equal that?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        
        Point that = (Point) y;
        return this.x == that.x && this.y == that.y;
    }

    // draw to standard draw
    public void draw() {
        StdDraw.point(x, y);
    }

    // string representation
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
