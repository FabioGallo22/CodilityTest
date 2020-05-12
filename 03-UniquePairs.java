/*
Author: Fabio Gallo (fabiorafael22@gmail.com)
		LinkedIn: https://www.linkedin.com/in/fabio-rafael-gallo-46334044/

PROBLEM DESCRIPTION
	a[] is an array of n numbers, count no. of identical pairs in an 
	array such that 0 <= p < q < n p,q are indices of pair.
	
	Example:
		a[3,5,6,3,3,5], n=6 
		Here the no of identical pairs are 4, which are (0,3), (0,4), (3,4), (1,5) 
		And not (2,2) or (4,3) as that violates p < q condition.
*/

// Here are presented two proposed solution

import java.util.*;

/* 
 * Counts the number of occurrences of each element in the array.
 * For each number of occurrences, calculates the number of unique pairs using 
 * the formula (n*(n-1))/2.
 */ 
class Solution1 {
    public int solution(int[] A) {
        int result = 0;
        
        // Store the number of occurrences of each element.
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + 1);
            } else {
                map.put(A[i], 1);
            }
        }
        
        // For each element, get the numbers of occurrences and compute the number of unique pairs.
        for (Map.Entry <Integer, Integer> entry : map.entrySet()) {
            int n = entry.getValue();
            
            // Given n occurrences, the number of unique pairs is defined by the formula (n*(n-1))/2
            result += (n * (n - 1)) / 2;
        }
         
        return result;
    }
}


//////////////////////////////////////////////////////////////////////////
//se puede mejorar con un solo ciclo  y haciendo el calculo progresivo
class Solution2 {
    public int solution(int[] A) {
        int result = 0;
        
        // Store the number of occurrences of each element.
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + 1);
            } else {
                map.put(A[i], 1);
            }
            
            // se descuenta 1 porq cada vez que se agrega la cantidad de 
            // combinaciones DISTINTAS aumenta en una unidad menos
            // Por ejemplo, al entonces por cuarta vez un elemento su combinatoria aumenta en 3.
            result += map.get(A[i]) - 1; 
            
        }
         
        return result;
    }
}