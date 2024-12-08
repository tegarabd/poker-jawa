package com.example.pokerjawa.controller.component;

import com.example.pokerjawa.controller.scene.GamePageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import com.example.pokerjawa.model.Game;
import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.CardConstant;
import com.example.pokerjawa.model.card.combination.Combination;
import com.example.pokerjawa.model.card.combination.CombinationHelper;
import com.example.pokerjawa.model.player.HumanPlayer;
import com.example.pokerjawa.model.player.PlayerConstant;
import com.example.pokerjawa.model.proxy.DatabaseProxy;
import com.example.pokerjawa.view.component.card.StackedCard;
import com.example.pokerjawa.view.component.card.TemporaryCard;
import com.example.pokerjawa.view.component.container.PlayerContainer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerContainerController {

	private PlayerContainer playerContainer;

	private GamePageController gamePageController;

	private HumanPlayer player;
	private Game game;
	private Integer winnerNumber;

	public HumanPlayer getPlayer() {
		return player;
	}

	public PlayerContainerController(PlayerContainer playerContainer, GamePageController gamePageController,
			Game game) {
		super();
		this.playerContainer = playerContainer;
		this.gamePageController = gamePageController;
		this.player = DatabaseProxy.getInstance().getCurrentPlayer();
		this.game = game;
	}

	private void fillPlayerData() {
		playerContainer.getUsernameText().setText(player.getUsername());
		playerContainer.getCardsBox().setOnDragOver(event -> {
			if (event.getGestureSource() != playerContainer.getCardsBox() && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
			event.consume();
		});
		playerContainer.getCardsBox().setOnDragDropped(event -> {
			Dragboard db = event.getDragboard();
			if (db.hasString()) {
				String cardImageUrl = db.getString();
				StackedCard stackedCard = getStackedCardInTemporaryByImageUrl(cardImageUrl);
				if (stackedCard != null) {
					moveCardFromTemporaryToBox(stackedCard);
				}
				event.setDropCompleted(true);
			} else {
				event.setDropCompleted(false);
			}
			event.consume();
		});

		fillPlayerCardsBox();
		fillPlayerTemporaryCardsBox();
	}

	private void fillPlayerCardsBox() {
		for (int i = 0; i < player.getCards().size(); i++) {
			Card card = player.getCards().get(i);
			StackedCard stackedCard = new StackedCard(card, CardConstant.CARD_WIDTH_RATIO * 13,
					CardConstant.CARD_HEIGHT_RATIO * 13);

			stackedCard.setOnMouseDragged(event -> event.setDragDetect(true));
			stackedCard.setOnDragDetected(event -> {
				Dragboard db = stackedCard.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString(card.getImageUrl());
				db.setContent(content);
			});
			stackedCard.setOnMouseEntered(event -> stackedCard.setPrefWidth(CardConstant.CARD_WIDTH_RATIO * 13));
			stackedCard.setOnMouseExited(event -> stackedCard.setPrefWidth(13));

			playerContainer.getCardsBox().getChildren().add(stackedCard);
		}
		playerContainer.getCardsBox()
				.setPrefWidth(player.getCards().size() * 13 + CardConstant.CARD_WIDTH_RATIO * (13 - 1) * 2);
	}

	private void fillPlayerTemporaryCardsBox() {
		for (int i = 0; i < 5; i++) {
			TemporaryCard temporaryCard = new TemporaryCard(CardConstant.CARD_WIDTH_RATIO * 13,
					CardConstant.CARD_HEIGHT_RATIO * 13);

			temporaryCard.setOnDragOver(event -> {
				if (event.getGestureSource() != temporaryCard && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			});
			temporaryCard.setOnDragDropped(event -> {
				Dragboard db = event.getDragboard();
				if (db.hasString()) {
					String cardImageUrl = db.getString();
					StackedCard stackedCard;

					stackedCard = getStackedCardInBoxByImageUrl(cardImageUrl);
					if (stackedCard != null) {
						moveCardFromBoxToTemporary(stackedCard, temporaryCard);
						return;
					}

					stackedCard = getStackedCardInTemporaryByImageUrl(cardImageUrl);
					if (stackedCard != null) {
						moveCardFromTemporaryToTemporary(stackedCard, temporaryCard);
						return;
					}

					event.setDropCompleted(true);
				} else {
					event.setDropCompleted(false);
				}
				event.consume();
			});

			playerContainer.getTemporaryCardsBox().getChildren().add(temporaryCard);
		}
	}

	private void moveCardFromTemporaryToBox(StackedCard stackedCard) {
		removeCardFromTemporary(stackedCard);
		addCardToBox(stackedCard);
	}

	private StackedCard getStackedCardInTemporaryByImageUrl(String cardImageUrl) {
		HBox temporaryCardsBox = playerContainer.getTemporaryCardsBox();
		return temporaryCardsBox.getChildren().stream().map(node -> ((StackedCard) ((TemporaryCard) node).getCenter()))
				.filter(stackedCard -> stackedCard != null)
				.filter(stackedCard -> stackedCard.getImageUrl().equals(cardImageUrl)).findAny().orElse(null);
	}

	private void moveCardFromTemporaryToTemporary(StackedCard fromStackedCard, TemporaryCard toTemporaryCard) {
		TemporaryCard fromTemporaryCard = (TemporaryCard) fromStackedCard.getParent();
		StackedCard toStackedCard = (StackedCard) toTemporaryCard.getCenter();

		fromTemporaryCard.setCenter(toStackedCard);
		toTemporaryCard.setCenter(fromStackedCard);
	}

	private StackedCard getStackedCardInBoxByImageUrl(String cardImageUrl) {
		HBox cardsBox = playerContainer.getCardsBox();
		return (StackedCard) cardsBox.getChildren().stream()
				.filter(node -> ((StackedCard) node).getImageUrl().equals(cardImageUrl)).findAny().orElse(null);
	}

	private void moveCardFromBoxToTemporary(StackedCard stackedCard, TemporaryCard temporaryCard) {
		removeCardFromBox(stackedCard);
		if (temporaryCard.getCenter() != null) {
			addCardToBox((StackedCard) temporaryCard.getCenter());
		}
		addCardToTemporary(temporaryCard, stackedCard);
	}

	private void removeCardFromTemporary(StackedCard stackedCard) {
		TemporaryCard temporaryCard = (TemporaryCard) stackedCard.getParent();
		temporaryCard.setCenter(null);
	}

	private void addCardToTemporary(TemporaryCard temporaryCard, StackedCard stackedCard) {
		stackedCard.getImageView().setFitWidth(CardConstant.CARD_WIDTH_RATIO * 13 - 10);
		stackedCard.getImageView().setFitHeight(CardConstant.CARD_HEIGHT_RATIO * 13 - 10);
		temporaryCard.setCenter(stackedCard);
	}

	private void removeCardFromBox(StackedCard stackedCard) {
		HBox cardsBox = playerContainer.getCardsBox();
		cardsBox.getChildren().remove(stackedCard);
	}

	private void addCardToBox(StackedCard stackedCard) {
		HBox cardsBox = playerContainer.getCardsBox();
		cardsBox.getChildren().add(stackedCard);
		stackedCard.getImageView().setFitWidth(CardConstant.CARD_WIDTH_RATIO * 13);
		stackedCard.getImageView().setFitHeight(CardConstant.CARD_HEIGHT_RATIO * 13);
	}

	public void onShown() {
		fillPlayerData();
		disableAllButton();
	}

	private void disableAllButton() {
		Button sortByValueButton = playerContainer.getSortByValueButton();
		Button sortBySuitButton = playerContainer.getSortBySuitButton();
		Button moveButton = playerContainer.getMoveButton();
		Button passButton = playerContainer.getPassButton();
		sortByValueButton.setDisable(true);
		sortBySuitButton.setDisable(true);
		moveButton.setDisable(true);
		passButton.setDisable(true);
	}

	private void addButtonsListener() {
		Button sortByValueButton = playerContainer.getSortByValueButton();
		Button sortBySuitButton = playerContainer.getSortBySuitButton();
		Button moveButton = playerContainer.getMoveButton();
		Button passButton = playerContainer.getPassButton();
		sortByValueButton.setOnAction(event -> sortStackedCardByValue());
		sortBySuitButton.setOnAction(event -> sortStackedCardBySuit());
		moveButton.setOnAction(event -> {
			ArrayList<Card> cards = (ArrayList<Card>) playerContainer.getTemporaryCardsBox().getChildren().stream()
					.filter(node -> ((TemporaryCard) node).getCenter() != null)
					.map(node -> ((StackedCard) ((TemporaryCard) node).getCenter()).getCard())
					.collect(Collectors.toList());

			Combination combination = CombinationHelper.getInstance().recognizeCombination(cards);

			if (combination == null) {
				giveInformation("Combination unrecognize");
				return;
			}

			if (game.getCurrentCombination() != null && !game.getLastPlayerMoved().equals(player)
					&& game.getCurrentCombination() != combination) {
				giveInformation("The combination not compitable with current combination\n" + "Current combination: "
						+ game.getCurrentCombination().getName() + "\n" + "Your combination: " + combination.getName());
				return;
			}

			if (game.getCurrentCombination() != null && !game.getLastPlayerMoved().equals(player)
					&& game.getCurrentCombination().compare(cards, game.getCurrentCardCombination()) <= 0) {
				giveInformation("The combination is cannot win against current combination");
				return;
			}

			game.setCurrentCombination(combination);
			game.setCurrentCardCombination(cards);
			game.setLastPlayerMoved(player);
			player.getCards().removeAll(cards);

			List<Node> stackedCards = playerContainer.getTemporaryCardsBox().getChildren().stream()
					.filter(node -> ((TemporaryCard) node).getCenter() != null)
					.map(node -> ((StackedCard) ((TemporaryCard) node).getCenter())).map(stackedCard -> {
						stackedCard.setPrefWidth(CardConstant.CARD_WIDTH_RATIO * 13);
						stackedCard.setPrefHeight(CardConstant.CARD_HEIGHT_RATIO * 13);
						stackedCard.getImageView().setFitWidth(CardConstant.CARD_WIDTH_RATIO * 13);
						stackedCard.getImageView().setFitHeight(CardConstant.CARD_HEIGHT_RATIO * 13);
						return stackedCard;
					}).collect(Collectors.toList());
			playerContainer.moveStackedCardsToPlayground(stackedCards,
					gamePageController.getGamePage().getCardPlayground());
			gamePageController.setSideBarCardCombination();

			if (player.getCards().size() == 0) {
				game.setCurrentCombination(null);
				game.setCurrentCardCombination(null);
				game.setLastPlayerMoved(null);
				game.unsubscribe(player);
				winnerNumber = PlayerConstant.PLAYER_COUNT - game.getPlayers().size();
				ImageView imageView = new ImageView(new Image("winner_" + winnerNumber + ".png", true));
				imageView.setFitWidth(120);
				imageView.setFitHeight(130);
				playerContainer.getCardsBox().getChildren().add(imageView);
				playerContainer.getCardsBox().setRotate(0);
				playerContainer.getCardsBox().setAlignment(Pos.CENTER);
				disableAllButton();

				Integer score = PlayerConstant.PLAYER_WINNER_SCORE.get(winnerNumber);
				DatabaseProxy.getInstance().addPlayerScore(score);

				if (winnerNumber == PlayerConstant.PLAYER_COUNT) {
					game.finish();
					gamePageController.onFinish();
					return;
				}
			}

			onPlayerWait();
		});
		passButton.setOnAction(event -> {
			onPlayerWait();
		});
	}

	private void onPlayerWait() {
		disableMoveAndPassButton();
		game.notifySubcribers();
		gamePageController.nextPlayerMove();
	}

	public void ableMoveAndPassButton() {
		Button moveButton = playerContainer.getMoveButton();
		Button passButton = playerContainer.getPassButton();
		moveButton.setDisable(false);
		passButton.setDisable(false);
	}

	public void disableMoveAndPassButton() {
		Button moveButton = playerContainer.getMoveButton();
		Button passButton = playerContainer.getPassButton();
		moveButton.setDisable(true);
		passButton.setDisable(true);
	}

	private void sortStackedCardBySuit() {
		HBox cardsBox = playerContainer.getCardsBox();
		ObservableList<Node> stackedCards = FXCollections.observableArrayList(cardsBox.getChildren());
		Collections.sort(stackedCards, (o1, o2) -> {
			Card card1 = ((StackedCard) o1).getCard();
			Card card2 = ((StackedCard) o2).getCard();
			if (card1.getSuit().equals(card2.getSuit())) {
				return card1.getValue().getWorth() - card2.getValue().getWorth();
			}
			return card1.getSuit().getWorth() - card2.getSuit().getWorth();
		});
		cardsBox.getChildren().setAll(stackedCards);
	}

	private void sortStackedCardByValue() {
		HBox cardsBox = playerContainer.getCardsBox();
		ObservableList<Node> stackedCards = FXCollections.observableArrayList(cardsBox.getChildren());
		Collections.sort(stackedCards, (o1, o2) -> {
			Card card1 = ((StackedCard) o1).getCard();
			Card card2 = ((StackedCard) o2).getCard();
			if (card1.getValue().equals(card2.getValue())) {
				return card1.getSuit().getWorth() - card2.getSuit().getWorth();
			}
			return card1.getValue().getWorth() - card2.getValue().getWorth();
		});
		cardsBox.getChildren().setAll(stackedCards);
	}

	public void onGameStart() {
		addButtonsListener();
		ableSortButton();
		moveTemporaryCardsToPlayground();
	}

	private void ableSortButton() {
		Button sortByValueButton = playerContainer.getSortByValueButton();
		Button sortBySuitButton = playerContainer.getSortBySuitButton();
		sortByValueButton.setDisable(false);
		sortBySuitButton.setDisable(false);
	}

	private void moveTemporaryCardsToPlayground() {
		HBox cardsBox = playerContainer.getCardsBox();
		List<String> cards = player.getTemporaryCards().stream().map(card -> card.getImageUrl())
				.collect(Collectors.toList());
		List<Node> stackedCards = cardsBox.getChildren().stream()
				.filter(node -> cards.contains(((StackedCard) node).getImageUrl())).collect(Collectors.toList());
		playerContainer.moveStackedCardsToPlayground(stackedCards,
				gamePageController.getGamePage().getCardPlayground());
	}

	public void onPlayerCanMove() {
		ableMoveAndPassButton();
	}

	public void addNodeToPlayground(Node node) {
		playerContainer.addNodeToPlayground(node, gamePageController.getGamePage().getCardPlayground());
	}

	public void removeNodeFromPlayground(Node node) {
		playerContainer.removeNodeFromPlayground(node, gamePageController.getGamePage().getCardPlayground());
	}

	public void giveInformation(String infromation) {
		playerContainer.generateOneSecondInformation(infromation, gamePageController.getGamePage().getCardPlayground());
	}

	public Integer getWinnerNumber() {
		return winnerNumber;
	}

}
