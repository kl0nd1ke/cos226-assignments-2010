/*************************************************************************
 *  Compilation:  javac RangeSearchVisualizer.java
 *  Execution:    java RangeSearchVisualizer N
 *  Dependencies: StdDraw.java PointSET.java KdTree.java Point.java RectHV.java
 *
 *  Read points from standard input and draw to standard draw.
 *  Also draw all of the points in the rectangle the user selects
 *  by dragging the mouse.
 *
 *  The range search results using the brute-force algorithm are drawn
 *  in red; the results using the kd-tree algorithms are drawn in blue.
 *
 *************************************************************************/

public class RangeSearchVisualizer {

    // add points into kd-tree one at a time and draw
    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.show(0);

        // initialize the data structures with N random points
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point p = new Point(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        double x0 = 0.0, y0 = 0.0;      // initial endpoint of rectangle
        double x1 = 0.0, y1 = 0.0;      // current location of mouse
        boolean isDragging = false;     // is the user dragging a rectangle

        // draw the points
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        brute.draw();

        while (true) {
            StdDraw.show(40);

            // no rectangle selected
            if (!StdDraw.mousePressed()) {
                isDragging = false;
                continue;
            }

            // user starts to drag a rectangle
            else if (!isDragging) {
                x0 = StdDraw.mouseX();
                y0 = StdDraw.mouseY();
                isDragging = true;
            }

            x1 = StdDraw.mouseX();
            y1 = StdDraw.mouseY();

            RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
                                     Math.max(x0, x1), Math.max(y0, y1));
            // draw the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            brute.draw();

            // draw the rectangle
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius();
            rect.draw();

            // draw the range search results for brute-force data structure in red
            StdDraw.setPenRadius(.03);
            StdDraw.setPenColor(StdDraw.RED);
            for (Point p : brute.range(rect))
                p.draw();

            // draw the range search results for kd-tree in blue
            StdDraw.setPenRadius(.02);
            StdDraw.setPenColor(StdDraw.BLUE);
            for (Point p : kdtree.range(rect))
                p.draw();

            StdDraw.show(40);
        }
    }
}
