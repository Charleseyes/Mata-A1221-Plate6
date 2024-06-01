package graph_to_java;

import java.util.HashMap;
import java.util.Map;

public class Problem2 {

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                {0, 1, 0, 1, 0},
                {1, 0, 3, 0, 3}, // Edge (1, 2) and (1, 3) appear three times each
                {0, 3, 0, 0, 3}, // Edge (2, 3) and (2, 5) appear three times each
                {1, 0, 0, 0, 1},
                {0, 3, 3, 1, 0}  // Edge (4, 2), (4, 3), and (5, 3) appear three times each
        };

        Map<Edge, Integer> edgeCounts = new HashMap<>();

        // Iterate through the entire adjacency matrix
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                int weight = adjacencyMatrix[i][j];
                if (weight > 0) { // If there's a connection (an edge)
                    for (int k = 0; k < weight; k++) { // Count the edge 'weight' times
                        Edge edge = new Edge(i, j);
                        edgeCounts.put(edge, edgeCounts.getOrDefault(edge, 0) + 1);
                    }
                }
            }
        }

        // Output the edges and their counts
        for (Map.Entry<Edge, Integer> entry : edgeCounts.entrySet()) {
            Edge edge = entry.getKey();
            int count = entry.getValue();
            System.out.println("Edge (" + edge.vertex1 + ", " + edge.vertex2 + ") appears " + count + " times");
        }
    }

    private static class Edge {
        final int vertex1;
        final int vertex2;

        public Edge(int vertex1, int vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return (vertex1 == edge.vertex1 && vertex2 == edge.vertex2) || (vertex1 == edge.vertex2 && vertex2 == edge.vertex1);
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(vertex1) + Integer.hashCode(vertex2);
        }
    }
}
