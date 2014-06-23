/* Subset.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Prints out a random, unique subset of a sequence of
 * Strings of size k (determined through command-line argument)
 * Dependencies: RandomizedQueue, StdIn
 */

import java.util.Iterator;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        // Read and enqueue each string
        while (!StdIn.isEmpty()) rq.enqueue(StdIn.readString());
        
        Iterator<String> iter = rq.iterator();
        
        // If k is negative, print nothing; if k is greater than
        // rq.size(), print all the elements
        for (int i = 0; i < k && i < rq.size(); i++) {
            System.out.println(iter.next());
        }
    }
}
