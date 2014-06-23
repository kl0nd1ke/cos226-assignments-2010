/* MoveToFront.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements move-to-front encoding and decoding.
 * Dependencies: BinaryStdIn, BinaryStdOut, 
 */

public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and 
    // writing to standard output
    public static void encode() {
        // Extended ASCII consists of 256 characters
        int size = 256;
        
        // Initialize the char array
        char[] ascii = new char[size];
        for (int i = 0; i < ascii.length; i++) {
            ascii[i] = (char) i;
        }
        
        // Read in the chars from standard binary input
        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            // Find the index at which the specified char is located
            int index;
            for (index = 0; index < ascii.length; index++) {
                if (ascii[index] == ch) break;
            }
            
            BinaryStdOut.write((char) index);
            
            // Shift all the characters to make room at the front
            while (index > 0) {
                ascii[index] = ascii[--index];
            }
            
            // Insert character in front
            ascii[index] = ch;
        }
        
        BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from standard input and 
    // writing to standard output
    public static void decode() {
        // Extended ASCII consists of 256 characters
        int size = 256;
        
        // Initialize the char array
        char[] ascii = new char[size];
        for (int i = 0; i < ascii.length; i++) {
            ascii[i] = (char) i;
        }
        // Read in the chars from standard binary input
        while (!BinaryStdIn.isEmpty()) {
            int index = BinaryStdIn.readChar();
            
            // Write the character
            BinaryStdOut.write(ascii[index]);
            
            char ch = ascii[index];
            // Shift all the characters to make room at the front
            while (index > 0) {
                ascii[index] = ascii[--index];
            }
            
            // Insert character in front
            ascii[index] = ch;
        }
        
        BinaryStdOut.flush();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new RuntimeException("Illegal command line argument");
    }
}
