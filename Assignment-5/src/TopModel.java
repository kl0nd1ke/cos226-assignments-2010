/* TopModel.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Given k, two training texts, and a number of test texts,
 * computes which of the training texts is a better k-th order Markov
 * model for each test text.
 * Dependencies: MarkovModel, KPiece, MaxPQ, StdOut
 */

public class TopModel {
    private static int k;
    private static MarkovModel candOne;
    private static MarkovModel candTwo;
    
    public static void main(String[] args) {
        k = Integer.parseInt(args[0]);
        String[] files = new String[args.length - 1];
        String[] texts = new String[files.length];
        // Read the filenames and read each file's contents
        for (int i = 0; i < files.length; i++) {
            files[i] = args[i + 1];
            texts[i] = new In(files[i]).readAll();
        }
        // Initialize the two candidates' Markov models
        candOne = new MarkovModel(k, texts[0]);
        candTwo = new MarkovModel(k, texts[1]);
        
        for (int i = 2; i < files.length; i++) {
            // Try-catch block catches exceptions occurring when testing
            // files whose text length is smaller than k
            try {
                evaluate(files[i], texts[i]);
            }
            catch (Exception e) {
                StdOut.println();
            }
        }
    }
    
    // Compute the log probabilities and print out the results
    public static void evaluate(String file, String text) {
        MaxPQ<KPiece> kPieces = new MaxPQ<KPiece>();
        double tProbOne = 0;
        double tProbTwo = 0;
        
        // Iterate through each index in the text
        for (int i = 0; i < text.length(); i++) {
            // Current sequence
            String temp = MarkovModel.substring(text, i + 1, k + 1);
            double probOne = Math.log(candOne.laplace(temp));
            double probTwo = Math.log(candTwo.laplace(temp));
            tProbOne += probOne;
            tProbTwo += probTwo;
            
            // Insert into MaxPQ
            KPiece kpTemp = new KPiece(temp, probOne, probTwo);
            kPieces.insert(kpTemp);
        }
        
        // Compute average probabilities
        double avProbOne = tProbOne / kPieces.size();
        double avProbTwo = tProbTwo / kPieces.size();
        double diff = avProbOne - avProbTwo;
        
        /* Print out the results */
        String sp = "  ";
        StdOut.println(file + sp + avProbOne + sp + avProbTwo + sp + diff);
        // Print out the 10 largest differences
        for (int i = 0; i < 10; i++) {
            StdOut.println(kPieces.delMax());
        }
        StdOut.println();
    }
}
