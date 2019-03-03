package com.ecut.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    private static boolean flag;

    private static class PrepareThread implements Runnable {

        private CyclicBarrier cyclicBarrier;

        PrepareThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "准备好了");
            try {
                cyclicBarrier.await();
                running();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private static void running() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "到达终点");
    }

    private static class BarrierAction implements Runnable {

        @Override
        public void run() {
            if (flag) {
                System.out.println("比赛结束！");
            } else {
                System.out.println("开始比赛！");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        BarrierAction barrierAction = new BarrierAction();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, barrierAction);
        PrepareThread prepareThread = new PrepareThread(cyclicBarrier);
        Thread thread1 = new Thread(prepareThread, "运动员1");
        Thread thread2 = new Thread(prepareThread, "运动员2");
        Thread thread3 = new Thread(prepareThread, "运动员3");
        Thread thread4 = new Thread(prepareThread, "运动员4");
        Thread thread5 = new Thread(prepareThread, "运动员5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
