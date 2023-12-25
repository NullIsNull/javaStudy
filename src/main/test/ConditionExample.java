package main.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean isConditionMet = false;

    public void waitForCondition() throws InterruptedException {
        lock.lock();
        try {
            // 使用 while 循环来防止虚假唤醒
            while (!isConditionMet) {
                System.out.println("Thread is waiting for the condition.");
                condition.await(); // 等待条件满足
            }
            System.out.println("Thread is now processing after the condition is met.");
        } finally {
            lock.unlock();
        }
    }

    public void signalCondition() {
        lock.lock();
        try {
            System.out.println("Condition is met. Signaling the waiting thread.");
            isConditionMet = true;
            condition.signal(); // 唤醒等待线程
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionExample example = new ConditionExample();

        // 创建一个线程等待条件
        Thread waitingThread = new Thread(() -> {
            try {
                example.waitForCondition();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 创建另一个线程设置条件并唤醒等待线程
        Thread signalingThread = new Thread(() -> {
            try {
                Thread.sleep(2000); // 模拟一些操作
                example.signalCondition();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        waitingThread.start();
        signalingThread.start();
    }
}

