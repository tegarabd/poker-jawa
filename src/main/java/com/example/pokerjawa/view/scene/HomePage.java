package com.example.pokerjawa.view.scene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import com.example.pokerjawa.view.component.HighscoreTableView;
import com.example.pokerjawa.view.component.LargeButton;
import com.example.pokerjawa.view.component.Title;
import com.example.pokerjawa.view.constant.BackgroundConstant;

public class HomePage extends Page {

	private BorderPane homePane;

	private VBox howToPlayContainer;
	private Title howToPlayTitle;
	private ScrollPane howToPlayScrollPane;
	private VBox howToPlayContent;

	private VBox highscoreContainer;
	private Title highscoreTitle;
	private HighscoreTableView highscoreTableView;

	private BorderPane playNowContainer;
	private Button playNowButton;

	public HomePage() {

		homePane = new BorderPane();
		howToPlayContainer = new VBox();
		highscoreContainer = new VBox();
		playNowContainer = new BorderPane();
		howToPlayTitle = new Title("How to Play");
		highscoreTitle = new Title("Highscore");
		playNowButton = new LargeButton("Play Now");
		howToPlayScrollPane = new ScrollPane();
		howToPlayContent = new VBox();
		highscoreTableView = new HighscoreTableView();

		howToPlayContent.setSpacing(10);
		howToPlayScrollPane.setContent(howToPlayContent);
		howToPlayScrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
		howToPlayScrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

		howToPlayContainer.setPrefWidth(1280 / 2);
		highscoreContainer.setPrefWidth(1280 / 2);
		howToPlayContainer.getChildren().add(howToPlayTitle);
		howToPlayContainer.getChildren().add(howToPlayScrollPane);
		highscoreContainer.getChildren().add(highscoreTitle);
		highscoreContainer.getChildren().add(highscoreTableView);
		playNowContainer.setCenter(playNowButton);

		howToPlayContainer.setSpacing(20);
		howToPlayContainer.setPadding(new Insets(20));
		highscoreContainer.setSpacing(20);
		highscoreContainer.setPadding(new Insets(20));

		playNowContainer.setPrefHeight(100);
		playNowContainer.setBackground(new Background(
				new BackgroundFill(Color.WHITESMOKE, new CornerRadii(65, 65, 0, 0, false), Insets.EMPTY)));
		playNowContainer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
				new CornerRadii(60, 60, 0, 0, false), new BorderWidths(3))));

		homePane.setLeft(howToPlayContainer);
		homePane.setRight(highscoreContainer);
		homePane.setBottom(playNowContainer);

		homePane.setBackground(BackgroundConstant.PRIMARY_GRADIENT);

		scene = new Scene(homePane, 1280, 720);
	}

	public Button getPlayNowButton() {
		return playNowButton;
	}

	public VBox getHowToPlayContent() {
		return howToPlayContent;
	}

	public HighscoreTableView getHighscoreTableView() {
		return highscoreTableView;
	}

}
