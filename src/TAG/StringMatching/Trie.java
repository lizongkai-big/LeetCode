package TAG.StringMatching;

class TrieNode {
    char c;
    final int SIZE = 26;
    TrieNode[] children = new TrieNode[SIZE];
    boolean isEndingChar = false;

    public TrieNode(char c) {
        this.c = c;
    }
}

public class Trie {
    private TrieNode root = new TrieNode('/');

    public void insert(char[] text) {
        TrieNode node = root;
        for(char c: text) {
            int inx = c - 'a';
            if(node.children[inx] == null) {
                node.children[inx] = new TrieNode(c);
            }
            node = node.children[inx];
        }
        node.isEndingChar = true;
    }

    public boolean find(char[] pattern) {
        TrieNode node = root;
        for (char c: pattern) {
            int inx = c - 'a';
            if(node.children[inx] == null)
                return false;
            node = node.children[inx];
        }
        return node.isEndingChar;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        char[][] words = new char[][]{{'h','e','l','l','o'}, {'e','e','l','l','o'},
                {'h','e','l','o','o'}, {'h','e','l','l','e'}, };
        System.out.println("对空的Trie树进行查找------------------");
        for(char[] w: words) {
            System.out.println(trie.find(w));
        }
        System.out.println("先插入，再查找------------------");
        for(char[] w: words) {
            trie.insert(w);
        }
        for(char[] w: words) {
            System.out.println(trie.find(w));
        }
        System.out.println("再插入，再查找------------------");
        for(char[] w: words) {
            trie.insert(w);
        }
        for(char[] w: words) {
            System.out.println(trie.find(w));
        }
        System.out.println("查找一个不存在的字符串------------------");
        System.out.println(trie.find(new char[]{'l', 'o', 'o'}));

    }
}
