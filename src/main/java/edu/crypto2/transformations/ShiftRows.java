/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;

import java.util.List;

import edu.crypto2.data.*;
/***********************************************************************
 * 
 */
public class ShiftRows implements Transformation{
	//public Data data = new Data();
	public int Nb;
	

	
	public String GetTransformationName() {
		return "----- ShiftRows -----";
	}
	
	public ShiftRows() {
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
		  int temp1, temp2;

		  temp1 = Data.State[0][1];
		  Data.State[0][1] = Data.State[1][1];
		  Data.State[1][1] = Data.State[2][1];
		  Data.State[2][1] = Data.State[3][1];
		  Data.State[3][1] = temp1;

		  temp1 = Data.State[0][2];
		  temp2 = Data.State[1][2];
		  Data.State[0][2] = Data.State[2][2];
		  Data.State[1][2] = Data.State[3][2];
		  Data.State[2][2] = temp1;
		  Data.State[3][2] = temp2;

		  temp1 = Data.State[0][3];
		  Data.State[0][3] = Data.State[3][3];
		  Data.State[3][3] = Data.State[2][3];
		  Data.State[2][3] = Data.State[1][3];
		  Data.State[1][3] = temp1;

		  return;		
	}

}
