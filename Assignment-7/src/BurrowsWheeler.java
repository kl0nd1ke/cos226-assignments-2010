/* BurrowsWheeler.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements Burrows-Wheeler encoding and decoding.
 * Dependencies: StringSuffix, Merge, ST, BinaryStdIn, BinaryStdOut, Arrays
 */

import java.util.Arrays;

public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and 
    // writing to standard output
    public static void encode() {   
        // Read the message from standard binary input
        String message = BinaryStdIn.readString();
        int N = message.length();
        // Concatenate to make circular indexing easier
        message += message;
        
        // Initialize suffix array
        StringSuffix[] suffixes = new StringSuffix[N];
        for (int i = 0; i < N; i++) {
            suffixes[i] = new StringSuffix(message, i);
        }
        // Sort the suffixes and find the index of the original message
        Merge.sort(suffixes);
        // "Undo" the concatenation of message with itself (when searching)
        int row = binarySearch(suffixes, message.substring(0, N));
        
        // Write to standard binary output
        BinaryStdOut.write(row);
        for (int i = 0; i < N; i++) {
            BinaryStdOut.write(suffixes[i].toString().charAt(N - 1));
        }
        
        BinaryStdOut.flush();
    }

    // apply Burrows-Wheeler decoding, reading from standard input and 
    // writing to standard output
    public static void decode() {
        // Array index of the original message
        int row = BinaryStdIn.readInt();
        // Read the BurrowsWheeler transform
        String lastString = BinaryStdIn.readString();
        // Array for last character of each suffix (the transform)
        char[] last = lastString.toCharArray();
        // Array for first character of each suffix (all characters sorted)
        char[] first = lastString.toCharArray();
        Arrays.sort(first);
        
        int N = last.length;
        int[] next = new int[N];
        ST<Character, Queue<Integer>> st = new ST<Character, Queue<Integer>>(); 

        // For each unique character, create a queue of the indices at which 
        // it appears in the transform array
        for (int i = 0; i < N; i++) {
            if (!st.contains(last[i])) st.put(last[i], new Queue<Integer>());
            st.get(last[i]).enqueue(i);
        }
        
        // Fill the next array by dequeuing the character indices enqueued 
        // in the previous step
        for (int i = 0; i < N; i++) {
            next[i] = st.get(first[i]).dequeue();
        }
        
        // Rebuild the message using next and the location of the original message
        for (int i = 0; i < N; i++) {
            row = next[row];
            BinaryStdOut.write(last[row]);
        }
        
        BinaryStdOut.flush();
    }
    
    // Does a binary search on the array and returns the index of the search item
    private static int binarySearch(StringSuffix[] ar, String s) {
        int lo = 0;
        int hi = ar.length - 1;
        int mid = (lo + hi) / 2;
        
        while (lo != hi) {
            // Search item found
            if (ar[mid].compare(s) == 0) return mid;
            else {
                // Search left half
                if (ar[mid].compare(s) > 0) hi = mid;
                // Search right half
                else lo = mid;
            }
            mid = (lo + hi) / 2;
        }
        
        // Search item not found
        return -1;
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new RuntimeException("Illegal command line argument");
    }
}
