
public abstract class Video extends Media{
	public int numActors;
	public Artist[] supportingActors;
	public Artist director;
	public int rating; // should be from 0-10
	
	
	public Video(String title, Artist majArtist, int time, int numActors, Artist[] actors, Artist director, int rating){
		super(title, majArtist, time);
		this.numActors = numActors;
		supportingActors = actors;
		this.director = director;
		this.rating = rating;
	}
	

	public void playMedia(){
		System.out.println("Now playing: " + getTitle() + ", with playing time " + getPlayingTime() + " seconds, and a rating of " + rating + " out of 10.");
		numPlays++;
	}
	
	public String toString(){
		String str = super.toString() + " Number of actors: " + numActors + " List of supporting actors and details: ";
		for (int i=0;i<numActors;i++){
			str+= supportingActors[i].toString() + ", ";
		}
		return str + "Director details: " + director.toString();
	}
}
