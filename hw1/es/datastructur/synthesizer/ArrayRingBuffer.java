package es.datastructur.synthesizer;
import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    /* total capacity of the queue. */
    private final int capacity;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /** return size of buffer */
    @Override
    public int capacity() {
        return capacity;
    }

    /** return number of items currently in the buffer. */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer is overflow");
        }
        fillCount++;
        rb[last] = x;
        last = (last + 1) % capacity;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        fillCount--;
        T temp = rb[first];
        first = (first + 1) % capacity;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        int originalFirst = first;
        int currentSize = 0;

        @Override
        public boolean hasNext() {
            return currentSize < fillCount();
        }

        @Override
        public T next() {
            int temp = originalFirst;
            originalFirst = (originalFirst + 1) % capacity;
            currentSize++;
            return rb[temp];
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> temp = (ArrayRingBuffer<T>) o;
        int equalSize = this.fillCount;
        int actualSize = 0;
        for (T x: temp) {
            for (T y: this) {
                if (x.equals(y)) {
                    actualSize++;
                    break;
                }
            }
        }
        return actualSize == equalSize;
    }
}
