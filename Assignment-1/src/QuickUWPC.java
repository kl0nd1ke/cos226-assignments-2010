/****************************************************************************
 *
 *  Compilation:  java QuickUWPC.java
 *
 *  This code is adapted from "Algorithms in Java, Third Edition",
 *  by Robert Sedgewick, Addison Wesley Longman, 2003.
 *
 *  Program 1.4: weighted quick-union with path compression by halving.
 *
 ****************************************************************************/

public class QuickUWPC {
    private int[] id;
    private int[] sz;
    private int components;

    // instantiate N isolated components 0 through N-1
    public QuickUWPC(int N) {
        id = new int[N];
        sz = new int[N];
        components = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // return id of component corresponding to element x
    private int root(int x) {
        while (x != id[x]) {
            id[x] = id[id[x]];    // path compression by halving
            x = id[x];
        }
        return x;
    }

    // return number of connected components
    public int components() {
        return components;
    }

    // are elements p and q in the same component?
    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    // merge components containing p and q, making smaller root point to larger one
    public void unite(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if   (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else                 { id[j] = i; sz[i] += sz[j]; }
        components--;
    }
}
