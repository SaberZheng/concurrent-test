package com.ecut.pattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumerTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> blockingQueue = new LinkedBlockingDeque<PCData>(10);
        Producer producer1 = new Producer(blockingQueue);
        Producer producer2 = new Producer(blockingQueue);
        Producer producer3 = new Producer(blockingQueue);
        Consumer consumer1 = new Consumer(blockingQueue);
        Consumer consumer2 = new Consumer(blockingQueue);
        Consumer consumer3 = new Consumer(blockingQueue);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(producer1);
        executorService.execute(producer2);
        executorService.execute(producer3);
        executorService.execute(consumer1);
        executorService.execute(consumer2);
        executorService.execute(consumer3);
        Thread.sleep(10000);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        executorService.shutdown();
    }
}
