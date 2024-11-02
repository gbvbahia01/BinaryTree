package br.com.gbvbahia.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BinaryTreeAllBiggerOrEqualTest {

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
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(6)).stream().toList();
        assertEquals(14, vals.size());
        for (int i = 6; i < values; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 6; i++) {
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
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(8)).stream().toList();
        assertEquals(12, vals.size());
        for (int i = 8; i < values; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 8; i++) {
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
        List<BinaryTreeValueImpl> vals = binaryTree.findAllGreaterThanOrEqualTo(new BinaryTreeValueImpl(10)).stream().toList();
        assertEquals(10, vals.size());
        for (int i = 10; i < values; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 10; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
    }
}
