
public class DVD extends Video {
	public int numFeatures;
	public String[] specialFeatures;
	public double wideScreenFormat;
	public double TVFormat;
	public int numSoundOptions;
	public String[] soundOptions;
	
	
	public DVD(String title, Artist majArtist, int time, int numActors, Artist[] actors, Artist director, int rating, int numFeatures, String[] features, double wsf, double tvf, int numSoundOptions, String[] soundOptions){
		super(title, majArtist, time, numActors, actors, director, rating);
		this.numFeatures = numFeatures;
		specialFeatures = features;
		wideScreenFormat = wsf;
		TVFormat = tvf;
		this.numSoundOptions = numSoundOptions;
		this.soundOptions = soundOptions;
	}
	
	public String toString(){
		String str = super.toString() + " Number of special features: " + numFeatures + " List of features: ";
		for (int i=0;i<numFeatures;i++){
			str+= specialFeatures[i] + ", ";
		}
		str+= "Wide screen format: " + wideScreenFormat + " TV Format: " + TVFormat + " Number of sound options: " + numSoundOptions + " List of sound options: ";
		for (int i=0;i<numSoundOptions;i++){
			str+= soundOptions[i] + ", ";
		}
		return str.substring(0, str.length()-3); // chops off the space and comma left over from for loop
	}
}
