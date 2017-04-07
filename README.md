# permutation
This project implements some permutation generating algorithms using Factory Pattern including:  
1.Lexicographic order generation method with LexOrderPermutation.java.  
2.Increasing carry number method with IncOrderPermutation.java.  
3.Decreasing carry number method with DecOrderPermutation.java.  
4.Ortho exchange method with SJTOrderPermutation.java.  
5.Recursion method with RecursionPermutation.java.  
6.BackTracking method with BackTrackingPermutation.java.  

PermutationTest is the main class to validate the implementation of permutation generating algorithms.  
There is a Java Interface named PermutationGeneration which has two abstract methods,   
One method named "void genPermutation()" generates all the permutation from the initial permutation you give to the last.  
The other one named "void genPermutation(int pos)" genrates the pos-th of the permutation after the initial permutation.  
