package com.ecut.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private static Semaphore semaphore = new Semaphore(3);

    private static class SemphoreThread implements Runnable {
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "获得一个许可");
                Thread.sleep(1);
                System.out.println(Thread.currentThread().getName() + "完成，开始释放许可");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SemphoreThread semphoreThread = new SemphoreThread();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 12; i++) {
            executorService.submit(semphoreThread);
        }
    }
}
