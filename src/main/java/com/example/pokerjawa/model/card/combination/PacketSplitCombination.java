package com.example.pokerjawa.model.card.combination;

import com.example.pokerjawa.model.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class PacketSplitCombination implements Combination {
	public Boolean isValid(ArrayList<Card> cards) {

		if (new HashSet<>(cards).size() != 5) {
			return false;
		}

		Collections.sort(cards, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());

		// 4 4 5 6 7
		// 5 6 7 9 9
		return ((cards.get(0).getValue().equals(cards.get(1).getValue())
				&& (cards.get(2).getValue().getWorth() + 1 == cards.get(3).getValue().getWorth()
						&& cards.get(3).getValue().getWorth() == cards.get(4).getValue().getWorth() - 1))
				|| (cards.get(3).getValue().equals(cards.get(4).getValue())
						&& (cards.get(0).getValue().getWorth() + 1 == cards.get(1).getValue().getWorth()
								&& cards.get(1).getValue().getWorth() == cards.get(2).getValue().getWorth() - 1)));
	}

	public Integer compare(ArrayList<Card> combination1, ArrayList<Card> combination2) {
		Collections.sort(combination1, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());
		Collections.sort(combination2, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());

		Card card1;
		Card card2;

		// color on the left
		if (combination1.get(3).getValue().equals(combination1.get(4).getValue())) {
			card1 = combination1.get(2);
		}
		// color on the right
		else {
			card1 = combination1.get(4);
		}

		// color on the left
		if (combination2.get(3).getValue().equals(combination2.get(4).getValue())) {
			card2 = combination2.get(2);
		}
		// color on the right
		else {
			card2 = combination2.get(4);
		}

		if (card1.getValue().equals(card2.getValue())) {
			return card1.getSuit().getWorth() - card2.getSuit().getWorth();
		}
		return card1.getValue().getWorth() - card2.getValue().getWorth();
	}

	@Override
	public String getName() {
		return "Packet Split Combination";
	}

	@Override
	public Integer getCardCount() {
		return 5;
	}

	@Override
	public ArrayList<ArrayList<Card>> findCombinationInSetOfCard(ArrayList<Card> cards) {
		ArrayList<ArrayList<Card>> combinations = new ArrayList<>();

		ArrayList<ArrayList<Card>> tripleColorCombinations = CombinationHelper.getInstance().getTripleColorCombination()
				.findCombinationInSetOfCard(cards);
		ArrayList<ArrayList<Card>> doubleCombinations = CombinationHelper.getInstance().getDoubleCombination()
				.findCombinationInSetOfCard(cards);

		for (ArrayList<Card> tripleColorCombination : tripleColorCombinations) {
			for (ArrayList<Card> doubleCombination : doubleCombinations) {
				ArrayList<Card> combination = new ArrayList<>();
				combination.addAll(tripleColorCombination);
				combination.addAll(doubleCombination);
				if (isValid(combination)) {
					combinations.add(combination);
				}
			}
		}

		return combinations;
	}
}
