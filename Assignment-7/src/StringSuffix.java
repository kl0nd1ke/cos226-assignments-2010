/* StringSuffix.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Helper class for suffixes (benefit: not storing the suffixes
 * in an array, so saving space)
 * Dependencies: none
 */

public class StringSuffix implements Comparable<StringSuffix> {
    private String s;
    private int index;

    // Constructor
    public StringSuffix(String s, int index) {
        this.s = s;
        this.index = index;
    }

    // Given an end index (exclusive) and a length, find a substring in the
    // string from end - length to end, using circular indices
    public String substring(int end, int length) {
        int start = end - length;
        // If the start of the substring is at the end
        if (start < 0) {
            start += length;
            end += length;
        }
        
        return s.substring(start, end);
    }

    // Compare to a StringSuffix
    public int compareTo(StringSuffix that) {
        return this.toString().compareTo(that.toString());
    }
    
    // Compare to a String
    public int compare(String that) {
        return this.toString().compareTo(that);
    }

    // Return the string suffix
    public String toString() {
        return substring(index, s.length() / 2);
    }

    // Return the index used to create the object
    public int index() {
        return index;
    }
    
    // Return the original string
    public String originalString() {
        return s;
    }
}
