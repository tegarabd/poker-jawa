package com.example.pokerjawa.view.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import com.example.pokerjawa.view.constant.BackgroundConstant;
import com.example.pokerjawa.view.constant.BorderConstant;

public class WinnerInformation extends VBox {

	private ImageView imageView;
	private SubTitle text;
	private HBox buttonContainer;
	private Button playAgainButton;
	private Button backToMainMenuButton;

	public WinnerInformation(Integer winnerNumber, Integer score) {

		imageView = new ImageView(new Image("winner_" + winnerNumber + ".png", true));
		buttonContainer = new HBox();
		playAgainButton = new SmallButton("Play Again");
		backToMainMenuButton = new SmallButton("Back to Main Menu");
		text = new SubTitle("You are the winner number " + winnerNumber + "\n" + "You've got " + score + " points");

		imageView.setFitWidth(120 * 3 / 2);
		imageView.setFitHeight(130 * 3 / 2);

		text.setTextAlignment(TextAlignment.CENTER);

		buttonContainer.setSpacing(20);
		buttonContainer.getChildren().addAll(playAgainButton, backToMainMenuButton);
		buttonContainer.setAlignment(Pos.CENTER);

		setAlignment(Pos.CENTER);
		setSpacing(20);
		getChildren().addAll(imageView, text, buttonContainer);
		setBackground(BackgroundConstant.WHITE_CORNER_RADIUS);
		setBorder(BorderConstant.DEFAULT_BORDER);
		setPadding(new Insets(20));
		setMaxWidth(600);
		setMaxHeight(300);
	}

	public Button getPlayAgainButton() {
		return playAgainButton;
	}

	public Button getBackToMainMenuButton() {
		return backToMainMenuButton;
	}

}
