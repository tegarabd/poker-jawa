package com.example.pokerjawa.model.player;

import com.example.pokerjawa.model.Game;
import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.state.State;
import com.example.pokerjawa.model.state.WaitState;

import java.util.ArrayList;

public abstract class Player {
	protected String username;
	protected ArrayList<Card> cards;
	protected ArrayList<Card> temporaryCards;
	protected State state;
	protected Game game;
	protected Boolean canMove;

	public Player(String username) {
		super();
		this.username = username;
		this.cards = new ArrayList<>();
		this.temporaryCards = new ArrayList<>();
		this.canMove = false;
		this.state = new WaitState(this);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public Boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(Boolean canMove) {
		if (canMove) {
			onCanMove();
		}
		this.canMove = canMove;
	}

	public void changeState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public ArrayList<Card> getTemporaryCards() {
		return temporaryCards;
	}

	public void setTemporaryCards(ArrayList<Card> temporaryCards) {
		this.temporaryCards = temporaryCards;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public abstract void onCanMove();

}
