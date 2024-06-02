package graph_to_java;
import java.util.*;
public class Problem5 {
	private Map<Integer, List<Integer>> adjList = new HashMap<>();

    public void addEdge(int u, int v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public boolean isBipartite() {
        Map<Integer, Integer> colorMap = new HashMap<>();
        for (int node : adjList.keySet()) {
            if (!colorMap.containsKey(node)) {
                if (!bfsCheck(node, colorMap)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean bfsCheck(int start, Map<Integer, Integer> colorMap) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        colorMap.put(start, 0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int currentColor = colorMap.get(node);

            for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
                if (!colorMap.containsKey(neighbor)) {
                    colorMap.put(neighbor, 1 - currentColor);
                    queue.add(neighbor);
                } else if (colorMap.get(neighbor) == currentColor) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
    	/*Write a java program that receives a list of edges of a simple graph and determine whether the graph is bipartite.*/
        List<int[][]> useCases = new ArrayList<>();
        useCases.add(new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 4}}); // Bipartite
        useCases.add(new int[][] {{0, 1}, {1, 2}, {2, 0}}); // Not bipartite (cycle of odd length)
        useCases.add(new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}}); // Bipartite (cycle of even length)
        useCases.add(new int[][] {{0, 1}, {1, 2}, {2, 3}}); // Bipartite
        useCases.add(new int[][] {}); // Empty graph, bipartite

        for (int i = 0; i < useCases.size(); i++) {
            Problem5 graph = new Problem5();
            String hold = " ";
            for (int[] edge : useCases.get(i)) {
                if (edge.length == 2) {
                    graph.addEdge(edge[0], edge[1]);
                    hold += edge[0]+"-"+edge[1]+" ";
                }
            }
            System.out.println("Use Case " + (i + 1) + ":"+hold);
            System.out.println("Is bipartite? " + graph.isBipartite());
            System.out.println();
        }
    }
}