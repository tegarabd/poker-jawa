package com.example.pokerjawa.model.player;

import java.util.HashMap;

public class PlayerConstant {

	public static final Integer PLAYER_COUNT = 4;

	@SuppressWarnings("serial")
	public static final HashMap<Integer, Integer> PLAYER_WINNER_SCORE = new HashMap<>() {
		{
			put(1, 100);
			put(2, 50);
			put(3, 25);
			put(4, 5);
		}
	};

}
