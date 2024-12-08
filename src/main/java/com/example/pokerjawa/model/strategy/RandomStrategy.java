package com.example.pokerjawa.model.strategy;

import com.example.pokerjawa.model.card.Card;

import java.util.ArrayList;
import java.util.Random;

public class RandomStrategy implements Strategy {

	private Random random;

	public RandomStrategy() {
		random = new Random();
	}

	@Override
	public ArrayList<Card> getSelectedCombination(ArrayList<ArrayList<Card>> combinations) {
		return combinations.get(random.nextInt(combinations.size()));
	}

}
