package com.ecut.lambda;

import java.util.stream.IntStream;

public class ParallelFilterTest {

    public static boolean isPrime(int number){
        int tmp = number ;
        if(tmp < 2){
            return false;
        }
        for (int i = 2 ; Math.sqrt(tmp) >= i ; i++){
            if(tmp % i == 0) {
                return  false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long count = IntStream.range(1,1000000).parallel().filter(ParallelFilterTest::isPrime).count();
        System.out.println(count);
    }
}
