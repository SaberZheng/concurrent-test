package com.ecut.pattern;

import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //构造futureTask
        FutureTask<String> futureTask = new FutureTask<String>(new RealData("a"));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //执行futureTask实际上是调用的RealData的call方法
        executorService.submit(futureTask);
        System.out.println("请求完毕！");
        //异步调用，因此这里可以进行其他的业务处理
        Thread.sleep(2000);
        //如果call方法没有执行完则依然等待
        System.out.println("数据为" + futureTask.get());
    }
}
