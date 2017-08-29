import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * List object class
 */
public class FriendList {
	private String listName;
	private ArrayList<Friend> friendsInList;
	
	// default constructor
	public FriendList(String name){
		listName = name;
		friendsInList = new ArrayList<Friend>(1);
	}
	
	
	public String getName() {
		return listName;
	}
	
	
	public void addFriendToList(Friend fr){
		friendsInList.add(fr);
	}
	
}
