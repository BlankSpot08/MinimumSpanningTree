package minimumSpanningTree;

import graph.weightedGraph.Edge;
import graph.weightedGraph.Node;
import graph.weightedGraph.WeightedGraph;

import java.util.*;

public class Kruskal {
    public <T extends Comparable<T>> WeightedGraph<T> algolyze(final WeightedGraph<T> weightedGraph) {
        final WeightedGraph<T> tempWeightedGraph = new WeightedGraph<>();

        final List<Edge<T>> sorted_edges = new LinkedList<>();
        sorted_edges.addAll(weightedGraph.edges());
        sorted_edges.sort(Comparator.comparingInt(e -> e.weight));

        final Map<T, List<T>> map = new HashMap<>();
        final Set<T> visited = new HashSet<>();

        for (final Edge<T> sorted_edge : sorted_edges) {
            final T parentValue = sorted_edge.parent.value;
            final T destinationValue = sorted_edge.destination.value;
            final int weight = sorted_edge.weight;

            map.computeIfAbsent(parentValue, e -> new LinkedList<>());
            map.computeIfAbsent(destinationValue, e -> new LinkedList<>());

            if (!map.get(parentValue).contains(destinationValue) &&
                !map.get(destinationValue).contains(parentValue)) {

                map.get(parentValue).add(destinationValue);
                map.get(destinationValue).add(parentValue);

                tempWeightedGraph.addNode(parentValue);
                tempWeightedGraph.addNode(destinationValue);

                tempWeightedGraph.addEdge(parentValue, destinationValue, weight);

                final Node<T> tempWeightedGraphParent = tempWeightedGraph.getNode(parentValue);
                final Node<T> tempWeightedGraphDestination = tempWeightedGraph.getNode(destinationValue);

                if (visited.contains(parentValue) && visited.contains(destinationValue)) {
                    final boolean result = checkForLoop(tempWeightedGraphParent, tempWeightedGraphDestination);

                    if (result) {
                        tempWeightedGraph.removeEdge(parentValue, destinationValue);
                    }
                }

                visited.add(parentValue);
                visited.add(destinationValue);
            }
        }

        return tempWeightedGraph;
    }

    private <T extends Comparable<T>> boolean checkForLoop(Node<T> firstNode, Node<T> secondNode) {
        final Set<Node<T>> set1 = edges(firstNode, secondNode);
        final Set<Node<T>> set2 = edges(secondNode, firstNode);

        for (final Node<T> node : set1) {
            if (set2.contains(node)) {
                return true;
            }
        }

        return false;
    }

    private <T extends Comparable<T>> Set<Node<T>> edges(final Node<T> node, final Node<T> avoid) {
        final Map<T, List<T>> mapOfEdges = new HashMap<>();
        final Set<Node<T>> list = new HashSet<>();

        mapOfEdges.computeIfAbsent(node.value, e -> new LinkedList<>());
        mapOfEdges.get(node.value).add(avoid.value);

        for (final Edge<T> tempEdge : node.listOfEdge) {
            if (tempEdge.destination != avoid) {
                traverse(tempEdge, mapOfEdges, list);
            }
        }

        return list;
    }

    private <T extends Comparable<T>> void traverse(final Edge<T> edge, final Map<T, List<T>> mapOfEdges, Set<Node<T>> list) {
        for (final Edge<T> tempEdge : edge.destination.listOfEdge) {
            final T parentValue = tempEdge.parent.value;
            final T destinationValue = tempEdge.destination.value;

            mapOfEdges.computeIfAbsent(parentValue, e -> new LinkedList<>());
            mapOfEdges.computeIfAbsent(destinationValue, e -> new LinkedList<>());

            if (!mapOfEdges.get(parentValue).contains(destinationValue) &&
                !mapOfEdges.get(destinationValue).contains(parentValue)) {

                mapOfEdges.get(parentValue).add(destinationValue);
                mapOfEdges.get(destinationValue).add(parentValue);

                list.add(tempEdge.destination);

                traverse(tempEdge, mapOfEdges, list);
            }
        }
    }
}


