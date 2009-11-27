/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.components;

import static org.testng.AssertJUnit.assertSame;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/***********************************************************************
 * 
 */
public class LinesOutTest {
	LinesOut linesOutToTest;
	@BeforeMethod
	public void begin(){
		linesOutToTest = new LinesOut("0b", "1b", "2b", "3b", "0", "1", "2", "3");
	}
	
	@Test
	public void testConstructor(){
		assertSame("0b", linesOutToTest.getLine0_before());
		assertSame("1b", linesOutToTest.getLine1_before());
		assertSame("2b", linesOutToTest.getLine2_before());
		assertSame("3b", linesOutToTest.getLine3_before());

		assertSame("0", linesOutToTest.getLine0());
		assertSame("1", linesOutToTest.getLine1());
		assertSame("2", linesOutToTest.getLine2());
		assertSame("3", linesOutToTest.getLine3());
	}

	@Test
	public void testAddLine0_before(){
		String line0_before = "line0_before";
		linesOutToTest.setLine0_before(line0_before);
		assertSame(line0_before, linesOutToTest.getLine0_before());
	}
	@Test
	public void testAddLine1_before(){
		String line1_before = "line1_before";
		linesOutToTest.setLine1_before(line1_before);
		assertSame(line1_before, linesOutToTest.getLine1_before());
	}
	@Test
	public void testAddLine2_before(){
		String line2_before = "line2_before";
		linesOutToTest.setLine2_before(line2_before);
		assertSame(line2_before, linesOutToTest.getLine2_before());
	}
	@Test
	public void testAddLine3_before(){
		String line3_before = "line3_before";
		linesOutToTest.setLine3_before(line3_before);
		assertSame(line3_before, linesOutToTest.getLine3_before());
	}
	
	@Test
	public void testAddLine0(){
		String line0 = "line0";
		linesOutToTest.setLine0(line0);
		assertSame(line0, linesOutToTest.getLine0());
	}

	@Test
	public void testAddLine1(){
		String line1 = "line1";
		linesOutToTest.setLine1(line1);
		assertSame(line1, linesOutToTest.getLine1());
	}
	
	@Test
	public void testAddLine2(){
		String line2 = "line2";
		linesOutToTest.setLine2(line2);
		assertSame(line2, linesOutToTest.getLine2());
	}
	
	@Test
	public void testAddLine3(){
		String line3 = "line3";
		linesOutToTest.setLine3(line3);
		assertSame(line3, linesOutToTest.getLine3());
	}

	
}
