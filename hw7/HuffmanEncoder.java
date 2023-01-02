import java.util.*;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character,Integer> m = new HashMap<>();
        for (int i = 0; i < inputSymbols.length; i++) {
            char ch = inputSymbols[i];
            if (!m.containsKey(ch)) {
                m.put(ch, 0);
            }
            else {
                int cnt = m.get(ch) + 1;
                m.replace(ch, cnt);
            }
        }
        return m;
    }

    public static void main(String[] args) {
        char[] eightBS = FileUtils.readFile(args[0]);
        Map<Character, Integer> frequencyTable = buildFrequencyTable(eightBS);
        BinaryTrie bTrie = new BinaryTrie(frequencyTable);
        ObjectWriter ow = new ObjectWriter(args[0] + ".huf");
        ow.writeObject(bTrie);
        ow.writeObject((Integer)eightBS.length);
        Map<Character, BitSequence> lookupTable =  bTrie.buildLookupTable();
        List<BitSequence> bsList = new ArrayList<>();
        for(int i = 0; i < eightBS.length; i++){
            bsList.add(lookupTable.get(eightBS[i]));
        }
        BitSequence bs = BitSequence.assemble(bsList);
        ow.writeObject(bs);
    }
}
