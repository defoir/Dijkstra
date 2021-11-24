package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class BruteForce {

	public static Graph initializeLogic(Graph graph, Node source) {
		source.setDistance(0);
		return recursiveCalculateShortestPathFromSource(graph, source, 0);
	}

	public static Graph recursiveCalculateShortestPathFromSource(Graph graph, Node source, int j) {
		if (source.getAdjacentNodes().size() <= 0 || source == null) {
			return graph;
		}
		Node currentNode = source;
		for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
			Node adjacentNode = adjacencyPair.getKey();
			Integer edgeWeight = adjacencyPair.getValue();
			if(adjacentNode.getDistance() == edgeWeight + currentNode.getDistance()) {
				return graph;
			}
			if (adjacentNode.getDistance() > edgeWeight + currentNode.getDistance()) {
				if (currentNode.getShortestPath().size() > 0) {
					LinkedList<Node> shortestPath = new LinkedList<>(currentNode.getShortestPath());
					Node teste = new Node(currentNode);	
					shortestPath.add(teste);
					adjacentNode.setShortestPath(shortestPath);
				} else {
					adjacentNode.addOnShortestPath(currentNode);
				}
				adjacentNode.setDistance(edgeWeight + currentNode.getDistance());
				
			}
			recursiveCalculateShortestPathFromSource(graph, adjacentNode, ++j);
		}
		return graph;
	}

}
