/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;


/******************************************************/
import edu.crypto2.entities.Source;
import edu.crypto2.entities.TestValues;
import edu.crypto2.entities.User;
import edu.crypto2.services.SourceDao;
import edu.crypto2.services.TestValuesDao;

import edu.crypto2.transformations.MetaTransformation;
import edu.crypto2.rest.XmlParser;
import edu.crypto2.components.LinesOut;
import edu.crypto2.data.*;
/***********************************************************************
 *  
 */
/***********************************************************************
 * 
 */
public class MetaTransformationPG {
	@SessionState
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
/* ----- GRID 1 -------- */
    @Inject
    private TestValuesDao testValuesDao;
	@Parameter
	private TestValues testValues;
	public TestValuesDao getTestValuesDao() {
		return testValuesDao;
	}
	public TestValues getTestValues() {
		return testValues;
	}

	public void setTestValues(TestValues testValues) {
		this.testValues = testValues;
	}
	public void setTestValuesDao(TestValuesDao testValuesDao) {
		this.testValuesDao = testValuesDao;
	}
	public List<TestValues> getTestValuesList(){
		return testValuesDao.findAllTestValues();
	}
/* ----- GRID 1 -------- */
/* ----- GRID 2 -------- */

    @Inject
    private SourceDao sourceDao;
	@Parameter
	private Source source;

	
	public SourceDao getSourceDao() {
		return sourceDao;
	}

	public void setSourceDao(SourceDao sourceDao) {
		this.sourceDao = sourceDao;
	}

	public List<Source> getSourceList(){
		return sourceDao.findAllSources();
	}

	public List<Source> getSourceS()
    {
        return session.createCriteria(Source.class).list();
    }
    


	public void setSource(Source source) {
		this.source = source;
	}

	public Source getSource() {
		return source;
	}
/* ----- GRID 2 -------- */
	@Inject
	private Block LinesOutDetails;
	private LinesOut LinesOut;
	private LinesOut detailLinesOut;

	/**
	 * Task:<br>
	 * before page loading, check if user is logged to decide 
	 * which values to show.  
	 */
	@SetupRender
	boolean setup() throws Exception {
		long userId = 1; 
		if (user != null){
			UserName = (user.getName());
			userId = user.getId();
		}
		else{
			UserName = "";
			userId = 1;
		}

		
		
    	final Logger logger = Logger.getLogger(MetaTransformationPG.class);
		logger.debug("MetaTransformationPG setUp ---------------------------------");
		logger.debug("---------------------------------");
		logger.debug("---------------------------------");
		logger.debug("User -------------" + user +  "--------------------");
		logger.debug("---------------------------------");

		sourceDao.reload(userId);
		testValuesDao.reload(userId);
		
		
		
		if ((persistValueBeforeMetaTransformation == "") || (persistValueBeforeMetaTransformation == null)) 
		{
			xml_p = new XmlParser("MetaTransformation"); 
			String ValueBefore = xml_p.getResultString();
			DoTransform(ValueBefore, 128, "2b7e151628aed2a6abf7158809cf4f3c", "TEST");
		}
		else
		{
			DoTransform(persistValueBeforeMetaTransformation, 128, "2b7e151628aed2a6abf7158809cf4f3c", "TEST");
		}
		//source_code="";

		return true;
	}

	private MetaTransformation meta_transformation;
	private XmlParser xml_p;
	
	
	private String ValueBeforeMetaTransformation;
	
	public String getValueBeforeMetaTransformation() {
		return ValueBeforeMetaTransformation;
	}

	public void setValueBeforeMetaTransformation(String valueBeforeMetaTransformation) {
		ValueBeforeMetaTransformation = valueBeforeMetaTransformation;
	}
	
	private String before_line0;
	private String before_line1;
	private String before_line2;
	private String before_line3;
	
	private String line0;
	private String line1;
	private String line2;
	private String line3;
	


	
	/**
	 * variable persistValueBeforeMetaTransformation (String)
	 * holds test vector for meta transformation when page reload
	 * **/
	@Persist
	private String persistValueBeforeMetaTransformation;
    
	
	
	
	
	/***************************************************************/
	/***************************************************************/
	@Component
    private Form form;
    
	//@Property
	@Persist
    private String source_code;
	
	public String getSource_code(){
		return source_code;
	}
	public void setSource_code(String source_code){
		this.source_code = source_code;
	}

    
	/***************************************************************/
	/***************************************************************/

	
    /**
     * Task:<br>
     * To do meta transformation according to selected values
     * @param testVector - selected testVector
     * @param key_len - selectted key length
     * @param init_key - selected initial key
     * @param MetaTr - selected meta transformation
     */
    public void DoTransform(String testVector, int key_len, String init_key, String MetaTr){
    	ValueBeforeMetaTransformation = testVector;
    	persistValueBeforeMetaTransformation = testVector;
		meta_transformation = new MetaTransformation();
		
		//String init_key = "2b7e151628aed2a6abf7158809cf4f3c";

		meta_transformation.initialize_State(testVector, key_len, init_key);
		
		String s = "";
		/**********************************************
		* da bi ga ispravno prikazao, mijenjamo j i i 
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
		
		meta_transformation.transform_state(testVector, key_len, init_key, MetaTr);
		s = "";
		
		/**********************************************
		* da bi ga ispravno prikazao, mijenjamo j i i 
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

    public MetaTransformationPG()throws Exception 
	{
    	
	}
    
    @InjectPage
    private MetaTransformationPG metaTransformationPG;
    

    /**
     * Task:<br>
     * To parse line with, for example key_len = 128; and extract number 128
     * @param In string which have form<br>
     * 'key_len = 128;' or<br>
     * 'key_len = 192;' or<br>
     * 'key_len = 256;'
     * @return selected key_len or 0 on error
     */
    int parseKey_len(String In){
    	int key_len = 0;
    	int pos = -1;
    	String substr;
    	
    	if (In == null)
    		return 0;
    	
    	In = In.toUpperCase();
    	pos = In.lastIndexOf("KEY_LEN");
    	
    	if (pos != -1)
    		substr = In.substring(pos, pos+14); // max alowed "KEY_LEN = 128;"
    	else
    		return 0;
    	

    	if (substr.contains("128"))
    		key_len = 128;
    	else
    	if (substr.contains("192"))
    		key_len = 192;
    	else
    	if (substr.contains("256"))
    		key_len = 256;
    	
    	return key_len;
    }


    /**
     * Task:<br>
     * To parse line with init_key = "xxzzyy..." and extract key
     * @param In - String which have form
     * 'init_key = "000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f";'
     * @return "" on error
     */
    String parseInit_key(String In){
    	String init_key = "";
    	int pos = -1;
    	int first_apostroph = -1;
    	int second_apostroph = -1;

    	if (In == null)
    		return "";
    	
    	In = In.toUpperCase();
    	pos = In.lastIndexOf("INIT_KEY");
    	
    	if (pos == -1)
    		return "";
    	
    	// for 256 bit key
    	// max alowed 'init_key = "000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f";'
    	for (int i = pos; i<pos+78; i++){
    		if ((In.charAt(i)== '\"') && (first_apostroph==-1))
    			first_apostroph = i;
    		else
        	if ((In.charAt(i)== '\"') && (first_apostroph!=-1))
        	{
        		second_apostroph=i;
        		break;
        	}
    	}
    	
    	if ((first_apostroph==-1) || (second_apostroph==-1)){
    		init_key = "";
    		return init_key;
    	}
    		
    	init_key = In.substring(first_apostroph+1, second_apostroph);
    	
    	
    	return init_key;
    }
    
	/**
	 * Task:<br>
	 * To trigger transformation according to selected test value and 
	 * previously selected source metatransformation
	 * @param id - int id from testValues table
	 * @return LinesOutDetails - to be rendered on screen
	 */
	Object onActionFromTransform(Long id){
		int key_len = 0;
		String init_key = "";
		
		// ovo je u stvari konstruktor
		TestValues tvrow = testValuesDao.find(id);
		ValueBeforeMetaTransformation = tvrow.getMetaTransformation_TestValue();
		persistValueBeforeMetaTransformation = ValueBeforeMetaTransformation;
		
		Source src = sourceDao.getCurrent();
		if (src == null){
			before_line0 = "You have to select at least one transformation - click on Edit-Fill!";
			before_line1 = "";
			before_line2 = "";
			before_line3 = "";
			line0 = "";
			line1 = "";
			line2 = "";
			line3 = "";
			
			LinesOut lines = new LinesOut(before_line0, before_line1, before_line2, before_line3,
					  line0, line1,	line2, line3); 
			detailLinesOut = lines;
			return LinesOutDetails;
		}
		
	
		String strToParse = src.getSourceCode();
		
		key_len = parseKey_len(strToParse);
		if (key_len == 0){
			before_line0 = "You must set key_len in a following way:";
			before_line1 = "key_len = 128; or";
			before_line2 = "key_len = 192; or";
			before_line3 = "key_len = 256;";
			line0 = "";
			line1 = "";
			line2 = "";
			line3 = "";
			
			LinesOut lines = new LinesOut(before_line0, before_line1, before_line2, before_line3,
					  line0, line1,	line2, line3); 
			detailLinesOut = lines;
			return LinesOutDetails;
		}
		
		
		init_key = parseInit_key(strToParse);
		if (init_key == ""){
			before_line0 = "You must set init_key. Examples:";
			before_line1 = "init_key = \"2b7e151628aed2a6abf7158809cf4f3c\"; or";
			before_line2 = "init_key = \"000102030405060708090a0b0c0d0e0f1011121314151617\"; or";
			before_line3 = "init_key = \"000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f\";";
			line0 = "";
			line1 = "";
			line2 = "";
			line3 = "";
			
			LinesOut lines = new LinesOut(before_line0, before_line1, before_line2, before_line3,
					  line0, line1,	line2, line3); 
			detailLinesOut = lines;
			return LinesOutDetails;
		}

		boolean greska = false;
		if (key_len==128){
			if (init_key.length()!=32)
				greska=true;
		}
		else 
		if (key_len==192){
			if (init_key.length()!=48)
				greska=true;
		}
		else
		if (key_len==256){
			if (init_key.length()!=64)
				greska=true;
		}
			
		if (greska){
			before_line0 = "You must set apropriate init_key lenghts. Examples:";
			before_line1 = "For 128 bit key, 32 hex letters.";
			before_line2 = "For 192 bit key, 48 hex letters.";
			before_line3 = "For 256 bit key, 64 hex letters.";
			line0 = "";
			line1 = "";
			line2 = "";
			line3 = "";
			
			LinesOut lines = new LinesOut(before_line0, before_line1, before_line2, before_line3,
					  line0, line1,	line2, line3); 
			detailLinesOut = lines;
			return LinesOutDetails;
		}

		
		if ((0!=key_len) && (""!=init_key) && (""!=strToParse))
			DoTransform(persistValueBeforeMetaTransformation, key_len, init_key, strToParse);
		
		return LinesOutDetails;   //TestValueRowDetails; odustao 17.09
		
	}
	
	/**
	 * Task:<br>
	 * To fill text area according to selected row in source grid
	 * @param id - int id - selected row id to show in text area
	 * @return metaTransformationPG
	 */
	Object onActionFromFill(Long id){
		Source source = sourceDao.find(id);
		//source_code=source.getSourceCode();
		String s = source.getSourceCode();
		setSource_code(s);

		return metaTransformationPG;
	}

	
	/**
	 * Task:<br>
	 * To delete row from source table/grid
	 * @param id - int id - selected row id to delete
	 * @return
	 */
	Object onActionFromDelete(Long id){
		String s = getSource_code();
		
		Source srce = sourceDao.find(id);
		sourceDao.delete(srce);
		return metaTransformationPG;
	}
	
	/**
	 * variable boolean _updateSource_code<br>
	 * indicate action to be performed on form success
	 * **/
	private boolean _updateSource_code;

    /**
     * Task:<br>
     * to set variable boolean _updateSource_code which
	 * indicate action to be performed on form success
     */
    void onSelectedFromUpdate() { _updateSource_code = true; }

    
	/**
	 * Task:<br>
	 * Depending on _updateSource_code value, update or insert new row in source table/grid<br>
	 * _updateSource_code - true: action = update;<br>
	 * _updateSource_code - false: action = insert new;<br>
	 * @return metaTransformationPG
	 */
	Object onSuccess() {
		if (_updateSource_code) {
			String s = getSource_code();
			Source srce = sourceDao.getCurrent();
			srce.setSourceCode(s);
			sourceDao.update(srce);
			return metaTransformationPG;
		}
		else
		{
			String s = getSource_code();
			Source srce = new Source();
			srce.setSourceCode(s); 
			srce.setUserId(user.getId());
			sourceDao.save(srce);
		}
		return metaTransformationPG;
	}


	/**
	 * getter for LinesOut
	 * @return LinesOut
	 */
	public LinesOut getLinesOut() {
		return LinesOut;
	}

	/**
	 * setter for LinesOut
	 * @param LinesOut
	 */
	public void setLinesOut(LinesOut LinesOut) {
		this.LinesOut = LinesOut;
	}


	/**
	 * getter for detailLinesOut
	 * @return detailLinesOut
	 */
	public LinesOut getDetailLinesOut() {
		return detailLinesOut;
	}	


	/**
	 * getter for before_line0
	 * @return before_line0
	 */
	public String getBefore_Line0() 
	{ 
		return before_line0; 
	}
	/**
	 * getter for before_line1
	 * @return before_line1
	 */
	public String getBefore_Line1() 
	{ 
		return before_line1; 
	}
	/**
	 * getter for before_line2
	 * @return before_line2
	 */
	public String getBefore_Line2() 
	{ 
		return before_line2; 
	}
	/**
	 * getter for before_line3
	 * @return before_line3
	 */
	public String getBefore_Line3() 
	{ 
		return before_line3; 
	}

	/**
	 * getter for line0
	 * @return line0
	 */
	public String getLine0() 
	{ 
		return line0; 
	}
	/**
	 * getter for line1
	 * @return line1
	 */
	public String getLine1() 
	{ 
		return line1; 
	}
	/**
	 * getter for line2
	 * @return line2
	 */
	public String getLine2() 
	{ 
		return line2; 
	}
	/**
	 * getter for line3
	 * @return line3
	 */
	public String getLine3() 
	{ 
		return line3; 
	}

	
	
	/***************************************************************/


}
