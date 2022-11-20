package lab11.graphs;
import edu.princeton.cs.algs4.Queue;
/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int s;
    private int t;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> q = new Queue<>();
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int cur = q.dequeue();
            if (cur == t) {
                break;
            }
            for (int suc : maze.adj(cur)) {
                if (!marked[suc]) {
                    edgeTo[suc] = cur;
                    marked[suc] = true;
                    q.enqueue(suc);
                    announce();
                    distTo[suc] = distTo[cur] + 1;
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

