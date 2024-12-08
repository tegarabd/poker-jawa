package com.example.pokerjawa.model.player;

import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.combination.CombinationHelper;
import com.example.pokerjawa.model.strategy.Strategy;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BotPlayer extends Player {

	private Strategy strategy;

	public BotPlayer(String username, Strategy strategy) {
		super(username);
		this.strategy = strategy;
	}

	@Override
	public void onCanMove() {
		ArrayList<ArrayList<Card>> possibleCombinations;
		ArrayList<Card> selectedCombination;

		if (game.getCurrentCombination() == null || game.getLastPlayerMoved().equals(this)) {
			possibleCombinations = CombinationHelper.getInstance().getPossibleCombinations(cards);
		} else {
			possibleCombinations = (ArrayList<ArrayList<Card>>) game
					.getCurrentCombination().findCombinationInSetOfCard(cards).stream().filter(combination -> game
							.getCurrentCombination().compare(combination, game.getCurrentCardCombination()) > 0)
					.collect(Collectors.toList());
		}

		if (possibleCombinations.size() == 0) {
			return;
		}
		selectedCombination = strategy.getSelectedCombination(possibleCombinations);
		moveSelectedCombinationToTemporary(selectedCombination);
	}

	private void moveSelectedCombinationToTemporary(ArrayList<Card> selectedCombination) {
		game.setCurrentCombination(CombinationHelper.getInstance().recognizeCombination(selectedCombination));
		game.setCurrentCardCombination(selectedCombination);
		game.setLastPlayerMoved(this);
		temporaryCards.clear();
		cards.removeAll(selectedCombination);
		temporaryCards.addAll(selectedCombination);
	}

}
