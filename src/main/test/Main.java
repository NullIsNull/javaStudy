package main.test;

import lombok.Getter;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, InterruptedException {
        ChangeThread changeThread = new ChangeThread();
        new Thread(changeThread).start();

        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        while (true) {
            boolean flag = changeThread.flag;
//            unsafe.loadFence(); //加入读内存屏障
            if (flag){
                System.out.println("detected flag changed");
                break;
            }
        }
        System.out.println("main thread end");
    }

    @Getter
    static class ChangeThread implements Runnable {
        boolean flag = false;
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("subThread change flag to:" + flag);
            flag = true;
        }
    }
}