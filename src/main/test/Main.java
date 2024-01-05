package main.test;

import lombok.Getter;
import sun.misc.Unsafe;

import java.io.Console;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.sql.Time;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Main extends AbstractQueuedSynchronizer {

    public static void main(String[] args) {
        new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100));

        Main main = new Main();
        int state = main.getState();
    }

    static class Worker implements Runnable {
        private final CyclicBarrier barrier;

        public Worker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread is waiting at the barrier1.");
                TimeUnit.SECONDS.sleep(10);
                barrier.await(); // 等待其他线程到达屏障点
                System.out.println("Thread has passed the barrier1.");

                System.out.println("Thread is waiting at the barrier2.");
                TimeUnit.SECONDS.sleep(10);
                barrier.await();
                System.out.println("Thread has passed the barrier2.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}