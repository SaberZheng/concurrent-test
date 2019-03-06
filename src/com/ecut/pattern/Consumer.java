package com.ecut.pattern;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<PCData> queue;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                PCData pcData = queue.take();
                if (pcData != null) {
                    System.out.println(Thread.currentThread().getName() + " consumer data " + pcData);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
