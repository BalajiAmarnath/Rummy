class Card {
	private int cardId;
	private int rank;
	private char suit;
	
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
		return null;
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
