package edu.crypto2.pages.values;


import java.util.List;


import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.annotations.OnEvent;

import edu.crypto2.entities.TestValues;

import edu.crypto2.pages.SubBytesPG;
import edu.crypto2.pages.values.CreateTestValues;


import edu.crypto2.services.TestValuesDao;


import org.hibernate.Session;


public class CreateTestValues {
    @Inject
    private Session session;
    @Inject
    private TestValuesDao testValuesDao;
    private List<TestValues> Grid_testValues;
    @Parameter
    private TestValues testValues;

	@SetupRender
	boolean setup() {
		testValuesDao.reload();
		//List<TestValues> result = session.createCriteria(TestValues.class).list();
		return true;
	}

    /**********************************************************/
    /*  link za prelazak na umetanje test vrijednosti         */
    @InjectPage
    private CreateTestValues createTestValues;
    @CommitAfter
    Object onSuccess()
    {
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
