package com.libertymutual.blackjack.models;

public class FaceCard implements Card {

	private String suit ;
	private String visualRepresentation;

	public FaceCard(String visualRepresentation, String suit) {
		this.suit = suit;
		this.visualRepresentation = visualRepresentation;
	}
	
	@Override
	public String toString() {
		return this.getVisualRepresentation() + " of " + this.getSuit();
	}
	

	public String getSuit() {
		return suit;
	}

	public String getVisualRepresentation() {
		return visualRepresentation;
	}

	@Override
	public int[] getValues() {
		return new int[] {10,10 };
	}
}
