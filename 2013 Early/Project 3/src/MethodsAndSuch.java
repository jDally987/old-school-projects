import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class MethodsAndSuch {
	Scanner in = new Scanner(System.in);
	
	// resume gaem
	public File resume(){
		File file;
		do{
			System.out.println("Please enter the name of the file you wish to load (not including .txt)");
			System.out.println("(If you are prompted again, that means your filename was invalid. Try again)");
			System.out.print(":>");
			file = new File(in.next() + ".txt");
		}while(!file.isFile());
		return file;
	}
	
	
	// print the board
	// could've been done with a loop but I implemented something similar with my tic-tac-toe
	// so ya know, why not
	public void printGrid(char[][] grid){
		System.out.println(grid[0][0] + "|" + grid[0][1] + "|" + grid[0][2] + "|" + grid[0][3] + "|" + grid[0][4] + "|" + grid[0][5] + "|" + grid[0][6]);
		System.out.println("-------------");
		System.out.println(grid[1][0] + "|" + grid[1][1] + "|" + grid[1][2] + "|" + grid[1][3] + "|" + grid[1][4] + "|" + grid[1][5] + "|" + grid[1][6]);
		System.out.println("-------------");
		System.out.println(grid[2][0] + "|" + grid[2][1] + "|" + grid[2][2] + "|" + grid[2][3] + "|" + grid[2][4] + "|" + grid[2][5] + "|" + grid[2][6]);
		System.out.println("-------------");
		System.out.println(grid[3][0] + "|" + grid[3][1] + "|" + grid[3][2] + "|" + grid[3][3] + "|" + grid[3][4] + "|" + grid[3][5] + "|" + grid[3][6]);
		System.out.println("-------------");
		System.out.println(grid[4][0] + "|" + grid[4][1] + "|" + grid[4][2] + "|" + grid[4][3] + "|" + grid[4][4] + "|" + grid[4][5] + "|" + grid[4][6]);
		System.out.println("-------------");
		System.out.println(grid[5][0] + "|" + grid[5][1] + "|" + grid[5][2] + "|" + grid[5][3] + "|" + grid[5][4] + "|" + grid[5][5] + "|" + grid[5][6]);
	}
	
	
	// a quick little one to check if the board's full or empty
	public boolean isFull(char[][] grid){
		boolean full = true;
		for (int i=0;i<grid.length;i++){
			for (int j=0;j<grid[i].length;j++){
				if (grid[i][j] == ' '){
					full = false;
					break;
				}
			}
		}
		return full;
	}
	
	// the big momma: inputting each move
	public String inputMove(char[][] grid, char cur){
		String returnValue="";
		int move=0;
		
		System.out.println("Enter the column into which you wish to drop your piece (1-7), or alternatively, type any key if you'd like to quit: ");
		// the lengthy process of idiot-proofing the input
		if (in.hasNextInt()){ // they entered a number, thank god
			move = in.nextInt();
			
			if ((move>=1) && (move<=7)){
				for (int i=5;i>=0;i--){
					if (grid[i][move-1] == ' '){
						grid[i][move-1] = cur;
						break;
					}
					if (i==0){
						System.out.println("That column is full. Try a different one.");
						inputMove(grid, cur);
					}
				}
			}else{
				System.out.println("Invalid move, try again pls.");
				inputMove(grid, cur);
			}
		}else{ // they entered a letter
			System.out.print("Wouldst thou like to save before quitting? (y/n): ");
			in.next(); // once again, I don't know why; but I shall not question
			if (in.next().equalsIgnoreCase("y")){
				returnValue = "save";
			}else{
				System.out.println("Alright fine whatever, well thanks for playing!");
				System.exit(0);
			}
		}
		
		// oh my goodness. time to check for winners
		if (chequeWin(grid, cur)) returnValue = "win";
		
		return returnValue;
	}
	
	
	
	// win checkin' method
	public boolean chequeWin(char[][] grid, char cur){
		// this will be a doozy
		boolean win = false;
		
		// cheque horizontals.
		for (int i=0;i<6;i++){
			for (int j=0;j<4;j++){
				if (grid[i][j] == cur){
					for (int c=0;c<4;c++){
						if (grid[i][j+c] != cur){
							break;
						}if (c==3){
							win = true;
						}
					}
				}
			}
		}
		
		
		// cheque verticals.
		for (int i=0;i<3;i++){
			for (int j=0;j<7;j++){
				if (grid[i][j] == cur){
					for (int c=0;c<4;c++){
						if (grid[i+c][j] != cur){
							break;
						}if (c==3){
							win = true;
						}
					}
				}
			}
		}
		
		
		// cheque diagonals.
		for (int i=0;i<3;i++){
			for (int j=0;j<4;j++){
				if (grid[i][j] != ' ' && grid[i+1][j+1] == grid[i][j] && grid[i+2][j+2] == grid[i][j] && grid[i+3][j+3] == grid[i][j]){
					win = true;
				}
			}
		}
		
		
		// cheque diagonals in other direction too.
		for (int i=0;i<3;i++){
			for (int j=3;j<7;j++){
				if (grid[i][j] != ' ' && grid[i+1][j-1] == grid[i][j] && grid[i+2][j-2] == grid[i][j] && grid[i+3][j-3] == grid[i][j]){
					win = true;
				}
			}
		}
		
		return win;
	}
	
	
	// save the gaeme
	public void save(char[][] grid, String p1, String p2) throws IOException{
		System.out.println("What should the name of the file be? (not including .txt): ");
		File file = new File(in.next() + ".txt");
		FileWriter fw = new FileWriter(file);
		
		// put both names at the top
		fw.write(p1+System.getProperty("line.separator"));
		fw.write(p2+System.getProperty("line.separator"));
		// arrange each element in the txt file just as it would appear in the array
		for (int i=0;i<6;i++){
			for (int j=0;j<7;j++){
				fw.write(grid[i][j]);
			}
			fw.write(System.getProperty("line.separator"));
		}
		fw.close();
		
		System.out.println("Game saved. Thanks for playan! See you next time.");
		System.exit(0);
	}
}
