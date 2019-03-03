package com.ecut.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SingleThreadScheduledExecutorTest {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        System.out.println(time + ":" + Thread.currentThread().getName());
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        /**
         * 在10秒后，会执行一次任务
         */
        scheduledExecutorService.schedule(() -> {
            try {
                String t = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                System.out.println("SingleThreadScheduledExecutor----" + t + ":" + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 10, TimeUnit.SECONDS);
    }
}
