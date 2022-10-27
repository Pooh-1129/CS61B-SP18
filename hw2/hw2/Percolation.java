package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation { 
  private boolean grid[][];
  private int N;
  private WeightedQuickUnionUF UF;
  private WeightedQuickUnionUF UFB;
  private int numOpen;

  public Percolation(int N) {
    if (N <= 0) {
      throw new IllegalArgumentException("N no less than 0");
    } 
    this.N = N;
    numOpen = 0;
    grid = new boolean [N][N];
    UF = new WeightedQuickUnionUF(N * N + 2);
    UFB = new WeightedQuickUnionUF(N * N + 2);
  }                
  // create N-by-N grid, with all sites initially blocked
  private int xyTO1D(int row, int col) {
    return row * N + col;
  }

  public void open(int row, int col) {
    if ( row < 0 || row >= N || col < 0 || col >= N) {
      throw new IndexOutOfBoundsException("out of range");
    }
    if (isOpen(row, col)) {
      return;
    }
    grid[row][col] = true;
    numOpen++;
    int[][] next = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
    for (int i = 0; i <= 3; i++) {
      int nrow = row + next[i][0];
      int ncol = col + next[i][1];
      if (ncol < 0 || ncol >= N)  continue;
      //first
      if (nrow == -1) {
        UF.union(xyTO1D(row, col), N * N);
        UFB.union(xyTO1D(row, col), N * N);

        continue;
      }
      //last
      if (nrow == N) {
        UF.union(xyTO1D(row, col), N * N + 1);
        continue;
      }
      if (isOpen(nrow, ncol) && !UF.connected(xyTO1D(nrow, ncol), xyTO1D(row, col))) {
        UF.union(xyTO1D(nrow, ncol), xyTO1D(row, col));
        UFB.union(xyTO1D(nrow, ncol), xyTO1D(row, col));
      }
    }
  } 
  // open the site (row, col) if it is not open already
  public boolean isOpen(int row, int col) {
    if (row < 0 || row >= N || col < 0 || col >= N) {
      throw new IndexOutOfBoundsException("out of range");
    }
    return grid[row][col];
  } 
  // is the site (row, col) open?
  public boolean isFull(int row, int col) {
    if (row < 0 || row >= N || col < 0 || col >= N) {
      throw new IndexOutOfBoundsException("out of range");
    }
    //able to connect one in the top row
    return UFB.connected(xyTO1D(row, col), N * N);
  } 
  // is the site (row, col) full?
  public int numberOfOpenSites() {
    return numOpen;
  }   
   // number of open sites
  public boolean percolates() {
    return UF.connected(N * N, N * N + 1);
  }           
  // does the system percolate?

  public static void main(String[] args) {

  }  
  // use for unit testing (not required)
}
