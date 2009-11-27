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
	//int minInterval = 2000;
	//int max_interval = 6000;
	//String speed = "2000";
	//int PAGE_LOAD_INTERVAL = 2000;
	
	int minInterval = 400;
	int maxInterval = 800;
	String speed = "800";
	int PAGE_LOAD_INTERVAL = 800;
	
	boolean firstTestLoaded = false;
	
	
    private int setInterval(){
    	if (firstTestLoaded){
    		return minInterval;
    	}else{
    		firstTestLoaded = true;
    		return maxInterval;    	
    	}
    }
    
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
        selenium.setSpeed(speed);
        selenium.windowMaximize();
	}
    
	
    @AfterClass
    public void tearDown() throws Exception {
    	selenium.stop();
    }
    


	



    @Test
    public void testSubBytesPG() {

    	this.selenium.open("http://localhost:8080/crypto2/");
    	
		PAGE_LOAD_INTERVAL = setInterval();
		
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=SubBytes");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

		org.testng.Assert.assertTrue(selenium.isTextPresent("Sub Bytes Test Value"));
		
		selenium.click("view");
    	org.testng.Assert.assertTrue( selenium.isTextPresent("d4 e0 b8 1e"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("27 bf b4 41"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("11 98 5d 52"));
        org.testng.Assert.assertTrue( selenium.isTextPresent("ae f1 e5 30"));

    }
    @Test (dependsOnMethods = {"testSubBytesPG"})
    public void invalidSubBytesPG() {
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Mix Columns Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Shift Rows Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Add Round Key Test Value"));

    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Sub Bytes Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Shift Rows Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Mix Columns Test Value"));
  	
    }
    
    @Test (dependsOnMethods = {"invalidSubBytesPG"})
    public void testShiftRowsPG() {

    	this.selenium.open("http://localhost:8080/crypto2/");
    	
		PAGE_LOAD_INTERVAL = setInterval();
		
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=ShiftRows");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	
		org.testng.Assert.assertTrue(selenium.isTextPresent("Shift Rows Test Value"));

		selenium.click("view");
    	org.testng.Assert.assertTrue( selenium.isTextPresent("d4 e0 b8 1e"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("bf b4 41 27"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("5d 52 11 98"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("30 ae f1 e5"));

    }
    @Test (dependsOnMethods = {"testShiftRowsPG"})
    public void invalidShiftRowsPG() {
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Mix Columns Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Sub Bytes Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Add Round Key Test Value"));

    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Sub Bytes Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Shift Rows Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Mix Columns Test Value"));
  	
    }

    @Test (dependsOnMethods = {"invalidShiftRowsPG"})
    public void testMixColumnsPG() {

    	this.selenium.open("http://localhost:8080/crypto2/");
    	
		PAGE_LOAD_INTERVAL = setInterval();

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=MixColumns");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

    	org.testng.Assert.assertTrue(selenium.isTextPresent("Mix Columns Test Value"));
    	
    	selenium.click("view");
    	org.testng.Assert.assertTrue(selenium.isTextPresent("04 e0 48 28"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("66 cb f8 06"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("81 19 d3 26"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("e5 9a 7a 4c"));
    }
    @Test (dependsOnMethods = {"testMixColumnsPG"})
    public void invalidMixColumnsPG() {
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Shift Rows Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Sub Bytes Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Add Round Key Test Value"));

    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Sub Bytes Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Shift Rows Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Mix Columns Test Value"));
    }

    
    @Test (dependsOnMethods = {"invalidMixColumnsPG"})
    public void testAddRoundKeyPG() {
    	
    	this.selenium.open("http://localhost:8080/crypto2/");
		
    	PAGE_LOAD_INTERVAL = setInterval();
    	
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=AddRoundKey");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

    	org.testng.Assert.assertTrue(selenium.isTextPresent("Add Round Key Test Value"));
		
    	selenium.click("view");
    	org.testng.Assert.assertTrue( selenium.isTextPresent("a4 68 6b 02"));
    	org.testng.Assert.assertTrue(selenium.isTextPresent("9c 9f 5b 6a"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("7f 35 ea 50"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("f2 2b 43 49"));   	
    }
    @Test (dependsOnMethods = {"testAddRoundKeyPG"})
    public void invalidAddRoundKeyPG() {
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Mix Columns Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Shift Rows Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Sub Bytes Test Value"));

    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Sub Bytes Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Shift Rows Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Mix Columns Test Value"));
  	
    }
    

    
    
    @Test (dependsOnMethods = {"invalidAddRoundKeyPG"})
    public void testInvSubBytesPG() {

    	this.selenium.open("http://localhost:8080/crypto2/");
    	
		PAGE_LOAD_INTERVAL = setInterval();
		
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=InvSubBytes");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

		org.testng.Assert.assertTrue(selenium.isTextPresent("Inv Sub Bytes Test Value"));
		
		selenium.click("view");
    	
    	org.testng.Assert.assertTrue( selenium.isTextPresent("3b f8 60 b9"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("fd da c6 d7"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("e7 9e fb 2f"));
        org.testng.Assert.assertTrue( selenium.isTextPresent("30 58 74 52"));

    }
    @Test (dependsOnMethods = {"testInvSubBytesPG"})
    public void invalidInvSubBytesPG() {
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Shift Rows Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Mix Columns Test Value"));
    }
    
    
    @Test (dependsOnMethods = {"invalidInvSubBytesPG"})
    public void testInvShiftRowsPG() {
    	this.selenium.open("http://localhost:8080/crypto2/");
    	
		PAGE_LOAD_INTERVAL = setInterval();
		
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=InvShiftRows");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

		org.testng.Assert.assertTrue(selenium.isTextPresent("Inv Shift Rows Test Value"));

		selenium.click("view");
    	org.testng.Assert.assertTrue( selenium.isTextPresent("d4 e0 b8 1e"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("41 27 bf b4"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("5d 52 11 98"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("f1 e5 30 ae"));
    }
    @Test (dependsOnMethods = {"testInvShiftRowsPG"})
    public void invalidInvShiftRowsPG() {
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Sub Bytes Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Mix Columns Test Value"));
    }

    
    
    @Test (dependsOnMethods = {"invalidInvShiftRowsPG"})
    public void testInvMixColumnsPG() {
    	
    	this.selenium.open("http://localhost:8080/crypto2/");
    	
		PAGE_LOAD_INTERVAL = setInterval();
		
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=InvMixColumns");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

		org.testng.Assert.assertTrue(selenium.isTextPresent("Inv Mix Columns Test Value"));

		selenium.click("view");
    	org.testng.Assert.assertTrue(selenium.isTextPresent("26 29 12 10"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("5c 94 c6 35"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("a3 d0 89 1e"));
    	org.testng.Assert.assertTrue( selenium.isTextPresent("df c5 44 7f"));

    }
    @Test (dependsOnMethods = {"testInvMixColumnsPG"})
    public void invalidInvMixColumnsPG() {
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Sub Bytes Test Value"));
    	org.testng.Assert.assertFalse(selenium.isTextPresent("Inv Shift Rows Test Value"));
    }

    

    @Test (dependsOnMethods = {"invalidInvMixColumnsPG"})
    public void testMetaTransformationPG() {

		selenium.open("http://localhost:8080/crypto2/");

		
		PAGE_LOAD_INTERVAL = setInterval();
    	
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
        selenium.waitForPageToLoad(Integer.toString(minInterval));
        selenium.click("link=Log out");
    }
 
    @Test (dependsOnMethods = {"testMetaTransformationPG"})
    public void testEnterMetaTransformation() {

		selenium.open("http://localhost:8080/crypto2/");

		
		PAGE_LOAD_INTERVAL = setInterval();
    	
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=login");

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

        selenium.type("userName", "dboris");
    	selenium.type("password", "damjanovic");
    	
    	selenium.click("//input[@value='Login']");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=Cipher - Meta Transformations");
    	selenium.waitForPageToLoad("2000");
    	selenium.click("link=Edit-Fill");
    	
    	selenium.click("//input[@value='Save new']");
    	selenium.click("link=3");
    	
    	org.testng.Assert.assertTrue(selenium.isTextPresent("// AES128 from FIPS197 pg.33-34 // First try test vector no.1 // (3243f6a8885a308d313198a2e0370734) // Then try to experiment. key_len = 128; init_key = "));
    	org.testng.Assert.assertTrue(selenium.isTextPresent("2b7e151628aed2a6abf7158809cf4f3c"));
    	org.testng.Assert.assertTrue(selenium.isTextPresent("keyExpansion.KeyExpansion128(); Nb = 4; //br.kolona //number of columns Nr = 10; //br.rundi // number of rounds addRoundKey.key_index = 0; addRoundKey.TransformState(); for (runda = 1; runda <= Nr-1; runda++) { subBytes.TransformState(); shiftRows.TransformState(); mixColumns.TransformState(); addRoundKey.key_index = 4*runda*Nb; addRoundKey.TransformState( ); runda2=runda; } runda2++; subBytes.TransformState(); // Final round shiftRows.TransformState(); addRoundKey.key_index = 4*runda2*Nb; addRoundKey.TransformState( );"));
    	



        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(minInterval));
        selenium.click("link=Log out");
    }
    @Test (dependsOnMethods = {"testEnterMetaTransformation"})
    public void testUpdateMetaTransformation() {

		selenium.open("http://localhost:8080/crypto2/");

		
		PAGE_LOAD_INTERVAL = setInterval();
    	
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=login");

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

        selenium.type("userName", "dboris");
    	selenium.type("password", "damjanovic");
    	
    	selenium.click("//input[@value='Login']");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

    	selenium.click("link=Cipher - Meta Transformations");
    	selenium.waitForPageToLoad("2500");
    	selenium.click("link=Edit-Fill");
    	
    	
    	selenium.type("source_code", "//TESTTTTT");
    	
    	selenium.click("//input[@value='Update']");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	org.testng.Assert.assertTrue(selenium.isTextPresent("//TESTTTTT"));
    	

        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(minInterval));
       
    }

    @Test (dependsOnMethods = {"testUpdateMetaTransformation"})
    public void testDeleteMetaTransformation() {

    	PAGE_LOAD_INTERVAL = setInterval();
    	selenium.click("link=Cipher - Meta Transformations");
    	selenium.waitForPageToLoad("2500");
    	
    	selenium.click("link=Delete");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=Edit-Fill");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	
    	org.testng.Assert.assertFalse(selenium.isTextPresent("//TESTTTTT"));

        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(minInterval));

        selenium.click("link=Log out");
    }
    
    
    

  
    @Test (dependsOnMethods = {"testDeleteMetaTransformation"})
    public void ValidRegistration() {
    	selenium.open("http://localhost:8080/crypto2/");
		
		PAGE_LOAD_INTERVAL = setInterval();
    	
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=register");

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

        selenium.type("userName", "boris");
    	selenium.type("password", "boki");
    	selenium.type("name", "Boris Damjanovic");
    	selenium.type("email", "dboris@poen.net");
    	selenium.click("//input[@value='Create/Update']");

        selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Boris Damjanovic"));

        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(minInterval));
        selenium.click("link=Log out");
        selenium.waitForPageToLoad(Integer.toString(minInterval));
        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(minInterval));

    }

    @Test (dependsOnMethods = {"ValidRegistration"})
    public void InvalidRegistration() {
		selenium.open("http://localhost:8080/crypto2/");
		
		PAGE_LOAD_INTERVAL = setInterval();
    	
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=register");

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

        selenium.type("userName", "boris");
    	selenium.type("password", "boki");
    	selenium.type("name", "abcd");
    	selenium.type("email", "abcd");
    	
    	selenium.click("//input[@value='Create/Update']");
    	
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid user name or password"));
        


    }



    @Test (dependsOnMethods = {"InvalidRegistration"})
    public void ValidLogIn() {
		selenium.open("http://localhost:8080/crypto2/");

		PAGE_LOAD_INTERVAL = setInterval();
    	
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=login");

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

        selenium.type("userName", "boris");
    	selenium.type("password", "boki");

    	
    	selenium.click("//input[@value='Login']");

        selenium.waitForPageToLoad(Integer.toString(minInterval));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Welcome back, Boris Damjanovic"));

        selenium.click("link=Log out");
        selenium.waitForPageToLoad(Integer.toString(minInterval));


    }

    @Test (dependsOnMethods = { "ValidLogIn"})
    public void InvalidLogIn() {
		selenium.open("http://localhost:8080/crypto2/");

		PAGE_LOAD_INTERVAL = setInterval();
    	
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=login");

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

        selenium.type("userName", "borisss");
    	selenium.type("password", "bokiss");

    	
    	selenium.click("//input[@value='Login']");

        selenium.waitForPageToLoad(Integer.toString(minInterval));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid user name or password"));

        selenium.click("link=Index");
        selenium.waitForPageToLoad(Integer.toString(minInterval));


    }

    @Test (dependsOnMethods = { "InvalidLogIn"})
    public void ValidTestVectors() {

    	
    	selenium.open("http://localhost:8080/crypto2/");
		PAGE_LOAD_INTERVAL = setInterval();

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=login");

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

        selenium.type("userName", "boris");
    	selenium.type("password", "boki");

    	selenium.click("//input[@value='Login']");
    	
        selenium.waitForPageToLoad(Integer.toString(3*minInterval));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Welcome back, Boris Damjanovic"));

    	selenium.click("link=Create test vectors");

    	selenium.waitForPageToLoad(Integer.toString(3*PAGE_LOAD_INTERVAL));

        selenium.type("subBytes_TestValue", "10112233445566778899aabbccddeeff");
    	selenium.type("mixColumns_TestValue", "20112233445566778899aabbccddeeff");
    	selenium.type("shiftRows_TestValue", "30112233445566778899aabbccddeeff");
    	selenium.type("keyExpansion_TestValue", "40112233445566778899aabbccddeeff");

        selenium.type("addRoundKey_TestValue", "50112233445566778899aabbccddeeff");
    	selenium.type("metaTransformation_TestValue", "60112233445566778899aabbccddeeff");
    	selenium.type("invSubBytes_TestValue", "70112233445566778899aabbccddeeff");
    	selenium.type("invShiftRows_TestValue", "80112233445566778899aabbccddeeff");
    	
    	selenium.type("invMixColumns_TestValue", "90112233445566778899aabbccddeeff");
    	selenium.type("invMetaTransformation_TestValue", "11112233445566778899aabbccddeeff");
    	
    	
    	selenium.click("//input[@value='Create Values']");

        selenium.waitForPageToLoad(Integer.toString(minInterval));
        
        org.testng.Assert.assertTrue(selenium.isTextPresent("10112233445566778899aabbccddeeff"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("20112233445566778899aabbccddeeff"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("30112233445566778899aabbccddeeff"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("40112233445566778899aabbccddeeff"));

        org.testng.Assert.assertTrue(selenium.isTextPresent("50112233445566778899aabbccddeeff"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("60112233445566778899aabbccddeeff"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("70112233445566778899aabbccddeeff"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("80112233445566778899aabbccddeeff"));

        org.testng.Assert.assertTrue(selenium.isTextPresent("90112233445566778899aabbccddeeff"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("11112233445566778899aabbccddeeff"));

        selenium.click("link=Log out");
        selenium.waitForPageToLoad(Integer.toString(minInterval));

    }

    
    @Test (dependsOnMethods = { "ValidTestVectors"})
    public void InvalidTestVectors() {

    	
    	selenium.open("http://localhost:8080/crypto2/");
		PAGE_LOAD_INTERVAL = setInterval();

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=login");

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

        selenium.type("userName", "boris");
    	selenium.type("password", "boki");

    	selenium.click("//input[@value='Login']");
    	
        selenium.waitForPageToLoad(Integer.toString(3*minInterval));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Welcome back, Boris Damjanovic"));

    	selenium.click("link=Create test vectors");

    	selenium.waitForPageToLoad(Integer.toString(3*PAGE_LOAD_INTERVAL));

        selenium.type("subBytes_TestValue", "1M112233445566778899aabbccddeeff");
    	selenium.type("mixColumns_TestValue", "2M112233445566778899aabbccddeeff");
    	selenium.type("shiftRows_TestValue", "3M112233445566778899aabbccddeeff");
    	selenium.type("keyExpansion_TestValue", "4M112233445566778899aabbccddeeff");

        selenium.type("addRoundKey_TestValue", "5M112233445566778899aabbccddeeff");
    	selenium.type("metaTransformation_TestValue", "6M112233445566778899aabbccddeeff");
    	selenium.type("invSubBytes_TestValue", "7M112233445566778899aabbccddeeff");
    	selenium.type("invShiftRows_TestValue", "8M112233445566778899aabbccddeeff");
    	
    	selenium.type("invMixColumns_TestValue", "9M112233445566778899aabbccddeeff");
    	selenium.type("invMetaTransformation_TestValue", "MM112233445566778899aabbccddeeff");
    	
    	
    	selenium.click("//input[@value='Create Values']");

        selenium.waitForPageToLoad(Integer.toString(minInterval));
        
        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid SubBytes test vector value"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid MixColumns test vector value"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid ShiftRows test vector value"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid AddRoundKey test vector value"));

        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid MetaTransformation test vector value"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid KeyExpansion test vector value"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid InvSubBytes test vector value"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid InvMixColumns test vector value"));

        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid InvShiftRows test vector value"));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Invalid InvMetaTransformation test vector value"));

        selenium.click("link=Log out");
        selenium.waitForPageToLoad(Integer.toString(minInterval));

    }

    
    @Test (dependsOnMethods = { "InvalidTestVectors"})
    public void DeleteTestVectors() {

    	
    	selenium.open("http://localhost:8080/crypto2/");
		PAGE_LOAD_INTERVAL = setInterval();

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	selenium.click("link=login");

    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));

        selenium.type("userName", "boris");
    	selenium.type("password", "boki");

    	selenium.click("//input[@value='Login']");
    	
        selenium.waitForPageToLoad(Integer.toString(3*minInterval));
        org.testng.Assert.assertTrue(selenium.isTextPresent("Welcome back, Boris Damjanovic"));

    	selenium.click("link=Create test vectors");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	
    	selenium.click("link=Delete");
    	selenium.waitForPageToLoad(Integer.toString(PAGE_LOAD_INTERVAL));
    	
        org.testng.Assert.assertFalse(selenium.isTextPresent("3925841D02DC09FBDC118597196A0B32"));

        selenium.click("link=Log out");
        selenium.waitForPageToLoad(Integer.toString(minInterval));

    }

}
