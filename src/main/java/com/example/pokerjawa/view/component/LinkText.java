package com.example.pokerjawa.view.component;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LinkText extends Text {

	public LinkText() {
		super();
		style();
	}

	public LinkText(String string) {
		super(string);
		style();
	}

	public LinkText(double x, double y, String text) {
		super(x, y, text);
		style();
	}

	private void style() {
		setFill(Color.BLUE);
		setFont(Font.font("Century Gothic", FontWeight.SEMI_BOLD, 14));
		setUnderline(true);
	}
}
