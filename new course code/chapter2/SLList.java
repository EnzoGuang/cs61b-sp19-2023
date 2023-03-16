package chapter2;

/** An SLList is a list of integers, which hides the terrible truth
 * of the nakedness within
 */
public class SLList{

    private class IntNode {
        public IntNode prev;
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public SLList() {
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /** Adds x to the front of the list */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    /** Adds x to the end of the list */
    public void addLast(int x) {
        IntNode L = sentinel;
        while (L.next != null) {
            L = L.next;
        }
        L.next = new IntNode(x, null);
        size += 1;
    }


    /** Return the size of list */
    public int size() {
        return size;
    }

    /** Return the first item of the list */
    public int getFirst() {
        return sentinel.next.item;
    }

    public static void main(String[] args) {
        SLList L = new SLList(20);
        L.addFirst(15);
        L.addFirst(10);
        System.out.println("L.first: " + L.getFirst());
        L.addLast(30);
        System.out.println("L.size: " + L.size());
    }
}
