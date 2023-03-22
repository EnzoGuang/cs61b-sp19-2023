public class Palindrome {
    /** Given a String, wordToDeque should return a Deque where
     * the characters appear in the same order as in the String.
     */
    public Deque<Character> wordToDeque(String word) {
        char[] temp = word.toCharArray();
        LinkedListDeque<Character> L = new LinkedListDeque<>();
        for (char x: temp) {
            L.addLast(x);
        }
        return L;
    }

    /** Return true if the word is Palindrome, if not return false.
     */
    public boolean isPalindrome(String word) {
        char[] test1 = word.toCharArray();
        for (int i = 0; i < test1.length / 2; i++) {
            if (test1[i] != test1[test1.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }
    /** Overloading isPalindrome, use cc.equalChars to compare each character */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        char[] test1 = word.toCharArray();
        for (int i = 0; i < test1.length / 2; i++) {
            boolean temp = cc.equalChars(test1[i], test1[test1.length - 1 - i]);
            if (!temp) {
                return false;
            }
        }
        return true;

    }
}
