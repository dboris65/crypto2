/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;

import edu.crypto2.data.*;
import edu.crypto2.transformations.SubBytes;
import edu.crypto2.transformations.AddRoundKey;
import edu.crypto2.transformations.ShiftRows;
import edu.crypto2.transformations.MixColumns;


/***********************************************************************
 * 
 */
public class Cipher {
	public Cipher(int duzina_kljuca) { 
		int Nb;
		int Nr;
		int i, j;
		int runda;
		int runda2;
		AddRoundKey addRoundKey = new AddRoundKey();
		SubBytes subBytes = new SubBytes();
		ShiftRows shiftRows = new ShiftRows();
		MixColumns mixColumns = new MixColumns();


		switch (duzina_kljuca)
		{
			case 128 :
			{
					  Nb = 4;  //br.kolona
					  //Nk = 4;  //br.rijeci u kljucu
					  Nr = 10; //br.rundi
					  break;
			}
			case 192 :
			{
					  Nb = 4;
					  //Nk = 6;
					  Nr = 12;
					  break;
			}
			case 256 :
			{
					  Nb = 4;
					  //Nk = 8;
					  Nr = 14;
					  break;
			}
			default  :
			{
					  System.out.println("Key length must be 128, 192 or 256");
					  return;
			}
		}
		//subBytes.transform_state();
		for (i = 0; i <= Nb-1; i++) {
			for (j = 0; j <= Nb-1; j++) {
			  Data.State[i][j] = Data.Input[i][j];
			}
		}

		runda2 = runda = 0;

		//*************************************
		addRoundKey.transform_state(0);

		for (runda = 1; runda <= Nr-1; runda++)
		{
			// SubBytes
			for (i = 0; i <= Nb-1; i++) {
				for (j = 0; j <= Nb-1; j++) {
					Data.State[i][j] = Data.SBox[ Data.State[i][j] ];
				}
			}
			shiftRows.transform_state();
			mixColumns.transform_state();
			addRoundKey.transform_state(4*runda*Nb );
			runda2=runda;
		}

		runda2++;
		// SubBytes
		for (i = 0; i <= Nb-1; i++) {
			for (j = 0; j <= Nb-1; j++) {
				Data.State[i][j] = Data.SBox[ Data.State[i][j] ];
			}
		}
		// Final round
		shiftRows.transform_state();
		addRoundKey.transform_state( 4*runda2*Nb );
		for (i = 0; i <= 3; i++) {
			for (j = 0; j <= 3; j++) {
				Data.Output[i][j] = Data.State[i][j];
			}
		}
		Data.byte_counter += 16;

	}



}
