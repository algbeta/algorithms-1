import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
  private final int num;
  private final int trials;
  private final int[] results;

  public PercolationStats(int n, int trials) {
    if (n < 1 || trials < 1) {
      throw new IllegalArgumentException();
    }
    this.num = n;
    this.trials = trials;
    this.results = new int[trials];

    for (int i = 0; i < trials; i++) {
      this.results[i] = this.runTrial(n);
    }
  }

  public double mean() {
    double sum = 0;
    double num2 = this.num * this.num;
    for (int i = 0; i < this.results.length; i++) {
      sum += (this.results[i] / num2);
    }

    return sum / this.trials;
  }

  public double stddev() {
    double mean = this.mean();
    double sumOfSquars = 0;
    double num2 = this.num * this.num;

    for (int element : this.results) {
      sumOfSquars += Math.pow(element / num2 - mean, 2);
    }

    return Math.sqrt(sumOfSquars / (this.trials - 1));
  }

  public double confidenceLo() {
    double mean = this.mean();
    double deviation = this.stddev();
    return mean - 1.96 * deviation / Math.sqrt(this.trials);
  }

  public double confidenceHi() {
    double mean = this.mean();
    double deviation = this.stddev();
    return mean + 1.96 * deviation / Math.sqrt(this.trials);
  }

  private int runTrial(int n) {
    Percolation myPercolation = new Percolation(n);
    while (!myPercolation.percolates()) {
      int row = StdRandom.uniform(1, n + 1);
      int col = StdRandom.uniform(1, n + 1);
      myPercolation.open(row, col);
    }

    return myPercolation.numberOfOpenSites();
  }

  public static void main(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException();
    }

    try {
      int nParsed = Integer.parseInt(args[0]);
      int trialsParsed = Integer.parseInt(args[1]);

      PercolationStats stats = new PercolationStats(nParsed, trialsParsed);
      System.out.println("mean                    = " + stats.mean());
      System.out.println("stddev                  = " + stats.stddev());
      System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");

    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException();
    }
  }
}
