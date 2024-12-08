package com.example.pokerjawa.view.component;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DefaultText extends Text {

	public DefaultText() {
		super();
		setFont(Font.font("Century Gothic", FontWeight.BOLD, 16));
	}

	public DefaultText(String string) {
		super(string);
		setFont(Font.font("Century Gothic", FontWeight.BOLD, 16));
	}

	public DefaultText(double x, double y, String text) {
		super(x, y, text);
		setFont(Font.font("Century Gothic", FontWeight.BOLD, 16));
	}

}
