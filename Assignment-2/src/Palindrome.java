/* Palindrome.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Determines if an input sequence is a Watson-Crick
 * complemented palindrome
 * Dependencies: Deque, StdIn
 */

public class Palindrome {
    public static void main(String[] args) {
        Deque<Character> dq = new Deque<Character>();
        // Read and add each char
        while (!StdIn.isEmpty()) dq.addLast(StdIn.readChar());

        boolean isPalindrome = false;
        // Only inputs of an even number of characters can be palindromes
        if (dq.size() % 2 == 0) {
            while (dq.size() >= 2) {
                // If the first and last characters are complements, keep going
                if (complement(dq.removeFirst()) == dq.removeLast()) {
                    isPalindrome = true;
                } else {
                    isPalindrome = false;
                    break;
                }
            }
        }
        
        System.out.println(isPalindrome);
    }

    // Helper method to determine complements
    public static char complement(char c) {
        switch (c) {
        case 'A':
            return 'T';
        case 'C':
            return 'G';
        case 'G':
            return 'C';
        case 'T':
            return 'A';
        default:    // default case: return a space
            return ' ';
        }
    }
}
