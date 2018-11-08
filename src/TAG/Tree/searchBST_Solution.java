package TAG.Tree;

/**
 * Given the root node of a binary search tree (BST) and a value.
 * You need to find the node in the BST that the node's value equals the given value.
 * Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.
 */
public class searchBST_Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }
    public TreeNode searchBST(TreeNode root, int val) {
        /*1. iteration*/
        /*
        while(true) {
            if(root == null) {
                return null;
            }
            if(root.val == val) {
                return root;
            }
            else if(root.val < val) {
                root = root.right;
            }
            else {
                root = root.left;
            }
        }
        */
        /*2. recrusion*/

        if(root == null) {
            return null;
        }
        TreeNode res;
        if(root.val == val) {
            res = root;
        }
        else if(root.val < val) {
            res = searchBST(root.right, val);
        }
        else {
            res = searchBST(root.left, val);
        }
        return res;
    }
}
