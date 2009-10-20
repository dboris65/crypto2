/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;

import java.util.List;

import edu.crypto2.data.*;

/***********************************************************************
 * 
 */
public class SubBytes implements Transformation {
	//public Data data = new Data();
	public int Nb;
	

	
	public String GetTransformationName() {
		return "----- SubBytes -----";
	}

	public SubBytes() {
		// Nb je uvijek 4 (po FIPS-197 dokumentu)		
		this.Nb = 4;
	}

	/* initialize_State - samo za edukaciju i testiranje
	 * initialize_State - just for education and testing
	 */
	public void initialize_State(String s)
	{
		String hex = "";
		int ulaz[] = new int[16];

		for (int i = 0; i <= 15; i++) {
			hex = "" + s.charAt(2 * i) + s.charAt(2 * i + 1);
			ulaz[i] = Integer.parseInt(hex, 16);
		}

		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				Data.State[i][j] = ulaz[4 * i + j];
			}
		}

	}
	
	/* Ovdje radimo pravi posao
	 * Here we do the job
	 * 
	 */
	public void transform_state() {
		// Nb je uvijek 4 (po FIPS-197 dokumentu), ali ostavljamo prostor da nesto promjenimo u buducnosti
		for (int i = 0; i <= Nb - 1; i++) {
			for (int j = 0; j <= Nb - 1; j++) {
				Data.State[i][j] = Data.SBox[Data.State[i][j]];
			}
		}
	}

}
