package com.libertymutual.blackjack.models;

import java.util.List;
import java.util.ArrayList;

public class Dealer {

	private Deck deck;
	private Hand hand;
	private boolean hideHole;

	public Dealer() {
		deck = new Deck();
		deck.shuffle();
		hand = new Hand();
	}

	public int getNumberOfCardsLeft() {
		return deck.getNumberofCardsLeft();
	}

	public void startRound() {
		hideHole = true;
		hand = new Hand();
	}

	public void finishRound() {
		hideHole = false;
		int[] values = hand.getValues();
		if (values[0] == 21 || values[1] == 21) {
			return;
		}
		while (values[0] < 17 || values[1] < 17) {
			dealCardToDealer();
			values = hand.getValues();
		}
	}

	public void dealCardToGambler(Gambler gambler) {
		Card card = deck.getCard();
		if (card != null) {
			gambler.takeCard(card);
		}
	}

	public void dealCardToDealer() {
		Card card = deck.getCard();
		if (card != null) {
			hand.addCard(card);
		}
	}

	public List<Card> getCards() {
		List<Card> cards = hand.getCards();
		if (!hideHole || cards.size() == 0) {
			return cards;
		}

		Card firstCard = cards.get(0);
		List<Card> cardsToShow = new ArrayList<Card>();
		cardsToShow.add(firstCard);
		cardsToShow.add(new HoleCard());
		return cardsToShow;
	}

	public int getNumberofCardsLeft() {
		return deck.getNumberofCardsLeft();
	}

	public boolean isBust() {
		int[] values = hand.getValues();
		if (values[0] > 21 && values[1] > 21)
			return true;
		{
			return false;
		}
	}

	public boolean hasBlackjack() {
		int[] values = hand.getValues();
		List<Card> cards = hand.getCards();
		if (cards.size() == 2 && (values[0] == 21 || values[1] == 21)) {
			return true;
		}
		return false;
	}

	public int getBestScore() {
		hand.getHighestValidValue();

		return hand.getHighestValidValue();
	}

	// not sure this is needed...
//	public void clearHand() {
//		hand = new Hand();
//	}
}
