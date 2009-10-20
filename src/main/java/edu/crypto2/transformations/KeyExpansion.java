/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;
import org.apache.log4j.Logger;
//import org.apache.tapestry5.annotations.SessionState;

import edu.crypto2.data.*;
import edu.crypto2.pages.SubBytesPG;
/***********************************************************************
 * 
 */
public class KeyExpansion implements Transformation{
	//@Session State(create = false)
	//SessionState;
	public int Nb;
	
	public String GetTransformationName() {
		return "----- AddRoundKey -----";
	}

	public KeyExpansion() {
		// Nb je uvijek 4 (po FIPS-197 dokumentu)
		this.Nb = 4;
	}

	public void initialise(int key_len, int[] kljuc) {
		int i;
		// Nb je uvijek 4 (po FIPS-197 dokumentu)
		this.Nb = 4;
		for (i = 0; i <= 239; i++)
			Data.key[i] = 0x0;
		final Logger logger = Logger.getLogger(KeyExpansion.class);
		logger.debug("--- key_len ---" + key_len);
		logger.debug("--- kljuc ---" + kljuc);
		
			  if (key_len == 128) {
				for (i = 0; i <= 15; i++)
					Data.key[i] = kljuc[i];
				logger.debug("--- pred KEX128 ---");
				KeyExpansion128();
			    logger.debug("--- nakon KEX128 ---");
			  }
			  else
			  if (key_len == 192) {
				for (i = 0; i <= 23; i++)
					Data.key[i] = kljuc[i];
				logger.debug("--- pred KEX192 ---");
				KeyExpansion192();
			    logger.debug("--- nakon KEX192 ---");
			  }
			  else
			  if (key_len == 256) {
				for (i = 0; i <= 31; i++)
					Data.key[i] = kljuc[i];
				logger.debug("--- pred KEX256 ---");
				KeyExpansion256();
			    logger.debug("--- nakon KEX256 ---");
			  }
			  
			
			logger.debug(" ");
		    logger.debug("Constructor KEX ----------------------------------");
		    String hex = "";
		    for (int k=1; k<64; k++){
		    	hex = hex + Integer.toString((Data.key[k] & 0xff) + 0x100,
  						16 /* radix */).substring(1);
		    	
		    	
		    }
		    logger.debug(hex);
		    logger.debug("----------------------------------");
		    logger.debug(" ");
			//logger.debug(Data.key[1]);
			//logger.debug(Data.key[2]);
			//logger.debug(Data.key[3]);
			  
			  
	}
	
	
	/***********************************************************
	 * Galois multiplication
	 *
	 */
	public int galoa_mul_tab(int a, int b) {
		int s;
		int z = 0;

		/* korak 1. pronadji brojeve u tabelama logaritama */
		/* korak 2. saberi i pronadji moduo 255 */
		s = Data.ltable[a] + Data.ltable[b];
		s %= 255;
		/* korak 3. pronadji rezultat u tabeli eksponenata */
		s = Data.atable[s];
		/* treba da vratimo rezultat nula ako je ili a ili b nula (ili oba) */
		if(a == 0) {
			s = z;
		}
		if(b == 0) {
			s = z;
		}

		return s;
	}

	/***********************************************************
	 * AES RotWord
	 *
	 */
	public void RotWord() {
			int a,c;
			a = Data.tmpkey[0];
			for(c=0;c<3;c++)
				Data.tmpkey[c] = Data.tmpkey[c + 1];
			Data.tmpkey[3] = a;
			return;
	}

	/***********************************************************
	 * AES rcon for key expansion
	 *
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

	/***********************************************************
	 * AES key expansion base
	 *
	 */
	public void key_expansion_base(int i) {
		int a;
		/* Rotiramo najvisih 8 bita ulaza */
		/*---------------------    ---------------------*/
		/*| 1d | 2c | 3a | 4f | -> | 2c | 3a | 4f | 1d |*/
		/*---------------------    ---------------------*/
		RotWord();

		/* Primjenicemo Rijndael-ov s-box na sva 4 bajta */
		for(a = 0; a < 4; a++)
			Data.tmpkey[a] = Data.SBox[ Data.tmpkey[a] ];
		/* Na najvisi (prvi) bajt, primjenicemo XOR sa 2 na i */
		/* word[0] = word[0] XOR RCON[i]; */
		Data.tmpkey[0] ^= rcon(i);

	}
	
	/***********************************************************
	 * AES key expansion 128
	 *
	 */
	public void KeyExpansion128() {
			/* c je 16 jer prvi podkljuc definise korisnik  */
			int c = 16;
			int i = 1;
			int a;

			/* Treba nam 11 skupova od po 16 bajtova za 128-bitni rezim rada */
			/*   c < Nb*(Nr+1) Nb-broj kolona, uvjek 4, Nr-broj RUNDI, 10, 12 ili 14 */
			/*     4*(10+1) * 4 bajta u rijeci */
			while(c < 176) {
					/* Kopiraj zadnja 4 bajta iz bloka u temp varijablu */
					for(a = 0; a < 4; a++)
						Data.tmpkey[a] = Data.key[a + c - 4];

					/* Na svaka 4 bloka (od po 4 bajta) malo racunamo */
					if(c % 16 == 0) {
						key_expansion_base(i);
						i++;
					}

					for(a = 0; a < 4; a++) {
						Data.key[c] = Data.key[c - 16] ^ Data.tmpkey[a];
							c++;
					}
			}
			final Logger logger = Logger.getLogger(KeyExpansion.class);
			logger.debug("-----After KeyExpansion--");
			logger.debug("-----KeyExpansion---------");
			logger.debug("-----KeyExpansion---------");

	}
	
	/***********************************************************
	 * AES key expansion 192
	 *
	 */
	public void KeyExpansion192() {
		/* c je 24 jer prvi podkljuc definise korisnik  */
		int c = 24;
		int i = 1;
		int a;

		/* Treba nam 12+1 skupova od po 16 bajtova za 192-bitni rezim rada */
		//    c < Nb*(Nr+1)     Nb-broj kolona, uvjek 4, Nr-broj RUNDI, 10, 12 ili 14
		//        4*(12+1) * 4 bajta u rijeci
		while(c < 208) {
				/* Kopiraj zadnja 4 bajta iz bloka u temp varijablu */
				for(a = 0; a < 4; a++)
					Data.tmpkey[a] = Data.key[a + c - 4];
				/* Na svaka 4 bloka (od po 4 bajta) malo racunamo */
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
	
	/***********************************************************
	 * AES key expansion 256
	 *
	 */
	public void KeyExpansion256() {
		/* c je 32 jer prvi podkljuc definise korisnik  */
		int c = 32;
		int i = 1;
		int a;

		/* Treba nam 14+1 skupova od po 16 bajtova za 256-bitni rezim rada */
		// 4*(14+1) * 4 bajta u rijeci
		while(c < 240) {
				/* Kopiraj zadnja 4 bajta iz bloka u temp varijablu */
				for(a = 0; a < 4; a++)
					Data.tmpkey[a] = Data.key[a + c - 4];

				/* Na svaka 4 bloka (od po 4 bajta) malo racunamo */
				if(c % 32 == 0) {
					key_expansion_base(i);
					i++;
				}
				
				//********************************************************
				// ovdje se 256 malo razlikuje
				// Za 256 bitni kljuc, dodajemo dodatni S-Box
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
