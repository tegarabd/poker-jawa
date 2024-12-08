package com.example.pokerjawa.view.component.card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.CardConstant;

import java.io.File;

public class StackedCard extends Pane {

	private Card card;
	private ImageView imageView;
	private String imageUrl;

	public StackedCard(Card card, Integer width, Integer height) {
		this.card = card;
		imageUrl = card.getImageUrl();
		imageView = new ImageView(new Image("cards/" + imageUrl, true));
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		setPrefWidth(width / CardConstant.CARD_WIDTH_RATIO);
		setPrefHeight(height);
		getChildren().add(imageView);
	}

	public void hide() {
		imageView.setImage(new Image("cards/back.png", true));
	}

	public void show() {
		imageView.setImage(new Image("cards/" + imageUrl, true));
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Card getCard() {
		return card;
	}

	public ImageView getImageView() {
		return imageView;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
