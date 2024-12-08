package com.example.pokerjawa.model.card.combination;

import com.example.pokerjawa.model.card.Card;

import java.util.ArrayList;
import java.util.Collections;

public class PacketFlushCombination implements Combination {
	public Boolean isValid(ArrayList<Card> cards) {
		if (cards.size() != 5) {
			return false;
		}

		// same suit
		for (int i = 1; i < cards.size(); i++) {
			if (!cards.get(0).getSuit().equals(cards.get(i).getSuit())) {
				return false;
			}
		}

		// 5 in the row
		Collections.sort(cards, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());

		for (int i = 1; i < cards.size(); i++) {
			if (cards.get(i).getValue().getWorth() - 1 != cards.get(i - 1).getValue().getWorth()) {
				return false;
			}
		}

		return true;
	}

	public Integer compare(ArrayList<Card> combination1, ArrayList<Card> combination2) {
		Collections.sort(combination1, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());
		Collections.sort(combination2, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());

		Card card1 = combination1.get(4);
		Card card2 = combination2.get(4);

		if (card1.getValue().equals(card2.getValue())) {
			return card1.getSuit().getWorth() - card2.getSuit().getWorth();
		}
		return card1.getValue().getWorth() - card2.getValue().getWorth();
	}

	@Override
	public String getName() {
		return "Packet Flush Combination";
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