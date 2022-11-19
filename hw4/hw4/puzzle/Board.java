package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;
import hw4.puzzle.WorldState;

public class Board implements WorldState{
    private int[][] tiles;
    private int N;
    private final int BLANK = 0;

    public Board(int[][] tiles) {
        N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0 ; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }

 /**
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.
     */
    // @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int dis = 0;
        for (int i =0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != BLANK && tiles[i][j] != i * N + j + 1) {
                    dis += 1;
                }
            }
        }
        return dis;
    }

    public int manhattan() {
        int dis = 0;
        for (int i =0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = tiles[i][j];
                if (value != BLANK) {
                    int row = (value - 1) / N;
                    int col = (value - 1) % N;
                    dis += Math.abs(i - row) + Math.abs(j - col);
                }
            }
        }
        return dis;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (y == this)  return true;
        if (y == null || this.getClass() != y.getClass()) return false;
        Board b = (Board) y;
        if (b.N != this.N) return false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tiles[i][j] != b.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
