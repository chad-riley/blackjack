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
			// TODO Auto-generated method stub
			return bet;
		}

		public void payout(int i) {
			// TODO Auto-generated method stub
			
		}
		
		public boolean hasBlackjack() {
			return false;
		}

		public int getBestScore() {
			// TODO Auto-generated method stub
			return 0;
		}

}