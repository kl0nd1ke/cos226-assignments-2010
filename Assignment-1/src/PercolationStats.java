/* PercolationStats.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Statistics client for percolation
 * Dependencies: Percolation, Stopwatch, StdRandom, StdStats
 */

public class PercolationStats {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[0]);
        double[] proportions = new double[T];
        double[] times = new double[T];
        double totalTime = 0;

        // Run T experiments and store results
        for (int c = 0; c < T; c++) {
            Stopwatch sw = new Stopwatch();

            Percolation perc = new Percolation(N);

            // Run until system percolates
            while (!perc.percolates()) {
                // Coordinates start from (1,1)
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;

                if (!perc.isOpen(i, j)) perc.open(i, j);
            }

            proportions[c] = perc.proportionOpen();
            times[c] = sw.elapsedTime();
            totalTime += times[c];
        }

        // Stats for proportions
        double mean = StdStats.mean(proportions);
        double stdDev = StdStats.stddev(proportions);
        double halfConf = 1.96 * stdDev / Math.sqrt(T);
        double confLow = mean - halfConf;
        double confHigh = mean + halfConf;

        // Stats for time
        double meanTime = StdStats.mean(times);
        double stdDevTime = StdStats.stddev(times);

        // Print everything out
        System.out.println("mean percolation threshold  = " + mean);
        System.out.println("stddev                      = " + stdDev);
        System.out.println("95% confidence interval     = [" + confLow + ", "
                + confHigh + "]");
        System.out.println("total time                  = " + totalTime);
        System.out.println("mean time per experiment    = " + meanTime);
        System.out.println("stddev                      = " + stdDevTime);
    }
}
