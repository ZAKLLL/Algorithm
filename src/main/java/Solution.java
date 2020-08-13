import java.util.*;

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

    //给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
    private TrieNode root;

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList();
        root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            int m = words[i].length();
            String s = words[i];
            //存在“”的情况
            if (s.length() > 0 && root.pos != -1) {
                if (isPalindrome(s, 0, s.length() - 1)) {
                    ret.add(Arrays.asList(i, root.pos));
                }
            }
            for (int j = 0; j < m; j++) {
                int pos = rFind(s, 0, j);
                if (pos != -1 && pos != i && isPalindrome(s, j + 1, s.length() - 1)) ret.add(Arrays.asList(i, pos));
                pos = rFind(s, j + 1, s.length() - 1);
                if (pos != -1 && pos != i && isPalindrome(s, 0, j)) ret.add(Arrays.asList(pos, i));
            }
        }
        return ret;
    }

    public void insert(String word, int pos) {
        if (word.length() == 0) root.pos = pos;
        char[] chars = word.toCharArray();
        int index;
        TrieNode node = root;
        for (char aChar : chars) {
            index = aChar - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
        }
        node.pos = pos;
    }

    public int rFind(String word, int s, int e) {

        int index;
        TrieNode node = root;
        for (int i = e; i >= s; i--) {
            index = word.charAt(i) - 'a';
            if (node.nexts[index] == null) return -1;
            node = node.nexts[index];
        }
        return node.pos;
    }


    class TrieNode {
        public int pos; //是否存在字符串以该节点结尾
        public TrieNode[] nexts; //当前点的儿子节点字母

        public TrieNode() {
            pos = -1;
            nexts = new TrieNode[26];
        }
    }

    public boolean isPalindrome(String s, int l, int r) {
        if (l == r) return true;
        int len = r - l + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(l + i) != s.charAt(r - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"a", ""};
        List<List<Integer>> lists = new Solution().palindromePairs(strs);
        for (List<Integer> list : lists) {
            System.out.println(list.get(0) + "--" + list.get(1));
        }
    }
}



