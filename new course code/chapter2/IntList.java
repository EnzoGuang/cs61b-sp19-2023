package chapter2;

public class IntList {
    public int first;
    public IntList rest;

    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }

    /** Return the link list size using "recursion" */
    public int size() {
        if (this.rest == null) {
            return 1;
        } else {
            return 1 + rest.size();
        }
    }

    /** Return the link list size using "iterative" */
    public int iterativeSize() {
        IntList temp = this;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.rest;
        }
        return size;
    }

    /** Return the ith item using iterative of the IntList */
    public int getIterative(int n) {
        IntList temp = this;
        for (int i = 0; i < n; i++) {
            temp = temp.rest;
        }
        return temp.first;
    }

    /** Return the ith item using recursion of the IntList */
    public int getRecursion(int n) {
        if (n == 0) {
            return first;
        } else {
            return rest.getRecursion(n - 1);
        }
    }

    /** Returns an IntList identical to L, but with
     * each element incremented by x, L is not allowed
     * to change
     */
    public static IntList incrList(IntList L, int x) {
        IntList newIntList = new IntList(L.first + 2, null);
        IntList temp = newIntList;
        L = L.rest;
        while (L != null) {
            temp.rest = new IntList(L.first + x, null);
            temp = temp.rest;
            L = L.rest;
        }
        return newIntList;
    }

    /** Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed to use
     * the 'new' keyword
     */
    public static IntList dincrList(IntList L, int x) {
        IntList start = L;
        while (L != null) {
            L.first += x;
            L = L.rest;
        }
        return start;
    }

    public void printIntList() {
        IntList L = this;
        System.out.print("IntList: ");
        while (L != null) {
            System.out.print(L.first + " ");
            L = L.rest;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);
        System.out.println("L.size: " + L.size());
        System.out.println("L.iterariveSize: " + L.iterativeSize());
        L.printIntList();
        for (int i = 0; i < L.size(); i++) {
            System.out.println("L.getIterative(" + i + "): " + L.getIterative(i));
            //System.out.println("L.getRecursion(" + i + "): " + L.getRecursion(i));
        }
        IntList incre = IntList.incrList(L, 2);
        incre.printIntList();
        IntList dincre = IntList.dincrList(L, 3);
        dincre.printIntList();
    }
}
