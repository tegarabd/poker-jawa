package com.example.pokerjawa.view.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.CardConstant;
import com.example.pokerjawa.view.component.card.StackedCard;
import com.example.pokerjawa.view.constant.BackgroundConstant;
import com.example.pokerjawa.view.constant.BorderConstant;

import java.util.ArrayList;

public class ItemComponent extends HBox {

	private AnchorPane cardsBox;
	private VBox textContainer;
	private DefaultText titleText;
	private SmallText descriptionText;

	public ItemComponent(ArrayList<Card> cards, String title, String description) {

		cardsBox = new AnchorPane();
		textContainer = new VBox();
		titleText = new DefaultText(title);
		descriptionText = new SmallText(description);

		textContainer.getChildren().add(titleText);
		textContainer.getChildren().add(descriptionText);

		getChildren().add(cardsBox);
		getChildren().add(textContainer);

		style();
		fillCardsBox(cards);
	}

	private void fillCardsBox(ArrayList<Card> cards) {
		int cardDegree = -((cards.size() - 1) * 8 / 2);
		for (Card card : cards) {

			StackedCard stackedCard = new StackedCard(card, CardConstant.CARD_WIDTH_RATIO * 13,
					CardConstant.CARD_HEIGHT_RATIO * 13);

			stackedCard.getTransforms().add(new Rotate(cardDegree, stackedCard.getImageView().getFitWidth() / 2 - 15,
					stackedCard.getImageView().getFitHeight()));

			AnchorPane.setLeftAnchor(stackedCard,
					cardsBox.getPrefWidth() / 2 - stackedCard.getImageView().getFitWidth() / 2);
			AnchorPane.setBottomAnchor(stackedCard, 20.0);
			cardsBox.getChildren().add(stackedCard);
			cardDegree += 8;
		}
	}

	private void style() {
		setPrefWidth(1280 / 2 - 50);
		setBackground(BackgroundConstant.WHITE_CORNER_RADIUS);
		setBorder(BorderConstant.DEFAULT_BORDER);
		setPadding(new Insets(10));
		setSpacing(30);
		setAlignment(Pos.TOP_CENTER);
		cardsBox.setPrefWidth(180);
		cardsBox.setPrefHeight(160);
		textContainer.setSpacing(5);
		descriptionText.setWrappingWidth(1280 / 2 - 50 - 220);
		titleText.setWrappingWidth(1280 / 2 - 50 - 220);
		HBox.setHgrow(textContainer, Priority.ALWAYS);
	}

}
