package DataStructure;

import java.util.List;

/**
 * @program: suanfa
 * @description:
 * @author: ZakL
 * @create: 2019-10-21 20:29
 **/
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

