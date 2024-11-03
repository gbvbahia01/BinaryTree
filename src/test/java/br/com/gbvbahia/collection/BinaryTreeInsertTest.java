package br.com.gbvbahia.collection;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinaryTreeInsertTest {

    private BinaryTree<BinaryTreeValueImpl> binaryTree;

    @Before
    public void setUp() throws Exception {
        binaryTree = new BinaryTree<>();
    }

    @Test
    public void testIsNotEmpty() {
        assertTrue(binaryTree.isEmpty());
        binaryTree.insert(new BinaryTreeValueImpl(1));
        assertTrue(binaryTree.isNotEmpty());
        binaryTree.insert(new BinaryTreeValueImpl(2));
        assertTrue(binaryTree.isNotEmpty());
    }

    @Test
    public void testHasLeftValue() {
        assertFalse(binaryTree.hasLeftValue());
        binaryTree.insert(new BinaryTreeValueImpl(1));
        assertFalse(binaryTree.hasLeftValue());
        binaryTree.insert(new BinaryTreeValueImpl(2));
        assertFalse(binaryTree.hasLeftValue());
        binaryTree.insert(new BinaryTreeValueImpl(0));
        assertTrue(binaryTree.hasLeftValue());
    }

    @Test
    public void testHasRightValue() {
        assertFalse(binaryTree.hasRightValue());
        binaryTree.insert(new BinaryTreeValueImpl(2));
        assertFalse(binaryTree.hasRightValue());
        binaryTree.insert(new BinaryTreeValueImpl(1));
        assertFalse(binaryTree.hasRightValue());
        binaryTree.insert(new BinaryTreeValueImpl(3));
        assertTrue(binaryTree.hasRightValue());
    }

    @Test
    public void testInsert() {
        assertTrue(binaryTree.isEmpty());
        binaryTree.insert(new BinaryTreeValueImpl(1));
        assertTrue(binaryTree.isNotEmpty());
    }


    @Test
    public void testBalancedInsert() {
        assertTrue(binaryTree.isEmpty());
        binaryTree.insert(new BinaryTreeValueImpl(5));
        assertTrue(binaryTree.isNotEmpty());

        binaryTree.insert(new BinaryTreeValueImpl(3));
        assertTrue(binaryTree.getLeft().isNotEmpty());
        assertFalse(binaryTree.getRight().isNotEmpty());

        binaryTree.insert(new BinaryTreeValueImpl(7));
        assertTrue(binaryTree.getLeft().isNotEmpty());
        assertTrue(binaryTree.getRight().isNotEmpty());

        assertFalse(binaryTree.getLeft().hasLeftValue());
        assertFalse(binaryTree.getLeft().hasRightValue());
        assertFalse(binaryTree.getRight().hasLeftValue());
        assertFalse(binaryTree.getRight().hasRightValue());

        binaryTree.insert(new BinaryTreeValueImpl(4));
        assertTrue(binaryTree.getLeft().hasRightValue());

        binaryTree.insert(new BinaryTreeValueImpl(6));
        assertTrue(binaryTree.getRight().hasLeftValue());

        binaryTree.insert(new BinaryTreeValueImpl(2));
        assertTrue(binaryTree.getLeft().hasLeftValue());

        binaryTree.insert(new BinaryTreeValueImpl(8));
        assertTrue(binaryTree.getRight().hasRightValue());
    }

}
