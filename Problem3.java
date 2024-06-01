package graph_to_java;

import java.util.ArrayList;
import java.util.List;

public class Problem3 {
    private List<List<Integer>> adjList;
    private boolean[] visited;
    private boolean[] recStack;

    public Problem3(int numVertices) {
        adjList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[numVertices];
        recStack = new boolean[numVertices];
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    public boolean hasCycle() {
        for (int i = 0; i < adjList.size(); i++) {
            if (!visited[i]) {
                if (hasCycleUtil(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleUtil(int vertex) {
        if (recStack[vertex]) {
            return true;
        }
        if (visited[vertex]) {
            return false;
        }

        visited[vertex] = true;
        recStack[vertex] = true;

        for (int neighbor : adjList.get(vertex)) {
            if (hasCycleUtil(neighbor)) {
                return true;
            }
        }

        recStack[vertex] = false;
        return false;
    }

    public static void main(String[] args) {
        Problem3 graph = new Problem3(5);

        graph.addEdge(0, 0); // Self-loop
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);



        if (graph.hasCycle()) {
            System.out.println("Graph has a cycle");
        } else {
            System.out.println("Graph does not have a cycle");
        }
    }
}
