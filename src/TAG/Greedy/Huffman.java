package TAG.Greedy;


import sun.reflect.generics.tree.Tree;
import utils.ListNode;
import utils.TreeNode;

import java.util.*;

class Node {
    char c;
    int prequency;
    Node left;
    Node right;
    public Node(char c, int prequency) {
        this.c = c;
        this.prequency = prequency;
    }
}

public class Huffman {
    public Node buildHuffmanTree(List<Node> list) {
        PriorityQueue<Node> queue = new PriorityQueue(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.prequency < o2.prequency)
                    return -1;
                else if (o1.prequency == o2.prequency)
                    return 0;
                else
                    return 1;
            }
        });
        for(Node node: list) {
            queue.add(node);
        }

        while (list.size() > 1) {
            Node node1 = queue.poll();
            Node node2 = queue.poll();
            Node newNode = new Node('/', node1.prequency + node2.prequency);
            newNode.left = node1;
            newNode.right = node2;
            queue.add(newNode);
        }
        Node root = queue.poll();
        return root;
    }
}
