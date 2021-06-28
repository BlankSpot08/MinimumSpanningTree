package minimumSpanningTree;

import graph.weightedGraph.Edge;
import graph.weightedGraph.WeightedGraph;

public abstract class MinimumSpanningTree {
    public <T extends Comparable<T>> int getSumOfAllEdges(WeightedGraph<T> weightedGraph) {
        int result = weightedGraph.edges().stream().mapToInt(edge -> edge.weight).sum();

        return result;
    }
}
