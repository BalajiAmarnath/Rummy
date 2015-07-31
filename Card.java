class Card implements Comparable<Card>{
	private int cardId;
	private int rank;
	private char suit;
	
	private final int suitSize = 13;
	private final int deckSize = 52;
	
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
	
		if(!isJoker(cardId))
		{
			char[] suitSymbols = {'C', 'D', 'H', 'S'};
			suit = suitSymbols[id / suitSize];
			rank = id % suitSize;
		}
		
	}
	
	public Card getNext() {
		if (cardId  == deckSize - 1 || isJoker())
			return null;
		
		return new Card(cardId + 1);
	
	}
	
	public boolean isJoker(int id)
	{
		return id == deckSize;
	}
	
	public boolean isJoker()
	{
		return cardId == deckSize;
	}
	
	public int compareTo(Card card) {
		return this.cardId - card.cardId;
	}
	
	public boolean isSame(Card card) {
		return this.cardId == card.cardId;
	}
	
}
