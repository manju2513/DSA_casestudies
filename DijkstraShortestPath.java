class DijkstraShortestPath {
    static final int V = 6; 
    int minDistance(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    void printSolution(int dist[]) {
        String cities[] = {"S", "A", "B", "C", "D", "T"};
        System.out.println("\nShortest Distance from Source S:");
        for (int i = 0; i < V; i++) {
            System.out.println(
                "S -> " + cities[i] + " = " + dist[i] + " km"
            );
        }
        System.out.println(
            "\nMinimum Distance from S to T = "
            + dist[5] + " km"
        );
    }
    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V];
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;
            for (int v = 0; v < V; v++) {
                if (!visited[v]
                        && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        printSolution(dist);
    }
    public static void main(String[] args) {
        DijkstraShortestPath dsp =
                new DijkstraShortestPath();
        /*
           Cities:
           S = 0
           A = 1
           B = 2
           C = 3
           D = 4
           T = 5
        */
        int graph[][] = {
        //S  A  B  C  D  T
          {0, 7, 9,14, 0, 0}, // S
          {0, 0, 0,10,15, 0}, // A
          {0, 0, 0, 2,11, 0}, // B
          {0, 0, 0, 0, 0, 9}, // C
          {0, 0, 0, 0, 0, 6}, // D
          {0, 0, 0, 0, 0, 0}  // T
        };
        System.out.println(
        "=== Logistics Routing using Dijkstra's Algorithm ===");
        dsp.dijkstra(graph, 0);
    }
}