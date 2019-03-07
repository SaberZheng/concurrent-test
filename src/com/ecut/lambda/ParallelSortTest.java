package com.ecut.lambda;

import java.util.Arrays;

public class ParallelSortTest {
    public static void main(String[] args) {
        int[] arr = {8, 5 ,7,2,9,1};
        Arrays.parallelSort(arr);
        for(int i = 0 ; i < arr.length ; i++){
            System.out.println(arr[i]);
        }
    }
}
