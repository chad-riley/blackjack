package com.libertymutual.blackjack.models;

public class Dealer {

	private Hand hand;
	
	public Dealer () {
		this.hand = new Hand();
	}

	public void giveCard(Card cardToDeal) {
		hand.addCard(cardToDeal);			
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public void finishHitting(Deck deck) {
		int [] values = hand.getValues(); 
		
		while (values[0] < 17 || values[1] <17 ) {
			Card theNextCard = deck.getCard();
			hand.addCard(theNextCard);
			values = hand.getValues();	
		}	
	}

	public int getNumberofCardsLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isBust() {
		int [] values = hand.getValues();
		if (values[0] > 21 && values[1] > 21);
		return true;
	}
	
	public boolean hasBlackjack() {
		
		return false;
	}

	public int getBestScore() {
		// TODO Auto-generated method stub
		return 0;
	}
}
	