package graph_to_java;
import java.util.*;
public class Problem1 {

    static class Graph {
        int V;
        List<List<Integer>> adj;

        Graph(int v) {
            V = v;
            adj = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int connectedComponents() {
            boolean[] visited = new boolean[V];
            int count = 0;
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    dfs(i, visited);
                    count++;
                }
            }
            return count;
        }

        void dfs(int v, boolean[] visited) {
            visited[v] = true;
            for (int x : adj.get(v)) {
                if (!visited[x]) {
                    dfs(x, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();
        Graph g = new Graph(V);
        System.out.println("Enter the edges (u, v):");
        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            g.addEdge(u, v);
        }
        int cc = g.connectedComponents();
        if (cc == 1) {
            System.out.println("The graph is connected.");
        } else {
            System.out.println("The graph is not connected. There are " + cc + " connected components.");
        }
    }
}