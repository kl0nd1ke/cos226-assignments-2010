
/****************************************************************************
 *
 *  Compilation:  javac QuickFind.java
 *
 ****************************************************************************/

public class QuickFind {
    private int[] id;
    private int components;

    // instantiate N isolated components 0 through N-1
    public QuickFind(int N) {
        components = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // return number of connected components
    public int components() { return components; }

    // are elements p and q in the same component?
    public boolean find(int p, int q) {
        return id[p] == id[q];
    }

    // merge components containing p and q
    public void unite(int p, int q) {
        if (find(p, q)) return;
        int pid = id[p];
        for (int i = 0; i < id.length; i++)
            if (id[i] == pid) id[i] = id[q]; 
        components--;
    }
}
