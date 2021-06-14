package graph.weightedGraph;

import graph.Graph;

import java.util.*;

public class WeightedGraph<T extends Comparable<T>> implements Graph<T> {
    private final List<Node<T>> listOfNode;

    public WeightedGraph() {
        listOfNode = new LinkedList<>();
    }

    @Override
    public boolean contains(final T value) {
        if (value == null) {
            throw new IllegalArgumentException("Parameter value cannot be null");
        }

        for (final Node<T> node : listOfNode) {
            if (node.value.compareTo(value) == 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsEdge(final T firstNode, final T secondNode) {
        if (firstNode == null || secondNode == null) {
            throw new IllegalArgumentException("Parameter value cannot be null");
        }

        for (final Node<T> node : listOfNode) {
            for (final Edge<T> edge : node.listOfEdge) {
                final T parent = edge.parent.value;
                final T destination = edge.destination.value;

                if ((parent == firstNode && destination == secondNode) ||
                        (parent == secondNode && destination == firstNode)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void addNode(final T value) {
        if (value == null) {
            throw new IllegalArgumentException("Parameter value cannot be null");
        }

        if (!contains(value)) {
            Node<T> node = new Node<>(value);

            listOfNode.add(node);
        }
    }

    @Override
    public void addEdge(final T parent, final T destination, final int weight) {
        if (parent == null || destination == null) {
            throw new IllegalArgumentException("Parameter value cannot be null");
        }

        if (parent == destination) {
            throw new IllegalArgumentException("Parent value and destination value cannot be the same");
        }

        if (contains(parent) && contains(destination) && !containsEdge(parent, destination)) {
            final Node<T> parentNode = getNode(parent);
            final Node<T> destinationNode = getNode(destination);


            final Edge<T> parentToDestinationEdge = new Edge<>(parentNode, destinationNode, weight);
            final Edge<T> destinationToParentEdge = new Edge<>(destinationNode, parentNode, weight);

            parentNode.listOfEdge.add(parentToDestinationEdge);
            destinationNode.listOfEdge.add(destinationToParentEdge);
        }
    }

    @Override
    public Edge<T> removeEdge(final T parent, final T destination) {
        if (parent == null || destination == null) {
            throw new IllegalArgumentException("Parameter cannot be a null");
        }

        final Edge<T> edge = getEdge(parent, destination);

        final Node<T> parentNode = getNode(parent);
        final Node<T> destinationNode = getNode(destination);

        for (int i = 0; i < parentNode.listOfEdge.size(); i++) {
            final Edge<T> tempEdge = parentNode.listOfEdge.get(i);

            if (tempEdge.parent == parentNode && tempEdge.destination == destinationNode) {
                parentNode.listOfEdge.remove(i);
                break;
            }
        }

        for (int i = 0; i < destinationNode.listOfEdge.size(); i++) {
            Edge<T> tempEdge = destinationNode.listOfEdge.get(i);

            if (tempEdge.parent == destinationNode && tempEdge.destination == parentNode) {
                destinationNode.listOfEdge.remove(i);
                break;
            }
        }


        return edge;
    }

    @Override
    public Node<T> getNode(final T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }


        if (contains(value)) {
            for (final Node<T> node : listOfNode) {
                if (node.value.compareTo(value) == 0) {
                    return node;
                }
            }
        }

        return null;
    }

    private Edge<T> getEdge(final T parent, final T destination) {
        if (parent == null || destination == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }

        Node<T> node = getNode(parent);
        for (Edge<T> edge : node.listOfEdge) {
            if (edge.parent.value == parent && edge.destination.value == destination) {
                return edge;
            }
        }

        return null;
    }

    @Override
    public List<Node<T>> nodes() {
        return listOfNode;
    }

    @Override
    public List<Edge<T>> edges() {
//        final List<Edge<T>> listofEdge = new LinkedList<>();
//        final Map<T, List<T>> visited = new HashMap<>();
//
//        for (final Node<T> node : listOfNode) {
//            for (final Edge<T> edge : node.listOfEdge) {
//                visited.computeIfAbsent(edge.parent.value, e -> new LinkedList<>());
//                visited.computeIfAbsent(edge.destination.value, e -> new LinkedList<>());
//
//                if (!visited.get(edge.parent.value).contains(edge.destination.value) &&
//                        !visited.get(edge.destination.value).contains(edge.parent.value)) {
//
//                    visited.get(edge.parent.value).add(edge.destination.value);
//                    visited.get(edge.destination.value).add(edge.parent.value);
//
//                    listofEdge.add(edge);
//                }
//            }
//        }
//
//        return listofEdge;

        List<Edge<T>> listOfEdge = new LinkedList<>();

        for (Node<T> node : listOfNode) {
            listOfEdge.addAll(node.listOfEdge);
        }

        return listOfEdge;
    }

    @Override
    public void printNodes() {
        for (final Node<T> node : listOfNode) {
            System.out.println(node.value);
        }
    }

    @Override
    public void printEdges() {
        for (final Node<T> node : listOfNode) {
            for (final Edge<T> edge : node.listOfEdge) {
                final String temp = edge.parent.value + " --> " + edge.destination.value + " | " + edge.weight;

                System.out.println(temp);
            }
        }
    }

    public void printEdgeWithAnotherWay() {
        final Node<T> nodeWithLowestWeight = getRandomNode();
        final Map<T, List<T>> visited = new HashMap<>();

        for (final Edge<T> edge : nodeWithLowestWeight.listOfEdge) {
            printEdgeWithAnotherWay(edge, visited);
        }
    }

    private void printEdgeWithAnotherWay(final Edge<T> edge, final Map<T, List<T>> visited) {
        for (final Edge<T> tempEdge : edge.destination.listOfEdge) {
            visited.computeIfAbsent(tempEdge.parent.value, k -> new LinkedList<>());
            visited.computeIfAbsent(tempEdge.destination.value, k -> new LinkedList<>());

            if (!visited.get(tempEdge.parent.value).contains(tempEdge.destination.value) &&
                !visited.get(tempEdge.destination.value).contains(tempEdge.parent.value)) {

                visited.get(tempEdge.parent.value).add(tempEdge.destination.value);
                visited.get(tempEdge.destination.value).add(tempEdge.parent.value);

                System.out.println(tempEdge.parent.value + " ==> " + tempEdge.destination.value + " : " + tempEdge.weight);
                printEdgeWithAnotherWay(tempEdge, visited);
            }
        }
    }

    private Node<T> getRandomNode() {
        Node<T> node = listOfNode.get((new Random().nextInt(listOfNode.size() - 1)));

        return node;
    }

    private Node<T> getLowestWeight() {
        int lowest = Integer.MAX_VALUE;
        Node<T> temp = null;

        for (final Node<T> node : listOfNode) {
            for (final Edge<T> edge : node.listOfEdge) {
                final int weight = edge.weight;

                if (weight < lowest) {
                    lowest = weight;
                    temp = edge.parent;
                }
            }
        }

        return temp;
    }
}