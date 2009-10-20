/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;
import edu.crypto2.data.*;
/***********************************************************************
 * 
 */
public class MixColumns implements Transformation {
	//public Data data = new Data();
	public int Nb;
	
	public String GetTransformationName() {
		return "----- mixColumns -----";
	}
	
	/**
	 * 
	 */
	public MixColumns() {
		// Nb je uvijek 4 (po FIPS-197 dokumentu)		
		this.Nb = 4;
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
	/* Ovdje radimo pravi posao
	 * Here we do the job
	 * 
	 */
	public void transform_state() {
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
}
