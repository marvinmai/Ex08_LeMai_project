package de.unistuttgart.dsass2022.ex08.p1;


import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


public class MinimalSpanningTree{

	/**
	 * This method calculates the minimal spanning tree using the kruskal algorithm.
	 * @param graph the graph object to calculate the MST for
	 * @return a set of edges, which belong to the MST of the given graph
	 */
	public static Set<IEdge> kruskal(IWeightedGraph graph){
		Set<IEdge> minSpanTreeSet = new HashSet<>();

		ArrayList<Edge> singleWayEdges = new ArrayList<>();
		Set<Long> edgeTargetSourceIds = new HashSet<>();

		// Go through all edges and remove those which have same source and destination
		graph.edgeIterator().forEachRemaining(e -> {
			if (!edgeTargetSourceIds.contains(e.getSource() + e.getDestination())) {
				singleWayEdges.add((Edge) e);
				edgeTargetSourceIds.add(e.getSource() + e.getDestination());
			}
		});
		Collections.sort(singleWayEdges);

		// prepare sets for acyclic test
		List<Set<Long>> sets = new ArrayList<>();
		graph.nodeIDIterator().forEachRemaining(nodeId -> {
			Set<Long> set = new HashSet<>();
			set.add(nodeId);
			sets.add(set);
		});

		int i = 0;
		while (minSpanTreeSet.size() < graph.numberOfNodes() - 1 // in case all nodes in the graph are connected
				&& i < singleWayEdges.size() -1	// in case there are subgraphs not connected to each other resulting in multiple min. spanning trees
		) {
			IEdge edge = singleWayEdges.get(i);

			Set<Long> m = find(edge.getSource(), sets);
			Set<Long> n = find(edge.getDestination(), sets);
			// if source and target are in same set this edge would create a cycle
			// only edges not creating a cycle are added to the minimal spanning tree
			if (!m.equals(n)) {
				m.addAll(n);
				sets.remove(n);
				minSpanTreeSet.add(edge);
			}
			i++;
		}
		return minSpanTreeSet;
	}

	private static Set<Long> find(Long nodeId, List<Set<Long>> sets) {
		Set<Long> foundSet = null;
		for (Set<Long> m: sets) {
			if (m.contains(nodeId)) {
				foundSet = m;
			}
		}
		return foundSet;
	}

}
