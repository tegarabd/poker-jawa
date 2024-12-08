package com.example.pokerjawa.view.component;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import com.example.pokerjawa.model.card.CardConstant;
import com.example.pokerjawa.view.component.card.StackedCard;

import java.util.ArrayList;

public class SideBar extends VBox {

	private DefaultText currentPlayerTitleText, currentCardTitleText, lastCombinationTitleText;
	private SubTitle currentUsernameText, lastCombinationText;
	private AnchorPane currentCards;
	private Region space;
	private SmallButton pauseButton, exitButton;

	public SideBar() {
		currentPlayerTitleText = new DefaultText("Current Player");
		currentCardTitleText = new DefaultText("Last Card");
		lastCombinationTitleText = new DefaultText("Last Combination");
		currentUsernameText = new SubTitle("Not yet started");
		lastCombinationText = new SubTitle("Free");
		currentCards = new AnchorPane();
		space = new Region();
		pauseButton = new SmallButton("Pause");
		exitButton = new SmallButton("Exit");

		setPadding(new Insets(20));
		setPrefWidth(300);
		setSpacing(20);
		setVgrow(space, Priority.ALWAYS);

		currentUsernameText.setWrappingWidth(260);
		lastCombinationText.setWrappingWidth(260);

		currentCards.setMaxHeight(130);
		currentCards.setMaxWidth(300);
		
		pauseButton.setMaxWidth(Double.MAX_VALUE);
		exitButton.setMaxWidth(Double.MAX_VALUE);

		getChildren().addAll(currentPlayerTitleText, currentUsernameText, lastCombinationTitleText, lastCombinationText,
				currentCardTitleText, currentCards, space, pauseButton, exitButton);
	}

	public SmallButton getPauseButton() {
		return pauseButton;
	}

	public SmallButton getExitButton() {
		return exitButton;
	}

	public SubTitle getCurrentUsernameText() {
		return currentUsernameText;
	}

	public AnchorPane getCurrentCards() {
		return currentCards;
	}

	public SubTitle getLastCombinationText() {
		return lastCombinationText;
	}

	public void clearAndFillCurrentCards(ArrayList<StackedCard> stackedCards) {
		currentCards.getChildren().clear();

		int firstCardDegree = -((stackedCards.size() - 1) * 8 / 2);
		for (StackedCard stackedCard : stackedCards) {

			stackedCard.getTransforms().add(new Rotate(firstCardDegree,
					stackedCard.getImageView().getFitWidth() / 2 - 15, stackedCard.getImageView().getFitHeight()));
//			node.setRotate(firstCardDegree);
			firstCardDegree += 8;
			stackedCard.setOnMouseDragged(null);
			stackedCard.setOnDragDetected(null);
			stackedCard.setOnMouseEntered(null);
			stackedCard.setOnMouseExited(null);

			stackedCard.setPrefWidth(CardConstant.CARD_WIDTH_RATIO * 13);

			AnchorPane.setLeftAnchor(stackedCard,
					currentCards.getMaxWidth() / 2 - stackedCard.getImageView().getFitWidth() / 2 - 20);
			AnchorPane.setBottomAnchor(stackedCard, 0.0);
			currentCards.getChildren().add(stackedCard);
		}
	}

}
