/* Board.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements an N-puzzle board
 * Dependencies: Stack
 */

public class Board {
    private int N;
    private int[][] board;
    // Indices of empty tile
    private int iEmpty;
    private int jEmpty;

    // construct a board from an N-by-N array of tiles
    public Board(int[][] tiles) {
        N = tiles.length;
        board = new int[N][N];

        // Initialize board
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    // Initialize indices of empty tile
                    iEmpty = i;
                    jEmpty = j;
                }
                board[i][j] = tiles[i][j];
            }
        }
    }

    // return number of blocks out of place
    public int hamming() {
        int hamming = 0;

        // Compare each tile with the corresponding reference tile
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                // Ignore the blank tile
                if (board[i][j] != 0 && board[i][j] != refVal(i, j))
                    hamming++;

        return hamming;
    }

    // return sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattan = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int currentTile = board[i][j];
                // Ignore the blank tile
                if (currentTile == 0) continue;
                
                // Find the reference indices
                int iRef = refRow(currentTile);
                int jRef = refCol(currentTile);

                // Increase the Manhattan distance
                manhattan += Math.abs(i - iRef) + Math.abs(j - jRef);
            }
        }

        return manhattan;
    }
    
    // Return the row index of value in the reference board
    private int refRow(int value) {
        if (value == 0) return N - 1;
        else return (value - 1) / N;
    }

    // Return the column index of value in the reference board
    private int refCol(int value) {
        if (value == 0) return N - 1;
        else return (value - 1) % N;
    }

    // Return the value in the reference board at indices i, j
    private int refVal(int i, int j) {
        if (i == N - 1 && j == N - 1) return 0;
        else return (i * N) + (j + 1);
    }
    
    // Determines whether the board is "solvable" (formula taken from
    // http://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html)
    public boolean isSolvable() {
        int inversions = inversions();
        int emptyRow = N - iEmpty;
        
        boolean a = N % 2 == 0;
        boolean b = inversions % 2 == 0;
        boolean c = emptyRow % 2 == 0;
        
        return (!a && b) || (a && b != c);
    }
    
    // Return the number of inversions when the tiles are placed linearly
    private int inversions() {
        int inversions = 0;
        
        // Traverse the board as if it is flattened into linear form
        for (int i = 0; i < N * N; i++) {
            for (int j = i + 1; j < N * N; j++) {
                int tileOne = board[i / N][i % N];
                int tileTwo = board[j / N][j % N];
                // Ignore the empty tile when considering inversions
                if (tileOne != 0 && tileTwo != 0 && tileOne > tileTwo)
                    inversions++;
            }
        }
        
        return inversions;
    }
    
    // Determines whether board is in the reference state
    public boolean isSolved() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (board[i][j] != refVal(i, j)) return false;
        return true;
    }

    // does this board position equal y
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;

        // Compare the two boards
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (this.board[i][j] != that.board[i][j])
                    return false;

        return true;
    }
    
    // return a string representation of the board
    public String toString() {
        int numChars = (int) Math.ceil(Math.log10(N * N));
        String number = "%" + numChars + "d ";
        String empty = "%" + numChars + "c ";
        
        String toString = "";
        for (int i = 0; i < N; i++) {
            toString += " ";
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    toString += String.format(number, board[i][j]);
                }
                else {
                    // Empty tile should be printed as a space, not a 0
                    toString += String.format(empty, ' ');
                }
            }
            toString += "\n";
        }
        
        return toString;
    }

    // return an Iterable of all neighboring board positions
    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<Board>();
        
        // The use of these three arrays is a hack to avoid using if statements
        int[] iIndices = { iEmpty - 1, iEmpty + 1, iEmpty, iEmpty };
        int[] jIndices = { jEmpty, jEmpty, jEmpty - 1, jEmpty + 1 };
        boolean[] conditions = { iEmpty != 0, iEmpty != N - 1, 
                jEmpty != 0, jEmpty != N - 1 };
        
        for (int c = 0; c < conditions.length; c++) {
            if (conditions[c]) {
                Board curNeighbor = new Board(board);
                
                // Swap the empty tile with a neighboring tile
                curNeighbor.board[iEmpty][jEmpty] = 
                    curNeighbor.board[iIndices[c]][jIndices[c]];
                curNeighbor.board[iIndices[c]][jIndices[c]] = 0;
                
                // Update the neighbor's indices for the empty tile
                curNeighbor.iEmpty = iIndices[c];
                curNeighbor.jEmpty = jIndices[c];
                
                neighbors.push(curNeighbor);
            }
        }
        
        return neighbors;
    }
}
