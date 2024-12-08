package com.example.pokerjawa.view.component;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SmallText extends Text {
	public SmallText() {
		super();
		setFont(Font.font("Century Gothic", FontWeight.SEMI_BOLD, 14));
	}

	public SmallText(String string) {
		super(string);
		setFont(Font.font("Century Gothic", FontWeight.SEMI_BOLD, 14));
	}

	public SmallText(double x, double y, String text) {
		super(x, y, text);
		setFont(Font.font("Century Gothic", FontWeight.SEMI_BOLD, 14));
	}
}
