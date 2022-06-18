package de.unistuttgart.dsass2022.ex08.p3;

import java.util.ArrayList;

public class TreeTraversal {


    /**
     * This method traverses the tree using depth first search. To eliminate any ambiguity choosing the next node,
     * the node with the smallest index is visited next.
     *
     * @param weights adjacency matrix defining the graph. Since only you are using this method
     *                calculating the ford_fulkerson algorithm, you can expect weights to be rectangular
     * @param s       the id of the node to start the dfs on
     * @return array with predecessors. I.e. pi[5] = 2 means, that node 2 is predecessor of node 5.
     */
    public static int[] dfs(ArrayList<ArrayList<Integer>> weights, int s) throws IllegalArgumentException {
        AdjArray adjArray = new AdjArray(weights);
        int[] path = new int[adjArray.getNumberOfNodes()];

        for (int i = 0; i < path.length; path[i++] = -1) {
        }

        int currentNodeId = s;
        ArrayList<Integer> children;
        while (adjArray.hasChildrenFor(currentNodeId)) {
            children = adjArray.getChildNodesSorted(currentNodeId);
            int nextNodeId = children.get(0);
            path[nextNodeId] = currentNodeId;
            currentNodeId = nextNodeId;
        }

        return path;
    }

}
