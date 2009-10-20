/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;

//import bsh.EvalError;
import org.apache.log4j.Logger;

import bsh.*;
import edu.crypto2.data.*;
import edu.crypto2.pages.MetaTransformationPG;
import edu.crypto2.transformations.KeyExpansion;

/***********************************************************************
 * 
 */
public class MetaTransformation implements Transformation{
	public int Nb;
	

	
	public String GetTransformationName() {
		return "----- MetaTransformation -----";
	}
	public MetaTransformation() {
		// Nb je uvijek 4 (po FIPS-197 dokumentu)
		this.Nb = 4;
	}
	/* initialize_State - samo za edukaciju i testiranje
	 * initialize_State - just for education and testing
	 */
	public void initialize_State(String testVector, int key_len, String init_key)
	{
		String hex = "";
		int ulaz[] = new int[16];
		int skey[] = new int[32];
		int init_key_len = 0;
		final Logger logger = Logger.getLogger(MetaTransformation.class);
		logger.debug("-----Meta init. state--");
		logger.debug("-----Meta---------");
		logger.debug("-----Meta---------");
		logger.debug("-----TestVector---" + testVector);
		

		KeyExpansion keyExpansion = new KeyExpansion();

		for (int i = 0; i <= 15; i++) {
			hex = "" + testVector.charAt(2 * i) + testVector.charAt(2 * i + 1);
			ulaz[i] = Integer.parseInt(hex, 16);
		}
		logger.debug("-----Ulaz---" + ulaz);
		
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				Data.State[i][j] = ulaz[4 * i + j];
			}
		}
		logger.debug("-----State---" + Data.State);
		
		if (key_len == 128) init_key_len = 16;
		if (key_len == 192) init_key_len = 24;  
		if (key_len == 256) init_key_len = 32;
		
		logger.debug("-----key_len---" + key_len);
		
		for (int i = 0; i <= init_key_len; i++ ){
			if (2*i+1 > init_key.length())
				break;
			hex = "" + init_key.charAt(2 * i) + init_key.charAt(2 * i + 1);
			logger.debug("-----hex---" + hex);
			skey[i] = Integer.parseInt(hex, 16);
		}
		logger.debug("-----skey---" + skey);

	    for (int i = 0; i <= 239; i++)
		     Data.key[i] = 0x0;
	    logger.debug("-----Data.key---" + Data.key);
	    logger.debug("-----key_len---" + key_len);
	    
	    if (key_len == 128) {
			for (int i = 0; i <= 15; i++)
				Data.key[i] = skey[i];
			keyExpansion.KeyExpansion128();
			logger.debug("-----Data.key---" + Data.key);
		}
		else
		if (key_len == 192) {
			for (int i = 0; i <= 23; i++)
				Data.key[i] = skey[i];
			keyExpansion.KeyExpansion192();
			logger.debug("-----Data.key---" + Data.key);
		}
		else
		if (key_len == 256) {
			for (int i = 0; i <= 31; i++)
				Data.key[i] = skey[i];
			keyExpansion.KeyExpansion256();
			logger.debug("-----Data.key---" + Data.key);
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

		final Logger logger = Logger.getLogger(MetaTransformation.class);
		logger.debug("----MetaTRState---");
		logger.debug("-----Meta---------");
		
    	try {
			if (MetaTr == "TEST"){
    		/* Ovdje cemo od korisnika sakriti 'dosadni' dio posla
    		 * i dati mu priliku da se koncentrise na srz algoritma, 
    		 * odnosno metatransformacije */
			logger.debug("-----11111111---------");
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
			
			/*
			"for (int i = 0; i <= 15; i++) {\n" +
			"	hex = \"\" + s.charAt(2 * i) + s.charAt(2 * i + 1);\n" +
			"	ulaz[i] = Integer.parseInt(hex, 16);\n" +
			"}\n" +

			"for (int i = 0; i <= 3; i++) {\n" +
			"	for (int j = 0; j <= 3; j++) {\n" +
			"		Data.State[i][j] = ulaz[4 * i + j];\n" +
			"	}\n" +
			"}\n" +
			
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
			
		    "if (key_len == 128) {\n" +
			"	for (int i = 0; i <= 15; i++)\n" +
			"		Data.key[i] = skey[i];\n" +
			"	keyExpansion.KeyExpansion128();\n" +
			"}\n" +
			"else\n" +
			"if (key_len == 192) {\n" +
			"	for (int i = 0; i <= 191; i++)\n" +
			"		Data.key[i] = skey[i];\n" +
			"	keyExpansion.KeyExpansion192();\n" +
			"}\n" +
			"else\n" +
			"if (key_len == 256) {\n" +
			"	for (int i = 0; i <= 255; i++)\n" +
			"		Data.key[i] = skey[i];\n" +
			"	keyExpansion.KeyExpansion256();\n" +
			"}\n" +

			
			
			"switch (duzina_kljuca)\n" +
			"{\n" +
			"	case 128 :\n" +
			"	{\n" +
			"			  Nb = 4;  //br.kolona\n" +
			"			  Nr = 10; //br.rundi\n" +
			"			  break;\n" +
			"	}\n" +
			"	case 192 :\n" +
			"	{\n" +
			"			  Nb = 4;\n" +
			"			  Nr = 12;\n" +
			"			  break;\n" +
			"	}\n" +
			"	case 256 :\n" +
			"	{\n" +
			"			  Nb = 4;\n" +
			"			  Nr = 14;\n" +
			"			  break;\n" +
			"	}\n" +
			"	default  :\n" +
			"	{\n" +
			"			  System.out.println(\"Key length must be 128, 192 or 256\");\n" +
			"			  return;\n" +
			"	}\n" +
			"}\n" +
			
			"for (i = 0; i <= 3; i++) {\n" +
			"	for (j = 0; j <= 3; j++) {\n" +
			"		Data.State[i][j] = ulaz[4*i + j];\n" +
			"	}\n" +
			"}\n" +
			
			"System.out.println(\"-----------\");\n" +
			
			*/
			
			
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
				logger.debug("-----222222222---------");

	    		/* Ovdje cemo od korisnika sakriti 'dosadni' dio posla
	    		 * i dati mu priliku da se koncentrise na srz algoritma, 
	    		 * odnosno metatransformacije */
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
				
				/*
				"for (int i = 0; i <= 15; i++) {\n" +
				"	hex = \"\" + s.charAt(2 * i) + s.charAt(2 * i + 1);\n" +
				"	ulaz[i] = Integer.parseInt(hex, 16);\n" +
				"}\n" +

				"for (int i = 0; i <= 3; i++) {\n" +
				"	for (int j = 0; j <= 3; j++) {\n" +
				"		Data.State[i][j] = ulaz[4 * i + j];\n" +
				"	}\n" +
				"}\n" +
				*/
				
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
				/*
			    "if (key_len == 128) {\n" +
				"	for (int i = 0; i <= 15; i++)\n" +
				"		Data.key[i] = skey[i];\n" +
				"	keyExpansion.KeyExpansion128();\n" +
				"}\n" +
				"else\n" +
				"if (key_len == 192) {\n" +
				"	for (int i = 0; i <= 191; i++)\n" +
				"		Data.key[i] = skey[i];\n" +
				"	keyExpansion.KeyExpansion192();\n" +
				"}\n" +
				"else\n" +
				"if (key_len == 256) {\n" +
				"	for (int i = 0; i <= 255; i++)\n" +
				"		Data.key[i] = skey[i];\n" +
				"	keyExpansion.KeyExpansion256();\n" +
				"}\n" +

				
				/*
				"switch (duzina_kljuca)\n" +
				"{\n" +
				"	case 128 :\n" +
				"	{\n" +
				"			  Nb = 4;  //br.kolona\n" +
				"			  Nr = 10; //br.rundi\n" +
				"			  break;\n" +
				"	}\n" +
				"	case 192 :\n" +
				"	{\n" +
				"			  Nb = 4;\n" +
				"			  Nr = 12;\n" +
				"			  break;\n" +
				"	}\n" +
				"	case 256 :\n" +
				"	{\n" +
				"			  Nb = 4;\n" +
				"			  Nr = 14;\n" +
				"			  break;\n" +
				"	}\n" +
				"	default  :\n" +
				"	{\n" +
				"			  System.out.println(\"Key length must be 128, 192 or 256\");\n" +
				"			  return;\n" +
				"	}\n" +
				"}\n" +
				
				"for (i = 0; i <= 3; i++) {\n" +
				"	for (j = 0; j <= 3; j++) {\n" +
				"		Data.State[i][j] = ulaz[4*i + j];\n" +
				"	}\n" +
				"}\n" +
				
				"System.out.println(\"-----------\");\n" +
				
				*/
				
				/*
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
				*/
				
				MetaTr +
				"for (i = 0; i <= 3; i++) {\n" +
				"	for (j = 0; j <= 3; j++) {\n" +
				"		Data.Output[i][j] = Data.State[i][j];\n" +
				"	}\n" +
				"}\n" +
				"Data.byte_counter += 16;\n";
				}


			
			
			

			logger.debug("-----Meta before eval ------");
			logger.debug(str_to_interpret);
			logger.debug("-----Meta before eval ------");
			i.eval(str_to_interpret);
			logger.debug("-----Meta after eval ------");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	

}
