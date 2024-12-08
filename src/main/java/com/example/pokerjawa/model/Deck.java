package com.example.pokerjawa.model;

import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.CardConstant;
import com.example.pokerjawa.model.card.CardSuit;
import com.example.pokerjawa.model.card.CardValue;
import com.example.pokerjawa.model.factory.CardFactory;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private ArrayList<Card> cards;
	private CardFactory cardFactory;
	private static Deck instance;
	
	private Deck() {
		cards = new ArrayList<>();
		cardFactory = new CardFactory();
		resetDeck();
	}

	public static Deck getInstance() {
		if (instance == null) {
			instance = new Deck();
		}
		return instance;
	}
	
	public void resetDeck() {
		cards.clear();
		fillCards();
		shuffleCards();		
	}

	private void shuffleCards() {
		Collections.shuffle(cards);
	}

	private void fillCards() {
		for (CardSuit suit : CardSuit.values()) {
			for (CardValue value : CardValue.values()) {
				Card card = cardFactory.createCard(suit, value);
				cards.add(card);
			}
		}
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public ArrayList<Card> giveSetOfCards() {
		ArrayList<Card> setOfCards = new ArrayList<>();
		for (int i = 0; i < CardConstant.CARD_COUNT_PER_SUIT; i++) {
			setOfCards.add(cards.remove(0));
		}
		return setOfCards;
	}

}
