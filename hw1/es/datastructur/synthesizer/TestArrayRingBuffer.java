package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    @Test
    public void testCapacity() {
        ArrayRingBuffer<Integer> a = new ArrayRingBuffer<>(4);
        int actualCapacity = a.capacity();
        int expectCapacity = 4;
        assertEquals(expectCapacity, actualCapacity);
    }

    @Test
    public void testFillCount() {
        ArrayRingBuffer<Integer> a = new ArrayRingBuffer<>(2);
        assertTrue(a.isEmpty());
        a.enqueue(10);
        int actual = a.fillCount();
        int expected = 1;
        assertEquals(actual, expected);
        a.enqueue(20);

        int actual2 = a.fillCount();
        int expected2 = 2;
        assertEquals(expected2, actual2);
        assertTrue(a.isFull());
    }

    @Test
    public void testDequeueAndPeek() {
        ArrayRingBuffer<Integer> a = new ArrayRingBuffer<>(4);
        a.enqueue(10);
        a.enqueue(20);
        a.enqueue(30);
        int actual = a.dequeue();
        int expected = 10;
        assertEquals(expected, actual);
        a.enqueue(40);
        int actual2 = a.dequeue();
        int expected2 = 20;
        assertEquals(actual2, expected2);

        int actual3 = a.peek();
        int expected3 = 30;
        assertEquals(expected3, actual3);

    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> a = new ArrayRingBuffer<>(4);
        a.enqueue(10);
        a.enqueue(20);
        for (Integer x : a) {
            System.out.println(x);
        }
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Integer> a = new ArrayRingBuffer<>(4);
        ArrayRingBuffer<Integer> b = new ArrayRingBuffer<>(4);
        a.enqueue(10);
        a.enqueue(20);

        b.enqueue(1);
        b.enqueue(2);
        b.enqueue(10);
        b.enqueue(20);
        b.dequeue();
        b.dequeue();
        assertTrue(a.equals(b));
    }
}
