package graph.weightedGraph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WeightedGraph<T extends Comparable<T>> {
    private List<Edge<T>> listOfEdge = new LinkedList<>();

    public void addEdge(T firstNode, T secondNode, int weight) {
        Edge<T> edge = new Edge<>(firstNode, secondNode, weight);

        listOfEdge.add(edge);
    }

    public Set<T> nodes() {
        Set<T> set = new HashSet<>();

        for (Edge<T> edge : listOfEdge) {
            set.add(edge.firstNode);
            set.add(edge.secondNode);
        }

        return set;
    }

    public List<Edge<T>> edges() {
        return listOfEdge;
    }

    public void printEdges() {
        for (Edge<T> edge : listOfEdge) {
            System.out.println(edge.firstNode + " <==> " + edge.secondNode + " <> " + edge.weight);
        }
    }
}
