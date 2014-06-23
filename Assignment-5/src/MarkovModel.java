/* MarkovModel.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a Markov model.
 * Dependencies: ST, StdOut
 */

public class MarkovModel {
    private int k;
    private int length;
    private int S;
    
    private String corpus;
    
    private ST<Character, Integer> alphabet;
    private ST<String, Integer> seqs;
    
    // build an order-k Markov model from corpus
    public MarkovModel(int k, String corpus) {
        this.k = k;
        this.corpus = corpus;
        length = corpus.length();
        
        alphabet = new ST<Character, Integer>();
        seqs = new ST<String, Integer>();
        S = buildAlphabet();
        
        buildModel();
    }
    // Find every distinct character in corpus and add it to alphabet
    private int buildAlphabet() {
        for (int i = 0; i < length; i++) {
            Character c = corpus.charAt(i);
            // Use a dummy value of 0 for each key
            alphabet.put(c, 0);
        }
        
        return alphabet.size();
    }
    // Find all the sequences of length k and k + 1 and the number of 
    // times each one occurs
    private void buildModel() {
        // Iterate through each index in the corpus
        for (int i = 0; i < length; i++) {
            // For each index i, find all sequences of sizes k and k + 1
            for (int j = k; j <= k + 1; j++) {
                // Current sequence
                String temp = substring(corpus, i + 1, j);
                int count = 1;
                // If the sequence exists already, get its count and add 1
                try { count += seqs.get(temp); }
                catch (Exception e) { }
                seqs.put(temp, count);
            }
        }
    }
    // Given an end index (exclusive) and a length, find a substring in the
    // string from end - length to end, using circular indices
    public static String substring(String s, int end, int length) {
        StringBuilder sb = new StringBuilder();
        int start = end - length;
        // If the start of the substring is at the end
        if (start < 0) {
            start += s.length();
            // Substring piece at the end
            sb.append(s.substring(start));
            // Substring piece at the beginning and up to the end index
            sb.append(s.substring(0, end));
        }
        else {
            sb.append(s.substring(start, end));
        }
        
        return sb.toString();
    }
    // return laplace-smoothed probability estimate of s
    public double laplace(String pc) {
        String p = pc.substring(0, pc.length() - 1);
        int npc = 0;
        int np = 0;
        // Handle null values in ST
        try { 
            npc += seqs.get(pc); 
            np += seqs.get(p);
        }
        catch (Exception e) { }
        // Compute the formula and autobox to double
        return (npc + 1.0) / (np + S);
    }

    // return a string representation of this model
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("alphabet size S = " + S + "\n");
        for (String s : seqs.keys()) {
            sb.append("\"" + s + "\" " + seqs.get(s) + "\n");
        }
        // Remove the last new line character
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    // Test client
    public static void main(String args[]) {
        MarkovModel model = new MarkovModel(2, "aabcabaacaac");
        StdOut.println(model);
        StdOut.printf("%.4f\n", model.laplace("aac"));
        StdOut.printf("%.4f\n", model.laplace("aaa"));
        StdOut.printf("%.4f\n", model.laplace("aab"));
    }
}
