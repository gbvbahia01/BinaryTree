package br.com.gbvbahia.collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeSearchTest {

	private BinaryTree<BinaryTreeValueImpl> binaryTree;
	
	@Before
	public void setUp() throws Exception {
		binaryTree = new BinaryTree<>();
	}

	@Test
	public void testSearch_Empty() {
		Optional<BinaryTreeValueImpl> search = binaryTree.search(new BinaryTreeValueImpl(1));
		assertTrue(search.isEmpty());
	}
	
	@Test
	public void testSearch_NotFound() {
		binaryTree.insert(new BinaryTreeValueImpl(1));
		Optional<BinaryTreeValueImpl> search = binaryTree.search(new BinaryTreeValueImpl(2));
		assertTrue(search.isEmpty());
	}
	
	@Test
	public void testSearch_Found_Value() {
		binaryTree.insert(new BinaryTreeValueImpl(1));
		Optional<BinaryTreeValueImpl> search = binaryTree.search(new BinaryTreeValueImpl(1));
		assertTrue(search.isPresent());
	}
	
	@Test
	public void testSearch_Found_LeftValue() {
		binaryTree.insert(new BinaryTreeValueImpl(2));
		binaryTree.insert(new BinaryTreeValueImpl(1));
		assertTrue(binaryTree.hasLeftValue());
		assertFalse(binaryTree.hasRightValue());
		Optional<BinaryTreeValueImpl> search = binaryTree.search(new BinaryTreeValueImpl(1));
		assertTrue(search.isPresent());
	}
	
	@Test
	public void testSearch_Found_RightValue() {
		binaryTree.insert(new BinaryTreeValueImpl(1));
		binaryTree.insert(new BinaryTreeValueImpl(2));
		assertFalse(binaryTree.hasLeftValue());
		assertTrue(binaryTree.hasRightValue());
		Optional<BinaryTreeValueImpl> search = binaryTree.search(new BinaryTreeValueImpl(2));
		assertTrue(search.isPresent());
	}
	
	@Test
	public void testBigInsertAndSearch() {
		assertFalse(binaryTree.hasValue());
		int values = 15;
		for (int i = 1; i < values; i++) {
			binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
		}
		
		assertTrue(binaryTree.hasValue());
		assertTrue(binaryTree.hasLeftValue());
		assertTrue(binaryTree.hasRightValue());
		
		for (int i = 1; i < values; i++) {
			Optional<BinaryTreeValueImpl> opt = binaryTree.search(new BinaryTreeValueImpl(i));
			assertTrue(String.format("Value %d is not present on three.", i), opt.isPresent());
		}
	}

}
