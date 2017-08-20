package com.libertymutual.blackjack.models;

public class Gambler {

	private Hand hand;
	
	public Gambler() {
		hand = new Hand();
	}

	public void giveCard(Card cardToDeal) {
		hand.addCard(cardToDeal);
		}
	
		public Hand getHand() {
		return hand;
		}

		public boolean isBust() {
			int [] values = hand.getValues();
			if (values[0] > 21 && values[1] > 21);
			return true;
		}

		public int getAvailableCash() {
			// TODO Auto-generated method stub
			return 0;
		}

		public int ante(int bet) {
			
			return bet;
		}

		public void payout(int i) {
			// TODO Auto-generated method stub
			
		}
		
		public boolean hasBlackjack() {
			
			int [] values = hand.getValues();
			
			if (cards.size() == 2 && (values[0] == 21 || values[1] == 21) );
			
			return true;
		}

		public int getBestScore() {
			// TODO Auto-generated method stub
			return 0;
		}

		public void clearHand() {
			hand = new Hand();
		}

}