/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;
import edu.crypto2.data.*;
/***********************************************************************
 * 
 */
/** 
 * Class MixColumns<p>
 * Task:<br>
 * To mix State columns (socond part of permutation)<br> 
 *MixColumns transformation takes column by column from AES State and performs matrix multiplication as follows:<br>
 *.........| 02 03 01 01 |<br>
 *new = | 01 02 03 01 | * old_column<br>
 *.........| 01 01 02 03 |<br>
 *.........| 03 01 01 02 |<br>
*/
public class MixColumns implements Transformation {
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
	 * Just set Nb to 4<br>
	 */
	public MixColumns() {
		// Nb - always 4 (by FIPS-197)	
		this.Nb = 4;
	}
	
	/**
	 * Galois multiplication<br>
	 * Task:<br>
	 * multiply two numbers using AES (Galois) multiplication<br>
	 * To speed up Galois multiplication, we will use well known formula:<br>
	 * log(x*y)=log(x)*log(y)<br>
	 * x*y=antilog(log(x)*log(y))<br>
	 * and already calculated subresults stored in ltable and atable<br>
	 * @param a (int/byte 0..255)
	 * @param b (int/byte 0..255)
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
	 * MixColumns.TransformState<p>
	 *MixColumns transformation takes column by column from AES State and performs matrix multiplication as follows:<br>
	 *.........| 02 03 01 01 |<br>
	 *new = | 01 02 03 01 | * old_column<br>
	 *.........| 01 01 02 03 |<br>
	 *.........| 03 01 01 02 |<br>
	*/
	public void TransformState() {
	int a[] = new int [4];
	int c;

	// 02 03 01 01
	// 01 02 03 01
	// 01 01 02 03
	// 03 01 01 02

	for(c=0;c<4;c++) {
		a[c] = Data.State[0][c];
		}
	Data.State[0][0] = galoa_mul_tab(a[0],2) ^ galoa_mul_tab(a[1],3) ^ galoa_mul_tab(a[2],1) ^ galoa_mul_tab(a[3],1);
	Data.State[0][1] = galoa_mul_tab(a[0],1) ^ galoa_mul_tab(a[1],2) ^ galoa_mul_tab(a[2],3) ^ galoa_mul_tab(a[3],1);
	Data.State[0][2] = galoa_mul_tab(a[0],1) ^ galoa_mul_tab(a[1],1) ^ galoa_mul_tab(a[2],2) ^ galoa_mul_tab(a[3],3);
	Data.State[0][3] = galoa_mul_tab(a[0],3) ^ galoa_mul_tab(a[1],1) ^ galoa_mul_tab(a[2],1) ^ galoa_mul_tab(a[3],2);

	for(c=0;c<4;c++) {
		a[c] = Data.State[1][c];
		}
	Data.State[1][0] = galoa_mul_tab(a[0],2) ^ galoa_mul_tab(a[1],3) ^ galoa_mul_tab(a[2],1) ^ galoa_mul_tab(a[3],1);
	Data.State[1][1] = galoa_mul_tab(a[0],1) ^ galoa_mul_tab(a[1],2) ^ galoa_mul_tab(a[2],3) ^ galoa_mul_tab(a[3],1);
	Data.State[1][2] = galoa_mul_tab(a[0],1) ^ galoa_mul_tab(a[1],1) ^ galoa_mul_tab(a[2],2) ^ galoa_mul_tab(a[3],3);
	Data.State[1][3] = galoa_mul_tab(a[0],3) ^ galoa_mul_tab(a[1],1) ^ galoa_mul_tab(a[2],1) ^ galoa_mul_tab(a[3],2);

	for(c=0;c<4;c++) {
		a[c] = Data.State[2][c];
		}
	Data.State[2][0] = galoa_mul_tab(a[0],2) ^ galoa_mul_tab(a[1],3) ^ galoa_mul_tab(a[2],1) ^ galoa_mul_tab(a[3],1);
	Data.State[2][1] = galoa_mul_tab(a[0],1) ^ galoa_mul_tab(a[1],2) ^ galoa_mul_tab(a[2],3) ^ galoa_mul_tab(a[3],1);
	Data.State[2][2] = galoa_mul_tab(a[0],1) ^ galoa_mul_tab(a[1],1) ^ galoa_mul_tab(a[2],2) ^ galoa_mul_tab(a[3],3);
	Data.State[2][3] = galoa_mul_tab(a[0],3) ^ galoa_mul_tab(a[1],1) ^ galoa_mul_tab(a[2],1) ^ galoa_mul_tab(a[3],2);

	for(c=0;c<4;c++) {
		a[c] = Data.State[3][c];
		}
	Data.State[3][0] = galoa_mul_tab(a[0],2) ^ galoa_mul_tab(a[1],3) ^ galoa_mul_tab(a[2],1) ^ galoa_mul_tab(a[3],1);
	Data.State[3][1] = galoa_mul_tab(a[0],1) ^ galoa_mul_tab(a[1],2) ^ galoa_mul_tab(a[2],3) ^ galoa_mul_tab(a[3],1);
	Data.State[3][2] = galoa_mul_tab(a[0],1) ^ galoa_mul_tab(a[1],1) ^ galoa_mul_tab(a[2],2) ^ galoa_mul_tab(a[3],3);
	Data.State[3][3] = galoa_mul_tab(a[0],3) ^ galoa_mul_tab(a[1],1) ^ galoa_mul_tab(a[2],1) ^ galoa_mul_tab(a[3],2);

	}
}
