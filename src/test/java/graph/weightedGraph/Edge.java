package graph.weightedGraph;

public class Edge<T extends Comparable<T>> {
    public T firstNode;
    public T secondNode;
    public int weight;

    public Edge(T firstNode, T secondNode, int weight) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.weight = weight;
    }
}
