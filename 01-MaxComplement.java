/*
Author: Fabio Gallo (fabiorafael22@gmail.com)
		LinkedIn: https://www.linkedin.com/in/fabio-rafael-gallo-46334044/

PROBLEM DESCRIPTION (paraphrased)
	a[] is an array of k numbers, k>=0.	
	Define a function to find the max number which has 
	its complement in the array. The complement of a number 'n' is 
	the same number with different sign. For instance, 3 and -3.
	If there is any number with its complement, return 0.	
	
	Example:
		Given a[2,-6,-7,4,-2,9,-7,6], k=8. 
		Here we have two complement numbers: 2 and -2, 6 and -6. Thus, the max number with ist complement is 6.
		
*/

import java.util.*;

/*
    Puts all the elements from the array in a set. 
    For each element in the set, checks if its complement is in the set too.
    The maximum value is updated during this cycle.
*/
class Solution {
    public int solution(int[] A) {
        
        HashSet<Integer> set= new HashSet<Integer>();
        
		int max = 0; // because '0' must be returned in case there is any number with its complement.
		
        // put every element in the set
        for(int i = 0; i < A.length; i++){
           set.add(A[i]);
		   // if the complement is in the set, update the maximum
		   if(set.contains(-A[i])){
                max = Math.max(Math.abs(A[i]), max);
            }
        }
        
        return max;
    }
}