package minimumSpanningTree;

import graph.weightedGraph.Edge;
import graph.weightedGraph.WeightedGraph;

import java.util.*;
import java.util.stream.Collectors;

public class Prims {
    public <T extends Comparable<T>> WeightedGraph<T> getMSTWithPrims(WeightedGraph<T> weightedGraph) {
        WeightedGraph<T> tempWeightedGraph = new WeightedGraph<>();

        List<Edge<T>> sortedEdges = new LinkedList<>(weightedGraph.edges());
        sortedEdges.sort(Comparator.comparingInt(e -> e.weight));

        List<Edge<T>> minimumSpanningTreeList = new LinkedList<>();
        minimumSpanningTreeList.add(sortedEdges.get(0));
        Set<T> visited = new HashSet<>();

        Edge<T> firstEdge = sortedEdges.get(0);
        visited.add(firstEdge.firstNode);
        visited.add(firstEdge.secondNode);

        Set<T> current = new HashSet<>();
        current.add(firstEdge.firstNode);
        current.add(firstEdge.secondNode);

        sortedEdges.remove(0);

        List<Edge<T>> temp = list(sortedEdges, visited, current, weightedGraph.nodes().size(), minimumSpanningTreeList);

        for (Edge<T> edge : temp) {
            tempWeightedGraph.addEdge(edge.firstNode, edge.secondNode, edge.weight);
        }

        return tempWeightedGraph;
    }

    public <T extends Comparable<T>> List<Edge<T>> list(List<Edge<T>> edges, Set<T> visited, Set<T> current, int nodeSize, List<Edge<T>> list) {
        if (visited.size() == nodeSize) {
            return list;
        }

        List<Edge<T>> temp = edges.stream()
                .filter(e ->
                        !(visited.contains(e.firstNode) && visited.contains(e.secondNode))
                                && (current.contains(e.firstNode) || current.contains(e.secondNode)))
                .collect(Collectors.toList());

        list.add(temp.get(0));
        edges.remove(temp.get(0));

        visited.add(temp.get(0).firstNode);
        visited.add(temp.get(0).secondNode);

        current.add(temp.get(0).firstNode);
        current.add(temp.get(0).secondNode);

        Set <T> tempCurrent = current.stream().filter(e -> {
            List<Edge<T>> andAnotherOne = edges.stream()
                    .filter(j -> j.firstNode == e || j.secondNode == e)
                    .collect(Collectors.toList());

            int size = andAnotherOne.size();

            List<Edge<T>> anotherAgain = andAnotherOne.stream()
                    .filter(j -> visited.contains(j.firstNode) && visited.contains(j.secondNode))
                    .collect(Collectors.toList());

            return size != anotherAgain.size();
        }).collect(Collectors.toSet());

        list(edges, visited, tempCurrent, nodeSize, list);

        return list;
    }
}
