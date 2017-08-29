
public class VHS extends Video {
	public int numTrailers;
	public String[] trailers;
	
	
	public VHS(String title, Artist majArtist, int time, int numActors, Artist[] actors, Artist director, int rating, int numTrailers, String[] trailers){
		super(title, majArtist, time, numActors, actors, director, rating);
		this.numTrailers = numTrailers;
		this.trailers = trailers;
	}
	
	public String toString(){
		String str = super.toString() + " Number of trailers: " + numTrailers + " List of trailers: ";
		for (int i=0;i<numTrailers;i++){
			str+= trailers[i] + ", ";
		}
		return str.substring(0, str.length()-3); // chops off the space and comma left over from for loop
	}
}
