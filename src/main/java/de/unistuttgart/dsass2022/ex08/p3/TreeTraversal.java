package de.unistuttgart.dsass2022.ex08.p3;

import java.util.ArrayList;

public class TreeTraversal {

    private static int[] path;
    private static ArrayList<String> dfsPaths;
    private int numberOfNodes;
    private static AdjArray graph;

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
        dfsPaths = new ArrayList<>();
        graph = new AdjArray(weights);

        String p = "" + s;
        visit(s, -1, graph, p);

        String selectedPath = selectPath(dfsPaths);

        return createReturnPath(selectedPath);
    }

    private static String selectPath(ArrayList<String> paths) {
        String selected = "";
        int maxLength = 0;
        int lastNodeOfCurrentMaxLengthPath = -1;
        for (String path: paths) {
            if (path.length() > maxLength && Integer.parseInt(String.valueOf(path.charAt(path.length() - 1))) != lastNodeOfCurrentMaxLengthPath) {
                selected = path;
                maxLength = path.length();
                lastNodeOfCurrentMaxLengthPath = Integer.parseInt(String.valueOf(path.charAt(path.length() - 1)));
            }
        }
        return selected;
    }

    private static int [] createReturnPath(String pathString) {
        int [] returnPath = new int[graph.getNumberOfNodes()];

        for (int i = 0; i < returnPath.length; returnPath[i++] = -1) {
        }
        ArrayList<Integer> pathList = new ArrayList<>();
        for (char c: pathString.toCharArray()) {
            int currentId = Integer.parseInt(String.valueOf(c));
            pathList.add(currentId);
        }
        int parentId = -1;
        int currentId = -1;
        for (int i = pathList.size() - 1; i >= 0; i--) {
            currentId = pathList.get(i);
            if (parentId != -1) {
                returnPath[parentId] = currentId;
            }
            parentId = currentId;
        }
        return returnPath;
    }

    private static void visit(int nodeId, int parentNodeId, AdjArray graph, String p) {
        if (graph.hasChildrenFor(nodeId)) {
            for (int childId: graph.getChildNodesSorted(nodeId)) {
                visit(childId, parentNodeId, graph, p + childId);
            }
        } else {
            dfsPaths.add(p);
        }
    }

}
