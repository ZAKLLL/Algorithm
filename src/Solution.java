import Algorithm.Utils;
import DataStructure.TreeNode;
import javafx.print.PageOrientation;
import jdk.nashorn.internal.ir.WhileNode;
import org.omg.CORBA.DataOutputStream;
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

    public String reformatDate(String date) {
        String[] s2 = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Map<String, String> map = new HashMap<>();
        int i = 0;
        for (String s : s2) {
            i++;
            map.put(s, i < 10 ? "0" + i : i + "");
        }
        String[] s = date.split(" ");
        String substring = s[0].substring(0, s[0].length() - 2);
        return s[2] + "-" + map.get(s[1]) + "-" + (substring.length() < 2 ? "0" + substring : substring);
    }

    public int rangeSum(int[] nums, int n, int left, int right) {
        int res = 0;
        Queue<Integer> pq = new PriorityQueue<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            List<Integer> tmpList = new ArrayList<>();
            for (Integer integer : list) {
                tmpList.add(num + integer);
                pq.add(num + integer);
            }
            tmpList.add(num);
            pq.add(num);
            list.clear();
            list.addAll(tmpList);
            tmpList.clear();
        }
        for (int i = 0; i < left - 1; i++) {
            pq.poll();
        }
        for (int i = 0; i < right - left + 1; i++) {
            res += pq.poll() % 1000000007;
        }
        return res;
    }

    public int minDifference(int[] nums) {
        int res = Integer.MAX_VALUE;
        Arrays.sort(nums);
        if (nums.length <= 3) return 0;
        int n = nums.length;
        res = Math.min(nums[n - 4] - nums[0], res);
        res = Math.min(nums[n - 1] - nums[3], res);
        res = Math.min(nums[n - 3] - nums[1], res);
        return Math.min(nums[n - 2] - nums[2], res);
    }


    public boolean winnerSquareGame(int n) {
        return help(n, true);
    }

    public boolean help(int a, boolean f) {
        if (isSq(a)) {
            return f;
        }
        int a1 = a;
        List<Integer> integers = new ArrayList<>();
        while (a1 > 0) {
            if (isSq(a1)) {
                integers.add(a1);
            }
            a1--;
        }
        boolean res = false;
        for (Integer integer : integers) {
            res |= help(a - integer, !f);
        }
        return res;
    }

    boolean isSq(int i) {
        int sqrt = (int) Math.sqrt(i);
        return sqrt * sqrt == i;
    }

    public static void main(String[] args) {
    }

}
