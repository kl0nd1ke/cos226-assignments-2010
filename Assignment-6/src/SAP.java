/* SAP.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Finds the shortest ancestral path between two vertices in a Digraph
 * Dependencies: Ancestor, Digraph, DirectedPathFinderBFS, ST, MinPQ, StdIn, StdOut
 */

public class SAP {
    private Digraph G;
    private int rootVertex;

    // constructor
    public SAP(Digraph G) {
        this.G = G;
        rootVertex = findRoot();
    }

    // return length of shortest ancestral path of v and w; -1 if no such path
    public int length(int v, int w) {
        if (rootVertex == -1) return -1;
        else return detailedAncestor(v, w).getDistance();
    }

    // return a common ancestor of v and w that participates in a shortest
    // ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return detailedAncestor(v, w).getV();
    }

    // Returns an Ancestor object containing both the ancestor vertex and
    // the shortest distance
    public Ancestor detailedAncestor(int v, int w) {
        if (rootVertex == -1) return new Ancestor(-1, -1);

        // BFS finders
        DirectedPathFinderBFS vBFS = new DirectedPathFinderBFS(G, v);
        DirectedPathFinderBFS wBFS = new DirectedPathFinderBFS(G, w);
        // If one of the vertices has no path to the root vertex, there is no path
        if (!vBFS.hasPathTo(rootVertex) || !wBFS.hasPathTo(rootVertex))
            return new Ancestor(-1, -1);
        
        // Shortest path to root vertex: <edge, distance>
        ST<Integer, Integer> vSP = new ST<Integer, Integer>();
        ST<Integer, Integer> wSP = new ST<Integer, Integer>();
        // PQ of all shared ancestors
        MinPQ<Ancestor> ancestors = new MinPQ<Ancestor>();

        // For v, add each edge on the way to the root vertex to vSP
        for (Integer edge : vBFS.pathTo(rootVertex)) {
            // Edges are represented as the vertex to which they point
            vSP.put(edge, vBFS.distanceTo(edge));
        }
        // For w, add each edge on the way to the root vertex to wSP
        for (Integer edge : wBFS.pathTo(rootVertex)) {
            // Edges are represented as the vertex to which they point
            wSP.put(edge, wBFS.distanceTo(edge));
            // Check for common ancestors (since we have filled vSP, 
            // we can use it)
            if (vSP.contains(edge)) {
                // Initialize a with the total distance (v to a + w to a)
                Ancestor a = new Ancestor(edge, vSP.get(edge) + wSP.get(edge));
                ancestors.insert(a);
            }
        }

        // Consider the case where w is a common ancestor
        if (vBFS.hasPathTo(w)) {
            Ancestor a = new Ancestor(w, vBFS.distanceTo(w));
            ancestors.insert(a);
        }
        // Consider the case where v is a common ancestor
        if (wBFS.hasPathTo(v)) {
            Ancestor a = new Ancestor(v, wBFS.distanceTo(v));
            ancestors.insert(a);
        }
        Ancestor closestAncestor;
        // The closest ancestor has the smallest distance value
        if (!ancestors.isEmpty()) closestAncestor = ancestors.delMin();
        // If there are no common ancestors, create a dummy Ancestor object
        else closestAncestor = new Ancestor(-1, -1);

        return closestAncestor;
    }

    // Returns the vertex that has no ancestors (no links leaving it)
    private int findRoot() {
        for (int i = 0; i < G.V(); i++) {
            if (!G.adj(i).iterator().hasNext()) {
                return i;
            }
        }
        // Return -1 if for some reason there is no root vertex
        return -1;
    }

    // Test client
    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        SAP sap = new SAP(G);
        while (true) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            Ancestor ancestor = sap.detailedAncestor(v, w);
            StdOut.println("sap = " + ancestor.getDistance() + ", ancestor = "
                    + ancestor.getV());
        }
    }
}
