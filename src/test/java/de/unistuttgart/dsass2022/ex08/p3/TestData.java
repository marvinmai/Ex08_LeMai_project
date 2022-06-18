package de.unistuttgart.dsass2022.ex08.p3;

public class TestData {

    public static AdjArray generateN2() {
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

    public static AdjArray generateExpectedMaxFlowN2() {
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
}
