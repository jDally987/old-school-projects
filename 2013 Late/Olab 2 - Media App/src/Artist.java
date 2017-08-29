
public class Artist {
	public String first;
	public String last;
	public Date birthdate;
	public String twitterName;
	public String url;
	
	
	public Artist(String first, String last, Date bday, String twitter, String url){
		this.first = first;
		this.last = last;
		birthdate = bday;
		twitterName = twitter;
		this.url = url;
	}
	
	
	public String toString(){
		return "First name: " + first + " Last name: " + last + " Birthdate: " + birthdate + " Twitter name: @" + twitterName + " URL: " + url;
	}
}
