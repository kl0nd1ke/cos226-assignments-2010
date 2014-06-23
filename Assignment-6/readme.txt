/**********************************************************************
 *  readme.txt template                                                   
 *  WordNet
**********************************************************************/

Name: Vladimir Costescu
Login: costescu
Precept: P02A

Partner name:     
Partner login:    
Partner precept:  

/**********************************************************************
 *  Describe the data structure(s) you used to store the information
 *  in synsets.txt. Why did you make this choice?
 **********************************************************************/

I used a symbol table with Integer keys and Synset values, where Synset
is a data type containing the synset in String form, a String array of
the nouns contained in the Synset, and the gloss in String form. This
data type also has a method that returns a symbol table containing the
nouns as keys (with dummy values of 0).

I chose to use these data structures in order to make noun retrieval
easier (because in later methods, I need to be able to check if a synset
contains a particular noun.

/**********************************************************************
 *  Describe the data structure(s) you used to store the information
 *  in hypernyms.txt. Why did you make this choice?
 **********************************************************************/

I didn't use any special data structure to store the information in
hypernyms.txt - I simply used the information to create the DAG.

/**********************************************************************
 *  Describe how you tested the various components.
 **********************************************************************/

I tested the various components by testing the entire system when I was
done coding it (using the sample inputs from the CS website).

/**********************************************************************
 *  Describe your algorithm to compute the SAP. What is the
 *  worst-case running time as a function of the number of vertices V
 *  and the number of edges E? Best case running time?
 **********************************************************************/

Description: My algorithm assumes there is a root vertex that has no 
links leaving it (i.e. no ancestors), finds it, and finds the paths from 
v and w to it. As it finds the vertices on the way to the root vertex (using BFS), the algorithm checks to see if the paths from v to root and 
w to root have any shared vertices, which are added to a MinPQ (using 
the data type Ancestor, which also stores the sum of the distances 
between v andthe vertex and w and the vertex). Once all the possible 
scenarios are considered (i.e. v or w being the common ancestor), the 
Ancestor with the smallest distance sum is removed from the MinPQ and 
returned.

Worst case: O(E + V + ln(E))

Best case: O(E + V)

/**********************************************************************
 *  Describe the effectiveness of the outcast detection scheme.
 *  Give a list of nouns for which you think it performs well.
 *  Give a list of nouns for which you think it performs poorly.
 **********************************************************************/

The outcast detection scheme is reasonably effective (so far as I can
tell), though it works better for shorter sets of nouns and for nouns
that are random (i.e. not nouns all but one of which have the same SAP
ancestor).

Good performance: 1750s 24/7 angstrom vitamin_A armored_personnel_carrier Aberdeen Abramis Achilles_tendon
(custom nouns chosen by me)

Poor performance: art canvas china culture kingdom particularism point portable ritual road script sculpture silk style transmission wall toluene
(contents of outcast10.txt)

/**********************************************************************
 *  If you implemented an optimization describe it here.
 **********************************************************************/


/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/

The SAP mechanism only works if there is a root vertex that has no
directed links leaving it. However, cycles within the structure of the
digraph should work OK.

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
