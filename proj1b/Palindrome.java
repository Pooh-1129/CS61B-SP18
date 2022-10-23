//import OffByOne.CharacterComparator;

public class Palindrome {
  public Deque<Character> wordToDeque(String word) {
    Deque<Character> d = new ArrayDeque<Character>();
    for (int i = 0; i < word.length(); i++) {
      d.addLast(word.charAt(i));
    }
    return d;
  }

  public boolean isPalindrome(String word) {
    if (word == null || word.length() == 1) {
      return true;
    }
    int len = word.length();
    for (int i = 0; i < len / 2; i++) {
      if (word.charAt(i) != word.charAt(len - i - 1)) {
        return false;
      }
    }
    return true;
  }

  //@Override whynot?
  public boolean isPalindrome(String word, CharacterComparator cc) {
    if (word == null || word.length() == 1) {
      return true;
    }
    int len = word.length();
    for (int i = 0; i < len / 2; i++) {
      if (cc.equalChars(word.charAt(i), word.charAt(len - i - 1)) == false) {
        return false;
      }
    }
    return true;
  }

}
