package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.Test;
import org.junit.*;

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
    public void expandBothPositive() {
        // Arrange
        Range range = new Range(1, 3);
        double lower = 0.5;
        double upper = 4.5;

        // Act
        Range expandedRange = Range.expand(range, lower, upper);

        // Assert
        assertEquals("Lower bound should be 0.5", 0.5, expandedRange.getLowerBound(), 0);
        assertEquals("Upper bound should be 4.5", 4.5, expandedRange.getUpperBound(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void expandBothNegative() {
        // Define the original range
        Range originalRange = new Range(2,  6);
        
        // Define the negative margins
        double lowerMargin = -0.25; //  25% of the range length
        double upperMargin = -0.5; //  50% of the range length
        
        // Call the expand function with negative margins
        Range expandedRange = Range.expand(originalRange, lowerMargin, upperMargin);
        
        // Assert that the lower and upper bounds of the expanded range are less than the original range
        assertTrue(expandedRange.getLowerBound() < originalRange.getLowerBound());
        assertTrue(expandedRange.getUpperBound() < originalRange.getUpperBound());
    }

    @Test
    public void expandZeroOnOne() {
        // Arrange
        Range range = new Range(-1, 1);
        double lower = -1;
        double upper = 2;

        // Act
        Range expandedRange = Range.expand(range, lower, upper);

        // Assert
        assertEquals("Lower bound should be -1", -1, expandedRange.getLowerBound(), 0);
        assertEquals("Upper bound should be 2", 2, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void expandZeroOnBoth() {
        // Arrange
        Range range = new Range(0, 0);
        double lower = -1;
        double upper = 1;

        // Act
        Range expandedRange = Range.expand(range, lower, upper);

        // Assert
        assertEquals("Lower bound should be -1", -1, expandedRange.getLowerBound(), 0);
        assertEquals("Upper bound should be 1", 1, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void expandMaxAndMinValues() {
        // Arrange
        Range range = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        double lower = -1;
        double upper = 1;

        // Act
        Range expandedRange = Range.expand(range, lower, upper);

        // Assert
        assertEquals("Lower bound should be Double.MIN_VALUE", Double.MIN_VALUE, expandedRange.getLowerBound(),0);
        assertEquals("Upper bound should be Double.MAX_VALUE", Double.MAX_VALUE, expandedRange.getUpperBound(),0);
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
	public void intersectShouldNotBeInBetweenNegativeTwoAndNegativeOne() {
		 assertFalse(exampleRange.intersects(-2.0, -1.0));
	}
	
	@Test
	public void intersectsShouldNotBeInBetweenOneAndOnePointOne () {
		 assertFalse(exampleRange.intersects(1.0, 1.1));
	}
	
	@Test
	public void intersectsShouldBeInBetweenZeroPointFiveAndZeroPointSix () {
		assertTrue(exampleRange.intersects(0.5, 0.6));
	}
    
	@Test
	public void intersectsShouldBeInBetweenNegativeOneAndZero() {
	    assertTrue(exampleRange.intersects(-1.0, 0.0));
	}

	@Test
	public void intersectsShouldBeInBetweenZeroAndOne() {
	    assertTrue(exampleRange.intersects(0.0, 1.0));
	}

	@Test
	public void intersectsShouldNotBeInBetweenNegativeTwoAndNegativeThree() {
	    assertFalse(exampleRange.intersects(-2.0, -3.0));
	}

	@Test
	public void intersectsShouldNotBeInBetweenOneAndTwo() {
	    assertFalse(exampleRange.intersects(1.0, 2.0));
	}

	@Test
	public void intersectsShouldBeAtLowerBound() {
	    assertTrue(exampleRange.intersects(-1.0, -0.5));
	}

	@Test
	public void intersectsShouldBeAtUpperBound() {
	    assertTrue(exampleRange.intersects(0.5, 1.0));
	}

	@Test
	public void intersectsShouldNotBeOutsideNegativeTwoAndTwo() {
	    assertFalse(exampleRange.intersects(-3.0, 3.0));
	}

	@Test
	public void intersectsShouldNotBeOutsideNegativeOneAndOne() {
	    assertFalse(exampleRange.intersects(-1.5, 1.5));
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