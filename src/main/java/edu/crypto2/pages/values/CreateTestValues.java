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

import edu.crypto2.pages.Index;
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
	
    @InjectPage
    private Index index;
	Object onActionFromLogOut(){
		user = null;
		return index;
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
		return true;
	}

    /**********************************************************/
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
    			return false;
    		}
    	}
    	return true;
    }
    
    void onValidateForm()
	{
    	boolean err = false;
    	// FIRST round *********************************************
    	// some of these can be done through Validate anotation, but
    	// not all of them. So we will keep all of them on same place. 
		if (testValues.getSubBytes_TestValue().length() != 32){
			form.recordError("Invalid SubBytes test vector length.");
			err = true;
		}
		if (testValues.getMixColumns_TestValue().length() != 32){
			form.recordError("Invalid MixColumns test vector length.");
			err = true;
		}
		if (testValues.getShiftRows_TestValue().length() != 32){
			form.recordError("Invalid ShiftRows test vector length.");
			err = true;
		}
		if (testValues.getAddRoundKey_TestValue().length() != 32){
			form.recordError("Invalid AddRoundKey test vector length.");
			err = true;
		}
		if (testValues.getMetaTransformation_TestValue().length() != 32){
			form.recordError("Invalid MetaTransformation test vector length.");
			err = true;
		}
		if ((testValues.getKeyExpansion_TestValue().length() != 32) &&
			(testValues.getKeyExpansion_TestValue().length() != 48) &&
			(testValues.getKeyExpansion_TestValue().length() != 64)	){
			form.recordError("Invalid KeyExpansion test vector length.");
			err = true;
		}

		if (testValues.getInvSubBytes_TestValue().length() != 32){
			form.recordError("Invalid InvSubBytes test vector length.");
			err = true;
		}
		if (testValues.getInvMixColumns_TestValue().length() != 32){
			form.recordError("Invalid InvMixColumns test vector length.");
			err = true;
		}
		if (testValues.getInvShiftRows_TestValue().length() != 32){
			form.recordError("Invalid InvShiftRows test vector length.");
			err = true;
		}
		if (testValues.getInvMetaTransformation_TestValue().length() != 32){
			form.recordError("Invalid InvMetaTransformation test vector length.");
			err = true;
		}
	
    	// SECOND round *********************************************
		if (!isHexDigit(testValues.getSubBytes_TestValue())){
			form.recordError("Invalid SubBytes test vector value.");
			err = true;
		}
		if (!isHexDigit(testValues.getMixColumns_TestValue())){
			form.recordError("Invalid MixColumns test vector value.");
			err = true;
		}
		if (!isHexDigit(testValues.getShiftRows_TestValue())){
			form.recordError("Invalid ShiftRows test vector value.");
			err = true;
		}
		if (!isHexDigit(testValues.getAddRoundKey_TestValue())){
			form.recordError("Invalid AddRoundKey test vector value.");
			err = true;
		}
		if (!isHexDigit(testValues.getMetaTransformation_TestValue())){
			form.recordError("Invalid MetaTransformation test vector value.");
			err = true;
		}
		if (!isHexDigit(testValues.getKeyExpansion_TestValue())){
			form.recordError("Invalid KeyExpansion test vector value.");
			err = true;
		}
		
		if (!isHexDigit(testValues.getInvSubBytes_TestValue())){
			form.recordError("Invalid InvSubBytes test vector value.");
			err = true;
		}
		if (!isHexDigit(testValues.getInvMixColumns_TestValue())){
			form.recordError("Invalid InvMixColumns test vector value.");
			err = true;
		}
		if (!isHexDigit(testValues.getInvShiftRows_TestValue())){
			form.recordError("Invalid InvShiftRows test vector value.");
			err = true;
		}
		if (!isHexDigit(testValues.getInvMetaTransformation_TestValue())){
			form.recordError("Invalid InvMetaTransformation test vector value.");
			err = true;
		}

	}

	
    @CommitAfter
    Object onSuccess()
    {
    	testValues.setUserId(user.getId());
        session.persist(testValues);
   	    List<TestValues> result = session.createCriteria(TestValues.class).list();
   	    
        return createTestValues;
    }
     /**********************************************************/
     
     @OnEvent(value="submit", component="testValues")
     public List<TestValues>  onFormSubmit(){
    	 List<TestValues> result = session.createCriteria(TestValues.class).list();
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
		List<TestValues> GtestValues = session.createCriteria(TestValues.class).list();
    	long GetId;
		for (TestValues testValues: GtestValues) {
			GetId = testValues.getId();
			if( GetId==id ){
				session.delete(testValues);
			}
		}
		return Grid_testValues;
	}



	
}
