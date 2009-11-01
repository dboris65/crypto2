/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.entities;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/***********************************************************************
 * 
 */
@Entity
@Table(name="crypto2")
public class TestValues {
	

    
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	private Long id;

	@Validate("required")
	private String SubBytes_TestValue;


	@Validate("required")
	private String MixColumns_TestValue;

	private String ShiftRows_TestValue;

	private String KeyExpansion_TestValue;
	private String AddRoundKey_TestValue;
	private String MetaTransformation_TestValue;
	@NonVisual
	private Long UserId;



	/*
	@Validate("required")
	private String InvSubBytes_TestValue;
	private String InvMixColumns_TestValue;
	private String InvShiftRows_TestValue;
	private String GMUL_TestValue;
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Validate("required")
	public String getSubBytes_TestValue() {
		return SubBytes_TestValue;
	}

	@Validate("required")
	public String getMixColumns_TestValue() {
		return MixColumns_TestValue;
	}


	@Validate("required")
	public String getShiftRows_TestValue() {
		return ShiftRows_TestValue;
	}


	public String getKeyExpansion_TestValue() {
		return KeyExpansion_TestValue;
	}

	public String getAddRoundKey_TestValue() {
		return AddRoundKey_TestValue;
	}
	
	public String getMetaTransformation_TestValue() {
		return MetaTransformation_TestValue;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return UserId;
	}
	
/*	
	@Validate("required")
	public String getInvSubBytes_TestValue() {
		return InvSubBytes_TestValue;
	}
	@Validate("required")
	public String getInvShiftRows_TestValue() {
		return InvShiftRows_TestValue;
	}

	@Validate("required")
	public String getInvMixColumns_TestValue() {
		return InvMixColumns_TestValue;
	}
	
	// ovo izbacujem
	public String getGMUL_TestValue() {
		return GMUL_TestValue;
	}

*/
	/****************************************************************************
	 * setters
	 */
	
	public void setSubBytes_TestValue(String subBytesTestValue) {
		SubBytes_TestValue = subBytesTestValue;
	}


	public void setMixColumns_TestValue(String MixColumns_TestValue) {
		this.MixColumns_TestValue = MixColumns_TestValue;
	}

	public void setShiftRows_TestValue(String ShiftRows_TestValue) {
		this.ShiftRows_TestValue = ShiftRows_TestValue;
	}


	public void setKeyExpansion_TestValue(String keyExpansionTestValue) {
		KeyExpansion_TestValue = keyExpansionTestValue;
	}

	public void setAddRoundKey_TestValue(String addRoundKeyTestValue) {
		AddRoundKey_TestValue = addRoundKeyTestValue;
	}
	 
	public void setMetaTransformation_TestValue(String metaTransformation_TestValue) {
		MetaTransformation_TestValue = metaTransformation_TestValue;
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		UserId = userId;
	} 
	
/*	
	public void setInvSubBytes_TestValue(String invSubBytesTestValue) {
		InvSubBytes_TestValue = invSubBytesTestValue;
	}
		public void setInvShiftRows_TestValue(String InvShiftRows_TestValue) {
		this.InvShiftRows_TestValue = InvShiftRows_TestValue;
	}

	public void setInvMixColumns_TestValue(String InvMixColumns_TestValue) {
		this.InvMixColumns_TestValue = InvMixColumns_TestValue;
	}
	
	
	//izbaciti
	public void setGMUL_TestValue(String gMULTestValue) {
		GMUL_TestValue = gMULTestValue;
	}

*/
}
