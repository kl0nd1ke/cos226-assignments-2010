/**********************************************************************
 *  8-Puzzle readme.txt template
 **********************************************************************/

Name: Vladimir Costescu      
Login: costescu
Precept: P02A   
Partner name: 
Partner login:    
Partner precept:
Hours to complete assignment (optional):

/**********************************************************************
 *  Explain briefly how you implemented Board.java.
 *  What methods (if any) did you add to the Board API? Why?
 **********************************************************************/

I represented each board using an int for N, a 2-d int array for the
tiles in the board, and two ints for the row and column indices of the
blank tile. I used these two ints heavily in the neighbors() method,
where knowing the position of the blank tile was instrumental in quickly
creating each board's neighbors.

I added the methods isSolvable() and isSolved() to the API
because I needed these methods for the Solver class, whose isSolvable()
method depends on Board's isSolvable() method, and which needs to test
for the condition that the reference board is reached in the
constructor's while loop.

/**********************************************************************
 *  Explain briefly how you represented states in the game
 *   (board + number of moves + previous state).
 **********************************************************************/

I represented states in the game using three instance variables: a
Board object for the state's board, an int primitive for the number of
moves, and a State object for the parent state.

/**********************************************************************
 *  Explain briefly how you detected unsolvable puzzles.
 **********************************************************************/

I implemented the formula described at http://www.cs.bham.ac.uk/~mdr
/teaching/modules04/java2/TilesSolvability.html.

/**********************************************************************
 *  For each of the following instances, give the minimal number of 
 *  moves to reach to goal state. Also give the amount of time
 *  it takes the A* heuristic with the Hamming and Manhattan
 *  priority functions to find the solution. If it can't find the
 *  solution in a reasonable amount of time (say 5 minutes) indicate
 *  that instead.
 **********************************************************************/

                  number of          seconds
     instance       moves      Hamming     Manhattan
   ------------  ----------   ----------   ----------
   puzzle28.txt      28          11.29         0.39
   puzzle30.txt      30          23.65         0.71
   puzzle32.txt      32           ---         29.84
   puzzle34.txt      34           ---          5.61
   puzzle36.txt      36           ---         55.21
   puzzle38.txt      38           ---         69.79
   puzzle40.txt      40           ---         28.30
   puzzle42.txt      42           ---        132.37

/**********************************************************************
 *  If you wanted to solve random 4-by-4 or 5-by-5 puzzles, which
 *  would you prefer:  more time (say 10x as much), more memory (say
 *  (10x as much), or a better priority function. Why?
 **********************************************************************/

I'd prefer a better priority function, because a vastly improved
heuristic would provide a much larger improvement in performance than
either more time or more memory. This is a symptom of the functions
for running time and memory usage, both of which are non-linear and
non-linearithmic due to the algorithm established using the present
priority functions.

/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/



/**********************************************************************
 *  List whatever help (if any) that you received. Be specific with
 *  the names of lab TAs, classmates, or course staff members.
 **********************************************************************/



/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/



/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/

