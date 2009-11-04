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

/**
 * class TestValues<br>
 * Holds test vestors for various transformations.
 */
@Entity
@Table(name="crypto2")
public class TestValues {
	

    
    
	/**
	 * Long id<br>
	 * Row identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	private Long id;

	/**
	 * String SubBytes_TestValue<br>
	 * Holds SubBytes test vector.
	 */
	@Validate("required")
	private String SubBytes_TestValue;


	/**
	 * String MixColumns_TestValue<br>
	 * Holds MixColumns test vector.
	 */
	@Validate("required")
	private String MixColumns_TestValue;
	
	/**
	 * String ShiftRows_TestValue<br>
	 * Holds ShiftRows test vector.
	 */
	@Validate("required")
	private String ShiftRows_TestValue;

	/**
	 * String KeyExpansion_TestValue<br>
	 * Holds KeyExpansion test vector.
	 */
	@Validate("required")
	private String KeyExpansion_TestValue;

	/**
	 * String AddRoundKey_TestValue<br>
	 * Holds AddRoundKey test vector.
	 */
	@Validate("required")
	private String AddRoundKey_TestValue;

	/**
	 * String MetaTransformation_TestValue<br>
	 * Holds MetaTransformation test vector.
	 */
	@Validate("required")
	private String MetaTransformation_TestValue;

	/**
	 * long UserID<br>
	 * Used to show rows that belong to logged User.
	 */
	@NonVisual
	private Long UserId;
	
	/**
	 * String InvSubBytes_TestValue<br>
	 * Holds InvSubBytes test vector.
	 */
	@Validate("required")
	private String InvSubBytes_TestValue;
	/**
	 * String InvMixColumns_TestValue<br>
	 * Holds InvMixColumns test vector.
	 */
	@Validate("required")
	private String InvMixColumns_TestValue;

	/**
	 * String InvShiftRows_TestValue<br>
	 * Holds InvShiftRows test vector.
	 */
	@Validate("required")
	private String InvShiftRows_TestValue;
/*	private String GMUL_TestValue;
*/
	
	/**
	 * Id getter.
	 * @return long Id
	*/
	public Long getId() {
		return id;
	}

	/**
	 * Id setter.
	 * @param id
	*/
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * SubBytes getter.
	 * @return String SubBytes_TestValue
	*/
	public String getSubBytes_TestValue() {
		return SubBytes_TestValue;
	}


	/**
	 * MixColumns getter.
	 * @return String MixColumns_TestValue
	*/
	public String getMixColumns_TestValue() {
		return MixColumns_TestValue;
	}


	/**
	 * ShiftRows getter.
	 * @return String ShiftRows_TestValue
	*/
	public String getShiftRows_TestValue() {
		return ShiftRows_TestValue;
	}


	/**
	 * KeyExpansion getter.
	 * @return String KeyExpansion_TestValue
	*/
	public String getKeyExpansion_TestValue() {
		return KeyExpansion_TestValue;
	}

	/**
	 * AddRoundKey getter.
	 * @return String AddRoundKey_TestValue
	*/
	public String getAddRoundKey_TestValue() {
		return AddRoundKey_TestValue;
	}
	
	/**
	 * MetaTransformation getter.
	 * @return String MetaTransformation_TestValue
	*/
	public String getMetaTransformation_TestValue() {
		return MetaTransformation_TestValue;
	}

	/**
	 * userId getter.
	 * @return the userId
	 */
	public Long getUserId() {
		return UserId;
	}
	
	
	/**
	 * InvSubBytes getter.
	 * @return String InvSubBytes_TestValue
	*/
	public String getInvSubBytes_TestValue() {
		return InvSubBytes_TestValue;
	}
	/**
	 * InvShiftRows getter.
	 * @return String InvShiftRows_TestValue
	*/
	public String getInvShiftRows_TestValue() {
		return InvShiftRows_TestValue;
	}

	/**
	 * InvMixColumns getter.
	 * @return String InvMixColumns_TestValue
	*/
	public String getInvMixColumns_TestValue() {
		return InvMixColumns_TestValue;
	}
	
	/****************************************************************************
	 * setters
	 */

	/**
	 * SubBytes setter.
	 * @param String subBytesTestValue
	*/
	public void setSubBytes_TestValue(String subBytesTestValue) {
		SubBytes_TestValue = subBytesTestValue;
	}

	/**
	 * MixColumns setter.
	 * @param String MixColumns_TestValue
	*/
	public void setMixColumns_TestValue(String MixColumns_TestValue) {
		this.MixColumns_TestValue = MixColumns_TestValue;
	}

	/**
	 * ShiftRows setter.
	 * @param String ShiftRows_TestValue
	*/
	public void setShiftRows_TestValue(String ShiftRows_TestValue) {
		this.ShiftRows_TestValue = ShiftRows_TestValue;
	}

	/**
	 * KeyExpansion setter.
	 * @param String KeyExpansionTestValue
	*/
	public void setKeyExpansion_TestValue(String keyExpansionTestValue) {
		KeyExpansion_TestValue = keyExpansionTestValue;
	}

	/**
	 * AddRoundKey setter.
	 * @param String AddRoundKeyTestValue
	*/
	public void setAddRoundKey_TestValue(String addRoundKeyTestValue) {
		AddRoundKey_TestValue = addRoundKeyTestValue;
	}
	 
	/**
	 * MetaTransformation setter.
	 * @param String MetaTransformation_TestValue
	*/
	public void setMetaTransformation_TestValue(String metaTransformation_TestValue) {
		MetaTransformation_TestValue = metaTransformation_TestValue;
	}
	
	/**
	 * userId setter.
	 * @param userId
	 */
	public void setUserId(long userId) {
		UserId = userId;
	} 
	
	
	/**
	 * InvSubBytes setter.
	 * @param String InvSubBytes_TestValue
	*/
	public void setInvSubBytes_TestValue(String InvSubBytes_TestValue) {
		this.InvSubBytes_TestValue = InvSubBytes_TestValue;
	}

	/**
	 * InvShiftRows setter.
	 * @param String InvShiftRows_TestValue
	*/
	public void setInvShiftRows_TestValue(String InvShiftRows_TestValue) {
		this.InvShiftRows_TestValue = InvShiftRows_TestValue;
	}

	/**
	 * InvMixColumns setter.
	 * @param String InvMixColumns_TestValue
	*/
	public void setInvMixColumns_TestValue(String InvMixColumns_TestValue) {
		this.InvMixColumns_TestValue = InvMixColumns_TestValue;
	}
	

}
