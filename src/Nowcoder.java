import java.util.Scanner;

/**
 * @program: suanfa
 * @description:
 * @author: ZakL
 * @create: 2019-09-01 14:51
 **/
public class Nowcoder {
    static {
        int x = 5;
    }

    static int x, y;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int count = in.nextInt();
            int[] target = new int[count];
            int[] L = new int[count];
            int[] R = new int[count];
            for (int i = 0; i < count; i++) {
                target[i] = in.nextInt();
            }
            for (int i = 0; i < count; i++) {
                L[i] = 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (target[j] < target[i]) {
                        L[i] = Math.max(L[i], L[j] + 1);
                    }
                }
            }
            for (int i = count - 1; i >= 0; i--) {
                R[i] = 1;
                for (int j = i + 1; j < count; j++)
                    if (target[i] > target[j])
                        R[i] = Math.max(R[i], R[j] + 1);
            }
            int max = 0;
            for (int i = 0; i < count; i++) {
                max = Math.max(max, L[i] + R[i] - 1);
            }
            System.out.println(count - max);
        }
    }
}


