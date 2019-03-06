package com.ecut.atomic;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    public static AtomicInteger allScore = new AtomicInteger();

    public final static AtomicIntegerFieldUpdater<Candidate> socoreUpdater =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    public static class Candidate {
        int id;
        volatile int score;
    }

    public static class Vote implements Runnable {

        Candidate candidate;

        public Vote(Candidate candidate) {
            this.candidate = candidate;
        }

        @Override
        public void run() {
            if (Math.random() > 0.4) {
                socoreUpdater.incrementAndGet(candidate);
                allScore.incrementAndGet();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Vector v = new Vector();
        Candidate candidate = new Candidate();
        Vote vote = new Vote(candidate);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0 ; i < 10000 ; i++){
            executorService.execute(vote);
        }
        Thread.sleep(5000);
        System.out.println(allScore+":"+candidate.score);
    }
}
