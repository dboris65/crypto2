/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;

import edu.crypto2.data.*;

/***********************************************************************
 * 
 */
/** 
 * Class InvSubBytes<p>
 * Task:<br>
 * To apply Inverse S-Box on State buffer. This S-Box will invert first S-Box results.<br> 
 * Two basic components of many algorithms are substitution box ( S-Box) 
 * and permutation box ( P-Box).<br>
 * AES performs SBox explicitly (using this transformation and SBox array), while permutations are done implicitly.
 */
public class InvSubBytes implements Transformation {
	public String inStr;
	
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
	public InvSubBytes() {
		// Nb - always 4 (by FIPS-197)		
		this.Nb = 4;
	}

	/**
	 *  initialize_State<p>
	 *  Fills State array with initial values taken from inStr.<p>
	 *  input:<br>
	 *  @param inStr (String) - 32 alphanumercis with hex values (16 bytes)
	 */
	public void InitializeState()
	{
		String hex = "";
		int ulaz[] = new int[16];

		for (int i = 0; i <= 15; i++) {
			hex = "" + inStr.charAt(2 * i) + inStr.charAt(2 * i + 1);
			ulaz[i] = Integer.parseInt(hex, 16);
		}

		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				Data.State[i][j] = ulaz[4 * i + j];
			}
		}

	}
	
	/**
	 * SubBytes.TransformState<p>
	 * Apply AES Inverse S-Box to every byte of State.
	 * 
	 */
	public void TransformState() {
		// Nb is always 4 (by FIPS-197), but they live the space to change something in the future
		for (int i = 0; i <= Nb - 1; i++) {
			for (int j = 0; j <= Nb - 1; j++) {
				Data.State[i][j] = Data.InvSBox[Data.State[i][j]];
			}
		}
	}

}
