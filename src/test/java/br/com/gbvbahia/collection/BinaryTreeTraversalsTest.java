package br.com.gbvbahia.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class BinaryTreeTraversalsTest {

    private final Logger log = Logger.getLogger(getClass().getName());
    private BinaryTree<BinaryTreeValueImpl> binaryTree;

    @Before
    public void setUp() throws Exception {
        binaryTree = new BinaryTree<>();
    }

    @Test
    public void test_breadthFirst() {
        fillTree();
        log.info(binaryTree.breadthFirst());
        log.info(binaryTree.toString());
        String expected = "(5)(3)(7)(2)(4)(6)(8)(1)(9)";
        assertEquals(expected, binaryTree.breadthFirst().trim());
    }

    private void fillTree() {
        binaryTree.insert(new BinaryTreeValueImpl(5));
        binaryTree.insert(new BinaryTreeValueImpl(3));
        binaryTree.insert(new BinaryTreeValueImpl(7));
        binaryTree.insert(new BinaryTreeValueImpl(4));
        binaryTree.insert(new BinaryTreeValueImpl(6));
        binaryTree.insert(new BinaryTreeValueImpl(2));
        binaryTree.insert(new BinaryTreeValueImpl(8));
        binaryTree.insert(new BinaryTreeValueImpl(9));
        binaryTree.insert(new BinaryTreeValueImpl(1));
    }

}
