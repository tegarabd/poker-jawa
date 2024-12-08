package com.example.pokerjawa.controller.component;

import com.example.pokerjawa.controller.scene.GamePageController;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import com.example.pokerjawa.model.Game;
import com.example.pokerjawa.model.card.CardConstant;
import com.example.pokerjawa.model.player.BotPlayer;
import com.example.pokerjawa.model.player.PlayerConstant;
import com.example.pokerjawa.view.component.card.StackedCard;
import com.example.pokerjawa.view.component.container.BotContainer;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class BotContainerController {

	private BotContainer botContainer;

	private GamePageController gamePageController;

	private BotPlayer bot;
	private Game game;

	public BotPlayer getBot() {
		return bot;
	}

	public BotContainerController(BotContainer botContainer, GamePageController gamePageController, BotPlayer bot,
			Game game) {
		super();
		this.botContainer = botContainer;
		this.gamePageController = gamePageController;
		this.bot = bot;
		this.game = game;
	}

	public void fillBotData() {
		botContainer.getUsernameText().setText(bot.getUsername());
		fillBotCardsBox();
	}

	private void fillBotCardsBox() {
		for (int j = 0; j < bot.getCards().size(); j++) {
			StackedCard stackedCard = new StackedCard(bot.getCards().get(j), CardConstant.CARD_WIDTH_RATIO * 13,
					CardConstant.CARD_HEIGHT_RATIO * 13);
			stackedCard.hide();
			botContainer.getCardsBox().getChildren().add(stackedCard);
		}
		botContainer.getCardsBox().setPrefWidth(bot.getCards().size() * 13 + CardConstant.CARD_WIDTH_RATIO * (13 - 1));
	}

	public void onShown() {
		fillBotData();
	}

	public void onGameStart() {
		List<Node> stackedCards = getCardsBasedOnBotTemporaryCards();
		botContainer.moveStackedCardsToPlayground(stackedCards, gamePageController.getGamePage().getCardPlayground());
	}

	public List<Node> getCardsBasedOnBotTemporaryCards() {
		HBox cardsBox = botContainer.getCardsBox();
		List<String> cardImageUrls = bot.getTemporaryCards().stream().map(card -> card.getImageUrl())
				.collect(Collectors.toList());
		List<Node> stackedCards = cardsBox.getChildren().stream()
				.filter(node -> cardImageUrls.contains(((StackedCard) node).getImageUrl()))
				.collect(Collectors.toList());
		stackedCards.forEach(stackedCard -> ((StackedCard) stackedCard).show());
		return stackedCards;
	}

	public void giveInformation(String infromation) {
		botContainer.generateOneSecondInformation(infromation, gamePageController.getGamePage().getCardPlayground());
	}

	public void onBotCanMove() {
		List<Node> stackedCards = getCardsBasedOnBotTemporaryCards();

		// Bot Pass
		if (stackedCards.size() == 0) {
			botContainer.generateOneSecondInformation(bot.getUsername() + " Pass",
					gamePageController.getGamePage().getCardPlayground());
		} else {
			botContainer.moveStackedCardsToPlayground(stackedCards,
					gamePageController.getGamePage().getCardPlayground());
			gamePageController.setSideBarCardCombination();
		}

		// Bot Win
		if (bot.getCards().size() == 0) {
			game.setCurrentCombination(null);
			game.setCurrentCardCombination(null);
			game.setLastPlayerMoved(null);
			game.unsubscribe(bot);
			Integer winnerNumber = PlayerConstant.PLAYER_COUNT - game.getPlayers().size();
			ImageView imageView = new ImageView(new Image("winner_" + winnerNumber + ".png", true));
			
			imageView.setFitWidth(120);
			imageView.setFitHeight(130);
			botContainer.getCardsBox().getChildren().add(imageView);
			botContainer.getCardsBox().setRotate(0);
			botContainer.getCardsBox().setAlignment(Pos.CENTER);

			if (winnerNumber == PlayerConstant.PLAYER_COUNT) {
				game.finish();
				gamePageController.onFinish();
				return;
			}
		}

		game.notifySubcribers();
		gamePageController.nextPlayerMove();
	}

}
