package com.company;

public class BubbleSort
{
    public void sort(int[] array )
    {
           boolean isSorted;

        for (int i = 0; i < array.length; i++) {
            isSorted = true;
        for (int j = 1; j < array.length - i; j++) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                    isSorted = false; 
					}
            }

            if (isSorted)
            {
                return; 
				}
        }
    }
         public void swap( int[] array, int index1, int index2 ){
     var temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
     }


