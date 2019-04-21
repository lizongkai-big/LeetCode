package TAG.Tree;


import utils.TreeNode;

import java.util.Stack;

public class _572__Subtree_of_Another_Tree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        // 前序遍历
        return traverse(s, t);
    }

    public boolean equals(TreeNode s, TreeNode t) {
        if(s == null && t == null)
            return true;
        // 当前题意是s有一个t的子树，完全包含，所以匹配得较为严格，两者有一个为空即为false；
        // 如果是剑指offer上所说的那种部分包含t的情况，t==null 是true的，
        // 但是t!=null && s==null，是false，因为相当于s不包含t
        if(s == null || t == null)
            return false;
        return s.val == t.val &&
                equals(s.left, t.left) &&
                equals(s.right, t.right);
    }

    public boolean traverse(TreeNode s, TreeNode t) {
        return  s!=null && (equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
    }
}
