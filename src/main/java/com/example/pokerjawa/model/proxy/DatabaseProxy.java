package com.example.pokerjawa.model.proxy;

import com.example.pokerjawa.model.database.DatabaseHelper;
import com.example.pokerjawa.model.player.HumanPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseProxy {

	private String username;
	private String password;
	private HumanPlayer currentPlayerCache;
	private ArrayList<HumanPlayer> playersCache;

	private static DatabaseProxy instance;

	private DatabaseProxy() {
		super();
	}

	public static DatabaseProxy getInstance() {
		if (instance == null) {
			instance = new DatabaseProxy();
		}
		return instance;
	}

	public HumanPlayer getCurrentPlayer() {

		if (currentPlayerCache == null) {
			try {
				fetchCurrentPlayer();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		return currentPlayerCache;
	}

	private void fetchCurrentPlayer() throws ClassNotFoundException, SQLException {
		ResultSet resultSet = DatabaseHelper.executeQuery(
				String.format("SELECT * FROM players WHERE username = '%s' AND password = '%s'", username, password));
		if (resultSet.next()) {
			currentPlayerCache = createPlayerFromSet(resultSet);
			return;
		}
		currentPlayerCache = null;
	}

	private HumanPlayer createPlayerFromSet(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String password = resultSet.getString("password");
		Integer score = resultSet.getInt("score");
		Integer playedGame = resultSet.getInt("game_played");
		return new HumanPlayer(id, username, password, score, playedGame);
	}

	public ArrayList<HumanPlayer> getPlayers() {
		if (playersCache == null || playersCache.isEmpty()) {
			try {
				fetchPlayers();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return playersCache;
	}

	private void fetchPlayers() throws ClassNotFoundException, SQLException {
		ResultSet resultSet = DatabaseHelper
				.executeQuery(String.format("SELECT * FROM players ORDER BY score DESC LIMIT 10"));
		playersCache = new ArrayList<>();
		while (resultSet.next()) {
			playersCache.add(createPlayerFromSet(resultSet));
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addPlayer(HumanPlayer humanPlayer) {
		try {
			String username = humanPlayer.getUsername();
			String password = humanPlayer.getPassword();
			DatabaseHelper.executeUpdate(
					String.format("INSERT INTO players (username, password) VALUES ('%s', '%s')", username, password));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void addPlayerScore(Integer score) {
		try {
			Integer id = currentPlayerCache.getId();
			DatabaseHelper.executeUpdate(String.format(
					"UPDATE players SET score = score + %d, game_played = game_played + 1  WHERE id = '%d'", score,
					id));
			fetchPlayers();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
