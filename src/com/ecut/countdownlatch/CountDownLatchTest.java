package com.ecut.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    private static class CountDownLatchThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始报道");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "报道成功");
            countDownLatch.countDown();

        }
    }

    public static void main(String[] args) {
        CountDownLatchThread countDownLatchThread = new CountDownLatchThread();
        Thread thread1 = new Thread(countDownLatchThread, "学生1");
        Thread thread2 = new Thread(countDownLatchThread, "学生2");
        Thread thread3 = new Thread(countDownLatchThread, "学生3");
        Thread thread4 = new Thread(countDownLatchThread, "学生4");
        Thread thread5 = new Thread(countDownLatchThread, "学生5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        try {
            System.out.println("等待学生报道");
            countDownLatch.await();
            System.out.println("所有学生报道成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
