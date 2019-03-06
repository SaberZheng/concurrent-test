package com.ecut.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CallablelleTest {
    public static class AddThread implements Callable {

       private AtomicInteger i = new AtomicInteger();

        @Override
        public AtomicInteger call() throws Exception {
            Thread.sleep(1000);
            i.incrementAndGet();
            System.out.println("i="+i);
            return i;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AddThread thread = new AddThread();
        Future future ;
        List<Future> futureList = new ArrayList();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0 ; i < 10 ; i++){
           future = executorService.submit(thread);
            System.out.println(future.get());
           futureList.add(future);
        }
        Thread.sleep(5000);
        for (Future f:futureList){
            System.out.println(f.get());
        }
    }
}
