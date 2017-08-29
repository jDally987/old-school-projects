import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Friend Object class
 */

public class Friend {
	private String friendName;s
	private boolean owner = false;
	
	public Friend(String name, boolean root) throws IOException {
		// this entire constructor handles initialization of a new friend
		// including checking for an existing friend of same name, and then adding them to friends.txt
		friendName = name;
		
		File file = new File("friends.txt");
		Scanner friendscanner = new Scanner(file);
		
		// first must check for duplicate friends
		boolean friendExists = false;
		while (friendscanner.hasNextLine()){
			if (friendscanner.nextLine().equalsIgnoreCase(friendName)) friendExists = true;
		}
		
		if (!friendExists){
			// now we can finally write the new friend to friends.txt file
			FileWriter write;
			// check if root user; if so, we need to overwrite existing friends.txt
			if (root){
				write = new FileWriter(file);
			}else{
				write = new FileWriter(file, true); // otherwise let's just append
			}
			
			PrintWriter printFriends = new PrintWriter(write);
			printFriends.println(friendName);
			
			System.out.println("Friend " + friendName + " added.");
			//* audit log
		}
	}
	
	
	// return friend's name
	public String getName() {
		return friendName;
	}
}
