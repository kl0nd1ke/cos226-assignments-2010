/* PercolationVisualizer.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Visualization client for percolation
 * Dependencies: Percolation, StdIn, StdDraw, Color
 */

import java.awt.Color;

public class PercolationVisualizer {

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Percolation perc = new Percolation(N);

        int wh = 600; // width/height (to be used for canvas)
        int cellWidth = wh / N;
        int sqWidth = (int) (wh / N * 0.9); // 90% of cellWidth

        StdDraw.setXscale(0, wh);
        StdDraw.setYscale(0, wh);

        while (!StdIn.isEmpty()) { // run the animation
            int x = StdIn.readInt();
            int y = StdIn.readInt();

            perc.open(x, y);

            StdDraw.filledSquare(wh / 2, wh / 2, wh / 2); // draw the black
                                                          // background

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (perc.isOpen(i, j))
                        StdDraw.setPenColor(Color.WHITE);
                    if (perc.isFull(i, j))
                        StdDraw.setPenColor(new Color(103, 198, 243));
                    // Draw a square, making the adjustments for the different
                    // coordinate system and for the i and j indices since pixels 
                    // start from (0,0)
                    StdDraw.filledSquare((0.5 + (j - 1)) * cellWidth, wh
                            - (0.5 + (i - 1)) * cellWidth, sqWidth / 2);
                    StdDraw.setPenColor(); // reset the pen color to black
                }
            }

            StdDraw.show(500); // small delay to allow drawing without tearing
        }
    }
}
