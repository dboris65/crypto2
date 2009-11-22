/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;

import edu.crypto2.data.*;
/***********************************************************************
 *  
 */
/**
 * Class ShiftRows<p>
 * Task:<br>
 * To shift State rows (first part of permutation)<br> 
 * State buffer will be transformed in following manner:<br> 
 * <br>
 * d4 e0 b8 1e  ---->  d4 e0 b8 1e <br>
 * <b>27</b> bf b4 41  ----> bf b4 41 <b>27</b><br>
 * <b>11 98</b> 5d 52  ---> 5d 52 <b>11 98</b><br>
 * <b>ae f1 e5</b> 30  ---> 30 <b>ae f1 e5</b><br>
 */
public class ShiftRows implements Transformation{
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
	public ShiftRows() {
		// Nb - always 4 (by FIPS-197)			
		this.Nb = 4;
	}
	
	/**
	 *  initialize_State<p>
	 *  Fills State array with initial values taken from inStr.<p>
	 *  @param inStr
	 *  String inStr - 32 alphanumercis with hex values (16 bytes)
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
	 * ShiftRows.TransformState<p>
	 * State buffer will be transformed in following manner:<br> 
	 * <br>
	 * d4 e0 b8 1e  ---->  d4 e0 b8 1e <br>
	 * <b>27</b> bf b4 41  ----> bf b4 41 <b>27</b><br>
	 * <b>11 98</b> 5d 52  ---> 5d 52 <b>11 98</b><br>
	 * <b>ae f1 e5</b> 30  ---> 30 <b>ae f1 e5</b><br>
	 * 
	 */
	public void TransformState() {
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
