package main;

import graph.weightedGraph.WeightedGraph;
import minimumSpanningTree.Prims;

public class Main {
    public void start() {
        WeightedGraph<String> weightedGraph = new WeightedGraph<>();

        weightedGraph.addEdge("Mindanao Ave.","Commonwealth",8);
        weightedGraph.addEdge("Mindanao Ave.","Diliman",6);
        weightedGraph.addEdge("Mindanao Ave.","E Rodriguez Jr. Ave",16);
        weightedGraph.addEdge("Mindanao Ave.","Cubao",10);
        weightedGraph.addEdge("Mindanao Ave.","Araneta Ave.",10);
        weightedGraph.addEdge("Mindanao Ave.","EDSA Triangle",3);
        weightedGraph.addEdge("Commonwealth","Diliman",11);
        weightedGraph.addEdge("Commonwealth","E Rodriguez Jr. Ave",17);
        weightedGraph.addEdge("Commonwealth","Cubao",14);
        weightedGraph.addEdge("Commonwealth","Araneta Ave.",15);
        weightedGraph.addEdge("Commonwealth","EDSA Triangle",12);
        weightedGraph.addEdge("Diliman","E Rodriguez Jr. Ave",12);
        weightedGraph.addEdge("Diliman","Cubao",5);
        weightedGraph.addEdge("Diliman","Araneta Ave.",7);
        weightedGraph.addEdge("Diliman","EDSA Triangle",3);
        weightedGraph.addEdge("E Rodriguez Jr. Ave","Cubao",6);
        weightedGraph.addEdge("E Rodriguez Jr. Ave","Araneta Ave.",11);
        weightedGraph.addEdge("E Rodriguez Jr. Ave","EDSA Triangle",12);
        weightedGraph.addEdge("Cubao","Araneta Ave.",6);
        weightedGraph.addEdge("Cubao","EDSA Triangle",7);
        weightedGraph.addEdge("Araneta Ave.","EDSA Triangle",7);

        Prims prims = new Prims();

        WeightedGraph<String> mstGraph = prims.getMSTWithPrims(weightedGraph);

        mstGraph.printEdges();
        System.out.println("Sum: " + prims.getSumOfAllEdges(mstGraph));
    }
}