import java.util.*;

public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);
        BinaryTrie trie = (BinaryTrie) or.readObject();
        int num = (Integer) or.readObject();
        BitSequence bs = (BitSequence) or.readObject();
        char[] symbols = new char[num];
        for (int i = 0; i < num; i++) {
            Match m = trie.longestPrefixMatch(bs);
            symbols[i] = m.getSymbol();
            bs = bs.allButFirstNBits(m.getSequence().length());
        }   
        FileUtils.writeCharArray(args[1], symbols);
    }
}
