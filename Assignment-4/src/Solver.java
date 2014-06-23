/* Solver.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Solves an N-puzzle game using A* search
 * Dependencies: Board, State, Stack, MinPQ
 */

public class Solver {
    private MinPQ<State> pq;
    private State initial;
    private State solution;
    private int numStates;

    // find a solution to the initial board
    public Solver(Board initial) {
        this.initial = new State(initial, 0, null);
        pq = new MinPQ<State>();
        numStates = 0;

        // Insert the initial state
        pq.insert(this.initial);
        numStates++;
        
        boolean isSolvable = isSolvable();
        while (isSolvable) {
            State curState = pq.delMin();

            // Stop the loop if the solution is found
            if (curState.getBoard().isSolved()) {
                solution = curState;
                break;
            }
            for (Board b : curState.getBoard().neighbors()) {
                State childState = new State(b, curState.getNumMoves() + 1,
                        curState);
                // Don't push states board positions equivalent to that of
                // the current state's parent
                if (curState.getParent() == null
                        || !b.equals(curState.getParent().getBoard())) {
                    pq.insert(childState);
                    numStates++;
                }
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return initial.getBoard().isSolvable();
    }

    // return min number of moves to solve initial board; -1 if no solution
    public int moves() {
        if (isSolvable()) return solution.getNumMoves();
        else return -1;
    }

    // return an Iterable of board positions in solution
    public Iterable<Board> solution() {
        Stack<Board> boards = new Stack<Board>();

        State curState = solution;
        while (curState != null) {
            boards.push(curState.getBoard());
            curState = curState.getParent();
        }

        return boards;
    }

    // Solver client
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int[][] tiles = new int[N][N];
        // Read in the tiles
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                tiles[i][j] = StdIn.readInt();
        
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);
        // Print out the series of boards from the initial to the solution
        for (Board board : solver.solution())
            System.out.println(board);
        if (!solver.isSolvable())
            System.out.println("No solution possible");
        else {
            System.out.println("Number of states enqueued = "
                    + solver.numStates);
            System.out.println("Minimum number of moves = " + solver.moves());
        }
    }
}
