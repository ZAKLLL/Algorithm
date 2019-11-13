package Test;

/**
 * @program: suanfa
 * @description:
 * @author: ZakL
 * @create: 2019-10-10 14:52
 **/
public class Demo {
    private int b = 0;

    public void set1() {
        b = 0;
    }

    public void set2() {
        b = -1;
    }

    public void check() {
        if (0 != b && -1 != b) {
            System.out.println(b+"error");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        new Thread(() -> {
            while (true) {
                demo.set1();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                demo.set2();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                demo.check();
            }
        }).start();
    }
}


