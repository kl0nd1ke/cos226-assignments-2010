/* RectHV.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a horizontal-vertical rectangle.
 * Dependencies: Point, StdDraw
 */

public class RectHV {
    private final double xmin;
    private final double ymin;
    private final double xmax;
    private final double ymax;

    // construct the rectangle [xmin, ymin] x [xmax, ymax]
    // throw an exception if (xmin > xmax) or (ymin > ymax)
    public RectHV(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    // minimum x-coordinate of rectangle
    public double xmin() {
        return xmin;
    }

    // minimum y-coordinate of rectangle
    public double ymin() {
        return ymin;
    }

    // maximum x-coordinate of rectangle
    public double xmax() {
        return xmax;
    }

    // maximum y-coordinate of rectangle
    public double ymax() {
        return ymax;
    }

    // does this rectangle contain p?
    public boolean contains(Point p) {
        Point bL = new Point(xmin, ymin);
        Point tR = new Point(xmax, ymax);
        // Consider the boundary to be inside the rectangle
        return p.y() >= bL.y() && p.x() >= bL.x() && p.y() <= tR.y()
                && p.x() <= tR.x();
    }

    // do the two rectangles intersect?
    public boolean intersects(RectHV that) {
        // Corners of that
        Point bL = new Point(that.xmin, that.ymin);
        Point bR = new Point(that.xmax, that.ymin);
        Point tL = new Point(that.xmin, that.ymax);
        Point tR = new Point(that.xmax, that.ymax);

        double[] xL = { bL.x(), tL.x() };
        double[] xR = { bR.x(), tR.x() };
        double[] yT = { tL.y(), tR.y() };
        double[] yB = { bL.y(), bR.y() };
        return between(xL, xmin, xmax) || between(xR, xmin, xmax)
                || between(yT, ymin, ymax) || between(yB, ymin, ymax);
    }

    // Returns true if all values in x are between lo and hi, false otherwise
    private boolean between(double[] x, double lo, double hi) {
        boolean between = true;
        for (int i = 0; i < x.length; i++) {
            if (!(x[i] > lo && x[i] < hi)) between = false;
        }
        return between;
    }

    // Euclidean distance from p to closest point in rectangle
    public double distanceTo(Point p) {
        // Distance is 0 if p is in the rectangle
        if (contains(p)) return 0;
        // p is to the left or right of the rectangle and between ymin and ymax
        else if (p.x() >= xmin && p.x() <= xmax) {
            if (p.y() < ymin) return ymin - p.y();
            else return p.y() - ymax;
        }
        // p is below or above the rectangle and between xmin and xmax
        else if (p.y() >= ymin && p.y() <= ymax) {
            if (p.x() < xmin) return xmin - p.x();
            else return p.x() - xmax;
        }
        // p shares no coordinates with any points in the rectangle
        else {
            Point bL = new Point(xmin, ymin);
            Point bR = new Point(xmax, ymin);
            Point tL = new Point(xmin, ymax);
            Point tR = new Point(xmax, ymax);
            
            if (p.x() < xmin && p.y() < ymin) return p.distanceTo(bL);
            else if (p.x() > xmax && p.y() < ymin) return p.distanceTo(bR);
            else if (p.x() < xmin && p.y() > ymax) return p.distanceTo(tL);
            else return p.distanceTo(tR);
        }
    }

    // square of Euclidean distance from p to closest point in rectangle
    public double distance2To(Point p) {
        double dist = distanceTo(p);
        return dist * dist;
    }

    // does this rectangle equal that?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        RectHV that = (RectHV) y;
        return this.xmin == that.xmin && this.xmax == that.xmax
                && this.ymin == that.ymin && this.ymax == that.ymax;
    }

    // draw to standard draw
    public void draw() {
        double[] x = { xmin, xmax, xmax, xmin };
        double[] y = { ymin, ymin, ymax, ymax };
        StdDraw.polygon(x, y);
    }

    // string representation
    public String toString() {
        Point bL = new Point(xmin, ymin);
        Point bR = new Point(xmax, ymin);
        Point tL = new Point(xmin, ymax);
        Point tR = new Point(xmax, ymax);
        return bL.toString() + "; " + bR.toString() + "; " + tL.toString()
                + "; " + tR.toString();
    }
}
