/********************************************************************************
* Authors: Seema Saijpaul, Pratibha Natani, Neeraja Budamagunta, Maxwell A. Garvey
*********************************************************************************/

package com.softengg;

import java.util.Random;

public class ArrayUtil {
      
    public static Object[] swap(Object array[], int i, int j) {
        //Prevent attempts to swap with elements outside the array
        if (i >= array.length || j >= array.length || i < 0 || j < 0) {
                    return array;
        }
                  
        Object temp = array[i]; 
        
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        
        return array;
    }
}