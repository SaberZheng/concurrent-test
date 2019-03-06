package com.ecut.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    public static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date d = threadLocal.get().parse("2019-03-03 19:36:15");
                System.out.println(i + "、" + Thread.currentThread() + ":" + d);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // sdf.format(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ParseDate parseDate = new ParseDate(1);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new ParseDate(i));
        }
    }
}
