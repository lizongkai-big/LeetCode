package TAG.Tree;

import utils.TreeNode;

public class _437_PathSumIII {
    /**
     * 双重递归
     * pathSum递归选择root point（起始结点）
     * pathSumHelper 拿着root point递归遍历这条树，直到leaf
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        return pathSumHelper(root, sum) +
                pathSum(root.left, sum) +
                pathSum(root.right, sum);
    }

    public int pathSumHelper(TreeNode root, int sum) {
        if(root == null) return 0;
        int count = 0;
        if(root.val == sum) count++;
        count += pathSumHelper(root.left, sum - root.val);
        count += pathSumHelper(root.right, sum - root.val);
        return count;
    }
}
