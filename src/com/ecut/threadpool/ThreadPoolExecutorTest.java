package com.ecut.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    public static class ThreadPoolExecutorTask implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.MILLISECONDS, new SynchronousQueue<>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行" + Thread.currentThread().getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行结束" + Thread.currentThread().getName());
            }

            @Override
            protected void terminated() {
                System.out.println("线程退出");
            }
        };
        ThreadPoolExecutorTask threadPoolExecutorTask = new ThreadPoolExecutorTask();
        for (int i = 0; i < 5; i++) {
            executorService.submit(threadPoolExecutorTask);
        }
        executorService.shutdown();
    }
}
