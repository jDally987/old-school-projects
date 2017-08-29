import java.util.Scanner;
public class cahd {
	
	Scanner reader = new Scanner(System.in);
	
	private String[] suitName = {"Clubs","Spades","Diamonds","Hearts"};
	private String[] faceValue = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
	
	private int[] numericValue = {2,3,4,5,6,7,8,9,10,10,10,10,11};
	
	private String face, suit;
	private int numeric;
	
	
	public cahd(int suitIndex, int nameIndex)
	{
		face = faceValue[nameIndex];
		suit = suitName[suitIndex];
		numeric = numericValue[nameIndex];
		
	}
	
	public String getFace()
	{
		return face;
	}
	
	public String getSuit()
	{
		return suit;
	}
	
	public int getNumeric()
	{
		return numeric;
	}
	
	public void setAceValue()
	{
		System.out.println("You've drawn an ace big boy. Do you want it to be worth 1 or 11?");
		numeric = reader.nextInt();
	}
}
