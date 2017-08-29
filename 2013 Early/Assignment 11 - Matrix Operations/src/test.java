import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class test {
	
	
	
	public static void main(String [] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		int r=2;
		int c=3;
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
		
	}
	
}
