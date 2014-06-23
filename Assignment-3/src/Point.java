/* Point.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a point and some helpful methods
 * Dependencies: StdDraw
 */

import java.util.Comparator;

public class Point {
    public static final Comparator<Point> BY_COORDS = new ByCoords();
    public final Comparator<Point> BY_POLAR_ORDER = new ByPolarOrder();
    
    private final int x;     // x coordinate
    private final int y;     // y coordinate
    // Needed for ByPolarOrder Comparator
    private final Point p;

    private class ByPolarOrder implements Comparator<Point>
    {
        public int compare(Point a, Point b) { 
            return (int) ((angle(p, a) - angle(p, b)) * 20000.0); 
        }
    }
    
    private static class ByCoords implements Comparator<Point>
    {
        public int compare(Point a, Point b) { 
            // Resolve the "tie" if y coords are the same
            if (a.y == b.y) {
                return a.x - b.x;
            }
            else {
                return a.y - b.y;
            }
        }
    }
    
    // constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.p = this;
    }
    
    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    // Determine if this is equal to that
    public boolean equals(Point that) {
        return this.x == that.x && this.y == that.y;
    }

    // plot this point using StdDraw
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line from this point to that point using standard draw
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    // Calculate the angle that b makes with a
    public static double angle(Point a, Point b) {
        // Take advantage of autoboxing
        return Math.atan((0.0 + b.y - a.y) / (0.0 + b.x - a.x));
    }
    
    // Tests whether or not 3 points are collinear
    public static boolean areCollinear(Point a, Point b, Point c) {
        Point[] points = {a, b, c};
        return areCollinear(points);
    }
    
    // Tests whether or not 4 points are collinear
    public static boolean areCollinear(Point a, Point b, Point c, Point d) {
        Point[] points = {a, b, c, d};
        return areCollinear(points);
    }
    
    // Takes an array of points and tests whether or not they are collinear
    public static boolean areCollinear(Point[] points) {
        // Trivial cases where there are 1 or 0 points
        if (points.length < 2) return true;
        
        // If there are any duplicate points, return false
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i] == points[j]) return false;
            }
        }
        
        // Calculate the angle between the first two points
        double angle = angle(points[0], points[1]);
        
        for (int i = 2; i < points.length; i++) {
            // If any angle does not equal angle, not collinear
            if (angle(points[0], points[i]) != angle) return false;
        }
        
        // If loops complete successfully, points are collinear
        return true;
    }
}
