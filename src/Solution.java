import DataStructure.TreeNode;

import java.util.*;

/**
 * @program: suanfa
 * @description: LeetCode
 * @author: ZakL
 * @create: 2019-09-25 21:24
 **/
public class Solution {
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int lowBit = xor & (-xor);
        for (int num : nums) {
            if ((num & lowBit) == 1) {
                res[0] ^= num;
            } else res[1] ^= num;
        }
        return res;
    }


    public static void main(String[] args) {
    }

}

