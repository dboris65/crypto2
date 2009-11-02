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
	
	//*iexplore -ok
	//*iehta -ok
	//*chrome - fails
	//*googlechrome - fails
	//*firefox3 - fails
    @BeforeClass
    @Parameters(value = "SeleniumBrowser")
    public void setUp(@Optional("*iehta") String browser_name) {
    	System.out.println("setUp port----------------------- " + SeleniumServer.getDefaultPort());
    	System.out.println("setUp ----------------------- " + browser_name);
		//super.setUp();
        selenium = new DefaultSelenium(
        		"localhost", 
        		SeleniumServer.getDefaultPort(), 
        		browser_name, 
        		"http://localhost:8080");
        selenium.start();
        System.out.println("SetUp before speed ----------------------- ");
        selenium.setSpeed("1000");
        System.out.println("SetUp after speed ----------------------- ");
	}
	
    @Test
    public void testMetaTransformationPG() {
    	System.out.println("test 1----------------------- ");
    	selenium.open("http://localhost:8080/crypto2/");
    	System.out.println("test 2----------------------- ");
    	selenium.waitForPageToLoad("2000");
    	selenium.click("link=login");
    	System.out.println("test ----------------------- ");
    	selenium.waitForPageToLoad("1000");
    	selenium.type("userName", "dboris");
    	selenium.type("password", "damjanovic");
    	
    	selenium.click("//input[@value='Login']");
    	selenium.waitForPageToLoad("1000");
    	selenium.click("link=Meta Transformations");
    	selenium.click("link=Edit-Fill");
    	selenium.click("transform");

    	org.testng.Assert.assertTrue(selenium.isTextPresent("39 02 dc 19"));
    	org.testng.Assert.assertTrue(selenium.isTextPresent("25 dc 11 6a"));
    	org.testng.Assert.assertTrue(selenium.isTextPresent("84 09 85 0b"));
    	org.testng.Assert.assertTrue(selenium.isTextPresent("1d fb 97 32"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad("1000");
        selenium.click("link=Log out");
    }

	
    @AfterClass
    public void tearDown() throws Exception {
    	System.out.println("tearDown ----------------------- ");
    	selenium.stop();
    	
    }

	
	
	
	
	
	
	
/*	
	private TestValuesDao testValuesDao;
	private SourceDao sourceDao;
    private SeleniumServer server;
    private DefaultSelenium selenium;
    
    

    
    // *iexplore and *googlechrome are working under windows. *chrome - not working
	// Create a string (br) that tells Selenium what browser to start (firefox)
	// This is the most painful part with setting Firefox Selenium environment
    // in profilemanager mode create new profile to listen port 4444
	// For more info check http://clearspace.openqa.org/thread/14617
    // String br = "*custom \"c:\\Program Files\\Mozilla Firefox\\firefox.exe\" ";
	// br += "-no-remote ";
	// br += "-profile \"C:\\Users\\Mare\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\badaabd3.testing\"";

    
    @BeforeClass
    @Parameters(value = "SeleniumBrowser")
    public void setUp(@Optional("*iexplore") String br) {
    /*
        try {
          sourceDao.reload();
          testValuesDao.reload();
            
          this.server = new SeleniumServer();
          this.server.start();
     } catch (Exception e) {
          System.err.println("Can't start selenium server");
     }
	  	//String br = "*iexplore";  
	    //String br = "*googlechrome";
     
        this.selenium  = new DefaultSelenium("localhost", 4444, br, "http://localhost:8080/");
        this.selenium.start();
        this.selenium.open("http://localhost:8080/crypto2/");

        this.selenium.setSpeed("1000");
}
    

    
    @Test
    public void testMetaTransformationPG() {
        //selenium.open("http://localhost:8080/crypto2/Selenium/SeleniumMetaTransformationPG.html");
    	this.selenium.open("http://localhost:8080/crypto2/");
    	selenium.click("link=login");
    	selenium.waitForPageToLoad("1000");
    	selenium.type("userName", "dboris");
    	selenium.type("password", "damjanovic");
    	
    	selenium.click("//input[@value='Login']");
    	selenium.waitForPageToLoad("1000");
    	selenium.click("link=Meta Transformations");
    	selenium.click("link=Edit-Fill");
    	selenium.click("transform");
    	assertTrue("prvi", selenium.isTextPresent("39 02 dc 19"));
        assertTrue("drugi", selenium.isTextPresent("25 dc 11 6a"));
        assertTrue("treci", selenium.isTextPresent("84 09 85 0b"));
        assertTrue("cetvrti", selenium.isTextPresent("1d fb 97 32"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad("1000");
        selenium.click("link=Log out");
    }
    
*/    
    
/*    

    @Test
    public void testAddRoundKeyPG() {
        //selenium.open("http://localhost:8080/crypto2/Selenium/SeleniumAddRoundKeyPG.html");
    	this.selenium.open("http://localhost:8080/crypto2/");
    	selenium.click("link=AddRoundKey");
    	selenium.waitForPageToLoad("1000");
    	selenium.click("view");
    	assertTrue("prvi", selenium.isTextPresent("a4 68 6b 02"));
        assertTrue("drugi", selenium.isTextPresent("9c 9f 5b 6a"));
        assertTrue("treci", selenium.isTextPresent("7f 35 ea 50"));
        assertTrue("cetvrti", selenium.isTextPresent("f2 2b 43 49"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad("1000");
    }
    @Test
    public void testMixColumnsPG() {
        //selenium.open("http://localhost:8080/crypto2/Selenium/SeleniumMixColumnsPG.html");  
    	this.selenium.open("http://localhost:8080/crypto2/");
    	selenium.click("link=MixColumns");
    	selenium.waitForPageToLoad("1000");
    	selenium.click("view");
    	assertTrue("prvi", selenium.isTextPresent("04 e0 48 28"));
        assertTrue("drugi", selenium.isTextPresent("66 cb f8 06"));
        assertTrue("treci", selenium.isTextPresent("81 19 d3 26"));
        assertTrue("cetvrti", selenium.isTextPresent("e5 9a 7a 4c"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad("1000");

    }
    @Test
    public void testShiftRowsPG() {
        //selenium.open("http://localhost:8080/crypto2/Selenium/SeleniumShiftRowsPG.html");
    	this.selenium.open("http://localhost:8080/crypto2/");
    	selenium.click("link=ShiftRows");
    	selenium.waitForPageToLoad("1000");
    	selenium.click("view");
    	assertTrue("prvi", selenium.isTextPresent("d4 e0 b8 1e"));
        assertTrue("drugi", selenium.isTextPresent("bf b4 41 27"));
        assertTrue("treci", selenium.isTextPresent("5d 52 11 98"));
        assertTrue("cetvrti", selenium.isTextPresent("30 ae f1 e5"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad("1000");
    }
    @Test
    public void testSubBytesPG() {
    	//this.selenium.open("http://localhost:8080/crypto2/Selenium/SeleniumSubBytesPG.html");
    	this.selenium.open("http://localhost:8080/crypto2/");
    	selenium.click("link=SubBytes");
    	selenium.waitForPageToLoad("1000");
    	selenium.click("view");
    	assertTrue("prvi", selenium.isTextPresent("d4 e0 b8 1e"));
        assertTrue("drugi", selenium.isTextPresent("27 bf b4 41"));
        assertTrue("treci", selenium.isTextPresent("11 98 5d 52"));
        assertTrue("cetvrti", selenium.isTextPresent("ae f1 e5 30"));
        selenium.click("link=Index");
        selenium.waitForPageToLoad("1000");
    }
    
    @AfterClass
    public void tearDown() {
        selenium.stop();
    }

*/	
}
