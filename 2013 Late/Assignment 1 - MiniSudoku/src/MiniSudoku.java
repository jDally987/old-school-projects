/*
 * John Chaney 800016133
 * CS 111 Tu/Th 2:00 class
 * Large Scale Programming Assignment 1
 * 10/1/13
 * please see the disclaimer in the readPuzzleFromFile method below.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MiniSudoku {

	private static int size = 0;


	public static void main(String[] args) {
		// checks for the 9x9 case; if not, nothing changes, puzzle remains 6x6
		if (is9x9()) size = 9;
		int puzzle[][] = new int[size][size];
		
		try {
			readPuzzleFromFile(puzzle, size);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		solve(puzzle);
		// print out final solution
		for (int i=0;i<size;i++){
			for (int j=0;j<size;j++){
				System.out.print(puzzle[i][j]);
			}
			System.out.println("");
		}
	}

	
	/**
	 * Check if the puzzle is a 9x9, based on user input
	 * Precond: none
	 * Postcond: either puzzle is a 9x9, or it's not
	 * @return true if user specifies a 9x9 puzzle
	 */
	public static boolean is9x9(){
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter the puzzle's size: ");
		System.out.println("1. 6x6");
		System.out.println("2. 9x9");
		int choice = 0;
		try{
			choice = reader.nextInt();
		}catch(NumberFormatException e){
			System.err.println("Not a number; please try again.");
			is9x9();
		}
		if (choice == 2) return true;
		else return false;
	}
	
	
	/**
	 * reads the sudoku puzzle into the matrix from puzzle.txt
	 * Precond: valid txt file with correct puzzle format; int size (that is either 6 or 9); valid int matrix that is same size as the int size, and the puzzle from the text file
	 * Postcond: the array is filled correctly with the puzzle from txt file
	 * @param puz - the matrix from the main method
	 * @param size - size of each side of matrix/puzzle
	 * @throws FileNotFoundException
	 */
	public static void readPuzzleFromFile(int[][] puz, int size) throws FileNotFoundException{
		// DISCLAIMER: for some reason, whenever I run the program, it won't find puzzle.txt. I can't
		// figure out why; whether I put the file in the root directory or src, it'll either terminate
		// with a FileNotFoundException and say nothing, or do the same thing but print out the error. So
		// because of that, the program won't run, and I tried asking Nithin but he hasn't emailed me back
		// yet. If you can get it to work, great, but it won't work for me and I just can't see why.
		File file = new File("puzzle.txt");
		Scanner in = new Scanner(file);
		for (int i=0;i<size;i++){
			for (int j=0;j<size;j++){
				puz[i][j] = in.nextInt();
			}
		}
		in.close();
	}
	
	
	/**
	 * Makes sure the current move, specified by the parameters, is ok and in an empty space
	 * Precond: valid puzzle matrix; row, column and number less than global variable size
	 * Postcond: move is valid or not
	 * @param puz
	 * @param r
	 * @param c
	 * @param num
	 * @return true if the move's proposed space is empty (value of 0)
	 */
	public static boolean moveOK(int[][] puz, int r, int c, int num){
		for (int i=0;i<size;i++){
			for (int j=0;j<size;j++){
				if (puz[i][j] != 0) return false;
			}
		}
		return true;
	}
	
	
	/**
	 * The main method for solving the sudoku puzzle stored in the puzzle matrix
	 * Precond: puz is the valid puzzle matrix read in from puzzle.txt
	 * Postcond: a fully solved & filled sudoku matrix (at the end); postcond at end of each move is simply the success of that individual move
	 * @param puz, the matrix containing the puzzle
	 * @return true if the current move is successful. Uses recursion so each move's success depends on the success of the next iteration of the method, and so on
	 */
	public static boolean solve(int[][] puz){
		int row=0, col=0;
		boolean fullySolved = false;
		
		// loop through array for first empty space to determine row/col for this move
		for (int i=0;i<size;i++){
			for (int j=0;j<size;j++){
				if (puz[i][j] == 0){
					row = i;
					col = j;
					break;
				}
			}
			if (i==size) return true; // base case - no open spaces were found; board is solved
		}
		
		for (int num=0;num<size;num++){
			if (moveOK(puz, row, col, num)){
				puz[row][col] = num;
				if (solve(puz)) return true;
				puz[row][col] = 0; // resets space to empty if the previous line isn't true...
			}
		}
		return false; // ...and then returns false because it didn't work. Then backtracking occurs
	}
}
