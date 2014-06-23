/* Ancestor.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Data type containing the vertex of an ancestor and the shortest ancestral
 * distance between two of its children, which are not stored in this data type
 * Dependencies: none
 */

public class Ancestor implements Comparable<Ancestor> {
    private int v;
    private int distance;
    
    // Constructor
    public Ancestor(int v, int distance) {
        this.v = v;
        this.distance = distance;
    }

    // Compare the distances
    public int compareTo(Ancestor that) {
        return this.distance - that.distance;
    }

    // Get the vertex
    public int getV() {
        return v;
    }
    
    // Get the distance
    public int getDistance() {
        return distance;
    }
}
