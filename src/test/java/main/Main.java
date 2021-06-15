package main;

import graph.weightedGraph.WeightedGraph;
import minimumSpanningTree.Kruskal;
import minimumSpanningTree.Prims;

public class Main {
    public void start() {
        WeightedGraph<String> weightedGraph = new WeightedGraph<>();

        weightedGraph.addEdge("A", "B", 10);
        weightedGraph.addEdge("A", "C", 12);
        weightedGraph.addEdge("B", "D", 8);
        weightedGraph.addEdge("B", "C", 9);
        weightedGraph.addEdge("C", "E", 3);
        weightedGraph.addEdge("C", "F", 1);
        weightedGraph.addEdge("D", "E", 7);
        weightedGraph.addEdge("D", "G", 8);
        weightedGraph.addEdge("D", "H", 5);
        weightedGraph.addEdge("F", "H", 6);
        weightedGraph.addEdge("F", "E", 3);
        weightedGraph.addEdge("G", "H", 9);
        weightedGraph.addEdge("G", "I", 2);
        weightedGraph.addEdge("H", "I", 11);

//        Prims prims = new Prims();
//        WeightedGraph<String> mstGraph = prims.getMSTWithPrims(weightedGraph);

        Kruskal kruskal = new Kruskal();

        WeightedGraph<String> mstGraph = kruskal.getMstWithKruskal(weightedGraph);
        mstGraph.printEdges();
    }
}
