/*
 * John Chaney
 * Assignment 7
 * 3/7/13
 */
import java.util.Scanner;
public class ChaneyA7 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		// get size of array
		System.out.println("How many rows should the array have?: ");
		int rows = reader.nextInt();
		System.out.println("How many columns should it have?: ");
		int cols = reader.nextInt();
		
		int[][] array = new int[rows][cols];
		
		// first let's fill it with user input
		System.out.println("We will now fill it one row at a time.");
		for (int i=0;i<array.length;i++){
			for (int j=0;j<array[0].length;j++){
				System.out.print("Enter an integer: ");
				array[i][j] = reader.nextInt();
			}
			System.out.println("Row complete.");
		}
		
		
		// bubble sortin time
		// utilizing (sort of) the same format of the algorithm
		// that was used in my project 2
		// with help from Aaron's tips and example
		boolean swapped = true;
		
		while (swapped){
			swapped=false;
			for (int i=0;i<array.length;i++){
				for (int j=1;j<array[i].length;j++){
					
					if (i==array.length-1 && j==array[i].length-1){
						// nothin lol
					}else if (j==array[i].length-1){
						if (array[i][j] > array[i+1][0]){
							int temp = array[i][j];
							array[i][j] = array[i+1][0];
							array[i+1][0] = temp;
							swapped = true;
						}
					}if (array[i][j] < array[i][j-1]){
						int temp = array[i][j];
						array[i][j] = array[i][j-1];
						array[i][j-1] = temp;
						swapped = true;
					}
				}
			}
		}
		
		
		// print it
		System.out.println("\n\n\nSorted array: ");
		for (int i=0;i<array.length;i++){
			for (int j=0;j<array[i].length;j++){
				System.out.printf("%d ",array[i][j]);
			}
			System.out.printf("\n");
		}
	}

}