package rummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class Hand {
	final int cardsInOneHand = 13;
	final int numberOfDecks = 3;
	final int numberOfJokersInDeck = 1;
	final int numberOfCardsInDeck = 52;
	final int JokerId = 52;
	final static int cardsInSuit = 13;
	final static int CLUB = 0;
	final static int DIAMOMND = 13;
	final static int HEART = 26;
	final static int SPADE = 39;
	static int naturalSequenceCount = 0;
	static int sequenceCount = 0;
	
	private List<Card> cards = new ArrayList<Card>();
	private List<Card> jokerCards = new ArrayList<Card>();
	Deal deal;
	Hand() {
		deal = new Deal(numberOfDecks);
		Random randomGenerator = new Random();
	    for (int i = 0; i < cardsInOneHand; ++i){
	      //int randomCard = randomGenerator.nextInt(numberOfCardsInDeck + numberOfJokersInDeck);
	      int randomCard = randomGenerator.nextInt(numberOfCardsInDeck);
	      System.out.println(randomCard);
	      if(randomCard == JokerId) {
	    	  jokerCards.add(deal.getCard(randomCard));
	      }
	      else {
	    	  cards.add(deal.getCard(randomCard));
	      }
	    }
 	}
	
	public void sortBySuit() {
		Collections.sort(cards);
	}
	
	public void sortByRank() {
		Collections.sort(cards);
		for(int i=0; i<13; i++) {
			System.out.println(cards.get(i).getCardId());
		}
	}
	
	private int checkRun(int[] availableCards, int size) {
		int min = size;
		int indexOfMin = 0;
		int[] cardsRequiredForRun = new int[cardsInOneHand + 1 - size + 1];
		int cardsRequiredForThisRun = size;
		for(int i = 0; i < cardsInOneHand + 1 - size + 1; i++) {
			cardsRequiredForThisRun = size;
			for(int j = 0; j < size; j++) {
				if(availableCards[i + j] > 0) {
					cardsRequiredForThisRun--;
				}
			}
			cardsRequiredForRun[i] = cardsRequiredForThisRun;
			//System.out.println("Cards Required for this run : " + cardsRequiredForThisRun);
			if(cardsRequiredForThisRun < min) {
				min = cardsRequiredForThisRun;
				indexOfMin = i;
			}
			System.out.println("min -> " + min + " IndexOfMin -> " + indexOfMin);
		}
		return indexOfMin;
	}
	
	public int makeRun(int size) {
		for(int p = 0; p < 4; p++) {
			int suit = p;
			int[] availableCards = new int[cardsInOneHand + 1];
			for(int i = 0; i < cards.size(); i++) {
				if( cards.get(i).getCardId() / cardsInSuit == suit ) {
					System.out.println( "cardId ->  " + cards.get(i).getCardId());
					if( cards.get(i).getCardId() % cardsInSuit == 0 ) {
						availableCards[cardsInOneHand] += 1;
					}
					availableCards[cards.get(i).getCardId() % cardsInSuit] += 1;
				}
			}
			
			int indexOfMin = checkRun(availableCards, size);
			for(int i = indexOfMin; i < indexOfMin + size; i++) {
				if(availableCards[i] == 0) {
					if (deal.Deck[suit * cardsInSuit + i] > 0) {
						deal.Deck[suit * cardsInSuit + i]--;
					}
					else {
						
					}
				}
			}
			
			for (int i = 0; i < 13; i++) {
				//System.out.println("Suit -> " + suit + " Card -> " + availableCards[i]);
			}
		}
		return 0;
	}
	
	public int findRun() {
		int x = makeRun(3);
		return 0;
	}
	
	public void removeSequence(int size) {
		
	}
	
	public void remove(int cardId) {
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getCardId() == cardId) {
				cards.remove(i);
			}
		}
	}
	
	public boolean exists(int cardId) {
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getCardId() == cardId) {
				return true;
			}
		}
		return false;
	}
	
	public void removeSets(int size) {
		int existanceCount = 0;
		
		for(int i = 0; i < cardsInSuit; i++) {
			if(exists(i + cardsInSuit * 0)){
				existanceCount++;
			}
			if(exists(i + cardsInSuit * 1)){
				existanceCount++;
			}
			if(exists(i + cardsInSuit * 2)){
				existanceCount++;
			}
			if(exists(i + cardsInSuit * 3)){
				existanceCount++;
			}
			if(existanceCount == size) {
				remove(i + cardsInSuit * 0);
				remove(i + cardsInSuit * 1);
				remove(i + cardsInSuit * 2);
				remove(i + cardsInSuit * 3);
			}
		}
	}
	
	public void removeCanasta() {
		boolean canastaFound;
		do{
			canastaFound = false;
			int i;
			for (i = 0; i <= cards.size() - 3; i++) {
				if (cards.get(i).isSame(cards.get(i+1)) &&  cards.get(i+1).isSame(cards.get(i+2))) {
					canastaFound = true; 
					break;
				}
			}
			if(canastaFound) {
				naturalSequenceCount++;
				for(int j = i; j < i + 3; j++) {
					cards.remove(j);
				}
			}
		}while (canastaFound);
	}
	
	public static void main(String[] args) {
		Hand hand = new Hand();
		System.out.println("Helloworld");
		hand.sortByRank();
		hand.removeCanasta();
		hand.removeSequence(3);
		hand.removeSets(3);
		int x = hand.makeRun(3);
	}
}
