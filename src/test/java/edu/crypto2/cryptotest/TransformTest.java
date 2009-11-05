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
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("====================================================");
		System.out.println("   SubBytes Test");
		System.out.println("====================================================");
		SubBytes sub_bytes = new SubBytes();
		
		Assert.assertNotNull(sub_bytes);
		String s = s_sub_bytes;
		Assert.assertEquals(s.length(), 32);
		
		sub_bytes.initialize_State(s);
		
		System.out.println("-------------------- START SubBytes");
		System.out.println("-------------------- Before SubBytes");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
										16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																			// +
																			// " ";
			}
			s = s.trim();
			System.out.println(s);
			s = "";
		}

		sub_bytes.transform_state();

		System.out.println("-------------------- After SubBytes");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s
						+ Integer.toString((Data.State[i][j] & 0xff) + 0x100,
								16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																		// +
																		// " ";
			}
			s = s.trim();
			System.out.println(s);

			s = "";
		}


		System.out.println("====================================================");
		System.out.println(" @Test - SubBytes");
		System.out.println(" END SubBytes");
		System.out.println("====================================================");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

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
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("====================================================");
		System.out.println("   InvSubBytes Test");
		System.out.println("====================================================");
		InvSubBytes inv_sub_bytes = new InvSubBytes();
		
		Assert.assertNotNull(inv_sub_bytes);
		String s = s_inv_sub_bytes;
		Assert.assertEquals(s.length(), 32);
		
		inv_sub_bytes.initialize_State(s);
		
		System.out.println("-------------------- START InvSubBytes");
		System.out.println("-------------------- Before InvSubBytes");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
										16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																			// +
																			// " ";
			}
			s = s.trim();
			System.out.println(s);
			s = "";
		}

		inv_sub_bytes.transform_state();

		System.out.println("-------------------- After InvSubBytes");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s
						+ Integer.toString((Data.State[i][j] & 0xff) + 0x100,
								16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																		// +
																		// " ";
			}
			s = s.trim();
			System.out.println(s);

			s = "";
		}


		System.out.println("====================================================");
		System.out.println(" @Test - InvSubBytes");
		System.out.println(" END InvSubBytes");
		System.out.println("====================================================");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");

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
		System.out.println(" ");
		System.out.println("====================================================");
		System.out.println("   MixColumns Test");
		System.out.println("====================================================");
		MixColumns mix_columns = new MixColumns();
		Assert.assertNotNull(mix_columns);
		String s = s_mix_columns;
		Assert.assertEquals(s.length(), 32);
		
		mix_columns.initialize_State(s);
		
		System.out.println("-------------------- START mix_columns");
		System.out.println("-------------------- Before mix_columns");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
										16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																			// +
																			// " ";
			}
			s = s.trim();
			System.out.println(s);
			s = "";
		}

		mix_columns.transform_state();

		System.out.println("-------------------- After mix_columns");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s
						+ Integer.toString((Data.State[i][j] & 0xff) + 0x100,
								16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																		// +
																		// " ";
			}
			s = s.trim();
			System.out.println(s);

			s = "";
		}


		System.out.println("====================================================");
		System.out.println(" @Test - mix_columns");
		System.out.println(" END mix_columns");
		System.out.println("====================================================");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");


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
		System.out.println(" ");
		System.out.println("====================================================");
		System.out.println("   InvMixColumns Test");
		System.out.println("====================================================");
		InvMixColumns inv_mix_columns = new InvMixColumns();
		Assert.assertNotNull(inv_mix_columns);
		String s = s_inv_mix_columns;
		Assert.assertEquals(s.length(), 32);
		
		inv_mix_columns.initialize_State(s);
		
		System.out.println("-------------------- START inv_mix_columns");
		System.out.println("-------------------- Before inv_mix_columns");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
										16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																			// +
																			// " ";
			}
			s = s.trim();
			System.out.println(s);
			s = "";
		}

		inv_mix_columns.transform_state();

		System.out.println("-------------------- After inv_mix_columns");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s
						+ Integer.toString((Data.State[i][j] & 0xff) + 0x100,
								16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																		// +
																		// " ";
			}
			s = s.trim();
			System.out.println(s);

			s = "";
		}


		System.out.println("====================================================");
		System.out.println(" @Test - inv_mix_columns");
		System.out.println(" END inv_mix_columns");
		System.out.println("====================================================");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");


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
		System.out.println(" ");
		System.out.println("====================================================");
		System.out.println("   ShiftRows Test");
		System.out.println("====================================================");
		ShiftRows shift_rows = new ShiftRows();
		Assert.assertNotNull(shift_rows);
		String s = s_shift_rows;
		Assert.assertEquals(s.length(), 32);
		
		shift_rows.initialize_State(s);
		
		System.out.println("-------------------- START shift_rows");
		System.out.println("-------------------- Before shift_rows");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
										16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																			// +
																			// " ";
			}
			s = s.trim();
			System.out.println(s);
			s = "";
		}

		shift_rows.transform_state();

		System.out.println("-------------------- After shift_rows");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s
						+ Integer.toString((Data.State[i][j] & 0xff) + 0x100,
								16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																		// +
																		// " ";
			}
			s = s.trim();
			System.out.println(s);

			s = "";
		}


		System.out.println("====================================================");
		System.out.println(" @Test - shift_rows");
		System.out.println(" END shift_rows");
		System.out.println("====================================================");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");


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
		System.out.println(" ");
		System.out.println("====================================================");
		System.out.println("   InvShiftRows Test");
		System.out.println("====================================================");
		InvShiftRows inv_shift_rows = new InvShiftRows();
		Assert.assertNotNull(inv_shift_rows);
		String s = s_inv_shift_rows;
		Assert.assertEquals(s.length(), 32);
		
		inv_shift_rows.initialize_State(s);
		
		System.out.println("-------------------- START inv_shift_rows");
		System.out.println("-------------------- Before inv_shift_rows");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
										16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																			// +
																			// " ";
			}
			s = s.trim();
			System.out.println(s);
			s = "";
		}

		inv_shift_rows.transform_state();

		System.out.println("-------------------- After inv_shift_rows");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s
						+ Integer.toString((Data.State[i][j] & 0xff) + 0x100,
								16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																		// +
																		// " ";
			}
			s = s.trim();
			System.out.println(s);

			s = "";
		}


		System.out.println("====================================================");
		System.out.println(" @Test - inv_shift_rows");
		System.out.println(" END inv_shift_rows");
		System.out.println("====================================================");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");


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
		System.out.println(" ");
		System.out.println("====================================================");
		System.out.println("   KeyExpansion Test");
		System.out.println("====================================================");
		KeyExpansion key_expansion = new KeyExpansion();
		Assert.assertNotNull(key_expansion);
		String s = s_key_expansion;
		Assert.assertEquals(s.length(), 32);
		
		System.out.println("-------------------- InitialKey128");
		System.out.println(s);
		
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

		s = "";
		// since we will test 128 bit KeyExpansion, we need 11*16 = 176 bytes
		for (int i = 0; i < 176; i++) {
				s = s
						+ Integer.toString((Data.key[i] & 0xff) + 0x100,
								16 /* radix */).substring(1); 
		}
			s = s.trim();
			System.out.println("-------------------- ExpandedKey128");
			System.out.println(s);



		System.out.println("====================================================");
		System.out.println(" @Test - KeyExpansion-");
		System.out.println(" END KeyExpansion");
		System.out.println("====================================================");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");


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
		String s = "";
		System.out.println("====================================================");
		System.out.println("   AddRoundKey Test ");
		System.out.println("====================================================");
		AddRoundKey add_round_key = new AddRoundKey();
		Assert.assertNotNull(add_round_key);
		//String s = s_key_expansion;
		Assert.assertEquals(s_init_key.length(), 32);
		
		System.out.println("");
		System.out.println("-------------------- CipherInitialKey128");
		System.out.println(s_init_key);
		System.out.println("");
		
		System.out.println("");
		System.out.println("-------------------- InputVector128");
		System.out.println(s_input_vector);
		System.out.println("");

		/*
		int [] skey = new int[32];
		String hex = "";
		for (int i = 0; i <= 16; i++ ){
			if (2*i+1 > s.length())
				break;
			hex = "" + s.charAt(2 * i) + s.charAt(2 * i + 1);
			skey[i] = Integer.parseInt(hex, 16);
		};
		*/
		add_round_key.initialize_State(128, s_init_key, s_input_vector);
		
		System.out.println("");
		System.out.println("-------------------- State BEFORE AddRoundkey");
		s = "";
		// da bi ga ispravno prikazao, mijenjamo j i i
		for (int j = 0; j <= 3; j++) {
			for (int i = 0; i <= 3; i++) {
				s = s
						+ Integer.toString((Data.State[i][j] & 0xff) + 0x100,
								16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																		// +
																		// " ";
			}
			s = s.trim();
			System.out.println(s);

			s = "";
		}
		System.out.println("");
		
		add_round_key.transform_state(16);
		
		s = "";
		// since we will test 128 bit KeyExpansion, we need 11*16 = 176 bytes
		for (int i = 0; i < 176; i++) {
				s = s
						+ Integer.toString((Data.key[i] & 0xff) + 0x100,
								16 /* radix */).substring(1); 
		}
			s = s.trim();
			System.out.println("-------------------- ExpandedKey128");
			System.out.println(s);
			System.out.println("");

			
			
			System.out.println("-------------------- State After AddRoundkey");
			s = "";
			// da bi ga ispravno prikazao, mijenjamo j i i
			for (int j = 0; j <= 3; j++) {
				for (int i = 0; i <= 3; i++) {
					s = s
							+ Integer.toString((Data.State[i][j] & 0xff) + 0x100,
									16 /* radix */).substring(1) + " "; // Integer.toHexString(AES_T.State[i][j])
																			// +
																			// " ";
				}
				s = s.trim();
				System.out.println(s);

				s = "";
			}

			System.out.println("");


		System.out.println("====================================================");
		System.out.println(" @Test - AddRoundKey");
		System.out.println(" END AddRoundKey");
		System.out.println("====================================================");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");


	}

	
	
	
	
}
