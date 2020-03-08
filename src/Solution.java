import DataStructure.ListNode;
import DataStructure.TreeNode;
import org.omg.CORBA.INTERNAL;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @program: suanfa
 * @description: LeetCode
 * @author: ZakL
 * @create: 2019-09-25 21:24
 **/
public class Solution {


    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int[][] dir = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        int time = -1;
        if (queue.size() == 0) time = 0;
        while (queue.size() > 0) {
            int len = queue.size();
            time++;
            for (int i = 0; i < len; i++) {
                int[] poll = queue.poll();
                for (int[] ints : dir) {
                    int newX = poll[0] + ints[0];
                    int newY = poll[1] + ints[1];
                    //判断新的newXnewY是否是好的橘子
                    if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2;
                        queue.add(new int[]{newX, newY});
                    }
                }
            }
        }
        for (int[] ints : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                if (ints[j] == 1) return -1;
            }
        }
        return time;
    }

    //用一个二维数组记录每个结点的入度和出度，出度为0入度为N的就是法官
    public int findJudge(int N, int[][] trust) {
        int[] count = new int[N];
        for (int[] ints : trust) {
            count[ints[0] - 1]--;
            count[ints[1] - 1]++;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] == N - 1) {
                return i + 1;
            }
        }
        return -1;
    }

    public boolean isLongPressedName(String name, String typed) {
        if (name == null || typed == null || typed.length() < name.length()) return false;
        if (name.length() == 0 && typed.length() > 0) return false;
        int index1 = 0;
        int index2 = 0;
        while (index1 < name.length() && index2 < typed.length()) {
            if (name.charAt(index1) != typed.charAt(index2)) return false;
            int count = 0;
            int count2 = 0;
            char pre = name.charAt(index1);
            while (index1 < name.length() && name.charAt(index1) == pre) {
                index1++;
                count++;
            }
            while (index2 < typed.length() && typed.charAt(index2) == pre) {
                count2++;
                index2++;
            }
            if (count2 < count) return false;
        }
        return index1 == name.length() && index2 == typed.length();
    }



    public static void main(String[] args) {
        boolean longPressedName = new Solution().isLongPressedName("alex","aaleex");
        System.out.println(longPressedName);
    }

}

