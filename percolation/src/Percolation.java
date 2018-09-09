import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final int num;
  private int[] nodes;
  private int open;
  private final WeightedQuickUnionUF uf;

  /**
   * constructor.
   * initializes data
   */
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
    this.num = n;
    this.nodes = new int[n * n + 2];
    this.uf = new WeightedQuickUnionUF(n * n + 2);
    this.open = 0;

    this.nodes[0] = 0;
    this.nodes[n * n + 1] = 0;
    for (int i = 1; i <= n * n; i++) {
      this.nodes[i] = -1;
    }
  }

  private boolean checkIfInOfRange(int n) {
    return n > 0 && n <= this.num;
  }

  private int xyTo1D(int row, int col) {
    return (row - 1) * this.num + col;
  }

  private void connectNeighbours(int row, int col) {
    if (row == 1) {
      this.uf.union(0, this.xyTo1D(row, col));
    }

    if (row == this.num) {
      this.uf.union(this.num * this.num + 1, this.xyTo1D(row, col));
    }

    try {
      if (this.isOpen(row - 1, col)) {
        this.uf.union(this.xyTo1D(row, col), this.xyTo1D(row - 1, col));
      }
    } catch (IllegalArgumentException ex) {
      // System.out.println(ex);
    }

    try {
      if (this.isOpen(row, col - 1)) {
        this.uf.union(this.xyTo1D(row, col), this.xyTo1D(row, col - 1));
      }
    } catch (IllegalArgumentException ex) {
      // System.out.println(ex);
    }

    try {
      if (this.isOpen(row + 1, col)) {
        this.uf.union(this.xyTo1D(row, col), this.xyTo1D(row + 1, col));
      }
    } catch (IllegalArgumentException ex) {
      // System.out.println(ex);
    }

    try {
      if (this.isOpen(row, col + 1)) {
        this.uf.union(this.xyTo1D(row, col), this.xyTo1D(row, col + 1));
      }
    } catch (IllegalArgumentException ex) {
      // System.out.println(ex);
    }
  }

  /**
   * Opens slot by coordinates.
   */
  public void open(int row, int col) {
    if (!this.isOpen(row, col)) {
      this.nodes[this.xyTo1D(row, col)] = 0;
      this.connectNeighbours(row, col);
      this.open++;
    }
  }

  /**
   * Checks if slot is open.
   */
  public boolean isOpen(int row, int col) {
    if (!this.checkIfInOfRange(row) || !this.checkIfInOfRange(col)) {
      throw new IllegalArgumentException();
    }
    return this.nodes[this.xyTo1D(row, col)] != -1;
  }

  /**
   * Checks if slot is full.
   */
  public boolean isFull(int row, int col) {
    if (!this.checkIfInOfRange(row) || !this.checkIfInOfRange(col)) {
      throw new IllegalArgumentException();
    }
    return this.uf.connected(0, this.xyTo1D(row, col));
  }

  public int numberOfOpenSites() {
    return this.open;
  }

  public boolean percolates() {
    return this.uf.connected(0, this.num * this.num + 1);
  }

  public static void main(String[] args) {
    Percolation myPercolation = new Percolation(3);
    myPercolation.open(1, 1);
    System.out.println(myPercolation.percolates());
    myPercolation.open(2, 1);
    System.out.println(myPercolation.percolates());


    System.out.println(myPercolation.isOpen(2, 1));
    System.out.println(myPercolation.isFull(2, 1));

    System.out.println(myPercolation.isOpen(2, 3));
    System.out.println(myPercolation.isFull(2, 3));

    myPercolation.open(3, 3);
    System.out.println(myPercolation.isOpen(3, 3));
    System.out.println(myPercolation.isFull(3, 3));

    System.out.println(myPercolation.isOpen(1, 3));
    System.out.println(myPercolation.isFull(1, 3));
  }
}
