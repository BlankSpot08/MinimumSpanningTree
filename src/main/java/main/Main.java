package main;

import graph.weightedGraph.WeightedGraph;
import minimumSpanningTree.Kruskal;

public class Main {
    public void start() {
        final WeightedGraph<String> weightedGraph = new WeightedGraph<>();

        weightedGraph.addNode("A");
        weightedGraph.addNode("B");
        weightedGraph.addNode("C");
        weightedGraph.addNode("D");
        weightedGraph.addNode("E");

        weightedGraph.addEdge("A", "B", 75);
        weightedGraph.addEdge("A", "C", 9);
        weightedGraph.addEdge("A", "E", 9);
        weightedGraph.addEdge("B", "C", 95);
        weightedGraph.addEdge("B", "D", 51);
        weightedGraph.addEdge("D", "C", 19);
        weightedGraph.addEdge("D", "E", 31);
        weightedGraph.addEdge("E", "C", 42);
        Kruskal kruskal = new Kruskal();

        WeightedGraph<String> tempWeightedGraph = kruskal.algolyze(weightedGraph);
        tempWeightedGraph.printNodes();
        tempWeightedGraph.printEdges();


    }
}
