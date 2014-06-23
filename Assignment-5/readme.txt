/**********************************************************************
 *  readme.txt template                                                   
 *  The Markovian Candidate
 **********************************************************************/

Name: Vladimir Costescu
Login: costescu
Precept: P02A

Partner name:     
Partner login:    
Partner precept:  

Hours to complete assignment (optional):

/**********************************************************************
 *  Explain your overall approach.
 **********************************************************************/

My overall approach was to follow the instructions on the assignment
page as best as possible and to try to modularize my programs through
the use of methods and data structures.
 
/**********************************************************************
 *  Estimate using order-of-growth notation how much time and space
 *  your program requires to construct a Markov model from a data file
 *  of total size N characters, where k is the order parameter and
 *  S is the alphabet size. Regard k as a constant, and assume
 *  that symbol tables require logarithmic time per operation,
 *  and takes space proportional to the number of keys they contain.
 **********************************************************************/

Time: log(S) + (2k + 1)N + 4log(N) 
Space: 3N

/**********************************************************************
 *  Estimate using order-of-growth notation how much time and space
 *  your program requires to compute the log likelihood of a new test
 *  string of length N under such a model. Disregard the time and space
 *  used to construct the Markov model.
 **********************************************************************/

Time: (k + 1)N + 2log(N)
Space: N
 
/**********************************************************************
 *  Train your program using the first two debates.  Then, using
 *  data from the third debate, create a table showing how often your
 *  program misclassified a Obama quote (i.e., incorrectly attributed
 *  the quote to McCain), and how often it misclassified a McCain quote,
 *  giving both as a function of the order parameter k.  Use a range
 *  of value for k, such as: 0, 1, 2, 3, 5, 7, 10, 20, 50.
 **********************************************************************/

  k         0     1     2     3     5     7     10     20     50
Obama     18/51 9/51  9/51  7/51  7/50  7/49   5/46   3/35   0/4
McCain    25/63 17/63 17/63 14/62 18/61 15/59 14/52   5/33   0/1

Ignored: 0 values and exceptions thrown

/**********************************************************************
 *  Write a brief paragraph exploring the effectiveness of this
 *  technique for this problem. You can do this however you wish, but
 *  try to be creative, thoughtful, perceptive, critical and objective.
 *  Your observations can be quantitative and systematic, or they can
 *  be more anecdotal.  You are welcome (but not required) to do more
 *  experiments besides the one in the last question. You might think 
 *  about questions like the following:  Which values of k seem to be
 *  most effective?  What about the behavior of this technique seemed
 *  to be what you would have expected, and what aspects seemed surprising?
 *  At an intuitive level, how does the method seem to be working
 *  (or not working)?  What features of the text seem to have the
 *  greatest influence on its effectiveness?  How does this approach to
 *  this problem compare to how a human might solve it?  What ideas do
 *  you have for improving it?
 **********************************************************************/

Values of k between 1 and 10 seem to work best, and I think that these
values work best because the lower k values address consonant and vowel
groups, while the higher ones address words and parts of previous words.
I think that once k gets bigger than 10 the approach becomes less useful
for shorter quotes (and for really short quotes the approach fails if
k > length of quote). I was surprised that the lower-order k values
produced such accurate results, especially the 0-th order, which I
wouldn't have expected to do better than 50-50. I think that the 
consonant and vowel groups / configurations have the greatest influence
on the result. This approach is different from how a human would do it,
since it relies on statistics and considers spaces and punctuation more
than a human would (i.e. combinations like "f l", "k p", "...", "m. ",
etc.)

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
 *  Did you and your partner each fill out the midterm
 *  evaluation questionnaire?  
 **********************************************************************/

Yes

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/

This readme was not fun... :(
