package br.com.gbvbahia.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class BinaryTreeBalanceTest {

    private BinaryTree<BinaryTreeValueImpl> binaryTree;

    @Before
    public void setUp() {
        binaryTree = new BinaryTree<>();
    }

    @Test
    public void testBalance_50() {
        assertFalse(binaryTree.hasValue());
        int values = 22;
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }

        assertTrue(binaryTree.hasValue());
        assertTrue(binaryTree.hasLeftValue());
        assertTrue(binaryTree.hasRightValue());

        assertTrue(isBinarySearchTree(binaryTree));
        assertEquals(Integer.valueOf(9), binaryTree.search(new BinaryTreeValueImpl(9)).get().getSomeValue());

        final Logger log = Logger.getLogger(getClass().getName());
        log.info(binaryTree.toString());
    }

    @Test
    public void testBalance_15() {
        assertFalse(binaryTree.hasValue());
        int values = 15;
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }

        assertTrue(binaryTree.hasValue());
        assertTrue(binaryTree.hasLeftValue());
        assertTrue(binaryTree.hasRightValue());

        Optional<BinaryTreeValueImpl> opt = binaryTree.search(new BinaryTreeValueImpl(5));
        assertTrue(String.format("Value %d is not present on three.", 5), opt.isPresent());

        assertTrue(isBinarySearchTree(binaryTree));
    }

    private boolean isBinarySearchTree(BinaryTree<BinaryTreeValueImpl> binaryTree) {
        if (!binaryTree.hasValue()) {
            return true;
        }
        boolean isGreater = isSubTreeGreater(binaryTree.getRight(), binaryTree.getValue());
        assertTrue("False for isGreater: " + binaryTree, isGreater);
        boolean isLesser = isSubTreeLesser(binaryTree.getLeft(), binaryTree.getValue());
        assertTrue("False for islesser: " + binaryTree, isLesser);
        boolean isRight = isBinarySearchTree(binaryTree.getRight());
        assertTrue("False for isRight: " + binaryTree, isRight);
        boolean isLeft = isBinarySearchTree(binaryTree.getLeft());
        assertTrue("False for isLeft: " + binaryTree, isLeft);
        return isGreater && isLesser && isRight && isLeft;
    }

    private boolean isSubTreeLesser(BinaryTree<BinaryTreeValueImpl> binaryTree, BinaryTreeValueImpl value) {
        if (!binaryTree.hasValue()) {
            return true;
        }
        int compare = binaryTree.getValue().compareTo(value);
        return compare <= 0;
    }

    private boolean isSubTreeGreater(BinaryTree<BinaryTreeValueImpl> binaryTree, BinaryTreeValueImpl value) {
        if (!binaryTree.hasValue()) {
            return true;
        }
        int compare = binaryTree.getValue().compareTo(value);
        return compare > 0;
    }
}
