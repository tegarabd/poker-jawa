package com.example.pokerjawa.model.card.combination;

import com.example.pokerjawa.model.card.Card;

import java.util.ArrayList;
import java.util.Collections;

public class PacketColorCombination implements Combination {
	public Boolean isValid(ArrayList<Card> cards) {
		if (cards.size() != 5) {
			return false;
		}

		for (int i = 1; i < cards.size(); i++) {
			if (!cards.get(0).getSuit().equals(cards.get(i).getSuit())) {
				return false;
			}
		}

		return true;
	}

	public Integer compare(ArrayList<Card> combination1, ArrayList<Card> combination2) {
		Collections.sort(combination1, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());
		Collections.sort(combination2, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());

		if (combination1.get(0).getSuit().equals(combination2.get(0).getSuit())) {
			return combination1.get(4).getValue().getWorth() - combination2.get(4).getValue().getWorth();
		}

		return combination1.get(0).getSuit().getWorth() - combination2.get(0).getSuit().getWorth();
	}

	@Override
	public String getName() {
		return "Packet Color Combination";
	}

	@Override
	public Integer getCardCount() {
		return 5;
	}

	@Override
	public ArrayList<ArrayList<Card>> findCombinationInSetOfCard(ArrayList<Card> cards) {
		Collections.sort(cards, (card1, card2) -> {
			if (card1.getSuit().equals(card2.getSuit())) {
				return card1.getValue().getWorth() - card2.getValue().getWorth();
			}
			return card1.getSuit().getWorth() - card2.getSuit().getWorth();
		});

		ArrayList<ArrayList<Card>> combinations = new ArrayList<>();

		for (int i = 0; i < cards.size() - (getCardCount() - 1); i++) {
			ArrayList<Card> combination = new ArrayList<>();
			for (int j = i; j < getCardCount() + i; j++) {
				combination.add(cards.get(j));
			}
			if (isValid(combination)) {
				combinations.add(combination);
			}
		}

		return combinations;
	}
}
