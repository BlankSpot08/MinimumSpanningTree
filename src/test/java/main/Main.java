package main;

import graph.weightedGraph.WeightedGraph;
import minimumSpanningTree.Prims;

public class Main {
    public void start() {
        WeightedGraph<String> weightedGraph = new WeightedGraph<>();

        weightedGraph.addEdge("A", "B", 75);
        weightedGraph.addEdge("A", "E", 9);
        weightedGraph.addEdge("A", "C", 9);
        weightedGraph.addEdge("B", "C", 95);
        weightedGraph.addEdge("B", "D", 51);
        weightedGraph.addEdge("C", "D", 19);
        weightedGraph.addEdge("C", "E", 42);
        weightedGraph.addEdge("E", "D", 31);

        Prims prims = new Prims();
        WeightedGraph<String> mstGraph = prims.getMSTWithPrims(weightedGraph);

        mstGraph.printEdges();
    }
}
