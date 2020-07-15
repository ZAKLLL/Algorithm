import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @program: suanfa
 * @description: LeetCode
 * @author: ZakL
 * @create: 2019-09-25 21:24
 **/
public class Solution {

    /**
     * gcd
     *
     * @param a
     * @param b
     * @return 最大公约数
     */
    int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    int[] dir = {1, 0, -1, 0, 1};
    public static void main(String[] args) {

    }
}

