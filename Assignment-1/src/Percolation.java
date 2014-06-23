/* Percolation.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Simulates percolation in an N-by-N grid using the
 * Quick Union With Path Compression algorithm
 * Dependencies: StdIn, StdDraw, QuickUWPC
 */

public class Percolation {
    // rc is short for rows/columns (equivalent)
    private int rc;
    // N represents the total number of elements (rc * rc + 2)
    private int N;

    private int[] grid;
    private QuickUWPC gridQU;

    // create N-by-N grid, will all sites blocked
    public Percolation(int N) {
        rc = N;
        // Include virtual top and bottom elements
        this.N = rc * rc + 2;
        
        // Initialize grid to all zeros (0 means blocked site)
        grid = new int[this.N];
        gridQU = new QuickUWPC(this.N);

        // Mark virtual sites as open
        grid[0] = 1;
        grid[this.N - 1] = 1;
    }

    // open site (row i, col j) if it is not already
    public void open(int i, int j) {
        open(convertTo1D(i, j));
    }

    private void open(int x) {
        // Do nothing if site is already open
        if (grid[x] == 1) return;
        
        // Mark site as open
        grid[x] = 1;

        // Get x's neighbors
        int[] neighbors = getNeighbors(x);

        // Unite x to all open neighbors
        for (int i = 0; i < neighbors.length; i++) {
            int neighbor = neighbors[i];
            // Ignore non-existent neighbors (set to -1 by getNeighbors)
            if (neighbor != -1 && grid[neighbor] == 1) {
                gridQU.unite(x, neighbor);
            }
        }
    }

    private int[] getNeighbors(int x) {
        // Since we don't take diagonals into account
        // (and we're not dealing with the virtual sites)
        int maxNeighbors = 4;
        int[] neighbors;
        neighbors = new int[maxNeighbors];
        
        // Top neighbor
        neighbors[0] = x - rc;
        // Bottom neighbor
        neighbors[1] = x + rc;
        // Left neighbor
        neighbors[2] = x - 1;
        // Right neighbor
        neighbors[3] = x + 1;

        // If in top row, top neighbor is virtual site
        if (x - rc <= 0) neighbors[0] = 0;
        // If in bottom row, bottom neighbor is virtual site
        if (x + rc >= N - 1) neighbors[1] = N - 1;
        // If in first column, there is no left neighbor (denoted as -1)
        if (x % rc == 1) neighbors[2] = -1;
        // If in last column, there is no right neighbor (denoted as -1)
        if (x % rc == 0) neighbors[3] = -1;

        return neighbors;
    }

    // Convert two-dimensional coordinates to one-dimensional index
    private int convertTo1D(int i, int j) {
        return (i - 1) * rc + j;
    }

    // is site (row i, col j) open?
    public boolean isOpen(int i, int j) {
        return grid[convertTo1D(i, j)] == 1;
    }

    // is site (row i, col j) full?
    public boolean isFull(int i, int j) {
        return gridQU.find(0, convertTo1D(i, j));
    }

    // does the system percolate?
    public boolean percolates() {
        return gridQU.find(0, N - 1);
    }
    
    // return the proportion of open sites
    public double proportionOpen() {
        int open = 0;
        int closed = N;
        double proportion;
        
        // Find the number of open sites
        for (int i = 0; i < N; i++) {
            if (grid[i] == 1) {
                open++;
            }
        }
        
        // closed + open always equals N
        closed -= open;
        proportion = (double) open / N;
        
        return proportion;
    }
}
