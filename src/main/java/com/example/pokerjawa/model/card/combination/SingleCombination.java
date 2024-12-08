package com.example.pokerjawa.model.card.combination;

import com.example.pokerjawa.model.card.Card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author tegarabd
 * 
 *         Single combination contains 1 card. Card with larger value will win
 *         against card with lower value. If 2 cards have same value, the winner
 *         is the card that have larger suit.
 * 
 */
public class SingleCombination implements Combination {

	public Boolean isValid(ArrayList<Card> cards) {
		if (cards.size() != 1)
			return false;
		return true;
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
		return "Single Combination";
	}

	@Override
	public Integer getCardCount() {
		return 1;
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

		for (Card card : cards) {
			ArrayList<Card> combination = new ArrayList<>();
			combination.add(card);
			combinations.add(combination);
		}

		return combinations;
	}
}
