package com.softengg;

import java.util.Random;

public class ArrayUtil {
    public static Object[] permuteArray(Object array[]) {
        int size = array.length;
        Random rand = new Random();
        
        for (int i = 0; i < size; ++i) {
            int j = rand.nextInt(size);
            swap(array, i, j);
        }
        
        return array;
    }
      
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