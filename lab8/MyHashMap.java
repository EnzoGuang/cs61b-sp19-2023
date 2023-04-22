/** Implement HashMap
 * @author EnzoGuang
 * @date 2023/04/22
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialSize;
    private double loadFactor;
    private Node<K, V>[] bucket;
    private int size;

    private class Node<K, V> {
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public MyHashMap() {
        this.initialSize = 16;
        this.loadFactor = 0.75;
        this.bucket = new Node[this.initialSize];
        this.size = 0;
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        this.loadFactor = 0.75;
        this.bucket = new Node[this.initialSize];
        this.size = 0;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        this.bucket = new Node[this.initialSize];
        this.size = 0;
    }

    public int hash(K key) {
        return (key.hashCode() & 0x7ffffff) % this.initialSize;
    }


    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.bucket = new Node[this.initialSize];
        this.size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (size == 0) {
            return false;
        }
        int hash = hash(key);
        Node temp = bucket[hash];
        while (temp != null) {
            if (temp.key.equals(key)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (containsKey(key)) {
            int hash = hash(key);
            Node temp = bucket[hash];
            while (temp != null) {
                if (temp.key.equals(key)) {
                    return (V) temp.value;
                }
                temp = temp.next;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        int hash = hash(key);
        Node<K, V> temp = bucket[hash];
        if (containsKey(key)) {
            while (temp != null) {
                if (temp.key.equals(key)) {
                    temp.value = value;
                    return;
                }
                temp = temp.next;
            }
        }
        size++;
        bucket[hash] = new Node(key, value, bucket[hash]);
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        HashSet<K> keys = new HashSet<>();
        for (int i = 0; i < this.initialSize; i++) {
            Node<K, V> temp = bucket[i];
            while (temp != null) {
                keys.add(temp.key);
                temp = temp.next;
            }
        }
        return keys;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new IteratorHashMap<>();
    }

    private class IteratorHashMap<K> implements Iterator<K> {
        int currentSize;
        int i;
        Node temp;

        public IteratorHashMap() {
            currentSize = 0;
            i = 0;
            temp = bucket[i];
        }

        @Override
        public boolean hasNext() {
            return currentSize < size;
        }

        @Override
        public K next() {
            if (temp == null && hasNext()) {
                i++;
                temp = bucket[i];
            }
            K key = (K) temp.key;
            temp = temp.next;
            currentSize++;

            return key;
        }
    }
}
