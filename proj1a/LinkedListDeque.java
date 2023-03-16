public class LinkedListDeque<T> {
    private class StaffNode {
        StaffNode prev;
        T item;
        StaffNode next;

        public StaffNode(T item, StaffNode prev, StaffNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private StaffNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new StaffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** These line seems to be useless to Gradescope
    public LinkedListDeque(T item) {
        this();
        sentinel.next = new StaffNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }*/

    /**
     //Creates a deep copy of "other"
    public LinkedListDeque(LinkedListDeque other) {
        this();
        size = other.size();
        StaffNode otherTemp = other.sentinel.next;
        for (int i = 0; i < other.size(); i++) {
            addLast(otherTemp.item);
            otherTemp = otherTemp.next;
        }
    }
    */

    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new StaffNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            StaffNode temp = sentinel.next;
            sentinel.next = new StaffNode(item, sentinel, temp);
            temp.prev = sentinel.next;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == 0) {
            sentinel.next = new StaffNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            StaffNode temp = sentinel.prev;
            sentinel.prev = new StaffNode(item, temp, sentinel);
            temp.next = sentinel.prev;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StaffNode temp = sentinel.next;
        System.out.print("List: ");
        while (temp != sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        StaffNode front = sentinel.next;
        front.next.prev = sentinel;
        sentinel.next = front.next;
        size -= 1;
        return front.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        StaffNode back = sentinel.prev;
        back.prev.next = sentinel;
        sentinel.prev = back.prev;
        size -= 1;
        return back.item;

    }

    /** Gets the item at the given index ,using iterative ,if no
     *  such item exists return null
     */
    public T get(int index) {
        StaffNode temp = sentinel;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    /** Helper method of getRecursive */
    private T get(StaffNode temp, int index) {
        if (index == 0) {
            return temp.item;
        } else {
            return get(temp.next, index - 1);
        }
    }

    /** Gets the item at the given index, using recursive */
    public T getRecursive(int index) {
        return get(sentinel.next, index);
    }
}

