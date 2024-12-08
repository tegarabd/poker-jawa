package com.example.pokerjawa.model.state;

import com.example.pokerjawa.model.player.Player;

public class MoveState extends State {

	public MoveState(Player player) {
		super(player);
		player.setCanMove(true);
	}

	@Override
	public void onPreviousPlayerFinishMove() {

	}

	@Override
	public void onThisPlayerFinishMove() {
		player.changeState(new WaitState(player));
	}

}
