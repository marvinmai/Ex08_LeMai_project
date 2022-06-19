package de.unistuttgart.dsass2022.ex08.p3;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTraversalTest {

    @Test
    public void testDfs1() {
        AdjArray n2 = TestData.generateN2();
        int[] dfsPath = TreeTraversal.dfs(n2.getAdjArr(), 0);
        int[] expectedDfsPath = new int[]{-1, 0, -1, -1, 1, -1, 4, -1, 6};
        assertArrayEquals(expectedDfsPath, dfsPath);
    }

    @Test
    public void testDfs2() {
        AdjArray n2 = TestData.generateN2();
        n2.set(4, 6, 0);
        int[] dfsPath = TreeTraversal.dfs(n2.getAdjArr(), 0);
        int[] expectedDfsPath = new int[]{-1, 0, -1, -1, 1, -1, -1, 4, 7};
        assertArrayEquals(expectedDfsPath, dfsPath);
    }

    @Test
    public void testDfs3() {
        AdjArray n2 = TestData.generateN2();
        n2.set(4, 6, 0);
        n2.set(1, 4, 0);
        int[] dfsPath = TreeTraversal.dfs(n2.getAdjArr(), 0);
        int[] expectedDfsPath = new int[]{-1, 0, -1, -1, -1, -1, 1, -1, 6};
        assertArrayEquals(expectedDfsPath, dfsPath);
    }

    @Test
    public void testDfs4() {
        AdjArray n2 = TestData.generateN2();
        n2.set(4, 6, 0);
        n2.set(1, 4, 0);
        n2.set(0, 1, 0);
        n2.set(1, 6, 0);
        int[] dfsPath = TreeTraversal.dfs(n2.getAdjArr(), 0);
        int[] expectedDfsPath = new int[]{-1, -1, 3, 0, 2, -1, -1, 4, 7};
        assertArrayEquals(expectedDfsPath, dfsPath);
    }

    @Test
    public void testDfs5() {
        AdjArray n2 = TestData.generateN2();
        n2.set(4, 6, 0);
        n2.set(1, 4, 0);
        n2.set(0, 1, 0);
        n2.set(1, 6, 0);
        n2.set(4, 7, 0);
        int[] dfsPath = TreeTraversal.dfs(n2.getAdjArr(), 0);
        int[] expectedDfsPath = new int[]{-1, -1, -1, 0, -1, 3, -1, 5, 7};
        assertArrayEquals(expectedDfsPath, dfsPath);
    }

    @Test
    public void testDfs6() {
        AdjArray n2 = TestData.generateN2();
        n2.set(4, 6, 0);
        n2.set(1, 4, 0);
        n2.set(0, 1, 0);
        n2.set(1, 6, 0);
        n2.set(4, 7, 0);
        n2.set(7, 8, 0);
        int[] dfsPath = TreeTraversal.dfs(n2.getAdjArr(), 0);
        int[] expectedDfsPath = new int[]{-1, -1, 3, 0, 2, -1, -1, -1, -1};
        assertArrayEquals(expectedDfsPath, dfsPath);
    }
}
