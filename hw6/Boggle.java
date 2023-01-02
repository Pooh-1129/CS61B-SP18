import java.util.*;
import java.io.File;

public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "words.txt";

    private static TSet t;
    private static Stack<TSet.node> stack;
    private static TSet.node p;
    private static List<Integer> passed;
    private static List<String> ret;

    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        // YOUR CODE HERE
        In i1 = new In(dictPath);
        t = new TSet();
        while (i1.hasNextLine()) {
            t.put(i1.readLine());
        }
        In i2 = new In(boardFilePath);
        String[] s = i2.readAllLines();
        int y = s.length;
        int x = s[0].length();
        char[][] b1 = new char[y][x];
        char[][] b2 = new char[x * y];
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                b1[j][i] = s[j].charAt(i);
                b2[x*j + i] = s[j].charAt(i);
            }
        } 
        List<Map<Integer, Character>> myMap  = new ArrayList<>();
        adjacencyMap(boardOneD,myMap,y,x);
        ret = new ArrayList<>();
        stack = new Stack<>();
        for(int i = 0; i < x * y; i++){
            passed = new ArrayList<>();
            char startChar = b2[i];
            p = t.root;
            stack.push(p);
            if(!p.link.containsKey(startChar)){
                continue;
            }
            passed.add(i);
            p = p.link.get(startChar);
            DFS(i, myMap, b2);
        }
        return sortList(ret, k);
    }
}
