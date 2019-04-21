package TAG.Graph;

import java.util.*;

public class Graph {
    private int v;
    private LinkedList<Integer>[] adj;

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    public void BFS(int s, int t) {
        if(s == t) return;
        // 已经被访问的节点不再被访问
        boolean[] visited = new boolean[v];
        visited[s] = true;
        // 用来存储已经被访问、但相连的顶点还没有被访问的顶点。
        // 广度优先搜索的辅助DS，如树的层次遍历
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        // 记录s-->t的搜索路径；是反向存储的
        // prev[w]存储的是，顶点w是从哪个前驱顶点遍历过来的
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }

        while (queue.size() != 0) {

            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if(!visited[q]) {
                    prev[q] = w;
                    if(q == t) {
                        print(prev, s, t);
                        // 双重循环中的跳出
                        return;
                    }
                    // 新值加入队列，并设为已访问
                    queue.add(q);
                    visited[q] = true;
                }
            }
        }
    }

    boolean found = false; // 全局变量或者类成员变量

    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true) return;
        // 在w==t之前，保证return后的visited合理
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); ++i) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }

    public void DFS_Iterative(int s, int t) {
        boolean[] visited = new boolean[v];
        int[] pre = new int[v];
        Arrays.fill(pre, -1);

        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            visited[node] = true;
            for (int i = 0; i < adj[node].size(); i++) {
                int k = adj[node].get(i);
                if(!visited[k]) {
                    visited[k] = true;
                    pre[k] = node;
                    if(k == t) {
                        print(pre, s, t);
                        return;
                    }
                    stack.push(k);
                    break;   // 与BFS的不同点
                }
            }
        }
    }


    private void print(int[] prev, int s, int t) { // 递归打印 s->t 的路径
        if(prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    boolean isFound = false;
    public void dfs_(int s, int t) {
        if(s == t) return;
        boolean visited[] = new boolean[v];
        // visited[s] = true;
        int[] prev = new int[v];
        recurDfs_(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs_(int w, int t, boolean[] visited, int[] prev) {
        if(found) return;
        visited[w] = true;
        if(w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); i++) {
            int q = adj[w].get(i);
            /*important*/
            if(visited[q]) continue;
            prev[w] = q;
            recurDfs_(q, t, visited, prev);
        }
    }

    public static void main(String[] args) {
        int point = 6;
        int[][] prerequisites = new int[][]{{0,1}, {1,2}, {2,3}, {3,5}, {5, 4}, {2, 5}};
        Graph graph = new Graph(point);
        for (int i = 0; i < prerequisites.length; i++) {
            graph.addEdge(prerequisites[i][0], prerequisites[i][1]);
        }
        graph.BFS(0,4);
    }
}
