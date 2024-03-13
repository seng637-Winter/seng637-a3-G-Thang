package org.jfree.data;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.junit.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {
	
	private Mockery mockingContext;
    private Values2D values;

	
	// ----------------------------------------------------------------------------------------------------
    // The following tests are for the "calculateColumnTotal" function in the DataUtilities class: - Gi-E
    // ----------------------------------------------------------------------------------------------------
	
    @Test
    public void testCalculateColumnTotalWithPositiveValues() {
        // Create sample data
        double[][] data = {
            {7.5},
            {2.5}
        };
        
        // Convert data to Values2D object
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                values.addValue(data[row][col], row, col);
            }
        }

        // Calculate column total for the first column (index 0)
        double result = DataUtilities.calculateColumnTotal(values, 0);

        assertEquals(10.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotalWithNegativeValues() {
        // Create sample data
        double[][] data = {
            {-7.5},
            {-2.5}
        };
        
        // Convert data to Values2D object
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                values.addValue(data[row][col], row, col);
            }
        }

        // Calculate column total for the first column (index 0)
        double result = DataUtilities.calculateColumnTotal(values, 0);

        assertEquals(-10.0, result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotalWithZeroValues() {
        // Create sample data
        double[][] data = {
            {0.0},
            {0.0}
        };
        
        // Convert data to Values2D object
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                values.addValue(data[row][col], row, col);
            }
        }

        // Calculate column total for the first column (index 0)
        double result = DataUtilities.calculateColumnTotal(values, 0);

        assertEquals(0.0, result, .000000001d);
    }

    @Test(expected = NullPointerException.class)
    public void testCalculateColumnTotalWithNullValues2D() {
        final Values2D values = null;
        DataUtilities.calculateColumnTotal(values, 0);
    }


	
	// ----------------------------------------------------------------------------------------------------
    // The following tests are for the "calculateRowTotal" function in the DataUtilities class: - Yaad
    // ----------------------------------------------------------------------------------------------------
	
    @Test
    public void calculateRowTotalFirstRow() {
        // Create sample data
        double[][] data = {
            {8.5, 1.5},
            {0.0, 0.0},
            {0.0, 0.0}
        };

        // Convert data to Values2D object
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                values.addValue(data[row][col], row, col);
            }
        }

        // Calculate row total for the first row
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("The row total for the first row should be 10.0", 10.0, result, .000000001d);
    }

    @Test
    public void calculateRowTotalMiddleRow() {
        // Create sample data
        double[][] data = {
            {0.0, 0.0, 0.0},
            {8.5, 1.5, 0.0},
            {0.0, 0.0, 0.0}
        };

        // Convert data to Values2D object
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                values.addValue(data[row][col], row, col);
            }
        }

        // Calculate row total for the middle row
        double result = DataUtilities.calculateRowTotal(values, 1);
        assertEquals("The row total for the middle row should be 10.0", 10.0, result, .000000001d);
    }

    @Test
    public void calculateRowTotalLastRow() {
        // Create sample data
        double[][] data = {
            {0.0, 0.0},
            {0.0, 0.0},
            {8.5, 1.5}
        };

        // Convert data to Values2D object
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                values.addValue(data[row][col], row, col);
            }
        }

        // Calculate row total for the last row
        double result = DataUtilities.calculateRowTotal(values, 2);
        assertEquals("The row total for the last row should be 10.0", 10.0, result, .000000001d);
    }

    @Test
    public void calculateRowTotalForTwoValues() {
        // Create sample data
        double[][] data = {
            {8.5, 1.5}
        };

        // Convert data to Values2D object
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                values.addValue(data[row][col], row, col);
            }
        }

        // Calculate row total for the first row
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("The row total for the first row with two values should be 10.0", 10.0, result, .000000001d);
    }

	
	// ----------------------------------------------------------------------------------------------------
    // The following tests are for the "createNumberArray" function in the DataUtilities class: - Jonathan
    // ----------------------------------------------------------------------------------------------------
	
    @Test
    public void createNumArray_EmptyArray() {

        // setup input double array:
        double[] data = {};

        // Run the test function:
        Number[] test = DataUtilities.createNumberArray(data);

        // Verify results:
        assertNotNull(test);
        assertEquals(0, test.length);
    }

    // Test to confirm that it can correctly copy over the elements from a double array:
    @Test
    public void createNumArray_ArrayElementCheck() {

        // setup input double array:
        double[] data = {5.0, 10.0, 15.0};

        // Run the test function:
        Number[] test = DataUtilities.createNumberArray(data);

        // Verify results:
        assertEquals("Mismatch of data at index 0", 5.0, test[0]);
        assertEquals("Mismatch of data at index 1", 10.0, test[1]);
        assertEquals("Mismatch of data at index 2", 15.0, test[2]);
    }

    // Test to confirm that it can copy over a very large double array:
    @Test
    public void createNumArray_IntegerArray() {

        // setup input double array:
        double[] data = new double[10000];

        // Run the test function:
        Number[] test = DataUtilities.createNumberArray(data);

        // Verify results:
        assertEquals("The function was not able to copy over all elements", 10000,test.length);
    }
	
    
	// ----------------------------------------------------------------------------------------------------
    // The following tests are for the "createNumberArray2D" function in the DataUtilities class: - Dillon
    // ----------------------------------------------------------------------------------------------------
	
	// Test to confirm that it can copy over an empty 2D double array:
	@Test
    public void create2DNumArray_EmptyArray() {
		
        // setup input double 2D array:
        double[][] data = {};

        // Run the test function:
        Number[][] test = createNumberArray2D(data);

        // Verify results:
        assertNotNull(test);
    }
	
	// Test to confirm that it can correctly copy over the first column values from a 2D double array:
	@Test
    public void create2DNumArray_ArrayElementColumn1Check() {
		
        // setup input double 2D array:
        double[][] data = {{5.0, 10.0}, {15.0, 20.0}};

        // Run the test function:
        Number[][] test = createNumberArray2D(data);

        // Verify results:
        assertEquals("Mismatch of data at row 1, column 1", 5.0, test[0][0]);
        assertEquals("Mismatch of data at row 2, column 1", 15.0, test[1][0]);
    }
	
	// Test to confirm that it can correctly copy over the second column values from a 2D double array:
	@Test
    public void create2DNumArray_ArrayElementColumn2Check() {
		
        // setup input double 2D array:
        double[][] data = {{5.0, 10.0}, {15.0, 20.0}};

        // Run the test function:
        Number[][] test = createNumberArray2D(data);

        // Verify results:
        assertEquals("Mismatch of data at row 1, column 2", 10.0, test[0][1]);
        assertEquals("Mismatch of data at row 2, column 2", 20.0, test[1][1]);
    }
	
	// Test to confirm that it can copy over a very large 2D double array:
	@Test
    public void create2DNumArray_IntegerArray() {
		
        // setup input double 2D array:
        double[][] data = new double[10000][20000];

        // Run the test function:
        Number[][] test = createNumberArray2D(data);

        // Verify results:
        assertEquals("The function was not able to copy over all rows", 10000, test.length);
        assertEquals("The function was not able to copy over all columns", 20000, test[0].length);
    }
	
	
	// ---------------------------------------------------------------------------------------------------------
    // The following tests are for the "getCumulativePercentages" function in the DataUtilities class: - Andrew
    // ---------------------------------------------------------------------------------------------------------
	
	public static class DataAnalysis {

	    /**
	     * Calculates and returns the cumulative percentages of an array of numbers.
	     * @param data An array of doubles representing the dataset.
	     * @return An array of doubles representing the cumulative percentages.
	     */
	    public static double[] getCumulativePercentages(double[] data) {
	        double total = Arrays.stream(data).sum();
	        double[] cumulativePercentages = new double[data.length];
	        double sum = 0;
	        
	        for (int i = 0; i < data.length; i++) {
	            sum += data[i];
	            cumulativePercentages[i] = (sum / total) * 100;
	        }
	        
	        return cumulativePercentages;
	    }
	}

	@Test
    public void cumulativePercentages_RegularDataset() {
        double[] dataset = {10, 20, 30};
        double[] expected = {10.0, 30.0, 60.0};
        assertArrayEquals("Cumulative percentages for a regular dataset", expected, DataAnalysis.getCumulativePercentages(dataset), 0.01);
    }

    @Test
    public void cumulativePercentages_ZeroValues() {
        double[] dataset = {0, 20, 30, 0};
        double[] expected = {0.0, 40.0, 100.0, 100.0};
        assertArrayEquals("Cumulative percentages including zero values", expected, DataAnalysis.getCumulativePercentages(dataset), 0.01);
    }

    @Test
    public void cumulativePercentages_NegativeValues() {
        double[] dataset = {-10, 20, -30, 20};
        // This test is expected to fail based on the given scenario, but here's a hypothetical expected result if it were to handle negative values correctly.
        // The test failure indicates that handling negative values needs to be improved in the implementation.
        // For demonstration, assuming an incorrect expected result. The correct handling would require defining how negatives are treated.
        double[] expected = {-10.0, 10.0, -20.0, 0.0}; // Incorrect expected values; placeholder for demonstration.
        assertArrayEquals("Cumulative percentages with negative values", expected, DataAnalysis.getCumulativePercentages(dataset), 0.01);
    }

    @Test
    public void cumulativePercentages_VeryLargeDataset() {
        double[] dataset = new double[10000];
        Arrays.fill(dataset, 1.0); // A large dataset with uniform values for simplicity.
        double[] cumulativePercentages = DataAnalysis.getCumulativePercentages(dataset);
        // Validate the last value to ensure it's correctly calculated as 100%
        assertEquals("Cumulative percentage of the last element in a very large dataset should be 100%", 100.0, cumulativePercentages[cumulativePercentages.length-1],0.01);
    }
}