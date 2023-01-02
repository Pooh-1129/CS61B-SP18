import java.util.*;
import edu.princeton.cs.algs4.MinPQ;

public class TSet {
    public class node {
        boolean exist;
        Map<Character, node> link;
        
        public node () {
            exist = false;
            link = new TreeMap<>();
        }
    }

    public node root = new node();
    
    private node nput(node n, String s, int d) {
        if (n == null) {
            n = new node();
        }
        if (d == s.length()) {
            n.exist = true;
            return n;
        }
        char c = s.charAt(d);
        n.link.put(c, nput(n.link.get(c), s, d+1));
        return n;
    }

    public void put (String s) {
        nput(root, s, 0);
    }
}
