package com.example.pokerjawa.model.card;

public class Card {

	private CardSuit suit;
	private CardValue value;
	private String imageUrl;

	public Card(CardSuit suit, CardValue value, String imageUrl) {
		super();
		this.suit = suit;
		this.value = value;
		this.imageUrl = imageUrl;
	}

	public CardSuit getSuit() {
		return suit;
	}

	public void setSuit(CardSuit suit) {
		this.suit = suit;
	}

	public CardValue getValue() {
		return value;
	}

	public void setValue(CardValue value) {
		this.value = value;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}