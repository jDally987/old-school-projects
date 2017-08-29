
public class Casette extends Audio{
	public int numSongs;
	public String[] songs;
	
	
	public Casette(String title, Artist majArtist, int time, int numMembers, Artist[] membersList, Artist producer, int numSongs, String[] songs){
		super(title, majArtist, time, numMembers, membersList, producer);
		this.numSongs = numSongs;
		this.songs = songs;
	}
	
	public String toString(){
		String str = super.toString() + " Number of songs: " + numSongs + " Song list: ";
		for (int i=0;i<numSongs;i++){
			str+= songs[i] + ", ";
		}
		return str.substring(0, str.length()-3); // chops off the space and comma left over from for loop
	}
}
