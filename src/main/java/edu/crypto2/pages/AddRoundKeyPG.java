/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.pages;

import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
/******************************************************/
import edu.crypto2.entities.TestValues;
import edu.crypto2.entities.User;
import edu.crypto2.services.TestValuesDao;

import edu.crypto2.transformations.AddRoundKey;
import edu.crypto2.rest.XmlParser;
import edu.crypto2.components.LinesOut;
import edu.crypto2.data.*;
/***********************************************************************
 * 
 */
public class AddRoundKeyPG {
	@SessionState(create=false)
	private User user;
	
	@Property
	private String UserName = "";

    public boolean getLoggedIn() {
        if (user != null)
            return true;
        else
            return false;
    }

	@Inject
    private Session session;
	@Inject
    private TestValuesDao testValuesDao;
	@Parameter
	private TestValues testValues;

	public TestValuesDao getTestValuesDao() {
		return testValuesDao;
	}
	
	public void setTestValuesDao(TestValuesDao testValuesDao) {
		this.testValuesDao = testValuesDao;
	}
	
	public List<TestValues> getTestValuesList(){
		return testValuesDao.findAllTestValues();
	}
	
	public TestValues getTestValues() {
		return testValues;
	}

	public void setTestValues(TestValues testValues) {
		this.testValues = testValues;
	}
	
	@SetupRender
	boolean setup() throws Exception {
		long userId = 1; 
		if (user != null){
            try {
				UserName = (user.getName());
				userId = user.getId();
			} catch (Exception e) {
				user = null;
				userId = 1;
			}
		}
		else{ 
			UserName = "";
			userId = 1;
		}
		testValuesDao.reload(userId);
		if (SesionInit_Key == null)
			SesionInit_Key = "";
		if ((persistValueBeforeAddRoundKey == "") || (persistValueBeforeAddRoundKey == null)) 
		{
	    	int key_len;
			xml_p = new XmlParser("AddRoundKey"); 
			String ValueBefore = xml_p.getResultString();
			key_len = 128; // because we read from bean
			xml_p2 = new XmlParser("TestKeyAddRoundKey"); 
			String init_key = xml_p2.getResultString();
			DoTransform(key_len, init_key, ValueBefore);
		}
		else
		{
			if (Init_KeyBeforeAddRoundKey.length() == 32) Key_LenBeforeAddRoundKey = 128;
			else
			if (Init_KeyBeforeAddRoundKey.length() == 48) Key_LenBeforeAddRoundKey = 192;
			else
			if (Init_KeyBeforeAddRoundKey.length() == 64) Key_LenBeforeAddRoundKey = 256;
			DoTransform(Key_LenBeforeAddRoundKey, SesionInit_Key, persistValueBeforeAddRoundKey);
		}
		return true;
	}
	
	@Inject
	private Block LinesOutDetails;
	private LinesOut LinesOut;
	private LinesOut detailLinesOut;
	
	//***********************************************************************
	
	
	private AddRoundKey add_round_key;
	private XmlParser xml_p, xml_p2;
	
	@Persist
	private String persistValueBeforeAddRoundKey;
	@Persist
	private String ValueBeforeAddRoundKey;
	
	public String getValueBeforeAddRoundKey() {
		return ValueBeforeAddRoundKey;
	}
	public void setValueBeforeAddRoundKey(String valueBeforeAddRoundKey) {
		ValueBeforeAddRoundKey = valueBeforeAddRoundKey;
	}
	
	@Persist
	private String SesionInit_Key;
	@Persist
	private String Init_KeyBeforeAddRoundKey;
	
	public String getInit_KeyBeforeAddRoundKey() {
		return Init_KeyBeforeAddRoundKey;
	}
	public void setInit_KeyBeforeAddRoundKey(String initKeyBeforeAddRoundKey) {
		Init_KeyBeforeAddRoundKey = initKeyBeforeAddRoundKey;
	}

	private int Key_LenBeforeAddRoundKey;
	public int getKey_LenBeforeAddRoundKey() {
		return Key_LenBeforeAddRoundKey;
	}
	public void setKey_LenBeforeAddRoundKey(int keyLenBeforeAddRoundKey) {
		Key_LenBeforeAddRoundKey = keyLenBeforeAddRoundKey;
	}
	
	private String before_line0;
	private String before_line1;
	private String before_line2;
	private String before_line3;
	
	private String line0;
	private String line1;
	private String line2;
	private String line3;


	/* DoTransform *****************************************************
     * */
    public void DoTransform(int key_len, String init_key, String ValueBefore){
    	ValueBeforeAddRoundKey = ValueBefore;
    	persistValueBeforeAddRoundKey = ValueBefore;
    	SesionInit_Key = init_key;
    	Init_KeyBeforeAddRoundKey = init_key;
    	Key_LenBeforeAddRoundKey = key_len;
    	add_round_key = new AddRoundKey();
    	add_round_key.initialize_State(Key_LenBeforeAddRoundKey, Init_KeyBeforeAddRoundKey, ValueBeforeAddRoundKey);
		
		String s = "";
		/**********************************************
		* STATE BEFORE SubBytes
		* */
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
								16 /* radix */).substring(1) + " "; 
			}
			s = "| " + s.trim() + " |";
			switch (j){
				case 0 : before_line0 = s; break;
				case 1 : before_line1 = s; break;
				case 2 : before_line2 = s; break;
				case 3 : before_line3 = s; break;
			}
			s = "";
		}
		/* we will use 1 as argument*/
		add_round_key.transform_state(16);
		s = "";
		/**********************************************
		* STATE After SubBytes
		* */
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
								16 /* radix */).substring(1) + " "; 
			}
			s = "| " + s.trim() + " |";
			switch (j){
				case 0 : line0 = s; break;
				case 1 : line1 = s; break;
				case 2 : line2 = s; break;
				case 3 : line3 = s; break;
			}
			s = "";
		}
		
		LinesOut lines = new LinesOut(before_line0, before_line1, before_line2, before_line3,
									  line0, line1,	line2, line3); 
		detailLinesOut = lines;
   	
    }

    /* Construct *****************************************************
     * */
	public AddRoundKeyPG() throws Exception 
	{

	}
	
    @InjectPage
    private AddRoundKeyPG addRoundKeyPG;
    
	Object onActionFromView(Long id){
		TestValues tvrow = testValuesDao.find(id);
		String s = tvrow.getAddRoundKey_TestValue();
		ValueBeforeAddRoundKey = s;
		persistValueBeforeAddRoundKey = s;
		
		String s2 = tvrow.getKeyExpansion_TestValue();
		Init_KeyBeforeAddRoundKey = s2;
		SesionInit_Key = s2;
		if (Init_KeyBeforeAddRoundKey.length() == 32) Key_LenBeforeAddRoundKey = 128;
		else
		if (Init_KeyBeforeAddRoundKey.length() == 48) Key_LenBeforeAddRoundKey = 192;
		else
		if (Init_KeyBeforeAddRoundKey.length() == 64) Key_LenBeforeAddRoundKey = 256;
		
		DoTransform(Key_LenBeforeAddRoundKey, SesionInit_Key, persistValueBeforeAddRoundKey);
		return LinesOutDetails;   
	}
	
	public LinesOut getLinesOut() {
		return LinesOut;
	}
	public void setLinesOut(LinesOut LinesOut) {
		this.LinesOut = LinesOut;
	}
	public LinesOut getDetailLinesOut() {
		return detailLinesOut;
	}	

    @InjectPage
    private Index index;
	Object onActionFromLogOut(){
		user = null;
		return index;
	}
	
	public String getBefore_Line0() 
	{ 
		return before_line0; 
	}
	public String getBefore_Line1() 
	{ 
		return before_line1; 
	}
	public String getBefore_Line2() 
	{ 
		return before_line2; 
	}
	public String getBefore_Line3() 
	{ 
		return before_line3; 
	}

	public String getLine0() 
	{ 
		return line0; 
	}
	public String getLine1() 
	{ 
		return line1; 
	}
	public String getLine2() 
	{ 
		return line2; 
	}
	public String getLine3() 
	{ 
		return line3; 
	}

	/***************************************************************/


}
