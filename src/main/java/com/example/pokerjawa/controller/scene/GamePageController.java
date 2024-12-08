package com.example.pokerjawa.controller.scene;

import com.example.pokerjawa.controller.component.BotContainerController;
import com.example.pokerjawa.controller.component.PlayerContainerController;
import com.example.pokerjawa.controller.stage.MainStageController;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import com.example.pokerjawa.model.Game;
import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.CardConstant;
import com.example.pokerjawa.model.card.combination.Combination;
import com.example.pokerjawa.model.player.BotPlayer;
import com.example.pokerjawa.model.player.PlayerConstant;
import com.example.pokerjawa.view.component.PlayAfterOneSecond;
import com.example.pokerjawa.view.component.SmallButton;
import com.example.pokerjawa.view.component.WinnerInformation;
import com.example.pokerjawa.view.component.card.StackedCard;
import com.example.pokerjawa.view.component.container.BotContainer;
import com.example.pokerjawa.view.scene.GamePage;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GamePageController {

	private GamePage gamePage;

	private MainStageController mainStageController;

	private PlayerContainerController playerContainerController;
	private ArrayList<BotContainerController> botContainerControllers;
	private Game game;

	public GamePageController(GamePage gamePage, MainStageController mainStageController) {
		super();
		this.game = new Game();
		this.gamePage = gamePage;
		this.mainStageController = mainStageController;
		this.playerContainerController = new PlayerContainerController(gamePage.getPlayerContainer(), this, game);
		this.botContainerControllers = new ArrayList<>();
		initializeBotContainerController();
	}

	private void initializeBotContainerController() {
		for (int i = 1; i < game.getPlayers().size(); i++) {
			BotPlayer bot = (BotPlayer) game.getPlayers().get(i);
			BotContainer botContainer = gamePage.getBotContainers().get(i - 1);
			BotContainerController botContainerController = new BotContainerController(botContainer, this, bot, game);
			botContainerControllers.add(botContainerController);
		}
	}

	public GamePage getGamePage() {
		return gamePage;
	}

	public void onShown() {
		playerContainerController.onShown();
		botContainerControllers.forEach(botContainerController -> botContainerController.onShown());
		Button playButton = new SmallButton("Start the Game");
		playButton.setOnAction(event -> {
			onReady();
			playerContainerController.removeNodeFromPlayground(playButton);
		});
		// place button in center of game page
		playerContainerController.addNodeToPlayground(playButton);
		addButtonsListener();
	}

	private void addButtonsListener() {
		Button pauseButton = gamePage.getSideBar().getPauseButton();
		Button exitButton = gamePage.getSideBar().getExitButton();

		pauseButton.setOnAction(event -> {
			pauseGame();
		});

		exitButton.setOnAction(event -> {
			exitGame();
		});
	}

	private void exitGame() {
		playGame();
		mainStageController.showHomePage();
		mainStageController.getHomePageController().getHomePage().getPlayNowButton().setText("Play Now");
	}

	private void pauseGame() {
		mainStageController.showHomePage();
		mainStageController.getHomePageController().getHomePage().getPlayNowButton().setText("Resume Game");
	}

	public void onReady() {
		game.start();
		playerContainerController.onGameStart();
		botContainerControllers.forEach(botContainerController -> botContainerController.onGameStart());
		game.selectFirstPlayer();

		new PlayAfterOneSecond(() -> {

			// give information who's play first
			if (game.getCurrentPlayer().equals(playerContainerController.getPlayer())) {
				playerContainerController.giveInformation("You Play First");
			} else {
				BotContainerController first = botContainerControllers.stream().filter(
						botContainerController -> botContainerController.getBot().equals(game.getCurrentPlayer()))
						.findAny().orElse(null);
				first.giveInformation(first.getBot().getUsername() + " Play First");
			}

			new PlayAfterOneSecond(() -> {
				nextPlayerMove();
			});
		});
	}

	public void nextPlayerMove() {
		if (game.getCurrentPlayer().equals(playerContainerController.getPlayer())) {
			setSideBarUsername();
			playerContainerController.onPlayerCanMove();
		} else {
			BotContainerController botContainerController = botContainerControllers.stream()
					.filter(controller -> game.getCurrentPlayer().equals(controller.getBot())).findAny().orElse(null);
			new PlayAfterOneSecond(() -> {
				setSideBarUsername();
				botContainerController.onBotCanMove();
			});
		}
	}

	public void setSideBarUsername() {
		String username = game.getCurrentPlayer().getUsername();
		gamePage.getSideBar().getCurrentUsernameText().setText(username);
	}

	public void setSideBarCardCombination() {
		ArrayList<Card> cards = game.getCurrentCardCombination();
		Combination combination = game.getCurrentCombination();
		String combinationName = "Free";

		if (cards == null || cards.isEmpty()) {
			cards = new ArrayList<>();
		}

		if (combination != null) {
			combinationName = combination.getName();
		}

		ArrayList<StackedCard> stackedCards = (ArrayList<StackedCard>) cards.stream().map(card -> {
			StackedCard stackedCard = new StackedCard(card, CardConstant.CARD_WIDTH_RATIO * 13,
					CardConstant.CARD_HEIGHT_RATIO * 13);
			stackedCard.setPrefWidth(CardConstant.CARD_WIDTH_RATIO * 13);
			stackedCard.setPrefHeight(CardConstant.CARD_HEIGHT_RATIO * 13);
			stackedCard.getImageView().setFitWidth(CardConstant.CARD_WIDTH_RATIO * 13);
			stackedCard.getImageView().setFitHeight(CardConstant.CARD_HEIGHT_RATIO * 13);
			return stackedCard;
		}).collect(Collectors.toList());
		gamePage.getSideBar().getLastCombinationText().setText(combinationName);
		gamePage.getSideBar().clearAndFillCurrentCards(stackedCards);
	}

	public void onFinish() {
		Integer winnerNumber = playerContainerController.getWinnerNumber();
		BorderPane informationPane = gamePage.getInformationPane();
		Integer score = PlayerConstant.PLAYER_WINNER_SCORE.get(winnerNumber);

		WinnerInformation winnerInformation = new WinnerInformation(winnerNumber, score);
		winnerInformation.getPlayAgainButton().setOnMouseClicked(event -> playGame());
		winnerInformation.getBackToMainMenuButton().setOnMouseClicked(event -> {
			exitGame();
		});

		informationPane.setCenter(winnerInformation);

		gamePage.getRoot().getChildren().add(informationPane);
	}

	private void playGame() {
		mainStageController.showGamePage(true);
	}

}
