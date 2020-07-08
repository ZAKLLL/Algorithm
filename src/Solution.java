import Algorithm.Utils;
import DataStructure.TreeNode;
import javafx.print.PageOrientation;
import jdk.nashorn.internal.ir.WhileNode;
import org.omg.CORBA.INTERNAL;

import java.lang.reflect.Array;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @program: suanfa
 * @description: LeetCode
 * @author: ZakL
 * @create: 2019-09-25 21:24
 **/
public class Solution {
    //    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[] dir = new int[]{1, 0, -1, 0, 1};

    public boolean isPathCrossing(String path) {
        Set<List<Integer>> set = new HashSet<>();
        int a = 0, b = 0;
        for (int i = 0; i < path.length(); i++) {
            ArrayList<Integer> objects = new ArrayList<>();
            objects.add(a);
            objects.add(b);
            if (set.contains(objects)) return true;
            set.add(objects);
            char c = path.charAt(i);
            if (c == 'N') {
                a++;
            } else if (c == 'S') {
                a--;
            } else if (c == 'E') {
                b++;
            } else b--;
        }
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(a);
        objects.add(b);
        return set.contains(objects);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPathCrossing("NES"));

    }

}
