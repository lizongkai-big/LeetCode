package utils;

import java.util.Stack;
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public void Mirror(TreeNode root) {
        root = getMirror(root);
    }

    private TreeNode getMirror(TreeNode node) {
        if(node == null) return null;
        TreeNode tmp = node.left;
        node.left = getMirror(node.right);
        node.right = getMirror(tmp);
        return node;
    }

    public static void leftTraverse(TreeNode node) {
        if(node == null) return;
        leftTraverse(node.left);
        System.out.print(node.val + " ");
        leftTraverse(node.right);
    }

    public static void leftTraverseIterative(TreeNode node) {
        if(node == null) return;
        Stack<TreeNode> stack = new Stack();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if(!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.val + " ");
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(9);
        TreeNode rootLeft = new TreeNode(7);
        rootLeft.left = new TreeNode(6);
        rootLeft.right = new TreeNode(8);
        TreeNode rootRight = new TreeNode(11);
        rootRight.left = new TreeNode(10);
        rootRight.right = new TreeNode(12);
        root.left = rootLeft;
        root.right = rootRight;
        leftTraverseIterative(root);
        System.out.println();
        TreeNode main = new TreeNode(-1);
        main.Mirror(root);
        leftTraverse(root);
    }
}
