package TAG.Tree;

import utils.TreeNode;

public class _112_PathSumI {
	// maybe consider purning when 'sum' < root.val, if 'root' isn't leaf, return false directly;
	// but the 'sum' and 'node'.val maybe negative, at this time, 'sum' < root.val is normal, shouldn't be purn.
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
            return false;
        return helper(root, sum);
    }
    public boolean helper(TreeNode node, int sum) {
        if(node == null) return false;
        // 叶子节点，要判断左右子结点均为null
        if(node.left == null && node.right == null && node.val == sum ) return true;
        return helper(node.left, sum - node.val) || helper(node.right, sum - node.val);
    }
}
