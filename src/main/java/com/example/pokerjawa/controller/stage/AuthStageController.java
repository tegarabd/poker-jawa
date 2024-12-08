package com.example.pokerjawa.controller.stage;

import com.example.pokerjawa.controller.Staging;
import com.example.pokerjawa.controller.scene.LoginPageController;
import com.example.pokerjawa.controller.scene.RegisterPageController;
import com.example.pokerjawa.view.scene.LoginPage;
import com.example.pokerjawa.view.scene.RegisterPage;
import com.example.pokerjawa.view.stage.AuthStage;

public class AuthStageController {

	private Staging staging;
	private AuthStage authStage;

	private LoginPageController loginPageController;
	private RegisterPageController registerPageController;

	public AuthStageController(AuthStage authStage, Staging staging) {
		super();
		this.staging = staging;
		this.authStage = authStage;
		this.authStage.setOnShowing(event -> showLoginPage());
	}

	public void showLoginPage() {
		if (loginPageController == null) {
			loginPageController = new LoginPageController(new LoginPage(), this);
		}
		loginPageController.onShown();
		authStage.setScene(loginPageController.getLoginPage().getScene());
	}

	public void showRegisterPage() {
		if (registerPageController == null) {
			registerPageController = new RegisterPageController(new RegisterPage(), this);
		}
		registerPageController.onShown();
		authStage.setScene(registerPageController.getRegisterPage().getScene());
	}

	public AuthStage getAuthStage() {
		return authStage;
	}

	public Staging getStaging() {
		return staging;
	}

}
