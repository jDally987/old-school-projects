
public abstract class Media {
	public String title;
	public Artist majorArtist;
	public int playingTime; //in seconds
	public int numPlays;
	
	
	public Media(String title, Artist majArtist, int time){ // # of plays is still 0 upon creation of new media
		this.title = title;
		majorArtist = majArtist;
		playingTime = time;
		numPlays = 0;
	}
	
	
	/**
	 * "Plays" a given media file and increments the number of plays.
	 * In Video subclass, this is overridden to include the rating of the movie in the printout.
	 * precond: none
	 * postcond: prints out the title and playing time of media, and increments the number of plays for the media.
	 */
	public void playMedia(){
		System.out.println("Now playing: " + title + ", with playing time " + playingTime + " seconds.");
		numPlays++;
	}
	
	
	/**
	 * Gets & returns the title of the given media.
	 * precond: the title is a valid string, and has been named by the user upon creation of media file
	 * postcond: the title is returned
	 * @return title String variable
	 */
	public String getTitle(){ return title;}
	
	/**
	 * Gets & returns the number of plays of the given media.
	 * precond: none
	 * postcond: the number of plays is returned
	 * @return numPlays integer variable
	 */
	public int getNumberPlays(){ return numPlays;}
	
	
	/**
	 * Gets & returns the playing time.
	 * precond: playingTime is a valid integer, and has been specified by the user upon creation of media file
	 * postcond: the playing time is returned
	 * @return playingTime integer variable
	 */
	public int getPlayingTime(){ return playingTime;}
	
	
	/**
	 * Gets & returns the majorArtist object.
	 * precond: majorArtist is a valid Artist object, whose details have been filled in by the user upon creation of media file
	 * postcod: the majorArtist is returned
	 * @return majorArtist Artist object
	 */
	public Artist getMajorArtist(){ return majorArtist;}
	
	
	/**
	 * Returns a string containing all the data members contained within the Media superclass.
	 * precond: title, majorArtist, playingTime, and numPlays are all valid and have been specified by the user
	 * postcond: a string containing a printout of all the data members of this class is returned
	 * @return string including all data members contained within this class
	 */
	public String toString(){
		return "Title: " + title + " Major artist details: " + majorArtist + " Playing time (in seconds): " + playingTime + " Number of plays: " + numPlays;
	}
}
