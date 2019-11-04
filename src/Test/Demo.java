package Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: suanfa
 * @description:
 * @author: ZakL
 * @create: 2019-10-10 14:52
 **/
public class Demo implements Runnable {
    public Integer i = 0;

    public final Object lock = new Object();

    @Override
    public void run() {
        synchronized (i) {
            for (int j = 0; j < 1_000_000; j++) {
                i++;
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        Thread thread1 = new Thread(demo);
        Thread thread2 = new Thread(demo);
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        System.out.println(demo.i);

    }
}


