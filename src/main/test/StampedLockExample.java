package main.test;

import org.w3c.dom.ls.LSOutput;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {

    private final StampedLock lock = new StampedLock();

    public void readCoordinates() {
        // 获取悲观读锁
        long stamp = lock.readLock();
        try {
            // 读取共享数据
            System.out.println("in read lock1");
        } finally {
            // 释放悲观读锁
//            lock.unlockRead(stamp);
//            lock.unlockWrite(stamp);
        }
    }

    public void readCoordinates2() {
        // 获取悲观读锁
//        long stamp = lock.readLock();
        long stamp = lock.writeLock();
        try {
            // 读取共享数据
            System.out.println("in read lock2");
        } finally {
            // 释放悲观读锁
//            lock.unlockWrite(stamp);
        }
    }

    public static void main(String[] args) {
        System.out.println("begin...");
        StampedLockExample lockExample = new StampedLockExample();
//        lockExample.readCoordinates2();
//        lockExample.readCoordinates();
//        System.out.println("end...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread111");
                lockExample.readCoordinates();
                try {
                    for (int i = 0; i < 10; i++) {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println(i);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("thread111end");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread222");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lockExample.readCoordinates();
                System.out.println("thread222end");
            }
        }).start();
        System.out.println("over==========");
    }
}
