package com.example.pokerjawa.model.card;

public enum CardValue {
	TWO("2", 13),
	THREE("3", 1),
	FOUR("4", 2),
	FIVE("5", 3),
	SIX("6", 4),
	SEVEN("7", 5),
	EIGHT("8", 6),
	NINE("9", 7),
	TEN("10", 8),
	JACK("j", 9),
	QUEEN("q", 10),
	KING("k", 11),
	ACE("a", 12);
	
	private final String abbreviation;
	private final Integer worth;
	
	private CardValue(String abbreviation, Integer worth) {
		this.abbreviation = abbreviation;
		this.worth = worth;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}

	public Integer getWorth() {
		return worth;
	}
}
