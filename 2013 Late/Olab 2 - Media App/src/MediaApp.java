// created date: October 24, 2013 at 12:47:10 PM

import java.util.ArrayList;
import java.util.Scanner;
public class MediaApp {

	private static Scanner reader = new Scanner(System.in);
	public static void main(String[] args) {
		ArrayList<Media> lib = new ArrayList<Media>(); //change accordingly
		
		int choice = menu();
		while (choice != 10){
			switch(choice){
				case 1: lib.add(createDVD());
				System.out.println("DVD successfully added.");
				break;
				case 2: lib.add(createCD());
				System.out.println("CD successfully added.");
				break;
				case 3: lib.add(createVHS());
				System.out.println("VHS successfully added.");
				break;
				case 4: lib.add(createCasette());
				System.out.println("Casette successfully added.");
				break;
				case 5: for (int i=0;i<lib.size();i++){
					System.out.println("Library item " + i+1 + ".");
					System.out.println(lib.get(i));
				}
				break;
				case 6: System.out.println("Which media item would you like to view? Enter its index #: ");
				System.out.println(lib.get(reader.nextInt()-1));
				break;
				case 7: System.out.println("Please enter the index of the media you wish to see the major artist details of: ");
				int x = reader.nextInt();
				System.out.println(lib.get(x-1).getMajorArtist());
				break;					
				case 8: System.out.println("Which media item would you like to play? Enter its index: ");
				lib.get(reader.nextInt()-1).playMedia();
				break;
				case 9: System.out.println("For which medium would you like to know the number of plays? Enter its index: ");
				System.out.println(lib.get(reader.nextInt()-1).getNumberPlays());
				break;
				default: System.out.println("Invalid choice. Pls try again.");
				break;
			}
			choice = menu();
		}
	}
	
	
	
	public static int menu(){
		//Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to Media. May we take your order?");
		System.out.println("***");
		System.out.println("Please select your choice from the list.\n");
		
		System.out.println("Enter 1 to CREATE DVD");
		System.out.println("Enter 2 to CREATE CD");
		System.out.println("Enter 3 to CREATE VHS");
		System.out.println("Enter 4 to CREATE Casette");
		System.out.println("Enter 5 to DISPLAY all Media");
		System.out.println("Enter 6 to DISPLAY all data for a particular Medium");
		System.out.println("Enter 7 to DISPLAY data of major artist of a particular Medium");
		System.out.println("Enter 8 to PLAY a particular Medium");
		System.out.println("Enter 9 to get the Number of Plays of a particular Medium");
		System.out.println("Enter 10 to EXIT");
		System.out.println("-----------------------------------------------------------------");
		return reader.nextInt();
	}
	
	
	public static Artist createArtist(){
		System.out.println("First name: ");
		String first = reader.next();
		System.out.println("Last name: ");
		String last = reader.next();
		
		System.out.println("Enter the birthdate - day: ");
		int d = reader.nextInt();
		System.out.println("Month: ");
		int m = reader.nextInt();
		System.out.println("Year: ");
		int y = reader.nextInt();
		Date date = new Date(d, m, y);
		
		System.out.println("Enter their twitter name (without the @): ");
		String twitter = reader.next();
		System.out.println("Enter their website URL: ");
		String url = reader.next();
		
		return new Artist(first, last, date, twitter, url);
	}
	
	
	public static DVD createDVD(){
		System.out.println("Enter the title.");
		String title = reader.next();
		
		System.out.println("Enter the information for the major artist.");
		Artist maj = createArtist();
		
		System.out.println("Enter the playing time (in seconds): ");
		int time = reader.nextInt();
		System.out.println("Enter the number of actors: ");
		int numActors = reader.nextInt();
		
		Artist[] actors = new Artist[numActors];
		System.out.println("Please enter the information for the actors.");
		for (int i=0;i<numActors;i++){
			System.out.println("Actor number " + (i+1) + ".");
			actors[i] = createArtist();;
		}
		
		System.out.println("Enter the information for the director.");
		Artist dir = createArtist();
		
		System.out.println("Enter the rating for the movie: ");
		int rating = reader.nextInt();
		System.out.println("Enter the number of features: ");
		int numFeatures = reader.nextInt();
		
		String[] features = new String[numFeatures];
		for (int i=0;i<numFeatures;i++){
			System.out.println("Enter details for feature " + (i+1) + ": ");
			features[i] = reader.next();
		}
		
		System.out.println("Enter the wide screen format (number with decimal): ");
		double wsf = reader.nextDouble();
		System.out.println("Enter the TV format (number with decimal): ");
		double tvf = reader.nextDouble();
		System.out.println("Enter the number of sound options: ");
		int numSoundOptions = reader.nextInt();
		
		String[] options = new String[numSoundOptions];
		for (int i=0;i<numSoundOptions;i++){
			System.out.println("Enter details for sound option " + (i+1) + ": ");
			options[i] = reader.next();
		}
		
		return new DVD(title, maj, time, numActors, actors, dir, rating, numFeatures, features, wsf, tvf, numSoundOptions, options);
	}
	
	
	public static CD createCD(){
		System.out.println("Enter the title.");
		String title = reader.next();
		
		System.out.println("Enter the information for the major artist.");
		Artist maj = createArtist();
		
		System.out.println("Enter the playing time (in seconds): ");
		int time = reader.nextInt();
		System.out.println("Enter the number of group members: ");
		int numMembers = reader.nextInt();
		
		Artist[] members = new Artist[numMembers];
		System.out.println("Please enter the information for the group members.");
		for (int i=0;i<numMembers;i++){
			System.out.println("Member number " + (i+1) + ".");
			members[i] = createArtist();;
		}
		
		System.out.println("Enter the information for the producer.");
		Artist pro = createArtist();
		
		System.out.println("Enter the number of tracks: ");
		int numTracks = reader.nextInt();
		
		String[] tracks = new String[numTracks];
		for (int i=0;i<numTracks;i++){
			System.out.println("Enter details for track " + (i+1) + ": ");
			tracks[i] = reader.next();
		}
		
		return new CD(title, maj, time, numMembers, members, pro, numTracks, tracks);
	}
	
	
	public static VHS createVHS(){
		System.out.println("Enter the title.");
		String title = reader.next();
		
		System.out.println("Enter the information for the major artist.");
		Artist maj = createArtist();
		
		System.out.println("Enter the playing time (in seconds): ");
		int time = reader.nextInt();
		System.out.println("Enter the number of actors: ");
		int numActors = reader.nextInt();
		
		Artist[] actors = new Artist[numActors];
		System.out.println("Please enter the information for the actors.");
		for (int i=0;i<numActors;i++){
			System.out.println("Actor number " + (i+1) + ".");
			actors[i] = createArtist();;
		}
		
		System.out.println("Enter the information for the director.");
		Artist dir = createArtist();
		
		System.out.println("Enter the rating for the movie: ");
		int rating = reader.nextInt();
		System.out.println("Enter the number of trailers: ");
		int numTrailers = reader.nextInt();
		
		String[] trailers = new String[numTrailers];
		for (int i=0;i<numTrailers;i++){
			System.out.println("Enter details for trailer " + (i+1) + ": ");
			trailers[i] = reader.next();
		}
		
		return new VHS(title, maj, time, numActors, actors, dir, rating, numTrailers, trailers);
	}
	
	
	public static Casette createCasette(){
		System.out.println("Enter the title.");
		String title = reader.next();
		
		System.out.println("Enter the information for the major artist.");
		Artist maj = createArtist();
		
		System.out.println("Enter the playing time (in seconds): ");
		int time = reader.nextInt();
		System.out.println("Enter the number of group members: ");
		int numMembers = reader.nextInt();
		
		Artist[] members = new Artist[numMembers];
		System.out.println("Please enter the information for the group members.");
		for (int i=0;i<numMembers;i++){
			System.out.println("Member number " + (i+1) + ".");
			members[i] = createArtist();;
		}
		
		System.out.println("Enter the information for the producer.");
		Artist pro = createArtist();
		
		System.out.println("Enter the number of songs: ");
		int numSongs = reader.nextInt();
		
		String[] songs = new String[numSongs];
		for (int i=0;i<numSongs;i++){
			System.out.println("Enter details for song " + (i+1) + ": ");
			songs[i] = reader.next();
		}
		
		return new Casette(title, maj, time, numMembers, members, pro, numSongs, songs);
	}
}
