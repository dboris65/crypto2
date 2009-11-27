/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.cryptotest;


import org.testng.Assert;
import org.testng.annotations.*;


import edu.crypto2.data.Data;
import edu.crypto2.transformations.AddRoundKey;
import edu.crypto2.transformations.InvMixColumns;
import edu.crypto2.transformations.InvShiftRows;
import edu.crypto2.transformations.InvSubBytes;
import edu.crypto2.transformations.KeyExpansion;
import edu.crypto2.transformations.MixColumns;
import edu.crypto2.transformations.ShiftRows;
import edu.crypto2.transformations.SubBytes;


/***********************************************************************
 * 
 */
public class TransformTest {
	
	private String getState(){
        String s = "";
        for (int j = 0; j <= 3; j++) {
                for (int i = 0; i <= 3; i++) {
                        s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
                                                        16 /* radix */).substring(1); // Integer.toHexString(AES_T.State[i][j])
                }
                s = s.trim();
        }
        return s;
	}
	
	
	/***********************************************************
	 *  SubBytes Test<p>
	 * 
	 * Test using predefined vector "193de3bea0f4e22b9ac68d2ae9f84808"
	 */
	@Test
	@Parameters(value = {"SubBytesString", "SubBytesResultString"})
	public void testAESSubBytes(
			@Optional("193de3bea0f4e22b9ac68d2ae9f84808") String sSubBytes,
			@Optional("d4e0b81e27bfb44111985d52aef1e530") String sResultVector
			) {
		
		SubBytes subBytes = new SubBytes();
		
		Assert.assertNotNull(subBytes);
		String s = sSubBytes;
		Assert.assertEquals(s.length(), 32);
		
		subBytes.inStr = s;
		subBytes.InitializeState();

		subBytes.TransformState();
		String result = getState();

		Assert.assertEquals(result.toUpperCase(), sResultVector.toUpperCase());
	}

	/***********************************************************
	 *  InvSubBytes Test<p>
	 * 
	 * Test using predefined vector "E254940441570B6AD0B40F92560E1500"
	 */
	@Test
	@Parameters(value = {"InvSubBytesString", "InvSubBytesResultString"})
	public void testAESInvSubBytes(
			@Optional("E254940441570B6AD0B40F92560E1500") String sInvSubBytes,
			@Optional("3bf860b9fddac6d7e79efb2f30587452") String sResultVector
			) {
		
		InvSubBytes invSubBytes = new InvSubBytes();
		
		Assert.assertNotNull(invSubBytes);
		String s = sInvSubBytes;
		Assert.assertEquals(s.length(), 32);
		
		invSubBytes.inStr = s;
		invSubBytes.InitializeState();
		invSubBytes.TransformState();
		
		String result = getState();

		Assert.assertEquals(result.toUpperCase(), sResultVector.toUpperCase());
	}



	
	/***********************************************************
	 * MixColumns Test<br>
	 * Test using predefined vector "d4bf5d30e0b452aeb84111f11e2798e5"
	 */
	@Test
	@Parameters(value = {"MixColumnsString", "MixColumnsResultString"})
	public void testAESMixColumns(
			@Optional("d4bf5d30e0b452aeb84111f11e2798e5") String sMixColumns,
			@Optional("04e0482866cbf8068119d326e59a7a4c") String sResultVector
			) {
		MixColumns mixColumns = new MixColumns();
		Assert.assertNotNull(mixColumns);
		String s = sMixColumns;
		Assert.assertEquals(s.length(), 32);
		
		mixColumns.inStr = s;
		mixColumns.InitializeState();

		mixColumns.TransformState();
		String result = getState();

		Assert.assertEquals(result.toUpperCase(), sResultVector.toUpperCase());
	}
	
	/***********************************************************
	 * InvMixColumns Test<br>
	 * Test using predefined vector "978A81C3E12042794817D235EE8B2F3C"
	 */
	@Test
	@Parameters(value = {"InvMixColumnsString", "InvMixColumnsResultString"})
	public void testAESInvMixColumns(
			@Optional("978A81C3E12042794817D235EE8B2F3C") String sInvMixColumns,
			@Optional("64a9a7e632f001d9cce556cbc5464882") String sResultVector
			) {
		InvMixColumns invMixColumns = new InvMixColumns();
		Assert.assertNotNull(invMixColumns);
		String s = sInvMixColumns;
		Assert.assertEquals(s.length(), 32);
		
		invMixColumns.inStr = s;
		invMixColumns.InitializeState();

		invMixColumns.TransformState();
		String result = getState();

		Assert.assertEquals(result.toUpperCase(), sResultVector.toUpperCase());
	}

	
	
	/***********************************************************
	 *  ShiftRows test<br>
	 *  
	 * Test using predefined vector "d42711aee0bf98f1b8b45de51e415230"
	 */

	@Test
	@Parameters(value = {"ShiftRowsString", "ShiftRowsResultString"})
	public void testAESShiftRows(
			@Optional("d42711aee0bf98f1b8b45de51e415230") String sShiftRows,
			@Optional("d4e0b81ebfb441275d52119830aef1e5") String sResultVector
			) {
		ShiftRows shiftRows = new ShiftRows();
		Assert.assertNotNull(shiftRows);
		String s = sShiftRows;
		Assert.assertEquals(s.length(), 32);
		
		shiftRows.inStr = s;
		shiftRows.InitializeState();

		shiftRows.TransformState();
		String result = getState();
		Assert.assertEquals(result.toUpperCase(), sResultVector.toUpperCase());
	}

	
	
	/***********************************************************
	 *  InvShiftRows test<br>
	 *  
	 * Test using predefined vector "E2570F0041B41504D00E946A56540B92"
	 */

	@Test
	@Parameters(value = {"InvShiftRowsString", "InvShiftRowsResultString"})
	public void testAESInvShiftRows(
			@Optional("E2570F0041B41504D00E946A56540B92") String sInvShiftRows,
			@Optional("e241d0565457b40e940b0f15046a9200") String sResultVector
			) {
		InvShiftRows invShiftRows = new InvShiftRows();
		Assert.assertNotNull(invShiftRows);
		String s = sInvShiftRows;
		Assert.assertEquals(s.length(), 32);
		
		invShiftRows.inStr = s;
		invShiftRows.InitializeState();

		invShiftRows.TransformState();
		
		String result = getState();
		Assert.assertEquals(result.toUpperCase(), sResultVector.toUpperCase());
	}


	private String getKey(){
		String s = "";
		for (int i = 0; i<176; i++)
			s = s + Integer.toString((Data.key[i] & 0xff) + 0x100,
                    16 /* radix */).substring(1);
		
		return s;
	}
	
	/***********************************************************
	 *  KeyExpansion<br>
	 *  Test using predefined vector "2b7e151628aed2a6abf7158809cf4f3c"
	 */
	@Test
	@Parameters(value = {"InitialKey128", "ResultingExpandedKey128"})
	public void testAESKeyExpansion(
			@Optional("2b7e151628aed2a6abf7158809cf4f3c") String sKeyExpansion,
			@Optional("2B7E151628AED2A6ABF7158809CF4F3CA0FAFE1788542CB123A339392A6C7605F2C295F27A96B9435935807A7359F67F3D80477D4716FE3E1E237E446D7A883BEF44A541A8525B7FB671253BDB0BAD00D4D1C6F87C839D87CAF2B8BC11F915BC6D88A37A110B3EFDDBF98641CA0093FD4E54F70E5F5FC9F384A64FB24EA6DC4FEAD27321B58DBAD2312BF5607F8D292FAC7766F319FADC2128D12941575C006ED014F9A8C9EE2589E13F0CC8B6630CA6") String sResultVector
			) {
		KeyExpansion keyExpansion = new KeyExpansion();
		Assert.assertNotNull(keyExpansion);
		String s = sKeyExpansion;
		Assert.assertEquals(s.length(), 32);
		
		
		int [] skey = new int[32];
		String hex = "";
		for (int i = 0; i <= 16; i++ ){
			if (2*i+1 > s.length())
				break;
			hex = "" + s.charAt(2 * i) + s.charAt(2 * i + 1);
			skey[i] = Integer.parseInt(hex, 16);
		};
		
		keyExpansion.initialise(128, skey);
		keyExpansion.KeyExpansion128();
		String result = getKey();
		
		Assert.assertEquals(result.toUpperCase(), sResultVector.toUpperCase());
	}
	


	
	/***********************************************************
	 *  AddRoundKey<br>
	 *  Test using predefined vector "2b7e151628aed2a6abf7158809cf4f3c"
	 */
	@Test
	@Parameters({"CipherInitialKey128", "InputVector128", "ResultCipherText128"})
	public void testAESAddRoundKey(
			@Optional("2b7e151628aed2a6abf7158809cf4f3c") String sInitKey,
			@Optional("046681e5e0cb199a48f8d37a2806264c") String sInputVector,
			@Optional("a4686b029c9f5b6a7f35ea50f22b4349") String sResultVector
			) {
		AddRoundKey addRoundKey = new AddRoundKey();
		Assert.assertNotNull(addRoundKey);
		
		Assert.assertEquals(sInitKey.length(), 32);
		
		addRoundKey.key_len = 128;
		addRoundKey.init_key = sInitKey;
		addRoundKey.InputVector = sInputVector;
		
		addRoundKey.InitializeState();
		
		addRoundKey.key_index = 16;
		addRoundKey.TransformState();
		
		String result = getState();
		Assert.assertEquals(result.toUpperCase(), sResultVector.toUpperCase());
	}

	
	
	
	
}
