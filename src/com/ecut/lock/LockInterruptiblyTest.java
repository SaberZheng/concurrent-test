package com.ecut.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyTest {
    private static ReentrantLock lock = new ReentrantLock();

    private static class ReentrantLockThread implements Runnable {

        @Override
        public void run() {
            try {
                lock.lockInterruptibly();
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "得到了锁 i =  " + i);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被打断了");
            } finally {
                //查询当前线程是否保持此锁。
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
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
        t2.interrupt();
    }
}
