class Card {
	private int cardId;
	private int rank;
	private char suit;
	
	private final int suitSize = 13;
	private final int deckSize = 52;
	private final int NoOfDecks = 3;
	
	public int getCardId() {
		return cardId;
	}

	public int getRank() {
		return rank;
	}

	public char getSuit() {
		return suit;
	}
	
	public Card(int id) {
		cardId = id;
		id = id % deckSize;
	
		if(!isJoker(cardId))
		{
			char[] suitSymbols = {'C', 'D', 'H', 'S'};
			suit = suitSymbols[id / suitSize];
			rank = id % suitSize;
		}
		
	}
	
	public Card getNext() {
		if (cardId % deckSize == deckSize - 1 || isJoker())
			return null;
		
		return new Card(cardId + 1);
	
	}
	
	public boolean isJoker(int id)
	{
		return NoOfDecks*deckSize <= id && id < NoOfDecks*deckSize + NoOfDecks ;
	}
	
	public boolean isJoker()
	{
		return NoOfDecks*deckSize <= cardId && cardId < NoOfDecks*deckSize + NoOfDecks ;		
	}
}
