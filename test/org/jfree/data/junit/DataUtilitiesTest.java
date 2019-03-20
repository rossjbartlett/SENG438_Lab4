package org.jfree.data.junit;

import static org.junit.Assert.*;

import org.jfree.data.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DataUtilitiesTest
{
	private Values2D values;

	@Before // added to ensure PIT runs
	public void setUp()
	{
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values = testValues;
		
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
	}

	@After // added to ensure PIT runs
	public void tearDown()
	{
		values = null;
	}
	
	@Test(expected = NullPointerException.class) // added to make test pass, make PIT run
	public void testNullDataColumnTotal()
	{
		assertEquals(0.0, DataUtilities.calculateColumnTotal(null, 0), 0.0000001d);
	}
	
	@Test
	public void testValidDataAndColumnColumnTotal()
	{
		assertEquals(5.0, DataUtilities.calculateColumnTotal(values, 0), 0.0000001d);
	}
	
	@Test(expected=IndexOutOfBoundsException.class) // added to make test pass, make PIT run
	public void testValidDataInvalidColumnColumnTotal()
	{
		assertEquals(0.0, DataUtilities.calculateColumnTotal(values, 1), 0.0000001d);
	}
	
	
}
