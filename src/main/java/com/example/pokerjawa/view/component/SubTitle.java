package com.example.pokerjawa.view.component;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SubTitle extends Text {

	public SubTitle() {
		super();
		setFont(Font.font("Century Gothic", FontWeight.BOLD, 24));
	}

	public SubTitle(String string) {
		super(string);
		setFont(Font.font("Century Gothic", FontWeight.BOLD, 24));
	}

	public SubTitle(double x, double y, String text) {
		super(x, y, text);
		setFont(Font.font("Century Gothic", FontWeight.BOLD, 24));
	}

}
