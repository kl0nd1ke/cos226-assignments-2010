/**********************************************************************
 *  readme.txt template                                                   
 *  Burrows-Wheeler data compression.
**********************************************************************/

Name: Vladimir Costescu
Login: costescu
Precept: P02A

Partner name:     
Partner login:    
Partner precept:  

/**********************************************************************
 *  Which input files did you use to test your program?
 *  How long did your program take to compress / decompress these
 *  instances? What compression ratio did your program achieve?
 *  Compression consists of BurrowsWheeler, MoveToFront and Huffman
 *  all together.
 **********************************************************************/

I used my cell phone's stopwatch app to record the time, because -Xprof was not practical in this situation.

COSshort.txt     compress:  0.553s  decompress:  0.552s  ratio: 0.616
  643 bytes
COSlong.txt      compress:  0.681s  decompress:  0.604s  ratio: 0.335
  12,306 bytes
us.gif           compress:  0.682s  decompress:  0.679s  ratio: 1.026
  12,400 bytes
aesop.txt        compress:  1.178s  decompress:  0.777s  ratio: 0.355
  191,943 bytes
29.jpg           compress: 10.098s  decompress:  2.913s  ratio: 0.990
  2,675,365 bytes
CreditsMenu.bik  compress: 93.112s  decompress: 85.398s  ratio: 0.992
  22,859,528 bytes

/**********************************************************************
 *  Compare the results of your program (compression ratio and running
 *  time) on mobydick.txt to that of the most popular Windows
 *  compression program (pkzip) or the most popular Unix one (gzip).
 *  If you don't have pkzip, use 7zip and compress using zip format.
 **********************************************************************/

mobydick.txt
  1,191,463 bytes

BurrowsWheeler   compress:  3.495s  decompress:  1.943s  ratio: 0.347
7zip using .zip  compress:  1.138s  decompress:  0.335s  ratio: 0.391

/**********************************************************************
 *  Give the order-of-growth of the  running time of each of the 6
 *  methods as a function of the input size N and the alphabet size R
 *  in practice (on typical inputs) and in theory (in the worst case),
 *  e.g., N, N log N, N^2, R N.
 **********************************************************************/

                           typical                worst
-------------------------------------------------------------------
BurrowsWheeler encode     N + N log N + log N    N + N log N + log N
BurrowsWheeler decode     N log N                N log N
MoveToFront encode        N log R                R N
MoveToFront decode        N log R                R N
Huffman compress          N + R log R            N + R log R
Huffman expand            N                      N

/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/


/**********************************************************************
 *  List whatever help (if any) that you received.
 **********************************************************************/


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

Getting the memory usage and running time for BurrowsWheeler down to a 
manageable level.

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
