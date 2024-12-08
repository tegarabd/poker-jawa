package com.example.pokerjawa.model.card.combination;

import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.CardValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class PacketFullHouseCombination implements Combination {

	public Boolean isValid(ArrayList<Card> cards) {

		if (new HashSet<>(cards).size() != 5) {
			return false;
		}

		Collections.sort(cards, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());

		return (cards.get(0).getValue().equals(cards.get(1).getValue())
				&& cards.get(2).getValue().equals(cards.get(4).getValue())
				|| cards.get(0).getValue().equals(cards.get(2).getValue())
						&& cards.get(3).getValue().equals(cards.get(4).getValue()));
	}

	public Integer compare(ArrayList<Card> combination1, ArrayList<Card> combination2) {
		Collections.sort(combination1, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());
		Collections.sort(combination2, (card1, card2) -> card1.getValue().getWorth() - card2.getValue().getWorth());

		CardValue combination1Value;
		CardValue combination2Value;

		// threes on the left
		if (combination1.get(0).getValue().equals(combination1.get(2).getValue())) {
			combination1Value = combination1.get(0).getValue();
		}
		// threes on the right
		else {
			combination1Value = combination1.get(4).getValue();
		}

		// threes on the left
		if (combination2.get(0).getValue().equals(combination2.get(2).getValue())) {
			combination2Value = combination2.get(0).getValue();
		}
		// threes on the right
		else {
			combination2Value = combination2.get(4).getValue();
		}

		return combination1Value.getWorth() - combination2Value.getWorth();
	}

	@Override
	public String getName() {
		return "Packet Full House Combination";
	}

	@Override
	public Integer getCardCount() {
		return 5;
	}

	@Override
	public ArrayList<ArrayList<Card>> findCombinationInSetOfCard(ArrayList<Card> cards) {
		ArrayList<ArrayList<Card>> combinations = new ArrayList<>();

		ArrayList<ArrayList<Card>> tripleThreesCombinations = CombinationHelper.getInstance()
				.getTripleThreesCombination().findCombinationInSetOfCard(cards);
		ArrayList<ArrayList<Card>> doubleCombinations = CombinationHelper.getInstance().getDoubleCombination()
				.findCombinationInSetOfCard(cards);

		for (ArrayList<Card> tripleThreesCombination : tripleThreesCombinations) {
			for (ArrayList<Card> doubleCombination : doubleCombinations) {
				ArrayList<Card> combination = new ArrayList<>();
				combination.addAll(tripleThreesCombination);
				combination.addAll(doubleCombination);
				if (isValid(combination)) {
					combinations.add(combination);
				}
			}
		}

		return combinations;
	}
}
