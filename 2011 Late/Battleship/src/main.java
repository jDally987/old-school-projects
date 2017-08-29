// created date: October 28, 2011 at 1:15:42 PM

import java.util.Random;
import java.util.Scanner;

public class main {
	
	// create n fill boards.
	int[][] playerBoard = new int[10][10];
	int[][] AIBoard = new int[10][10];
	
	// show player's board.
	public void printPlayerBoard()
	{
		System.out.println("Right now, your board looks like this.\n\n  0123456789\n  ----------");
		for (int i=0; i<10; i++)
		{
			System.out.print(i+ "|");
			for (int j=0; j<10; j++)
			{
				System.out.print(playerBoard[i][j]);
				if (j==9)
				{
					System.out.println("");
				}
			}
			
		}
	}
	
	
	// automatically place pieces on board
	public void autoPopulate()
	{
		// make dat randomizer now
		Random randomizer = new Random();
		int x, y;
		
		// populate destroyer
		for (int i=0; i<2; i++)
		{
			// place ship either vertically or horizontally, randomly
			boolean vertOrHoriz = randomizer.nextBoolean();
			if (vertOrHoriz == true)
			{
				x = randomizer.nextInt(8)+i;
				y = randomizer.nextInt(9);
			}else{
				x = randomizer.nextInt(9);
				y = randomizer.nextInt(8)+i;
			}
			
			// checkin' to see if that space is already taken. I know this isn't perfect due to the fact
			// that if a long ship, such as the carrier, goes through numerous iterations of the for loop
			// and then finds out on, say, the 4th iteration, that the chosen point conflicts with
			// an existing one, and needs to find a new point, it will simply jump to a different
			// point on the map, leaving behind half of an already-built ship. But, I honestly
			// cannot think of any way to correct this problem.....but hey I tried. A lot actually
			while (playerBoard[x][y] != 0)
			{
				if (vertOrHoriz == true)
				{
					x = randomizer.nextInt(8)+i;
					y = randomizer.nextInt(9);
				}else{
					x = randomizer.nextInt(9);
					y = randomizer.nextInt(8)+i;
				}
				
			}
			playerBoard[x][y] = 1;
		}
		
		// populate submarine
		for (int i=0; i<3; i++)
		{
			boolean vertOrHoriz = randomizer.nextBoolean();
			if (vertOrHoriz == true)
			{
				x = randomizer.nextInt(7)+i;
				y = randomizer.nextInt(9);
			}else{
				x = randomizer.nextInt(9);
				y = randomizer.nextInt(7)+i;
			}
			
			while (playerBoard[x][y] != 0)
			{
				if (vertOrHoriz == true)
				{
					x = randomizer.nextInt(9);
					y = randomizer.nextInt(7)+i;
				}else{
					x = randomizer.nextInt(7)+i;
					y = randomizer.nextInt(9);
				}
				
			}
			playerBoard[x][y] = 2;
		}
		
		// populate cruiser
		for (int i=0; i<3; i++)
		{
			boolean vertOrHoriz = randomizer.nextBoolean();
			if (vertOrHoriz == true)
			{
				x = randomizer.nextInt(7)+i;
				y = randomizer.nextInt(9);
			}else{
				x = randomizer.nextInt(9);
				y = randomizer.nextInt(7)+i;
			}
			
			while (playerBoard[x][y] != 0)
			{
				if (vertOrHoriz == true)
				{
					x = randomizer.nextInt(9);
					y = randomizer.nextInt(7)+i;
				}else{
					x = randomizer.nextInt(7)+i;
					y = randomizer.nextInt(9);
				}
				
			}
			playerBoard[x][y] = 3;
		}
		
		// populate battleship
		for (int i=0; i<4; i++)
		{
			boolean vertOrHoriz = randomizer.nextBoolean();
			if (vertOrHoriz == true)
			{
				x = randomizer.nextInt(6)+i;
				y = randomizer.nextInt(9);
			}else{
				x = randomizer.nextInt(9);
				y = randomizer.nextInt(6)+i;
			}
			
			while (playerBoard[x][y] != 0)
			{
				if (vertOrHoriz == true)
				{
					x = randomizer.nextInt(9);
					y = randomizer.nextInt(6)+i;
				}else{
					x = randomizer.nextInt(6)+i;
					y = randomizer.nextInt(9);
				}
				
			}
			playerBoard[x][y] = 4;
		}
		
		// populate carrier
		for (int i=0; i<5; i++)
		{
			boolean vertOrHoriz = randomizer.nextBoolean();
			if (vertOrHoriz == true)
			{
				x = randomizer.nextInt(5)+i;
				y = randomizer.nextInt(9);
			}else{
				x = randomizer.nextInt(9);
				y = randomizer.nextInt(5)+i;
			}
			
			while (playerBoard[x][y] != 0)
			{
				if (vertOrHoriz == true)
				{
					x = randomizer.nextInt(9);
					y = randomizer.nextInt(5)+i;
				}else{
					x = randomizer.nextInt(5)+i;
					y = randomizer.nextInt(9);
				}
				
			}
			playerBoard[x][y] = 5;
		}
	}
	
	
	// now, autopopulate the COMPUTER's board
	public void autoPopulateAI()
	{
		// make dat randomizer now
		Random randomizer = new Random();
		int x, y;
		
		// populate destroyer
		for (int i=0; i<2; i++)
		{
			// place ship either vertically or horizontally, randomly
			boolean vertOrHoriz = randomizer.nextBoolean();
			if (vertOrHoriz == true)
			{
				x = randomizer.nextInt(8)+i;
				y = randomizer.nextInt(9);
			}else{
				x = randomizer.nextInt(9);
				y = randomizer.nextInt(8)+i;
			}
			
			// checkin' to see if that space is already taken. I know this isn't perfect due to the fact
			// that if a long ship, such as the carrier, goes through numerous iterations of the for loop
			// and then finds out on, say, the 4th iteration, that the chosen point conflicts with
			// an existing one, and needs to find a new point, it will simply jump to a different
			// point on the map, leaving behind half of an already-built ship. But, I honestly
			// cannot think of any way to correct this problem.....but hey I tried. A lot actually
			while (playerBoard[x][y] != 0)
			{
				if (vertOrHoriz == true)
				{
					x = randomizer.nextInt(8)+i;
					y = randomizer.nextInt(9);
				}else{
					x = randomizer.nextInt(9);
					y = randomizer.nextInt(8)+i;
				}
				
			}
			AIBoard[x][y] = 1;
		}
		
		// populate submarine
		for (int i=0; i<3; i++)
		{
			boolean vertOrHoriz = randomizer.nextBoolean();
			if (vertOrHoriz == true)
			{
				x = randomizer.nextInt(7)+i;
				y = randomizer.nextInt(9);
			}else{
				x = randomizer.nextInt(9);
				y = randomizer.nextInt(7)+i;
			}
			
			while (playerBoard[x][y] != 0)
			{
				if (vertOrHoriz == true)
				{
					x = randomizer.nextInt(9);
					y = randomizer.nextInt(7)+i;
				}else{
					x = randomizer.nextInt(7)+i;
					y = randomizer.nextInt(9);
				}
				
			}
			AIBoard[x][y] = 2;
		}
		
		// populate cruiser
		for (int i=0; i<3; i++)
		{
			boolean vertOrHoriz = randomizer.nextBoolean();
			if (vertOrHoriz == true)
			{
				x = randomizer.nextInt(7)+i;
				y = randomizer.nextInt(9);
			}else{
				x = randomizer.nextInt(9);
				y = randomizer.nextInt(7)+i;
			}
			
			while (playerBoard[x][y] != 0)
			{
				if (vertOrHoriz == true)
				{
					x = randomizer.nextInt(9);
					y = randomizer.nextInt(7)+i;
				}else{
					x = randomizer.nextInt(7)+i;
					y = randomizer.nextInt(9);
				}
				
			}
			AIBoard[x][y] = 3;
		}
		
		// populate battleship
		for (int i=0; i<4; i++)
		{
			boolean vertOrHoriz = randomizer.nextBoolean();
			if (vertOrHoriz == true)
			{
				x = randomizer.nextInt(6)+i;
				y = randomizer.nextInt(9);
			}else{
				x = randomizer.nextInt(9);
				y = randomizer.nextInt(6)+i;
			}
			
			while (playerBoard[x][y] != 0)
			{
				if (vertOrHoriz == true)
				{
					x = randomizer.nextInt(9);
					y = randomizer.nextInt(6)+i;
				}else{
					x = randomizer.nextInt(6)+i;
					y = randomizer.nextInt(9);
				}
				
			}
			AIBoard[x][y] = 4;
		}
		
		// populate carrier
		for (int i=0; i<5; i++)
		{
			boolean vertOrHoriz = randomizer.nextBoolean();
			if (vertOrHoriz == true)
			{
				x = randomizer.nextInt(5)+i;
				y = randomizer.nextInt(9);
			}else{
				x = randomizer.nextInt(9);
				y = randomizer.nextInt(5)+i;
			}
			
			while (playerBoard[x][y] != 0)
			{
				if (vertOrHoriz == true)
				{
					x = randomizer.nextInt(9);
					y = randomizer.nextInt(5)+i;
				}else{
					x = randomizer.nextInt(5)+i;
					y = randomizer.nextInt(9);
				}
				
			}
			AIBoard[x][y] = 5;
		}
	}
	
	
	// now make methods to execute both player's and computer's turns and return whether or not it was a hit
	public boolean playerTurn()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the x coordinate of the point you wish to attack on the computer's board: ");
		int x = scanner.nextInt();
		System.out.print("Now enter the y coordinate: ");
		int y = scanner.nextInt();
		if (AIBoard[x][y] != 0)
		{
			System.out.println("Good freaking job, you got a hit!");
			AIBoard[x][y] = -1;
			return true;
		}else{
			System.out.println("Oops, that was a miss. Try a different point next time.");
			return false;
		}
	}
	
	public boolean AITurn()
	{
		Random randomizer = new Random();
		
		System.out.println("It is now the computer's turn...");
		int x = randomizer.nextInt(9);
		int y = randomizer.nextInt(9);
		if (playerBoard[x][y] != 0)
		{
			System.out.println("Oh my goodness, the computer hit one of your ships! Prepare to dieeee!");
			return true;
		}else{
			System.out.println("The computer fired.....and missed.");
			return false;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// make a constructor (I think this is a constructor?) for a main object, since for some reason
		// it won't let me type this.WhateverMethodImTryingToUse
		// (cannot be used in a static context...whatever that means)
		main main = new main();
		// make a scanner while we're at it
		Scanner scanner = new Scanner(System.in);
		
		// let the games begin
		System.out.println("Welcome to the magnificent and very dated game of BATTLESHIP!");
		System.out.println("Now automatically placing your ships, to save you valuable time...");
		main.autoPopulate();
		main.printPlayerBoard();
		System.out.println("Done. Each number refers to one of the ship types, going from smallest ship size to largest - i.e. the 1's are the destroyer, and the 5's are the aircraft carrier.");
		
		// now just carry out the game's turns
		int playerShipsLeft = 17, AIShipsLeft = 17;
		while (playerShipsLeft > 0 && AIShipsLeft > 0)
		{
			if (main.playerTurn() == true)
			{
				AIShipsLeft -= 1;
			}
			
			if (main.AITurn() == true)
			{
				playerShipsLeft -= 1;
			}
		}
		
		// announce tha winnerrrrr when one person's ships are all at the bottom of the pacific ocean
		if (playerShipsLeft == 0)
		{
			System.out.println("Oh no, you lost! Wow....you lost to a horribly coded AI robot. You must be the worst guesser in the world.");
			System.exit(0);
		}
		if (AIShipsLeft == 0)
		{
			System.out.println("Good job, you won! Give yourself a pat on the back big boy.");
			System.exit(0);
		}
	}

}
