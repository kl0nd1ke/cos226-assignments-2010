/* Synset.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Data type containing a synset, its nouns, and a gloss
 * Dependencies: ST
 */

public class Synset {
    private String synset;
    private String[] nouns;
    private String gloss;
    
    // Constructor
    public Synset(String synset, String gloss) {
        this.synset = synset;
        this.nouns = synset.split(" ");
        this.gloss = gloss;
    }
    
    // Return an ST containing the nouns in the synset
    public ST<String, Integer> getNouns() {
        ST<String, Integer> nouns = new ST<String, Integer>();
        for (int i = 0; i < this.nouns.length; i++) {
            // Use nouns as keys, and values of 0
            nouns.put(this.nouns[i], 0);
        }
        return nouns;
    }
    
    // Return the gloss associated with the word
    public String getGloss() {
        return gloss;
    }
    
    // Return a string representation of the synset
    public String toString() {
        return synset;
    }
}
