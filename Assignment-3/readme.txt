/**********************************************************************
 *  Pattern Recognition readme.txt template
 **********************************************************************/

Name: Vladimir Costescu 
Login: costescu
Precept: P02A   
Partner name:     
Partner login:    
Partner precept:
Hours to complete assignment (optional): way too many

/**********************************************************************
 *  Step 1.  Explain briefly how you implemented brute force.
 *           What method(s) did you add to the Point data type?
 **********************************************************************/

I implemented brute force by using 4 loops to find 4-tuples of collinear
points.
In the Point data type, I implemented 3 and 4 argument versions of
areCollinear(), as well as a version that takes an array of Points.

/**********************************************************************
 *  Step 2.  Explain briefly how you implemented the sorting solution.
 *           Did you sort by angle, slope, or something else?
 *           What method(s) did you add to the Point data type?
 *           What steps did you do to print a minimal representation?
 **********************************************************************/

I implemented the sorting solution by creating an array of all points other than i for each i, and then sorting it by angle. 
I added an angle(Point a, Point b) method and an equals(Point that) 
method to the Point data type.

To maintain a minimal representation, I created and sorted an array for 
each "block" of Points of the same angle to the point at i, and added 
the start and end Points in the array to two auxiliary arrays in order 
to keep track of drawn lines and not redraw lines in the future. To 
these ends, I created an increaseSize(Object[] a, int numItems) method
and a contains(Point[] a, Point p) method that uses binary search.

/**********************************************************************
 *  Empirical    Fill in the table below with actual running times in
 *  Analysis     seconds when reasonable (say 180 seconds or less).
 *               You can round to the nearest tenth of a second.
 **********************************************************************/

      N       brute       sorting
---------------------------------
     10         0.5           0.4
     20         0.7           0.5
     40         1.7           0.5
     80        21.6           0.5
    100        52.8           0.7
    150       280.9           0.7
    200                       0.7
    400                       1.3
   1000                       5.3
   2000                      21.5
   3000                      49.0
   4000                      95.5
   5000                     154.7
   6000                     238.9
  10000                     

/**********************************************************************
 *  Estimate how long it would take to solve an instance of size
 *  N = 1,000,000 for each of the two algorithms using your computer.
 **********************************************************************/

Brute: 5.28e17 seconds
Sorting: 5.3e8 seconds

/**********************************************************************
 *  Theoretical   Give the worst-case running time of your programs
 *  Analysis      as a function of N. Justify your answer briefly.
 **********************************************************************/

Brute: ~N^4, because there are 4 nested loops, and each one iterates
N times
Sorting: ~N^2, because there are 2 nested loops of significance, both of
which iterate N times (one loops through each point, and the other loops
through each point except the point in the first loop)

/**********************************************************************
 *  Known bugs / limitations. For example, if your program prints
 *  out different representations of the same line segment when there
 *  are 5 or more points on a line segment, indicate that here.
 **********************************************************************/


/**********************************************************************
 *  List whatever help (if any) that you received. Be specific with
 *  the names of lab TAs, classmates, or course staff members.
 **********************************************************************/


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

Trying to maintain a minimal representation of points in Fast was very,
very, very annoying (and took me at least 3 hours).

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
