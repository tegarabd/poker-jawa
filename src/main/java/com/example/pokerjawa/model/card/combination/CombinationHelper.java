package com.example.pokerjawa.model.card.combination;

import com.example.pokerjawa.model.card.Card;

import java.util.ArrayList;

public class CombinationHelper {

	private ArrayList<Combination> allowedCombinations;
	private SingleCombination singleCombination;
	private DoubleCombination doubleCombination;
	private TripleThreesCombination tripleThreesCombination;
	private TripleColorCombination tripleColorCombination;
	private PacketStraightCombination packetStraightCombination;
	private PacketColorCombination packetColorCombination;
	private PacketFullHouseCombination packetFullHouseCombination;
	private PacketSplitCombination packetSplitCombination;
	private PacketFlushCombination packetFlushCombination;
	private static CombinationHelper instance;

	private CombinationHelper() {
		allowedCombinations = new ArrayList<>();

		singleCombination = new SingleCombination();
		doubleCombination = new DoubleCombination();
		tripleThreesCombination = new TripleThreesCombination();
		tripleColorCombination = new TripleColorCombination();
		packetStraightCombination = new PacketStraightCombination();
		packetColorCombination = new PacketColorCombination();
		packetFullHouseCombination = new PacketFullHouseCombination();
		packetSplitCombination = new PacketSplitCombination();
		packetFlushCombination = new PacketFlushCombination();

		allowedCombinations.add(singleCombination);
		allowedCombinations.add(doubleCombination);
		allowedCombinations.add(tripleThreesCombination);
		allowedCombinations.add(tripleColorCombination);
		allowedCombinations.add(packetStraightCombination);
		allowedCombinations.add(packetColorCombination);
		allowedCombinations.add(packetFullHouseCombination);
		allowedCombinations.add(packetSplitCombination);
		allowedCombinations.add(packetFlushCombination);
	}

	public static CombinationHelper getInstance() {
		if (instance == null) {
			instance = new CombinationHelper();
		}
		return instance;
	}

	public SingleCombination getSingleCombination() {
		return singleCombination;
	}

	public DoubleCombination getDoubleCombination() {
		return doubleCombination;
	}

	public TripleThreesCombination getTripleThreesCombination() {
		return tripleThreesCombination;
	}

	public TripleColorCombination getTripleColorCombination() {
		return tripleColorCombination;
	}

	public PacketStraightCombination getPacketStraightCombination() {
		return packetStraightCombination;
	}

	public PacketColorCombination getPacketColorCombination() {
		return packetColorCombination;
	}

	public PacketFullHouseCombination getPacketFullHouseCombination() {
		return packetFullHouseCombination;
	}

	public PacketSplitCombination getPacketSplitCombination() {
		return packetSplitCombination;
	}

	public PacketFlushCombination getPacketFlushCombination() {
		return packetFlushCombination;
	}

	public Combination recognizeCombination(ArrayList<Card> cards) {
		for (Combination combination : allowedCombinations) {
			if (combination.isValid(cards))
				return combination;
		}
		return null;
	}

	public ArrayList<ArrayList<Card>> getPossibleCombinations(ArrayList<Card> cards) {
		ArrayList<ArrayList<Card>> combinations = new ArrayList<>();

		for (Combination combination : allowedCombinations) {
			for (ArrayList<Card> cardCombination : combination.findCombinationInSetOfCard(cards)) {
				combinations.add(cardCombination);
			}
		}

		return combinations;
	}
}
