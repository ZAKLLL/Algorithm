import com.sun.org.apache.xerces.internal.xs.LSInputList;

import java.io.File;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class Main {


    //回文
    public String getracts(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            StringBuilder target = new StringBuilder();
            target.append(chars[i]);
            for (int j = i + 1; j < chars.length; j++) {
                target.append(chars[j]);
                if (istract(target.toString())) {
                    return target.toString();
                }
            }
        }
        return "无回文字符串";
    }

    public static boolean istract(String string) {
        List<Character> l1 = new ArrayList<>();
        List<Character> l2 = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            l1.add(string.charAt(i));
        }
        for (int i = string.length() - 1; i >= 0; i--) {
            l2.add(string.charAt(i));
        }
        return l1.equals(l2);
    }

    //递归实现斐波那契
    public static int getFibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return getFibonacci(n - 1) + (n - 2);
        }
    }

    //使用循环实现斐波那契
    public static int getFibonacci2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int a1 = 1;
        int a2 = 2;
        int a3 = 0;
        for (int i = 0; i < n - 1; i++) {
            a3 = a1 + a2;
            a1 = a2;
            a2 = a3;
        }
        return a3;
    }

    //使用递归遍历文件夹
    public static void show(String path) {
        File file = new File(path);
        getallfilename(file);
    }

    public static void getallfilename(File file) {
        File[] files = file.listFiles();
        assert files != null;
        for (File file1 : files) {
            if (file1.isFile()) {
                System.out.println("文件名为:" + file1.getName());
            } else {
                getallfilename(file1);
            }
        }
    }

    //快速排序算法,返回标杆值所在的位置
    public static int sortUnit(int[] ints, int s, int e) {
        int num = ints[s]; //标杆值
        int i = s;//i为左循环位所在位置
        int j = e;//j为右循环为所在位置
        while (i < j) {
            //从右向左的循环
            while (i < j) {
                if (ints[j] < num) {
                    ints[i] = ints[j];
                    break;
                }
                j--;//继续向左边移动
            }
            while (i < j) {
                if (ints[i] >= num) {
                    ints[j] = ints[i];
                    break;
                }
                i++;
            }
        }
        ints[i] = num;
        return i;
    }

    public static void sort(int[] ints, int s, int e) {
        if (s < e) {
            int m = sortUnit(ints, s, e);
            sort(ints, s, m - 1);//左循环
            sort(ints, m + 1, e);//右循环
        }
    }




    //二叉树堆排序，使用数组表示
    public static void binarytreeheapsort(int[] ints) {
        int end = ints.length;
        while (end >= 3) { //意味着数组中至少还有一个父节点一个子节点，任然需要比较

            //数组的第一个数为0，不表示任何数，方便计算
            //循环次数为父节点个数=(数组长度L-1)/2
            for (int i = (end - 1) / 2; i >= 1; i--) {

                //假设最大儿子为左节点(是为了避免无右节点的情况，超出数组边界）
                int maxindex = i * 2;
                //如果右儿子存在，且右儿子的值大于左儿子，则最大儿子索引变成maxindex+1
                if (maxindex + 1 < end && ints[maxindex + 1] > ints[maxindex]) {
                    maxindex++;
                }
                //判断最大儿子和父节点谁大
                if (ints[maxindex] > ints[i]) {
                    int temp = ints[maxindex];
                    ints[maxindex] = ints[i];
                    ints[i] = temp;
                }
            }
            //将顶级父节点与最后一个节点替换，并剔除最后一个节点继续循环
            int a = ints[1];
            ints[1] = ints[end - 1];
            ints[end - 1] = a;
            end--;
        }
    }

    //不引入第三个值交换两个值
    public void exchangetwonum() {
        int a = 5;
        int b = 10;

        a = a + b;
        b = a - b;
        a = a - b;
    }

    //顺序二叉树
    class Node {
        int value;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        Node left;
        Node right;

    }

    class DoubleTree {
        Node root;

        public void add(int value) {
            Node Newnode = new Node(value);
            if (root == null) {
                root = Newnode;
            } else {
                Node temp = root;
                while (true) {
                    //小的在左边
                    if (value < temp.getValue()) {
                        if (temp.getLeft() == null) {
                            temp.setLeft(Newnode);
                            break;
                        } else {
                            temp = temp.getLeft();
                        }
                    } else {
                        //大的在右边
                        if (temp.getRight() == null) {
                            temp.setRight(Newnode);
                            break;
                        } else {
                            temp = temp.getRight();
                        }
                    }
                }
            }
        }

        //遍历节点
        public void show(Node node) {
            showNode(node);
        }

        //遍历方式
        private void showNode(Node node) {
            //System.out.println(node.value);//前序遍历
            //向左遍历
            if (node.getLeft() != null) {
                showNode(node.getLeft());
            }

            //System.out.println(node.value);//中序遍历

            //向右遍历
            if (node.getRight() != null) {
                showNode(node.getRight());
            }
            //System.out.println(node.value);//后序遍历
        }
    }


    //给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    //你可以假设数组中无重复元素。
    public static int searchInsert(int[] nums, int target) {
        if (target > nums[nums.length - 1]) {
            return nums.length;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (target <= nums[i]) {
                    return i;
                }
            }
        }

        return 0;
    }

    //你需要在原地删除重复出现的元素
    public static int removeDuplicates2(int[] nums) {
        int a = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[a]) {
                nums[a + 1] = nums[i];
                a++;
            }
        }

        return a + 1;
    }


    //给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
    public static int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        if (haystack.contains(needle)) {
            for (int i = 0; i < haystack.length(); i++) {
                if (haystack.substring(i).startsWith(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }


    //报数
    public String countAndSay(int n) {
        if (n == 1) {
            return String.valueOf(1);
        }
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (n > 1) {
            String a = countAndSay(n - 1);

            char[] newArray = getNewArray(a.toCharArray());

            char c = a.toCharArray()[a.length() - 1];

            for (int i = newArray.length - 1; i >= 0; i--) {
                if (newArray[i] != c) {
                    stringBuilder.append(c);
                    stringBuilder.append(count);
                    c = newArray[i];
                    count = 0;
                }
                count++;
            }
        }
        StringBuilder finalStrB = new StringBuilder();
        for (int i = stringBuilder.toString().length() - 1; i >= 0; i--) {
            finalStrB.append(stringBuilder.toString().toCharArray()[i]);
        }


        return finalStrB.toString();


    }

    public char[] getNewArray(char[] nums) {
        char[] newArray = new char[nums.length + 1];
        newArray[0] = '0';
        for (int i = 0; i < nums.length; i++) {
            newArray[i + 1] = nums[i];
        }
        return newArray;
    }


/*
给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。
 */

    public static int[] plus1(int[] ints) {

        int carry = 1;
        for (int j = ints.length - 1; j >= 0; j--) {
            ints[j] += carry;
            if (ints[j] > 9) {
                ints[j] = 0;
            } else {
                break;
            }
        }
        if (ints[0] == 0) {
            int[] rs = new int[ints.length + 1];
            rs[0] = 1;
            for (int i = 0; i < ints.length; i++) {
                rs[i + 1] = ints[i];
            }
            return rs;
        }
        return ints;
    }


    //two-sum
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> intergers = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (intergers.containsKey(target - nums[i])) {
                res[0] = intergers.get(nums[i]);
                res[1] = i;
                break;
            }
            intergers.put(nums[i], i);
        }
        return res;
    }

    // 移除元素
    public static int removeElement(int[] nums, int val) {
        int a = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[a] = nums[i];
                a++;
            }
        }
        return a;
    }

    //有效的括号,使用栈，先进后出
    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');

        Stack<Character> characters = new Stack<>();
        for (Character c : s.toCharArray()) {
            //判断是不是右括号
            if (map.containsKey(c)) {
                if (characters.empty()) {
                    return false;
                }
                //当右括号与栈顶元素不匹配时候，返回false
                if (characters.pop() != map.get(c)) {
                    return false;
                }
            } else { //未被匹配到的左括号将会被放到栈中
                characters.push(c);
            }
        }
        //如果不为空，说明还有尚未匹配到的左括号
        return characters.isEmpty();
    }

    //最大连续子数组和
    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int res = 0;
        int a = nums[0];
        for (int i = 0; i < nums.length; i++) {
            res = nums[i];
            sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                res = Math.max(res, sum);
            }
            a = Math.max(res, a);
        }
        return a;
    }

    //第二种解法
    public static int maxSubArray2(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res = Math.max(sum, res);
            if (sum < 0) {
                //相当于剔除前面的会让和的值变小的值
                sum = 0;
            }
        }
        return res;
    }

    //给定两个二进制字符串，返回他们的和（用二进制表示）。
    //输入为非空字符串且只包含数字 1 和 0。
    public static String addBinary(String a, String b) {
        //保证a是比较长的那个字符串
        if (a.length() < b.length()) {
            addBinary(b, a);
        }
        StringBuilder res = new StringBuilder();
        int carry = 0;
        int p = b.length();
        int c;
        for (int i = a.length() - 1; i >= 0; i--) {
            if (p > 0) {
                c = Integer.parseInt(String.valueOf(a.toCharArray()[i])) + Integer.parseInt(String.valueOf(b.toCharArray()[p - 1])) + carry;
                p--;
            } else {
                c = Integer.parseInt(String.valueOf(a.toCharArray()[i])) + carry;
            }
            if (c > 1) {
                carry = 1;
                res.insert(0, c - 2);
            } else {
                res.insert(0, c);
                carry = 0;
            }
        }
        if (carry == 1) {
            res.insert(0, '1');
        }

        return res.toString();
    }

    //中位数
    public static Double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //确保nums1是长度比较短的那个数组
        if (nums2.length < nums1.length) {
            findMedianSortedArrays(nums2, nums1);
        }
        int len = nums1.length + nums2.length;
        int cut1 = 0;  //cut1是数组一割点的左边个数
        int cut2 = 0;  //cut2是数组二割点左边个数
        //cutL和cutR是割点的范围
        int cutL = 0;
        int cutR = nums1.length;
        while (cut1 < nums1.length) {
            //默认选择切割点第一个数组中间
            cut1 = (cutR - cutL) / 2;
            cut2 = len / 2 - cut1;
            double L1 = (cut1 == 0) ? Integer.MAX_VALUE : nums1[cut1 - 1];
            double L2 = (cut2 == 0) ? Integer.MAX_VALUE : nums2[cut2 - 1];
            double R1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1];
            double R2 = (cut1 == 0) ? Integer.MIN_VALUE : nums2[cut2];

            if (L1 > R2) {
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                cutL = cut1 + 1;
            } else {
                if (len % 2 == 0) {
                    L1 = L1 > L2 ? L1 : L2;
                    R1 = R1 < R2 ? R1 : R2;
                    return (L1 + R1) / 2;
                } else {
                    R1 = (R1 < R2) ? R1 : R2;
                    return R1;
                }
            }
        }
        return Double.valueOf(-1);
    }

    //给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
//初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
//你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
/*
输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]
 */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (nums1[j] > nums2[i]) {
                    for (int k = m - 1; k >= j; k--) {
                        nums1[k + 1] = nums1[k];
                    }
                    nums1[j] = nums2[i];
                    m++;
                    break;
                } else if (j == m - 1) {
                    nums1[m] = nums2[i];
                    m++;
                    break;
                }

            }

        }
    }

    /*
    实现 int sqrt(int x) 函数。

    计算并返回 x 的平方根，其中 x 是非负整数。

    由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     */
    public static int mySqrt(int x) {
        double a = Math.sqrt(x);
        return (int) a;
    }

    /*
    给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。

    如果不存在最后一个单词，请返回 0 。

    说明 ：一个单词是指由字母组成，但不包含任何空格的字符串。
     */
    public static int lengthOfLastWord(String s) {
        String b = s.trim();
        if (b.length() == 0) {
            return 0;
        }

        for (int i = b.length() - 1; i > 0; i--) {
            if (b.toCharArray()[i] == ' ') {
                return b.length() - 1 - i;
            }
        }
        return b.length();
    }



    //将一个给定字符串根据给定的行数，以从上往下、从左到右进行 N 字形排列。
    //
    //比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
    //
    //L   C   I   R
    //E T O E S I I G
    //E   D   H   N


    //给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n
    // 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
    // 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    //解题思路：
    //最初我们考虑由最外围两条线段构成的区域。现在，为了使面积最大化，
    // 我们需要考虑更长的两条线段之间的区域。如果我们试图将指向较长线段的指针向内侧移动
    // ，矩形区域的面积将受限于较短的线段而不会获得任何增加。
    // 但是，在同样的条件下，移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大
    // 。因为移动较短线段的指针会得到一条相对较长的线段，这可以克服由宽度减小而引起的面积减小。
    public int maxArea(int[] height) {
        int maxArea = 0;
        int minHeight;
        for (int i = 0, j = height.length - 1; i < j; ) {
            minHeight = height[i] < height[j] ? height[i++] : height[j--];
            //j-i+1的原因是上一步无论怎样都有低高度的前进一位或者后退一位
            maxArea = Math.max(maxArea, (j - i + 1) * minHeight);
        }
        return maxArea;
    }

    public static int myAtoi(String str) {
        String a = str.trim();
        if (a.length() > 1) {
            //第一个是负号
            if (a.toCharArray()[0] == '-') {
                if (!Character.isDigit(a.toCharArray()[1])) {
                    return 0;
                } else {
                    for (int i = 1; i < a.length(); i++) {
                        if (!Character.isDigit(a.toCharArray()[i])) {
                            try {
                                return Integer.parseInt(a.substring(0, i));
                            } catch (Exception e) {
                                return Integer.MIN_VALUE;
                            }
                        }
                    }
                    try {
                        return Integer.parseInt(a.substring(0, a.length()));
                    } catch (Exception e) {
                        return Integer.MIN_VALUE;
                    }
                }
            } else if (!Character.isDigit(a.toCharArray()[0])) {
                if (a.toCharArray()[0] == '+') {
                    if (!Character.isDigit(a.toCharArray()[1])) {
                        return 0;
                    } else {
                        for (int i = 1; i < a.length(); i++) {
                            if (!Character.isDigit(a.toCharArray()[i])) {
                                try {
                                    return Integer.parseInt(a.substring(0, i));
                                } catch (Exception e) {
                                    return Integer.MAX_VALUE;
                                }
                            }
                        }
                        try {
                            return Integer.parseInt(a);
                        } catch (Exception e) {
                            return Integer.MAX_VALUE;
                        }
                    }

                }
                return 0;
            } else {
                for (int i = 0; i < a.length(); i++) {
                    if (!Character.isDigit(a.toCharArray()[i])) {
                        try {
                            return Integer.parseInt(a.substring(0, i));
                        } catch (Exception e) {
                            return Integer.MAX_VALUE;
                        }
                    }
                }
                try {
                    return Integer.parseInt(a);
                } catch (Exception e) {
                    return Integer.MAX_VALUE;
                }
            }
        } else if (str.length() == 0 || !Character.isDigit(str.toCharArray()[0])) {
            return 0;
        } else {
            return Integer.parseInt(str);
        }
    }

    public static int myAtoi2(String str) {
        str = str.trim();
        if (str == null || str.length() == 0) {
            return 0;
        }

        int sign = 1;
        int start = 0;
        long res = 0;
        char a = str.charAt(0);
        if (a == '+') {
            start++;
        } else if (a == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            res = res * 10 + str.charAt(i) - '0';
            if (sign == 1 && res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (sign == -1 && res > Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return (int) res * sign;
    }

    public static String intToRoman(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        map.put(4, "IV");
        map.put(9, "IX");
        map.put(40, "XL");
        map.put(90, "XC");
        map.put(400, "CD");
        map.put(900, "CM");

        String a = String.valueOf(num);
        int len = a.length();
        //千位数
        StringBuilder str = new StringBuilder();
        while (len > 0) {
            for (int i = 0; i < a.length(); i++) {
                int u = Integer.parseInt(String.valueOf(a.toCharArray()[i]));
                Integer x = (int) Math.pow(10, len - 1);
                //当位上的值小于四的时候
                if (u < 4) {
                    if (u == 0) {
                        len--;
                    } else {
                        for (int j = 0; j < u; j++) {
                            str.append(map.get(x));
                        }
                        len--;
                    }
                } else if (u == 4) {
                    str.append(map.get(x * 4));
                    len--;
                } else if (u < 9) {
                    str.append(map.get(x * 5));
                    for (int j = 0; j < u - 5; j++) {
                        str.append(map.get(x));
                    }
                    len--;
                } else if (u == 9) {
                    str.append(map.get(x * 9));
                    len--;
                }
            }

        }

        return str.toString();
    }

    public String intToRoman2(int num) {
        int values[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String reps[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = null;
        for (int i = 0; i < 13; i++) {
            while (num >= values[i]) {
                num -= values[i];
                res.append(reps[i]);
            }
        }
        return res.toString();


    }

    public boolean isPalindrome(int x) {
        if (x < 0 || x != 0 && x % 10 == 0) {
            return false;
        }
        int palind = x;
        int rev = 0;

        while (x > 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return palind == rev;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) return false;
        int div = 1, num = x;
        while (x / div >= 10) {
            div *= 10;
        }
        while (num > 0) {
            int left = num / div;
            int right = num % 10;
            if (left != right) return false;
            num = (num - left * div) / 10;
            div /= 100;
        }
        return true;
    }

    //给定一个没有重复数字的序列，返回其所有可能的全排列。
    /*
    输入: [1,2,3]
    输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length == 0) {
            return lists;
        }
        if (nums.length == 1) {
            List<Integer> integer = new ArrayList<>();
            integer.add(nums[0]);
            lists.add(integer);
            return lists;
        }
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            nums[i] = nums[0];
            nums[0] = a;
            int[] nums2 = new int[nums.length - 1];
            for (int j = 1; j < nums.length; j++) {
                nums2[j - 1] = nums[j];
            }
            List<List<Integer>> permute = permute(nums2);
            for (List<Integer> integerList : permute) {
                integerList.add(0, a);
            }
            lists.addAll(permute);
        }
        return lists;
    }

//    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
//    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.equals("")) {
            return new ArrayList<>();
        }
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        String s = map.get(Integer.parseInt(String.valueOf(digits.charAt(0))));
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            strings.add(String.valueOf(s.charAt(i)));
        }
        if (digits.length() == 1) {
            return new ArrayList<>(strings);
        }

        for (int i = 1; i < digits.length(); i++) {
            String s1 = map.get(Integer.parseInt(String.valueOf(digits.charAt(i))));
            List<String> stringList = new ArrayList<>(strings);
            strings.removeAll(strings);
            for (int j = 0; j < stringList.size(); j++) {
                for (int z = 0; z < s1.length(); z++) {
                    String b = stringList.get(j) + s1.charAt(z);
                    strings.add(b);
                }
            }
        }
        return new ArrayList<>(strings);
    }

    /*
    给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
     */
    public static List<String> generateParenthesis(int n) {
        Set<String> result = new HashSet<>();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 2 * n; i++) {
            if (i < n) {
                strings.add("(");
            } else {
                strings.add(")");
            }
        }
        //生成所有有可能的情况
        int[] nums = new int[2 * n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        List<List<Integer>> permute = permute(nums);
        for (List<Integer> integers : permute) {
            List<String> stringList = strings;
            for (Integer i : integers) {
                //与运算判断是否为偶数
                if ((i & 1) == 0) {
                    stringList.set(i, "(");
                } else {
                    stringList.set(i, ")");
                }
            }
            //判断当前是否闭合正确
            StringBuilder sb = new StringBuilder();
            stringList.forEach(sb::append);
            if (isValid(sb.toString())) {
                result.add(sb.toString());
            }

        }
        return new ArrayList<>(result);
    }


    /*
    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    示例：
    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807
     */

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3;
        int step = 0;
        while (true) {
            list1.add(l1.val);
            if (l1.next != null) {
                l1 = l1.next;
            } else {
                break;
            }
        }
        while (true) {
            list2.add(l2.val);
            if (l2.next != null) {
                l2 = l2.next;
            } else {
                break;
            }
        }
        if (list1.size() > list2.size()) {
            list3 = list1;
        } else {
            list3 = list2;
        }
        for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
            int sum = list1.get(i) + list2.get(i) + step;
            step = sum / 10;
            list3.set(i, sum % 10);
        }
        //将list3后面的数累加0
        for (int i = Math.min(list1.size(), list2.size()); i < list3.size(); i++) {
            int a = list3.get(i) + step;
            list3.set(i, a % 10);
            step = a / 10;
        }
        if (step > 0) {
            list3.add(step);
        }
        //将list构造成一个listNode
        ListNode result = new ListNode(0);
        ListNode next = new ListNode(0);
        result.next = next;
        for (Integer i : list3) {
            next.next = new ListNode(i);
            next = next.next;
        }
        return result.next.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode res = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int temp = num1 + num2 + carry;
            carry = temp / 10;
            res.next = new ListNode(temp % 10);
            res = res.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            res.next = new ListNode(carry);
        }
        return head.next;
    }

    /*
    给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    示例:
    给定 1->2->3->4, 你应该返回 2->1->4->3.
     */
    private static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    /*
     给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
    注意：答案中不可以包含重复的三元组。
    例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
    满足要求的三元组集合为：
    [[-1, 0, 1],[-1, -1, 2] ]
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        if (nums.length <= 3) {
            if ((nums[0] + nums[1] + nums[2]) == 0) {
                result.add(Arrays.asList(nums[0], nums[1], nums[2]));
            } else {
                return new ArrayList<>();

            }

        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > 0) {
                break;
            }
            int l = i + 1;
            int r = nums.length - 1;
            int sum;
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                } else if (sum < 0) l++;
                else r--;
            }
        }
        return new ArrayList<>(result);
    }


    /*
    给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    示例 1：
    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。
    示例 2：
    输入: "cbbd"
    输出: "bb"
     */
    public String longestPalindromeByLeeCode(String s) {
        int start = 0;
        int end = 0;
        int len;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAround(s, i, i);
            int len2 = expandAround(s, i, i + 1);
            len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAround(String s, int l, int r) {
        int L = l, R = r;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    class Foo {
        private volatile Integer flag = 1;

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            while (flag != 1) {

            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            flag = 2;
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (flag != 2) {
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            flag = 3;
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (flag != 3) {
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            flag = 1;
        }
    }

    /*你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
给定 matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],
原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
*/
    public static void rotate(int[][] matrix) {

        for (int j = 0; j < matrix.length / 2; j++) {
            int a;
            for (int i = j + 1; i < matrix.length - j; i++) {
                a = matrix[matrix.length - 1 - i][j];
                matrix[matrix.length - 1 - i][j] = matrix[matrix.length - j - 1][matrix.length - 1 - i];
                matrix[matrix.length - j - 1][matrix.length - 1 - i] = matrix[i][matrix.length - j - 1];
                matrix[i][matrix.length - j - 1] = matrix[j][i];
                matrix[j][i] = a;
            }
        }
//        for (int j = 0; j < matrix.length / 2; j++) {
//            int a, b;
//            int d = matrix.length -  j;
//            for (int i = j + 1; i < d; i++) {
//                //ABOVE
//                a = matrix[j][i];
//                //RIGHT
//                b = matrix[i][d- 1];
//                matrix[i][d - 1] = a;
//                //BELOW
//                a = matrix[d - 1][matrix.length - 1 - i];
//                System.out.println("a--->"+a);
//                matrix[d - 1][matrix.length - 1 - i] = b;
//                //LEFT
//                b = matrix[matrix.length  - 1 - i][j];
//                matrix[matrix.length  - 1 - i][j] = a;
//                //ABOVE
//                matrix[j][i] = b;
//            }
//        }
    }

/*
小Q定义了一种数列称为翻转数列:
给定整数n和m, 满足n能被2m整除。对于一串连续递增整数数列1, 2, 3, 4..., 每隔m个符号翻转一次, 最初符号为'-';。
例如n = 8, m = 2, 数列就是: -1, -2, +3, +4, -5, -6, +7, +8.
而n = 4, m = 1, 数列就是: -1, +2, -3, + 4.
小Q现在希望你能帮他算算前n项和为多少。
输入描述:
输入包括两个整数n和m(2 <= n <= 109, 1 <= m), 并且满足n能被2m整除。
输出描述:
输出一个整数, 表示前n项和。
输入例子1:
8 2
输出例子1:
8
  Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = a % 2 * b;
            if (c != 0) {
                return;
            }
            System.out.println((a*b)/2);
            break;
        }
 */

    /*
    牛牛和羊羊正在玩一个纸牌游戏。这个游戏一共有n张纸牌, 第i张纸牌上写着数字ai。
    牛牛和羊羊轮流抽牌, 牛牛先抽, 每次抽牌他们可以从纸牌堆中任意选择一张抽出, 直到纸牌被抽完。
    他们的得分等于他们抽到的纸牌数字总和。
    现在假设牛牛和羊羊都采用最优策略, 请你计算出游戏结束后牛牛得分减去羊羊得分等于多少。
     */
    private static int extractCards(int n) {
        return 0;
    }

    private static int maxiProfit(int[] prices) {
        if (prices.length < 1) {
            return 0;
        }
        int sum = 0;
        int min = prices[0], max = 0;
        for (int price : prices) {
            max = Math.max(max, price - min);
            if (max > 0) {
                //卖出
                sum += max;
                max = 0;
                min = price;
            } else {
                min = Math.min(min, price);
            }
        }
        return sum;
    }

    /*
    将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
    示例：
    输入：1->2->4, 1->3->4
    输出：1->1->2->3->4->4
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        //当其中任意一个为空的时候
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return dummyHead.next;
    }


    /*
           给定一个二叉树，找出其最大深度。
       二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
       说明: 叶子节点是指没有子节点的节点。
       示例：
       给定二叉树 [3,9,20,null,null,15,7]，
           3
          / \
         9  20
           /  \
          15   7
            */

    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */

    class MinStack {
        private Stack<Integer> integerStack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            integerStack = new Stack<>();
        }

        public void push(int x) {
            if (integerStack.isEmpty()) {
                integerStack.push(x);
                integerStack.push(x);
            } else {
                //获取栈中的最小值
                int temp = integerStack.peek();
                integerStack.push(x);
                if (x < temp) {
                    integerStack.push(x);
                } else {
                    integerStack.push(temp);
                }
            }
        }

        public void pop() {
            integerStack.pop();
            integerStack.pop();
        }

        public int top() {
            return integerStack.get(integerStack.size() - 2);
        }

        public int getMin() {
            return integerStack.peek();
        }
    }

    /*
        给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
        示例 1:
        输入: num1 = "2", num2 = "3"
        输出: "6"
        示例 2:
        输入: num1 = "123", num2 = "456"
        输出: "56088"
     */
    //todo 字符串乘法,未完成
    public static String multiply(String num1, String num2) {
        return null;
    }


    /*
    给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
        示例 1:
        输入: 1
        输出: true
        解释: 20 = 1
        示例 2:
        输入: 16
        输出: true
        解释: 24 = 16
        示例 3:
        输入: 218
        输出: false
     */
    public static boolean isPowerOfTwo(int n) {
        if (n % 2 == 0 && n / 2 > 1) {
            return isPowerOfTwo(n / 2);
        }
        if (n == 2 || n == 1) {
            return true;
        }
        return false;
    }

    public static boolean isPowerOfTwo2(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        levelMaker(lists, root, 0);
        Collections.reverse(lists);
        return lists;
    }

    private static void levelMaker(List<List<Integer>> lists, TreeNode root, int level) {
        if (root == null) return;
        if (level >= lists.size()) {
            lists.add(new ArrayList<>());
        }
        levelMaker(lists, root.left, level + 1);
        levelMaker(lists, root.right, level + 1);
        lists.get(level).add(root.val);
    }

    //    输入: head = [4,5,1,9], node = 5
//    输出: [4,1,9]
//    解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
    private static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        if (head == null) {
            return null;
        }
        while (head.next != null) {
            if (head.val == head.next.val) {
                if (head.next.next != null) {
                    head.next = head.next.next;
                } else {
                    head.next = null;
                    break;
                }
            }
            if (head.val != head.next.val) {
                head = head.next;
            }
        }
        return dummyHead.next;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    public static int titleToNumber(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum = sum * 26 + (s.charAt(i) - 'A' + 1);
        }
        return sum;
    }

    //获取中位数
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /*
      反转一个单链表。
      *示例:
      *输入: 1->2->3->4->5->NULL
      *输出: 5->4->3->2->1->NULL
      *进阶:
      *你可以迭代或递归地反转链表。你能否用两种方法解决这道题
       */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp;
        ListNode result = null;
        while (head != null) {
            temp = head;
            head = head.next;
            temp.next = result;
            result = temp;
        }
        return result;
    }

    private static boolean hasCycle(ListNode head) {

        return false;
    }

    /*
    给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
    示例 1:
    输入: "Let's take LeetCode contest"
    输出: "s'teL ekat edoCteeL tsetnoc" 
    注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格
     */
    private static String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        String[] ss = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String a : ss) {
            stringBuilder.append(new StringBuilder(a).reverse()).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    private static void exChangeTwoNums(int a, int b) {
//        a = a - b;
//        b = b + a;
//        a = b - a;

    }


    /**
     * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,3,4]
     * 输出: [24,12,8,6]
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * <p>
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     */
    public static int[] productExceptSelf(int[] nums) {
        if (nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] outPut = new int[len];
        int[] leftTemp = new int[len];
        leftTemp[0] = 1;
        int[] rightTemp = new int[len];
        rightTemp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            leftTemp[i] = nums[i - 1] * leftTemp[i - 1];
            rightTemp[i] = nums[len - i] * rightTemp[i - 1];
        }
        for (int i = 0; i < outPut.length; i++) {
            outPut[i] = leftTemp[i] * rightTemp[len - 1 - i];
        }
        return outPut;
    }

    public static int[] productExceptSelf2(int[] nums) {
        if (nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] outPut = new int[len];
        outPut[0] = 1;
        int left = 1, right = 1;
        for (int i = 0; i < nums.length; ++i) {
            outPut[i] *= left;
            left *= nums[i];

            outPut[len - 1 - i] *= right;
            right *= nums[len - 1 - i];
            System.out.println("left->" + left + "right->" + right);
        }
        return outPut;
    }

    /**
     * 给定一个字符串s，你可以从中删除一些字符，使得剩下的串是一个回文串。如何删除才能使得回文串最长呢？
     * 输出需要删除的字符个数。
     *
     * @param str
     */
    private static String buildReacts(String str) {

        return "";
    }
    /*
    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    示例:
    输入: [0,1,0,3,12]
    输出: [1,3,12,0,0]
    说明:
    必须在原数组上操作，不能拷贝额外的数组。
    尽量减少操作次数。
     */

    public static void moveZeroes(int[] nums) {
        outer:
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                for (int j = nums.length - 1; j >= 0; j--) {
                    if (nums[j] != 0) {
                        if (j == i - 1) {
                            break outer;
                        }
                        System.arraycopy(nums, i + 1, nums, i, j - i);
                        nums[j] = 0;
                        i--;
                        break;
                    }
                }
            }
        }
    }

    /*
    你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     示例 1:
     输入: [1,2,3,1]
     输出: 4
     解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
          偷窃到的最高金额 = 1 + 3 = 4 。
     示例 2:
     输入: [2,7,9,3,1]
     输出: 12
     解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
          偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     */
    public int rob(int[] nums) {

        return 0;
    }


    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode left, TreeNode right) {
        //最低点
        if (left == null && right == null) return true;
        //左右一个为空一个不为空的情况
        if (left == null || right == null) return false;
        //对称点都不为空
        return (left.val == right.val) && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : getTreeNode(nums, 0, nums.length - 1);
    }

    private TreeNode getTreeNode(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = getTreeNode(nums, start, mid);
        root.left = getTreeNode(nums, mid + 1, end);
        return root;
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }

    public static void f1(List<Integer> list) {
        Collections.sort(list);
        HashSet<Integer> set = new HashSet<>(list);
        set.forEach(System.out::println);
    }


    public static char charAt(String s, int n, int m) {
        if (n == 1) return s.charAt(m);
        char first = charAt(s, n / 2, m * 2);
        char second = charAt(s, n / 2, m * 2 + 1);
        System.out.print(first + "-" + second + "-");
        return first;
    }

    /*
    将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int p = numRows * 2 - 2;
        int a = s.length() / p;
        int b = s.length() % p;
        if (b != 0) {
            a += 1;
        }
        int count = 0;
        int count2 = 0;
        Character[][] cs = new Character[a * (numRows - 1)][numRows];
        for (int i = 0; i < a * (numRows - 1); i++) {
            if (i == 0 || i % (numRows - 1) == 0) {
                for (int j = 0; j < numRows; j++) {
                    cs[i][j] = s.toCharArray()[count];
                    count++;
                    if (count == s.length()) {
                        break;
                    }
                }
                count2++;
            } else {
                if (count == s.length()) {
                    break;
                }
                int f = numRows - 1 - (i - (numRows - 1) * (count2 - 1));
                cs[i][f] = s.toCharArray()[count];
                count++;
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < a * (numRows - 1); j++) {
                if (cs[j][i] != null) {
                    stringBuilder2.append(cs[j][i]);
                }
            }
        }
        return stringBuilder2.toString();
    }

    public static String convert2(String s, int numRows) {
//        if (numRows==1) return s;
        int curRow = 0;
        boolean goDown = false;
        StringBuilder res = new StringBuilder();
        StringBuilder[] stringBuilders = new StringBuilder[s.length() < numRows ? s.length() : numRows];
        for (int i = 0; i < stringBuilders.length; i++) {
            stringBuilders[i] = new StringBuilder();
        }
        for (int i = 0; i < s.length(); i++) {
            stringBuilders[curRow].append(s.charAt(i));
            if (curRow == 0 || curRow == stringBuilders.length - 1) goDown = !goDown;
            curRow += goDown ? 1 : -1;
        }
        for (StringBuilder stringBuilder : stringBuilders) {
            res.append(stringBuilder.toString());
        }
        return res.toString();
    }

    public static int superpalindromesInRange(String L, String R) {
        long a1 = Long.parseLong(L);
        long a2 = (long) Math.sqrt((double) a1);
        long b1 = Long.parseLong(R);
        long b2 = (long) Math.sqrt((double) b1);
        int count = 0;
        for (long i = a2; i < b2; i++) {
            if (isPalinromicNum(i) && isPalinromicNum(i * i)) {
                System.out.println(i);
                count++;
            }
        }
        return count;

    }

    public static boolean isPalinromicNum(long num) {
        long a = num;
        int dev = 1;
        while (a >= 10) {
            a /= 10;
            dev *= 10;
        }
        while (num > 0) {
            long l = num / dev;
            long r = num % 10;
            if (r != l) return false;
            num = num % dev;
            num = num / 10;
            dev /= 100;
        }
        return true;
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]).append(" ");
            if (sb.length() - 1 > maxWidth) {
                sb.delete(sb.length() - 1 - words[i].length(), sb.length() - 1);
                res.add(sb.toString());
                sb = new StringBuilder();
                --i;
            } else if (sb.length() - 1 == maxWidth) {
                res.add(sb.toString());
            } else if (i == words.length - 1) {
                res.add(sb.toString());
            }
        }
        for (int i = 0; i < res.size() - 1; i++) {
            String[] ss = res.get(i).split(" ");
            int eachsize = 0;
            for (String s : ss) {
                eachsize += s.length();
            }
            int count = ((maxWidth - eachsize) / ((ss.length - 1) == 0 ? 1 : ss.length - 1));
            int b = 0;
            if (ss.length != 1) {
                b = ((maxWidth - eachsize) % (ss.length - 1));
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < ss.length - 1; j++) {
                if (j == 0) {
                    stringBuilder.append(ss[j]);
                    for (int k = 0; k < b + count; k++) {
                        stringBuilder.append(" ");
                    }
                } else {
                    stringBuilder.append(ss[j]);
                    for (int k = 0; k < count; k++) {
                        stringBuilder.append(" ");
                    }
                }
            }
            stringBuilder.append(ss[ss.length - 1]);
            res.set(i, stringBuilder.toString());
        }
        return res;
    }

    public static int minDepth2(TreeNode treeNode) {
        if (maxDepth(treeNode) == 2) {
            return 2;
        }
        return treeNode == null ? 0 : Math.min(minDepth2(treeNode.left), minDepth2(treeNode.right)) + 1;
    }


    public static int change(List<Integer> integers) {
        int temp1 = 0;
        for (Integer integer : integers) {
            if (integer > 0) {
                temp1 = integer;
            }
        }
        for (Integer integer : integers) {
            if (integer > 0 && integer < temp1) temp1 = integer;

        }
        for (int i = 0; i < integers.size(); i++) {
            integers.set(i, integers.get(i) - temp1);
        }
        return temp1;
    }

    public static boolean isPalindrome(String s) {
        if (s.equals("")) {
            return true;
        }
        String str = s;
        StringBuilder sb = new StringBuilder();
        for (char c : str.toLowerCase().toCharArray()) {
            if (c >= 97 && c <= 122 || c >= 48 && c <= 57) {
                sb.append(c);
            }
        }
        if (sb.length() <= 1) {
            return true;
        }
        str = sb.toString();
        for (int i = 0; i < str.length() / 2 + 1; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static int hammingWeight(int n) {
        int res = 0;
        while (n > 0) {
            res += n % 2;
            n >>= 1;
        }
        Integer.bitCount(n);
        return res;
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(temp);
        return root;
    }

    static List<String> res;

    public static List<String> binaryTreePaths(TreeNode root) {
        res = new ArrayList<>();
        m1(root, "");
        return res;
    }

    public static void m1(TreeNode root, String string) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            string += "->" + root.val;
            res.add(string.substring(2));
        } else {
            string += "->" + root.val;
            //判断是否为页子节点
            if (root.left != null) m1(root.left, string);
            if (root.right != null) m1(root.right, string);
        }
    }

    public static int trailingZeroes(int n) {

        return 0;
    }

    /*
    给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     */
    public static void rotate(int[] nums, int k) {
        int temp;
        for (int i = 0; i < k; i++) {
            temp = nums[nums.length - 1];
            if (nums.length - 1 >= 0) System.arraycopy(nums, 0, nums, 1, nums.length - 1);
            nums[0] = temp;
        }
    }

    /*
    给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     例如:
     给定二叉树: [3,9,20,null,null,15,7],
         3
        / \
       9  20
         /  \
        15   7
     返回其层次遍历结果：
     [
       [3],
       [9,20],
       [15,7]
     ]
     */
    static int count = 0;
    static int Md = 0;
    static List<List<Integer>> result;

    public static List<List<Integer>> levelOrder(TreeNode root) {
        result = new ArrayList<>();
        Md = MaxDepth(root);
        for (int i = 0; i < Md; i++) {
            result.add(new ArrayList<>());
        }
        f1(root);
        return result;
    }

    public static int MaxDepth(TreeNode root) {
        return root == null ? 0 : (Math.max(MaxDepth(root.left), MaxDepth(root.right)) + 1);
    }

    public static TreeNode f1(TreeNode root) {
        if (root == null) {
            return null;
        }
        result.get(count).add(root.val);
        count = Md - MaxDepth(root) + 1;
        root.left = f1(root.left);
        root.right = f1(root.right);
        return root;
    }


    public boolean containsDuplicate(int[] nums) {
        return Arrays.stream(nums).distinct().count() == nums.length;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return String.valueOf(a).equals(String.valueOf(b));
    }

    // 0 1 2 4
    public static int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
            res ^= i;
        }
        return res;
    }

    /*
    1 0 1 1  1 0 0 1
    0 1 1 0  0 0 1 0
     */
    public static int getSum(int a, int b) {
        if (b == 0) return a;
        int ab = a ^ b;
        int carry = a & b;
        return getSum(ab, carry);
    }

    public int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) return i;
        }
        return -1;
    }

    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 15 == 0) {
                res.add("FizzBuzz");
                continue;
            } else if (i % 3 == 0) {
                res.add("Fizz");
                continue;
            } else if (i % 5 == 0) {
                res.add("Buzz");
                continue;
            }
            res.add(String.valueOf(i));
        }

        return res;
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int i : nums1) {
            map1.merge(i, 1, (a, b) -> a + b);
        }
        for (int i : nums2) {
            map2.merge(i, 1, (a, b) -> a + b);
        }
        List<Integer> integers = new ArrayList<>();
        map1.keySet().forEach(
                i -> {
                    if (map2.get(i) != null) {
                        for (int k = 0; k < Math.min(map1.get(i), map2.get(i)); k++) {
                            integers.add(i);
                        }
                    }
                }
        );
        int[] res = new int[integers.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = integers.get(i);
        }
        return res;
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> integers = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                integers.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[integers.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = integers.get(i);
        }
        return res;
    }

    public static boolean wordPattern(String pattern, String str) {
        if (pattern.length() != str.split(" ").length) return false;
        char[] chars = pattern.toCharArray();
        String[] s = str.split(" ");
        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
            String temp = map.get(chars[i]);
            //判断当前的s[i]是都已经有对应的key
            if (temp == null && map.values().contains(s[i])) return false;
            if (temp == null) {
                map.put(chars[i], s[i]);
            } else if (!temp.equals(s[i])) return false;
        }
        return true;
    }

    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && ((n & 0x55555555) == n);
    }


    public static TreeNode buildTree(int[] num1, int[] num2) {
        if (num1.length == 0) return null;
        int a = num1[0];
        //获取a在num2中的位置
        int indexAInNum2 = 0;
        for (int i = 0; i < num2.length; i++) {
            if (a == num2[i]) {
                indexAInNum2 = i;
                break;
            }
        }
        //构造当前节点
        TreeNode root = new TreeNode(a);
        //分配到左节点的按先序排列的数组
        int[] tema = new int[indexAInNum2];
        //分配到右节点的按先序排列的数组
        int[] temb = new int[num1.length - 1 - tema.length];
        System.arraycopy(num1, 1, tema, 0, tema.length);
        System.arraycopy(num1, indexAInNum2 + 1, temb, 0, temb.length);
        //分配到左边的按照中序排列的数组
        int[] temL = new int[indexAInNum2];
        //分配到右边的按照中序排列的数组
        int[] temR = new int[num2.length - 1 - temL.length];
        System.arraycopy(num2, 0, temL, 0, temL.length);
        System.arraycopy(num2, indexAInNum2 + 1, temR, 0, temR.length);

        root.left = buildTree(tema, temL);
        root.right = buildTree(temb, temR);
        return root;
    }


    public static void main(String[] args) {
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * 使用队列实现栈的下列操作：
 * <p>
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 */
class MyStack {

    private LinkedList<Integer> integers;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        integers = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        integers.add(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return integers.removeLast();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return integers.getLast();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return integers.isEmpty();
    }
}
