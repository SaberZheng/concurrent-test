package com.ecut.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorTest {

    public static class SingleThreadExecutorTask implements Runnable {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                System.out.println("singleThreadExecutorTask----" + time + ":" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SingleThreadExecutorTask singleThreadExecutorTask = new SingleThreadExecutorTask();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.submit(singleThreadExecutorTask);
        }
    }
}
