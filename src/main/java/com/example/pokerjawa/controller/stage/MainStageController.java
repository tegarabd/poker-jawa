package com.example.pokerjawa.controller.stage;

import com.example.pokerjawa.controller.scene.GamePageController;
import com.example.pokerjawa.controller.scene.HomePageController;
import com.example.pokerjawa.view.scene.GamePage;
import com.example.pokerjawa.view.scene.HomePage;
import com.example.pokerjawa.view.stage.MainStage;

public class MainStageController {

	private MainStage mainStage;

	private GamePageController gamePageController;
	private HomePageController homePageController;

	public MainStageController(MainStage mainStage) {
		super();
		this.mainStage = mainStage;
		this.mainStage.setOnShowing(event -> showHomePage());
	}

	public void showGamePage(Boolean forceNew) {
		if (forceNew || gamePageController == null) {
			gamePageController = new GamePageController(new GamePage(), this);
			gamePageController.onShown();
		}
		mainStage.setScene(gamePageController.getGamePage().getScene());
	}

	public void showHomePage() {
		if (homePageController == null) {
			homePageController = new HomePageController(new HomePage(), this);
		}
		homePageController.onShown();
		mainStage.setScene(homePageController.getHomePage().getScene());
	}

	public HomePageController getHomePageController() {
		return homePageController;
	}

	public MainStage getMainStage() {
		return mainStage;
	}

}
