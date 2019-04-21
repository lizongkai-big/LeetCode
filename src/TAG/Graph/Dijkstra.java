package TAG.Graph;

import java.util.Arrays;
import java.util.LinkedList;

class WeighedGraph{
    private LinkedList<Edge> adj[]; // 邻接表
    private int v; // 顶点个数

    public WeighedGraph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t, int w) {
        this.adj[s].add(new Edge(s, t, w));
    }

    private class Edge{
        public int sid; // 起始顶点
        public int tid; // 终止顶点
        public int w; // 权重
        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w   = w;
        }
    }

    private class Vertex{
        public int id; // 顶点编号 ID
        public int dist; // 从起始顶点到这个顶点的距离
        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }
    // 根据vertext.dist构建的小顶堆
    private class PriorityQueue{
        private Vertex[] nodes;
        private int capacity;
        private int size;
        public PriorityQueue(int v) {
            this.nodes = new Vertex[v+1];
            this.capacity = v;
            this.size = 0;
        }


        public Vertex poll() {
            if(size < 1)
                return null;
            Vertex res = nodes[1];
            nodes[1] = nodes[size--];
            treefy(nodes, 1, size);
            return res;
        }

        public void add(Vertex vertex) {
            if(size >= capacity) {
                System.out.println("Queue is Full! Add failed");
                return;
            }
            nodes[++size] = vertex;
            // 只有一个节点，无需调整
            if(size == 1)
                return;
            int i = size;
            // 自下而上的调整
            while (true) {
                int minInx = i;
                if(i/2 > 0 && nodes[i/2].dist > nodes[minInx].dist) minInx = i/2;
                if(minInx == i)
                    break;
                swap(nodes, i, minInx);
                i = minInx;
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 怎么找到那个要更新的节点嘞？
        public void update(Vertex vertex) {
            treefy(nodes, 1, size);
        }

        // 自上而下的调整
        private void treefy(Vertex[] nodes, int i, int j) {
            while (true) {
                int minInx = i;
                if(2*i <= j && nodes[2*i].dist < nodes[minInx].dist) minInx = 2*i;
                if(2*i+1 <= j && nodes[2*i+1].dist < nodes[minInx].dist) minInx = 2*i+1;
                if(minInx == i)
                    break;
                swap(nodes, i, minInx);
                i = minInx;
            }
        }
        private void swap(Vertex[] nodes, int i, int j) {
            Vertex tmp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = tmp;
        }
    }

    public void dijkstra(int s, int t) {
        int[] predecessor = new int[this.v];
        Arrays.fill(predecessor, -1);
        Vertex[] vertices = new Vertex[this.v];
        for (int i = 0; i < this.v; i++) {
            vertices[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        PriorityQueue queue = new PriorityQueue(this.v);
        boolean[] inqueue = new boolean[this.v];
        vertices[s].dist = 0;
        inqueue[s] = true;
        queue.add(vertices[s]);
        while (!queue.isEmpty()) {
            Vertex minVertext = queue.poll();
            if(minVertext.id == t) break;
            for (int i = 0; i < adj[minVertext.id].size(); i++) {
                Edge e = adj[minVertext.id].get(i);
                Vertex nextVertext = vertices[e.tid];
                if(minVertext.dist + e.w < nextVertext.dist) {
                    nextVertext.dist = minVertext.dist + e.w;
                    predecessor[nextVertext.id] = minVertext.id;
                    if(inqueue[nextVertext.id]) {
                        queue.update(nextVertext);
                    }
                    else {
                        queue.add(nextVertext);
                        inqueue[nextVertext.id] = true;
                    }
                }
            }
        }
        print(predecessor, s, t);
    }
    private void print(int[] predecessor, int s, int t) {
        if(predecessor[t] != -1 && t != s) {
            print(predecessor, s, predecessor[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        int point = 6;
        int[][] prerequisites = new int[][]{{0, 1, 10}, {1, 2, 20}, {2, 3, 12},
                {3, 5, 30}, {5, 4, 10}, {2, 5, 100}, {0, 3, 1}, {0, 4, 10}};
        WeighedGraph weighedGraph = new WeighedGraph(point);
        for (int i = 0; i < prerequisites.length; i++) {
            weighedGraph.addEdge(prerequisites[i][0], prerequisites[i][1], prerequisites[i][2]);
        }
        weighedGraph.dijkstra(0, 4);
    }
}

