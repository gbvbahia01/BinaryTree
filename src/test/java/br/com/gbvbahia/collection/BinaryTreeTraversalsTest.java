package br.com.gbvbahia.collection;

import static org.junit.Assert.*;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTraversalsTest {

  private final Logger log = Logger.getLogger(getClass().getName());
  private BinaryTree<BinaryTreeValueImpl> binaryTree;
  
  @Before
  public void setUp() throws Exception {
    binaryTree = new BinaryTree<>();
  }

  @Test
  public void test_preorder() {
    fillTree();
    log.info(binaryTree.preorder());
    String expected = "L: (3) M: (5) R: (7)\n"
        + "L: (2) M: (3) R: (4)\n"
        + "L: (1) M: (2) R: null\n"
        + "L: null M: (1) R: null\n"
        + "L: null M: (4) R: null\n"
        + "L: (6) M: (7) R: (8)\n"
        + "L: null M: (6) R: null\n"
        + "L: null M: (8) R: (9)\n"
        + "L: null M: (9) R: null";
    assertEquals(expected, binaryTree.preorder().trim());
    
  }
  
  @Test
  public void test_inorder() {
    fillTree();
    log.info(binaryTree.inorder());
    String expected = "L: null M: (1) R: null\n"
        + "L: (1) M: (2) R: null\n"
        + "L: (2) M: (3) R: (4)\n"
        + "L: null M: (4) R: null\n"
        + "L: (3) M: (5) R: (7)\n"
        + "L: null M: (6) R: null\n"
        + "L: (6) M: (7) R: (8)\n"
        + "L: null M: (8) R: (9)\n"
        + "L: null M: (9) R: null";
    assertEquals(expected, binaryTree.inorder().trim());
    
  }
  
  @Test
  public void test_postorder() {
    fillTree();
    log.info(binaryTree.postorder());
    String expected = "L: null M: (1) R: null\n"
        + "L: (1) M: (2) R: null\n"
        + "L: null M: (4) R: null\n"
        + "L: (2) M: (3) R: (4)\n"
        + "L: null M: (6) R: null\n"
        + "L: null M: (9) R: null\n"
        + "L: null M: (8) R: (9)\n"
        + "L: (6) M: (7) R: (8)\n"
        + "L: (3) M: (5) R: (7)";
    assertEquals(expected, binaryTree.postorder().trim());
    
  }
  
  @Test
  public void test_breadthFirst() {
    fillTree();
    log.info(binaryTree.breadthFirst());
    String expected = "(5)(3)(7)(2)(4)(1)(6)(8)(9)";
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
