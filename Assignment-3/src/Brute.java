/* Brute.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a brute force algorithm for finding
 * 4 or more collinear points.
 * Dependencies: Point, StdIn, StdOut, StdDraw
 */

public class Brute {
    public static void main(String args[]) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        int N = StdIn.readInt();
        Point[] pts = new Point[N];
        // Read in the points
        for (int i = 0; i < N; i++) {
            pts[i] = new Point(StdIn.readInt(), StdIn.readInt());
            pts[i].draw();
        }

        // 4 loops for 4-tuples
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < N; l++) {
                        if (Point.areCollinear(pts[i], pts[j], pts[k], pts[l])) {
                            pts[i].drawTo(pts[l]);
                            // Number of points is hardcoded to 4
                            StdOut.println("4: " + pts[i] + " -> " + pts[j]
                                    + " -> " + pts[k] + " -> " + pts[l]);
                        }
                    }
                }
            }
        }

        StdDraw.show(0);
    }
}
