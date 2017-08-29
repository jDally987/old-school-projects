import java.util.Scanner;
import java.util.ArrayList;
public class Chaney1 {
	
	/**
	 * John Chaney
	 * Project 1: Intro
	 * CS 110 - 1/30/13 Spring 2013
	 * Bonus: yes
	 */
	public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        
        // arraylist to hold unknown amount of numbers
        ArrayList<Integer> numbers = new ArrayList<>();
        
        // get user input & add whatever amount of numbers to arraylist
        boolean moreNumbers = true;
        while (moreNumbers == true) {
        	System.out.print("Please enter an integer: ");
        	numbers.add(reader.nextInt());
        	
        	// ask if they want to add another
        	System.out.print("Would you like to add another number? (y/n): ");
        	if (reader.next().equalsIgnoreCase("n")) {
        		moreNumbers = false;
        	}
        }
        
        System.out.println("Enter your choice of operation to be applied to all entries:");
        System.out.println("---------------------------------------------------------------");
        System.out.println("1) Addition");
        System.out.println("2) Subtraction");
        System.out.println("3) Multiplication");
        System.out.println("4) Division");
        System.out.println("5) Quotient/remainder division");
        System.out.println("(please note, the operations will be carried out on the numbers in the order in which they were entered.)");
        
        int operationIndex = reader.nextInt();
        int result = numbers.get(0);
        double resultDouble = numbers.get(0);
        int remainder = 0;
        
        // show the user their result
        if (operationIndex == 1) {
        	for (int i=1;i<numbers.size();i++){
            	result+=numbers.get(i);
            }
        	System.out.printf("The result is %d.",result);
        }else if (operationIndex == 2) {
        	result = numbers.get(0);
        	for (int i=1;i<numbers.size();i++){
            	result-=numbers.get(i);
            }
        	System.out.printf("The result is %d.",result);
        }else if (operationIndex == 3) {
        	for (int i=1;i<numbers.size();i++){
            	result*=numbers.get(i);
            }
        	System.out.printf("The result is %d.", result);
        }else if (operationIndex == 4) {
        	for (int i=1;i<numbers.size();i++){
            	resultDouble/= (double)numbers.get(i);
            }
        	System.out.printf("The result is %.2f. (rounded to 2 decimal places)", resultDouble);
        }else{
        	// this one was a headache
        	for (int i=1;i<numbers.size();i++){
        		resultDouble/= (double)numbers.get(i);
        		if (i == numbers.size()-1){
        			remainder = (int)resultDouble % numbers.get(numbers.size()-1);
        		}
            }
        	
        	System.out.printf("The result is %.0f, remainder %d.", Math.floor(resultDouble),remainder);
        }
        
        
	}

}
