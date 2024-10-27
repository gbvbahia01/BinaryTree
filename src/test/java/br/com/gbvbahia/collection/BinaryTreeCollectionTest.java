package br.com.gbvbahia.collection;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Optional;
import java.util.logging.Logger;

public class BinaryTreeCollectionTest extends TestCase {

    private final Logger log = Logger.getLogger(getClass().getName());

    @Test
    public void testBinaryTreeCollection() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();
        int values = 22;
        for (int i = 1; i < values; i++) {
            binaryCollection.insert(new BinaryTreeValueImpl(i));
        }

        // Search operations
        Optional<BinaryTreeValueImpl> result = binaryCollection.search(new BinaryTreeValueImpl(5));
        assertTrue(result.isPresent());
        assertEquals(Integer.valueOf(5), result.get().getSomeValue());

        // Check if it is a valid BST
        assertTrue(binaryCollection.isBinarySearchTree());

        // Removal
        binaryCollection.remove(new BinaryTreeValueImpl(5));
        assertFalse(binaryCollection.search(new BinaryTreeValueImpl(5)).isPresent());
        assertTrue("Invalid three: " + binaryCollection, binaryCollection.isBinarySearchTree());
    }

    @Test
    public void testRootRemoval() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();
        binaryCollection.insert(new BinaryTreeValueImpl(8));
        binaryCollection.insert(new BinaryTreeValueImpl(12));
        binaryCollection.insert(new BinaryTreeValueImpl(17));
        binaryCollection.insert(new BinaryTreeValueImpl(25));
        binaryCollection.insert(new BinaryTreeValueImpl(10));
        binaryCollection.insert(new BinaryTreeValueImpl(20));
        binaryCollection.insert(new BinaryTreeValueImpl(15));
        log.info(binaryCollection.breadthFirst());
        log.info(binaryCollection.toString());
        assertTrue(binaryCollection.isBinarySearchTree());
        assertEquals("(12)(8)(20)(10)(17)(25)(15)", binaryCollection.breadthFirst().trim());

        binaryCollection.remove(new BinaryTreeValueImpl(12));
        log.info(binaryCollection.breadthFirst());
        log.info(binaryCollection.toString());
        assertTrue(binaryCollection.isBinarySearchTree());
        assertEquals("(15)(8)(20)(10)(17)(25)", binaryCollection.breadthFirst().trim());
    }

    @Test
    public void testRemoveLeafNode() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();
        binaryCollection.insert(new BinaryTreeValueImpl(10));
        binaryCollection.insert(new BinaryTreeValueImpl(5));
        binaryCollection.insert(new BinaryTreeValueImpl(15));
        binaryCollection.insert(new BinaryTreeValueImpl(3));
        assertTrue(binaryCollection.isBinarySearchTree());
        binaryCollection.remove(new BinaryTreeValueImpl(3));
        assertTrue(binaryCollection.isBinarySearchTree());
        assertEquals("(10)(5)(15)", binaryCollection.breadthFirst().trim());
    }

    @Test
    public void testRemoveNodeWithOneChild() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();
        binaryCollection.insert(new BinaryTreeValueImpl(10));
        binaryCollection.insert(new BinaryTreeValueImpl(5));
        binaryCollection.insert(new BinaryTreeValueImpl(15));
        binaryCollection.insert(new BinaryTreeValueImpl(12));
        assertTrue(binaryCollection.isBinarySearchTree());
        binaryCollection.remove(new BinaryTreeValueImpl(15));
        assertTrue(binaryCollection.isBinarySearchTree());
        assertEquals("(10)(5)(12)", binaryCollection.breadthFirst().trim());
    }

    @Test
    public void testRemoveNodeWithTwoChildren() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();
        binaryCollection.insert(new BinaryTreeValueImpl(20));
        binaryCollection.insert(new BinaryTreeValueImpl(10));
        binaryCollection.insert(new BinaryTreeValueImpl(30));
        binaryCollection.insert(new BinaryTreeValueImpl(5));
        binaryCollection.insert(new BinaryTreeValueImpl(15));
        binaryCollection.insert(new BinaryTreeValueImpl(25));
        binaryCollection.insert(new BinaryTreeValueImpl(35));
        assertTrue(binaryCollection.isBinarySearchTree());
        binaryCollection.remove(new BinaryTreeValueImpl(20));
        assertTrue(binaryCollection.isBinarySearchTree());
        assertEquals("(25)(10)(30)(5)(15)(35)", binaryCollection.breadthFirst().trim());
    }

    @Test
    public void testRemovalCausingRebalance() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();
        binaryCollection.insert(new BinaryTreeValueImpl(30));
        binaryCollection.insert(new BinaryTreeValueImpl(20));
        binaryCollection.insert(new BinaryTreeValueImpl(40));
        binaryCollection.insert(new BinaryTreeValueImpl(10));
        binaryCollection.insert(new BinaryTreeValueImpl(25));
        binaryCollection.insert(new BinaryTreeValueImpl(35));
        binaryCollection.insert(new BinaryTreeValueImpl(50));
        binaryCollection.insert(new BinaryTreeValueImpl(5));
        assertTrue(binaryCollection.isBinarySearchTree());
        log.info("Before remove:" + binaryCollection.breadthFirst());

        binaryCollection.remove(new BinaryTreeValueImpl(40));
        binaryCollection.remove(new BinaryTreeValueImpl(5));
        log.info("After remove:" + binaryCollection.breadthFirst());
        assertTrue(binaryCollection.isBinarySearchTree());
        assertEquals("(30)(20)(50)(10)(25)(35)", binaryCollection.breadthFirst().trim());
    }

    @Test
    public void testInsertionsCausingRotations() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();

        // Case leading to a simple right rotation
        binaryCollection.insert(new BinaryTreeValueImpl(30));
        binaryCollection.insert(new BinaryTreeValueImpl(20));
        binaryCollection.insert(new BinaryTreeValueImpl(10));

        // Checks if the tree is balanced after simple rotation
        assertTrue(binaryCollection.isBinarySearchTree());
        assertEquals("(20)(10)(30)", binaryCollection.breadthFirst().trim());

        // Tree Reset
        binaryCollection = new BinaryTreeCollection<>();

        // Case leading to a simple left rotation
        binaryCollection.insert(new BinaryTreeValueImpl(10));
        binaryCollection.insert(new BinaryTreeValueImpl(20));
        binaryCollection.insert(new BinaryTreeValueImpl(30));

        // Checks if the tree is balanced after simple rotation
        assertTrue(binaryCollection.isBinarySearchTree());
        assertEquals("(20)(10)(30)", binaryCollection.breadthFirst().trim());

        // Tree Reset
        binaryCollection = new BinaryTreeCollection<>();

        // Case leading to a double left-right rotation
        binaryCollection.insert(new BinaryTreeValueImpl(30));
        binaryCollection.insert(new BinaryTreeValueImpl(10));
        binaryCollection.insert(new BinaryTreeValueImpl(20));

        // Checks if the tree is balanced after double rotation
        assertTrue(binaryCollection.isBinarySearchTree());
        assertEquals("(20)(10)(30)", binaryCollection.breadthFirst().trim());

        // Tree Reset
        binaryCollection = new BinaryTreeCollection<>();

        // Case leading to a double right-left rotation
        binaryCollection.insert(new BinaryTreeValueImpl(10));
        binaryCollection.insert(new BinaryTreeValueImpl(30));
        binaryCollection.insert(new BinaryTreeValueImpl(20));

        // Checks if the tree is balanced after double rotation
        assertTrue(binaryCollection.isBinarySearchTree());
        assertEquals("(20)(10)(30)", binaryCollection.breadthFirst().trim());
    }


    @Test
    public void testComplexRootRemoval() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45, 55, 65, 75, 85};
        for (int val : values) {
            binaryCollection.insert(new BinaryTreeValueImpl(val));
        }

        // Checks if tree is a valid BST before removal
        assertTrue(binaryCollection.isBinarySearchTree());

        // Remove root node (50)
        binaryCollection.remove(new BinaryTreeValueImpl(50));

        // Checks if tree is still a valid BST after removal
        assertTrue(binaryCollection.isBinarySearchTree());

        // The new root must be the in-order successor of 50, which is 55
        // Check if 55 is the new root
        assertEquals(Integer.valueOf(55), binaryCollection.getRoot().getValue().getSomeValue());

        // Check if the tree is balanced
        assertTrue(binaryCollection.isBinarySearchTree());
    }

    @Test
    public void testMultipleRebalances() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int val : values) {
            binaryCollection.insert(new BinaryTreeValueImpl(val));
        }

        // Checks if tree is a valid BST before removals
        assertTrue(binaryCollection.isBinarySearchTree());

        // Sequential removals
        binaryCollection.remove(new BinaryTreeValueImpl(80));
        binaryCollection.remove(new BinaryTreeValueImpl(70));
        binaryCollection.remove(new BinaryTreeValueImpl(60));

        // Checks if the tree is still a valid BST after removals
        assertTrue(binaryCollection.isBinarySearchTree());

        // Check if the tree is balanced
        assertTrue(binaryCollection.isBinarySearchTree());

        // Check the resulting structure
        assertEquals("(30)(20)(50)(40)", binaryCollection.breadthFirst().trim());
    }


    @Test
    public void testRemoveAllNodes() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();
        int[] values = {10, 5, 15, 3, 7, 12, 18};
        for (int val : values) {
            binaryCollection.insert(new BinaryTreeValueImpl(val));
        }

        // Remove todos os nós
        for (int val : values) {
            binaryCollection.remove(new BinaryTreeValueImpl(val));
        }
        assertNull(binaryCollection.getRoot());
        assertTrue(binaryCollection.isBinarySearchTree());
    }

    @Test
    public void testInsertDuplicateValues() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();
        binaryCollection.insert(new BinaryTreeValueImpl(10));
        binaryCollection.insert(new BinaryTreeValueImpl(5));
        binaryCollection.insert(new BinaryTreeValueImpl(15));
        binaryCollection.insert(new BinaryTreeValueImpl(10));
        assertTrue(binaryCollection.isBinarySearchTree());
        assertEquals("(10)(5)(15)", binaryCollection.breadthFirst().trim());
    }


    @Test
    public void testRemovalInDegenerateTree() {
        BinaryTreeCollection<BinaryTreeValueImpl> binaryCollection = new BinaryTreeCollection<>();
        // Inserting values in ascending order to create a degenerate tree
        int[] values = {10, 20, 30, 40, 50};
        for (int val : values) {
            binaryCollection.insert(new BinaryTreeValueImpl(val));
        }

        // Checks if tree is a valid BST before removal
        assertTrue(binaryCollection.isBinarySearchTree());

        // Remove the middle node (30)
        binaryCollection.remove(new BinaryTreeValueImpl(30));

        // Checks if tree is a valid BST after removal
        assertTrue(binaryCollection.isBinarySearchTree());

        // Check the resulting structure
        assertEquals("(20)(10)(40)(50)", binaryCollection.breadthFirst().trim());
    }

}