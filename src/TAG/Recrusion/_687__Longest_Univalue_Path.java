package TAG.Recrusion;

import utils.TreeNode;

public class _687__Longest_Univalue_Path {
    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        getPath(root);
        return max;
    }

    public int getPath(TreeNode root) {
        if(root == null || root.left == null && root.right == null) return 0;
        int left = getPath(root.left);
        int right = getPath(root.right);
        // 以当前节点为根的子树的longestUnivaluePath，Longest-Univalue-Path-Across - node
        int maxLength = 0;
        // 以当前节点为根的子树，单向最长路径，对父节点而言
        int pathLen = 0;

        if(root.left != null && root.val == root.left.val) {
            maxLength += (left+1);
            pathLen = left+1;
        }
        if(root.right != null && root.val == root.right.val) {
            maxLength += (right+1);
            if(right+1>pathLen)
                pathLen = right + 1;
        }
        if(maxLength > max) max = maxLength;

        return pathLen;
    }
}
