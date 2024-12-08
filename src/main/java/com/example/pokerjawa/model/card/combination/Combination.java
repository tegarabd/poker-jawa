package com.example.pokerjawa.model.card.combination;

import com.example.pokerjawa.model.card.Card;

import java.util.ArrayList;

public interface Combination {

	public String getName();

	public Integer getCardCount();

	public ArrayList<ArrayList<Card>> findCombinationInSetOfCard(ArrayList<Card> cards);

	public Boolean isValid(ArrayList<Card> cards);

	public Integer compare(ArrayList<Card> combination1, ArrayList<Card> combination2);

}
