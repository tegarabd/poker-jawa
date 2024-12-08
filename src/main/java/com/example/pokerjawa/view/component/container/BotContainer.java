package com.example.pokerjawa.view.component.container;

import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import com.example.pokerjawa.model.card.CardConstant;
import com.example.pokerjawa.view.component.DefaultText;
import com.example.pokerjawa.view.component.PlayAfterOneSecond;
import com.example.pokerjawa.view.component.SubTitle;
import com.example.pokerjawa.view.component.card.StackedCard;
import com.example.pokerjawa.view.constant.BackgroundConstant;
import com.example.pokerjawa.view.constant.BorderConstant;

import java.util.List;

public class BotContainer extends GridPane {

	private Text usernameText;
	private HBox cardsBox;
	private Side side;

	public BotContainer(Side side) {
		this.side = side;
		usernameText = new SubTitle();
		cardsBox = new HBox();

		switch (side) {
		case TOP:
			add(cardsBox, 0, 0);
			add(usernameText, 1, 0);
			cardsBox.setRotate(180);
			break;
		case LEFT:
			add(cardsBox, 0, 1);
			add(usernameText, 0, 0);
			cardsBox.setRotate(90);
			cardsBox.setTranslateY(50);
			setTranslateY(-50);
			break;
		case RIGHT:
			add(cardsBox, 0, 0);
			add(usernameText, 0, 1);
			cardsBox.setRotate(-90);
			cardsBox.setTranslateY(-50);
			setTranslateY(50);
			break;
		default:
			break;
		}

		setVgap(20);
		setHgap(20);
		setAlignment(Pos.CENTER);
		setHalignment(usernameText, HPos.CENTER);
		setHalignment(cardsBox, HPos.CENTER);
		setValignment(usernameText, VPos.CENTER);
		setValignment(cardsBox, VPos.CENTER);
		cardsBox.setPrefHeight(130);
	}

	public Text getUsernameText() {
		return usernameText;
	}

	public HBox getCardsBox() {
		return cardsBox;
	}

	public void generateOneSecondInformation(String information, AnchorPane playground) {

		BorderPane container = new BorderPane();
		DefaultText text = new DefaultText(information);
		container.setCenter(text);
		container.setBackground(BackgroundConstant.WHITE_CORNER_RADIUS);
		container.setBorder(BorderConstant.DEFAULT_BORDER);
		container.setPadding(new Insets(20));
		container.setPrefHeight(60);
		container.setPrefWidth(200);

		switch (side) {
		case TOP:
			AnchorPane.setLeftAnchor(container, playground.getMaxWidth() / 2 - container.getPrefWidth() / 2);
			AnchorPane.setTopAnchor(container, 20.0);
			break;
		case LEFT:
			AnchorPane.setTopAnchor(container, playground.getMaxHeight() / 2 - container.getPrefHeight() / 2);
			AnchorPane.setLeftAnchor(container, 20.0);
			break;
		case RIGHT:
			AnchorPane.setTopAnchor(container, playground.getMaxHeight() / 2 - container.getPrefHeight() / 2);
			AnchorPane.setRightAnchor(container, 20.0);
			break;
		default:
			break;
		}

		playground.getChildren().add(container);
		new PlayAfterOneSecond(() -> {
			playground.getChildren().remove(container);
		});
	}

	public void moveStackedCardsToPlayground(List<Node> stackedCards, AnchorPane playground) {

		int firstCardDegree = -((stackedCards.size() - 1) * 8 / 2);
		for (Node node : stackedCards) {

			StackedCard stackedCard = (StackedCard) node;
			stackedCard.setPrefWidth(CardConstant.CARD_WIDTH_RATIO * 13);

			switch (side) {
			case TOP:
//				node.setRotate(180 + firstCardDegree);
				stackedCard.getTransforms().add(new Rotate(firstCardDegree + 180,
						stackedCard.getImageView().getFitWidth() / 2 - 15, stackedCard.getImageView().getFitHeight()));
				AnchorPane.setLeftAnchor(node,
						playground.getMaxWidth() / 2 - stackedCard.getImageView().getFitWidth() / 2 + 25);
				AnchorPane.setTopAnchor(node, -130.0);
				break;
			case LEFT:
//				node.setRotate(90 + firstCardDegree);
				stackedCard.getTransforms().add(new Rotate(firstCardDegree + 90,
						stackedCard.getImageView().getFitWidth() / 2 - 15, stackedCard.getImageView().getFitHeight()));
				AnchorPane.setTopAnchor(node,
						playground.getMaxHeight() / 2 - stackedCard.getImageView().getFitHeight() / 2 - 80);
				AnchorPane.setLeftAnchor(node, 40.0);
				break;
			case RIGHT:
//				node.setRotate(-90 + firstCardDegree);
				stackedCard.getTransforms().add(new Rotate(firstCardDegree - 90,
						stackedCard.getImageView().getFitWidth() / 2 - 15, stackedCard.getImageView().getFitHeight()));
				AnchorPane.setTopAnchor(node,
						playground.getMaxHeight() / 2 - stackedCard.getImageView().getFitHeight() / 2 - 50);
				AnchorPane.setRightAnchor(node, 10.0);
				break;
			default:
				break;
			}

			firstCardDegree += 8;
			playground.getChildren().add(node);
		}
	}
}
