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
public class SourceTest {
	Source sourceToTest;
	@BeforeMethod
	public void begin(){
		sourceToTest = new Source();
	}
	
	@Test
	public void testAddSourceCode(){
		String sourceCode = "if (true) {}";
		sourceToTest.setSourceCode(sourceCode);
		assertSame(sourceCode, sourceToTest.getSourceCode());
	}
	@Test
	public void testAddUserId(){
		long userId = 1;
		sourceToTest.setUserId(userId);
		assertSame(userId, sourceToTest.getUserId());
	}

}
