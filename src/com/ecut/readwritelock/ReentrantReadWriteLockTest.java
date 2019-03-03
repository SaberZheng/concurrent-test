package com.ecut.readwritelock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static Lock readLock = lock.readLock();

    private static Lock writeLock = lock.writeLock();

    private static class ReadThread implements Runnable {

        @Override
        public void run() {
            System.out.println("开始读操作");
            try {
                System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName());
                readLock.lock();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }
    }

    private static class WriteThread implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName());
            writeLock.lock();
            System.out.println("开始写操作");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReadThread readThread = new ReadThread();
        WriteThread writeThread = new WriteThread();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            executorService.submit(readThread);
            executorService.submit(writeThread);

        }
        for (int i = 0; i < 20; i++) {
            executorService.submit(readThread);
        }
        for (int i = 0; i < 20; i++) {
            executorService.submit(writeThread);
        }
    }

}
