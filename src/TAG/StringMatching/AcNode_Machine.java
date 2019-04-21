package TAG.StringMatching;

import java.util.LinkedList;
import java.util.Queue;

class AcNode {
    public char data;
    final static int SIZE = 26;
    public AcNode[] children = new AcNode[SIZE];
    public boolean isEndingChar = false;
    public int length = -1;
    public AcNode fail;
    public AcNode() {

    }
    public AcNode(char data) {
        this.data = data;
    }
}
public class AcNode_Machine {
    final static int SIZE = 26;
    private AcNode root = new AcNode('/');
    public void insert(char[] text) {
        AcNode node = root;
        for(char c: text) {
            int inx = c - 'a';
            if(node.children[inx] == null) {
                node.children[inx] = new AcNode(c);
            }
            node = node.children[inx];
        }
        node.isEndingChar = true;
        node.length = text.length;
    }

    // 构建失败指针，失败指针一般都指向上一层
    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            for (int i = 0; i < SIZE; i++) {
                AcNode pc = p.children[i];
                if(pc == null) continue;
                // 父节点是root，则失败指针指向root
                if(p == root) {
                    pc.fail = root;
                }
                else {
                    // 父节点的失败指针
                    AcNode q = p.fail;
                    // 尽量少回退
                    while (q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        // q中存在与p的子节点（pc）相同的节点qc，左右pc的失败节点指向qc
                        if(qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if(q == null) {
                        pc.fail = root;
                    }
                }
                ((LinkedList<AcNode>) queue).add(pc);
            }
        }
    }

    public void match(char[] text) { // text 是主串
        int n = text.length;
        AcNode p = root;
        for (int i = 0; i < n; ++i) {
            int idx = text[i] - 'a';
            while (p.children[idx] == null && p != root) {
                p = p.fail; // 失败指针发挥作用的地方
            }
            p = p.children[idx];
            if (p == null) p = root; // 如果没有匹配的，从 root 开始重新匹配
            AcNode tmp = p;
            while (tmp != root) { // 打印出可以匹配的模式串
                if (tmp.isEndingChar == true) {
                    int pos = i-tmp.length+1;
                    System.out.println(" 匹配起始下标 " + pos + "; 长度 " + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }

    public static void main(String[] args) {
        AcNode_Machine acNode_machine = new AcNode_Machine();
        char[][] words = new char[][]{{'h','e','l','l','o'}, {'e','e','l','l','o'},
                {'h','e','l','o','o'}, {'h','e','l','l','e'}, };
        for(char[] w: words) {
            acNode_machine.insert(w);
        }
        acNode_machine.buildFailurePointer();
        for(char[] w: words) {
            acNode_machine.match(w);
        }
        acNode_machine.match(new char[]{'e','e','e','l','l','o', 'l', 'o', 'o'});
    }

}
