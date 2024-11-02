package br.com.gbvbahia.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BinaryTreeAllSmallerOrEqualTest {

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
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(6)).stream().toList();
        assertEquals(6, vals.size());
        for (int i = 1; i <= 6; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 7; i < 20; i++) {
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
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(8)).stream().toList();
        assertEquals(8, vals.size());
        for (int i = 1; i <= 8; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 9; i < 20; i++) {
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
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(10)).stream().toList();
        assertEquals(10, vals.size());
        for (int i = 1; i <= 10; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 11; i < 20; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
    }
}
