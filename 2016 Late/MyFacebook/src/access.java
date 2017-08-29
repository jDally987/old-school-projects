// created date: December 1, 2016 at 4:31:29 PM

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Assignment #2 - MyFacebook program
 * CS 465 - Dr. Katerina Goseva-Popstojanova
 * by John Chaney
 */
public class access {
	
	private static Friend currUsr;

	public static void main(String[] args) throws IOException {
		// read in the file, which is named according to the cmd line argument given at runtime
		String fileName = args[0];
		Scanner reader = new Scanner(new FileInputStream(fileName));
		
		String line;
		String[] cmds;
		boolean firstLine = true; // flag to indicate the first line; changes after initial run
		
		do {
			// read in each new line, and split it into separate tokens
			line = reader.nextLine();
			cmds = line.split(" ", 2);
			
			
			// initial line procedures
			if (firstLine && (cmds[0].equals("addfriend"))){
				Friend root = new Friend(cmds[1], true);
				firstLine = false;
			}else{
				System.out.println("Invalid initial command. Please ensure the first command in the instruction .txt file is of type \"addfriend\".");
				//* print to audit log
				System.exit(0);
			}
			
			ArrayList<Friend> friends = new ArrayList<Friend>(1);
			ArrayList<FriendList> lists = new ArrayList<FriendList>(1);
			ArrayList<Picture> pics = new ArrayList<Picture>(1);
			
			switch (cmds[0]){
			case "addfriend" :
				Friend friend = new Friend(cmds[1], false);
				friends.add(friend);
			break;
			
			case "viewby" :
				if (currUsr == null){
					for (Friend i : friends){
						if (i.getName().equalsIgnoreCase(cmds[1])){
							currUsr = i;
							break;
						}else{
							System.out.println("Specified name is not on your friends list. He/she may not view your profile.");
							//* print to audit log
						}
					}
				}else{
					System.out.println("View failed: concurrent friends not supported.");
					//* audit log
				}
			break;
			
			case "logout" :
				System.out.println("Friend " + currUsr.getName() + " logged out.");
				currUsr = null;
			break;
			
			case "listadd" :
				if (currUsr.getName().equals("root")){
					for (int i=0; i<lists.size(); i++){
						if (lists.get(i).getName().equalsIgnoreCase(cmds[1])){
							System.out.println("Error: a list by that name already exists.");
							break;
						}else{
							FriendList newList = new FriendList(cmds[1]);
							lists.add(newList);
							System.out.println("List " + cmds[1] + " added.");
							//*audit log
						}
					}
				}else{
					System.out.println("Error: please login to the root profile first in order to create a list.");
					//*audit log
				}
			break;
			
			case "friendlist" :
				String[] listCmds = cmds[1].split(" ", 2); // split into [friendname, listname]
				
				// make sure this is a valid operation...do ALL these checks (whew)
				if (currUsr.getName().equals("root")){
					for (int i=0; i<friends.size(); i++){
						if (friends.get(i).getName().equalsIgnoreCase(listCmds[0])){
							for (int j=0; j<lists.size(); j++){
								if (lists.get(j).getName().equalsIgnoreCase(listCmds[1])){
									lists.get(j).addFriendToList(friends.get(i));
									System.out.println("Friend " + listCmds[0] + " added to list "  + listCmds[1] + ".");
									//* audit log
									break;
								}else if(j==lists.size()-1){
									System.out.println("Error: a list by that name does not exist.");
								}
							}
							break;
						}else if(i==friends.size()-1){
							System.out.println("Error: a friend by that name does not exist.");
						}
					}
				}else{
					System.out.println("Error: please login to the root profile first in order to add to a list.");
					//*audit log
				}
			break;
			
			case "postpicture" :
				if (currUsr != null){
					Picture pic = new Picture(cmds[0], currUsr);
					pics.add(pic);
					System.out.println("Picture " + cmds[0] + " with owner " + currUsr.getName() + " and default permissions created.");
				}else{
					System.out.println("Error: no user is logged in.");
					//*audit log
				}
			break;
			
			case "chlst" :
				String[] picCmds = cmds[1].split(" ", 2);
				if (currUsr != null){
					// if the current user is either the root or the owner of the picture; go ahead and modify the picture object
				}else{
					System.out.println("Error: no user is logged in.");
					//*audit log
				}
			break;
			
			case "chmod" :
			break;
			
			case "chown" :
			break;
			
			case "readcomments" :
			break;
			
			case "writecomments" :
			break;
			}
			
			
		}while(!cmds[0].equalsIgnoreCase("end")); // continues until the end command triggers exit
		
	}

}
