/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.integration;


import junit.framework.TestCase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;


import edu.crypto2.services.SourceDao;
import edu.crypto2.services.TestValuesDao;



import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;



/***********************************************************************
 * 
 */
public class SeleniumIntegrationTest {
	protected Selenium selenium;
	int min_interval = 2000;
	int max_interval = 6000;
	int PAGE_LOAD_INTERVAL = 2000;
	boolean firstTest_Loaded = false;
	
	//Windows XP:
	//*iexplore - ok
	//*iehta - ok
	//*chrome - ok
	//*firefox - ok
	//*firefox3 - ok
	//*googlechrome - ok
	//*opera - ok
	
	// Linux (Fedora 11)
	//*chrome - ok
	//*firefox - ok

	
    @BeforeClass
    @Parameters(value = "SeleniumBrowser")
    public void setUp(@Optional("*iehta") String browser_name) {
    	System.out.println("setUp port---------> " + SeleniumServer.getDefaultPort());
    	System.out.println("setUp ---------> " + browser_name);
        selenium = new DefaultSelenium(
        		"localhost", 
        		SeleniumServer.getDefaultPort(), 
        		browser_name, 
        		"http://localhost:8080/crypto2/");
        selenium.start();
        selenium.setSpeed("2000");
        selenium.windowMaximize();
	}
	
    @Test
    public void testMetaTransformationPG() {
    	try {
			selenium.open("http://localhost:8080/crypto2/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		

   		PAGE_LOAD_INTERVAL = max_interval;   
   		firstTest_Loaded = true;

    	System.out.println("MetaTr interval---------> " + PAGE_LOAD_INTERVAL);
    	
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=login");

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

        selenium.type("userName", "dboris");
    	selenium.type("password", "damjanovic");
    	
    	selenium.click("//input[@value='Login']");
    	selenium.waitForPageToLoad("1500");
    	selenium.click("link=Cipher - Meta Transformations");
    	selenium.click("link=Edit-Fill");
    	selenium.click("transform");

    	org.testng.Assert.assertTrue(selenium.isTextPresent("39 02 dc 19"));
    	org.testng.Assert.assertTrue(selenium.isTextPresent("25 dc 11 6a"));
    	org.testng.Assert.assertTrue(selenium.isTextPresent("84 09 85 0b"));
    	org.testng.Assert.assertTrue(selenium.isTextPresent("1d fb 97 32"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(min_interval));
        selenium.click("link=Log out");
    }

	
    @AfterClass
    public void tearDown() throws Exception {
    	System.out.println("tearDown ----------------------- ");
    	selenium.stop();
    	
    }

    @Test
    public void testAddRoundKeyPG() {
    	this.selenium.open("http://localhost:8080/crypto2/");
    	
    	if (firstTest_Loaded){
    		PAGE_LOAD_INTERVAL = min_interval;
    	}else{
    		PAGE_LOAD_INTERVAL = max_interval;  
    		firstTest_Loaded = true;
    	}
    	System.out.println("AddRK interval---------> " + PAGE_LOAD_INTERVAL);
    	

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=AddRoundKey");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("view");
    	org.testng.Assert.assertTrue( selenium.isTextPresent("a4 68 6b 02"));
    	org.testng.Assert.assertTrue(selenium.isTextPresent("9c 9f 5b 6a"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("7f 35 ea 50"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("f2 2b 43 49"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(min_interval));
    }
    
    @Test
    public void testMixColumnsPG() {
    	this.selenium.open("http://localhost:8080/crypto2/");
    	
    	if (firstTest_Loaded){
    		PAGE_LOAD_INTERVAL = min_interval;
    	}else{
    		PAGE_LOAD_INTERVAL = max_interval;   
    		firstTest_Loaded = true;
    	}
    	System.out.println("MixCol interval---------> " + PAGE_LOAD_INTERVAL);
    	

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=MixColumns");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("view");
    	org.testng.Assert.assertTrue(selenium.isTextPresent("04 e0 48 28"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("66 cb f8 06"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("81 19 d3 26"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("e5 9a 7a 4c"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(min_interval));

    }
    @Test
    public void testShiftRowsPG() {
    	this.selenium.open("http://localhost:8080/crypto2/");
    	
    	if (firstTest_Loaded){
    		PAGE_LOAD_INTERVAL = min_interval;
    	}else{
    		PAGE_LOAD_INTERVAL = max_interval;    	
    		firstTest_Loaded = true;
    	}
    	System.out.println("ShRows interval---------> " + PAGE_LOAD_INTERVAL);
    	

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=ShiftRows");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("view");
    	org.testng.Assert.assertTrue( selenium.isTextPresent("d4 e0 b8 1e"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("bf b4 41 27"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("5d 52 11 98"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("30 ae f1 e5"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(min_interval));
    }
    
    @Test
    public void testSubBytesPG() {
    	this.selenium.open("http://localhost:8080/crypto2/");
    	
    	if (firstTest_Loaded){
    		PAGE_LOAD_INTERVAL = min_interval;
    	}else{
    		PAGE_LOAD_INTERVAL = max_interval;    
    		firstTest_Loaded = true;
    	}
    	System.out.println("SubBytes interval---------> " + PAGE_LOAD_INTERVAL);
    	

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=SubBytes");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("view");
    	org.testng.Assert.assertTrue( selenium.isTextPresent("d4 e0 b8 1e"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("27 bf b4 41"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("11 98 5d 52"));
        org.testng.Assert.assertTrue( selenium.isTextPresent("ae f1 e5 30"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(min_interval));
    }
    
    @Test
    public void testInvSubBytesPG() {
    	this.selenium.open("http://localhost:8080/crypto2/");
    	
    	if (firstTest_Loaded){
    		PAGE_LOAD_INTERVAL = min_interval;
    	}else{
    		PAGE_LOAD_INTERVAL = max_interval;    	
    		firstTest_Loaded = true;
    	}
    	System.out.println("InvSubBytes interval---------> " + PAGE_LOAD_INTERVAL);
    	

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=InvSubBytes");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("view");
    	
    	org.testng.Assert.assertTrue( selenium.isTextPresent("3b f8 60 b9"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("fd da c6 d7"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("e7 9e fb 2f"));
        org.testng.Assert.assertTrue( selenium.isTextPresent("30 58 74 52"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(min_interval));
    }
    
    @Test
    public void testInvShiftRowsPG() {
    	this.selenium.open("http://localhost:8080/crypto2/");
    	
    	if (firstTest_Loaded){
    		PAGE_LOAD_INTERVAL = min_interval;
    	}else{
    		PAGE_LOAD_INTERVAL = max_interval; 
    		firstTest_Loaded = true;
    	}
    	System.out.println("InvShRows interval---------> " + PAGE_LOAD_INTERVAL);
    	

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=InvShiftRows");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("view");
    	org.testng.Assert.assertTrue( selenium.isTextPresent("d4 e0 b8 1e"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("41 27 bf b4"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("5d 52 11 98"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("f1 e5 30 ae"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(min_interval));
    }
    
    @Test
    public void testInvMixColumnsPG() {
    	this.selenium.open("http://localhost:8080/crypto2/");
    	
    	if (firstTest_Loaded){
    		PAGE_LOAD_INTERVAL = min_interval;
    	}else{
    		PAGE_LOAD_INTERVAL = max_interval;    	
    		firstTest_Loaded = true;
    	}
    	System.out.println("InvMxCols interval---------> " + PAGE_LOAD_INTERVAL);
    	

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=InvMixColumns");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("view");
    	org.testng.Assert.assertTrue(selenium.isTextPresent("26 29 12 10"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("5c 94 c6 35"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("a3 d0 89 1e"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("df c5 44 7f"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(min_interval));

    }
	
}
