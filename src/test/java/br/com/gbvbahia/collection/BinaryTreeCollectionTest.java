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

}