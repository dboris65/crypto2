/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;

import edu.crypto2.data.*;

/***********************************************************************
 * 
 */
public class AddRoundKey implements Transformation{
	/**
	 * Nb is always 4 (by FIPS-197), but authors of AES left
	 * the space to change something in the future, 
	 * so we wiil do the same
	 */
	public int Nb;

	public KeyExpansion keyExpansion;


	/**
	 * Dummy constructor.<br>
	 * Task:<br>
	 * Just set Nb to 4<br>
	 */
	public AddRoundKey(){            
		// Nb - always 4 (by FIPS-197)
		this.Nb = 4;
	}

	/**
	 *  initialize_State<p>
	 *  Task:<br>
	 *  Fills State array with initial values taken from InputVector and expand key according to key_len and init_key variables.<br>
	 *  Input variables must satisfy following conditions:<br>
	 *  InputVector - 32 alphanumercis with hex values (16 bytes)<br>
	 *  key_len=128 => length(init_key)=16 bytes<br>
	 *  key_len=192 => length(init_key)=24 bytes<br>
	 *  key_len=256 => length(init_key)=32 bytes<br>
	 *  Resulting key will be stored in Data.Key variable
	 */
	public void initialize_State(int key_len, String init_key, String InputVector)
	{
		int	init_key_len = 16;
		String hex = "";
		int ulaz[] = new int[16];
		int skljuc[] = new int[32];
		if (key_len == 128) init_key_len = 16;
		if (key_len == 192) init_key_len = 24;  
		if (key_len == 256) init_key_len = 32;
		
		
		
		for (int i = 0; i <= 15; i++) {
			hex = "" + InputVector.charAt(2 * i) + InputVector.charAt(2 * i + 1);
			ulaz[i] = Integer.parseInt(hex, 16);
		}
		
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				Data.State[i][j] = ulaz[4 * i + j];
			}
		}
		
  	  	for (int i = 0; i <= init_key_len-1; i++) {
  	  		hex = "" + init_key.charAt(2*i) + init_key.charAt(2*i+1);
  	  		skljuc[i] = Integer.parseInt(hex, 16);
  	  	}
  	  	
		keyExpansion = new KeyExpansion();
		keyExpansion.initialise(key_len, skljuc);
	}
	
	

	/**
	 * AddRoundkey.transform_state<p>
	 * Use key_index to XOR each of 16 State bytes with current 16 key bytes 
	*/
	public void transform_state(int key_index) {
		  int i, j;
		  for (j=0; j <= 3; j++) {
			for (i=0; i <= 3; i++) {
				Data.State[i][j] = Data.State[i][j] ^ Data.key[key_index + 4*i + j] ;
			}
		}
			
	}

	
}
