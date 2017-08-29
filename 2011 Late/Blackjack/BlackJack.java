// created date: December 14, 2011 at 1:38:50 PM

import java.util.Stack;
import java.util.Scanner;
public class BlackJack {
	
	Scanner reader = new Scanner(System.in);
	public dek deckie = new dek();
	public Stack<cahd> deckCards = new Stack<cahd>();
	
	// running total
	static int total = 0;
	
	public void displayHit()
	{
		// populate stack
		
		// also, I don't know why it won't let me put this statement below outside of the method
		// (like just in the class's main body) but when I do, it tells me the semicolon on the end of 
		// "new Stack<cahd>(); <<<< is wrong. no idea why...but i just put this lil statement
		// inside the methods to pacify stupid java and it works so whatevvs
		
		// and I realize it shuffles the deck every time too. whatever...more random that way
		deckCards = deckie.shuffleCards();
		
		System.out.println("Your next card:");
		if (deckCards.peek().getFace() == "Ace")
		{
			deckCards.peek().setAceValue();
		}
		System.out.println(deckCards.peek().getFace() + " of " + deckCards.peek().getSuit());
		
		total += deckCards.peek().getNumeric();
		deckCards.pop();
	}
	
	// print first two cards
	public void displayFirstCards()
	{
		deckCards = deckie.shuffleCards();
		
		System.out.println("Your first two cards:");
		if (deckCards.peek().getFace() == "Ace")
		{
			deckCards.peek().setAceValue();
		}
		System.out.println(deckCards.peek().getFace() + " of " + deckCards.peek().getSuit());
		total += deckCards.peek().getNumeric();
		deckCards.pop();
		
		if (deckCards.peek().getFace() == "Ace")
		{
			deckCards.peek().setAceValue();
		}
		System.out.println(deckCards.peek().getFace() + " of " + deckCards.peek().getSuit());
		total += deckCards.peek().getNumeric();
		deckCards.pop();
		
		System.out.println("Your current total: " + total);
		if (total == 21)
		{
			System.out.println("Holy white whale, you got a blackjack! You win already, my friend. Now that was easy, wasn't it.");
			System.exit(0);
		}
	}
	
	public void hitOrStay()
	{
		
		System.out.println("Wanna hit or stay?");
		String response = reader.next();
		if (response.equalsIgnoreCase("hit"))
		{
			displayHit();
			System.out.println("Your current total: " + total);
		}else if (response.equalsIgnoreCase("stay")){
			System.out.println("K you stayed.");
			System.out.println("Your current total: " + total);
		}else{
			System.out.println("That was invalid, retard. Try again...");
			hitOrStay();
		}
	}
	
	
	
	// dealer side of things
	// declare dealer's total
	static int dealersTotal = 0;
	
	// create method for dealer's turns
	public void dealerHit()
	{
		deckCards = deckie.shuffleCards();
		
		
		dealersTotal += deckCards.peek().getNumeric();
		deckCards.pop();
	}
	
	
	
	// now for the main shenanigans
	public static void main(String []args){
		
		BlackJack bj = new BlackJack();
		
		// opening welcome message
		System.out.println("Herro and welcome to Jack Black!");
		
		// get dealer's first two cards; display first user's first 2 cards
		bj.dealerHit();
		bj.dealerHit();
		bj.displayFirstCards();
		
		
		while (total <21 && dealersTotal < 17)
		{
			if (dealersTotal < 17)
			{
				bj.dealerHit();
			}
			
			
			bj.hitOrStay();
			
		}
		
		if (dealersTotal > 21){
			System.out.println("The dealer busted. You win! Congratz.");
			System.exit(0);
		}
		
		if (total >21)
		{
			System.out.println("LOL you busted. Good job, thanks for the pecuniary gain. See ya later.");
		}
		
		
		if (dealersTotal > 16)
		{
			System.out.println("The dealer stays.");
		}
		if (total > dealersTotal)
		{
			System.out.println("Your total is greater than the dealer's. You win, congr4tz!");
		}
		
		if (dealersTotal > total)
		{
			System.out.println("The dealer's total is greater than yours. You lose, sry lul!!");
		}
	}
}
