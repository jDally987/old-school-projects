import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * Picture object class
 */
public class Picture {
	private String picName;
	private Friend owner;
	private String listName;
	private String perm;
	
	public Picture(String name, Friend f) throws IOException{
		picName = name;
		owner = f;
		listName = "nil";
		perm = "rw -- --";
		
		File file = new File(picName);
		// first must check for duplicate pics
		boolean picExists = file.isFile();
		
		if (!picExists){
			// we can create a new picture file
			file.createNewFile();
		}
	}
	
	
	public void chlst(String name){
		
	}
	
	
	
	
}
