package com.ecut.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ExceptionTest {
    private  static  Integer Div(Integer para){
        return para / 0 ;
    }

    public static void main(String[] args) {
        CompletableFuture<Integer> completeFuture = CompletableFuture.supplyAsync(()->Div(1)).exceptionally(ex ->{
            System.out.println(ex.toString());
            return 0;
        });
    }
}
