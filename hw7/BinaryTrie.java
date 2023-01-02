import java.io.*;
import java.util.*;

public class BinaryTrie implements Serializable {

    private class Node implements Serializable {
        private char ch;
        private double freq;
        private Node left, right;
        public Node(char ch, double freq, Node left, Node right){
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }
    private Node root;

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        Comparator<Node> cmp = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.freq > o2.freq){
                    return 1;
                } else{
                    return -1;
                }
            }
        };
        PriorityQueue<Node> pq = new PriorityQueue<>(cmp);
        Iterator<Character> ci = frequencyTable.keySet().iterator();
        // initialize priority queue with singleton trees
        while(ci.hasNext()) {
            char c = ci.next();
            double freq = frequencyTable.get(c);
            pq.add(new Node(c, freq, null, null));
        }
        // merge two smallest trees
        while (pq.size() > 1) {
            Node left  = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.add(parent);
        }
        root = pq.poll();
    }

    private boolean isLeaf(Node n) {
        return n.left == null && n.right == null;
    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        Node p = root;
        StringBuilder bu = new StringBuilder();
        for (int i = 0; i < querySequence.length(); i++) {
            int k = querySequence.bitAt(i);
            if (k == 0) p = p.left;
            else p = p.right;
            bu.append(k);
            if (isLeaf(p)) break;
        }
        BitSequence bs = new BitSequence(bu.toString());
        return new Match(bs, p.ch);
    }

    private Map<Character, BitSequence> ret;
    private StringBuilder sb;
    
    public Map<Character, BitSequence> buildLookupTable() {
        ret = new HashMap<>();
        sb = new StringBuilder();
        traverse(root);
        return ret;        
    }

    private void traverse(Node n) {
        if (isLeaf(n)) {
            BitSequence bs = new BitSequence(sb.toString());
            ret.put(n.ch, bs);
            return;
        }
        sb.append(0);
        traverse(n.left);
        sb.deleteCharAt(sb.length()-1);
        sb.append(1);
        traverse(n.right);
        sb.deleteCharAt(sb.length()-1);
    }
            
}
