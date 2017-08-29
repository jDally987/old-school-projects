// created date: November 14, 2013 at 5:46:55 AM

import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
public class InfixToPost {

	public static LinkedList<Character> post = new LinkedList<Character>();
	public static Stack<Character> ops = new Stack<Character>();
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		while (true){
			System.out.println("Please enter the infix expression to be converted. I encourage you to double check for errors but it's ok if not because you will be notified and chastised for them anyway.");
			// the below expression gets rid of those nasty spaces (delimiters)
			char[] in = reader.nextLine().replaceAll("\\s", "").toCharArray();
			// for some reason this line does not read the input correctly after the first run through.
			// I can't figure out why, I was never good with the readLine() method of the scanner,
			// but at least it works for the first run through the program.
			
			try {
				algorithm(in);
			} catch (MismatchedParenthesesException | MissingOperandException
					| MissingOperatorException e) {
				e.printStackTrace();
			}
			while(!post.isEmpty()){
				System.out.print(post.poll());
			}
			System.out.println("\nYou may try another one. Would you like to exit? (y/n)");
			if (reader.next().equalsIgnoreCase("y")) System.exit(0);
		}
		
	}
	
	public static void algorithm(char[] in) throws MismatchedParenthesesException, MissingOperandException, MissingOperatorException {
		int numOperators = 0; // these will be used for error checking later in the method
		int numOperands = 0; // (a bit roundabout I know...this is weird and hey it works right?)
		for (char c : in){
			switch(c){
				case ' ':
					//do nothing because spaces are delimeters, and they suck
					break;
				case '(':
					ops.push(c);
					break;
				case ')':
					while(ops.peek() != '('){
						post.offer(ops.pop());
					}
					if (ops.isEmpty()){
						throw new MismatchedParenthesesException("You forgot your left parentheses. The program will now shut down.");
					}
					ops.pop();
					break;
				case '+':
				case '-':
				case '*':
				case '/':
					while(!ops.isEmpty() && ops.peek() != '(' && precedence(c) <= precedence(ops.peek())){
						post.offer(ops.pop());
					}
					ops.push(c);
					numOperators++;
					break;
				default:
					post.offer(c);
					numOperands++;
					break;
			}
		}
		// error catching
		if (numOperators < numOperands-1){
			throw new MissingOperatorException("You're missing an operator(s). The program will now shut down.");
		}
		if (numOperands <= numOperators){
			throw new MissingOperandException("You're missing an operand(s). The program will now shut down.");
		}
		while (!ops.isEmpty()) post.offer(ops.pop());
	}
	
	public static int precedence(char o){
		int result = 0;
		switch(o){
			case '*':
			case '/':
				result = 1;
				break;
		}
		return result;
	}

}
