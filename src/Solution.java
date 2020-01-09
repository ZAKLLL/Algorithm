import DataStructure.ListNode;
import DataStructure.TreeNode;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @program: suanfa
 * @description: LeetCode
 * @author: ZakL
 * @create: 2019-09-25 21:24
 **/
public class Solution {


    public List<Integer> sequentialDigits(int low, int high) {
        int l = low;
        List<Integer> res = new ArrayList<>();
        int count = 0;
        int f = 0; //low数的首位
        while (l > 0) {
            l /= 10;
            if (l > 0 && l < 10) f = l % 10;
            count++;
        }
        while (true) {
            int v = f;
            for (int i = 1; i < count; i++) {
                if ((v % 10) < 9) {
                    v = v * 10 + (v % 10) + 1;
                } else {
                    count++;
                    i = 0;
                    f = 1;
                    v = f;
                    if (count > 9) {
                        //获得最大顺次数 123456789
                        return res;
                    }
                }
            }
            if (v > high) break;
            if (v >= low && v <= high) res.add(v);
            f++;
        }
        return res;
    }

    public String tree2str(TreeNode t) {
        if (t == null) return "";
        StringBuilder res = help(t, new StringBuilder());
        return res.toString().substring(1, res.length() - 1);
    }

    public StringBuilder help(TreeNode root, StringBuilder str) {
        str.append("(").append(root.val);
        if (root.left == null && root.right == null) {
            return str.append(")");
        } else if (root.right == null) {
            str = help(root.left, str);
        } else if (root.left == null) {
            str.append("()");
            str = help(root.right, str);
        } else {
            str = help(root.left, str);
            str = help(root.right, str);
        }
        return str.append(")");
    }

    public int findSecondMinimumValue(TreeNode root) {
        int a = helpFindSecondMinimumValue(root.left, root.val);
        int b = helpFindSecondMinimumValue(root.right, root.val);
        if (a == b && a == root.val) return -1;
        else if (a == root.val) return b;
        else if (b == root.val) return a;
        else return Math.min(a, b);
    }

    public int helpFindSecondMinimumValue(TreeNode root, int parentVal) {
        if (root == null) return parentVal;
        if (root.val == parentVal) {
            int a = helpFindSecondMinimumValue(root.left, root.val);
            int b = helpFindSecondMinimumValue(root.right, root.val);
            if (a == b) return a;
            if (a != parentVal && b != parentVal) return Math.min(a, b);
            else return Math.max(a, b);
        } else return root.val;
    }

    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        helpDeepestLeavesSum(root, map, 0);
        int i = Integer.MIN_VALUE;
        for (Integer integer : map.keySet()) {
            i = Math.max(integer, i);
        }
        return map.get(i);
    }

    public void helpDeepestLeavesSum(TreeNode root, Map<Integer, Integer> map, int count) {
        if (root.left == null && root.right == null) {
            if (!map.containsKey(count)) {
                map.put(count, root.val);
            } else {
                map.put(count, map.get(count) + root.val);
            }
        }
        int a = count;
        int b = count;
        if (root.left != null) {
            helpDeepestLeavesSum(root.left, map, ++a);
        }
        if (root.right != null) {
            helpDeepestLeavesSum(root.right, map, ++b);
        }
    }

    //平衡树==一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1==每个子树都是平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        boolean flag;
        //先判断左树是否平衡并且右树是否平衡
        flag = isBalanced(root.left) && isBalanced(root.right);
        //当左右树高度都平衡的时候两棵树高度是否满足高度差唯一。
        flag = flag && (Math.abs(helpIsBalanced1(root.left) - helpIsBalanced1(root.right)) <= 1);
        return flag;
    }

    public int helpIsBalanced1(TreeNode root) {
        return root == null ? 0 : Math.max(helpIsBalanced1(root.left), helpIsBalanced1(root.right)) + 1;
    }

    public int findBestValue(int[] arr, int target) {
        int min = target / arr.length;
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        int[] arrbackup = new int[arr.length];
        int v = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int tempSum = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] > i) arrbackup[j] = i;
                else arrbackup[j] = arr[j];
                tempSum += arrbackup[j];
            }
            int abs = Math.abs(tempSum - target);
            if (abs < v) {
                v = abs;
                res = i;
            }
            if (tempSum - target > 0) break;
        }
        return res;
    }


    public boolean isBalanced2(TreeNode treeNode) {
        return process(treeNode) != -1;
    }

    public int process(TreeNode root) {
        if (root == null) return 0;
        int leftH = process(root.left);
        if (leftH == -1) return -1;
        int rightH = process(root.right);
        if (rightH == -1) return -1;
        //高度差相差1
        if (Math.abs(leftH - rightH) > 1) return -1;
        //当前树为平衡二叉树
        return Math.max(leftH, rightH) + 1;
    }

    double preValue = Double.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return false;
        if (isValidBST(root.left)) {
            if (root.val > preValue) {
                preValue = root.val;
                return isValidBST(root.right);
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean validPalindrome(String s) {
        if (s == null) return false;
        if (s.length() == 0 || s.length() == 1) return true;
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return helpValidPalindrome(s.substring(l, r)) || helpValidPalindrome(s.substring(l + 1, r + 1));
            }
        }
        return true;
    }

    public boolean helpValidPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) return true;
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }

    public List<String> letterCasePermutation(String S) {
        List<String> res = new LinkedList<>();
        if (S == null || S.length() == 0) return res;
        helpLetterCasePermutation(S.toCharArray(), 0, res);
        return res;

    }

    public void helpLetterCasePermutation(char[] s, int index, List<String> res) {
        if (index == s.length) {
            res.add(String.valueOf(s));
            return;
        }
        if (Character.isDigit(s[index])) {
            helpLetterCasePermutation(s, ++index, res);
        } else {
            s[index] = Character.toLowerCase(s[index]);
            helpLetterCasePermutation(s, index + 1, res);
            s[index] = Character.toUpperCase(s[index]);
            helpLetterCasePermutation(s, index + 1, res);
        }
    }

    public List<String> commonChars(String[] A) {
        List<String> res = new LinkedList<>();
        if (A == null || A.length == 0) return res;
        int[] arr = new int[26];
        for (int i = 0; i < A[0].length(); i++) {
            arr[A[0].charAt(i) - 'a']++;
        }
        int[] tempArr = new int[26];
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < A[i].length(); j++) {
                tempArr[A[i].charAt(j) - 'a']++;
            }
            for (int j = 0; j < arr.length; j++) {
                arr[j] = Math.min(arr[j], tempArr[j]);
                tempArr[j] = 0;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }

    public int repeatedStringMatch(String A, String B) {
        if (B.contains(A)) return 1;
        int maxLen = 2 * A.length() + B.length();
        StringBuilder str = new StringBuilder(A);
        int i = 1;
        while (str.length() < maxLen) {
            if (str.toString().contains(B)) {
                return i;
            } else {
                i++;
                str.append(A);
            }
        }
        return -1;
    }

    //lrc 最长公共子串
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                boolean b = text1.charAt(i) == text2.charAt(j);
                if (i != 0 && j != 0) {
                    if (b) dp[i][j] = dp[i - 1][j - 1] + 1;
                    else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                } else {
                    if (b) dp[i][j] = 1;
                    else {
                        if (i == 0 && j == 0) continue;
                        if (i == 0) dp[i][j] = dp[i][j - 1];
                        else dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }


    int res = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        helpLongestUnivaluePath(root);
        return res;
    }

    public int helpLongestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        int leftCount = 0;
        int rightCount = 0;
        if (root.left != null && root.val == root.left.val) {
            leftCount = helpLongestUnivaluePath(root.left);
        } else helpLongestUnivaluePath(root.left);
        if (root.right != null && root.val == root.right.val) {
            rightCount = helpLongestUnivaluePath(root.right);
        } else helpLongestUnivaluePath(root.right);
        res = Math.max(res, leftCount + rightCount);
        return Math.max(leftCount, rightCount) + 1;
    }

    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        String res = "";
        for (String str : words) {
            //对字母排序后，第一个单词一定是共有的，后面只需在此基础上添加
            if (str.length() == 1 || set.contains(str.substring(0, str.length() - 1))) {
                res = str.length() > res.length() ? str : res;
                set.add(str);
            }
        }
        return res;

    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int count = 0;
        int index = 0;
        while (candies > 0) {
            if (candies <= count) {
                res[index] += candies;
                break;
            }
            res[index++] += ++count;
            candies -= count;
            if (index == num_people) {
                index = 0;
            }
        }
        return res;
    }


    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new LinkedList<>();
        helpCombinationSum3(k, n, 0, new LinkedList<>(), res);
        return res;
    }

    public void helpCombinationSum3(int k, int n, int tempSum, LinkedList<Integer> integers, List<List<Integer>> res) {
        if (tempSum > n || integers.size() > k || (tempSum != n && integers.size() == k)) return;
        if (tempSum == n && integers.size() == k) res.add((List<Integer>) integers.clone());
        for (int i = integers.size() == 0 ? 1 : integers.getLast(); i <= 9; i++) {
            if (integers.size() > 0 && integers.getLast() >= i) continue;
            tempSum += i;
            integers.add(i);
            helpCombinationSum3(k, n, tempSum, integers, res);
            tempSum -= i;
            integers.removeLast();
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head; //奇数
        ListNode even = head.next; //偶数
        ListNode cur = head.next;
        while (cur.next != null && cur.next.next != null) {
            cur = cur.next;
            ListNode tempEven = odd.next;
            odd.next = cur;
            odd = odd.next;
            cur = cur.next;
            even.next = cur;
            even = even.next;
            odd.next = tempEven;
        }
        if (cur.next != null) {
            ListNode temp = odd.next;
            odd.next = cur.next;
            odd.next.next = temp;
            even.next = null;
        }

        return head;
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list = new LinkedList<>();
        bst(root1, list);
        bst(root2, list);
        list.sort(Comparator.comparingInt(o -> o));
        return list;
    }

    private List<Integer> merge(List<Integer> list, int mid) {
        List<Integer> res = new LinkedList<>(list);
        int p = 0;
        int q = mid + 1;
        int index = 0;
        while (p <= mid && q < list.size()) {
            res.set(index++, list.get(p) < list.get(q) ? list.get(p++) : list.get(q++));
        }
        while (p <= mid) {
            res.set(index++, list.get(p++));
        }
        while (q < list.size()) {
            res.set(index++, list.get(q++));
        }
        return res;
    }

    private void bst(TreeNode root, List<Integer> list1) {
        if (root == null) return;
        bst(root.left, list1);
        list1.add(root.val);
        bst(root.right, list1);
    }

    public String freqAlphabets(String s) {
        StringBuilder res = new StringBuilder();
        int index = 0;
        while (index < s.length()) {
            if (index + 2 < s.length() && s.charAt(index + 2) == '#') {
                Integer integer = Integer.valueOf(s.substring(index, index + 2));
                res.append((char) (integer + 96));
                index += 3;
            } else {
                res.append((char) (s.charAt(index) + 48));
                index++;
            }
        }
        return res.toString();
    }

    //    public List<Integer> diffWaysToCompute(String input) {
//        return partition(input);
//    }
//
//    private List<Integer> partition(String str) {
//        List<Integer> res = new LinkedList<>();
//        for (int i = 0; i < str.length(); i++) {
//            char c = str.charAt(i);
//            if (!Character.isDigit(c)) {
//                List<Integer> partitionLeft = partition(str.substring(0, i));
//                List<Integer> partitionRight = partition(str.substring(i + 1));
//                for (int left : partitionLeft) {
//                    for (int right : partitionRight) {
//                        if (c == '+') {
//                            res.add(left + right);
//                        }
//                        if (c == '-') {
//                            res.add(left - right);
//                        }
//                        if (c == '*') {
//                            res.add(left * right);
//                        }
//                    }
//                }
//            }
//        }
//        if (res.size() == 0) res.add(Integer.valueOf(str));
//        return res;
//    }
    public List<Integer> diffWaysToCompute(String input) {
        return recursive(input, new HashMap<>());
    }

    private List<Integer> recursive(String input, Map<String, List<Integer>> map) {
        if (map.get(input) != null) return map.get(input);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                List<Integer> lefts = recursive(input.substring(0, i), map);
                List<Integer> rights = recursive(input.substring(i + 1), map);
                for (Integer left : lefts) {
                    for (Integer right : rights) {
                        if (input.charAt(i) == '+') result.add(left + right);
                        if (input.charAt(i) == '-') result.add(left - right);
                        if (input.charAt(i) == '*') result.add(left * right);
                    }
                }
            }
        }
        if (result.size() == 0) {
            result.add(Integer.valueOf(input));
        }
        map.put(input, result);
        return result;
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
            for (int j = 0; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public static int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;
        int a = nums[0];
        int res = 0;
        boolean head = false;
        boolean tail = false;
        for (int i = 1; i < nums.length - 1; i++) {
            if ((nums[i] > a && nums[i + 1] < nums[i]) || (nums[i] < a && nums[i + 1] > nums[i])) {
                res++;
            }
            if (i == 1 && res == 0) head = true;
            a = nums[i];
        }
        if (tail) res++;
        if (head) res++;
        return res;
    }

    public static int wiggleMaxLength2(int[] nums) {
        if (nums.length < 2) return nums.length;
        int low = 1;
        int high = 1;
        for (int i = 1; i < nums.length; i++) {
            //起始位高低位都为1.
            //保证了在连续上升/下降的情况下 high/low 不会重复递增，
            //在上升情况中，只要没有发生坡度中断，则high对应的low不会发生改变。那么high位就不会发生改变。
            if (nums[i] > nums[i - 1]) high = low + 1;
            if (nums[i] < nums[i - 1]) low = high + 1;
        }
        return Math.max(low, high);
    }

    private static int[] randomArr() {

        int[] arr = new int[(int) (Math.random() * 100)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    public int maxProduct(String[] words) {
        int[] arr = new int[words.length];
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                arr[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
//                    res = Math.max(res, Integer.bitCount(arr[i]) * Integer.bitCount(arr[j]));
                    System.out.println("word[i]" + words[i] + ":" + Integer.toBinaryString(arr[i]) + "-----word[j]" + words[j] + ":" + Integer.toBinaryString(arr[j]));
                }
            }
        }
        return res;
    }

    public int bulbSwitch(int n) {

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct(new String[]{"eae", "ea", "aaf", "bda", "fcf", "dc", "ac", "ce", "cefde", "dabae"}));

    }
}


