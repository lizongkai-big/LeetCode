package TAG.Tree;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 最近公共祖先
 */
public class _236__Lowest_Common_Ancestor_of_a_Binary_Tree {

    /**
     * If the current (sub)tree contains both p and q, then the function result is their LCA.
     * If only one of them is in that subtree, then the result is that one of them.
     * If neither are in that subtree, the result is null/None/nil.
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    /**
     * 将寻找最近公共祖先转换为两个数组找公共节点。时间复杂度是O(n)
     * 分别找到p,q的公共祖先序列，然后找到最近的公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_MINE(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pAncestor = new ArrayList<>();
        List<TreeNode> qAncestor = new ArrayList<>();
        isAncestor(root, p, pAncestor);
        isAncestor(root, q, qAncestor);
        int len1 = pAncestor.size();
        int len2 = qAncestor.size();
        int i = len1-1, j = len2-1;
        for(; i>=0 && j>= 0;) {
            if(pAncestor.get(i) == qAncestor.get(j)) {
                i --;
                j --;
            }
            else {
                break;
            }
        }
        return pAncestor.get(i+1);
    }

    /**
     * Is node p's ancester?
     * 将p的祖先节点保存在list中
     * @param node
     * @param p
     * @param list
     * @return
     */
    public boolean isAncestor(TreeNode node, TreeNode p, List<TreeNode> list) {
        if(node == null) return false;
        if(node == p) {
            // 把p节点放进list
            list.add(node);
            return true;
        }
        boolean found = false;
        if(isAncestor(node.left, p, list) || isAncestor(node.right, p, list)) {
            found = true;
            // 把p的祖先节点放进list
            list.add(node);
        }
        return found;
    }
}
