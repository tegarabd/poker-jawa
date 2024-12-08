package com.example.pokerjawa.model.strategy;

import com.example.pokerjawa.model.card.Card;

import java.util.ArrayList;

public class ConservativeStrategy implements Strategy {

	@Override
	public ArrayList<Card> getSelectedCombination(ArrayList<ArrayList<Card>> combinations) {
		return combinations.get(0);
	}

}
