package com.example.pokerjawa.model.card.combination;

import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.CardValue;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author tegarabd
 * 
 *         Triple threes combination contains 3 cards. That 3 cards must have
 *         same value. Combination with larger value will win against
 *         combination with lower value.
 * 
 */
public class TripleThreesCombination implements Combination {
	public Boolean isValid(ArrayList<Card> cards) {
		if (cards.size() != 3) {
			return false;
		}

		Collections.sort(cards, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());

		return cards.get(0).getValue().equals(cards.get(2).getValue());
	}

	public Integer compare(ArrayList<Card> combination1, ArrayList<Card> combination2) {
		CardValue combination1Value = combination1.get(0).getValue();
		CardValue combination2Value = combination2.get(0).getValue();

		return combination1Value.getWorth() - combination2Value.getWorth();
	}

	@Override
	public String getName() {
		return "Triple Threes Combination";
	}

	@Override
	public Integer getCardCount() {
		return 3;
	}

	@Override
	public ArrayList<ArrayList<Card>> findCombinationInSetOfCard(ArrayList<Card> cards) {
		Collections.sort(cards, (card1, card2) -> {
			if (card1.getValue().equals(card2.getValue())) {
				return card1.getSuit().getWorth() - card2.getSuit().getWorth();
			}
			return card1.getValue().getWorth() - card2.getValue().getWorth();
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
