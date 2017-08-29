
public abstract class Audio extends Media{
	public int numMembers;
	public Artist[] groupMembers;
	public Artist producer;
	
	
	public Audio(String title, Artist majArtist, int time, int numMembers, Artist[] membersList, Artist producer){
		super(title, majArtist, time);
		this.numMembers = numMembers;
		groupMembers = membersList;
		this.producer = producer;
	}
	
	public String toString(){
		String str = super.toString() + " Number of members: " + numMembers + " List of members and details: ";
		for (int i=0;i<numMembers;i++){
			str+= groupMembers[i].toString() + ", ";
		}
		return str + "Producer details: " + producer.toString();
	}
}
