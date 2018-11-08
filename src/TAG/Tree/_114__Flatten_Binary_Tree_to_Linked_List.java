package TAG.Tree;

import utils.TreeNode;

public class _114__Flatten_Binary_Tree_to_Linked_List {
    public void flatten(TreeNode root) {
        if(root == null) return;

        if(root.left != null) flatten(root.left);
        if(root.right != null) flatten(root.right);
        TreeNode node = root.right;
        root.right = root.left;
        root.left = null; // nessesary? yes! because it maybe has TreeNode before, need to be set null.
        while(root.right != null) root = root.right;
        root.right = node;

    }
}
