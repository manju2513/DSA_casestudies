class PrimsMST {
    static final int V = 6;
    int minKey(int key[], boolean mstSet[]) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < V; v++) {

            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    void printMST(int parent[], int graph[][]) {
        String cities[] = {"A", "B", "C", "D", "E", "F"};
        System.out.println("\nMinimum Spanning Tree:");
        int totalCost = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(
                cities[parent[i]] + " - " +
                cities[i] + " : " +
                graph[i][parent[i]]
            );
            totalCost += graph[i][parent[i]];
        }
        System.out.println("\nTotal Cable Cost = "
                           + totalCost + " Crores");
    }
    void primMST(int graph[][]) {
        int parent[] = new int[V];
        int key[] = new int[V];
        boolean mstSet[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;  
        parent[0] = -1;
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 &&
                    !mstSet[v] &&
                    graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        printMST(parent, graph);
    }
    public static void main(String[] args) {
        PrimsMST mst = new PrimsMST();
        /*
            Cities:
            A=0, B=1, C=2,
            D=3, E=4, F=5
        */
        int graph[][] = {
          {0, 4, 3, 0, 0, 0}, // A
          {4, 0, 1, 2, 0, 0}, // B
          {3, 1, 0, 0, 8, 0}, // C
          {0, 2, 0, 0, 5, 6}, // D
          {0, 0, 8, 5, 0, 7}, // E
          {0, 0, 0, 6, 7, 0}  // F

        };
        System.out.println(
            "=== Network Infrastructure Design using Prim's Algorithm ==="
        );
        mst.primMST(graph);
    }
}