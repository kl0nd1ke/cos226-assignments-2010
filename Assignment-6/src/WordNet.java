/* WordNet.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a WordNet using a DAG
 * Dependencies: Synset, SAP, Ancestor, ST, Digraph, Queue, MinPQ, StdIn, StdOut
 */

public class WordNet {
    private ST<Integer, Synset> synsets;
    private Digraph DAG;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In synsetReader = new In(synsets);
        In hypernymReader = new In(hypernyms);

        // Initialize the synsets
        this.synsets = new ST<Integer, Synset>();
        while (!synsetReader.isEmpty()) {
            String[] synsetData = synsetReader.readLine().split(",");
            int id = Integer.parseInt(synsetData[0]);
            String synsetNouns = synsetData[1];
            String gloss = synsetData[2];
            // Add the synset to the ST
            this.synsets.put(id, new Synset(synsetNouns, gloss));
        }

        // Initialize the DAG
        DAG = new Digraph(this.synsets.size());
        while (!hypernymReader.isEmpty()) {
            String[] ids = hypernymReader.readLine().split(",");
            // The first id is the synset (and the rest are hypernyms)
            int synsetId = Integer.parseInt(ids[0]);
            for (int i = 1; i < ids.length; i++) {
                // Add an edge from the synset to each of its hypernyms,
                // adjusting the id to match the digraph range (0 to V-1)
                DAG.addEdge(synsetId - 1, Integer.parseInt(ids[i]) - 1);
            }
        }
        sap = new SAP(DAG);
    }

    // return all of the glosses associated with a given noun's synsets
    public Iterable<String> glosses(String noun) {
        Queue<String> glosses = new Queue<String>();
        for (Integer id : synsetIds(noun)) {
            glosses.enqueue(synsets.get(id).getGloss());
        }
        return glosses;
    }
    
    // Return all of the synset ids associated with a given noun
    private Iterable<Integer> synsetIds(String noun) {
        Queue<Integer> synsetIds = new Queue<Integer>();
        // Iterate through all the synsets
        for (Integer id : this.synsets.keys()) {
            Synset curSynset = this.synsets.get(id);
            // If a synset contains the noun, add its id to the queue
            if (curSynset.getNouns().contains(noun)) {
                synsetIds.enqueue(id);
            }
        }

        return synsetIds;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        // Iterate through all the synsets
        for (Integer id : synsets.keys()) {
            Synset curSynset = synsets.get(id);
            // Return true if the word is found in the nouns of a synset
            if (curSynset.getNouns().contains(word)) {
                return true;
            }
        }

        return false;
    }

    // return the distance between nounA and nounB (defined below); -1 if
    // infinite
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) return -1;
        
        MinPQ<Integer> distances = new MinPQ<Integer>();
        for (Integer idA : synsetIds(nounA)) {
            for (Integer idB : synsetIds(nounB)) {
                distances.insert(sap.length(idA - 1, idB - 1));
            }
        }
        
        return distances.delMin();
    }

    // return the synset (second field of synsets.txt) that is the common
    // ancestor of nounA and nounB in a shortest ancestral path; null if no such
    // path (defined below)
    public String sap(String nounA, String nounB) {
        Ancestor ancestor = ancestor(nounA, nounB);
        if (ancestor.getV() == -1) return null;
        return synsets.get(ancestor.getV() + 1).toString();
    }
    
    private Ancestor ancestor(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) return new Ancestor(-1, -1);
        
        MinPQ<Ancestor> ancestors = new MinPQ<Ancestor>();
        for (Integer idA : synsetIds(nounA)) {
            for (Integer idB : synsetIds(nounB)) {
                ancestors.insert(sap.detailedAncestor(idA - 1, idB - 1));
            }
        }
        if (ancestors.isEmpty()) return new Ancestor(-1, -1);
        else return ancestors.delMin();
    }
    
    // Test client
    public static void main(String[] args) {
        // Create a new WordNet from two text files
        WordNet wn = new WordNet(args[0], args[1]);
        String nounA = StdIn.readString();
        String nounB = StdIn.readString();
        StdOut.println(wn.sap(nounA, nounB));
        StdOut.println(wn.distance(nounA, nounB));
    }
}
