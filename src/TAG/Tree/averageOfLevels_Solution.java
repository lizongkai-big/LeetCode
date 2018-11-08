package TAG.Tree;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 要知道每层的个数，和值才能求平均
 * 可以使用BFS DFS两种方法
 */
public class averageOfLevels_Solution {
    /**
     * BFS：利用队列按层遍历
     * @param root
     * @return
     */
    public List<Double> averageOfLevels1(TreeNode root) {
        if(root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int preLevelCount = 1;
        List<Double> res = new ArrayList<>();
        // 按层遍历队列
        while(!queue.isEmpty()) {
            int curLevelCount = 0;
            double sum = 0;
            // 从队列中取出上一层的元素；求和，取平均，加入res；更新preLevelCount
            for(int i = 0; i < preLevelCount; i ++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if(node.left != null) {
                    curLevelCount ++;
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    curLevelCount ++;
                    queue.offer(node.right);
                }
            }
            res.add(sum / preLevelCount);
            preLevelCount = curLevelCount;
        }
        return res;
    }

    class Node {
        double sum;
        int count;
        Node (double d, int c) {
            sum = d;
            count = c;
        }
    }
    /**
     * DFS：利用
     * @param root
     * @return
     */
    public List<Double> averageOfLevels2(TreeNode root) {
        List<Node> temp = new ArrayList<>();
        helper(root, temp, 0);
        List<Double> result = new LinkedList<>();
        for (int i = 0; i < temp.size(); i++) {
            result.add(temp.get(i).sum / temp.get(i).count);
        }
        return result;
    }
    public void helper(TreeNode root, List<Node> temp, int level) {
        if (root == null) return;
        // temp中每个值是Node，对应的是Tree中每层节点的总和以及个数
        if (level == temp.size()) {
            Node node = new Node((double)root.val, 1);
            temp.add(node);
        } else {
            temp.get(level).sum += root.val;
            temp.get(level).count++;
        }
        helper(root.left, temp, level + 1);
        helper(root.right, temp, level + 1);
    }
}
