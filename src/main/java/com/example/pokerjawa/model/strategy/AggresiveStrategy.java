package com.example.pokerjawa.model.strategy;

import com.example.pokerjawa.model.card.Card;

import java.util.ArrayList;

public class AggresiveStrategy implements Strategy {

	@Override
	public ArrayList<Card> getSelectedCombination(ArrayList<ArrayList<Card>> combinations) {
		return combinations.get(combinations.size() - 1);
	}

}
