package Algorithm;

public class Quick_pow {


    long pow(int a, int b) {
        if (b == 0) return 1;
        long res = pow(a, b / 2);
        if (b % 2 == 0) {
            return res * res;
        } else return res * res * a;
    }

    public static void main(String[] args) {

        System.out.println(new Quick_pow().pow(2, 10));
    }

}
