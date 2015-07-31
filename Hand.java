package rummy;

import java.util.*;


public class Hand {
	final int cardsInOneHand = 13;
	final int numberOfDecks = 3;
	final int numberOfJokersInDeck = 1;
	final int numberOfCardsInDeck = 52;
	Hand() {
		Card[] cards = new Card[cardsInOneHand];
		Random randomGenerator = new Random();
	    for (int i = 0; i < cardsInOneHand; ++i){
	      int randomCard = randomGenerator.nextInt(numberOfDecks * (numberOfCardsInDeck + numberOfJokersInDeck));
	      cards[i] = new Card(randomCard);
	      System.out.println(cards[i].id);
	    }
 	}
	
	
	
	public static void main(String[] args) {
		Hand hand = new Hand();
	}
}
