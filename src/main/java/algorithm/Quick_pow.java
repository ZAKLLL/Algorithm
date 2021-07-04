package algorithm;

public class Quick_pow {

    /**
     * 快速幂算法
     * 求a的b次方
     *
     * @param a
     * @param b
     * @return
     */
    long pow(int a, int b) {
        if (b == 0) return 1;
        long res = pow(a, b / 2);
        return (b & 1) == 0 ? res * res : res * res * a;
    }

    int mod = (int) 1e9 + 7;

    long powWithMod(int a, long b) {
        if (b == 0) return 1;
        long res = powWithMod(a, b / 2);
        if ((b & 1) == 0) {
            res = res * res;
        } else {
            res = ((res * res) % mod) * a;
        }
        return res % mod;
    }

}
