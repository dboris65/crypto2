/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;
import edu.crypto2.data.*;
/***********************************************************************
 * 
 */
/**
 * Class KeyExpansion<p>
 * Task:<br>
 * To expand inital key from 16, 24 or 32 bytes to 16*11, 16*13 or 16*15 bytes, respectively<br> 
 * This transformation always takes last 4 bytes from already generated key, 
 * and expand it to give next 16 bytes of key 
*/
public class KeyExpansion implements Transformation{
	/**
	 * Nb is always 4 (by FIPS-197), but authors of AES left
	 * the space to change something in the future, 
	 * so we wiil do the same
	 */
	public int Nb;
	/**
	 * Dummy constructor.<br>
	 * Task:<br>
	 * Just set Nb to 4<br>
	 */
	public KeyExpansion() {
		// Nb - always 4 (by FIPS-197)
		this.Nb = 4;
	}
	
	/**
	 *  initialize<p>
	 *  Task:<br>
	 *  To expand key.<br>
	 *  Input variables must satisfy following conditions:<br>
	 *  key_len=128 => length(kljuc)=16 bytes<br>
	 *  key_len=192 => length(kljuc)=24 bytes<br>
	 *  key_len=256 => length(kljuc)=32 bytes<br>
	 *  Resulting key will be stored in Data.Key variable
	 */
	public void initialise(int key_len, int[] kljuc) {
		int i;
		// Nb - always 4 (by FIPS-197)
		this.Nb = 4;
		for (i = 0; i <= 239; i++)
			Data.key[i] = 0x0;
			  if (key_len == 128) {
				for (i = 0; i <= 15; i++)
					Data.key[i] = kljuc[i];
				KeyExpansion128();
			  }
			  else
			  if (key_len == 192) {
				for (i = 0; i <= 23; i++)
					Data.key[i] = kljuc[i];
				KeyExpansion192();
			  }
			  else
			  if (key_len == 256) {
				for (i = 0; i <= 31; i++)
					Data.key[i] = kljuc[i];
				KeyExpansion256();
			  }
			  
			
		    String hex = "";
		    for (int k=1; k<64; k++){
		    	hex = hex + Integer.toString((Data.key[k] & 0xff) + 0x100,
  						16 /* radix */).substring(1);
		    }
	}
	
	
	/**
	 * Galois multiplication<br>
	 * Task:<br>
	 * multiply two numbers using AES (Galois) multiplication<br>
	 * To speed up Galois multiplication, we will use well known formula:<br>
	 * log(x*y)=log(x)*log(y)<br>
	 * x*y=antilog(log(x)*log(y))<br>
	 * and already calculated subresults stored in ltable and atable<br>
	 * @return result of multiplication
	 */
	public int galoa_mul_tab(int a, int b) {
		int s;
		int z = 0;

		/* step 1. find numbers in logarithm table */
		/* step 2. add and calculate moduo 255 */
		s = Data.ltable[a] + Data.ltable[b];
		s %= 255;
		/* step 3. find result in exponent table */
		s = Data.atable[s];
		if(a == 0) {
			s = z;
		}
		if(b == 0) {
			s = z;
		}

		return s;
	}

	/**
	 * AES RotWord<br>
	 * Task:<br>
	 * Rotate bytes inside 4 byte word, in following manner:<br>
	 * 01 02 03 04  ----> 02 03 04 01<br>
	 * This 4-byte word is Data.tmpKey variable.
	 */
	public void RotWord() {
			int a,c;
			a = Data.tmpkey[0];
			for(c=0;c<3;c++)
				Data.tmpkey[c] = Data.tmpkey[c + 1];
			Data.tmpkey[3] = a;
			return;
	}

	/**
	 * AES RCon<br>
	 * Task:<br>
	 * Exponentiate 2 (in Galois fields) In times <br>
	 * So, Variable In must be element of Galois field (e.g. Byte, or 0..255)<br>
	 * Rcon[i] is constant array that contains values given by:<br>
	 * [x^(i-1), {00}, {00}, {00}]
 	 */
	public int rcon(int in) {
			int c=1;
			if(in == 0)
					return 0;
			while(in != 1) {
					c = galoa_mul_tab(c,2);
					in--;
			}
			return c;
	}

	/**
	 * key_expansion_base<br>
	 * Task:<br>
	 * Apply RotWord, SBox and RCon on Data.tmpKey variable.<br>
	 * Key expansion function is slightly different for different initial key sizes.<br> 
	 * For 256 bit key, we have to use SubWord (S-Box) transformation one more time 
	 * then for expansion of 128 or 192 bit keys.<br>
	 * Common part for expanding keys of any length is separated this function.
	 */
	public void key_expansion_base(int i) {
		int a;
		/* Rotate hi 8 bits in tmpKey */
		/*---------------------    ---------------------*/
		/*| 1d | 2c | 3a | 4f | -> | 2c | 3a | 4f | 1d |*/
		/*---------------------    ---------------------*/
		RotWord();

		/* AES S-Box on every byte of State */
		for(a = 0; a < 4; a++)
			Data.tmpkey[a] = Data.SBox[ Data.tmpkey[a] ];
		/* On hi byte, apply XOR with 2^(i-1) */
		/* word[0] = word[0] XOR RCON[i]; */
		Data.tmpkey[0] ^= rcon(i);

	}
	
	/**
	 * AES key expansion 128<p>
	 * Task:<br>
	 * To expand key to 11*16 = 176 bytes<br>
	 * We need 11 sets of 16 bytes each
	 * because AES128 have (10+1) rounds and State is always 16 bytes long
	 */
	public void KeyExpansion128() {
			/* c = 16 because first 16 bytes are user defined  */
			int c = 16;
			int i = 1;
			int a;

			while(c < 176) {
					/* Copy last 4 bytes from key to temp variable */
					for(a = 0; a < 4; a++)
						Data.tmpkey[a] = Data.key[a + c - 4];

					/* On every 4 blocks (of four bytes), do calculations with tmpKey */
					if(c % 16 == 0) {
						key_expansion_base(i);
						i++;
					}

					for(a = 0; a < 4; a++) {
						Data.key[c] = Data.key[c - 16] ^ Data.tmpkey[a];
							c++;
					}
			}
	}
	
	/**
	 * AES key expansion 192<p>
	 * Task:<br>
	 * To expand key to 13*16 = 208 bytes<br>
	 * We need 13 sets of 16 bytes each
	 * because AES192 have (12+1) rounds and State is always 16 bytes long
	 */
	public void KeyExpansion192() {
		/* c = 24 because first 24 bytes are user defined  */
		int c = 24;
		int i = 1;
		int a;

		while(c < 208) {
				/* Copy last 4 bytes from key to temp variable */
				for(a = 0; a < 4; a++)
					Data.tmpkey[a] = Data.key[a + c - 4];
				/* On every 6 blocks (of four bytes), do calculations with tmpKey */
				if(c % 24 == 0) {
					key_expansion_base(i);
					i++;
				}
				for(a = 0; a < 4; a++) {
					Data.key[c] = Data.key[c - 24] ^ Data.tmpkey[a];
						c++;
				}
		}
	}
	
	/**
	 * AES key expansion 256<p>
	 * Task:<br>
	 * To expand key to 15*16 = 240 bytes<br>
	 * We need 15 sets of 16 bytes each
	 * because AES192 have (14+1) rounds and State is always 16 bytes long
	 */
	public void KeyExpansion256() {
		/* c = 32 because first 32 bytes are user defined  */
		int c = 32;
		int i = 1;
		int a;

		while(c < 240) {
				/* Copy last 4 bytes from key to temp variable */
				for(a = 0; a < 4; a++)
					Data.tmpkey[a] = Data.key[a + c - 4];

				/* On every 8 blocks (of four bytes), do calculations with tmpKey */
				if(c % 32 == 0) {
					key_expansion_base(i);
					i++;
				}
				
				// Extra S-Box, only in 256 bit mode
				if(c % 32 == 16) {
						for(a = 0; a < 4; a++)
							Data.tmpkey[a] = Data.SBox[ Data.tmpkey[a] ];
				}

				for(a = 0; a < 4; a++) {
					Data.key[c] = Data.key[c - 32] ^ Data.tmpkey[a];
						c++;
				}
		}
	}

}
