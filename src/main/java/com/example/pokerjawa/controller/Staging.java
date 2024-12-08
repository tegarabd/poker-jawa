package com.example.pokerjawa.controller;

import com.example.pokerjawa.controller.stage.AuthStageController;
import com.example.pokerjawa.controller.stage.MainStageController;
import javafx.application.Application;
import javafx.stage.Stage;
import com.example.pokerjawa.model.proxy.DatabaseProxy;
import com.example.pokerjawa.view.stage.AuthStage;
import com.example.pokerjawa.view.stage.MainStage;

public class Staging extends Application {

	private AuthStageController authStageController;
	private MainStageController mainStageController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		initializeDatabase();
		showAuthStage();
	}

	public void showAuthStage() {
		if (authStageController == null) {
			authStageController = new AuthStageController(new AuthStage(), this);
		}
		if (mainStageController != null) {
			mainStageController.getMainStage().hide();
		}
		authStageController.getAuthStage().show();
	}

	private void initializeDatabase() {
		DatabaseProxy.getInstance().getCurrentPlayer();
	}

	public void showMainStage() {
		if (mainStageController == null) {
			mainStageController = new MainStageController(new MainStage());
		}
		if (authStageController != null) {
			authStageController.getAuthStage().hide();
		}
		mainStageController.getMainStage().show();
	}

}
