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
	
	private List<Card> cards = new ArrayList<Card>();
	private List<Card> jokerCards = new ArrayList<Card>();
	Hand() {
		Deal deal = new Deal(numberOfDecks);
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
		int[] cardsRequiredForRun = new int[14 - size + 1];
		int cardsRequiredForThisRun = size;
		for(int i = 0; i < 14 - size + 1; i++) {
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
		return min;
	}
	
	private void makeRunForSuit(int suit, int size) {
		int[] availableCards = new int[14];
		for(int i = 0; i < cards.size(); i++) {
			if( cards.get(i).getCardId() / cardsInSuit == suit ) {
				System.out.println( "cardId ->  " + cards.get(i).getCardId());
				if( cards.get(i).getCardId() % cardsInSuit == 0 ) {
					availableCards[cards.size()] += 1;
				}
				availableCards[cards.get(i).getCardId() % cardsInSuit] += 1;
			}
		}
		
		int cardsRequiredForRun = checkRun(availableCards, size);
		System.out.println("cardsRequiredForRun " + cardsRequiredForRun);
		
		for (int i = 0; i < 13; i++) {
			System.out.println("Suit -> " + suit + " Card -> " + availableCards[i]);
		}
	}
	
	public int makeRun(int size) {
		for(int i = 0; i < 4; i++) {  //change 1 to 4
			makeRunForSuit(i, size);
		}
		return 0;
	}
	
	public int findRun() {
		int x = makeRun(3);
		return 0;
	}
	
	public static void main(String[] args) {
		Hand hand = new Hand();
		System.out.println("Helloworld");
		hand.sortByRank();
		int x = hand.makeRun(3);
	}
}
