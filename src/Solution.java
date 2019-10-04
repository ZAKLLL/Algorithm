import DataStructure.*;

import java.util.*;

/**
 * @program: suanfa
 * @description: LeetCode 新类
 * @author: ZakL
 * @create: 2019-09-25 21:24
 **/
public class Solution {

    public Node connect(Node root) {
        if (root == null || root.left == null) return root;
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode start;
        ListNode end;
        start = dummy;
        end = dummy;
        for (int i = 0; i < n; i++) {
            end = end.next;
        }
        System.out.println(end.val);
        while (end.next != null) {
            end = end.next;
            start = start.next;
        }
        start.next = start.next.next;
        return dummy.next;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        return 0;
    }

    public void solve(char[][] board) {
        if (board.length == 0) return;
        int w = board.length;
        int h = board[0].length;
        //判断该字符是否为边界字符
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                boolean isBorder = i == 0 || i == w - 1 || j == 0 || j == h - 1;
                if (isBorder && board[i][j] == 'O') dfs(board, i, j);
            }
        }
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }


    private void dfs(char[][] board, int i, int j) {
        //使用‘#’来标记与边界关联的O
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#' || board[i][j] == 'X')
            return;

        board[i][j] = '#';
        dfs(board, i - 1, j); // 向上查找
        dfs(board, i + 1, j); // 向下查找
        dfs(board, i, j - 1); // 向左查找
        dfs(board, i, j + 1); // 向右查找
    }

    public List<List<String>> partition(String s) {

        return null;
    }

    List<Integer> res;

    public List<Integer> inorderTraversal(TreeNode root) {
        res = new LinkedList<>();
        help(root);
        return null;
    }

    public void help(TreeNode treeNode) {
        if (treeNode == null) return;
        res.add(treeNode.val);
        help(treeNode.left);
        help(treeNode.right);
    }

    //数字相乘，不能使用Integer
    public static String multiply(String num1, String num2) {
        if (num1.length() == 1 && num1.equals("0") || num2.length() == 1 && num2.equals("0")) return "0";
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        List<String> strings = new ArrayList<>();
        int carry;
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            carry = 0;
            sb.delete(0, sb.length());
            //补0
            for (int k = 0; k < num1.length() - 1 - i; k++) {
                sb.append('0');
            }
            for (int j = num2.length() - 1; j >= 0; j--) {
                int temp = (chars1[i] - '0') * (chars2[j] - '0') + carry;
                carry = temp / 10;
                sb.append(temp % 10);
            }
            if (carry != 0) sb.append(carry);
            strings.add(sb.toString());
        }
        sb.delete(0, sb.length());
        int temp;
        carry = 0;
        for (int i = 0; i < strings.get(strings.size() - 1).length(); i++) {
            temp = 0;
            for (String s : strings) {
                if (s.length() >= i + 1) {
                    temp += s.charAt(i) - 48;
                }
            }
            temp += carry;
            carry = temp / 10;
            sb.append(temp % 10);
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public static String multiply2(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] res = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int LowPos = i + j + 1;
                int highPos = i + j;
                int temp = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + res[LowPos];
                res[LowPos] = (temp) % 10;
                res[highPos] += temp / 10; //这里高位不要取mod
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : res) {
            if (!(sb.length()==0)&&i==0) sb.append(i);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }


    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 1) return gas[0] >= cost[0] ? 0 : -1;
        int currentGas;
        outer:
        for (int i = 0; i < gas.length; i++) {
            currentGas = 0;
            if (gas[i] < cost[i]) continue;
            currentGas += gas[i];
            currentGas -= cost[i];
            if (i != gas.length - 1) {
                for (int j = i + 1; j < gas.length; j++) {
                    currentGas += gas[j];
                    if (currentGas < cost[j]) continue outer;
                    else if (j == gas.length - 1 && i == 0 && currentGas >= cost[j]) return 0;
                    currentGas -= cost[j];
                }
            }
            for (int k = 0; k < i; k++) {
                if (k == i - 1) {
                    currentGas += gas[k];
                    if (currentGas >= cost[k]) return i;
                }
                currentGas += gas[k];
                if (currentGas < cost[k]) continue outer;
                currentGas -= cost[k];
            }

        }
        return -1;
    }

    public static boolean publicStr(String a, String b) {
        if (a.length() != b.length()) return false;
        if (!b.contains(String.valueOf(a.charAt(0)))) return false;
        for (int i = 0; i <= a.length(); i++) {
            String tmp = a.substring(0, i + 1);
            if (!b.contains(tmp)) {
                return tmp.length() - 1 == b.length() - b.indexOf(tmp.substring(0, tmp.length() - 1));
            }
        }
        return true;
    }

    /*
    有一堆石头，每块石头的重量都是正整数。
    每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
    如果 x == y，那么两块石头都会被完全粉碎；
    如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
    最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
    提示：
    1 <= stones.length <= 30
    1 <= stones[i] <= 1000
     */
    public static int lastStoneWeight(int[] stones) {
        LinkedList<Integer> integers = new LinkedList<>();
        for (int stone : stones) {
            integers.add(stone);
        }
        Collections.sort(integers);
        while (integers.size() > 1) {
            int y = integers.removeLast();
            int x = integers.removeLast();
            if (x < y) integers.addLast(y - x);
            Collections.sort(integers);
        }
        return integers.size() == 0 ? 0 : integers.get(0);
    }

    /*
    给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
    示例:
    输入: 38
    输出: 2
    解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
    进阶:
    你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
     */
    public static int addDigits(int num) {
        if (num < 10) return num;
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return addDigits(sum);
    }

    /*
    给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
    示例 1:
    输入: candies = [1,1,2,2,3,3]
    输出: 3
    解析: 一共有三种种类的糖果，每一种都有两个。
         最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
    示例 2 :
    输入: candies = [1,1,2,3]
    输出: 2
    解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
     */
    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();
        for (int i : candies) {
            set.add(i);
        }
        //妹妹获得的糖的数量跟种类和数量有关,
        // 种类大于数量的一半时候，那能够获取到最多的种类是数量的一半
        // 当种类小于数量的一半时，能够获得最多的种类就是种类数
        return Math.min(set.size(), candies.length / 2);
    }

    /*
    在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
    示例 1:
    输入: 4->2->1->3
    输出: 1->2->3->4
    示例 2:
    输入: -1->5->3->4->0
    输出: -1->0->3->4->5
     */
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode res = new ListNode(0);
        ListNode tmpNode = new ListNode(0);
        res.next = tmpNode;
        while (pre.next != null) {
            int a = head.val;
            //找出链表中最小的那个节点
            while (head.next != null) {
                a = Math.min(a, head.next.val);
                head = head.next;
            }
            //删除链表中的最小节点：
            pre.next = deleteNode(pre.next, a);
            head = pre.next;
            tmpNode.next = new ListNode(a);
            tmpNode = tmpNode.next;
        }
        return res.next.next;
    }

    public ListNode deleteNode(ListNode listNode, int a) {
        ListNode res = new ListNode(0);
        ListNode res2 = new ListNode(0);
        res2.next = res;
        while (listNode != null) {
            if (listNode.val == a) {
                res.next = listNode.next;
                break;
            } else {
                res.next = new ListNode(listNode.val);
                listNode = listNode.next;
            }
            res = res.next;
        }
        return res2.next.next;
    }


    public ListNode sortList2(ListNode head) {
        if (head == null) return null;
        LinkedList<Integer> integers = new LinkedList<>();
        while (head != null) {
            integers.addLast(head.val);
            head = head.next;
        }
        Collections.sort(integers);
        ListNode listNode = new ListNode(0);
        ListNode res = listNode;
        for (Integer integer : integers) {
            listNode.next = new ListNode(integer);
            listNode = listNode.next;
        }
        return res.next;
    }

    public static boolean canGoOut(int[] nums) {
        if (nums[0] < 0) return true;
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < nums.length; ) {
            if (i + nums[i] >= nums.length || i + nums[i] < 0) return true;
            integers.add(i);
            i += nums[i];
            if (integers.contains(i)) return false;
        }
        return false;
    }

    public static boolean canGoLast(int[] nums) {
        if (nums[0] == nums.length - 1) return true;
        for (int i = 0; i < nums.length; ) {
            if (nums[i] == 0) return false;
            if (i + nums[i] == nums.length - 1) return true;
            i += nums[i];
        }
        return false;
    }

    public static int getMode(int[] nums) {
        if (nums.length == 1) return nums[0];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, (a, b) -> a + b);
        }
        for (int i : map.keySet()) {
            if (map.get(i) > nums.length / 2) {
                return i;
            }
        }
        return 0;
    }

    public static int getSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i % 2 == 0 ? -i : i;
        }
        return sum;
    }

    public static int getThirdNum(int[] nums) {
        Arrays.sort(nums);
        return nums[2];
    }

    public static int getNumIndex(int[] nums, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int num : nums) {
            stringBuilder.append(num);
        }
        return stringBuilder.toString().indexOf(String.valueOf(n));
    }

    /*
    将给定的数转换为字符串，
    原则如下：1对应 a，2对应b，…..26对应z，
    例如12258可以转换为"abbeh", "aveh", "abyh",
    "lbeh" and "lyh"，个数为5，编写一个函数，给出可以转换的不同字符串的个数。
     */
    public static int getCount(int[] nums) {
        if (nums.length == 1) return 1;
        if (nums.length == 2) return nums[0] + nums[1] <= 26 ? 2 : 1;
        if (nums[nums.length - 1] + nums[nums.length - 2] * 10 <= 26)
            return getCount(Arrays.copyOfRange(nums, 0, nums.length - 1))
                    + getCount(Arrays.copyOfRange(nums, 0, nums.length - 2));
        return getCount(Arrays.copyOfRange(nums, 0, nums.length - 1));
    }

    /*
    求一个数二进制的1的个数
     */
    public static boolean getBinaryCount(int n) {
        int i = Integer.bitCount(n);
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) count++;
            n >>>= 1;
        }
        return i == count;
    }

    public static boolean f1(int[] Locations) {
        Arrays.sort(Locations);
        int a = 0;
        for (int i = 0; i < Locations.length; i++) {
            if (a == Locations[i]) return false;
            a = i;
        }
        return true;
    }

    public static int getOneGroup(int[][] nums) {
        int count = 2;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                if (nums[i][j] == 1) {
                    goFindAnotherOne(nums, i, j, count);
                    count++;
                }
            }
        }
        return count - 2;
    }

    private static void goFindAnotherOne(int[][] nums, int i, int j, int count) {
        if (i < 0 || i >= nums.length || j < 0 || j >= nums.length || nums[i][j] != 1) {
            return;
        }
        nums[i][j] = count;

        goFindAnotherOne(nums, i - 1, j, count);//向上
        goFindAnotherOne(nums, i, j - 1, count);//向左
        goFindAnotherOne(nums, i + 1, j, count);//向下
        goFindAnotherOne(nums, i, j + 1, count);//向右
    }

    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE+" "+Integer.MIN_VALUE);
    }

}

