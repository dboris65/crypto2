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

import bsh.EvalError;
/******************************************************/
import edu.crypto2.entities.Source;
import edu.crypto2.entities.TestValues;
import edu.crypto2.services.SourceDao;
import edu.crypto2.services.TestValuesDao;

import edu.crypto2.transformations.MetaTransformation;
import edu.crypto2.rest.XmlParser;
import edu.crypto2.components.LinesOut;
import edu.crypto2.data.*;
/***********************************************************************
 * 
 */
public class MetaTransformationPG {
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

	@SetupRender
	boolean setup() throws Exception {
		/* Ovo odstupa od skolskih primjera za rad sa Tapestry-jem i DAO-ima.
		 * 
		 * Ako prvi put udjemo u stranicu, ona ce ucitati i formirati TestValuesDao
		 * iz baze podataka. Pri svakom slijedecem ulasku na ovu (ili neku drugu)
		 * stranicu, uvjek cemo dobiti te iste vrijednosti, sto je potencijalna greska.
		 * 
		 * PRIMJER potencijalne greske:
		 * Ako poslije ucitavanja neke od strana sa transformacijama
		 * npr. odemo na stranicu za unos novih vrijednosti i unesemo nesto novo,
		 * nakon povratka na ovu stranicu necemo vidjeti novu vrijednost - DAO je ranije ucitan.
		 * 
		 * RJESENJE potencijalne greske - Reload()
		 * Napisati funkciju reload, koja obezbjedjuje da ce pri svakom ulasku na stranicu 
		 * DAO vrijednosti biti (pravilno) ponovo ucitane i prikazane.
		 * 
		 * Ovdje bi mogli i bez SourceDAO, jer se on na drugim stranicama ne javlja, ali
		 * ako budemo prosirivali aplikaciju...*/
		sourceDao.reload();
		testValuesDao.reload();
		
		
		if ((SesionValueBeforeMetaTransformation == "") || (SesionValueBeforeMetaTransformation == null)) 
		{
			final Logger logger = Logger.getLogger(MetaTransformationPG.class);
			logger.debug("-------------------------------------------");
			logger.debug(SesionValueBeforeMetaTransformation);
			logger.debug("-------------------------------------------");
			logger.debug("ulaz u 1");
			logger.debug("-------------------------------------------");
			
			xml_p = new XmlParser("MetaTransformation"); 
			String ValueBefore = xml_p.getResultString();
			DoTransform(ValueBefore, 128, "2b7e151628aed2a6abf7158809cf4f3c", "TEST");
		}
		else
		{
			final Logger logger = Logger.getLogger(MetaTransformationPG.class);
			logger.debug("-------------------------------------------");
			logger.debug(SesionValueBeforeMetaTransformation);
			logger.debug("-------------------------------------------");
			logger.debug("ulaz u 2");
			logger.debug("-------------------------------------------");
			DoTransform(SesionValueBeforeMetaTransformation, 128, "2b7e151628aed2a6abf7158809cf4f3c", "TEST");
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
	


	
	@Persist
	private String SesionValueBeforeMetaTransformation;
    
	
	
	
	
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

	
    public void DoTransform(String ValueBefore, int key_len, String init_key, String MetaTr){
    	ValueBeforeMetaTransformation = ValueBefore;
    	SesionValueBeforeMetaTransformation = ValueBefore;
		meta_transformation = new MetaTransformation();
		
		//String init_key = "2b7e151628aed2a6abf7158809cf4f3c";
		final Logger logger = Logger.getLogger(MetaTransformationPG.class);
		logger.debug("DoTransform---KeyLen---" + key_len);
		logger.debug("DoTransform---init_key---" + init_key);

		meta_transformation.initialize_State(ValueBefore, key_len, init_key);
		logger.debug("=======DoTransform---KeyLen---" + key_len + "=======");
		logger.debug("=======DoTransform---init_key---" + init_key + "=======");

		
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
		
		meta_transformation.transform_state(ValueBefore, key_len, init_key, MetaTr);
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
    
    /*
     * Return 0 on error*/
    int parseKey_len(String In){
    	int key_len = 0;
    	int pos = -1;
    	String substr;
    	
    	if (In == null)
    		return 0;
    	
    	final Logger logger = Logger.getLogger(MetaTransformationPG.class);
    	In = In.toUpperCase();
    	logger.debug("-------->>>>....." +  In + " = in ---------------" );
    	pos = In.lastIndexOf("KEY_LEN");
    	
    	logger.debug("parseKeyLen---------------" );
		logger.debug(pos + " = pos ---------------" );
    	if (pos != -1)
    		substr = In.substring(pos, pos+14); // max alowed "KEY_LEN = 128;"
    	else
    		return 0;
    	
    	logger.debug("SubStr---------------" );
		logger.debug(substr + " = substr ---------------" );

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

    /*
     * Return "" on error*/
    String parseInit_key(String In){
    	String init_key = "";
    	int pos = -1;
    	int first_apostroph = -1;
    	int second_apostroph = -1;
    	String substr;
    	final Logger logger = Logger.getLogger(MetaTransformationPG.class);
    	if (In == null)
    		return "";
    	
    	In = In.toUpperCase();
    	pos = In.lastIndexOf("INIT_KEY");
    	logger.debug("-------->>>>....." +  In + " = in ---------------" );
    	logger.debug("-------->>>>....." +  pos + " = pos ---------------" );
    	
    	if (pos == -1)
    		return "";
    	
    	logger.debug("-------->>>>....." +  In.charAt(pos) + " = In.charAt(pos) ---------------" );
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
    		
    	logger.debug("-------->>>>....." +  first_apostroph + " = first_apostroph ---------------" );
    	logger.debug("-------->>>>....." +  second_apostroph + " = second_apostroph ---------------" );

    	init_key = In.substring(first_apostroph+1, second_apostroph);
    	
    	
    	return init_key;
    }
    
	Object onActionFromTransform(Long id){
		int key_len = 0;
		String init_key = "";
		
		// ovo je u stvari konstruktor
		TestValues tvrow = testValuesDao.find(id);
		ValueBeforeMetaTransformation = tvrow.getMetaTransformation_TestValue();
		SesionValueBeforeMetaTransformation = ValueBeforeMetaTransformation;
		
		final Logger logger = Logger.getLogger(MetaTransformationPG.class);
		
		
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
		
		logger.debug("after srce costruct---------------" );
		logger.debug(src + "---------------" );
		
		String strToParse = src.getSourceCode();
		logger.debug("After StrToParse---------------" );
		logger.debug("---------------" + strToParse);
		
		
		logger.debug("Before KeyLen Parse---------------" );
		key_len = parseKey_len(strToParse);
		logger.debug("After KeyLen Parse---------------" );
		logger.debug(key_len);
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
		
		
		logger.debug("Before InitKey Parse---------------" );
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

		logger.debug("---------------" );
		logger.debug("---------------" );
		logger.debug("---------------" );
		logger.debug("-----Keylen--" + key_len);
		logger.debug("-----Key--" + init_key);
		logger.debug("---------------" );
		logger.debug("---------------" );
		logger.debug("---------------" );
		
		

		
		if ((0!=key_len) && (""!=init_key) && (""!=strToParse))
			DoTransform(SesionValueBeforeMetaTransformation, key_len, init_key, strToParse);
		
		return LinesOutDetails;   //TestValueRowDetails; odustao 17.09
		
	}
	
	Object onActionFromFill(Long id){
		Source source = sourceDao.find(id);
		//source_code=source.getSourceCode();
		String s = source.getSourceCode();
		setSource_code(s);
		final Logger logger = Logger.getLogger(MetaTransformationPG.class);
		logger.debug("-----Meta fill--");
		logger.debug("-----Meta fill--");
		logger.debug("-----Meta fill--");
		logger.debug("-----Meta fill--");
		logger.debug("-----Meta fill--");
		logger.debug(source_code);
		logger.debug("-----Meta fill--");
		logger.debug("-----Meta fill--");
		logger.debug("-----Meta fill--");
		logger.debug("-----Meta fill--");
		logger.debug("-----Meta fill--");

		return metaTransformationPG;
	}

	
	Object onActionFromDelete(Long id){
		String s = getSource_code();
		final Logger logger = Logger.getLogger(MetaTransformationPG.class);
		logger.debug("-----Meta delete--");
		logger.debug("-----Meta delete--");
		logger.debug("-----Meta delete--");
		logger.debug("-----Meta delete--");
		//logger.debug(source_code);
		logger.debug(s);
		logger.debug("-----Meta delete--");
		logger.debug("-----Meta delete--");
		logger.debug("-----Meta delete--");
		logger.debug("-----Meta delete--");
		
		
		
		Source srce = sourceDao.find(id);
		logger.debug("-----Before delete(srce)--");
		logger.debug("-----Before delete(srce)--");
		sourceDao.delete(srce);
		return metaTransformationPG;
	}
	
	private boolean _updateSource_code;

    void onSelectedFromUpdate() { _updateSource_code = true; }

	Object onSuccess() {
		if (_updateSource_code) {
			String s = getSource_code();
			final Logger logger = Logger.getLogger(MetaTransformationPG.class);
			logger.debug("-----Meta Update_selected--");
			logger.debug("-----Meta Update_selected--");
			logger.debug("-----Meta Update_selected--");
			logger.debug("-----Meta Update_selected--");
			//logger.debug(source_code);
			logger.debug(s);
			logger.debug("-----Meta Update_selected--");
			logger.debug("-----Meta Update_selected--");
			logger.debug("-----Meta Update_selected--");
			logger.debug("-----Meta Update_selected--");
			
			
			
			Source srce = sourceDao.getCurrent();
			logger.debug("-----Before Update_selected(srce)--");
			logger.debug("-----Before Update_selected(srce)--");
			srce.setSourceCode(s);
			sourceDao.update(srce);
			return metaTransformationPG;
			
		}
		else
		{
			final Logger logger = Logger.getLogger(MetaTransformationPG.class);
			logger.debug("-----Meta save--");
			logger.debug("-----Before getSource_codee--");
			logger.debug("-----Before getSource_codee--");
			
			String s = getSource_code();
			logger.debug("-----Meta save--");
			logger.debug("-----Meta save--");
			logger.debug("-----Meta save--");
			logger.debug("-----Meta save--");
			logger.debug("-----Meta save--");
			logger.debug(source_code);
			logger.debug(s);
	
			logger.debug("-----Before new Source--");
			logger.debug("-----Before new Source--");
			Source srce = new Source();
			srce.setSourceCode(s); 
			srce.setId(srce.getId());
	
			logger.debug("-----Before save(srce)--");
			logger.debug("-----Before save(srce)--");
			sourceDao.save(srce);
			
			logger.debug("-----Meta save--");
			logger.debug("-----Meta save--");
			logger.debug("-----Meta save--");
			logger.debug("-----Meta save--");
		}
		return metaTransformationPG;
		
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
