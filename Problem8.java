package graph_to_java;
import java.util.*;
public class Problem8 {

	 private int numVertices;
	    private int[][] graph1;
	    private int[][] graph2;
	    private boolean[] visited;
	    private int[] map;

	    public Problem8(int[][] graph1, int[][] graph2) {
	        this.graph1 = graph1;
	        this.graph2 = graph2;
	        this.numVertices = graph1.length;
	        this.visited = new boolean[numVertices];
	        this.map = new int[numVertices];
	        Arrays.fill(map, -1);
	    }

	    public boolean areIsomorphic() {
	        return isomorphicUtil(0);
	    }

	    private boolean isomorphicUtil(int vertex) {
	        if (vertex == numVertices) {
	            return true;
	        }

	        for (int i = 0; i < numVertices; i++) {
	            if (!visited[i] && isSafe(vertex, i)) {
	                visited[i] = true;
	                map[vertex] = i;

	                if (isomorphicUtil(vertex + 1)) {
	                    return true;
	                }

	                visited[i] = false;
	                map[vertex] = -1;
	            }
	        }

	        return false;
	    }

	    private boolean isSafe(int vertex1, int vertex2) {
	        for (int i = 0; i < vertex1; i++) {
	            if (map[i] != -1 && graph1[vertex1][i] != graph2[vertex2][map[i]]) {
	                return false;
	            }
	        }
	        return true;
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter the number of vertices in the graphs: ");
	        int numVertices = scanner.nextInt();

	        int[][] graph1 = new int[numVertices][numVertices];
	        int[][] graph2 = new int[numVertices][numVertices];

	        System.out.println("Enter the adjacency matrix for graph 1:");
	        for (int i = 0; i < numVertices; i++) {
	            for (int j = 0; j < numVertices; j++) {
	                graph1[i][j] = scanner.nextInt();
	            }
	        }

	        System.out.println("Enter the adjacency matrix for graph 2:");
	        for (int i = 0; i < numVertices; i++) {
	            for (int j = 0; j < numVertices; j++) {
	                graph2[i][j] = scanner.nextInt();
	            }
	        }

	        Problem8 problem = new Problem8(graph1, graph2);
	        if (problem.areIsomorphic()) {
	            System.out.println("The graphs are isomorphic.");
	        } else {
	            System.out.println("The graphs are not isomorphic.");
	        }
	    }

}
