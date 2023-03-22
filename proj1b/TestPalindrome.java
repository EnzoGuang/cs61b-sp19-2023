import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);

        Deque d2 = palindrome.wordToDeque("HelloWorld");
        String actual2 = "";
        for (int i = 0; i < "HelloWorld".length(); i++) {
            actual2 += d2.removeFirst();
        }
        assertEquals("HelloWorld", actual2);
    }

    @Test
    public void testIsPalindrome() {
        String t1 = "racecar";
        assertTrue(palindrome.isPalindrome(t1));


        String t2 = "a";
        assertTrue(palindrome.isPalindrome(t2));

        String t3 = "horse";
        assertFalse(palindrome.isPalindrome(t3));

        String t4 = "cat";
        assertFalse(palindrome.isPalindrome(t4));

        String t5 = "";
        assertTrue(palindrome.isPalindrome(t5));
    }

    @Test
    public void testOverloadingIsPalindrome() {
        CharacterComparator c = new OffByOne();

        String t1 = "";
        assertTrue(palindrome.isPalindrome(t1, c));

        String t2 = "a";
        assertTrue(palindrome.isPalindrome(t2, c));

        String t3 = "racecar";
        assertFalse(palindrome.isPalindrome(t3, c));

        String t4 = "noon";
        assertFalse(palindrome.isPalindrome(t4, c));

        String t5 = "horse";
        assertFalse(palindrome.isPalindrome(t5, c));

        String t6 = "rancor";
        assertFalse(palindrome.isPalindrome(t6, c));

        String t7 = "aaaaab";
        assertFalse(palindrome.isPalindrome(t7, c));

        String t8 = "flake";
        assertTrue(palindrome.isPalindrome(t8, c));
    }
}