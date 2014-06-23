/* State.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a state of the N-puzzle game
 * Dependencies: Board
 */

public class State implements Comparable<State> {
    private Board board;
    private int numMoves;
    private State parent;
    
    // Constructor
    public State(Board board, int numMoves, State parent) {
        this.board = board;
        this.numMoves = numMoves;
        this.parent = parent;
    }
    
    // Get the board
    public Board getBoard() {
        return board;
    }

    // Get the number of moves
    public int getNumMoves() {
        return numMoves;
    }

    // Get the parent state
    public State getParent() {
        return parent;
    }

    // Determine the Hamming priority
    public int hammingPriority() {
        return board.hamming() + numMoves;
    }
    
    // Determine the Manhattan priority
    public int manhattanPriority() {
        return board.manhattan() + numMoves;
    }
    
    // Compare the Manhattan priorities of the two states
    public int compareTo(State that) {
        return this.manhattanPriority() - that.manhattanPriority();
    }
}
