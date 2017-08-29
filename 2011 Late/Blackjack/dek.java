import java.util.Collections;
import java.util.Stack;
public class dek {
	
	private Stack<cahd> shuffleDeck = new Stack<cahd>();
	private cahd[] deckk = new cahd[52];
	private final int NUMSUITS = 4, NUMFACE = 13;
	
	public dek()
	{
		int cardCntr = 0;
		
		for (int suit = 0; suit<NUMSUITS; suit++){
			for (int face = 0; face<NUMFACE; face++){
				
				deckk[cardCntr] = new cahd(suit, face);
				shuffleDeck.push(deckk[cardCntr]);
				
				cardCntr++;
			}
		}
	}
	
	public Stack<cahd> shuffleCards()
	{
		Collections.shuffle(shuffleDeck);
		return shuffleDeck;
	}
}
