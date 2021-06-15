package minimumSpanningTree;

import graph.weightedGraph.Edge;
import graph.weightedGraph.WeightedGraph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Kruskal {
    private boolean bullyan;

    public <T extends Comparable<T>>WeightedGraph<T> getMstWithKruskal(WeightedGraph<T> weightedGraph) {
        WeightedGraph<T> tempWeightedGraph = new WeightedGraph<>();

        List<Edge<T>> sortedEdges = new LinkedList<>(weightedGraph.edges());
        sortedEdges.sort(Comparator.comparingInt(e -> e.weight));

        Set<Edge<T>> selectedEdges = new HashSet<>();

        Set<T> visitedNodes = new HashSet<>();
        for (Edge<T> edge : sortedEdges) {
            T firstNode = edge.firstNode;
            T secondNode = edge.secondNode;

            selectedEdges.add(edge);
            if (visitedNodes.contains(firstNode) && visitedNodes.contains(secondNode)) {
                selectedEdges.remove(edge);

                bullyan = false;

                test(selectedEdges, firstNode, null, secondNode);

                if (!bullyan) {
                    selectedEdges.add(edge);
                }
            }

            visitedNodes.add(firstNode);
            visitedNodes.add(secondNode);
        }

        for (Edge<T> edge : selectedEdges) {
            tempWeightedGraph.addEdge(edge.firstNode, edge.secondNode, edge.weight);
        }

        return tempWeightedGraph;
    }

    private <T extends Comparable<T>> void test(Set<Edge<T>> selectedEdges, T node, T parent, T nodeToFind) {
        Set<Edge<T>> temp = selectedEdges.stream()
                .filter(e ->
                        (e.firstNode == node || e.secondNode == node)
                        && (e.firstNode != parent && e.secondNode != parent))
                .collect(Collectors.toSet());

        for (Edge<T> edge : temp) {
            T tempNode = edge.firstNode == node ? edge.secondNode : edge.firstNode;

            if (tempNode == nodeToFind) {
                bullyan = true;
            }

            test(selectedEdges, tempNode, node, nodeToFind);
        }
    }
}