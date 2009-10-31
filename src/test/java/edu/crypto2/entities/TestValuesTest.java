/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.entities;

import static org.testng.AssertJUnit.assertSame;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/***********************************************************************
 * 
 */
public class TestValuesTest {
	TestValues testValuesToTest;
	@BeforeMethod
	public void begin(){
		testValuesToTest = new TestValues();
	}
	
	@Test
	public void testAddSubBytes_TestValue(){
		String subBytesTestValue = "193de3bea0f4e22b9ac68d2ae9f84808";
		testValuesToTest.setSubBytes_TestValue(subBytesTestValue);
		assertSame(subBytesTestValue, testValuesToTest.getSubBytes_TestValue());
	}
	@Test
	public void testAddShiftRows_TestValue(){
		String shiftRowsTestValue = "d42711aee0bf98f1b8b45de51e415230";
		testValuesToTest.setShiftRows_TestValue(shiftRowsTestValue);
		assertSame(shiftRowsTestValue, testValuesToTest.getShiftRows_TestValue());
	}
	@Test
	public void testAddMixColumns_TestValue(){
		String mixColumnsTestValue = "d4bf5d30e0b452aeb84111f11e2798e5";
		testValuesToTest.setMixColumns_TestValue(mixColumnsTestValue);
		assertSame(mixColumnsTestValue, testValuesToTest.getMixColumns_TestValue());
	}
	@Test
	public void testAddKeyExpansion_TestValue(){
		String keyExpansion_TestValue = "2b7e151628aed2a6abf7158809cf4f3c";
		testValuesToTest.setKeyExpansion_TestValue(keyExpansion_TestValue);
		assertSame(keyExpansion_TestValue, testValuesToTest.getKeyExpansion_TestValue());
	}

	@Test
	public void testAddAddRoundKey_TestValue(){
		String addRoundKey_TestValue = "2b7e151628aed2a6abf7158809cf4f3c";
		testValuesToTest.setAddRoundKey_TestValue(addRoundKey_TestValue);
		assertSame(addRoundKey_TestValue, testValuesToTest.getAddRoundKey_TestValue());
	}

	@Test
	public void testAddMetaTransformation_TestValue(){
		String metaTransformation_TestValue = "Test";
		testValuesToTest.setMetaTransformation_TestValue(metaTransformation_TestValue);
		assertSame(metaTransformation_TestValue, testValuesToTest.getMetaTransformation_TestValue());
	}
	

}
