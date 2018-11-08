package TAG.Tree;

import utils.TreeNode;

/**
 * Given an array where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 */
public class _108_Convert_Sorted_Array_to_Binary_Search_Tree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null) return null;
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int start, int end) {
        if(start > end) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, start, mid - 1);
        node.right = helper(nums, mid + 1, end);
        return node;
    }
}
