package TAG.BackTracking;

import utils.TreeNode;

import java.util.ArrayList;

/**
 * 输入一棵二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 */
public class FindPath {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        findPathRecur(root, target, list, res);
        return res;
    }

    /**
     *
     * @param root
     * @param target
     * @param list
     * @param res
     */
    public void findPathRecur(TreeNode root, int target, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> res) {
        if(root == null) return;
        list.add(root.val);
        if(root.left == null && root.right == null) {
            if(root.val == target) res.add(new ArrayList(list));
            return;
        }

        if(root.left != null) {
            findPathRecur(root.left, target-root.val, list, res);
            // 每一次探索都有一个
            list.remove(list.size()-1);
        }
        if(root.right != null) {
            findPathRecur(root.right, target-root.val, list, res);
            list.remove(list.size()-1);
        }
    }
}
