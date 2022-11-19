import hw4.puzzle.WorldState;
import edu.princeton.cs.algs4.MinPQ;
import java.util.*;
import java.util.ArrayList;

public class Solver {
    private class SearchNode implements Comparable<SearchNode>  {
        private WorldState state;
        private int moves;
        private SearchNode pre;
        
        private WorldState state() {
            return state;
        }

        private int moves() {
            return moves;
        }

        private SearchNode pre() {
            return pre;
        }

        public int compareTo(SearchNode o) {
            return this.moves + this.state.estimatedDistanceToGoal() - o.moves - o.state.estimatedDistanceToGoal();
        } 
    }

    private int moveCnts;
    private List<WorldState> path;
    
    public Solver(WorldState initial) {
        MinPQ<SearchNode> q = new MinPQ<>();
        q.insert(new SearchNode(initial, 0, null));
        while (!q.isEmpty()) {
            SearchNode node = q.delMin;
            if (node.state.isgoal()) {
                moveCnts = node.moves;
                path = new ArrayList<>();
                SearchNode cur = node;
                while (cur != null) {
                    path.add(cur.state);
                    cur = cur.pre;
                }
                return;
            }
            else {
                for (WorldState neighbor: node.state.neighbors()) {
                    if (node.pre == null || !neighbor.equals(node.pre.state)) {
                        q.insert(new SearchNode(neighbor, node.moves + 1, node));
                    }
                }
            }
        }
    }

    public int moves()  {
        return moveCnts;
    }

    public Iterable<WorldState> solution()  {
        List<WorldState> ls = new ArrayList<>();
        for (int i = moveCnts; i >= 0; i--) {
            ls.add(path.get(i));
        }
        return ls;
    }   
}
