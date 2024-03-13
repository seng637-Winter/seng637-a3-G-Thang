package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.Test;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class RangeTest{
	
	// Instantiate the ranges that will be used for testing the "contains" function:
	private Range exampleRange;
	private Range negMaxRange;
	private Range zeroRange;
	
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	
    	// Define the ranges that will be used for testing the "contains" function:
    	exampleRange = new Range(-1, 1);
    	negMaxRange = new Range(-Double.MAX_VALUE, 100);
    	zeroRange = new Range(0, 0);
    }

    
    // ---------------------------------------------------------------------------------
    // The following tests are for the "contains" function in the range class: - Dillon
    // ---------------------------------------------------------------------------------
    
    // The seven following tests use the Range "negMaxRange" [-Double.MAX_VALUE, 100]:
    
    // Checks that a value at the lower MAX bound is contained in the range
    @Test
    public void negMaxRange_LowerBound_Contains() {
        assertTrue("The value on the lower bound should be contained in the range (TRUE)",
        negMaxRange.contains(-Double.MAX_VALUE));
    }
    
    // Checks that a negative value within the range is contained in the range
    @Test
    public void negMaxRange_NegInside_Contains() {
        assertTrue("The negative value within the range should be contained in the range (TRUE)",
        negMaxRange.contains(-10));
    }
    
    // Checks that a 0 value within the range is contained in the range
    @Test
    public void negMaxRange_0Inside_Contains() {
        assertTrue("The 0 value within the range should be contained in the range (TRUE)",
        negMaxRange.contains(0));
    }
    
    // Checks that a positive value within the range is contained in the range
    @Test
    public void negMaxRange_PosInside_Contains() {
        assertTrue("The positive value within the range should be contained in the range (TRUE)",
        negMaxRange.contains(10));
    }
    
    // Checks that a value at the upper regular bound is contained in the range
    @Test
    public void negMaxRange_UpperBound_Contains() {
        assertTrue("The value on the upper bound should be contained in the range (TRUE)",
        negMaxRange.contains(100));
    }
    
    // Checks that a value outside the upper regular bound is NOT contained in the range
    @Test
    public void negMaxRange_UpperOutside_Contains() {
        assertTrue("The value outside of the upper bound should NOT be contained in the range (FALSE)",
        !negMaxRange.contains(110));
    }
    
    // Checks that a MAX value outside the upper bound is NOT contained in the range
    @Test
    public void negMaxRange_UpperMaxOutside_Contains() {
        assertTrue("The MAX value outside of the upper bound should NOT be contained in the range (FALSE)",
        !negMaxRange.contains(Double.MAX_VALUE));
    }
    
    // The following three tests use the Range "zeroRange" [0, 0]:
    
    // Checks that a negative value outside the lower and upper bound is NOT contained in the range
    @Test
    public void zeroRange_NegOutside_Contains() {
        assertTrue("The value outside of the lower bound should NOT be contained in the range (FALSE)",
        !zeroRange.contains(-10));
    }
    
    // Checks that a 0 value on the lower and upper bound is contained in the range
    @Test
    public void zeroRange_0Inside_Contains() {
        assertTrue("The value on the lower and upper bound should be contained in the range (TRUE)",
        zeroRange.contains(0));
    }
    
    // Checks that a positive value outside the lower and upper bound is NOT contained in the range
    @Test
    public void zeroRange_PosOutside_Contains() {
        assertTrue("The value outside of the upper bound should NOT be contained in the range (FALSE)",
        !zeroRange.contains(10));
    }
    
    
    // ---------------------------------------------------------------------------------
    // The following tests are for the "expand" function in the range class: - Jonathan
    // ---------------------------------------------------------------------------------
    
    @Test
    public void testExpand() {
        // Test cases: (originalRange, lowerMargin, upperMargin, expectedExpandedRange)
        double[][] testCases = {
            {1, 3, 0.5, 4.5, 0, 0},    // expandBothPositive
            {2, 6, -0.25, -0.5, 1.5, 8},    // expandBothNegative
            {-1, 1, -1, 2, -2, 3},    // expandZeroOnOne
            {0, 0, -1, 1, -2, 2},    // expandZeroOnBoth
            {Double.MIN_VALUE, Double.MAX_VALUE, -1, 1, Double.MIN_VALUE - 1, Double.MAX_VALUE + 1} // expandMaxAndMinValues
        };

        List<Range> expandedRanges = new ArrayList<>();

        for (double[] testCase : testCases) {
            double lowerMargin = testCase[2];
            double upperMargin = testCase[3];
            Range originalRange = new Range(testCase[0], testCase[1]);
            Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);
            expandedRanges.add(expandedRange);
        }

        // Print out the results for verification
        for (int i = 0; i < expandedRanges.size(); i++) {
            System.out.println("Test case " + (i + 1) + ": Expanded range: " + expandedRanges.get(i));
        }
    }
    
    // ---------------------------------------------------------------------------------
    // The following tests are for the "getLength" function in the range class: - Yaad
    // ---------------------------------------------------------------------------------
    
    @Test
    public void getLengthWithBothBoundsPositiveDouble() { // This test checks a range where both boundaries are positive, which is a standard condition for this method
        Range positiveRange = new Range(5.0, 10.0);
        assertEquals("Testing the length of a range with both bounds positive (Double)", 5.0, positiveRange.getLength(), 0.0000001);
    }

    @Test
    public void getLengthWithBothBoundsNegativeDouble() { // This test ensures correct handling of ranges with negative boundaries, covering another standard equivalence class.
        Range negativeRange = new Range(-10.0, -5.0);
        assertEquals("Testing the length of a range with both bounds negative (Double)", 5.0, negativeRange.getLength(), 0.0000001);
    }

    @Test
    public void getLengthWithNegativeAndPositiveBoundsDouble() { // Testing boundary transition from negative to positive to ensure the range correctly spans across zero
        Range mixedRange = new Range(-5.0, 5.0);
        assertEquals("Testing the length of a range with negative and positive bounds (Double)", 10.0, mixedRange.getLength(), 0.0000001);
    }

    @Test
    public void getLengthWithZeroAndPositiveBoundDouble() { // Testing a range starting at zero to positive, examining the lower boundary condition
        Range zeroPositiveRange = new Range(0.0, 10.0);
        assertEquals("Testing the length of a range with 0 and a positive bound (Double)", 10.0, zeroPositiveRange.getLength(), 0.0000001);
    }

    @Test
    public void getLengthWithNegativeAndZeroBoundDouble() { // Examining the upper boundary condition with a range that includes zero from a negative start
        Range negativeZeroRange = new Range(-10.0, 0.0);
        assertEquals("Testing the length of a range with a negative and 0 bound (Double)", 10.0, negativeZeroRange.getLength(), 0.0000001);
    }

    @Test
    public void getLengthWithBothBoundsPositiveInt() { // SAME AS PREVIOUSLY TESTED, BUT WITH INT
        Range positiveRange = new Range(5, 10); 
        assertEquals("Testing the length of a range with both bounds positive (Int)", 5, positiveRange.getLength(), 0.0000001);
    }

    @Test
    public void getLengthWithBothBoundsNegativeInt() { // SAME AS PREVIOUSLY TESTED, BUT WITH INT
        Range negativeRange = new Range(-10, -5); 
        assertEquals("Testing the length of a range with both bounds negative (Int)", 5, negativeRange.getLength(), 0.0000001);
    }

    @Test
    public void getLengthWithNegativeAndPositiveBoundsInt() { // SAME AS PREVIOUSLY TESTED, BUT WITH INT
        Range mixedRange = new Range(-5, 5); 
        assertEquals("Testing the length of a range with negative and positive bounds (Int)", 10, mixedRange.getLength(), 0.0000001);
    }

    @Test
    public void getLengthWithZeroAndPositiveBoundInt() { // SAME AS PREVIOUSLY TESTED, BUT WITH INT
        Range zeroPositiveRange = new Range(0, 10); 
        assertEquals("Testing the length of a range with 0 and a positive bound (Int)", 10, zeroPositiveRange.getLength(), 0.0000001);
    }

    @Test
    public void getLengthWithNegativeAndZeroBoundInt() { // SAME AS PREVIOUSLY TESTED, BUT WITH INT
        Range negativeZeroRange = new Range(-10, 0);
        assertEquals("Testing the length of a range with a negative and 0 bound (Int)", 10, negativeZeroRange.getLength(),0.0000001);
    }

    
    // ---------------------------------------------------------------------------------
    // The following tests are for the "intersects" function in the range class: - Gi-E
    // ---------------------------------------------------------------------------------
    
    @Test
    public void testIntersects() {
        double[][] testCases = {
                {-2.0, 1.0, 0.5, -1.0, 0.0, -2.0, 1.0, -1.0, 0.5, -2.0, -1.6},
                {-1.0, 1.1, 0.6, 0.0, 1.0, -3.0, 2.0, -0.5, 1.0, 2.0, -1.0}
        };

        boolean[] expectedResults = {false, false, true, true, true, false, false, true, true, true, false};

        for (int i = 0; i < testCases[0].length; i++) {
            assertEquals(expectedResults[i], exampleRange.intersects(testCases[0][i], testCases[1][i]));
        }
    }
    
    
    // --------------------------------------------------------------------------------------
    // The following tests are for the "getLowerBound" function in the range class: - Andrew
    // --------------------------------------------------------------------------------------
    
	// Assuming this is part of the Range class mentioned in the tests
	@Test
    public void testPositiveLowerBound() {
        Range range = new Range(5.0, 10.0);
        assertEquals("The lower bound should be positive", 5.0, range.getLowerBound(), 0.0000001d);
    }

    @Test
    public void testZeroLowerBound() {
        Range range = new Range(0.0, 10.0);
        assertEquals("The lower bound should be zero", 0.0, range.getLowerBound(), 0.0000001d);
    }

    @Test
    public void testNegativeLowerBound() {
        Range range = new Range(-10.0, 5.0);
        assertEquals("The lower bound should be negative", -10.0, range.getLowerBound(),0.0000001d);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }	
}