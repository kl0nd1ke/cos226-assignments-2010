/**********************************************************************
 *  Randomized Queues and Deques
 **********************************************************************/

Name: Vladimir Costescu
Login: costescu
Precept: P02A
Partner name:
Parner login:
Partner precept:
Hours to complete assignment (optional):


/**********************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **********************************************************************/

I implemented the deque using a doubly-linked list, and the randomized
queue using an array. Both of these choices were motivated by the 
time and size constraints imposed by the assignment - I felt that these
data types best fit the constraints.

/**********************************************************************
 *  Briefly describe why any sequence of N randomized queue operations,
 *  starting from an empty randomized queue, takes O(N) time.
 **********************************************************************/

This is true because any enqueues and dequeues take constant time in all
cases except when the array has to be resized (grown or shrunk), where
such an operation takes O(N) time. Given these characteristics and the
characteristics of a random sequence of operations, the amortized time
becomes cN, where c is a constant.

/**********************************************************************
 *  Briefly describe why each Deque operation takes O(1) time.
 **********************************************************************/

This is true because adding or removing an item to / from the deque
doesn't involve any traversal of the deque, only a fixed number of 
operations associated with changing the links as necessary.

/**********************************************************************
 *  How much memory (in bytes) does your data type use to store N items.
 *  Use tilde notation to simplify your answer. Use the memory requirements
 *  for a "typical machine" given in Lecture. In your analysis, don't include
 *  the memory for the items themselves (as this memory is allocated by
 *  the client and depends on the item type.
 **********************************************************************/

RandomizedQueue
Best Case: ~20N

Worst Case: ~20N


Dequeue
Best Case: ~4N

Worst Case: ~16N


/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/


/**********************************************************************
 *  List whatever help (if any) that you received and from whom,
 *  including help from staff members or lab TAs.
 **********************************************************************/



/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/




/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
