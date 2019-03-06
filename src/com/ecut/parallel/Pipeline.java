package com.ecut.parallel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pipeline {

    public static class Msg {
        public double i;
        public double j;
        public String orgStr = null;
    }

    public static class Plus implements Runnable {

        public static BlockingQueue<Msg> blockingQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            try {
                Msg msg = blockingQueue.take();
                msg.j = msg.i + msg.j;
                Multiply.blockingQueue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static class Multiply implements Runnable {

        public static BlockingQueue<Msg> blockingQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            try {
                Msg msg = blockingQueue.take();
                msg.j = msg.j * msg.i;
                Div.blockingQueue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Div implements Runnable {

        public static BlockingQueue<Msg> blockingQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            try {
                Msg msg = blockingQueue.take();
                msg.j = msg.j / 2;
                System.out.println(msg.orgStr + "=" + msg.j);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j < 1000; j++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.orgStr = "((" + i + "+" + j + ")" + "*" + i + ")/2";
                Plus.blockingQueue.add(msg);
                new Thread(new Plus()).start();
                new Thread(new Multiply()).start();
                new Thread(new Div()).start();
            }
        }


    }
}
