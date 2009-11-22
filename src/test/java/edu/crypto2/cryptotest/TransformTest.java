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
	
	/***********************************************************
	 *  SubBytes Test<p>
	 * 
	 * Test using predefined vector "193de3bea0f4e22b9ac68d2ae9f84808"
	 */
	@Test
	@Parameters(value = "SubBytesString")
	public void testAESSubBytes(
			@Optional("193de3bea0f4e22b9ac68d2ae9f84808") String s_sub_bytes
			) {
		
		SubBytes sub_bytes = new SubBytes();
		
		Assert.assertNotNull(sub_bytes);
		String s = s_sub_bytes;
		Assert.assertEquals(s.length(), 32);
		
		sub_bytes.inStr = s;
		sub_bytes.InitializeState();
		

		sub_bytes.TransformState();
	}

	/***********************************************************
	 *  InvSubBytes Test<p>
	 * 
	 * Test using predefined vector "E254940441570B6AD0B40F92560E1500"
	 */
	@Test
	@Parameters(value = "InvSubBytesString")
	public void testAESInvSubBytes(
			@Optional("E254940441570B6AD0B40F92560E1500") String s_inv_sub_bytes
			) {
		
		InvSubBytes inv_sub_bytes = new InvSubBytes();
		
		Assert.assertNotNull(inv_sub_bytes);
		String s = s_inv_sub_bytes;
		Assert.assertEquals(s.length(), 32);
		
		inv_sub_bytes.inStr = s;
		inv_sub_bytes.InitializeState();
		

		inv_sub_bytes.TransformState();

	}



	
	/***********************************************************
	 * MixColumns Test<br>
	 * Test using predefined vector "d4bf5d30e0b452aeb84111f11e2798e5"
	 */
	@Test
	@Parameters(value = "MixColumnsString")
	public void testAESMixColumns(
			@Optional("d4bf5d30e0b452aeb84111f11e2798e5") String s_mix_columns
			) {
		MixColumns mix_columns = new MixColumns();
		Assert.assertNotNull(mix_columns);
		String s = s_mix_columns;
		Assert.assertEquals(s.length(), 32);
		
		mix_columns.inStr = s;
		mix_columns.InitializeState();
		

		mix_columns.TransformState();


	}
	
	/***********************************************************
	 * InvMixColumns Test<br>
	 * Test using predefined vector "978A81C3E12042794817D235EE8B2F3C"
	 */
	@Test
	@Parameters(value = "InvMixColumnsString")
	public void testAESInvMixColumns(
			@Optional("978A81C3E12042794817D235EE8B2F3C") String s_inv_mix_columns
			) {
		InvMixColumns inv_mix_columns = new InvMixColumns();
		Assert.assertNotNull(inv_mix_columns);
		String s = s_inv_mix_columns;
		Assert.assertEquals(s.length(), 32);
		
		inv_mix_columns.inStr = s;
		inv_mix_columns.InitializeState();
		

		inv_mix_columns.TransformState();



	}

	
	
	/***********************************************************
	 *  ShiftRows test<br>
	 *  
	 * Test using predefined vector "d42711aee0bf98f1b8b45de51e415230"
	 */

	@Test
	@Parameters(value = "ShiftRowsString")
	public void testAESShiftRows(
			@Optional("d42711aee0bf98f1b8b45de51e415230") String s_shift_rows
			) {
		ShiftRows shift_rows = new ShiftRows();
		Assert.assertNotNull(shift_rows);
		String s = s_shift_rows;
		Assert.assertEquals(s.length(), 32);
		
		shift_rows.inStr = s;
		shift_rows.InitializeState();
		

		shift_rows.TransformState();

	}

	
	
	/***********************************************************
	 *  InvShiftRows test<br>
	 *  
	 * Test using predefined vector "E2570F0041B41504D00E946A56540B92"
	 */

	@Test
	@Parameters(value = "InvShiftRowsString")
	public void testAESInvShiftRows(
			@Optional("E2570F0041B41504D00E946A56540B92") String s_inv_shift_rows
			) {
		InvShiftRows inv_shift_rows = new InvShiftRows();
		Assert.assertNotNull(inv_shift_rows);
		String s = s_inv_shift_rows;
		Assert.assertEquals(s.length(), 32);
		
		inv_shift_rows.inStr = s;
		inv_shift_rows.InitializeState();
		

		inv_shift_rows.TransformState();


	}

	
	
	/***********************************************************
	 *  KeyExpansion<br>
	 *  Test using predefined vector "2b7e151628aed2a6abf7158809cf4f3c"
	 */

	@Test
	@Parameters(value = "InitialKey128")
	public void testAESKeyExpansion(
			@Optional("2b7e151628aed2a6abf7158809cf4f3c") String s_key_expansion
			) {
		KeyExpansion key_expansion = new KeyExpansion();
		Assert.assertNotNull(key_expansion);
		String s = s_key_expansion;
		Assert.assertEquals(s.length(), 32);
		
		
		int [] skey = new int[32];
		String hex = "";
		for (int i = 0; i <= 16; i++ ){
			if (2*i+1 > s.length())
				break;
			hex = "" + s.charAt(2 * i) + s.charAt(2 * i + 1);
			skey[i] = Integer.parseInt(hex, 16);
		};
		
		key_expansion.initialise(128, skey);
		key_expansion.KeyExpansion128();

	}
	

	
	/***********************************************************
	 *  AddRoundKey<br>
	 *  Test using predefined vector "2b7e151628aed2a6abf7158809cf4f3c"
	 */

	@Test
	@Parameters({"CipherInitialKey128", "InputVector128"})
	public void testAESAddRoundKey(
			@Optional("2b7e151628aed2a6abf7158809cf4f3c") String s_init_key,
			@Optional("046681e5e0cb199a48f8d37a2806264c") String s_input_vector
			) {
		AddRoundKey add_round_key = new AddRoundKey();
		Assert.assertNotNull(add_round_key);
		//String s = s_key_expansion;
		Assert.assertEquals(s_init_key.length(), 32);
		

		
		
		add_round_key.key_len = 128;
		add_round_key.init_key = s_init_key;
		add_round_key.InputVector = s_input_vector;
		
		add_round_key.InitializeState();
		
		add_round_key.key_index = 16;
		add_round_key.TransformState();
		


	}

	
	
	
	
}
