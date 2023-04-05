
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestUnionFind {
    @Test
    public void testSizeOf() {
        UnionFind actual = new UnionFind(7);
        int[] expected = new int[7];
        Arrays.fill(expected, 1);
        for (int i = 0; i < actual.items.length; i++) {
            assertEquals(expected[i], actual.sizeOf(i));
        }
    }

    @Test
    public void testUnion() {
        UnionFind actual = new UnionFind(7);
        actual.union(0, 1);
        actual.union(1, 2);
        actual.union(0, 4);

        actual.union(5, 6);
        actual.union(0, 2);
    }

    @Test
    public void testIsConnected() {
        UnionFind actual = new UnionFind(7);
        actual.union(0, 1);
        actual.union(1, 2);
        actual.union(0, 4);

        actual.union(5, 6);

        assertTrue(actual.connected(0, 1));
        assertTrue(actual.connected(1, 2));
        assertTrue(actual.connected(0, 4));
        assertTrue(actual.connected(5, 6));

        assertFalse(actual.connected(0, 3));
        assertFalse(actual.connected(0, 5));

    }
}
