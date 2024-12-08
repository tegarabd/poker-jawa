package com.example.pokerjawa.view.component;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Title extends Text {

	public Title(String text) {
		super(text);
		setFont(Font.font("Century Gothic", FontWeight.BLACK, 48));
	}

	public Title() {
		super();
		setFont(Font.font("Century Gothic", FontWeight.BLACK, 48));
	}

	public Title(double x, double y, String text) {
		super(x, y, text);
		setFont(Font.font("Century Gothic", FontWeight.BLACK, 48));
	}

}
