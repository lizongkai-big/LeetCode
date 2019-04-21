package SwordOffer;

import utils.TreeNode;

import java.util.Arrays;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * */
public class ReConstructBinaryTree {
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        TreeNode dummy = new TreeNode(0);
        dummy.left = recru(pre, in);
        return dummy.left;
    }
    private TreeNode recru(int[] pre, int[] in) {
        if(pre.length == 0 && in.length ==0)return null;
        // 树顶点
        int i = 0;
        for(; i < in.length; i ++) {
            if(in[i] == pre[0])
                break;
        }
        TreeNode node = new TreeNode(pre[0]);
        if(i <= 0) // i+1>pre.length || i >in.length
            node.left = null;
        else
            node.left = recru(Arrays.copyOfRange(pre, 1, i+1), Arrays.copyOfRange(in, 0, i));
        if(pre.length - i - 1 <= 0) // i+1>=pre.length || i+1 >=in.length
            node.left = null;
        else
            node.right = recru(Arrays.copyOfRange(pre, i+1, pre.length), Arrays.copyOfRange(in, i+1, in.length));
        return node;
    }
}
