/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.services;


import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.crypto2.entities.Source;
import edu.crypto2.entities.TestValues;
import edu.crypto2.pages.MetaTransformationPG;
import edu.crypto2.rest.HibernateUtil;
/***********************************************************************
 * 
 */
public class TestValuesDaoImpl implements TestValuesDao{
	private static long CurrentId = 0;
    
	@Inject
    private Session session;
    
	private List<TestValues> database; 
	
	public void reload(){
		if (session.isOpen())
			session.close();
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		database = session.createQuery("from TestValues").list();
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
				// AES 128 - 1, from FIPS197 pg.33
				transaction = null;
				transaction = session.beginTransaction();
				TestValues testValues = new TestValues();
				testValues.setShiftRows_TestValue("d42711aee0bf98f1b8b45de51e415230");
				testValues.setMixColumns_TestValue("d4bf5d30e0b452aeb84111f11e2798e5");
				testValues.setSubBytes_TestValue("193de3bea0f4e22b9ac68d2ae9f84808");
				testValues.setKeyExpansion_TestValue("2b7e151628aed2a6abf7158809cf4f3c");  // init key
				testValues.setAddRoundKey_TestValue("046681e5e0cb199a48f8d37a2806264c");
				testValues.setMetaTransformation_TestValue("3243f6a8885a308d313198a2e0370734");
				/*
				testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
				testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
				testValues.setInvSubBytes_TestValue("33");
				testValues.setGMUL_TestValue("229");
				*/
				
				session.persist(testValues);
				transaction.commit();

				// AES 128 - 2, from FIPS197, pg.35-36
				transaction = session.beginTransaction();
				testValues = new TestValues();
				testValues.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
				testValues.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
				testValues.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
				testValues.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f");  // init key
				testValues.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
				testValues.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff");
				
				/*
				testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
				testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
				testValues.setInvSubBytes_TestValue("33");
				testValues.setGMUL_TestValue("229");
				*/
				session.persist(testValues);
				transaction.commit();

				// AES 192, from FIPS197, pg.38-39
				transaction = session.beginTransaction();
				testValues = new TestValues();
				testValues.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
				testValues.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
				testValues.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
				testValues.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f1011121314151617");  // init key
				testValues.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
				testValues.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff");
				/*
				testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
				testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
				testValues.setInvSubBytes_TestValue("33");
				testValues.setGMUL_TestValue("229");
				*/
				session.persist(testValues);
				transaction.commit();
				
				// AES 256, from FIPS197, pg.38-39
				transaction = session.beginTransaction();
				testValues = new TestValues();
				testValues.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
				testValues.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
				testValues.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
				testValues.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f"); // init key
				testValues.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
				testValues.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff"); // plain text
				
				/*
				  testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
				  testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
				  testValues.setInvSubBytes_TestValue("33");
					testValues.setGMUL_TestValue("229");
				*/
				session.persist(testValues);
				transaction.commit();

				CurrentId = testValues.getId(); 
			}
			
			transaction = null;
			transaction = session.beginTransaction();
			database = session.createQuery("from TestValues").list();
			transaction.commit();

			final Logger logger = Logger.getLogger(MetaTransformationPG.class);
			logger.debug("createQuery(from Source)--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
			logger.debug("--------------------------------------------------------------");
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
			String hql = "delete from Source where id = " + testValues.getId();
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
