package com.ecut.lock;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockTest {

    private static ReentrantLock lock = new ReentrantLock(true);

    private static class ReentrantLockThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "得到了锁");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockThread runnable = new ReentrantLockThread();
        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        Thread t3 = new Thread(runnable, "t3");
        Thread t4 = new Thread(runnable, "t4");
        Thread t5 = new Thread(runnable, "t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
