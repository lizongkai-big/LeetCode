package TAG.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
public class preOrder_Solution {
    // DFS
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        // recrusionPreOrder(root, res);
        iterationPreOrder(root, res);

        return res;
    }
    void iterationPreOrder(Node root, List<Integer> res) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node tmp = stack.pop();
            res.add(tmp.val);
            List<Node> children = tmp.children;
            int size = children.size();
            for(int i = size-1; i >= 0; i --) {
                stack.push(children.get(i));
            }
        }
    }
    void recrusionPreOrder(Node root, List<Integer> list) {
        if(root == null) {
            return;
        }
        list.add(root.val);
        List<Node> children = root.children;
        int size = children.size();
        for(int i = 0; i < size; i ++) {
            recrusionPreOrder(children.get(i), list);
        }
    }
}
