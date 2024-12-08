package com.example.pokerjawa.view.component.container;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import com.example.pokerjawa.model.card.CardConstant;
import com.example.pokerjawa.view.component.DefaultText;
import com.example.pokerjawa.view.component.PlayAfterOneSecond;
import com.example.pokerjawa.view.component.SmallButton;
import com.example.pokerjawa.view.component.SubTitle;
import com.example.pokerjawa.view.component.card.StackedCard;
import com.example.pokerjawa.view.constant.BackgroundConstant;
import com.example.pokerjawa.view.constant.BorderConstant;

import java.util.List;

public class PlayerContainer extends GridPane {

	private Text usernameText;
	private HBox cardsBox;
	private HBox temporaryCardsBox;
	private HBox buttonContainer;
	private Button sortBySuitButton, sortByValueButton, moveButton, passButton;

	public PlayerContainer() {
		usernameText = new SubTitle();
		cardsBox = new HBox();
		temporaryCardsBox = new HBox();

		createAndSetButtonContainer();

		add(usernameText, 0, 0);
		add(cardsBox, 1, 0);
		add(temporaryCardsBox, 2, 0);
		add(buttonContainer, 0, 1, 3, 1);

		setAlignment(Pos.BOTTOM_CENTER);

		setVgap(20);
		setHgap(20);
		temporaryCardsBox.setSpacing(10);

		setAlignment(Pos.CENTER);
		cardsBox.setAlignment(Pos.CENTER_LEFT);
		cardsBox.setPrefHeight(130);
	}

	private void createAndSetButtonContainer() {

		buttonContainer = new HBox();
		sortBySuitButton = new SmallButton("Sort by Suit");
		sortByValueButton = new SmallButton("Sort by Value");
		moveButton = new SmallButton("Move");
		passButton = new SmallButton("Pass");

		addSpaceBetweenContainer(buttonContainer, sortBySuitButton, sortByValueButton, moveButton, passButton);

	}

	private void addSpaceBetweenContainer(HBox parent, Node... nodes) {
		for (Node node : nodes) {
			parent.getChildren().add(createHSpace());
			parent.getChildren().add(node);
			parent.getChildren().add(createHSpace());
		}
	}

	private Region createHSpace() {
		Region space = new Region();
		HBox.setHgrow(space, Priority.ALWAYS);
		return space;
	}

	public void addNodeToPlayground(Node node, AnchorPane playground) {
		AnchorPane.setLeftAnchor(node, playground.getMaxWidth() / 2 - 100);
		AnchorPane.setBottomAnchor(node, 20.0);
		playground.getChildren().add(node);
	}

	public void removeNodeFromPlayground(Node node, AnchorPane playground) {
		playground.getChildren().remove(node);
	}

	public void generateOneSecondInformation(String information, AnchorPane playground) {
		BorderPane container = new BorderPane();
		DefaultText text = new DefaultText(information);
		container.setCenter(text);
		container.setBackground(BackgroundConstant.WHITE);
		container.setPadding(new Insets(20));
		container.setPrefHeight(50);
		container.setPrefWidth(340);
		container.setBackground(BackgroundConstant.WHITE_CORNER_RADIUS);
		container.setBorder(BorderConstant.DEFAULT_BORDER);
		text.setWrappingWidth(300);
		text.setTextAlignment(TextAlignment.CENTER);

		AnchorPane.setLeftAnchor(container, playground.getMaxWidth() / 2 - container.getPrefWidth() / 2);
		AnchorPane.setBottomAnchor(container, 20.0);

		playground.getChildren().add(container);
		new PlayAfterOneSecond(() -> {
			playground.getChildren().remove(container);
		});
	}

	public void moveStackedCardsToPlayground(List<Node> stackedCards, AnchorPane playground) {
		int firstCardDegree = -((stackedCards.size() - 1) * 8 / 2);
		for (Node node : stackedCards) {
			cardsBox.getChildren().remove(node);
			temporaryCardsBox.getChildren().remove(node);

			StackedCard stackedCard = (StackedCard) node;

			stackedCard.getTransforms().add(new Rotate(firstCardDegree,
					stackedCard.getImageView().getFitWidth() / 2 - 15, stackedCard.getImageView().getFitHeight()));
//			node.setRotate(firstCardDegree);
			firstCardDegree += 8;
			stackedCard.setOnMouseDragged(null);
			stackedCard.setOnDragDetected(null);
			stackedCard.setOnMouseEntered(null);
			stackedCard.setOnMouseExited(null);

			stackedCard.setPrefWidth(CardConstant.CARD_WIDTH_RATIO * 13);

			AnchorPane.setLeftAnchor(node, playground.getMaxWidth() / 2 - stackedCard.getImageView().getFitWidth() / 2);
			AnchorPane.setBottomAnchor(node, 0.0);
			playground.getChildren().add(node);
		}
	}

	public Text getUsernameText() {
		return usernameText;
	}

	public HBox getCardsBox() {
		return cardsBox;
	}

	public HBox getTemporaryCardsBox() {
		return temporaryCardsBox;
	}

	public Button getSortBySuitButton() {
		return sortBySuitButton;
	}

	public Button getSortByValueButton() {
		return sortByValueButton;
	}

	public Button getMoveButton() {
		return moveButton;
	}

	public Button getPassButton() {
		return passButton;
	}

}
