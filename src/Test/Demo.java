package Test;

/**
 * @program: suanfa
 * @description:
 * @author: ZakL
 * @create: 2019-10-10 14:52
 **/
public class Demo implements Runnable {

    //    private Integer a = 0;
    private A a = new A();

    static class A {
        public int value = 0;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1_000_000; j++) {
            synchronized (a) {
                a.value++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(demo.a.value);
    }
}

