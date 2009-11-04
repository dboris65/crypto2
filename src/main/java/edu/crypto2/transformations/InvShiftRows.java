/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;

import edu.crypto2.data.*;
/***********************************************************************
 *  
 */
/**
 * Class InvShiftRows<p>
 * Task:<br>
 * To rearrange bytes of State to invert ShiftRows results.<br> 
 * State buffer will be transformed in following manner:<br> 
 * <br>
 * 7A 89 2B 3D  ---->  7A 89 2B 3D <br>
 * D5 EF CA <b>9F</b>  ----> <b>9F</b> DF EF CA<br>
 * FD 4E <b>10 F5</b>  ---> <b>10 F5</b> FD 4E<br>
 * A7 <b>27 0B 9F0</b>  ---> <b>27 0B 9F</b> A7<br>
 */
public class InvShiftRows implements Transformation{
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
	public InvShiftRows() {
		// Nb - always 4 (by FIPS-197)			
		this.Nb = 4;
	}
	
	/**
	 *  initialize_State<p>
	 *  Fills State array with initial values taken from inStr.<p>
	 *  @param inStr
	 *  String inStr - 32 alphanumercis with hex values (16 bytes)
	 */
	public void initialize_State(String inStr)
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
	 * InvShiftRows.transform_state<p>
	 * State buffer will be transformed in following manner:<br> 
	 * <br>
	 * 7A 89 2B 3D  ---->  7A 89 2B 3D <br>
	 * D5 EF CA <b>9F</b>  ----> <b>9F</b> DF EF CA<br>
	 * FD 4E <b>10 F5</b>  ---> <b>10 F5</b> FD 4E<br>
	 * A7 <b>27 0B 9F0</b>  ---> <b>27 0B 9F</b> A7<br>
	 * 
	 */
	public void transform_state() {
		  int temp1, temp2;

		  temp1 = Data.State[0][1];
		  temp2 = Data.State[2][1];

		  Data.State[0][1] = Data.State[3][1];
		  Data.State[2][1] = Data.State[1][1];
		  Data.State[1][1] = temp1;
		  Data.State[3][1] = temp2;

		  temp1 = Data.State[3][2];
		  temp2 = Data.State[0][2];
		  Data.State[0][2] = Data.State[2][2];
		  Data.State[3][2] = Data.State[1][2];
		  Data.State[1][2] = temp1;
		  Data.State[2][2] = temp2;

		  temp1 = Data.State[0][3];
		  Data.State[0][3] = Data.State[1][3];
		  Data.State[1][3] = Data.State[2][3];
		  Data.State[2][3] = Data.State[3][3];
		  Data.State[3][3] = temp1;

		  return;
	}

}
