package multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: suanfa
 * @description:
 * @author: ZakL
 * @create: 2019-12-06 16:39
 **/
class FooBar {
    private int n;
    private volatile boolean flag = true;
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition foo = reentrantLock.newCondition();
    Condition bar = reentrantLock.newCondition();


    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            reentrantLock.lock();
            try {
                while (!flag) {
                    foo.await();
                }
                printFoo.run();
                flag = !flag;
                bar.signalAll();
            } finally {
                reentrantLock.unlock();
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            reentrantLock.lock();
            try {
                while (flag) {
                    bar.await();
                }
                printBar.run();
                flag = !flag;
                foo.signalAll();
            } finally {
                reentrantLock.unlock();
            }
        }
    }
}

class H2O {

    private Semaphore o = new Semaphore(0);
    private Semaphore h = new Semaphore(2);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire(1);
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        o.release(1);
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        o.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        h.release(2);
    }
}
