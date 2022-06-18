package de.unistuttgart.dsass2022.ex08.p3;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class FordFulkersonTest {

    @Test
    public void testFordFulkerson() {
        AdjArray n2 = TestData.generateN2();
        AdjArray expectedN2MaxFlow = TestData.generateExpectedMaxFlowN2();
        int expectedMaxFlow = 15;

        FordFulkerson ford = new FordFulkerson();
        int calculatedMaxFlow = ford.calculate(n2.getAdjArr(), 0, 8);
        assertEquals(expectedMaxFlow, calculatedMaxFlow);

        ArrayList<ArrayList<Integer>> calculatedN2MaxFlow = ford.flow();
        assertEquals(expectedN2MaxFlow, calculatedN2MaxFlow);
    }

    @Test
    public void testAdjArrayEquality() {
        AdjArray arr1 = TestData.generateN2();
        AdjArray arr2 = TestData.generateN2();
        AdjArray arr3 = TestData.generateExpectedMaxFlowN2();

        assertEquals(arr1, arr2);
        assertNotEquals(arr2, arr3);

        assertEquals(arr2, arr1.getAdjArr());
    }


}
