package de.unistuttgart.dsass2022.ex08.p3;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class FordFulkersonTest {

    @Test
    public void testFordFulkerson() {
        AdjArray n2 = generateN2();
        AdjArray expectedN2MaxFlow = generateMaxFlowN2();
        int expectedMaxFlow = 15;

        FordFulkerson ford = new FordFulkerson();
        int calculatedMaxFlow = ford.calculate(n2.getAdjArr(), 0, 8);
        assertEquals(expectedMaxFlow, calculatedMaxFlow);

        ArrayList<ArrayList<Integer>> calculatedN2MaxFlow = ford.flow();
        assertEquals(expectedN2MaxFlow, calculatedN2MaxFlow);
    }

    @Test
    public void testAdjArrayEquality() {
        AdjArray arr1 = generateN2();
        AdjArray arr2 = generateN2();
        AdjArray arr3 = generateMaxFlowN2();

        assertEquals(arr1, arr2);
        assertNotEquals(arr2, arr3);

        assertEquals(arr2, arr1.getAdjArr());
    }

    private AdjArray generateN2() {
        AdjArray n2 = new AdjArray(9);

        n2.set(0, 1, 12);
        n2.set(1, 6, 3);
        n2.set(6, 8, 20);
        n2.set(1, 4, 9);
        n2.set(4, 6, 7);
        n2.set(0, 3, 10);
        n2.set(3, 2, 5);
        n2.set(2, 4, 4);
        n2.set(3, 4, 7);
        n2.set(3, 5, 18);
        n2.set(5, 7, 10);
        n2.set(4, 7, 3);
        n2.set(7, 8, 5);

        return n2;
    }

    private AdjArray generateMaxFlowN2() {
        AdjArray n2MaxFlow = new AdjArray(9);

        n2MaxFlow.set(0, 1, 12);
        n2MaxFlow.set(1, 6, 3);
        n2MaxFlow.set(6, 8, 10);
        n2MaxFlow.set(1, 4, 9);
        n2MaxFlow.set(4, 6, 7);
        n2MaxFlow.set(0, 3, 3);
        n2MaxFlow.set(3, 2, 1);
        n2MaxFlow.set(2, 4, 1);
        n2MaxFlow.set(3, 4, 0);
        n2MaxFlow.set(3, 5, 2);
        n2MaxFlow.set(5, 7, 2);
        n2MaxFlow.set(4, 7, 3);
        n2MaxFlow.set(7, 8, 5);

        return n2MaxFlow;
    }

    private class AdjArray {
        private ArrayList<ArrayList<Integer>> adjArr;

        public AdjArray(int numberOfNodes) {
            adjArr = new ArrayList<>(numberOfNodes);
            for (int i = 0; i < numberOfNodes; i++) {
                ArrayList<Integer> list = new ArrayList<>(numberOfNodes);
                for (int j = 0; j < numberOfNodes; j++) {
                    list.add(j, 0);
                }
                adjArr.add(list);
            }

        }

        public ArrayList<ArrayList<Integer>> getAdjArr() {
            return adjArr;
        }

        public void set(int src, int target, int value) {
            this.adjArr.get(src).set(target, value);
        }

        public int get(int src, int target) {
            return this.adjArr.get(src).get(target);
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
}
