package com.example.pokerjawa.view.component;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import com.example.pokerjawa.model.player.HumanPlayer;

public class HighscoreTableView extends TableView<HumanPlayer> {

	private TableColumn<HumanPlayer, String> usernameColumn;
	private TableColumn<HumanPlayer, Integer> scoreColumn;
	private TableColumn<HumanPlayer, Integer> gamePlayedColumn;

	@SuppressWarnings("unchecked")
	public HighscoreTableView() {

		double width = 1280 / 2;

		setPrefWidth(width);

		usernameColumn = new TableColumn<>("Username");
		scoreColumn = new TableColumn<>("Score");
		gamePlayedColumn = new TableColumn<>("Game Played");

		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
		gamePlayedColumn.setCellValueFactory(new PropertyValueFactory<>("gamePlayed"));

		usernameColumn.setPrefWidth(width / 3 - 15);
		scoreColumn.setPrefWidth(width / 3 - 15);
		gamePlayedColumn.setPrefWidth(width / 3 - 15);

		getColumns().addAll(usernameColumn, scoreColumn, gamePlayedColumn);

		style();
	}

	public void style() {
		usernameColumn.setCellFactory(new Callback<TableColumn<HumanPlayer, String>, TableCell<HumanPlayer, String>>() {
			@Override
			public TableCell<HumanPlayer, String> call(TableColumn<HumanPlayer, String> param) {
				return new TableCell<HumanPlayer, String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (!isEmpty()) {
							setFont(Font.font("Century Gothic", FontWeight.BOLD, 16));
							setText(item);
						}
					}
				};
			}
		});

		scoreColumn.setCellFactory(new Callback<TableColumn<HumanPlayer, Integer>, TableCell<HumanPlayer, Integer>>() {

			@Override
			public TableCell<HumanPlayer, Integer> call(TableColumn<HumanPlayer, Integer> param) {
				return new TableCell<HumanPlayer, Integer>() {
					@Override
					protected void updateItem(Integer item, boolean empty) {
						super.updateItem(item, empty);
						if (!isEmpty()) {
							setFont(Font.font("Century Gothic", FontWeight.BOLD, 16));
							setText(item.toString());
						}
					}
				};
			}

		});
		
		gamePlayedColumn.setCellFactory(new Callback<TableColumn<HumanPlayer, Integer>, TableCell<HumanPlayer, Integer>>() {

			@Override
			public TableCell<HumanPlayer, Integer> call(TableColumn<HumanPlayer, Integer> param) {
				return new TableCell<HumanPlayer, Integer>() {
					@Override
					protected void updateItem(Integer item, boolean empty) {
						super.updateItem(item, empty);
						if (!isEmpty()) {
							setFont(Font.font("Century Gothic", FontWeight.BOLD, 16));
							setText(item.toString());
						}
					}
				};
			}

		});
	}

}
