package com.libertymutual.blackjack.models;

import java.util.List;

public class Gambler {

	private Hand hand;
	private int playerMoney;
	
	public Gambler() {
		playerMoney = 250;
		hand = new Hand();
	}
	
	public int getplayerMoney() {
		return playerMoney;
	}
	
	public void giveCard(Card cardToDeal) {
		hand.addCard(cardToDeal);
		}
	
		public Hand getHand() {
		return hand;
		}

		public boolean isBust() {
			int [] values = hand.getValues();
			if (values[0] > 21 && values[1] > 21) {
				return true;
			}
			return false;
		}

		public int getAvailableCash() {
			return playerMoney;
		}

		public int ante(int ante) {	
			hand = new Hand();
			ante = Math.min(ante, playerMoney);
			playerMoney -= ante;
			return ante;
		}
		
		public void takeCard(Card card) {
			hand.addCard(card);
		}
		
		public List<Card> getCards(){
			return hand.getCards();
		}

		public void payout(int i) {
			playerMoney = playerMoney + i;
			
		}
		
		public boolean hasBlackjack() {
			return hand.isBlackjack();
		}

		public int getBestScore() {
			hand.getHighestValidValue();
			
			return hand.getHighestValidValue();
		}

		//Do I need this?
		public void clearHand() {
			hand = new Hand();
		}

}