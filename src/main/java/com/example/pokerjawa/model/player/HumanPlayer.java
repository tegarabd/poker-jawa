package com.example.pokerjawa.model.player;

public class HumanPlayer extends Player {

	private Integer id;
	private String password;
	private Integer score;
	private Integer gamePlayed;

	public HumanPlayer(Integer id, String username, String password, Integer score, Integer gamePlayed) {
		super(username);
		this.id = id;
		this.password = password;
		this.score = score;
		this.gamePlayed = gamePlayed;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getGamePlayed() {
		return gamePlayed;
	}

	public void setGamePlayed(Integer gamePlayed) {
		this.gamePlayed = gamePlayed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public void onCanMove() {

	}

}
