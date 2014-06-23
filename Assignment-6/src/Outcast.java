/* Outcast.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Given an array of nouns, finds the noun that is least
 * related to the others
 * Dependencies: WordNet, MaxPQ, ST, In, StdOut
 */

public class Outcast {
    private WordNet wn;
    
    // constructor
    public Outcast(WordNet wordnet) {
        wn = wordnet;
    }

    // given an array of nouns, return the outcast
    public String outcast(String[] nouns) {
        MaxPQ<Integer> distances = new MaxPQ<Integer>();
        ST<Integer, String> nounST = new ST<Integer, String>();
        
        for (int i = 0; i < nouns.length; i++) {
            int sqDist = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (i == j) continue;
                int dist = wn.distance(nouns[i], nouns[j]);
                sqDist += dist * dist;
            }
            distances.insert(sqDist);
            nounST.put(sqDist, nouns[i]);
        }
        
        return nounST.get(distances.delMax());
    }

    // Test client
    public static void main(String[] args) {
        // Initialize the outcast
        Outcast oc = new Outcast(new WordNet(args[0], args[1]));
        // Iterate through each outcast text file
        for (int i = 2; i < args.length; i++) {
            // Read in the nouns in the file
            In fileReader = new In(args[i]);
            int numNouns = fileReader.readInt();
            String[] nouns = new String[numNouns];
            for (int j = 0; j < numNouns; j++) {
                nouns[j] = fileReader.readString();
            }
            
            // Find the outcast and print the result
            StdOut.println(args[i] + ": " + oc.outcast(nouns));
        }   
    }
}
