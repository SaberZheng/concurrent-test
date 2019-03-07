package com.ecut.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompleteTest {
    public static class AskThread implements Runnable {

        private CompletableFuture<Integer> completableFuture = null;

        public AskThread(CompletableFuture<Integer> completableFuture) {
            this.completableFuture = completableFuture;
        }

        @Override
        public void run() {
            try {
                // CompletableFuture中没有数据，处于未完成状态
                System.out.println(completableFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture = new  CompletableFuture<>();
        //没有数据，请求线程一直等待
        new Thread(new AskThread(completableFuture)).start();
        //手动设置完成结果
        completableFuture.complete(1);
    }
}
