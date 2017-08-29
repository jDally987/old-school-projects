
public class CD extends Audio{
	public int numTracks;
	public String[] tracks;
	
	
	public CD(String title, Artist majArtist, int time, int numMembers, Artist[] membersList, Artist producer, int numTracks, String[] tracks){
		super(title, majArtist, time, numMembers, membersList, producer);
		this.numTracks = numTracks;
		this.tracks = tracks;
	}
	
	public String toString(){
		String str = super.toString() + " Number of tracks: " + numTracks + " Track list: ";
		for (int i=0;i<numTracks;i++){
			str+= tracks[i] + ", ";
		}
		return str.substring(0, str.length()-3); // chops off the space and comma left over from for loop
	}
}
