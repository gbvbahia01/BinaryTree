package br.com.gbvbahia.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BinaryTreeAllBetweenTest {

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
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(6), new BinaryTreeValueImpl(14))
                .stream().toList();
        assertEquals(9, vals.size());
        for (int i = 6; i <= 14; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 6; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 15; i < values; i++) {
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
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(8), new BinaryTreeValueImpl(14))
                .stream().toList();
        assertEquals(7, vals.size());
        for (int i = 8; i <= 14; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 8; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 15; i < values; i++) {
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
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(10), new BinaryTreeValueImpl(18))
                .stream().toList();
        assertEquals(9, vals.size());
        for (int i = 10; i <= 18; i++) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 1; i < 9; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
        for (int i = 19; i < values; i++) {
            assertFalse(vals.contains(new BinaryTreeValueImpl(i)));
        }
    }

    // Interval where both start and end values are not present in the tree
    @Test
    public void testIntervalNotInTree() {
        assertFalse(binaryTree.hasValue());
        int[] values = {1, 3, 5, 7, 9, 11, 13};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Start and end values are 4 and 10, which are not in the tree
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(4), new BinaryTreeValueImpl(10))
                .stream().toList();
        // Should include 5, 7, 9
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(5)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(7)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(9)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(1)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(3)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(11)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(13)));
    }

    // Interval where start is less than the minimum value in the tree
    @Test
    public void testStartLessThanMinimum() {
        assertFalse(binaryTree.hasValue());
        int[] values = {5, 10, 15, 20};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Start value is 0, which is less than the minimum value in the tree
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(0), new BinaryTreeValueImpl(15))
                .stream().toList();
        // Should include 5, 10, 15
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(5)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(15)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(20)));
    }

    // Interval where end is greater than the maximum value in the tree
    @Test
    public void testEndGreaterThanMaximum() {
        assertFalse(binaryTree.hasValue());
        int[] values = {5, 10, 15, 20};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // End value is 25, which is greater than the maximum value in the tree
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(10), new BinaryTreeValueImpl(25))
                .stream().toList();
        // Should include 10, 15, 20
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(15)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(20)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(5)));
    }

    // Interval where start and end are equal and present in the tree
    @Test
    public void testStartEqualsEndValuePresent() {
        assertFalse(binaryTree.hasValue());
        int[] values = {5, 10, 15};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Start and end values are both 10
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(10), new BinaryTreeValueImpl(10))
                .stream().toList();
        // Should include only 10
        assertEquals(1, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));
    }

    // Interval where start and end are equal and not present in the tree
    @Test
    public void testStartEqualsEndValueNotPresent() {
        assertFalse(binaryTree.hasValue());
        int[] values = {5, 15};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Start and end values are both 10, which is not in the tree
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(10), new BinaryTreeValueImpl(10))
                .stream().toList();
        // Should return an empty list
        assertEquals(0, vals.size());
    }

    // Empty tree
    @Test
    public void testEmptyTree() {
        assertFalse(binaryTree.hasValue());
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(5), new BinaryTreeValueImpl(15))
                .stream().toList();
        // Should return an empty list
        assertEquals(0, vals.size());
    }

    // Tree with a single element
    @Test
    public void testSingleElementTree() {
        assertFalse(binaryTree.hasValue());
        binaryTree = binaryTree.insert(new BinaryTreeValueImpl(10));
        // Interval includes the single element
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(5), new BinaryTreeValueImpl(15))
                .stream().toList();
        assertEquals(1, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));
        // Interval does not include the single element
        vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(15), new BinaryTreeValueImpl(20))
                .stream().toList();
        assertEquals(0, vals.size());
    }

    // Tree with negative values
    @Test
    public void testWithNegativeValues() {
        assertFalse(binaryTree.hasValue());
        int[] values = {-20, -10, -5, 0, 5, 10};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Interval from -10 to 5
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(-10), new BinaryTreeValueImpl(5))
                .stream().toList();
        // Should include -10, -5, 0, 5
        assertEquals(4, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(-10)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(-5)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(0)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(5)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(-20)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(10)));
    }

    // Interval that does not overlap any values in the tree
    @Test
    public void testIntervalNotOverlapping() {
        assertFalse(binaryTree.hasValue());
        int[] values = {1, 3, 5, 7, 9};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Interval from 11 to 15, which does not overlap any values
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(11), new BinaryTreeValueImpl(15))
                .stream().toList();
        // Should return an empty list
        assertEquals(0, vals.size());
    }

    // Attempting to insert duplicates
    @Test
    public void testWithDuplicates() {
        assertFalse(binaryTree.hasValue());
        int[] values = {5, 10, 15, 10, 5}; // duplicates
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Interval from 5 to 15
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(5), new BinaryTreeValueImpl(15))
                .stream().toList();
        // Should include 5, 10, 15 without duplicates
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(5)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(10)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(15)));
    }

    // Non-sequential values
    @Test
    public void testWithNonSequentialValues() {
        assertFalse(binaryTree.hasValue());
        int[] values = {50, 20, 70, 10, 30, 60, 80};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Interval from 25 to 65
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(25), new BinaryTreeValueImpl(65))
                .stream().toList();
        // Should include 30, 50, 60
        assertEquals(3, vals.size());
        assertTrue(vals.contains(new BinaryTreeValueImpl(30)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(50)));
        assertTrue(vals.contains(new BinaryTreeValueImpl(60)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(10)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(20)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(70)));
        assertFalse(vals.contains(new BinaryTreeValueImpl(80)));
    }

    // Mixed values with a complex tree structure
    @Test
    public void testMixedValues() {
        assertFalse(binaryTree.hasValue());
        int[] values = {50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 43, 56, 68, 81, 93};
        for (int val : values) {
            binaryTree = binaryTree.insert(new BinaryTreeValueImpl(val));
        }
        // Interval from 30 to 70
        List<BinaryTreeValueImpl> vals = binaryTree.findAllBetween(new BinaryTreeValueImpl(30), new BinaryTreeValueImpl(70))
                .stream().toList();
        int[] expectedValues = {31, 37, 43, 50, 56, 62, 68};
        assertEquals(expectedValues.length, vals.size());
        for (int val : expectedValues) {
            assertTrue(vals.contains(new BinaryTreeValueImpl(val)));
        }
    }
}
