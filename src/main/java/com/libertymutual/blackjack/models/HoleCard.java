package com.libertymutual.blackjack.models;

public class HoleCard implements Card {

	@Override
	public String getSuit() {
		return "";
	}

	@Override
	public String getVisualRepresentation() {
		
		return "????";
	}

	@Override
	public int[] getValues() {
		return new int[] { 0,0 };
	}

}
