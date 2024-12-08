package com.example.pokerjawa.model.factory;

import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.CardSuit;
import com.example.pokerjawa.model.card.CardValue;

public class CardFactory {

	public Card createCard(CardSuit suit, CardValue value) {
		return new Card(suit, value, toImageUrl(suit, value));
	}

	private String toImageUrl(CardSuit suit, CardValue value) {
		return suit.toString().toLowerCase() + "_" + value.getAbbreviation() + ".png";
	}

}
