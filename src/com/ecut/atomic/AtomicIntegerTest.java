package com.ecut.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    static AtomicInteger i = new AtomicInteger();

    public static class AtomicIntegerThread implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerThread atomicIntegerThread = new AtomicIntegerThread();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int k = 0 ; k < 10 ; k++){
            executorService.submit(atomicIntegerThread);
        }
        Thread.sleep(500);
       /* Thread[] thread = new Thread[10];
        for (int k = 0 ; k < 10 ; k++){
            thread[k] = new Thread(new AtomicIntegerThread());
        }
        for (int k = 0 ; k < 10 ; k++){
            thread[k].start();
        }
        for (int k = 0 ; k < 10 ; k++){
            thread[k].join();
        }*/
        System.out.println(i);
    }
}
