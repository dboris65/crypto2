/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;


import bsh.Interpreter;
import edu.crypto2.data.*;
import edu.crypto2.transformations.KeyExpansion;

/***********************************************************************
 * 
 */
/**
 * Class MetaTransformation<p>
 * Task:<br>
 * To encrypt Test Vector according to selected algorithm.<br> 
 * Uses bash interpreter to interpret Java code that represent transformation 
 */
public class MetaTransformation implements Transformation{
	/**
	 * Nb is always 4 (by FIPS-197), but authors of AES left
	 * the space to change something in the future, 
	 * so we wiil do the same
	 */
	public int Nb;
	

	/**
	 * Dummy constructor.<br>
	 * Task:<br>
	 * Just to set Nb to 4<br>
	 */
	public MetaTransformation() {
		// Nb - always 4 (by FIPS-197)
		this.Nb = 4;
	}
	
	/**
	 *  initialize_State<p>
	 *  Task:<br>
	 *  Fills State array with initial values taken from testVector and expand key according to key_len and init_key variables.<br>
	 *  Input variables must satisfy following conditions:<br>
	 *  testVector - 32 alphanumercis with hex values (16 bytes)<br>
	 *  key_len=128 => length(init_key)=16 bytes<br>
	 *  key_len=192 => length(init_key)=24 bytes<br>
	 *  key_len=256 => length(init_key)=32 bytes<br>
	 *  Resulting key will be stored in Data.Key variable
	 */
	public void initialize_State(String testVector, int key_len, String init_key)
	{
		String hex = "";
		int ulaz[] = new int[16];
		int skey[] = new int[32];
		int init_key_len = 0;

		KeyExpansion keyExpansion = new KeyExpansion();

		for (int i = 0; i <= 15; i++) {
			hex = "" + testVector.charAt(2 * i) + testVector.charAt(2 * i + 1);
			ulaz[i] = Integer.parseInt(hex, 16);
		}
		
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				Data.State[i][j] = ulaz[4 * i + j];
			}
		}
		
		if (key_len == 128) init_key_len = 16;
		if (key_len == 192) init_key_len = 24;  
		if (key_len == 256) init_key_len = 32;
		
		
		for (int i = 0; i <= init_key_len; i++ ){
			if (2*i+1 > init_key.length())
				break;
			hex = "" + init_key.charAt(2 * i) + init_key.charAt(2 * i + 1);
			skey[i] = Integer.parseInt(hex, 16);
		}

	    for (int i = 0; i <= 239; i++)
		     Data.key[i] = 0x0;
	    
	    if (key_len == 128) {
			for (int i = 0; i <= 15; i++)
				Data.key[i] = skey[i];
			keyExpansion.KeyExpansion128();
		}
		else
		if (key_len == 192) {
			for (int i = 0; i <= 23; i++)
				Data.key[i] = skey[i];
			keyExpansion.KeyExpansion192();
		}
		else
		if (key_len == 256) {
			for (int i = 0; i <= 31; i++)
				Data.key[i] = skey[i];
			keyExpansion.KeyExpansion256();
		}
	}
	
	/* Ovdje radimo pravi posao
	 * Here we do the job
	 * 
	 */
	public void transform_state(String testVector, int key_len, String init_key, String MetaTr) {
		Interpreter i = new Interpreter();
		String str_to_interpret, hex_key;
		str_to_interpret = "";

		
    	try {
			if (MetaTr == "TEST"){
    		/* We will hide some parts of algorithm, and give
    		 * users a chance to concentrate to algorithm core
    	    */
    		str_to_interpret = 
  			"import System.out;\n" +
    		"import edu.crypto2.data.*;\n" +
			"import edu.crypto2.transformations.SubBytes;\n" +
			"import edu.crypto2.transformations.ShiftRows;\n" +
			"import edu.crypto2.transformations.MixColumns;\n" +
			"import edu.crypto2.transformations.KeyExpansion;\n" +
			"import edu.crypto2.transformations.AddRoundKey;\n" +
			"int key_len = 128;\n" +
			"int duzina_kljuca = " +  key_len + ";\n" + 
			"int Nb;\n" +
			"int Nr;\n" +
			"int i, j;\n" +
			"int runda;\n" +
			"int runda2;\n" +
			"String hex = \"\";\n" +
			"int [] ulaz = new int[16];\n" +
			"int [] skey = new int[32];\n" +
			"int init_key_len = 0;\n" +
			

			"SubBytes subBytes = new SubBytes();\n" +
			"ShiftRows shiftRows = new ShiftRows();\n" +
			"MixColumns mixColumns = new MixColumns();\n" +
			
			"AddRoundKey addRoundKey = new AddRoundKey();\n" +
			
			
			"s = \"" +  testVector +  "\";  \n" +
			"init_key = \"" +  init_key +  "\";  \n" +
			
			
			
			"runda2 = 0;\n" +
			"runda = 0;\n" +
			"//*************************************\n" +
			"addRoundKey.transform_state(0);\n" +
			"for (runda = 1; runda <= Nr-1; runda++)\n" +
			"{\n" +
			"   subBytes.transform_state();\n" +
			"	shiftRows.transform_state();\n" +
			"	mixColumns.transform_state();\n" +
			"	addRoundKey.transform_state(4*runda*Nb );\n" +
			"	runda2=runda;\n" +
			"}\n" +
			"\n" +
			"runda2++;\n" +
			"subBytes.transform_state();\n" +
			"// Final round\n" +
			"shiftRows.transform_state();\n" +
			"addRoundKey.transform_state( 4*runda2*Nb );\n" +
			"for (i = 0; i <= 3; i++) {\n" +
			"	for (j = 0; j <= 3; j++) {\n" +
			"		Data.Output[i][j] = Data.State[i][j];\n" +
			"	}\n" +
			"}\n" +
			"Data.byte_counter += 16;\n";
			}
			else
			{

	    		/* We will hide some parts of algorithm, and give
	    		 * users a chance to concentrate to algorithm core
	    	    */
	    		str_to_interpret = 
	  			"import System.out;\n" +
	  			"import org.apache.log4j.Logger;\n" +
	    		"import edu.crypto2.data.*;\n" +
				"import edu.crypto2.transformations.SubBytes;\n" +
				"import edu.crypto2.transformations.ShiftRows;\n" +
				"import edu.crypto2.transformations.MixColumns;\n" +
				"import edu.crypto2.transformations.KeyExpansion;\n" +
				"import edu.crypto2.transformations.AddRoundKey;\n" +
				"final Logger logger = Logger.getLogger(\"global\");\n" +
				"logger.debug(\"------    duzina_kljuca  -----------\");\n" +
				"int key_len = " +  key_len + ";\n" +
				"int duzina_kljuca = " +  key_len + ";\n" + 
				"logger.debug(\"------    duzina_kljuca  -----------\" + duzina_kljuca);\n" +
				"int Nb;\n" +
				"int Nr;\n" +
				"int i, j;\n" +
				"int runda;\n" +
				"int runda2;\n" +
				"String hex = \"\";\n" +
				"int [] ulaz = new int[16];\n" +
				"int [] skey = new int[32];\n" +
				"int init_key_len = 0;\n" +
				

				"SubBytes subBytes = new SubBytes();\n" +
				"ShiftRows shiftRows = new ShiftRows();\n" +
				"MixColumns mixColumns = new MixColumns();\n" +
				
				"AddRoundKey addRoundKey = new AddRoundKey();\n" +
				
				
				"s = \"" +  testVector +  "\";  \n" +
				"init_key = \"" +  init_key +  "\";  \n" +
				
				
				"if (key_len == 128) init_key_len = 16;\n" +
				"if (key_len == 192) init_key_len = 24;\n" +  
				"if (key_len == 256) init_key_len = 32;\n" +
				"hex = \"\";\n" +
				"for (int i = 0; i <= init_key_len; i++ ){\n" +
				"	if (2*i+1 > init_key.length())\n" +
				"		break;\n" +
				"	hex = \"\" + init_key.charAt(2 * i) + init_key.charAt(2 * i + 1);\n" +
				"	skey[i] = Integer.parseInt(hex, 16);\n" +
				"}\n" +

			    "for (int i = 0; i <= 239; i++)\n" +
				"     Data.key[i] = 0x0;\n" +
				
				"keyExpansion = new KeyExpansion();\n" +
				"keyExpansion.initialise(key_len, skey);\n" +
				
				
				
				MetaTr +
				"for (i = 0; i <= 3; i++) {\n" +
				"	for (j = 0; j <= 3; j++) {\n" +
				"		Data.Output[i][j] = Data.State[i][j];\n" +
				"	}\n" +
				"}\n" +
				"Data.byte_counter += 16;\n";
				}


			i.eval(str_to_interpret);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	

}
