/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;
import edu.crypto2.data.*;
/***********************************************************************
 * 
 */
/** 
 * Class InvMixColumns<p>
 * Task:<br>
 * To reverse MixColumns transformation<br> 
 * InvMixColumns transformation takes column by column from AES State and performs matrix multiplication as follows:<br>
 *.........| 0e 0b 0d 09 |<br>
 *new = | 09 0e 0b 0d | * old_column<br>
 *.........| 0d 09 0e 0b |<br>
 *.........| 0b 0d 09 0e |<br>
*/
public class InvMixColumns implements Transformation {
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
	public InvMixColumns() {
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
	 * InvMixColumns.transform_state<p>
	 * InvMixColumns transformation takes column by column from AES State and performs matrix multiplication as follows:<br>
	 *.........| 0e 0b 0d 09 |<br>
	 *new = | 09 0e 0b 0d | * old_column<br>
	 *.........| 0d 09 0e 0b |<br>
	 *.........| 0b 0d 09 0e |<br>
	*/
	public void transform_state() {
		int a[] = new int[4];
		int c;

		// 0e 0b 0d 09
		// 09 0e 0b 0d
		// 0d 09 0e 0b
		// 0b 0d 09 0e

		for(c=0;c<4;c++) {
			a[c] = Data.State[0][c];
			}
		Data.State[0][0] = galoa_mul_tab(a[0],0x0e) ^ galoa_mul_tab(a[1],0x0b) ^ galoa_mul_tab(a[2],0x0d) ^ galoa_mul_tab(a[3],0x09);
		Data.State[0][1] = galoa_mul_tab(a[0],0x09) ^ galoa_mul_tab(a[1],0x0e) ^ galoa_mul_tab(a[2],0x0b) ^ galoa_mul_tab(a[3],0x0d);
		Data.State[0][2] = galoa_mul_tab(a[0],0x0d) ^ galoa_mul_tab(a[1],0x09) ^ galoa_mul_tab(a[2],0x0e) ^ galoa_mul_tab(a[3],0x0b);
		Data.State[0][3] = galoa_mul_tab(a[0],0x0b) ^ galoa_mul_tab(a[1],0x0d) ^ galoa_mul_tab(a[2],0x09) ^ galoa_mul_tab(a[3],0x0e);

		for(c=0;c<4;c++) {
			a[c] = Data.State[1][c];
			}
		Data.State[1][0] = galoa_mul_tab(a[0],0x0e) ^ galoa_mul_tab(a[1],0x0b) ^ galoa_mul_tab(a[2],0x0d) ^ galoa_mul_tab(a[3],0x09);
		Data.State[1][1] = galoa_mul_tab(a[0],0x09) ^ galoa_mul_tab(a[1],0x0e) ^ galoa_mul_tab(a[2],0x0b) ^ galoa_mul_tab(a[3],0x0d);
		Data.State[1][2] = galoa_mul_tab(a[0],0x0d) ^ galoa_mul_tab(a[1],0x09) ^ galoa_mul_tab(a[2],0x0e) ^ galoa_mul_tab(a[3],0x0b);
		Data.State[1][3] = galoa_mul_tab(a[0],0x0b) ^ galoa_mul_tab(a[1],0x0d) ^ galoa_mul_tab(a[2],0x09) ^ galoa_mul_tab(a[3],0x0e);

		for(c=0;c<4;c++) {
			a[c] = Data.State[2][c];
			}
		Data.State[2][0] = galoa_mul_tab(a[0],0x0e) ^ galoa_mul_tab(a[1],0x0b) ^ galoa_mul_tab(a[2],0x0d) ^ galoa_mul_tab(a[3],0x09);
		Data.State[2][1] = galoa_mul_tab(a[0],0x09) ^ galoa_mul_tab(a[1],0x0e) ^ galoa_mul_tab(a[2],0x0b) ^ galoa_mul_tab(a[3],0x0d);
		Data.State[2][2] = galoa_mul_tab(a[0],0x0d) ^ galoa_mul_tab(a[1],0x09) ^ galoa_mul_tab(a[2],0x0e) ^ galoa_mul_tab(a[3],0x0b);
		Data.State[2][3] = galoa_mul_tab(a[0],0x0b) ^ galoa_mul_tab(a[1],0x0d) ^ galoa_mul_tab(a[2],0x09) ^ galoa_mul_tab(a[3],0x0e);

		for(c=0;c<4;c++) {
			a[c] = Data.State[3][c];
			}
		Data.State[3][0] = galoa_mul_tab(a[0],0x0e) ^ galoa_mul_tab(a[1],0x0b) ^ galoa_mul_tab(a[2],0x0d) ^ galoa_mul_tab(a[3],0x09);
		Data.State[3][1] = galoa_mul_tab(a[0],0x09) ^ galoa_mul_tab(a[1],0x0e) ^ galoa_mul_tab(a[2],0x0b) ^ galoa_mul_tab(a[3],0x0d);
		Data.State[3][2] = galoa_mul_tab(a[0],0x0d) ^ galoa_mul_tab(a[1],0x09) ^ galoa_mul_tab(a[2],0x0e) ^ galoa_mul_tab(a[3],0x0b);
		Data.State[3][3] = galoa_mul_tab(a[0],0x0b) ^ galoa_mul_tab(a[1],0x0d) ^ galoa_mul_tab(a[2],0x09) ^ galoa_mul_tab(a[3],0x0e);

	}
}
