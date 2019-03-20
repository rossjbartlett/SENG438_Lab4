package org.jfree.data.junit;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class MyDataUtilitiesTest {

	// tests for calculateColumnTotal()
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: A null data table
	 */
	@Test(expected = InvalidParameterException.class)
	public void calculateColumnTotalForNullTable() {
		DataUtilities.calculateColumnTotal(null, 0);
		// throws NullPointerException instead
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Boundary value testing for below lower bound
	 */
	@Test
	public void calculateColumnTotalBLB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				oneOf(values).getRowCount();
				will(returnValue(5));
			}
		});
		// exercise - testing index -1
		double result = DataUtilities.calculateColumnTotal(values, -1);
		// verify
		//docs say it will return 0 upon invalid input
		//instead, it does try to get the values at index=-1, causing unexpected invocation jMock.exception 
		assertEquals("Attempting to acess column -1 should return 0",0, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BVT at lower bound
	 */
	@Test
	public void calculateColumnTotalLB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(5));
				one(values).getValue(0, 0);
				will(returnValue(1));
				one(values).getValue(1, 0);
				will(returnValue(1));
				one(values).getValue(2, 0);
				will(returnValue(1));
				one(values).getValue(3, 0);
				will(returnValue(1));
				one(values).getValue(4, 0);
				will(returnValue(1));
			}
		});
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 0);
		// verify
		assertEquals("Summing column 0 should return 5",5, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BVT above lower bound
	 */
	@Test
	public void calculateColumnTotalALB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(5));
				one(values).getValue(0, 1);
				will(returnValue(1));
				one(values).getValue(1, 1);
				will(returnValue(1));
				one(values).getValue(2, 1);
				will(returnValue(1));
				one(values).getValue(3, 1);
				will(returnValue(1));
				one(values).getValue(4, 1);
				will(returnValue(1));
			}
		});
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 1);
		// verify
		assertEquals("Acessing column 1 should return 5",5, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BVT below upper bound
	 */
	@Test
	public void calculateColumnTotalBUB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(5));
				one(values).getValue(0, 3);
				will(returnValue(1));
				one(values).getValue(1, 3);
				will(returnValue(1));
				one(values).getValue(2, 3);
				will(returnValue(1));
				one(values).getValue(3, 3);
				will(returnValue(1));
				one(values).getValue(4, 3);
				will(returnValue(1));
			}
		});
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 3);
		// verify
		assertEquals("Acessing column 3 should return 5",5, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BVT at upper bound
	 */
	@Test
	public void calculateColumnTotalUB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(5));
				one(values).getValue(0, 4);
				will(returnValue(1));
				one(values).getValue(1, 4);
				will(returnValue(1));
				one(values).getValue(2, 4);
				will(returnValue(1));
				one(values).getValue(3, 4);
				will(returnValue(1));
				one(values).getValue(4, 4);
				will(returnValue(1));
			}
		});
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 4);
		// verify
		assertEquals("Acessing column 4 should return 5",5, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BVT above upper bound
	 */
	@Test
	public void calculateColumnTotalAUB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(5));
			}
		});
		// exercise - testing index > numCols
		double result = DataUtilities.calculateColumnTotal(values, 5);
		// verify
		//docs say it will return 0 upon invalid input
		//instead, it does try to get the values at index=5, causing unexpected invocation jMock.exception 
		assertEquals("Acessing column 5 (out of bounds) should return 0",0, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Partition within valid range
	 */
	@Test
	public void calculateColumnTotalNOM() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(5));
				one(values).getValue(0, 2);
				will(returnValue(1));
				one(values).getValue(1, 2);
				will(returnValue(1));
				one(values).getValue(2, 2);
				will(returnValue(1));
				one(values).getValue(3, 2);
				will(returnValue(1));
				one(values).getValue(4, 2);
				will(returnValue(1));
			}
		});
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 2);
		// verify
		assertEquals("Acessing column 2 should return 5",5, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 * Testing calculateColumnTotal where some of the columns contain null values
	 */
	@Test
	public void testing_calculateColumnTotal_with_null_values_in_column()
	{
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() 
		{
			{
				one(values).getRowCount();
				will(returnValue(5));
				
				one(values).getValue(0, 0);
				will(returnValue(5));
				
				one(values).getValue(1, 0);
				will(returnValue(4));
				
				one(values).getValue(2, 0);
				will(returnValue(null));
				
				one(values).getValue(3, 0);
				will(returnValue(1));
				
				one(values).getValue(4, 0);
				will(returnValue(0));
			}
		});
		
		double result = DataUtilities.calculateColumnTotal(values, 0);
		
		assertEquals("Acessing column 0 should return 10",10, result, .000000001d);
	}

	
	// tests for calculateRowTotal()
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: A null data table
	 */
	@Test(expected = InvalidParameterException.class)
	public void calculateRowTotalForNullTable() {
		DataUtilities.calculateRowTotal(null, 0);
		// throws NullPointerException instead
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Boundary value testing for below lower bound
	 */
	@Test
	public void calculateRowTotalBLB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				oneOf(values).getColumnCount();
				will(returnValue(5));
			}
		});
		// exercise - test at row=-1
		double result = DataUtilities.calculateRowTotal(values, -1);
		// verify
		//docs say it will return 0 upon invalid input
		//instead, it does try to get the values at index=-1, causing unexpected invocation jMock.exception 
		assertEquals("Acessing row -1 should return 0",0, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BVT at lower bound
	 */
	@Test
	public void calculateRowTotalLB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(5));
				one(values).getValue(0, 0);
				will(returnValue(1));
				one(values).getValue(0, 1);
				will(returnValue(1));
				one(values).getValue(0, 2);
				will(returnValue(1));
				one(values).getValue(0, 3);
				will(returnValue(1));
				one(values).getValue(0, 4);
				will(returnValue(1));
			}
		});
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 0);
		// verify
		assertEquals("Summing row 0 should return 5",5, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BVT above lower bound
	 */
	@Test
	public void calculateRowTotalALB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(5));
				one(values).getValue(1, 0);
				will(returnValue(1));
				one(values).getValue(1, 1);
				will(returnValue(1));
				one(values).getValue(1, 2);
				will(returnValue(1));
				one(values).getValue(1, 3);
				will(returnValue(1));
				one(values).getValue(1, 4);
				will(returnValue(1));
			}
		});
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 1);
		// verify
		assertEquals("Summing row 1 should return 5",5, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BVT below lower bound
	 */
	@Test
	public void calculateRowTotalBUB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(5));
				one(values).getValue(3, 0);
				will(returnValue(1));
				one(values).getValue(3, 1);
				will(returnValue(1));
				one(values).getValue(3, 2);
				will(returnValue(1));
				one(values).getValue(3, 3);
				will(returnValue(1));
				one(values).getValue(3, 4);
				will(returnValue(1));
			}
		});
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 3);
		// verify
		assertEquals("Summing row 3 should return 5",5, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BVT at upper bound
	 */
	@Test
	public void calculateRowTotalUB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(5));
				one(values).getValue(4, 0);
				will(returnValue(1));
				one(values).getValue(4, 1);
				will(returnValue(1));
				one(values).getValue(4, 2);
				will(returnValue(1));
				one(values).getValue(4, 3);
				will(returnValue(1));
				one(values).getValue(4, 4);
				will(returnValue(1));
			}
		});
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 4);
		// verify
		assertEquals("Summing row 4 should return 5",5, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BVT above upper bound
	 */
	@Test
	public void calculateRowTotalAUB() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(5));
			}
		});
		// exercise - test at row > numRows
		double result = DataUtilities.calculateRowTotal(values, 5);
		// verify
		//docs say it will return 0 upon invalid input
		//instead, it does try to get the values at index=-5, causing unexpected invocation jMock.exception 
		assertEquals("Attempting to access row 5 (out of bound) should return 0",0, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Partition within valid range
	 */
	@Test
	public void calculateRowTotalNOM() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(5));
				one(values).getValue(2, 0);
				will(returnValue(1));
				one(values).getValue(2, 1);
				will(returnValue(1));
				one(values).getValue(2, 2);
				will(returnValue(1));
				one(values).getValue(2, 3);
				will(returnValue(1));
				one(values).getValue(2, 4);
				will(returnValue(1));
			}
		});
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 2);
		// verify
		assertEquals("Summing row 2 should return 5",5, result, .000000001d);
		// tear-down: NONE in this test method
	}
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 * Testing calculateRowTotal where some of the rows contain null values
	 */
	@Test
	public void testing_calculateRowTotal_with_null_values_in_row()
	{
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations()
		{
			{
				one(values).getColumnCount();
				will(returnValue(5));
				
				one(values).getValue(0, 0);
				will(returnValue(6));
				
				one(values).getValue(0, 1);
				will(returnValue(4));
				
				one(values).getValue(0, 2);
				will(returnValue(3));
				
				one(values).getValue(0, 3);
				will(returnValue(null));
				
				one(values).getValue(0, 4);
				will(returnValue(null));
			}
				
		});
		
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		assertEquals("Acessing row 0 should return 13",13, result, .000000001d);
	}
	
	// tests for createNumberArray()
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Null Data
	 */
	@Test(expected = InvalidParameterException.class)
	public void createNumberArrayNull() {
		DataUtilities.createNumberArray(null);
		// throws IllegalArgumentException instead
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Empty (non-null) data
	 */
	@Test
	public void createNumberArrayEmpty() {
		double a[] = {};
		Number r[] = DataUtilities.createNumberArray(a);
		Number expected[] = {};
		assertArrayEquals("Arrays should be equal",expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: A valid double array
	 */
	@Test
	public void createNumberArrayValid() {
		double a[] = { 1.5, 2.5, 3.5, 4.5};
		Number r[] = DataUtilities.createNumberArray(a);
		Number expected[] = { 1.5, 2.5, 3.5, 4.5};
		assertArrayEquals("Arrays should be equal",expected, r);
	}

	
	// tests for createNumberArray2D()
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Null Data
	 */
	@Test(expected = InvalidParameterException.class)
	public void createNumberArray2DNull() {
		DataUtilities.createNumberArray2D(null);
		// throws IllegalArgumentException instead
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Empty (non-null) data
	 */
	@Test
	public void createNumberArray2DEmpty() {
		double a[][] = {{}};
		Number r[][] = DataUtilities.createNumberArray2D(a);
		Number expected[][] = {{}};
		assertArrayEquals("Arrays should be equal",expected, r);		
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: A valid double 2D-array
	 */
	@Test
	public void createNumberArray2DValid() {
		double a[][] = { { 1.0, 2.0, 3.0 }, { 1.5, 2.5, 3.5 }, { -2.0, -1.0, 0.0 } };
		Number r[][] = DataUtilities.createNumberArray2D(a);
		Number expected[][] = { { 1.0, 2.0, 3.0 }, { 1.5, 2.5, 3.5 }, { -2.0, -1.0, 0.0 } };
		assertArrayEquals("Arrays should be equal",expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Array with some null inner-arrays 
	 */
	@Test(expected = InvalidParameterException.class)
	public void createNumberArray2DInnerNull() {
		double a[][] = { { 1.0, 2.0, 3.0 }, null, { -2.0, -1.0, 0.0 } };
		DataUtilities.createNumberArray2D(a);
		// throws IllegalArgumentException instead
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Array with some empty inner-arrays
	 */
	@Test
	public void createNumberArray2DInnerEmpty() {
		double a[][] = { { 1.0, 2.0, 3.0 }, {}, { -2.0, -1.0, 0.0 } };
		Number r[][] = DataUtilities.createNumberArray2D(a);
		Number expected[][] = { { 1.0, 2.0, 3.0 }, {}, { -2.0, -1.0, 0.0 } };
		assertArrayEquals("Arrays should be equal",expected, r);
	}


	//tests for getCumulativePercentage()
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Null Table
	 */
	@Test(expected = InvalidParameterException.class)
	public void getCumulativePercentageForNullTable() {
		DataUtilities.getCumulativePercentages(null);
		// throws NullPointerException instead
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Valid Data
	 */
	@Test
	public void getCumulativePercentageNOM(){
		//Setup
		Mockery mockingContext = new Mockery();
		final KeyedValues values = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{

				atLeast(1).of(values).getItemCount();
				will(returnValue(3));

				//key/value pair 
				atLeast(1).of(values).getKey(0); //get Key at index 0
				will(returnValue(0));			
				atLeast(1).of(values).getValue(0); // get value at key=0
				will(returnValue(5));

				//key/value pair 
				atLeast(1).of(values).getKey(1); 
				will(returnValue(1));			
				atLeast(1).of(values).getValue(1); 
				will(returnValue(9));

				//key/value pair 
				atLeast(1).of(values).getKey(2);
				will(returnValue(2));			
				atLeast(1).of(values).getValue(2);
				will(returnValue(2));

			}
		});
		Mockery mockingContext2 = new Mockery();
		final KeyedValues expected = mockingContext2.mock(KeyedValues.class);
		mockingContext2.checking(new Expectations() {
			{

				atLeast(1).of(expected).getItemCount();
				will(returnValue(3));

				//key/value pair 
				atLeast(1).of(expected).getKey(0); //get Key at index 0
				will(returnValue(0));			
				atLeast(1).of(expected).getValue(0); // get value at key=0
				will(returnValue(0.3125)); // this is the expected cumulative percentage 

				//key/value pair 
				atLeast(1).of(expected).getKey(1); 
				will(returnValue(1));			
				atLeast(1).of(expected).getValue(1); 
				will(returnValue(0.875));

				//key/value pair 
				atLeast(1).of(expected).getKey(2);
				will(returnValue(2));			
				atLeast(1).of(expected).getValue(2);
				will(returnValue(1.0));

			}
		});
		// exercise
		KeyedValues result = DataUtilities.getCumulativePercentages(values);
		// verify
		for(int i = 0 ; i < result.getItemCount(); i++){
			int key = (int) result.getKey(i);
			assertEquals("Expect each result value == expected value",expected.getValue(key).doubleValue(), result.getValue(0).doubleValue(), .000000001d);
		}
		// tear-down: NONE in this test method

	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Divide By 0 Logical Error
	 */
	@Test(expected = ArithmeticException.class)
	public void getCumulativePercentageDivByZero(){
		//Setup
		Mockery mockingContext = new Mockery();
		final KeyedValues values = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{

				atLeast(1).of(values).getItemCount();
				will(returnValue(2));

				//key/value pair 
				atLeast(1).of(values).getKey(0); //get Key at index 0
				will(returnValue(0));			
				atLeast(1).of(values).getValue(0); // get value at key=0
				will(returnValue(0));


				//key/value pair 
				atLeast(1).of(values).getKey(1);
				will(returnValue(1));			
				atLeast(1).of(values).getValue(1);
				will(returnValue(0));

			}
		});
		Mockery mockingContext2 = new Mockery();
		final KeyedValues expected = mockingContext2.mock(KeyedValues.class);
		mockingContext2.checking(new Expectations() {
			{

				atLeast(1).of(expected).getItemCount();
				will(returnValue(2));

				//key/value pair 
				atLeast(1).of(expected).getKey(0); //get Key at index 0
				will(returnValue(0));			
				atLeast(1).of(expected).getValue(0); // get value at key=0
				will(returnValue(0)); // this is the expected cumulative percentage 

				//key/value pair 
				atLeast(1).of(expected).getKey(1);
				will(returnValue(1));			
				atLeast(1).of(expected).getValue(1);
				will(returnValue(0));

			}
		});
		// exercise
		//expect a div by zero (arithmetic exception) here
		//but it is not thrown, they must check if numerator is 0 then they don't do the division
		DataUtilities.getCumulativePercentages(values);
	}
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 * Testing getCumulativePercentage where the KeyedValues contain null values
	 */
	@Test
	public void testing_getCumulativePercentage_with_null_value()
	{
		Mockery mockingContext1 = new Mockery();
		final KeyedValues testCase = mockingContext1.mock(KeyedValues.class);
		mockingContext1.checking(new Expectations() 
		{
			{
				atLeast(1).of(testCase).getItemCount();
				will(returnValue(4));
				
				atLeast(1).of(testCase).getKey(0);
				will(returnValue(0));
				atLeast(1).of(testCase).getValue(0);
				will(returnValue(2));
				
				atLeast(1).of(testCase).getKey(1);
				will(returnValue(1));
				atLeast(1).of(testCase).getValue(1);
				will(returnValue(3));
				
				atLeast(1).of(testCase).getKey(2);
				will(returnValue(2));
				atLeast(1).of(testCase).getValue(2);
				will(returnValue(5));
				
				atLeast(1).of(testCase).getKey(3);
				will(returnValue(3));
				atLeast(1).of(testCase).getValue(3);
				will(returnValue(null));
			}
		});

		// exercise
		KeyedValues result = DataUtilities.getCumulativePercentages(testCase);
		// verify
		assertEquals("Expecting index 0 to have a cumulitive percentage of 0.2", 0.2, result.getValue(0).doubleValue(), .000000001d);
		assertEquals("Expecting index 1 to have a cumulitive percentage of 0.5", 0.5, result.getValue(1).doubleValue(), .000000001d);
		assertEquals("Expecting index 2 to have a cumulitive percentage of 1.0", 1.0, result.getValue(2).doubleValue(), .000000001d);
		assertEquals("Expecting index 3 to have a cumulitive percentage of 1.0", 1.0, result.getValue(3).doubleValue(), .000000001d);
				// tear-down: NONE in this test method
		
	}

}


