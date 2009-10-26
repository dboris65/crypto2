/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
/******************************************************/
import edu.crypto2.entities.TestValues;
import edu.crypto2.entities.User;
import edu.crypto2.services.TestValuesDao;

import edu.crypto2.transformations.ShiftRows;
import edu.crypto2.rest.XmlParser;
import edu.crypto2.components.LinesOut;
import edu.crypto2.data.*;
/***********************************************************************
 * 
 */
public class ShiftRowsPG {
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
		 * DAO vrijednosti biti (pravilno) ponovo ucitane i prikazane.*/
		testValuesDao.reload();
		
		if (user != null){
			UserName = (user.getName());
		}
		else{
			UserName = "";
		}
		
		if ((SesionValueBeforeShiftRows == "") || (SesionValueBeforeShiftRows == null)) 
		{
			final Logger logger = Logger.getLogger(SubBytesPG.class);
			logger.debug("--------------------------------------------------------------");
			logger.debug(SesionValueBeforeShiftRows);
			logger.debug("--------------------------------------------------------------");
			logger.debug("ulaz u 1");
			logger.debug("--------------------------------------------------------------");
			
			xml_p = new XmlParser("SubBytes"); 
			String ValueBefore = xml_p.getResultString();
			DoTransform(ValueBefore);
		}
		else
		{
			final Logger logger = Logger.getLogger(SubBytesPG.class);
			logger.debug("--------------------------------------------------------------");
			logger.debug(SesionValueBeforeShiftRows);
			logger.debug("--------------------------------------------------------------");
			logger.debug("ulaz u 2");
			logger.debug("--------------------------------------------------------------");
			DoTransform(SesionValueBeforeShiftRows);
		}

		return true;
	}
	
	@Inject
	private Block LinesOutDetails;
	private LinesOut LinesOut;
	private LinesOut detailLinesOut;

	//***********************************************************************
		
	
	private ShiftRows shift_rows;
	private XmlParser xml_p;
	
	private String ValueBeforeShiftRows;


	public String getValueBeforeShiftRows() {
		return ValueBeforeShiftRows;
	}


	public void setValueBeforeShiftRows(String valueBeforeShiftRows) {
		ValueBeforeShiftRows = valueBeforeShiftRows;
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
	private String SesionValueBeforeShiftRows;
	

	
    public void DoTransform(String ValueBefore){
    	ValueBeforeShiftRows = ValueBefore;
    	SesionValueBeforeShiftRows = ValueBefore;
		shift_rows = new ShiftRows();
		shift_rows.initialize_State(ValueBeforeShiftRows);
		
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
		
		shift_rows.transform_state();
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
    
	public ShiftRowsPG() throws Exception 
	{
		

		
	}

	
    @InjectPage
    private SubBytesPG subBytesPG;
	Object onActionFromView(Long id){
		// ovo je u stvari konstruktor
		TestValues tvrow = testValuesDao.find(id);
		ValueBeforeShiftRows = tvrow.getShiftRows_TestValue();
		SesionValueBeforeShiftRows = ValueBeforeShiftRows;
		
		final Logger logger = Logger.getLogger(SubBytesPG.class);
		logger.debug("--------------------------------------------------------------");
		logger.debug(SesionValueBeforeShiftRows);
		logger.debug("--------------------------------------------------------------");

		DoTransform(SesionValueBeforeShiftRows);
		logger.debug(line0);		
		return LinesOutDetails;   //TestValueRowDetails; odustao 17.09
	}

    @InjectPage
    private Index index;
	Object onActionFromLogOut(){
		user = null;
		return index;
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
