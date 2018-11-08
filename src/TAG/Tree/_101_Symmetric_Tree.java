package TAG.Tree;

import utils.TreeNode;

/**
 * Given a binary tree,
 * check whether it is a mirror of itself (ie, symmetric around its center).
 */
public class _101_Symmetric_Tree {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSymmetricHelper(root.left, root.right);
    }

    public boolean isSymmetricHelper(TreeNode node1, TreeNode node2) {
        if(node1 == null || node2 == null) return node1 == node2;
        if(node1.val != node2.val) return false;
        return isSymmetricHelper(node1.left, node2.right) && isSymmetricHelper(node1.right, node2.left);
    }
}
