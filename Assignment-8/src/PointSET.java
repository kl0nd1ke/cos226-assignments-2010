/* PointSET.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a set of points in the plane using a SET.
 * Dependencies: Point, SET, Stack
 */

public class PointSET {
    private SET<Point> points;
    
    // construct an empty set of points
    public PointSET() {
        points = new SET<Point>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point p to the set
    public void insert(Point p) {
        points.add(p);
    }

    // does the set contain p?
    public boolean contains(Point p) {
        return points.contains(p);
    }

    // draw all of the points to standard draw
    public void draw() {
        for (Point p : points) {
            p.draw();
        }
    }

    // points in the set that are in the rectangle
    public Iterable<Point> range(RectHV rect) {
        Stack<Point> rectPoints = new Stack<Point>();
        for (Point p : points) {
            if (rect.contains(p)) rectPoints.push(p);
        }
        return rectPoints;
    }

    // nearest neighbor in the set to p (null if set is empty)
    public Point nearest(Point p) {
        if (isEmpty()) return null;
        else {
            // Arbitrary first point
            Point n = points.min();
            double nDist = p.distanceTo(n);
            for (Point o : points) {
                double oDist = p.distanceTo(o);
                // If o is closer to p than n, o is the new n
                if (oDist < nDist) {
                    n = o;
                    nDist = oDist;
                }
            }
            return n;
        }
    }
}
