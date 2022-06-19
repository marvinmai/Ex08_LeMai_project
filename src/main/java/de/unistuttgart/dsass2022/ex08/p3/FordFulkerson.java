package de.unistuttgart.dsass2022.ex08.p3;

import java.util.ArrayList;

public class FordFulkerson implements IFordFulkerson{
	private AdjArray maxFlowNetwork;

	@Override
	public int calculate(ArrayList<ArrayList<Integer>> graph, int s, int t) {
		AdjArray remainingNetwork = new AdjArray(graph);
		maxFlowNetwork = new AdjArray(remainingNetwork.getNumberOfNodes());



		return 0;
	}

	@Override
	public ArrayList<ArrayList<Integer>> flow() {
		return null;
	}

}
