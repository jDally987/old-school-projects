/*
 * John Chaney
 * Assignment 11
 * 4/25/13
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Main {
	
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		
		// I don't see the point in asking the user for the dimensions when this could be done
		// in the makeMatrix method, checking the number of rows and cols actually in the matrix
		// in the file, but it's what the prompt said so here it is
		System.out.print("How many rows does the matrix have?: ");
		int rows = reader.nextInt();
		System.out.print("How many columns?: ");
		int cols = reader.nextInt();
		
		// maek matrix objeckts
		Matrix matrix1 = makeMatrix(rows, cols);
		Matrix matrix2 = makeMatrix(rows, cols);
		
		// add
		Matrix addMatrix = matrix1.addMatrix(matrix2);
		
		// maek 3rd matrix
		Matrix matrix3 = makeMatrix(rows, cols);
		
		// subtrackt
		Matrix subtractMatrix = matrix1.subtractMatrix(matrix3);
		
		
		FileWriter writer = new FileWriter(new File("Chaney-output.txt"));
		// decided to be spontaneous and put all 3 matrices into an object array
		// just cuz
		Object[] matrices = {matrix1.getMatrix(), addMatrix.getMatrix(), subtractMatrix.getMatrix()};
		for (Object m : matrices){
			int[][] matrix = (int[][]) m;
			for (int i=0;i<rows;i++){
				for (int j=0;j<cols;j++){
					writer.write(String.valueOf(matrix[i][j]));
					writer.write(" ");
				}
				writer.write(System.getProperty("line.separator"));
			}
			writer.write(System.getProperty("line.separator"));
		}
		writer.close();
	}
	
	
	// I'm also confused as to why I must ask the user for a filename each time. I thought
	// we were supposed to store all 3 matrices in one output file at the end?
	// But I guess that's separate, so I've stored each matrix in a different input file
	public static Matrix makeMatrix(int r, int c) throws IOException{
		Scanner sc = new Scanner(System.in);
		int[][] m = new int[r][c];
		
		System.out.print("Please enter the filename for tha matrix (not including \".txt\"): ");
		File file = new File(sc.next() + ".txt");
		Scanner reader = new Scanner(file);
		
		for (int i=0;i<r;i++){
			for (int j=0;j<c;j++){
				m[i][j] = reader.nextInt();
			}
			reader.nextLine(); // skips that pesky newline
		}
		reader.close();
		
		Matrix newMatrix = new Matrix(r,c,m);
		return newMatrix;
	}
}
