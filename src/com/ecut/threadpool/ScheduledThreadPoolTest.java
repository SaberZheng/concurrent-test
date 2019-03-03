package com.ecut.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        System.out.println(time + ":" + Thread.currentThread().getName());

        /**
         *  第一个任务将在10秒后执行，相邻任务的开始时间之间相差2秒，period如果小于任务的执行时间则下一个任务会立即执行
         */
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                String t = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                System.out.println("scheduleAtFixedRate----" + t + ":" + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 10, 2, TimeUnit.SECONDS);


        /**
         *   第一个任务将在10秒后执行，任务每隔2秒执行一次，因为线程睡了1秒，所以相邻任务的开始时间之间相差3秒
         */
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            {
                String t = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                System.out.println("scheduleWithFixedDelay----" + t + ":" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 10, 2, TimeUnit.SECONDS);

    }

}
