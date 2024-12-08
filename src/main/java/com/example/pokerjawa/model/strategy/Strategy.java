package com.example.pokerjawa.model.strategy;

import com.example.pokerjawa.model.card.Card;

import java.util.ArrayList;

public interface Strategy {
	public ArrayList<Card> getSelectedCombination(ArrayList<ArrayList<Card>> combinations);
}
