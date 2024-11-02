package br.com.gbvbahia.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BinaryTreeAllBetweenTest {

    private BinaryTree<BinaryTreeValueImpl> binaryTree;

    @Before
    public void setUp() {
        binaryTree = new BinaryTree<>();
    }

    @Test
    public void testStartingFromLeft() {
        assertFalse(binaryTree.hasValue());
        int values = 20;
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(6), new BinaryTreeValueImpl(14))
                .stream().toList();
        assertEquals(9, vals.size());
        for (int i = 6; i <= 14; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 6; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 15; i < values; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
    }

    @Test
    public void testStartingFromCenter() {
        assertFalse(binaryTree.hasValue());
        int values = 20;
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(8), new BinaryTreeValueImpl(14))
                .stream().toList();
        assertEquals(7, vals.size());
        for (int i = 8; i <= 14; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 8; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 15; i < values; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
    }

    @Test
    public void testStartingFromRight() {
        assertFalse(binaryTree.hasValue());
        int values = 20;
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(10), new BinaryTreeValueImpl(18))
                .stream().toList();
        assertEquals(9, vals.size());
        for (int i = 10; i <= 18; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 9; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 19; i < values; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
    }
}
