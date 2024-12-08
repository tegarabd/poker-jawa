package com.example.pokerjawa.controller.scene;

import com.example.pokerjawa.controller.stage.AuthStageController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.example.pokerjawa.model.player.HumanPlayer;
import com.example.pokerjawa.model.proxy.DatabaseProxy;
import com.example.pokerjawa.view.scene.LoginPage;

public class LoginPageController {

	private LoginPage loginPage;

	private AuthStageController authStageController;

	public LoginPageController(LoginPage loginPage, AuthStageController authStageController) {
		super();
		this.loginPage = loginPage;
		this.authStageController = authStageController;
		addLoginButtonListener();
		addRegisterLinkListener();
	}

	private void addRegisterLinkListener() {
		loginPage.getRegisterLink().setOnMouseClicked(event -> {
			authStageController.showRegisterPage();
		});
	}

	private void addLoginButtonListener() {
		loginPage.getLoginButton().setOnAction(event -> {

			String username = loginPage.getUsernameTextField().getText();
			String password = loginPage.getPasswordField().getText();

			if (username.isEmpty() || password.isEmpty()) {
				showError("Username and Password can not be empty");
				return;
			}

			DatabaseProxy.getInstance().setUsername(username);
			DatabaseProxy.getInstance().setPassword(password);

			HumanPlayer currentPlayer = DatabaseProxy.getInstance().getCurrentPlayer();

			if (currentPlayer == null) {
				showError("Username not found or Password incorrect");
				return;
			}

			authStageController.getStaging().showMainStage();
		});
	}

	private void showError(String string) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Validation Error");
		alert.setHeaderText("Login Validation Failed");
		alert.setContentText(string);
		alert.showAndWait();
	}

	public LoginPage getLoginPage() {
		return loginPage;
	}

	public void onShown() {
		resetFields();
	}

	private void resetFields() {
		loginPage.getUsernameTextField().setText("");
		loginPage.getPasswordField().setText("");
	}

}
