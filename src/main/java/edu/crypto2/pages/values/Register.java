/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.pages.values;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;


import edu.crypto2.entities.TestValues;
import edu.crypto2.entities.User;
import edu.crypto2.pages.Index;
import edu.crypto2.pages.SubBytesPG;
import edu.crypto2.services.UserDao;

/***********************************************************************
 * 
 */
public class Register {
	@Property
	private User user;
    @Inject
    private Session session;
    @InjectPage
    private Index index;
    @Inject
    private UserDao userDao;
    @Property
    private String userName;
    @Property
    private String password;
    
    @Component
    private BeanEditForm form;
    
	@SetupRender
	boolean setup() throws Exception {
		userDao.reload();
		if (userDao != null)
			return true;
		else
			return false;
	}
	
	public boolean isValid(String userName, String password){
		userDao.reload();
		User find_user;
		find_user = userDao.findUserName(userName);
		if ((find_user != null) )
		{
			if ((find_user.getUserName().equals(userName)) )
				return false;
		}
		find_user = userDao.findPassword(password);
		if ((find_user != null) ){
			if ((find_user.getPassword().equals(password)) )
				//password exists
				return false;
		}
		return true;
	}

	void onValidateForm()
	{
		if (! isValid(user.getUserName(), user.getPassword()))
		{
			form.recordError("Invalid user name or password.");
		}
	}
	  
    @CommitAfter
    Object onSuccess(){
    	
    	session.persist(user);
		// Some test vectors, for beginners
		// AES 128 - 1, from FIPS197 pg.33
    	//Transaction transaction = null;
		//transaction = session.beginTransaction();
		TestValues testValues1 = new TestValues();
		testValues1.setShiftRows_TestValue("d42711aee0bf98f1b8b45de51e415230");
		testValues1.setMixColumns_TestValue("d4bf5d30e0b452aeb84111f11e2798e5");
		testValues1.setSubBytes_TestValue("193de3bea0f4e22b9ac68d2ae9f84808");
		testValues1.setKeyExpansion_TestValue("2b7e151628aed2a6abf7158809cf4f3c");  // init key
		testValues1.setAddRoundKey_TestValue("046681e5e0cb199a48f8d37a2806264c");
		testValues1.setMetaTransformation_TestValue("3243f6a8885a308d313198a2e0370734");
		testValues1.setUserId((long)user.getId());
		/*
		testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
		testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
		testValues.setInvSubBytes_TestValue("33");
		testValues.setGMUL_TestValue("229");
		*/
		session.persist(testValues1);
		//transaction.commit();

		// AES 128 - 2, from FIPS197, pg.35-36
		//transaction = session.beginTransaction();
		TestValues testValues2 = new TestValues();
		testValues2.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
		testValues2.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
		testValues2.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
		testValues2.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f");  // init key
		testValues2.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
		testValues2.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff");

		testValues2.setUserId((long)user.getId());
		/*
		testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
		testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
		testValues.setInvSubBytes_TestValue("33");
		testValues.setGMUL_TestValue("229");
		*/
		session.persist(testValues2);
		//transaction.commit();


		// AES 192, from FIPS197, pg.38-39
		//transaction = session.beginTransaction();
		TestValues testValues3 = new TestValues();
		testValues3.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
		testValues3.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
		testValues3.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
		testValues3.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f1011121314151617");  // init key
		testValues3.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
		testValues3.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff");

		testValues3.setUserId((long)user.getId());
		
		/*
		testValues.setInvShiftRows_TestValue("7ad5fda789ef4e272bca100b3d9ff59f");
		testValues.setInvMixColumns_TestValue("e9f74eec023020f61bf2ccf2353c21c7");
		testValues.setInvSubBytes_TestValue("33");
		testValues.setGMUL_TestValue("229");
		*/
		session.persist(testValues3);
		//transaction.commit();
		
		// AES 256, from FIPS197, pg.38-39
		//transaction = session.beginTransaction();
		TestValues testValues4 = new TestValues();
		testValues4.setShiftRows_TestValue("63CAB7040953D051CD60E0E7BA70E18C");
		testValues4.setMixColumns_TestValue("6353E08C0960E104CD70B751BACAD0E7");
		testValues4.setSubBytes_TestValue("00102030405060708090A0B0C0D0E0F0");
		testValues4.setKeyExpansion_TestValue("000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f"); // init key
		testValues4.setAddRoundKey_TestValue("5F72641557F5BC92F7BE3B291DB9F91A");
		testValues4.setMetaTransformation_TestValue("00112233445566778899aabbccddeeff"); // plain text
		testValues4.setUserId((long)user.getId());
		session.persist(testValues4);
    	return index;
    }
}
