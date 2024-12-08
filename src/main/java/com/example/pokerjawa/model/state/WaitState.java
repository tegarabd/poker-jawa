package com.example.pokerjawa.model.state;

import com.example.pokerjawa.model.player.Player;

public class WaitState extends State {

	public WaitState(Player player) {
		super(player);
		player.setCanMove(false);
	}

	@Override
	public void onPreviousPlayerFinishMove() {
		player.changeState(new MoveState(player));
	}

	@Override
	public void onThisPlayerFinishMove() {
		
	}

	

	

	

}
