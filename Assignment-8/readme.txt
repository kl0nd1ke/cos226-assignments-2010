/**********************************************************************
 *  readme.txt template                                                   
 *  Kd-tree
**********************************************************************/

Name: Vladimir Costescu
Login: costescu
Precept: P02A

Partner name:     
Partner login:    
Partner precept:  

/**********************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 **********************************************************************/

The Node data type I used to implement the 2d-tree data stracture is
exactly like the one described on the checklist page.

/**********************************************************************
 *  Describe your method for range search in a kd-tree.
 **********************************************************************/

My method for range search recursively searches the subtrees of the
2d tree (starting at the root), but ignores nodes (and their subtrees) whose rectangle does not intersect the range rectangle. Each node 
examined is tested for whether its point is located inside the range 
rectangle. If this condition is fulfilled, the point is added to the
stack (and when the method is done, the stack is returned).

/**********************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 **********************************************************************/

My method for nearest neighbor search recursively goes down the tree,
and for each node it searches the subtree in which the query point would
be inserted, then the other subtree, and then the point in the node 
itself. 

/**********************************************************************
 *  Give the total memory usage in bytes (using tilde notation)
 *  of your 2d-tree data structure as a function of the number of
 *  points N. Count all memory that is used by your 2d-tree,
 *  including memory for the nodes, points, and rectangles.
 **********************************************************************/

~44N

/**********************************************************************
 *  Give the expected running time in seconds (using tilde notation)
 *  to build a 2d-tree on N random points in the unit square.
 *  (Do not count the time to generate the N points or to read them
 *  in from standard input.)
 **********************************************************************/

~log N

/**********************************************************************
 *  How many nearest neighbor calculations can your 2d-tree
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? (Do not count the time to 
 *  read in the points or to build the 2d-tree.)
 *  Repeat the question but with the brute-force implementation.
 **********************************************************************/

                 nearest neighbor calculations per second
                     kd-tree             brute-force
input100K.txt
input1M.txt



/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/


/**********************************************************************
 *  List whatever help (if any) that you received.
 **********************************************************************/


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
