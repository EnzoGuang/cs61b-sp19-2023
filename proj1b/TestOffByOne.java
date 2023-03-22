import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars() {
        boolean actual1 = offByOne.equalChars('a', 'b');
        assertTrue(actual1);

        boolean actual2 = offByOne.equalChars('r', 'q');
        assertTrue(actual2);

        boolean actual3 = offByOne.equalChars('a', 'e');
        assertFalse(actual3);

        boolean actual4 = offByOne.equalChars('z', 'a');
        assertFalse(actual4);

        boolean actual5 = offByOne.equalChars('a', 'a');
        assertFalse(actual5);

        boolean actual6 = offByOne.equalChars('&', '%');
        assertTrue(actual6);
    }
}
