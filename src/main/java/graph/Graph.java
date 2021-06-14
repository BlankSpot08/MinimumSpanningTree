package graph;

import graph.weightedGraph.Edge;
import graph.weightedGraph.Node;

import java.util.List;

public interface Graph<T extends Comparable<T>> {
    boolean contains(final T value);

    boolean containsEdge(final T firstNode, final T secondNode);

    void addNode(final T value);

    void addEdge(final T parent, final T destination, final int weight);

    Edge<T> removeEdge(final T parent, final T destination);

    Node<T> getNode(final T value);
//    Edge<T> getEdge(final T parent, final T destination);
    List<Node<T>> nodes();

    List<Edge<T>> edges();

    void printNodes();
    void printEdges();
}