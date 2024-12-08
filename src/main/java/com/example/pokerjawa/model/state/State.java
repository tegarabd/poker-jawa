package com.example.pokerjawa.model.state;

import com.example.pokerjawa.model.player.Player;

public abstract class State {
	
	protected Player player;

	public State(Player player) {
		super();
		this.player = player;
	}
	
	public abstract void onPreviousPlayerFinishMove();
	public abstract void onThisPlayerFinishMove();

}
