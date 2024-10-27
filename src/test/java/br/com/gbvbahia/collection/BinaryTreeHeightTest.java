package br.com.gbvbahia.collection;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeHeightTest {

	private BinaryTree<BinaryTreeValueImpl> binaryTree;

	@Before
	public void setUp() throws Exception {
		binaryTree = new BinaryTree<>();
	}

	@Test
	public void testCalculateHeight() {
		assertEquals(0, binaryTree.calculateHeight());
		binaryTree.insert(new BinaryTreeValueImpl(5));
		assertEquals(1, binaryTree.calculateHeight());
		
		binaryTree.insert(new BinaryTreeValueImpl(3));
		assertEquals(2, binaryTree.calculateHeight());
		assertEquals(1, binaryTree.getLeft().calculateHeight());
		
		binaryTree.insert(new BinaryTreeValueImpl(7));
		assertEquals(2, binaryTree.calculateHeight());
		assertEquals(1, binaryTree.getRight().calculateHeight());
		
		binaryTree.insert(new BinaryTreeValueImpl(4));
		assertEquals(3, binaryTree.calculateHeight());
		assertEquals(2, binaryTree.getLeft().calculateHeight());
		assertEquals(1, binaryTree.getLeft().getRight().calculateHeight());
		
		binaryTree.insert(new BinaryTreeValueImpl(6));
		assertEquals(3, binaryTree.calculateHeight());
		assertEquals(2, binaryTree.getRight().calculateHeight());
		assertEquals(1, binaryTree.getRight().getLeft().calculateHeight());
		
		binaryTree.insert(new BinaryTreeValueImpl(2));
		assertEquals(3, binaryTree.calculateHeight());
		assertEquals(2, binaryTree.getLeft().calculateHeight());
		assertEquals(1, binaryTree.getLeft().getLeft().calculateHeight());
		assertEquals(1, binaryTree.getLeft().getRight().calculateHeight());
		
		binaryTree.insert(new BinaryTreeValueImpl(8));
		assertEquals(3, binaryTree.calculateHeight());
		assertEquals(2, binaryTree.getRight().calculateHeight());
		assertEquals(1, binaryTree.getRight().getRight().calculateHeight());
		assertEquals(1, binaryTree.getRight().getLeft().calculateHeight());
		
		binaryTree.insert(new BinaryTreeValueImpl(9));
		assertEquals(4, binaryTree.calculateHeight());
		assertEquals(3, binaryTree.getRight().calculateHeight());
		assertEquals(2, binaryTree.getRight().getRight().calculateHeight());
		assertEquals(1, binaryTree.getRight().getLeft().calculateHeight());
		
		binaryTree.insert(new BinaryTreeValueImpl(1));
		assertEquals(4, binaryTree.calculateHeight());
		assertEquals(3, binaryTree.getLeft().calculateHeight());
		assertEquals(2, binaryTree.getLeft().getLeft().calculateHeight());
		assertEquals(1, binaryTree.getLeft().getRight().calculateHeight());
	}

}
