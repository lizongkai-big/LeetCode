package TAG.Tree;

import utils.TreeNode;

/**
 * 以前做过求一棵树高度的题目
 * 求最小高度，盲目地以为是对根而言，左子树的高度和右子树的高度的较小值+1
 * 其实不然，应是对整棵树而言，它的最小高度
 * 所以会有一个minDepth值
 * 那在求解时，是不断更新这个minDepth吗？因为（本题使用递归方式）求解树的高度，肯定是叶子的高度最小；
 * 如果是不断更新敏Depth的话，怎么确定这个更新值的条件呢？
 * 所以应当有一个函数，就是用来确定树的最小高度的
 */
public class _111_Minimum_Depth_of_Binary_Tree {
    public int minDepth(TreeNode root) {
        // 判空
        if(root == null) return 0;
        return getMinDepth(root);
    }

    // 求最小高度
    public int getMinDepth(TreeNode node) {
        if(node == null) return 0;
        int rd = getMinDepth(node.right);
        int ld = getMinDepth(node.left);
        int minDepth = 0;
        if(ld == 0 || rd == 0) {
            minDepth = ld + rd + 1;
        }
        else {
            minDepth = Math.min(ld, rd) + 1;
        }
        return minDepth;
    }
}
