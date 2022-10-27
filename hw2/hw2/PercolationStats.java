package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private int T;
  private double[] rec;

  public PercolationStats(int N, int T, PercolationFactory pf)  {
    if (N <= 0 || T <= 0) {
      throw new IllegalArgumentException("invalid N or T");
    } 
    rec = new double[N];
    this.T = T;
    for (int i = 0; i < T; i++) {
      Percolation p = pf.make(N);
      while (!p.percolates()) {
        int rrow = StdRandom.uniform(N);
        int rcol = StdRandom.uniform(N);
        p.open(rrow, rcol);
      }
      rec[i] = (double) p.numberOfOpenSites()/(N * N);
    }
  }   // perform T independent experiments on an N-by-N grid

  public double mean()  {
    return StdStats.mean(rec);
  }   // sample mean of percolation threshold

  public double stddev()  {
    return StdStats.stddev(rec);
  }   // sample standard deviation of percolation threshold

  public double confidenceLow()  {
    return mean() - 1.96 *stddev() / Math.sqrt(T);
  }   // low endpoint of 95% confidence interval

  public double confidenceHigh()  {
    return mean() + 1.96 * stddev() / Math.sqrt(T);
  }

}
