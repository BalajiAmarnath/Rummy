	public class Deal
	{
		int Deck[];
		
		Deal(int numberOfDeck)
		{
			Deck = new int [53];
			for (int card = 0; card < Deck.length; card++)
			{
				Deck[card] = numberOfDeck;
			}
		}
		
		public Card getCard(int cardNumber)
		{
			Card card = new Card(cardNumber);
			Deck[cardNumber]--;
			return card;
		}
		
		public void putCard(Card card)
		{
			int cardId = card.getCardId();
			Deck[cardId]++;
			return;
		}
		
		public boolean isPresent(Card card)
		{
			return Deck[card.getCardId()] > 0;
		}
	}