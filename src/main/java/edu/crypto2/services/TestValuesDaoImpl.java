/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.services;


import java.util.List;


import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import edu.crypto2.entities.TestValues;
import edu.crypto2.entities.User;
import edu.crypto2.pages.MetaTransformationPG;

import edu.crypto2.rest.HibernateUtil;
/***********************************************************************
 * 
 */
public class TestValuesDaoImpl implements TestValuesDao{
	@SessionState(create=false)
	private User user;
    public boolean getLoggedIn() {
        if (user != null)
            return true;
        else
            return false;
    }

	private static long CurrentId = 0;
    
	@Inject
    private Session session;
    
	private List<TestValues> database; 
	
	public void reload(long userId){
		UserDao userDao = new UserDaoImpl();
		user = userDao.find(userId);

		String qry = "from TestValues where USERID=" + user.getId();

		
		if (session.isOpen())
			session.close();
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		database = session.createQuery(qry).list();
		
		if (database.isEmpty())
		{
			// Give him some test vectors
			// AES 128 - 1, from FIPS197 pg.33
			transaction = null;
			transaction = session.beginTransaction();
			TestValues testValues1 = new TestValues();
			testValues1.setShiftRows_TestValue("d42711aee0bf98f1b8b45de51e415230");
			testValues1.setMixColumns_TestValue("d4bf5d30e0b452aeb84111f11e2798e5");
			testValues1.setSubBytes_TestValue("193de3bea0f4e22b9ac68d2ae9f84808");
			testValues1.setKeyExpansion_TestValue("2b7e151628aed2a6abf7158809cf4f3c");  // init key
			testValues1.setAddRoundKey_TestValue("046681e5e0cb199a48f8d37a2806264c");
			testValues1.setMetaTransformation_TestValue("3243f6a8885a308d313198a2e0370734");
			// Here, UserId is always 1
			testValues1.setUserId(userId);
			
			/*
			testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
			testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
			testValues.setInvSubBytes_TestValue("33");
			testValues.setGMUL_TestValue("229");
			*/
			
			session.persist(testValues1);
			transaction.commit();

			// AES 128 - 2, from FIPS197, pg.35-36
			transaction = session.beginTransaction();
			TestValues testValues2 = new TestValues();
			testValues2.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
			testValues2.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
			testValues2.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
			testValues2.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f");  // init key
			testValues2.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
			testValues2.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff");
			// Here, UserId is always 1
			testValues2.setUserId(userId);
			
			/*
			testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
			testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
			testValues.setInvSubBytes_TestValue("33");
			testValues.setGMUL_TestValue("229");
			*/
			session.persist(testValues2);
			transaction.commit();

			// AES 192, from FIPS197, pg.38-39
			transaction = session.beginTransaction();
			TestValues testValues3 = new TestValues();
			testValues3.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
			testValues3.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
			testValues3.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
			testValues3.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f1011121314151617");  // init key
			testValues3.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
			testValues3.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff");
			// Here, UserId is always 1
			testValues3.setUserId(userId);
			
			/*
			testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
			testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
			testValues.setInvSubBytes_TestValue("33");
			testValues.setGMUL_TestValue("229");
			*/
			session.persist(testValues3);
			transaction.commit();
			
			// AES 256, from FIPS197, pg.38-39
			transaction = session.beginTransaction();
			TestValues testValues4 = new TestValues();
			testValues4.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
			testValues4.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
			testValues4.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
			testValues4.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f"); // init key
			testValues4.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
			testValues4.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff"); // plain text
			// Here, UserId is always 1
			testValues4.setUserId(userId);
			
			/*
			  testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
			  testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
			  testValues.setInvSubBytes_TestValue("33");
				testValues.setGMUL_TestValue("229");
			*/
			session.persist(testValues4);
			transaction.commit();

			CurrentId = testValues4.getId(); 
		}
		transaction = null;
		transaction = session.beginTransaction();
		String qry2 = "from TestValues where USERID=" + user.getId();
		database = session.createQuery(qry2).list();
		transaction.commit();

	
	}
	
	public TestValuesDaoImpl(){
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			//transaction = null;
			transaction = session.beginTransaction();
			database = session.createQuery("from TestValues").list();
			transaction.commit();
			if (database.isEmpty())
			{
				// Give him some test vectors
				// AES 128 - 1, from FIPS197 pg.33
				transaction = null;
				transaction = session.beginTransaction();
				TestValues testValues1 = new TestValues();
				testValues1.setShiftRows_TestValue("d42711aee0bf98f1b8b45de51e415230");
				testValues1.setMixColumns_TestValue("d4bf5d30e0b452aeb84111f11e2798e5");
				testValues1.setSubBytes_TestValue("193de3bea0f4e22b9ac68d2ae9f84808");
				testValues1.setKeyExpansion_TestValue("2b7e151628aed2a6abf7158809cf4f3c");  // init key
				testValues1.setAddRoundKey_TestValue("046681e5e0cb199a48f8d37a2806264c");
				testValues1.setMetaTransformation_TestValue("3243f6a8885a308d313198a2e0370734");
				// Here, UserId is always 1
				testValues1.setUserId((long)1);
				
				/*
				testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
				testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
				testValues.setInvSubBytes_TestValue("33");
				testValues.setGMUL_TestValue("229");
				*/
				
				session.persist(testValues1);
				transaction.commit();

				// AES 128 - 2, from FIPS197, pg.35-36
				transaction = session.beginTransaction();
				TestValues testValues2 = new TestValues();
				testValues2.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
				testValues2.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
				testValues2.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
				testValues2.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f");  // init key
				testValues2.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
				testValues2.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff");
				// Here, UserId is always 1
				testValues2.setUserId((long)1);
				
				/*
				testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
				testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
				testValues.setInvSubBytes_TestValue("33");
				testValues.setGMUL_TestValue("229");
				*/
				session.persist(testValues2);
				transaction.commit();

				// AES 192, from FIPS197, pg.38-39
				transaction = session.beginTransaction();
				TestValues testValues3 = new TestValues();
				testValues3.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
				testValues3.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
				testValues3.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
				testValues3.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f1011121314151617");  // init key
				testValues3.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
				testValues3.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff");
				// Here, UserId is always 1
				testValues3.setUserId((long)1);
				
				/*
				testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
				testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
				testValues.setInvSubBytes_TestValue("33");
				testValues.setGMUL_TestValue("229");
				*/
				session.persist(testValues3);
				transaction.commit();
				
				// AES 256, from FIPS197, pg.38-39
				transaction = session.beginTransaction();
				TestValues testValues4 = new TestValues();
				testValues4.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
				testValues4.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
				testValues4.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
				testValues4.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f"); // init key
				testValues4.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
				testValues4.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff"); // plain text
				// Here, UserId is always 1
				testValues4.setUserId((long)1);
				
				/*
				  testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
				  testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
				  testValues.setInvSubBytes_TestValue("33");
					testValues.setGMUL_TestValue("229");
				*/
				session.persist(testValues4);
				transaction.commit();

				CurrentId = testValues4.getId(); 
			}
			
			transaction = null;
			transaction = session.beginTransaction();
			database = session.createQuery("from TestValues").list();
			transaction.commit();
		}catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
		

	
	public List<TestValues> findAllTestValues() {
		return database;
	}

	public void save(TestValues testValues) {
		Transaction transaction = null;
		transaction = session.beginTransaction();
		database.add(testValues);
		session.persist(testValues);
		transaction.commit();
		CurrentId = testValues.getId().intValue();
	}
	
	public void update(TestValues testValues) {
		Transaction transaction = null;
		transaction = session.beginTransaction();
		session.update(testValues);
		transaction.commit();
	}
	
	
	public TestValues find(long id) {
		for (TestValues testValues: database) {
			if(testValues.getId()==id){
				CurrentId = testValues.getId().intValue();
				return testValues;
			}	
		}
		return null;
	}
	
	public TestValues getCurrent(){
		for (TestValues testValues: database) {
			if(testValues.getId()==CurrentId){
				return testValues;
			}	
		}
		return null;		
	}

	public void delete(TestValues testValues) {
		if(testValues!=null && database.contains(testValues)){
			String hql = "delete from TestValues where id = " + testValues.getId();
			Transaction transaction = null;
			transaction = session.getTransaction();
			if (transaction.isActive()) 
				transaction.commit();
			transaction = session.beginTransaction();
			database.remove(testValues);
			Query query = session.createQuery(hql);
			query.executeUpdate();
			transaction.commit();
		}
		CurrentId = testValues.getId().intValue();
	}

	public TestValues findTestValuesBySubBytesValue(String name) {
		TestValues found = null;
		for (TestValues testValues : database) {
			if (name.equals(testValues.getSubBytes_TestValue())) {
				found = testValues;
				break;
			}
		}
		return found;
	}




}
