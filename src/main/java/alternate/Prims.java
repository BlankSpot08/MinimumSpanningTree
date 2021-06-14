package alternate;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Prims {
    public void getMST(final int[][] graph, final int numOfVertices) {
        int numOfEdge = 0;

        final boolean[] selected = new boolean[numOfVertices];
        Arrays.fill(selected, false);

        // the number of egde in minimum spanning tree will be
        // always less than (V -1), where V is number of vertices in
        // graph

        // choose 0th vertex and make it true
        selected[0] = true;

        // print for edge and weight
        System.out.println("Edge : Weight");

        while (numOfEdge < numOfVertices - 1) {
            // For every vertex in the set S, find the all adjacent vertices
            // , calculate the distance from the vertex selected at step 1.
            // if the vertex is already in the set S, discard it otherwise
            // choose another vertex nearest to selected vertex at step 1.

            int min = Integer.MAX_VALUE;
            int rowNumber = 0;
            int colNumber = 0;

            for (int i = 0; i < numOfVertices; i++) {
                if (selected[i]) {
                    for (int j = 0; j < numOfVertices; j++) {
                        // not in selected and there is an edge
                        if (!selected[j] && graph[i][j] != 0) {
                            if (min > graph[i][j]) {
                                min = graph[i][j];
                                rowNumber = i;
                                colNumber = j;
                            }
                        }
                    }
                }
            }

            System.out.println(rowNumber + " - " + colNumber + " :  " + graph[rowNumber][colNumber]);
            selected[colNumber] = true;
            numOfEdge++;
        }
    }

    public static void main(String[] args) {
        final Prims prims = new Prims();

        // number of vertices in graph
        final int numOfVertices = 5;

        // create a 2d array of size 5x5
        // for adjacency matrix to represent graph
        final int[][] graph = {
                 /**  0   1   2   3   4 **/
        /** 0 **/  {  0,  9, 75,  0,  0 },
        /** 1 **/  {  9,  0, 95, 19, 42 },
        /** 2 **/  { 75, 95,  0, 51, 66 },
        /** 3 **/  {  0, 19, 51,  0, 31 },
        /** 4 **/  {  0, 42, 66, 31,  0 }
        };

        prims.getMST(graph, numOfVertices);
    }
}
