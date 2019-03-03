package com.ecut.threadpool;

import java.util.concurrent.*;

public class ThreadFactoryTest {

    public static class ThreadFactoryTask implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadFactoryTask threadFactoryTask = new ThreadFactoryTask();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                System.out.println("create"+thread);
                return thread;
            }
        });

        for(int i = 0 ; i < 5 ; i++){
            executorService.submit(threadFactoryTask);
        }
        Thread.sleep(2000);
    }
}
