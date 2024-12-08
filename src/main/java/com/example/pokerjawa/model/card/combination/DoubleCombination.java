package com.example.pokerjawa.model.card.combination;

import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.CardSuit;
import com.example.pokerjawa.model.card.CardValue;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author tegarabd
 * 
 *         Double combination contains 2 cards. That 2 cards must have same
 *         value. Combination with larger value will win against combination
 *         with lower value. If 2 combinations have same value, the winner is
 *         the combination that contains SPADE suit card.
 * 
 */
public class DoubleCombination implements Combination {

	public Boolean isValid(ArrayList<Card> cards) {
		if (cards.size() != 2) {
			return false;
		}

		return cards.get(0).getValue().equals(cards.get(1).getValue());
	}

	public Integer compare(ArrayList<Card> combination1, ArrayList<Card> combination2) {
		CardValue combination1Value = combination1.get(0).getValue();
		CardValue combination2Value = combination2.get(0).getValue();

		if (combination1Value == combination2Value) {
			// check who has spade
			Card spadeInCombination1 = combination1.stream().filter(card -> card.getSuit().equals(CardSuit.SPADE))
					.findAny().orElse(null);
			if (spadeInCombination1 == null) {
				return -1;
			}
			return 1;
		}

		return combination1Value.getWorth() - combination2Value.getWorth();
	}

	@Override
	public String getName() {
		return "Double Combination";
	}

	@Override
	public Integer getCardCount() {
		return 2;
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
