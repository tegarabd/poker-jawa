package com.example.pokerjawa.model.builder;

import com.example.pokerjawa.model.player.HumanPlayer;

public class HumanPlayerBuilder {

	private Integer id;
	private String username;
	private String password;
	private Integer score;
	private Integer playedGame;

	public HumanPlayerBuilder() {
		reset();
	}

	public void reset() {
		username = "";
		password = "";
		score = 0;
		playedGame = 0;
	}

	public HumanPlayer build() {
		return new HumanPlayer(id, username, password, score, playedGame);
	}

	public HumanPlayerBuilder setUsername(String username) {
		this.username = username;
		return this;
	}

	public HumanPlayerBuilder setScore(Integer score) {
		this.score = score;
		return this;
	}

	public HumanPlayerBuilder setPlayedGame(Integer playedGame) {
		this.playedGame = playedGame;
		return this;
	}

	public HumanPlayerBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public HumanPlayerBuilder setId(Integer id) {
		this.id = id;
		return this;
	}

}
