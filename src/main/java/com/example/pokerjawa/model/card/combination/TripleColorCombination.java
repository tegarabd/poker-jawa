package com.example.pokerjawa.model.card.combination;

import com.example.pokerjawa.model.card.Card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author tegarabd
 * 
 *         Triple color combination contains 3 cards. That 3 cards must have
 *         same suit and triple in the row (4, 5, 6), (7, 8, 9). Combination
 *         with larger value will win against combination with lower value. If 2
 *         combinations have same value, the winner is the combination that have
 *         larger suit.
 * 
 */
public class TripleColorCombination implements Combination {
	public Boolean isValid(ArrayList<Card> cards) {
		if (cards.size() != 3) {
			return false;
		}

		Card card1 = cards.get(0);
		Card card2 = cards.get(1);
		Card card3 = cards.get(2);

		// all cards have same suit
		if (!(card1.getSuit().equals(card2.getSuit()) && card2.getSuit().equals(card3.getSuit()))) {
			return false;
		}

		// triple in the row
		return (card1.getValue().getWorth() + 1 == card2.getValue().getWorth()
				&& card2.getValue().getWorth() == card3.getValue().getWorth() - 1);
	}

	public Integer compare(ArrayList<Card> combination1, ArrayList<Card> combination2) {
		Card card1 = combination1.get(0);
		Card card2 = combination2.get(0);

		if (card1.getValue().equals(card2.getValue())) {
			return card1.getSuit().getWorth() - card2.getSuit().getWorth();
		}
		return card1.getValue().getWorth() - card2.getValue().getWorth();
	}

	@Override
	public String getName() {
		return "Triple Color Combination";
	}

	@Override
	public Integer getCardCount() {
		return 3;
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
