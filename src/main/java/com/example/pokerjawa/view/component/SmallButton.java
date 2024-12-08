package com.example.pokerjawa.view.component;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import com.example.pokerjawa.view.constant.BackgroundConstant;
import com.example.pokerjawa.view.constant.BorderConstant;

public class SmallButton extends Button {

	public SmallButton() {
		super();
		style();
	}

	private void style() {
		setFont(Font.font("Century Gothic", FontWeight.BOLD, 16));
		setBackground(BackgroundConstant.WHITE_CORNER_RADIUS);
		setPadding(new Insets(10, 40, 10, 40));
		setBorder(BorderConstant.DEFAULT_BORDER);
	}

	public SmallButton(String text, Node graphic) {
		super(text, graphic);
		style();
	}

	public SmallButton(String text) {
		super(text);
		style();
	}

}
