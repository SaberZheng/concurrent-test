package com.ecut.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {
    static AtomicIntegerArray array = new AtomicIntegerArray(10);

    public static class AtomicIntegerThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                array.getAndIncrement(k % array.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerThread thread = new AtomicIntegerThread();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int k = 0; k < 10; k++) {
            executorService.submit(thread);
        }
        Thread.sleep(5000);
        System.out.println(array);
    }
}
