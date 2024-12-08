package com.example.pokerjawa.model;

import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.CardSuit;
import com.example.pokerjawa.model.card.CardValue;
import com.example.pokerjawa.model.card.combination.Combination;
import com.example.pokerjawa.model.observer.Observer;
import com.example.pokerjawa.model.player.BotPlayer;
import com.example.pokerjawa.model.player.Player;
import com.example.pokerjawa.model.player.PlayerConstant;
import com.example.pokerjawa.model.proxy.DatabaseProxy;
import com.example.pokerjawa.model.state.MoveState;
import com.example.pokerjawa.model.strategy.AggresiveStrategy;
import com.example.pokerjawa.model.strategy.ConservativeStrategy;
import com.example.pokerjawa.model.strategy.RandomStrategy;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Game implements Observer<Player> {

	private Deck deck;
	private ArrayList<Player> players;
	private Integer currentPlayerIndex;
	private Combination currentCombination;
	private ArrayList<Card> currentCardCombination;
	private Player lastPlayerMoved;

	public Game() {
		deck = Deck.getInstance();
		deck.resetDeck();
		players = new ArrayList<>(PlayerConstant.PLAYER_COUNT);
		subscribe(DatabaseProxy.getInstance().getCurrentPlayer());
		generateBotPlayers();
		givePlayerCards();
	}

	private void givePlayerCards() {
		for (Player player : players) {
			player.setCards(deck.giveSetOfCards());
		}
	}

	private void generateBotPlayers() {
		subscribe(new BotPlayer("Bot 1", new ConservativeStrategy()));
		subscribe(new BotPlayer("Bot 2", new AggresiveStrategy()));
		subscribe(new BotPlayer("Bot 3", new RandomStrategy()));
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	private void notifyNextPlayer() {
		// last Player win
		if (lastPlayerMoved == null) {
			if (currentPlayerIndex >= players.size()) {
				currentPlayerIndex = 0;
			}
			players.get(currentPlayerIndex).getState().onPreviousPlayerFinishMove();
		} else {
			players.get(currentPlayerIndex).getState().onThisPlayerFinishMove();
			if (++currentPlayerIndex >= players.size()) {
				currentPlayerIndex = 0;
			}
			players.get(currentPlayerIndex).getState().onPreviousPlayerFinishMove();
		}
	}

	public void start() {
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			ArrayList<Card> threes = (ArrayList<Card>) player.getCards().stream()
					.filter(card -> card.getValue().equals(CardValue.THREE)).collect(Collectors.toList());
			player.setTemporaryCards(threes);
			player.getCards().removeAll(threes);
		}
	}

	public void finish() {

	}

	public void selectFirstPlayer() {
		ArrayList<Integer> playerWithTwoThreesIndexes = new ArrayList<>();

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);

			// has 3 or 4 threes
			if (player.getTemporaryCards().size() >= 3) {
				currentPlayerIndex = i;
				getCurrentPlayer().changeState(new MoveState(getCurrentPlayer()));
				return;
			}

			// has 2 threes
			if (player.getTemporaryCards().size() == 2) {
				playerWithTwoThreesIndexes.add(i);
			}
		}

		// only one player has 2 threes
		if (playerWithTwoThreesIndexes.size() == 1) {
			currentPlayerIndex = playerWithTwoThreesIndexes.get(0);
			getCurrentPlayer().changeState(new MoveState(getCurrentPlayer()));
			return;
		}

		// check who has spade three
		for (int i = 0; i < players.size(); i++) {
			Card spadeThree = players.get(i).getTemporaryCards().stream()
					.filter(card -> card.getSuit().equals(CardSuit.SPADE)).findAny().orElse(null);
			if (spadeThree != null) {
				currentPlayerIndex = i;
				getCurrentPlayer().changeState(new MoveState(getCurrentPlayer()));
				return;
			}
		}
	}

	@Override
	public void notifySubcribers() {
		notifyNextPlayer();
	}

	@Override
	public void subscribe(Player subcriber) {
		subcriber.setGame(this);
		players.add(subcriber);
	}

	@Override
	public void unsubscribe(Player subcriber) {
		players.remove(subcriber);
	}

	public Combination getCurrentCombination() {
		return currentCombination;
	}

	public void setCurrentCombination(Combination currentCombination) {
		this.currentCombination = currentCombination;
	}

	public ArrayList<Card> getCurrentCardCombination() {
		return currentCardCombination;
	}

	public void setCurrentCardCombination(ArrayList<Card> currentCardCombination) {
		this.currentCardCombination = currentCardCombination;
	}

	public Player getLastPlayerMoved() {
		return lastPlayerMoved;
	}

	public void setLastPlayerMoved(Player lastPlayerMoved) {
		this.lastPlayerMoved = lastPlayerMoved;
	}

}
