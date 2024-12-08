package com.example.pokerjawa.view.scene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import com.example.pokerjawa.view.component.LargeButton;
import com.example.pokerjawa.view.component.LinkText;
import com.example.pokerjawa.view.component.Title;
import com.example.pokerjawa.view.constant.BackgroundConstant;
import com.example.pokerjawa.view.constant.BorderConstant;

import java.io.File;

public class LoginPage extends Page {

	private BorderPane borderPane;

	private VBox formContainer, usernameContainer, passwordContainer;
	private Text title;
	private Label usernameLabel, passwordLabel;
	private TextField usernameTextField;
	private PasswordField passwordField;
	private Button loginButton;
	private LinkText registerLink;

	private ImageView backgroundImage;

	public LoginPage() {

		borderPane = new BorderPane();
		formContainer = new VBox();
		title = new Title("Login");
		usernameContainer = new VBox();
		passwordContainer = new VBox();
		usernameLabel = new Label("Username");
		passwordLabel = new Label("Password");
		usernameTextField = new TextField();
		passwordField = new PasswordField();
		loginButton = new LargeButton("Login");
		backgroundImage = new ImageView(new Image("auth_background.jpg", true));
		registerLink = new LinkText("Don't have an account? Register here");

		styleLabel(usernameLabel);
		styleLabel(passwordLabel);

		styleTextField(usernameTextField);
		styleTextField(passwordField);

		usernameLabel.setLabelFor(usernameTextField);
		passwordLabel.setLabelFor(passwordField);

		backgroundImage.setFitWidth(320);
		backgroundImage.setFitHeight(480);

		usernameContainer.getChildren().addAll(usernameLabel, usernameTextField);
		passwordContainer.getChildren().addAll(passwordLabel, passwordField);
		usernameContainer.setSpacing(5);
		passwordContainer.setSpacing(5);

		formContainer.getChildren().addAll(title, usernameContainer, passwordContainer, loginButton, registerLink);

		borderPane.setCenter(formContainer);
		borderPane.setRight(backgroundImage);

		formContainer.setPadding(new Insets(40));
		formContainer.setSpacing(15);
		formContainer.setBackground(BackgroundConstant.PRIMARY_GRADIENT);

		loginButton.setMaxWidth(Double.MAX_VALUE);

		scene = new Scene(borderPane, 800, 480);
	}

	private void styleTextField(TextField textField) {
		textField.setFont(Font.font("Century Gothic", FontWeight.BOLD, 16));
		textField.setBorder(BorderConstant.DEFAULT_BORDER);
		textField.setBackground(BackgroundConstant.WHITE_CORNER_RADIUS);
	}

	private void styleLabel(Label label) {
		label.setFont(Font.font("Century Gothic", FontWeight.BOLD, 14));
	}

	public TextField getUsernameTextField() {
		return usernameTextField;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public Button getLoginButton() {
		return loginButton;
	}

	public LinkText getRegisterLink() {
		return registerLink;
	}

}
