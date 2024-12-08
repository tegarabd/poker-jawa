package com.example.pokerjawa.view.component;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import com.example.pokerjawa.view.constant.BackgroundConstant;
import com.example.pokerjawa.view.constant.BorderConstant;

public class LargeButton extends Button {

	public LargeButton() {
		super();
		style();
	}

	private void style() {
		setFont(Font.font("Century Gothic", FontWeight.BLACK, 24));
		setBackground(BackgroundConstant.WHITE_CORNER_RADIUS);
		setPadding(new Insets(15, 45, 15, 45));
		setBorder(BorderConstant.DEFAULT_BORDER);
	}

	public LargeButton(String text, Node graphic) {
		super(text, graphic);
		style();
	}

	public LargeButton(String text) {
		super(text);
		style();
	}

}
