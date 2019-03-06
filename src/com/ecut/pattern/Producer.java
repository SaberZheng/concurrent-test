package com.ecut.pattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private volatile boolean isRunning = true;

    private AtomicInteger count = new AtomicInteger();

    private BlockingQueue<PCData> queue;

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isRunning) {
            PCData data = new PCData(count.incrementAndGet());
            if (!queue.offer(data)) {
                System.out.println(Thread.currentThread().getName() + "failed to put data:" + data);
            } else {
                System.out.println(Thread.currentThread().getName() + " product data :" + data);
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
