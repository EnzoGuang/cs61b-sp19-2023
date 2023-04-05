import java.util.Arrays;

import static java.lang.Math.abs;

public class UnionFind {
    public int[] items;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        items = new int[n];
        Arrays.fill(items, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        try {
            if (vertex >= items.length || vertex < 0) {
                throw new IllegalArgumentException(vertex + " is not a valid index.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int rootIndex = find(v1);
        return Math.abs(items[rootIndex]);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    private int parent(int v1) {
        validate(v1);
        int p = v1;
        while (items[p] >= 0) {
            p = items[p];
        }
        return p;
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int rootIndexOfV1 = find(v1);
        int rootIndexOfV2 = find(v2);
        if (rootIndexOfV1 == rootIndexOfV2) {
            return;
        }
        if (items[rootIndexOfV1] == items[rootIndexOfV2]) {
            items[rootIndexOfV2] -= sizeOf(v1);
            items[v1] = rootIndexOfV2;
        } else if (abs(items[rootIndexOfV1]) > abs(items[rootIndexOfV2])) {
            items[rootIndexOfV1] -= sizeOf(v2);
            items[v2] = rootIndexOfV1;
        } else {
            items[rootIndexOfV2] -= sizeOf(v1);
            items[v1] = rootIndexOfV2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if (items[vertex] < 0) {
            return vertex;
        }
        int rootIndex = parent(vertex);
        items[vertex] = rootIndex;
        return rootIndex;
    }
}
