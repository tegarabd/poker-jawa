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

public class RegisterPage extends Page {

	private BorderPane borderPane;

	private VBox formContainer, usernameContainer, passwordContainer, confirmPasswordContainer;
	private Text title;
	private Label usernameLabel, passwordLabel, confirmPasswordLabel;
	private TextField usernameTextField;
	private PasswordField passwordField;
	private PasswordField confirmPasswordField;
	private Button registerButton;
	private LinkText loginLink;

	private ImageView backgroundImage;

	public RegisterPage() {

		borderPane = new BorderPane();
		formContainer = new VBox();
		title = new Title("Register");
		usernameContainer = new VBox();
		passwordContainer = new VBox();
		confirmPasswordContainer = new VBox();
		usernameLabel = new Label("Username");
		passwordLabel = new Label("Password");
		confirmPasswordLabel = new Label("Confirm Password");
		usernameTextField = new TextField();
		passwordField = new PasswordField();
		confirmPasswordField = new PasswordField();
		registerButton = new LargeButton("Register");
		backgroundImage = new ImageView(new Image("auth_background.jpg", true));
		loginLink = new LinkText("Already have an account? Login here");

		styleLabel(usernameLabel);
		styleLabel(passwordLabel);
		styleLabel(confirmPasswordLabel);

		styleTextField(usernameTextField);
		styleTextField(passwordField);
		styleTextField(confirmPasswordField);

		usernameLabel.setLabelFor(usernameTextField);
		passwordLabel.setLabelFor(passwordField);
		confirmPasswordLabel.setLabelFor(confirmPasswordField);

		backgroundImage.setFitWidth(320);
		backgroundImage.setFitHeight(480);

		usernameContainer.getChildren().addAll(usernameLabel, usernameTextField);
		passwordContainer.getChildren().addAll(passwordLabel, passwordField);
		confirmPasswordContainer.getChildren().addAll(confirmPasswordLabel, confirmPasswordField);
		usernameContainer.setSpacing(5);
		passwordContainer.setSpacing(5);
		confirmPasswordContainer.setSpacing(5);

		formContainer.getChildren().addAll(title, usernameContainer, passwordContainer, confirmPasswordContainer,
				registerButton, loginLink);

		borderPane.setCenter(formContainer);
		borderPane.setRight(backgroundImage);

		formContainer.setPadding(new Insets(40));
		formContainer.setSpacing(15);
		formContainer.setBackground(BackgroundConstant.PRIMARY_GRADIENT);

		registerButton.setMaxWidth(Double.MAX_VALUE);

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

	public Button getRegisterButton() {
		return registerButton;
	}

	public PasswordField getConfirmPasswordField() {
		return confirmPasswordField;
	}

	public LinkText getLoginLink() {
		return loginLink;
	}

}
