package myTests;

import static org.junit.Assert.*;
import java.security.InvalidParameterException;
import org.jfree.data.Range;

import org.junit.*;

public class MyRangeTest {

	private Range exampleRange;
	private Range exampleRange2;
	private Range exampleRange3;
	private Range exampleRange4;
	private Range exampleRange5;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
	}

	@Before
	public void setUp() throws Exception{
		exampleRange = new Range(-1, 1);
		exampleRange2 = new Range(0, 2);
		exampleRange3 = new Range(3, 5);
		exampleRange4 = new Range(0.5, 1.5);
		exampleRange5 = new Range(0, 10);
	}

	//Tests for combine()
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Both args are null
	 */
	@Test
	public void combineNullRanges(){
		Range r = Range.combine(null,null);
		assertEquals("The combination of 2 null ranges should be null", null, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Arg1 is null, Arg2 is not
	 */
	@Test
	public void combineFirstRangeNull(){
		Range r = Range.combine(null,exampleRange);
		Range expected = new Range(-1, 1);
		assertEquals("The combination of 1 null range (first arg) and 1 valid should return the valid range", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Arg2 is null, Arg1 is not
	 */
	@Test
	public void combineSecondRangeNull(){
		Range r = Range.combine(exampleRange,null);
		Range expected = new Range(-1, 1);
		assertEquals("The combination of 1 null range (second arg) and 1 valid should return the valid range",expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Both args are the same object
	 */
	@Test
	public void combineSameRangeObject(){
		Range r = Range.combine(exampleRange,exampleRange);
		Range expected = new Range(-1, 1);
		assertEquals("The combination of the same range object should return the same object", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Overlapping ranges, first range is lower than second.
	 */
	@Test
	public void combineOverlappingRangesLowThenHigh(){
		Range r = Range.combine(exampleRange,exampleRange2);
		Range expected = new Range(-1, 2);
		assertEquals("The combination of (-1,1) and (0,2) overlapping ranges should be -1 to 2", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Overlapping ranges, second range is lower than first.
	 */
	@Test
	public void combineOverlappingRangesHighThenLow(){
		Range r = Range.combine(exampleRange2,exampleRange);
		Range expected = new Range(-1, 2);
		assertEquals("The combination of (0,2) and (-1,1) overlapping ranges should be -1 to 2", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Non-overlapping ranges, first range is lower than second.
	 */
	@Test
	public void combineNotOverlappingRangesLowThenHigh(){
		Range r = Range.combine(exampleRange2,exampleRange3);
		Range expected = new Range(0, 5);
		assertEquals("The combination of (0,2) and (3,5) overlapping ranges should be 0 to 5", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Non-overlapping ranges, second range is lower than first..
	 */
	@Test
	public void combineNotOverlappingRangesHighThenLow(){
		Range r = Range.combine(exampleRange3,exampleRange2);
		Range expected = new Range(0, 5);
		assertEquals("The combination of (3,5) and (0,2)  overlapping ranges should be 0 to 5", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: First range contains second range. 
	 */
	@Test
	public void combineContainedRangesFirstContainsSecond(){
		Range r = Range.combine(exampleRange2,exampleRange4);
		Range expected = new Range(0, 2);
		assertEquals("The combination of (0,2) and (0.5,1.5) ranges should be 0 to 2", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered:  Second Range contains first range. 
	 */
	@Test
	public void combineContainedRangesSecondContainsFirst(){
		Range r = Range.combine(exampleRange4,exampleRange2);
		Range expected = new Range(0, 2);
		assertEquals("The combination of (0.5,1.5) and (0,2) ranges should be 0 to 2", expected, r);
	}

	//Tests for expand()
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Null range
	 */
//	@Test(expected = InvalidParameterException.class)
	@Test(expected = IllegalArgumentException.class) // for PIT to work 
	public void expandNullRange(){
		Range.expand(null,1,1);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BB and BB
	 */
	@Test
	public void expandMarginsBBandBB(){
		Range r = Range.expand(exampleRange5,-0.1,-0.1);
		Range expected = new Range(1, 9);
		assertEquals("The expansion of (0,10) by margins (-0.1,-0.1)", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BB and AB
	 */
	@Test
	public void expandMarginsBBandAB(){
		Range r = Range.expand(exampleRange5,-0.1,0.1);
		Range expected = new Range(1, 11);
		assertEquals("The expansion of (0,10) by margins (-0.1,0.1)", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: BB and B
	 */
	@Test
	public void expandMarginsBBandB(){
		Range r = Range.expand(exampleRange5,-0.1,0);
		Range expected = new Range(1, 10);
		assertEquals("The expansion of (0,10) by margins (-0.1,0)", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: B and BB
	 */
	@Test
	public void expandMarginsBandBB(){
		Range r = Range.expand(exampleRange5,0,-0.1);
		Range expected = new Range(0, 9);
		assertEquals("The expansion of (0,10) by margins (0,-0.1)", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: B and AB
	 */
	@Test
	public void expandMarginsBandAB(){
		Range r = Range.expand(exampleRange5,0,0.1);
		Range expected = new Range(0, 11);
		assertEquals("The expansion of (0,10) by margins (0,0.1)", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered:B and B
	 */
	@Test
	public void expandMarginsBandB(){
		Range r = Range.expand(exampleRange5,0,0);
		Range expected = new Range(0, 10);
		assertEquals("The expansion of (0,10) by margins (0,0)", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: AB and BB
	 */
	@Test
	public void expandMarginsABandBB(){
		Range r = Range.expand(exampleRange5,0.1,-0.1);
		Range expected = new Range(-1, 9);
		assertEquals("The expansion of (0,10) by margins (0.1,-0.1)", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: AB and AB
	 */
	@Test
	public void expandMarginsABandAB(){
		Range r = Range.expand(exampleRange5,0.1,0.1);
		Range expected = new Range(-1, 11);
		assertEquals("The expansion of (0,10) by margins (0.1,0.1)", expected, r);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: AB and B
	 */
	@Test
	public void expandMarginsABandB(){
		Range r = Range.expand(exampleRange5,0.1,0);
		Range expected = new Range(-1, 10);
		assertEquals("The expansion of (0,10) by margins (0.1,0)", expected, r);
	}

	//tests for intersects()
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered:Ranges which have a lower bound that is 
	 * 		less than the upper bound of a Range object 
	 */
	@Test
	public void test_Intersects_Provided_Lowerbound_Less_Than_Upperbound()
	{
		Range toTest = new Range(0, 10);
		boolean result = toTest.intersects(2, 15);
		assertEquals("Testing intersects() where the provided lower bound < toTests upper bound", true, result);
		toTest = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Ranges which have a lower bound that is 
	 * 		equal the upper bound of a Range object
	 */
	@Test
	public void test_Intersects_Provided_Lowerbound_Equal_To_Upperbound()
	{
		Range toTest = new Range(0, 10);
		boolean result = toTest.intersects(10, 15);
		assertEquals("Testing intersects() where the provided lower bound == toTests upper bound", false, result);
		toTest = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Ranges which have a lower bound that 
	 * 		is greater than the upper bound of a Range object
	 */
	@Test
	public void test_Intersects_Provided_Lowerbound_Greater_Than_Upperbound()
	{
		Range toTest = new Range(0, 10);
		boolean result = toTest.intersects(11, 15);
		assertEquals("Testing intersects() where the provided lower bound > toTests upper bound", false, result);
		toTest = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Ranges which have a upper bound that 
	 * 		is less than the lower bound of a Range object
	 */
	@Test
	public void test_Intersects_provided_Upperbound_Less_Than_Lowerbound()
	{
		Range toTest = new Range(0, 10);
		boolean result = toTest.intersects(-10, -5);
		assertEquals("Testing intersects() where the provided Upper bound < toTests lower bound", false, result);
		toTest = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Ranges which have a upper bound that 
	 * 		is equal the lower bound of a Range object
	 */
	@Test
	public void test_Intersects_provided_Upperbound_Equal_To_Lowerbound()
	{
		Range toTest = new Range(0, 10);
		boolean result = toTest.intersects(-10, 0);
		assertEquals("Testing intersects() where the provided Upper bound == toTests lower bound", false, result);
		toTest = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Ranges which have a upper bound that 
	 * 		is greater than the lower bound of a Range object
	 */
	@Test
	public void test_Intersects_provided_Upperbound_Greater_Than_Lowerbound()
	{
		Range toTest = new Range(0, 10);
		boolean result = toTest.intersects(-10, 1);
		//		System.out.println(toTest);
		assertEquals("Testing intersects() where the provided Upper bound > toTests lower bound", true, result);
		toTest = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Ranges that are completely contained 
	 * 		within the range of a  Range object
	 */
	@Test
	public void test_Intersects_Provided_Range_Contained_Within_Range()
	{
		Range toTest = new Range(0, 10);
		boolean result = toTest.intersects(4, 8);
		assertEquals("Testing intersects() where the provided range is whithin toTests range", true, result);
		toTest = null;
	}

	
	//Tests for shift()
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Testing the shift function with 
	 * 		a Range object initialized to zero 
	 */
	@Test(expected=NullPointerException.class)
	public void test_Shift_Null_Range()
	{
		Range toTest = null;
		Range.shift(toTest, 5);
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Testing shift function with a value 
	 * 		that results in neither the upper or lower bound of a 
	 * 		Range object hitting zero 
	 */
	@Test
	public void test_Shift_Neither_Bound_Hits_Zero()
	{
		Range toTest = new Range(0, 10);
		Range compare = new Range(5, 15);
		toTest = Range.shift(toTest, 5);
		assertEquals("Testing shift where the shift results in niether bound hitting zero:", compare, toTest);
		toTest = null;
		compare = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Testing shift function where the upper 
	 * 		bound hits zero
	 */
	@Test
	public void test_Shift_Upper_Bound_Hits_Zero()
	{
		Range toTest = new Range(-20, -5);
		Range compare = new Range(-10, 0);
		toTest = Range.shift(toTest, 10);
		assertEquals("Testing shift where the shift results in the upper bound hitting zero:", compare, toTest);
		toTest = null;
		compare = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Testing shift where the lower bound hits zero
	 */
	@Test
	public void test_Shift_Lower_Bound_Hits_Zero()
	{
		Range toTest = new Range(-5, 5);
		Range compare = new Range(0, 15);
		toTest = Range.shift(toTest, 10);
		assertEquals("Testing shift where the shift results in the lower bound hitting zero:", compare, toTest);
		toTest = null;
		compare = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Testing shift where both lower and 
	 * 		upper bound hit zero 
	 */
	@Test
	public void test_Shift_Both_Bounds_Hit_Zero()
	{
		Range toTest = new Range(-5, -1);
		Range compare = new Range(0, 0);
		toTest = Range.shift(toTest, 10);
		assertEquals("Testing shift where the shift results in both bounds hitting zero:", compare, toTest);
		toTest = null;
		compare = null;
	}
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage.
	 */
	@Test
	public void test_Shift_Crosses_Zero_Enabled()
	{
		Range toTest = new Range(-5, -1);
		Range compare = new Range(-2, 2);
		toTest = Range.shift(toTest, 3, true);
		assertEquals("Testing shift that crosses zero, with zeroCrossing enabled", compare, toTest);
	}

	
	//tests for contains()
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Values greater than the upper 
	 * 		bound of a Range object 
	 */
	@Test
	public void test_Contains_Greater_Than_Upper()
	{
		Range toTest = new Range(-5.0, 10);
		boolean result = toTest.contains(15);
		assertEquals("Testing contains with a value greater than the upper bound of range", false, result);
		toTest = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Values equal to the upper 
	 * 		bound of a Range object 
	 */
	@Test
	public void test_Contains_Equal_To_Upper()
	{
		Range toTest = new Range(-5.0, 10);
		boolean result = toTest.contains(10.0);
		assertEquals("Testing contains with a value equal to the upper bound of range", true, result);
		toTest = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Values somewhere in the 
	 * 		middle of a Range object 
	 */
	@Test
	public void test_Contains_In_Middle()
	{
		Range toTest = new Range(-5.0, 10);
		boolean result = toTest.contains(3.0);
		assertEquals("Testing contains with a value in the middle of the specified range:", true, result);
		toTest = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Values that are equal to the lower 
	 * 		bound of a Range object 
	 */
	@Test
	public void test_Contains_Equal_To_Lower()
	{
		Range toTest = new Range(-5.0, 10);
		boolean result = toTest.contains(-5.0);
		assertEquals("Testing contains with a value equal to the lower bound of range", true, result);
		toTest = null;
	}
	/*
	 * Test type: Black Box
	 * Strategy followed: Equivalence Partitions
	 * 		Partition Covered: Values that are less than the 
	 * 		lower bound of a Range Object 
	 */
	@Test
	public void test_Contains_Less_Than()
	{
		Range toTest = new Range(0, 10);
		boolean result = toTest.contains(-5.0);
		assertEquals("Testing contains() with a value that is less than the lower bound of range", false, result);
		toTest = null;
	}
	
	//tests for expandToInclude(), added for lab3
	//note that expandToInclude(range,null) cant be tested, cant pass in null for primitive type double
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 */
	@Test
	public void test_expandToInclude_valBelowRange(){
		Range r = Range.expandToInclude(exampleRange5, -5); //exampleRange5 is (0,10)
		Range expected = new Range(-5, 10);
		assertEquals("The expansion of (0,10) to inlcude -5", expected, r);
	}
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 */
	@Test
	public void test_expandToInclude_valAboveRange(){
		Range r = Range.expandToInclude(exampleRange5, 15); //exampleRange5 is (0,10)
		Range expected = new Range(0, 15);
		assertEquals("The expansion of (0,10) to inlcude 15", expected, r);
	}
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 */
	@Test
	public void test_expandToInclude_valInRange(){
		Range r = Range.expandToInclude(exampleRange5, 5); //exampleRange5 is (0,10)
		Range expected = new Range(0, 10);
		assertEquals("The expansion of (0,10) to inlcude 5", expected, r);
	}
	
	//tests for constrain(), added for lab3
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 */
	@Test
	public void test_constrain_valInRange(){
		double r = exampleRange5.constrain(3.2); //exampleRange5 is (0,10)
		double expected = 3.2;
		assertEquals("The contrain of 3.2 within (0,10)", expected, r, 0.01d);
	}
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 */
	@Test
	public void test_constrain_valAboveRange(){
		double r = exampleRange5.constrain(13.3); //exampleRange5 is (0,10)
		double expected = 10.0;
		assertEquals("The contrain of 3 within (0,10)", expected, r, 0.01d);
	}
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 */
	@Test
	public void test_constrain_valBelowRange(){
		double r = exampleRange5.constrain(-4.5); //exampleRange5 is (0,10)
		double expected = 0.0;
		assertEquals("The contrain of 3 within (0,10)", expected, r, 0.01d);
	}
	
	//tests for getCentralValue(), added for lab3
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 */
	@Test
	public void test_getCentralValue(){
		double r = exampleRange5.getCentralValue(); //exampleRange5 is (0,10)
		double expected = 5.0;
		assertEquals("The central value of (0,10)", expected, r, 0.01d);
	}
	
	//tests for constructor, added for lab3
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_ctor_lowerBiggerThanUpper(){
		Range r = new Range(10, 5);
	}
	
	//tests for hashCode(), added for lab3
	/*
	 * This test added for lab3
	 * Test type: White Box
	 * Strategy followed: Examined the implementation and developed a test cases for missing branches to increase coverage
	 */
	@Test
	public void test_hashCode(){
		int r = exampleRange5.hashCode(); //exampleRange5 is (0,10)
		int expected = 1076101120;
		assertEquals("The hashCode of (0,10)", expected, r);	}


	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception { 
	}

}