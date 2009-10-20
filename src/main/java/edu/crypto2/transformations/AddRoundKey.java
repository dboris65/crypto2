/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;

import java.util.List;

import edu.crypto2.data.*;
import edu.crypto2.services.TestValuesDao;

import org.apache.log4j.Logger;

/***********************************************************************
 * 
 */
public class AddRoundKey implements Transformation{
	//@SessionState(create = false)
	//public Data Data = new Data();
	public int Nb;
	public KeyExpansion keyExpansion;

	public String GetTransformationName() {
		return "----- AddRoundKey -----";
	}

	public AddRoundKey(){              //int key_len, String init_key) {
		// Nb je uvijek 4 (po FIPS-197 dokumentu)
		this.Nb = 4;
		
		
/*		//int key_len = 128;
		int skljuc[] = new int[32];
		int	init_key_len = 16;
		int i;
		//String s = "2b7e151628aed2a6abf7158809cf4f3c";
		String hex;
		
		if (key_len == 128) init_key_len = 16;
		if (key_len == 192) init_key_len = 24;  
		if (key_len == 256) init_key_len = 32;

		
		// Nb je uvijek 4 (po FIPS-197 dokumentu)
		this.Nb = 4;
  	  	for (i = 0; i <= init_key_len-1; i++) {
  	  		hex = "" + init_key.charAt(2*i) + init_key.charAt(2*i+1);
  	  		skljuc[i] = Integer.parseInt(hex, 16);
  	  	}
		keyExpansion = new KeyExpansion();
		keyExpansion.initialise(key_len, skljuc);
		final Logger logger = Logger.getLogger(AddRoundKey.class);
		logger.debug(" ");
        logger.debug("Constructor AddRK ----------------------------------");
		logger.debug(Data.key[0]);
		logger.debug(Data.key[1]);
		logger.debug(Data.key[2]);
		logger.debug(Data.key[3]);
*/
	}

	/* initialize_State 
	 * 
	 */
	public void initialize_State(int key_len, String init_key, String InputVector)
	{
		int	init_key_len = 16;
		String hex = "";
		int ulaz[] = new int[16];
		int skljuc[] = new int[32];
		//String sk = "2b7e151628aed2a6abf7158809cf4f3c";
		if (key_len == 128) init_key_len = 16;
		if (key_len == 192) init_key_len = 24;  
		if (key_len == 256) init_key_len = 32;
		final Logger logger = Logger.getLogger(AddRoundKey.class);
		logger.debug("initialize_State ---");
		logger.debug("key_len ---" + key_len);
		logger.debug("init_key ---" + init_key);
		logger.debug("InputVector ---" + InputVector);
		logger.debug("Ulaz=InputVector -------------");
		
		for (int i = 0; i <= 15; i++) {
			hex = "" + InputVector.charAt(2 * i) + InputVector.charAt(2 * i + 1);
			ulaz[i] = Integer.parseInt(hex, 16);
			logger.debug(hex);
		}
		logger.debug("Data.State=Ulaz -------------");
		
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				Data.State[i][j] = ulaz[4 * i + j];
				logger.debug(Integer.toHexString(Data.State[i][j]));
			}
		}
		
  	  	for (int i = 0; i <= init_key_len-1; i++) {
  	  		hex = "" + init_key.charAt(2*i) + init_key.charAt(2*i+1);
  	  		skljuc[i] = Integer.parseInt(hex, 16);
  	  	}
  	  	
  	    logger.debug("Pred new KeyExpansion -------------");
		keyExpansion = new KeyExpansion();
		
		logger.debug("Pred initialise -------------");
		keyExpansion.initialise(key_len, skljuc);

		
		logger.debug(" ");
		
		
		logger.debug("initialize_State ---");
		logger.debug("init_state AddRK --------------------------------");
		logger.debug(" ");
		logger.debug("--------- KEY ------------------");
		String s = " ";
		for (int j=0; j <= 3; j++) {
			for (int i=0; i <= 3; i++) {
				s = s + Integer.toString((Data.key[16 + 4*i + j] & 0xff) + 0x100,
						16 /* radix */).substring(1) + " "; 
				//Data.State[i][j] = Data.State[i][j] ^ Data.key[16 + 4*i + j] ;
			}
		logger.debug(s);
		s = " ";
		}
		
		s = " ";
		logger.debug("initialize_State ---");
		logger.debug("--------- STATE-----------------");
  		 for (int j=0; j <= 3; j++) {
  			for (int i=0; i <= 3; i++) {
  				s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
  						16 /* radix */).substring(1) + " "; 

  			}
    	    logger.debug(s);
    	    s = " ";
  		}
        logger.debug("---Initialize State izlaz ------");
        logger.debug("--------------------------------");
        logger.debug("--------------------------------");
        logger.debug("--------------------------------");
        logger.debug(" ");
        logger.debug(" ");
        logger.debug(" ");


	}

	/* Ovdje radimo pravi posao
	 * Here we do the job
	 * 
	 */
	public void transform_state(int key_index) {
		  int i, j;
		  //key_index = 16;
		  String s = " ";
          final Logger logger = Logger.getLogger(AddRoundKey.class);
          logger.debug("---- Transform State -------");
		  logger.debug("--------- STATE-----------------");
  		  for (j=0; j <= 3; j++) {
  			for (i=0; i <= 3; i++) {
  				s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
  						16 /* radix */).substring(1) + " "; 

  			}
    	    logger.debug(s);
    	    s = " ";
  		  }
          logger.debug("--------------------------------");

          
          logger.debug(" ");
		  logger.debug("--------------------------------");
		  logger.debug(key_index);
		  logger.debug("--------- KEY ------------------");
		  for (j=0; j <= 3; j++) {
			for (i=0; i <= 3; i++) {
				s = s + Integer.toString((Data.key[key_index + 4*i + j] & 0xff) + 0x100,
						16 /* radix */).substring(1) + " "; 

				
				Data.State[i][j] = Data.State[i][j] ^ Data.key[key_index + 4*i + j] ;
			}
		  logger.debug(s);
		  s = " ";
		}
          logger.debug("--------------------------------");
		  logger.debug("--------- STATE-----------------");
  		  for (j=0; j <= 3; j++) {
  			for (i=0; i <= 3; i++) {
  				s = s + Integer.toString((Data.State[i][j] & 0xff) + 0x100,
  						16 /* radix */).substring(1) + " "; 

  			}
    	    logger.debug(s);
    	    s = " ";
  		  }
          logger.debug("--------------------------------");
			
	}

	
}
