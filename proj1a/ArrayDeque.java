public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[])new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[])other.items;
        size = other.size();
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        T[] temp = (T[])new Object[other.items.length];
        int tempFirst = other.realFirst(other.nextFirst);
        for (int i = 0; i < other.size(); i++) {
            temp[tempFirst] = (T)other.items[tempFirst];
            tempFirst = firstBackward(tempFirst);
        }
        items = temp;
    }

    private void resizeArray() {
        int originalSize = size();
        T[] temp = (T[])new Object[items.length * 2];
        int tempNextFirst = nextFirst;
        for (int i = 0; i < size; i++) {
            int tempIndex = realFirst(tempNextFirst);
            temp[i] = items[tempIndex];
            tempNextFirst = realFirst(tempNextFirst);
        }
        items = temp;
        nextFirst = items.length - 1;
        nextLast = originalSize;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resizeArray();
        }
        items[nextFirst] = item;
        nextFirst = firstMoveForward(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resizeArray();
        }
        items[nextLast] = item;
        nextLast = lastMoveBackward(nextLast);
        size++;

    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    /** Return the real first's index of the items */
    private int realFirst(int temp) {
        return (temp + 1) % items.length;
    }

    /** Return the real last's index of the items */
    private int realLast(int temp) {
        return (temp - 1 + items.length) % items.length;
    }

    /** Return the position of the nextFirst ; addFirst's helper method*/
    private int firstMoveForward(int temp) {
        return (temp - 1 + items.length) % items.length;
    }

    /** Return the position of the nextLast ;addLast's helper method */
    private int lastMoveBackward(int temp) {
        return (temp + 1) % items.length;
    }

    /** A helper method of removeFirst */
    private int firstBackward(int temp) {
        return (temp + 1) % items.length;
    }

    /** A helper method of removeLast */
    private int lastBackward(int temp) {
        return (temp - 1 + items.length) % items.length;
    }

    public void printDeque() {
        int tempFirst = realFirst(nextFirst);
        int tempLast = realLast(nextLast);
        int tempSum = 0;
        while (/*tempFirst != tempLast && */tempSum < size) {
            System.out.print(items[tempFirst] + " ");
            tempFirst = (tempFirst + 1) % items.length;
            tempSum++;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            System.out.println("size is 0 ,is not available to removeFirst");
            return null;
        }
        size--;
        int temp = realFirst(nextFirst);
        T result = items[temp];
        items[temp] = null;
        nextFirst = firstBackward(nextFirst);
        return result;

    }

    public T removeLast() {
        if (size == 0) {
            System.out.println("size is 0, is not available to removeLast");
            return null;
        }
        size--;
        int temp = realLast(nextLast);
        T result = items[temp];
        items[temp] = null;
        nextLast = lastBackward(nextLast);
        return result;
    }

    public T get(int index) {
        int realFirst = realFirst(nextFirst);
        return items[(realFirst + index) % items.length];
    }
}
