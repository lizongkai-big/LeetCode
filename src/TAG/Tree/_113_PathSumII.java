// class _113_PathSumII.java {
//     public List<List<Integer>> pathSum(TreeNode root, int sum) {
//         // error 1: init list's list also use <>()
//         List<List<Integer>> res = new ArrayList<>();
//         // error 2: forget part list
//         recur(root, sum, res, new ArrayList<>());
//         return res;
//     }
//
//     public void recur(TreeNode root, int sum, List<List<Integer>> res, List<Integer> part) {
//         if(root == null) return;
//         if(root.val == sum && root.left == null && root.right == null) {
//             part.add(root.val);
//             res.add(new ArrayList(part));
// 			// error 3: In recursion, where there is a 'add', there should be a 'remove'.
//             part.remove(part.size()-1);
//             return;
//         }
//         part.add(root.val);
//         recur(root.left, sum - root.val, res, part);
//         recur(root.right, sum - root.val, res, part);
//         part.remove(part.size()-1);
//     }
// }
