/*
 * John Chaney
 * Project 3: Game
 * CS 110 4/12/13 Spring 2013
 * Bonus: nope
 */
import java.io.*;
import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		final int ROWS = 6;
		final int COLS = 7;
		String player1, player2;
		Scanner in = new Scanner(System.in);
		MethodsAndSuch methodz = new MethodsAndSuch();
		
		// make ze board
		// this is the grid that makes up the board
		char[][] grid = new char[ROWS][COLS];
		
		
		// before ANYTHING else happens...we must see if they have a previous game saved
		int option;
		do{
			System.out.println("Welcome to Connect Four!");
			System.out.println("------------------------");
			System.out.println("1. New Game");
			System.out.println("2. Resume saved game");
			System.out.print(":>");
			while (!in.hasNextInt()){
				System.out.println("Please enter either 1 or 2.");
				in.next();
			}
			option = in.nextInt();
		}while ((option != 1) && (option != 2));
		if (option == 1){ // new game
			// fill it with spaces
			for (int i=0;i<ROWS;i++){
				for (int j=0;j<COLS;j++){
					grid[i][j] = ' ';
				}
			}
			//ask for dey names
			System.out.println("Please enter player 1's name: ");
			in.nextLine(); // still can't figure out for the life of me why I need to put two of these
			player1=in.nextLine(); // to avoid skipping input. But I will sate the Java gods nevertheless
			System.out.println("Please enter player 2's name: ");
			player2=in.nextLine();
		}else{ // resume game
			BufferedReader reader = new BufferedReader(new FileReader(methodz.resume()));
			player1=reader.readLine();
			player2=reader.readLine();
			for (int i=0;i<ROWS;i++){
				for (int j=0;j<COLS;j++){
					grid[i][j] = (char)reader.read();
				}
				reader.readLine(); // skips that pesky newline
			}
			System.out.println("Welcome back, "+player1+" and "+player2+"! Your game will now resume.");
			reader.close();
		}
		
		System.out.println("Just to be clear, "+player1+"'s pieces are the X's, and "+player2+"'s pieces are the O's.");
		// show initial b0arde
		methodz.printGrid(grid);
		
		
		// main loop!
		char currentPlayer;
		int cntr=0;
		while (!methodz.isFull(grid)){
			// this if block assigns the piece of the current player
			// based on the number of the current move
			if (cntr % 2 == 0) currentPlayer = 'X';
			else currentPlayer = 'O';
			
			System.out.print("Player of piece "+currentPlayer+"'s turn. ");
			// below line inputs the move, as well as determines whether we should end & save the game,
			// or if there's a winner
			String result = methodz.inputMove(grid, currentPlayer);
			
			// before printing the board again, check if they're tryna save
			if (result.equals("save")){
				methodz.save(grid, player1, player2);
			}
			
			methodz.printGrid(grid);
			
			if (result.equals("win")){
				if (currentPlayer == 'X'){
					System.out.println("Oh snap! "+player1+" is the winner!");
				}else{
					System.out.println("Oh snap! "+player2+" is the winner!");
				}
				System.out.println("Thankz for playin yo.");
				System.exit(0);
			}
			cntr++;
		}
	}

}
