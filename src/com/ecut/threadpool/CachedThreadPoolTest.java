package com.ecut.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolTest {

    public static class CachedThreadPoolTask implements Runnable {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                System.out.println("cachedThreadPoolTask----" + time + ":" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CachedThreadPoolTask cachedThreadPoolTask = new CachedThreadPoolTask();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(cachedThreadPoolTask);
        }
    }
}
