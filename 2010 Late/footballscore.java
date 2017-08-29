// created date: October 12, 2010 at 2:08 PM

import java.util.Scanner;

public class footballscore {
	public static void main(String[]args){
		Scanner reader=new Scanner(System.in);
		
		
		final int TD=6,EXTRA2=2,EXTRA1=1,FGOAL=3,SAFETY=2;
		int td,extra2, extra1, fgoal, safety, finalscore;
		
		System.out.println("So. How many touchdowns were scored?: ");
		td=reader.nextInt();
		
		System.out.println("Alright. How many of those did they get both extra points on?: ");
		extra2=reader.nextInt();
		
		System.out.println("But how many touchdowns did they only get one extra point?: ");
		extra1=reader.nextInt();
		
		System.out.println("Alrighty then! How many field goals did they get?: ");
		fgoal=reader.nextInt();
		
		System.out.println("Kk. And finally...how many safeties did they get?: ");
		safety=reader.nextInt();
		
		if (safety>0){
			System.out.println("Wow. That's humiliating lol.");
		}
		
		finalscore=(TD*td)+(EXTRA2*extra2)+(EXTRA1*extra1)+(FGOAL*fgoal)+(SAFETY*safety);
		
		System.out.println("\n\nSo you want to know the final score? Oh alright then. The team won a total of "+finalscore+" points. Good job.");
		
		
	}

}
