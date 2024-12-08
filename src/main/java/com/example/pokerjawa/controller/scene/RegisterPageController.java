package com.example.pokerjawa.controller.scene;

import com.example.pokerjawa.controller.stage.AuthStageController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.example.pokerjawa.model.builder.HumanPlayerBuilder;
import com.example.pokerjawa.model.player.HumanPlayer;
import com.example.pokerjawa.model.proxy.DatabaseProxy;
import com.example.pokerjawa.view.scene.RegisterPage;

public class RegisterPageController {

	private RegisterPage registerPage;

	private AuthStageController authStageController;

	public RegisterPageController(RegisterPage registerPage, AuthStageController authStageController) {
		super();
		this.registerPage = registerPage;
		this.authStageController = authStageController;
		addLoginLinkListener();
		addRegisterButtonListener();
	}

	private void addRegisterButtonListener() {
		registerPage.getRegisterButton().setOnAction(event -> {

			String username = registerPage.getUsernameTextField().getText();
			String password = registerPage.getPasswordField().getText();
			String confirmPassword = registerPage.getConfirmPasswordField().getText();

			if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
				showError("All fields can not be empty");
				return;
			}

			if (username.length() < 5 || username.length() > 10) {
				showError("Username length must be 5 - 10 characters");
				return;
			}

			if (password.length() < 8 || password.length() > 20) {
				showError("Password length must be 8 - 10 characters");
				return;
			}

			Boolean alpha = false;
			Boolean numeric = false;
			for (Character character : password.toCharArray()) {
				if (Character.isAlphabetic(character)) {
					alpha = true;
				}
				if (Character.isDigit(character)) {
					numeric = true;
				}
			}

			if (!alpha || !numeric) {
				showError("Password must be alpha numeric");
				return;
			}

			if (!password.equals(confirmPassword)) {
				showError("Confirm Password does not match");
				return;
			}

			HumanPlayer humanPlayer = new HumanPlayerBuilder().setUsername(username).setPassword(password).build();
			DatabaseProxy.getInstance().addPlayer(humanPlayer);
			authStageController.showLoginPage();
		});
	}

	private void addLoginLinkListener() {
		registerPage.getLoginLink().setOnMouseClicked(event -> authStageController.showLoginPage());
	}

	public void onShown() {
		resetFields();
	}

	private void resetFields() {
		registerPage.getUsernameTextField().setText("");
		registerPage.getPasswordField().setText("");
		registerPage.getConfirmPasswordField().setText("");
	}

	private void showError(String string) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Validation Error");
		alert.setHeaderText("Register Validation Failed");
		alert.setContentText(string);
		alert.showAndWait();
	}

	public RegisterPage getRegisterPage() {
		return registerPage;
	}

}
