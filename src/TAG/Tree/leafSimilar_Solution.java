package TAG.Tree;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class leafSimilar_Solution {
    // 1.分别先序遍历得到序列，比较序列
    public boolean leafSimilar1(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = getLeaves(root1);
        List<Integer> leaves2 = getLeaves(root2);
        int size = leaves1.size();
        if(size != leaves2.size()){
            return false;
        }
        /* 判断两个长度相同的list是否完全相等
        // solution 1
        for(int i = 0; i < size; i++){
            if(leaves1.get(i) != leaves2.get(i))
                return false;
        }
        return true;
        */
        // solution 2
        return leaves1.equals(leaves2);
    }
    // 2.分别遍历得到一个叶子节点，判断是否相等；利用栈可以做到
    public boolean leafSimilar2(TreeNode root1, TreeNode root2) {
        // if(root1 == null && root2 == null)
        //     return true;
        // else if(root1 != null && root2 == null || root2 != null && root1 == null)
        //     return false;
        Stack<TreeNode> s1 = new Stack<>(), s2 = new Stack<>();
        s1.push(root1); s2.push(root2);
        while(!s1.isEmpty() && !s2.isEmpty()) {
            if(dfs(s1) != dfs(s2))
                return false;
        }
        return s1.isEmpty() && s2.isEmpty();
    }
    public int dfs(Stack<TreeNode> s) {
        while(true) {
            TreeNode root = s.pop();
            if(root.left != null)
                s.push(root.left);
            if(root.right != null)
                s.push(root.right);
            // 返回叶子节点
            if(root.left == null && root.right == null)
                return root.val;
        }
    }
    List getLeaves(TreeNode root) {
        List<Integer> leaves = new ArrayList<>();
        if(root != null){
            forEach(root, leaves);
        }
        Integer[] xx = leaves.toArray(new Integer[leaves.size()]);
        return leaves;
    }
    void forEach(TreeNode root, List leaves){
        if(root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }
        if(root.left != null) {
            forEach(root.left, leaves);
        }
        if(root.right != null) {
            forEach(root.right, leaves);
        }
    }
    TreeNode listToTree(List<Integer> list) {
        if(list == null) {
            return null;
        }
        TreeNode res = new TreeNode(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i) != null) {

            }
        }
        return res;
    }
    public static void main(String[] args) {

    }
}
