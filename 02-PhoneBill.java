/*
Author: Fabio Gallo (fabiorafael22@gmail.com)
		LinkedIn: https://www.linkedin.com/in/fabio-rafael-gallo-46334044/

PROBLEM DESCRIPTION
		< In file 02-PhoneBill-ProblemDescription.png >
*/


import java.io.*;
import java.util.*;

class Solution {
    public int solution(String S) {
        int totalCost = 0;
        // Store the cost asociated to each phone number.
        //#cambio HashMap<String, Integer> map = new HashMap<String, Integer>();
        HashMap<String, int[]> map = new HashMap<String, int[]>(); // <phoneNumber, [sum of all durations, cost of all calls]>
		
        
       
        BufferedReader bufReader = new BufferedReader(new StringReader(S));
        String line = null;
        try{
            // Iterate over the lines of the string.
            while ((line = bufReader.readLine()) != null) {
                int duration =  getSeconds(line.substring(0,8));
                String phoneNumber = line.substring(9,20);
                             
                int partialCost = calculateCost(duration);          
                totalCost += partialCost;
                
                if (map.containsKey(phoneNumber)) {
                    //  already exist the number
					// the durations' sum and total cost to that number are updated
                    // map.put(phoneNumber, map.get(phoneNumber) + partialCost);
					//#cambio
					int[] values = map.get(phoneNumber);
					
					// System.out.println("**** previo a actualizar . costo anterior: " + values[1]);
					
					values[0] += duration;
					values[1] += partialCost;
					map.put(phoneNumber, values);
					// System.out.println("** Se actualiza: " + phoneNumber + " ( " + values[0] + " , " + values[1] + " )");
					
                } else {
                    // is the firt time that the number occurrence
                    //#cambio
					map.put(phoneNumber, 
						new int[] { duration, partialCost});
					// System.out.println("** Se guarda: " + phoneNumber + " ( " + duration + " , " + partialCost + " )");
                }
                
            }
        } catch(IOException e){
            System.out.println("I/O Exception.");
        }
        
        //System.out.println("Costo total sin descuento: " + totalCost);
        
        // Get the cost of the highest duration       
        totalCost -= getCostAllCallsBiggestDuration(map);
        
        return totalCost;
        
    }
	/* #nuevo
	Given a hashmap, return the cost of a phone number with the higgest duration considering the smaller number
	*/
	public int getCostAllCallsBiggestDuration(HashMap<String, int[]> map){
		String minNumOfMaxDuration = "999-99-999"; 
        int maxDuration = 0;
        int cost = 0;
		
        for (Map.Entry <String, int[]> entry : map.entrySet()) {
			int [] values = entry.getValue();
			
			// System.out.println("\t > > Se leyó del hashmap: "  + entry.getKey() + ": (" + values[0] + " , " + values[1] + ")");
			
            if (values[0] == maxDuration) {
				// The smaller numer is keeped.
				 
                /* num1.compareTo(num2) Retorna 
                0 si son iguales, 
                <0 si num2 es menor que num1
                >0 si num1 es mayor que num2  */
                int comparisonResult = minNumOfMaxDuration.compareTo(entry.getKey());
                // System.out.println("\t\t Comparison: " + comparisonResult + "(" + minNumOfMaxDuration + " , " + entry.getKey() + ")");
                if (comparisonResult > 0){
					// new phone number is smaller than the previos one
					minNumOfMaxDuration = entry.getKey();
					cost = values[1];// se tiene actualizar el costo porque aunque duren lo mismo no necesariamente cuesta lo mismo
				}
            }else{
				if (values[0] > maxDuration){
					// found a phone number with bigger duration 
					maxDuration = values[0];
					minNumOfMaxDuration = entry.getKey();
					cost = values[1];
				}
			}
        }
        
        // System.out.println("Se decuenta " + cost + " correspondiente al num de tel. " + minNumOfMaxDuration);
		
		return cost;		
	}
    
    /* 
     * Given a string representing a duration in the format hh:mm:ss, returns the duration in seconds.
     */
    public int getSeconds(String duration){
        int hours = Integer.parseInt(duration.substring(0,2));
        int minutes = Integer.parseInt(duration.substring(3,5));
        int seconds = Integer.parseInt(duration.substring(6,8));
        int total = seconds + (minutes * 60) + (hours * 3600);
        return total;  
    }
    
    /* 
     * Given a call duracion in (seconds), return its cost.
     */
    public int calculateCost(int duration){
        int cost = 0;
        
        if(duration < 300){
            // Tha call duration is lower than 5 minutes
            cost = duration * 3;
        }else{
            int minutes = duration / 60;
            if(duration % 60 != 0){
                minutes ++;
            }
            cost = minutes * 150;
        }
        
        //System.out.println("\t Costo para " + duration + " segundos: " + cost);
        
        return cost;
    }
}