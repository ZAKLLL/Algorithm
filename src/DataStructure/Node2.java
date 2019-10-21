package DataStructure;

import java.util.List;

/**
 * @program: suanfa
 * @description:
 * @author: ZakL
 * @create: 2019-10-21 20:29
 **/
public class Node2 {
    public int val;
    public List<Node2> children;

    public Node2() {
    }

    public Node2(int _val, List<Node2> _children) {
        val = _val;
        children = _children;
    }
};
