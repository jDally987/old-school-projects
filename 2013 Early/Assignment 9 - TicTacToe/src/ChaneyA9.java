/*
 * John Chaney
 * Assignment 9
 * 4/4/13
 */
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChaneyA9 {
	public static void main(String[] args) {
		// this is the char array that will store all X's/O's (or blanks)
		char[][] grid = new char[3][3];
		// we also gotta fill it with spaces
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				grid[i][j] = ' ';
			}
		}
		
		// print b0arde
		printGrid(grid);
		
		
		// whoosh time to do the actual game part.
		char currentPlayer;
		String winrar = "Tie"; // winner (to be used in/after while loop)
		int cntr = 0;
		
		while (cntr<9){
			// this if block assigns the piece of the current player
			// based on the number of the current move
			if (cntr % 2 == 0) currentPlayer = 'X';
			else currentPlayer = 'O';
			
			// get a move from user, store it in the grid, get temporary winner value
			winrar = inputMove(grid,currentPlayer);
			
			printGrid(grid);
			
			// check, for each turn, if someone has indeed won
			if (!winrar.equals("Tie")){
				break;
			}
			cntr++;
		}
		
		if (winrar.equals("Tie")){
			System.out.println("Oh snap! It's a tie!");
		}else{
			System.out.println("Looks like the winner is " + winrar + "!");
		}
		
		final String FILE = "Record.txt";
		try {
			PrintWriter write = new PrintWriter(new FileWriter(FILE,true));
			if (winrar.equals("X")){
				write.println("Player 1");
			}else if (winrar.equals("O")){
				write.println("Player 2");
			}else{
				write.println("Tie");
			}
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	// the method that prints the grid. wow
	// takes the 'grid' 2D array as param
	public static void printGrid(char[][] grid){
		System.out.println(grid[0][0] + "|" + grid[0][1] + "|" + grid[0][2]);
		System.out.println("-----");
		System.out.println(grid[1][0] + "|" + grid[1][1] + "|" + grid[1][2]);
		System.out.println("-----");
		System.out.println(grid[2][0] + "|" + grid[2][1] + "|" + grid[2][2]);
	}
	
	
	// takes input from user, checks if it's valid, then makes tha move
	// also checks each move for a win. WINRAR
	public static String inputMove(char[][] grid, char currentPlayer){
		Scanner reader = new Scanner(System.in);
                String win = "Tie";
		System.out.print("Player of piece " + currentPlayer + ", maketh thy move (r c): ");
		
		int r=reader.nextInt(); int c=reader.nextInt();
		if ((r<=2) && (c<=2) && (grid[r][c] ==' ')){
			grid[r][c] = currentPlayer;
		}else{
                        System.out.println("Invalid move; try again.");
			inputMove(grid,currentPlayer);
		}
                
                // tiem to check for winrars.
                // check across columns
                for (int i=0;i<3;i++){
                    if (grid[r][i] != currentPlayer){
                        break;
                    }
                    if (i == 2){
                        win = Character.toString(currentPlayer);
                    }
                }
                
                // check across rows
                for (int i=0;i<3;i++){
                    if (grid[i][c] != currentPlayer){
                        break;
                    }
                    if (i == 2){
                        win = Character.toString(currentPlayer);
                    }
                }
                
                // cheque diagonalz
                for (int i=0;i<3;i++){
                    if (grid[i][i] != currentPlayer){
                        break;
                    }
                    if (i == 2){
                        win = Character.toString(currentPlayer);
                    }
                }
                
                // don't forget the other diagonal
                for (int i=0;i<3;i++){
                    if (grid[i][2-i] != currentPlayer){
                        break;
                    }
                    if (i == 2){
                        win = Character.toString(currentPlayer);
                    }
                }
                
                return win;
	}
	
}