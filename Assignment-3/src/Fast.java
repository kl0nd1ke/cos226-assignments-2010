/* Fast.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a fast algorithm for finding
 * 4 or more collinear points.
 * Dependencies: Point, StdIn, StdOut, StdDraw, Arrays
 */

import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        int N = StdIn.readInt();
        Point[] pts = new Point[N];

        // These arrays keep track of drawn endpoints of line segments
        Point[] startPoints = new Point[1];
        Point[] endPoints = new Point[1];
        int spCount = 0;
        int epCount = 0;

        // Read in the points
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new Point(StdIn.readInt(), StdIn.readInt());
            pts[i].draw();
        }

        for (int i = 0; i < pts.length; i++) {
            // Array that contains all points except pts[i]
            Point[] byAngle = new Point[N - 1];
            // Initialize byAngle
            for (int j = 0, k = 0; j < pts.length; j++, k++) {
                // Skip pts[i]
                if (i == j) {
                    k--;
                    continue;
                }
                byAngle[k] = pts[j];
            }
            // Sort byAngle by angle
            Arrays.sort(byAngle, 0, byAngle.length, pts[i].BY_POLAR_ORDER);

            // numEqual keeps track of how many equal angles are in a block
            int numEqual = 1;
            // Both startIndex and endIndex are INCLUSIVE
            int startIndex = 0;
            int endIndex = 0;

            // Analyze byAngle to find blocks of equal angles
            for (int j = 1; j < byAngle.length; j++) {
                boolean areEqual = pts[i].BY_POLAR_ORDER.compare(
                        byAngle[j - 1], byAngle[j]) == 0;
                if (areEqual) {
                    numEqual++;
                }
                // Not an else statement because there might be a block of 3 or
                // more at the end of the array
                if (!areEqual || j == byAngle.length - 1) {
                    endIndex = startIndex + (numEqual - 1);

                    if (numEqual >= 3) {
                        boolean shouldDraw = true;
                        Point startPoint;
                        Point endPoint;

                        Point[] collinear = new Point[numEqual + 1];
                        // Include pts[i] in collinear
                        collinear[0] = pts[i];
                        for (int k = startIndex, l = 1; k <= endIndex; k++, l++) {
                            collinear[l] = byAngle[k];
                        }
                        Arrays.sort(collinear, Point.BY_COORDS);

                        // Set start and end points
                        startPoint = collinear[0];
                        endPoint = collinear[collinear.length - 1];

                        // Don't draw if line segment has already been drawn
                        if (contains(startPoints, startPoint)
                                || contains(endPoints, endPoint)) {
                            shouldDraw = false;
                        }

                        if (shouldDraw) {
                            // Print points in line
                            StdOut.print((numEqual + 1) + ": " + startPoint);
                            for (int k = 1; k < collinear.length; k++) {
                                StdOut.print(" -> " + collinear[k]);
                            }

                            // Draw line
                            startPoint.drawTo(endPoint);
                            
                            // Add drawn start point to startPoints
                            startPoints[startPoints.length - 1] = startPoint;
                            spCount++;
                            increaseSize(startPoints, spCount);
                            
                            // Add drawn end point to endPoints
                            endPoints[endPoints.length - 1] = endPoint;
                            spCount++;
                            increaseSize(endPoints, epCount);

                            StdOut.println();
                        }
                    }
                    // Reset numEqual and startIndex for the next block
                    numEqual = 1;
                    startIndex = j;
                }
            }
        }
        StdDraw.show(0);
    }

    // Double array size (only if necessary)
    public static void increaseSize(Object[] a, int numItems) {
        if (numItems < a.length) return;
        
        Object[] newA = new Object[a.length * 2];
        for (int i = 0; i < a.length; i++) {
            newA[i] = a[i];
        }
        a = newA;
    }

    // Determine whether an array contains a Point using BS
    public static boolean contains(Point[] a, Point p) {
        int lo = 0;
        int mid = a.length / 2;
        int hi = a.length - 1;

        while (hi - lo > 1) {
            if (Point.BY_COORDS.compare(a[mid], p) < 0) {
                lo = mid;
                mid += (hi - mid) / 2;
            } else {
                hi = mid;
                mid -= (mid - lo) / 2;
            }
        }
        return a[mid] != null && a[mid].equals(p);
    }
}
