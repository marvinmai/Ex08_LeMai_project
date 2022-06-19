package de.unistuttgart.dsass2022.ex08.p3;

import java.util.ArrayList;
import java.util.Collections;

public class AdjArray {
    private ArrayList<ArrayList<Integer>> adjArr;
    private int numberOfNodes;

    public AdjArray(ArrayList<ArrayList<Integer>> adjArr) {
        this.adjArr = adjArr;
        this.numberOfNodes = adjArr.size();
    }

    public AdjArray(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        adjArr = new ArrayList<>(numberOfNodes);
        for (int i = 0; i < numberOfNodes; i++) {
            ArrayList<Integer> list = new ArrayList<>(numberOfNodes);
            for (int j = 0; j < numberOfNodes; j++) {
                list.add(j, 0);
            }
            adjArr.add(list);
        }

    }

    public void set(int src, int target, int value) {
        this.adjArr.get(src).set(target, value);
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public ArrayList<ArrayList<Integer>> getAdjArr() {
        return adjArr;
    }

    public int getWeight(int src, int target) {
        return this.adjArr.get(src).get(target);
    }

    public boolean hasChildrenFor(int nodeId) {
        for (int weight : adjArr.get(nodeId)) {
            if (weight != 0) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getChildNodesSorted(int srcId) {
        ArrayList<Integer> children = new ArrayList<>();
        ArrayList<Integer> childList = adjArr.get(srcId);
        for (int i = 0; i < childList.size(); i++) {
            if (childList.get(i) != 0) {
                children.add(i);
            }
        }
        Collections.sort(children);
        return children;
    }

    public ArrayList<Edge> getOutgoingEdgesForSource(int src) {
        ArrayList<Integer> childNodes = getChildNodesSorted(src);
        ArrayList<Edge> outgoingEdges = new ArrayList<>();
        for (int childNodeId: childNodes) {
            int weight = getWeight(src, childNodeId);
            outgoingEdges.add(new Edge(src, childNodeId, weight));
        }
        return outgoingEdges;
    }

    public ArrayList<Edge> pathToEdgeList(int[] path) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = path.length - 1; i >= 0;) {
            if (path[i] != -1) {
                int target = i;
                int src = path[i];
                int weight = this.getWeight(src, target);
                edges.add(new Edge(src, target, weight));
                i = src;
            } else {
                i--;
            }

        }
        return edges;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AdjArray) {
            AdjArray otherAdjArr = (AdjArray) other;
            if (otherAdjArr.getAdjArr().equals(this.getAdjArr())) {
                return true;
            }
        } else if (other instanceof ArrayList) {
            ArrayList<ArrayList<Integer>> otherList = (ArrayList<ArrayList<Integer>>) other;
            if (otherList.equals(this.getAdjArr())) {
                return true;
            }
        }
        return false;
    }
}