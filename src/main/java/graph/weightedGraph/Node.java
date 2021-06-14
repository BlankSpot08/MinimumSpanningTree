package graph.weightedGraph;

import java.util.LinkedList;
import java.util.List;

public class Node<T extends Comparable<T>> {
    public final List<Edge<T>> listOfEdge;
    public T value;

    public Node(final T value) {
        listOfEdge = new LinkedList<>();

        this.value = value;
    }
}