/* KPiece.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Data structure to store a string, two probabilities,
 * and the difference between the probabilities (for use with PQ)
 * Dependencies: none
 */

public class KPiece implements Comparable<KPiece> {
    private String piece;
    private Double probOne;
    private Double probTwo;
    private Double diff;
    
    // Constructor
    public KPiece(String piece, Double probOne, Double probTwo) {
        // Format the string according to assignment guidelines
        this.piece =  "\"" + piece.replaceAll("\\s", " ") + "\"";
        this.probOne = probOne;
        this.probTwo = probTwo;
        diff = probOne - probTwo;
    }

    // Convert to string
    public String toString() {
        String sp = "  ";
        return piece + sp + probOne + sp + probTwo + sp + diff;
    }

    // Compare one KPiece to another
    public int compareTo(KPiece that) {
        // * 100000 needed because when cast to int, precision is lost
        return (int) ((Math.abs(this.diff) - Math.abs(that.diff)) * 100000);
    }
}
