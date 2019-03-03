package com.ecut.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest {
    private static ReentrantLock lock = new ReentrantLock();

    private static class ReentrantLockThread implements Runnable {

        @Override
        public void run() {
            try {
                if (lock.tryLock(1, TimeUnit.MICROSECONDS)) {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(Thread.currentThread().getName() + "得到了锁 i =  " + i);
                    }
                    lock.unlock();
                } else {
                    System.out.println(Thread.currentThread().getName() + "尝试获取锁失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockThread runnable = new ReentrantLockThread();
        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        Thread t3 = new Thread(runnable, "t3");
        t1.start();
        t2.start();
        t3.start();
    }
}
