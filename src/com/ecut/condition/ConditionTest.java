package com.ecut.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private   static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static class ConditionThread implements  Runnable{

        @Override
        public void run() {
            try {
                lock.lock();
                condition.await();
                System.out.println("going on");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionThread conditionThread = new ConditionThread();
        Thread thread = new Thread(conditionThread);
        thread.start();
        Thread.sleep(5000);
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
