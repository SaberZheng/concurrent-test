package com.ecut.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncTest {

    private static Integer calc(Integer para){
        try {
            //模拟长时间的计算过程
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para*para;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(()->calc(60)).thenApply((i) -> Integer.toString(i)).thenAccept(System.out::println);
        System.out.println(completableFuture.get());
    }
}
