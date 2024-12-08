package com.example.pokerjawa.controller.scene;

import com.example.pokerjawa.controller.stage.MainStageController;
import javafx.scene.layout.VBox;
import com.example.pokerjawa.model.card.Card;
import com.example.pokerjawa.model.card.CardSuit;
import com.example.pokerjawa.model.card.CardValue;
import com.example.pokerjawa.model.factory.CardFactory;
import com.example.pokerjawa.model.proxy.DatabaseProxy;
import com.example.pokerjawa.view.component.ItemComponent;
import com.example.pokerjawa.view.scene.HomePage;

import java.util.ArrayList;

public class HomePageController {

	private HomePage homePage;

	private DatabaseProxy databaseProxy;

	private MainStageController mainStageController;

	private CardFactory cardFactory;
	private ArrayList<Card> cards;

	public HomePageController(HomePage homePage, MainStageController mainStageController) {
		super();
		this.homePage = homePage;
		this.mainStageController = mainStageController;
		this.databaseProxy = DatabaseProxy.getInstance();
		this.cardFactory = new CardFactory();
		this.cards = new ArrayList<>();
	}

	public void onShown() {
		homePage.getPlayNowButton().setOnAction(event -> mainStageController.showGamePage(false));
		fillHowToPlayContent();
		fillHighscoreTableView();
	}

	private void fillHighscoreTableView() {
		homePage.getHighscoreTableView().getItems().clear();
		homePage.getHighscoreTableView().getItems().addAll(databaseProxy.getPlayers());
		homePage.getHighscoreTableView().style();
	}

	private void fillHowToPlayContent() {
		VBox howToPlayContent = homePage.getHowToPlayContent();
		howToPlayContent.getChildren().clear();
		howToPlayContent.getChildren().addAll(createIntroductionComponent(), createCardOrderComponent(),
				createWhosPlayFirstComponent(), createFirstPlayerMoveComponent(), createCardUndefeatedComponent(),
				createWinConditionComponent(), createSingleCombinationComponent(), createDoubleCombinationComponent(),
				createTripleThreesCombinationComponent(), createTripleColorCombinationComponent(),
				createPacketStraightCombinationComponent(), createPacketColorCombinationComponent(),
				createPacketFullHouseCombinationComponent(), createPacketSplitCombinationComponent(),
				createPacketFlushCombinationComponent());
	}

	private ItemComponent createPacketFlushCombinationComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.TEN));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.JACK));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.QUEEN));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.KING));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.ACE));
		ItemComponent itemComponent = new ItemComponent(cards, "Packet Flush Combination",
				"The Packet Flush Combination requires five cards with the same suit and in consecutive order, for example a spade of 10, a spade of J, a spade of Q, a spade of K, and a spade of A. This combination can only be beaten by a Packet Flush Combination with a higher value or the same value but with a more valuable suit.");
		return itemComponent;
	}

	private ItemComponent createPacketSplitCombinationComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.JACK));
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.QUEEN));
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.KING));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.FOUR));
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.FOUR));
		ItemComponent itemComponent = new ItemComponent(cards, "Packet Split Combination",
				"The Packet Split Combination requires five cards that are made up of a combination of a Triple Color Combination (3 cards) and a Double Combination (2 cards). In this case, the winning combination is determined by the Triple Color, so if the Triple Color has a higher value or the same value but a more valuable suit, it can beat a Packet Split Combination even if the Double Combination has a higher value.");
		return itemComponent;
	}

	private ItemComponent createPacketFullHouseCombinationComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.KING));
		cards.add(cardFactory.createCard(CardSuit.DIAMOND, CardValue.KING));
		cards.add(cardFactory.createCard(CardSuit.CLUB, CardValue.KING));
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.SEVEN));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.SEVEN));
		ItemComponent itemComponent = new ItemComponent(cards, "Packet Full House Combination",
				"The Packet Full House Combination requires five cards that are made up of a combination of a Triple Threes Combination (3 cards) and a Double Combination (2 cards). In this case, the winning combination is determined by the Triple Threes, so if the Triple Threes has a higher value, it can beat a Packet Full House Combination even if the Double Combination has a higher value.");
		return itemComponent;
	}

	private ItemComponent createPacketColorCombinationComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.EIGHT));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.SIX));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.JACK));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.SEVEN));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.TEN));
		ItemComponent itemComponent = new ItemComponent(cards, "Packet Color Combination",
				"The Packet Color Combination requires five cards with the same suit and any value, for example a spade of 8, a spade of 6, a spade of J, a spade of 7, and a spade of 10. This combination can only be beaten by a Packet Color Combination with a more valuable suit or a Packet Color Combination with the same suit but with a higher value for the highest card in the combination.");
		return itemComponent;
	}

	private ItemComponent createPacketStraightCombinationComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.FOUR));
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.FIVE));
		cards.add(cardFactory.createCard(CardSuit.DIAMOND, CardValue.SIX));
		cards.add(cardFactory.createCard(CardSuit.DIAMOND, CardValue.SEVEN));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.EIGHT));
		ItemComponent itemComponent = new ItemComponent(cards, "Packet Straight Combination",
				"The Packet Straight Combination requires five cards in consecutive order and any suit, for example a spade of 4, a heart of 5, a diamond of 6, a diamond of 7, and a spade of 8. This combination can only be beaten by a Packet Straight Combination with a higher value.");
		return itemComponent;
	}

	private ItemComponent createTripleColorCombinationComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.EIGHT));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.NINE));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.TEN));
		ItemComponent itemComponent = new ItemComponent(cards, "Triple Color Combination",
				"The Triple Color Combination requires three cards with the same suit and in consecutive order, for example a spade of 8, a spade of 9, and a spade of 10. This combination can only be beaten by a Triple Color Combination with a higher value, or a Triple Color Combination with the same value but a more valuable suit.");
		return itemComponent;
	}

	private ItemComponent createTripleThreesCombinationComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.DIAMOND, CardValue.ACE));
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.ACE));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.ACE));
		ItemComponent itemComponent = new ItemComponent(cards, "Triple Threes Combination",
				"The Triple Threes Combination requires three cards with the same value, for example a spade of A, a diamond of A, and a heart of A. This combination can only be beaten by a Triple Threes Combination with a higher value.");
		return itemComponent;
	}

	private ItemComponent createDoubleCombinationComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.CLUB, CardValue.NINE));
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.NINE));
		ItemComponent itemComponent = new ItemComponent(cards, "Double Combination",
				"The Double Combination requires two cards with the same value, for example a spade of 5 and a heart of 5. This combination can only be beaten by a Double Combination Triple Threes with a higher value or a Double Combination with the same value but one of the cards has a spade suit. For example, a heart of 9 and a club of 9 will lose to a spade of 9 and a diamond of 9.");
		return itemComponent;
	}

	private ItemComponent createSingleCombinationComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.DIAMOND, CardValue.FOUR));
		ItemComponent itemComponent = new ItemComponent(cards, "Single Combination",
				"The Single Combination is the most basic combination, as it only requires one card. This combination can only be beaten by a card with a higher value or a card with the same value but a more valuable suit, for example a diamond of 4 can be beaten by a spade of 4.");
		return itemComponent;
	}

	private ItemComponent createWinConditionComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.ACE));
		ItemComponent itemComponent = new ItemComponent(cards, "Win Condition",
				"The player who runs out of cards first will be the winner. The game will continue until all players have run out of cards, and there will be 1st, 2nd, 3rd, and 4th place winners. Each winner will receive 100, 50, 25, and 5 points respectively.");
		return itemComponent;
	}

	private ItemComponent createCardUndefeatedComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.TWO));
		ItemComponent itemComponent = new ItemComponent(cards, "Card Undefeated",
				"If none of the other players are able to play a card in response to player A's card, then player A is able to play any card or combination of cards just like first player move.");
		return itemComponent;
	}

	private ItemComponent createFirstPlayerMoveComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.DIAMOND, CardValue.TWO));
		cards.add(cardFactory.createCard(CardSuit.CLUB, CardValue.TWO));
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.TWO));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.TWO));
		ItemComponent itemComponent = new ItemComponent(cards, "First Player Move",
				"The first player is free to choose any card or combination of cards to play. Afterwards, the next player can only respond with the same combination and a higher value if they wish to play.");
		return itemComponent;
	}

	private ItemComponent createWhosPlayFirstComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.DIAMOND, CardValue.THREE));
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.THREE));
		cards.add(cardFactory.createCard(CardSuit.CLUB, CardValue.THREE));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.THREE));
		ItemComponent itemComponent = new ItemComponent(cards, "Who's Play First?",
				"At the start of the game, each player is dealt 13 random cards. To determine who goes first, each player will play a 3 card. The player with the most 3 cards or with a spades of 3 will go first.");
		return itemComponent;
	}

	private ItemComponent createCardOrderComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.DIAMOND, CardValue.THREE));
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.FOUR));
		cards.add(cardFactory.createCard(CardSuit.CLUB, CardValue.ACE));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.TWO));
		ItemComponent itemComponent = new ItemComponent(cards, "Card Order",
				"The order of cards from smallest to largest in this game is 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A, 2. Yes, in this game the 2 card is the highest and the 3 card is the smallest. The order of suits in this game is diamond, heart, club, spade. So, cards with the same value can still be played against each other, for example a heart of 4 can be beaten by a spade of 4.");
		return itemComponent;
	}

	private ItemComponent createIntroductionComponent() {
		cards.clear();
		cards.add(cardFactory.createCard(CardSuit.DIAMOND, CardValue.JACK));
		cards.add(cardFactory.createCard(CardSuit.CLUB, CardValue.QUEEN));
		cards.add(cardFactory.createCard(CardSuit.HEART, CardValue.KING));
		cards.add(cardFactory.createCard(CardSuit.SPADE, CardValue.ACE));
		cards.add(cardFactory.createCard(CardSuit.DIAMOND, CardValue.TWO));
		ItemComponent itemComponent = new ItemComponent(cards, "Poker Jawa",
				"Poker Jawa is a card game played by 4 people, where each player can play a card with a higher value than the previous player's card or pass if they don't have any. In this game, there are several combinations that allow a player to play more than one card at a time. Some of the combinations include single, double, triple threes, triple color, packet straight, packet color, packet full house, packet flush, and packet split.");
		return itemComponent;
	}

	public HomePage getHomePage() {
		return homePage;
	}

}
