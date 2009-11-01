package edu.crypto2.pages.values;


import java.util.List;


import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.corelib.components.BeanEditForm;

import edu.crypto2.entities.TestValues;
import edu.crypto2.entities.User;

import edu.crypto2.pages.SubBytesPG;
import edu.crypto2.pages.values.CreateTestValues;


import edu.crypto2.services.TestValuesDao;


import org.hibernate.Session;


public class CreateTestValues {
	@SessionState(create=false)
	private User user;
	
	@Property
	private String UserName = "";
    @Component
    private BeanEditForm form;

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
    private List<TestValues> Grid_testValues;
    @Parameter
    private TestValues testValues;

	@SetupRender
	boolean setup() {
		long userId = 1; 
		if (user != null){
			UserName = (user.getName());
			userId = user.getId(); 
		}
		else{
			UserName = "";
			userId = 1;
		}
		
		testValuesDao.reload(userId);
		//List<TestValues> result = session.createCriteria(TestValues.class).list();
		return true;
	}

    /**********************************************************/
    /*  link za prelazak na umetanje test vrijednosti         */
    @InjectPage
    private CreateTestValues createTestValues;

    public boolean isHexDigit(String hexDigit)
    {
    	char[] hexDigitArray = hexDigit.toCharArray();
    	int hexDigitLength = hexDigitArray.length;

    	boolean isNotHex;
    	for (int i = 0; i < hexDigitLength; i++) {
    		isNotHex = Character.digit(hexDigitArray[i], 16) == -1;
    		if (isNotHex) {
    			final Logger logger = Logger.getLogger(SubBytesPG.class);
    			logger.debug("-----------------------------------");
    			logger.debug("I =" + i);
    			logger.debug("hexDigitArray[i] =" + hexDigitArray[i]);
    			logger.debug("-----------------------------------");

    			return false;
    		}
    	}
    	return true;
    }
    
    void onValidateForm()
	{
    	// FIRST round *********************************************
    	// some of these can be done through Validate anotation, but
    	// not all of them. So we will keep all of them on same place. 
		if (testValues.getSubBytes_TestValue().length() != 32)
			form.recordError("Invalid SubBytes test vector length.");
		if (testValues.getMixColumns_TestValue().length() != 32)
			form.recordError("Invalid MixColumns test vector length.");
		if (testValues.getShiftRows_TestValue().length() != 32)
			form.recordError("Invalid ShiftRows test vector length.");
		if (testValues.getAddRoundKey_TestValue().length() != 32)
			form.recordError("Invalid AddRoundKey test vector length.");
		if (testValues.getMetaTransformation_TestValue().length() != 32)
			form.recordError("Invalid MetaTransformation test vector length.");
		if ((testValues.getKeyExpansion_TestValue().length() != 32) &&
			(testValues.getKeyExpansion_TestValue().length() != 48) &&
			(testValues.getKeyExpansion_TestValue().length() != 64)	)
			form.recordError("Invalid KeyExpansion test vector length.");
	
    	// SECOND round *********************************************
		if (!isHexDigit(testValues.getSubBytes_TestValue()))
			form.recordError("Invalid SubBytes test vector value.");
		if (!isHexDigit(testValues.getMixColumns_TestValue()))
			form.recordError("Invalid MixColumns test vector value.");
		if (!isHexDigit(testValues.getShiftRows_TestValue()))
			form.recordError("Invalid ShiftRows test vector value.");
		if (!isHexDigit(testValues.getAddRoundKey_TestValue()))
			form.recordError("Invalid AddRoundKey test vector value.");
		if (!isHexDigit(testValues.getMetaTransformation_TestValue()))
			form.recordError("Invalid MetaTransformation test vector value.");
		if (!isHexDigit(testValues.getKeyExpansion_TestValue()))
			form.recordError("Invalid KeyExpansion test vector value.");
				
	}

    @CommitAfter
    Object onSuccess()
    {
    	testValues.setUserId(user.getId());
        session.persist(testValues);
   	    List<TestValues> result = session.createCriteria(TestValues.class).list();
	    System.out.println(result);
	    System.out.println("----");
        return createTestValues;
    }
     /*  KRAJ link za prelazak na umetanje test vrijednosti         */
     /**********************************************************/
     
     @OnEvent(value="submit", component="testValues")
     public List<TestValues>  onFormSubmit(){
    	 List<TestValues> result = session.createCriteria(TestValues.class).list();
    	 System.out.println(result);
    	 return result;
     }
     
     
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
	
    public List<TestValues> getGrid_testValues()
    {
    	
    	
    	List<TestValues> database;
    	database = session.createCriteria(TestValues.class).list();
    	Grid_testValues = database; 
    	 
    	return Grid_testValues;
        
    	
    }


    @CommitAfter
	Object onActionFromDelete(Long id){

		
		final Logger logger = Logger.getLogger(SubBytesPG.class);
		logger.debug("-----------------------------------");
		logger.debug("Id to delete =" + id);
		logger.debug("-----------------------------------");
		
		List<TestValues> GtestValues = session.createCriteria(TestValues.class).list();
    	long GetId;
		for (TestValues testValues: GtestValues) {

			GetId = testValues.getId().longValue();

			if( GetId==id ){
				
				logger.debug("-----------------------------------");
				logger.debug("Id=" + id);
				logger.debug("-----------------------------------");
				
				session.delete(testValues);    //createSQLQuery("delete from TestValueRow where id =" + id);
			}
				//if(testValueRow!=null)
				//	testValueRowDao.delete(testValueRow);
		}
		
		//session.flush();
		return Grid_testValues;
  
	}



	
}
