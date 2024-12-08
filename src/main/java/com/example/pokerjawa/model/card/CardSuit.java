package com.example.pokerjawa.model.card;

public enum CardSuit {
	SPADE(4), 
	CLUB(3), 
	HEART(2), 
	DIAMOND(1),;

	private final Integer worth;
	
	private CardSuit(int worth) {
		this.worth = worth;
	}

	public Integer getWorth() {
		return worth;
	}
}
