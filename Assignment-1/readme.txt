/**********************************************************************
 *  Percolation
 **********************************************************************/

Name: Vladimir Costescu
Login: costescu
Precept: P02A
Partner name (if applicable):
Parner login:
Partner precept:
Operating system: Windows 7 Professional
Compiler: JavaSE-1.6
Text editor / IDE: Eclipse 3.5.1
Hours to complete assignment (optional):


/**********************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates?
 **********************************************************************/

I implemented Percolation.java using a one-dimensional array to store
0 or 1 (open or closed) for each site and a QuickUWPC object for the
backend (i.e. uniting neighboring open sites and finding whether a site
is connected to the virtual site at the top). Using these data
structures, checking whether the system percolates was a simple call to
the QuickUWPC object's find() method to test whether the top and bottom
virtual sites are connected.

/**********************************************************************
 *  Using Percolation with quick find, give a formula (using tilde
 *  notation) for the running time (in seconds) of PercolationStats.java
 *  as a function of N and T. Estimate the largest experiment you could
 *  perform in 1 { minute, day, year } assuming your computer has
 *  enough memory.
 **********************************************************************/

measurements: (keep T constant) T = 50

 N          time (seconds)
------------------------------
50           0.716
100         20.503
200        659.581

(keep N constant) N = 75

 T          time (seconds)
------------------------------
50000        5.120
100000       5.169
200000       5.010

running time as a function of N and T:  ~2.06e-9 * N^5

1 minute: N = 123 and T = infinity
1 day: N = 530 and T = infinity
1 year: N = 1725 and T = infinity


/**********************************************************************
 *  Repeat the previous question, but use weighted quick union with
 *  path compression.
 **********************************************************************/

measurements: (keep T constant) T = 50

 N          time (seconds)
------------------------------
100          0.475
200          4.563
400         44.903 

(keep N constant)

 T          time (seconds) N = 75
------------------------------
50000        0.213
100000       0.212
200000       0.202

running time as a function of N and T:  ~1.16e-7 * N^3.3

1 minute: N = 436 and T = infinity
1 day: N = 3956 and T = infinity
1 year: N = 23644 and T = infinity


/**********************************************************************
 *  How many bytes does your Percolation.java object use as a function
 *  of N? Use tilde notation to simplify your answer.
 **********************************************************************/

using quick find: ~8N

using weighted quick union with path compression: ~12N



/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/




/**********************************************************************
 *  List whatever help (if any) that you received, including help
 *  from staff members or lab TAs.
 **********************************************************************/


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/




/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/

