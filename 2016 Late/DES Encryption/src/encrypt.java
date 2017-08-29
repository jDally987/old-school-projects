/* Assignment 1 - DES Encryption Program
 * CS 465 Data Security
 * For Dr. Katerina Goseva
 * John Chaney
 * LCSEE username: jdchaney
 * Student ID #: 800016133
 * Submission date: 9/26/2016
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;


//public static final int CYCLES = 16;

public class encrypt {

	public static void main(String[] args) throws IOException {
		int[] plainkey = getKey();
		int[] plaintext = readFile("plaintext.txt");
		
		int[][] blockArray = splitBlocks(plaintext); // split plaintext into blocks
		
		
		// iterate through each block of plaintext, & encrypt it!
		int[][] cBlock = new int[blockArray.length][64];
		for (int i=0; i<blockArray.length; i++){
			int[] permutedBlock = initPermute(blockArray[i]);
			int[] output = permutedBlock;
			for (int j=0; i<16; i++){
				output = blockCipher(output, plainkey, i);
			}
			
			
			cBlock[i] = finalPermute(output);
		}
		
		
		//System.out.println(Arrays.toString(plainkey));
	}
	
	
	
	
	
	
	// returns an int-bit array containing ASCII representations of the contents of plaintext file
	public static int[] readFile(String name) throws IOException {
		File file = new File(name);
		byte[] dataBytes = Files.readAllBytes(file.toPath());
		
		// have a byte array - now convert it into String array
		int[] data = new int[dataBytes.length*8];
		for (int i=0; i<8; i++){
			String str = Integer.toBinaryString((dataBytes[i] & 0xFF) + 0x100).substring(1);
			// convert each byte into string. Then, parse string for each bit
			for (int j=0; j<8; j++){
				data[(i*8)+j] = Integer.parseInt(str.charAt(j)+"");
			}
	    }
		
		return data;
	}
	
	
	// asks the user for the password desired to generate a key, then
	// returns an int-bit array representation of ASCII characters of key
	public static int[] getKey(){
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter the password for the key:");
		String keyStr = reader.nextLine();
		byte[] keyBytes = keyStr.getBytes();
		
		// have a byte array - now convert each byte, into 8 separate bits, in an int array
		int[] key = new int[8*8];
		for (int i=0; i<8; i++){
			String str = Integer.toBinaryString((keyBytes[i] & 0xFF) + 0x100).substring(1);
			// convert each byte into string. Then, parse string for each bit
			for (int j=0; j<8; j++){
				key[(i*8)+j] = Integer.parseInt(str.charAt(j)+"");
			}
	    }
		return key;
	}
	
	
	
	// does all the operations on the 64bit key, & returns 48bit subkey
	public static int[] subKey(int[] key, int cycle){
		// first, remove every 8th bit to obtain 56bit key
		int[] key56 = new int[56];
		for (int i=0; i<8; i++){
			for (int j=0; j<7; j++){
				key56[(i*7)+j] = key[(i*8)+j];
			}
		}
		
		// next, split 56bit key in half - 2x 28bit arrays
		int[] K1 = Arrays.copyOfRange(key56, 0, 33); // split the block into its
		int[] K2 = Arrays.copyOfRange(key56, 33, 64); // left & right components
		
		// shift each half
		K1 = shift(K1, cycle);
		K2 = shift(K2, cycle);
		
		// recombine both halves, back into the 56bit key array
		for (int i=0; i<28; i++) key56[i] = K1[i];
		for (int i=0; i<28; i++) key56[i+28] = K2[i];
		
		// Permuted Choice 2 operation, to result a 48-bit subkey, which is returned
		int[] key48 = permutedChoice2(key56);
		return key48;
	}
	
	
	// splits the raw array of plaintext intbits into multiple 64-bit (8-byte) subarrays
	public static int[][] splitBlocks(int[] p){
		// determine total number of blocks needed
		int numBlocks = (int) Math.ceil(p.length / (double) 64);
		// create empty 2D array to hold blocks, which hold 64 bits each
		int[][] blocks = new int[numBlocks][64];
		
		for (int i = 0; i < blocks.length; i++){ // number of blocks
			blocks[i] = Arrays.copyOfRange(p, i*64, (i*64)+64); // copy an entire block from position 0-63, 64-127, etc.
		}
		return blocks;
	}
	
	
	
	/*
	 * Big Meaty Method - the Block Cipher
	 */
	public static int[] blockCipher(int[] block, int[] key, int cycle){
		int[] Li = Arrays.copyOfRange(block, 0, 33); // split the block into its
		int[] Ri = Arrays.copyOfRange(block, 33, 64); // left & right components
		
		// Begin Feistel Network
		// start with expansion permutation on Ri
		int[] RiExpanded = expansionPermute(Ri);
		
		// XOR expanded Ri with new 48-bit subkey Ki
		int[] Ki = subKey(key, cycle);
		
		int[] feis = new int[48];
		for (int i=0; i<48; i++){
			feis[i] = RiExpanded[i] ^ Ki[i];
		}
		
		
		// now for substitutions
		//int[] subst = substitute(feis);
		int[] subst = S[0];
		
		// perform final permutation in Feistel Network
		int[] newfeis = FeistelPermute(subst);
		
		
		//XOR Li with output of last permutation
		int[] Ri_plus1 = new int[32];
		
		int[] output = new int[32];
		for (int i=0; i<32; i++){
			Ri_plus1[i] = newfeis[i] ^ Li[i];
		}
		// this produces Ri+1
		
		// finally, recombine Ri+1 and Li+1 (which is just Ri) into cycleOutput intbit array
		int[] cycleOutput = new int[32];
		for (int i=0; i<32; i++) cycleOutput[i] = Ri[i]; // equals Li+1, which remember, is simply Ri
		for (int i=0; i<32; i++) cycleOutput[i+32] = Ri_plus1[i];
		
		return cycleOutput;
	}
	
	
	
	// performs Initial Permutation on a Block (64b)
	public static int[] initPermute(int[] block){
		int[] permInts = new int[64];
		
		for (int i=0; i<64; i++){
			permInts[i] = block[IP[i]-1]; // route permuted positions of block to sequential positions of permInts
		}
		return permInts;
	}
	
	
	// performs Expansion Permutation on 32b block half
	public static int[] expansionPermute(int[] Ri){
		int[] permInts = new int[48];
		
		for (int i=0; i<48; i++){
			permInts[i] = Ri[EP[i]-1]; // route permuted positions of 32b Ri to sequential positions of 48b permInts
		}
		return permInts;
	}
	
	
	// performs Permuted Choice 2 on a 56b shifted key
	public static int[] permutedChoice2(int[] key){
		int[] permInts = new int[48];
		
		for (int i=0; i<48; i++){
			permInts[i] = key[PC2[i]-1]; // route PC2 positions of 56b key to sequential positions of 48b permInts
		}
		return permInts;
	}
	
	
	
	
	// performs bit shifting on 28-bit key halves
	public static int[] shift(int[] key, int cycle){
		int[] table = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
		int firstBit = key[0]; // store first bit to put back at end of array
		int secondBit = key[1]; // also second, in case the shift is 2 bits
		
		int[] newHalf = new int[28];
		for (int i=0; i<26; i++){
			newHalf[i] = key[i+table[cycle]];
		}
		// then, add missing bit (or two) according to cycle
		if (table[cycle] == 1){
			newHalf[26] = key[27];
			newHalf[27] = firstBit;
		}else{
			newHalf[26] = firstBit;
			newHalf[27] = secondBit;
		}
		return newHalf;
	}
	
	
	
	// performs substitution (S-boxes) on Feistel 48bit number
//	public static int[] substitute(int[] nums){
//		
//	}
	
	
	
	// performs permutation on 32bit Feistel number, after S-boxes
	public static int[] FeistelPermute(int[] nums){
		int[] permInts = new int[32];
		
		for (int i=0; i<32; i++){
			permInts[i] = nums[FeiP[i]-1]; // route Feistel Permute positions of 32b intbit array to positions of 32b permInts
		}
		return permInts;
	}
	
	
	// performs final permutation
	public static int[] finalPermute(int[] nums){
		int[] permInts = new int[64];
		
		for (int i=0; i<64; i++){
			permInts[i] = nums[FP[i]-1]; // route Final Permutation positions of 64b cipher block to positions of 64b permInts
		}
		return permInts;
	}
	
	
	
	
	/*
	 * Hardcoded P-Boxes & S-Boxes (Permutation and Substitution tables)
	 */
	
	
	// Initial Permutation (IP)
	public static int[] IP = {
			58, 50, 42, 34, 26, 18, 10, 2,
			60, 52, 44, 36, 28, 20, 12, 4,
			62, 54, 46, 38, 30, 22, 14, 6,
			64, 56, 48, 40, 32, 24, 16, 8,
			57, 49, 41, 33, 25, 17, 9,  1,
			59, 51, 43, 35, 27, 19, 11, 3,
			61, 53, 45, 37, 29, 21, 13, 5,
			63, 55, 47, 39, 31, 23, 15, 7
	};
	
	
	// Expansion Permutation (EP)
	public static int[] EP = {
			32, 1,  2,  3,  4,  5,
			4,  5,  6,  7,  8,  9,
			8,  9,  10, 11, 12, 13,
			12, 13, 14, 15, 16, 17,
			16, 17, 18, 19, 20, 21,
			20, 21, 22, 23, 24, 25,
			24, 25, 26, 27, 28, 29,
			28, 29, 30, 31, 32, 1
	};
	
	
	// Permuted Choice 1 (PC1)
	
	
	
	// Permuted Choice 2 (PC2)
	public static int[] PC2 = {
			14, 17, 11, 24, 1,  5,
			3,  28, 15, 6,  21, 10,
			23, 19, 12, 4,  26, 8,
			16, 7,  27, 20, 13, 2,
			41, 52, 31, 37, 47, 55,
			30, 40, 51, 45, 33, 48,
			44, 49, 39, 56, 34, 53,
			46, 42, 50, 36, 29, 32
	};
	
	
	// Feistel Permutation (FeiP)
	public static int[] FeiP = {
			16, 7,  20, 21,
			29, 12, 28, 17,
			1,  15, 23, 26,
			5,  18, 31, 10,
			2,  8,  24, 14,
			32, 27, 3,  9,
			19, 13, 30, 6,
			22, 11, 4,  25
	};
	
	
	// the almighty S-boxes
	public static int[][] S = {{
		14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7,
		0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8,
		4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0,
		15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13
	},{
		15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10,
		3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5,
		0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15,
		13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9
	},{
		10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8,
		13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1,
		13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7,
		1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12
	},{
		7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15,
		13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9,
		10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4,
		3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14
	},{
		2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9,
		14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6,
		4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14,
		11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3
	},{
		12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11,
		10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8,
		9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6,
		4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13
	},{
		4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1,
		13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6,
		1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2,
		6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12
	},{
		13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7,
		1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2,
		7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8,
		2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11
	}};
	
	
	// Final Permutation (FP)
	public static int[] FP = {
			40, 8, 48, 16, 56, 24, 64, 32,
			39, 7, 47, 15, 55, 23, 63, 31,
			38, 6, 46, 14, 54, 22, 62, 30,
			37, 5, 45, 13, 53, 21, 61, 29,
			36, 4, 44, 12, 52, 20, 60, 28,
			35, 3, 43, 11, 51, 19, 59, 27,
			34, 2, 42, 10, 50, 18, 58, 26,
			33, 1, 41, 9,  49, 17, 57, 25
	};
}
