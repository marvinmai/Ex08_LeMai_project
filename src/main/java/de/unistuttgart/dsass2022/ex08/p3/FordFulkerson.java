package de.unistuttgart.dsass2022.ex08.p3;

import java.util.ArrayList;

public class FordFulkerson implements IFordFulkerson {
    private AdjArray maxFlowNetwork;
    private AdjArray remainingNetwork;

    @Override
    public int calculate(ArrayList<ArrayList<Integer>> graph, int s, int t) {

        remainingNetwork = new AdjArray(graph);
        maxFlowNetwork = new AdjArray(remainingNetwork.getNumberOfNodes());

        int[] currentPath = TreeTraversal.dfs(remainingNetwork.getAdjArr(), s);
        while (currentPath[currentPath.length - 1] != -1) {
            ArrayList<Edge> pathEdges = remainingNetwork.pathToEdgeList(currentPath);
            Edge smallestRemainingWeightEdge = getSmallestRemainingWeightForPath(pathEdges);
            for (Edge edge : pathEdges) {
                int currentWeight = maxFlowNetwork.getWeight(edge.getSource(), edge.getTarget());
                int newWeight = currentWeight + smallestRemainingWeightEdge.getWeight();
                maxFlowNetwork.set(edge.getSource(), edge.getTarget(), newWeight);

                if (newWeight >= remainingNetwork.getWeight(edge.getSource(), edge.getTarget())) {
                    remainingNetwork.set(edge.getSource(), edge.getTarget(), 0);
                }
            }
            currentPath = TreeTraversal.dfs(remainingNetwork.getAdjArr(), s);
        }
        int maxFlow = 0;
        for (Edge e: maxFlowNetwork.getOutgoingEdgesForSource(s)) {
            maxFlow += e.getWeight();
        }
        return maxFlow;
    }

    private Edge getSmallestRemainingWeightForPath(ArrayList<Edge> pathEdges) {
        int smallestWeight = Integer.MAX_VALUE;
        Edge smallestWeightEdge = null;
        for (Edge edge : pathEdges) {
            int remainingEdgeCapacity = getRemainingCapacityForEdge(edge);
            smallestWeight = Math.min(remainingEdgeCapacity, smallestWeight);
            smallestWeightEdge = new Edge(edge.getSource(), edge.getTarget(), smallestWeight);
        }
        return smallestWeightEdge;
    }

    private int getRemainingCapacityForEdge(Edge edge) {
        return remainingNetwork.getWeight(edge.getSource(), edge.getTarget())
                - maxFlowNetwork.getWeight(edge.getSource(), edge.getTarget());
    }


    @Override
    public ArrayList<ArrayList<Integer>> flow() {
        return maxFlowNetwork.getAdjArr();
    }

}
