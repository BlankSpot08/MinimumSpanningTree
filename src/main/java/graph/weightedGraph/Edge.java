package graph.weightedGraph;

public class Edge<T extends Comparable<T>> {
    public Node<T> parent;
    public Node<T> destination;
    public int weight;

    public Edge(final Node<T> firstNode, final Node<T> secondNode, final int weight) {
        this.weight = weight;
        this.destination = secondNode;
        this.parent = firstNode;
    }
}