/*
 * John Chaney
 * Project 2: Algo
 * CS 110 3/8/13
 * Bonus: yes
 */
import java.util.Scanner;
public class Chaney2 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		// create the array.
		double weightsBig[] = new double[100];
		
		// ask them
		System.out.println("Please enter your bodyweights in decimal lbs, at least 5 and up to 100. Once done, type any character other than numbers to quit.");
		for (int i=0;i<weightsBig.length;i++){
			double input; // local var input
			System.out.print("Enter a weight: ");
			
			if (!reader.hasNextDouble() && i>4){ //see if they're done first
				break;
			}else if (!reader.hasNextDouble() && i<5){ //see if they THINK they're done but they aint
				System.out.println("You're not finished yet. Enter more weights plz.");
				reader.next();
				i-=1;
			}else { //alright they must have entered a number then
				input = reader.nextDouble();
				//error catching
				if (input<=0 || input>400){
					System.out.println("Invalid weight. It must be above 0 and under 400 lbs.");
					i-=1;
				}else{
					weightsBig[i] = input;
				}
			}
		}
		
		
		// let's shrink that array by finding where the zeroes start
		int pos=100;
		for (int i=0;i<weightsBig.length;i++){
			if (weightsBig[i]==0){
				pos = i;
				break;
			}
		}
		
		// ok now make the new one
		double weights[] = new double[pos];
		for (int i=0;i<pos;i++){
			weights[i]=weightsBig[i];
		}
		
		// sortan time
		// bubble sort btw
		int lastSorted=0;
		while (lastSorted < weights.length-1){
			// each time this loop fully runs is one full run-through of the bubble sort
			// it repeats until the number of sorted elements (on the end) equals the length
			// of the weights array (minus 1 for the 1st element), hence the while loop
			for (int i=1;i<weights.length-lastSorted;i++){
				if (weights[i] < weights[i-1]){
					double temp = weights[i];
					weights[i] = weights[i-1];
					weights[i-1] = temp;
				}
			}
			lastSorted++;
		}
		
		// sweet, now let's show the user the sorted list
		System.out.println("\nHere are your bodyweights sorted from lightest to heaviest, each in lbs:");
		for (int i=0;i<weights.length;i++){
			System.out.print(weights[i] + " ");
		}
		
		// calculate some stuff
		double sum=0;
		for (int i=0;i<weights.length;i++){
			sum+=weights[i];
		}
		double avg = sum/weights.length;
		
		//lezgo
		System.out.printf("\n\nThe average weight is %.2f lbs.\n",avg);
		System.out.println("The lightest weight of the bunch is "+weights[0]+" lbs, and the heaviest is "+weights[weights.length-1]+" lbs.");
		
		
		// BONUS
		int fly=0;
		int bantam=0;
		int feather=0;
		int light=0;
		int welter=0;
		int middle=0;
		int lightheavy=0;
		int heavy=0;
		int superheavy=0;
		for (int i=0;i<weights.length;i++){
			if (weights[i]<125) fly++;
			else if (weights[i]<135) bantam++;
			else if (weights[i]<145) feather++;
			else if (weights[i]<155) light++;
			else if (weights[i]<170) welter++;
			else if (weights[i]<185) middle++;
			else if (weights[i]<205) lightheavy++;
			else if (weights[i]<265) heavy++;
			else superheavy++;
		}
		
		System.out.printf("If your weights represented MMA fighters, you would have %d flyweights, %d bantamweights, %d featherweights, %d lightweights, %d welterweights, %d middleweights, %d light heavyweights, %d heavyweights, and %d super heavyweights.",fly,bantam,feather,light,welter,middle,lightheavy,heavy,superheavy);
	}

}
