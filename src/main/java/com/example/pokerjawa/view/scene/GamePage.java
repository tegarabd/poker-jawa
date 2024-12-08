package com.example.pokerjawa.view.scene;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import com.example.pokerjawa.view.component.SideBar;
import com.example.pokerjawa.view.component.container.BotContainer;
import com.example.pokerjawa.view.component.container.PlayerContainer;
import com.example.pokerjawa.view.constant.BackgroundConstant;

import java.util.ArrayList;

public class GamePage extends Page {

	private StackPane root;

	private BorderPane gamePaneContainer;
	private BorderPane gamePane;
	private PlayerContainer playerContainer;
	private ArrayList<BotContainer> botContainers;
	private BotContainer botContainer1, botContainer2, botContainer3;
	private AnchorPane cardPlayground;

	private BorderPane informationPane;

	private SideBar sideBar;

	public GamePage() {
		gamePaneContainer = new BorderPane();
		gamePane = new BorderPane();
		playerContainer = new PlayerContainer();
		botContainers = new ArrayList<>();
		botContainer1 = new BotContainer(Side.LEFT);
		botContainer2 = new BotContainer(Side.TOP);
		botContainer3 = new BotContainer(Side.RIGHT);
		cardPlayground = new AnchorPane();
		root = new StackPane();
		informationPane = new BorderPane();
		sideBar = new SideBar();

		informationPane.setBackground(BackgroundConstant.BLACK_OVERLAY);

		botContainers.add(botContainer1);
		botContainers.add(botContainer2);
		botContainers.add(botContainer3);

		gamePane.setLeft(botContainer1);
		gamePane.setTop(botContainer2);
		gamePane.setRight(botContainer3);
		gamePane.setBottom(playerContainer);
		gamePane.setCenter(cardPlayground);
		gamePane.setPadding(new Insets(20));

		gamePane.setBackground(BackgroundConstant.PRIMARY_GRADIENT);
		cardPlayground.setMaxHeight(300);
		cardPlayground.setMaxWidth(450);

		gamePaneContainer.setCenter(gamePane);
		gamePaneContainer.setRight(sideBar);

		root.getChildren().add(gamePaneContainer);

		scene = new Scene(root, 1280, 720);
	}

	public PlayerContainer getPlayerContainer() {
		return playerContainer;
	}

	public ArrayList<BotContainer> getBotContainers() {
		return botContainers;
	}

	public BorderPane getGamePane() {
		return gamePane;
	}

	public AnchorPane getCardPlayground() {
		return cardPlayground;
	}

	public StackPane getRoot() {
		return root;
	}

	public BorderPane getInformationPane() {
		return informationPane;
	}

	public SideBar getSideBar() {
		return sideBar;
	}

}
