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

    @Test
    public void testValueLessThanAllNodes() {
        assertFalse(binaryTree.hasValue());
        int values = 20;
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(0)).stream().toList();
        assertEquals(0, vals.size());
    }

    @Test
    public void testValueGreaterThanAllNodes() {
        assertFalse(binaryTree.hasValue());
        int values = 20;
        for (int i = 1; i < values; i++) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(i));
        }
        int searchValue = 25;
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(searchValue)).stream().toList();
        assertEquals(19, vals.size());
        for (int i = 1; i < 20; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
    }

    @Test
    public void testEmptyTree() {
        assertFalse(binaryTree.hasValue());
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(10)).stream().toList();
        assertEquals(0, vals.size());
    }

    @Test
    public void testSingleElementTree() {
        assertFalse(binaryTree.hasValue());
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(10));
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(10)).stream().toList();
        assertEquals(1, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));
        vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(5)).stream().toList();
        assertEquals(0, vals.size());
    }

    @Test
    public void testWithNegativeValues() {
        assertFalse(binaryTree.hasValue());
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-10));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-20));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-5));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(0));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(10));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(5));

        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(0)).stream().toList();
        assertEquals(4, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(-20)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(-10)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(-5)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(0)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(5)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(10)));
    }

    @Test
    public void testWithNonSequentialInsertions() {
        assertFalse(binaryTree.hasValue());
        int[] values = {10, 5, 15, 3, 7, 12, 17};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(12)).stream().toList();
        assertEquals(5, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(3)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(5)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(7)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(12)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(15)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(17)));
    }

    @Test
    public void testValueNotInTree() {
        assertFalse(binaryTree.hasValue());
        int[] values = {10, 5, 15, 3, 7, 12, 17};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(11)).stream().toList();
        assertEquals(4, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(3)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(5)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(7)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(12)));
    }

    @Test
    public void testValueEqualToMinimum() {
        assertFalse(binaryTree.hasValue());
        int[] values = {10, 5, 15, 3, 7, 12, 17};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(3)).stream().toList();
        assertEquals(1, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(3)));
    }

    @Test
    public void testValueEqualToMaximum() {
        assertFalse(binaryTree.hasValue());
        int[] values = {10, 5, 15, 3, 7, 12, 17};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(17)).stream().toList();
        assertEquals(7, vals.size());
        for (int val : values) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(val)));
        }
    }

    @Test
    public void testWithDuplicates() {
        assertFalse(binaryTree.hasValue());
        int[] values = {10, 5, 15, 5, 10, 15};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(15)).stream().toList();
        // Must contain only unique values
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(5)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(15)));
    }

    @Test
    public void testMixedValues() {
        assertFalse(binaryTree.hasValue());
        int[] values = {50, 20, 70, 10, 30, 60, 80, 5, 15, 25, 35, 55, 65, 75, 85};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(55)).stream().toList();
        int[] expectedValues = {5, 10, 15, 20, 25, 30, 35, 50, 55};
        assertEquals(expectedValues.length, vals.size());
        for (int val : expectedValues) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(val)));
        }
    }

    @Test
    public void testValueLessThanNegativeMinimum() {
        assertFalse(binaryTree.hasValue());
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-10));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-20));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-5));

        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(-30)).stream().toList();
        assertEquals(0, vals.size());
    }

    @Test
    public void testValueGreaterThanNegativeMaximum() {
        assertFalse(binaryTree.hasValue());
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-10));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-20));
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(-5));

        List<BinaryTreeValueImpl> vals = binaryTree.findAllLessThanOrEqualTo(new BinaryTreeValueImpl(0)).stream().toList();
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(-20)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(-10)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(-5)));
    }
}
