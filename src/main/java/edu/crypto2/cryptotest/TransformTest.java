/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.cryptotest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.*;

import edu.crypto2.data.Data;
import edu.crypto2.rest.XmlParser;
import edu.crypto2.transformations.KeyExpansion;
import edu.crypto2.transformations.MixColumns;
import edu.crypto2.transformations.ShiftRows;
import edu.crypto2.transformations.SubBytes;
import edu.crypto2.transformations.TransformationService;

/***********************************************************************
 * 
 */
public class TransformTest {
	private TransformationService TTransformation;
	private SubBytes sub_bytes;
	private MixColumns mix_columns;
/*
	@BeforeClass
	public void oneTimeSetUp() {
		System.out.println("@BeforeClass - oneTimeSetUp");
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		TransformationService TTransformation = (TransformationService) context.getBean("TransformationService");
		Assert.assertNotNull(TTransformation); 

		String s = TTransformation.GetTransformationName();
		System.out.println(s);
		
		if (s == "----- SubBytes -----"){
			sub_bytes = (SubBytes)TTransformation.transformation;
			Assert.assertNotNull(sub_bytes);
			System.out.println("@BeforeClass - (sub_bytes <> null)");
		}
		//if (s == "----- MixColumns -----"){
		//	mix_columns = (MixColumns)TTransformation.transformation;
		//	Assert.assertNotNull(mix_columns);
		//	System.out.println("@BeforeClass - (mix_columns <> null)");
		//}
		
	}
*/
	/***********************************************************
	 * oneTimeTearDown
	 * 
	 */
/*
	@AfterClass
	public void oneTimeTearDown() {
		System.out.println("@AfterClass - oneTimeTearDown");
	}
*/
	
	
	/***********************************************************
	 *  SubBytes
	 * 
	 */
	@Test
	@Parameters(value = "SubBytesString")
	public void testAESSubBytes(
			@Optional("193de3bea0f4e22b9ac68d2ae9f84808") String s_sub_bytes
			) {
		
		System.out.println(" ");
		System.out.println("-- SubBytes Test --");
		SubBytes sub_bytes = new SubBytes();
		
		Assert.assertNotNull(sub_bytes);
		String s = s_sub_bytes;
		Assert.assertEquals(s.length(), 32);
		
		sub_bytes.initialize_State(s);
		
		System.out.println("START SubBytes --------------------");
		System.out.println("Before SubBytes --------------------");
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

		System.out.println("After SubBytes --------------------");
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


		System.out.println("@Test - SubBytes ----------------");
		System.out.println("END SubBytes --------------------");
		System.out.println(" ");

	}
	

	@Test
	@Parameters(value = "SubBytes")
	public void testXmlParser(
			@Optional("SubBytes") String element_name
			) throws Exception 
	{
		
		XmlParser xml_p = new XmlParser(element_name); 
		String ValueBeforeSubBytes = xml_p.getResultString();
		System.out.println(ValueBeforeSubBytes);
		
	}


	
	/***********************************************************
	 *  MixColumns
	 * 
	 */
	/*
	@DataProvider(name="MixColumnsString")
	public Object[][] getMixColumnsString(){ 
		return new Object[][]{
				   {String.class, new String[] {"d4bf5d30e0b452aeb84111f11e2798e5"}},
				   {Integer.class, new String[] {"1", "2"}}
				  };
	}
	@Test(dataProvider="MixColumnsString")
	*/
	@Test
	@Parameters(value = "MixColumnsString")
	public void testAESMixColumns(
			@Optional("d4bf5d30e0b452aeb84111f11e2798e5") String s_mix_columns
			) {
		System.out.println(" ");
		System.out.println("-- MixColumns Test --");
		MixColumns mix_columns = new MixColumns();
		Assert.assertNotNull(mix_columns);
		String s = s_mix_columns;
		Assert.assertEquals(s.length(), 32);
		
		mix_columns.initialize_State(s);
		
		System.out.println("START mix_columns --------------------");
		System.out.println("Before mix_columns --------------------");
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

		System.out.println("After mix_columns --------------------");
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


		System.out.println("@Test - mix_columns ----------------");
		System.out.println("END mix_columns --------------------");
		System.out.println(" ");


	}
	

	/***********************************************************
	 *  ShiftRows
	 * 
	 */

	@Test
	@Parameters(value = "ShiftRowsString")
	public void testAESShiftRows(
			@Optional("d42711aee0bf98f1b8b45de51e415230") String s_shift_rows
			) {
		System.out.println(" ");
		System.out.println("-- ShiftRows Test --");
		ShiftRows shift_rows = new ShiftRows();
		Assert.assertNotNull(shift_rows);
		String s = s_shift_rows;
		Assert.assertEquals(s.length(), 32);
		
		shift_rows.initialize_State(s);
		
		System.out.println("START shift_rows --------------------");
		System.out.println("Before shift_rows --------------------");
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

		System.out.println("After shift_rows --------------------");
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


		System.out.println("@Test - shift_rows ----------------");
		System.out.println("END shift_rows --------------------");
		System.out.println(" ");


	}

	/***********************************************************
	 *  KeyExpansion
	 * 
	 */

	@Test
	@Parameters(value = "InitialKey128")
	public void testAESKeyExpansion(
			@Optional("2b7e151628aed2a6abf7158809cf4f3c") String s_key_expansion
			) {
		System.out.println(" ");
		System.out.println("-- KeyExpansion Test --");
		KeyExpansion key_expansion = new KeyExpansion();
		Assert.assertNotNull(key_expansion);
		String s = s_key_expansion;
		Assert.assertEquals(s.length(), 32);
		
		System.out.println("InitialKey128 --------------------");
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
			System.out.println("ExpandedKey128 --------------------");
			System.out.println(s);



		System.out.println("@Test - KeyExpansion ----------------");
		System.out.println("END KeyExpansion --------------------");
		System.out.println(" ");


	}
	
	

}
