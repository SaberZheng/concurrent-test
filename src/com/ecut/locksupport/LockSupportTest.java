package com.ecut.locksupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    private static class LockSupportThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程阻塞");
            LockSupport.park();
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockSupportThread lockSupportThread = new LockSupportThread();
        Thread thread = new Thread(lockSupportThread,"t1");
        thread.start();
        Thread.sleep(5000);
        //唤醒阻塞线程
        System.out.println("main唤醒阻塞线程");
        LockSupport.unpark(thread);
    }

}
