package com.example.pokerjawa.view.component.card;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TemporaryCard extends BorderPane {
	
	public TemporaryCard(Integer width, Integer height) {
		setPrefWidth(width);
		setPrefHeight(height);
		setMaxHeight(height);
		setBorder(new Border(new BorderStroke(Color.DARKGREEN, 
	            BorderStrokeStyle.DASHED, new CornerRadii(10), new BorderWidths(5))));
	}
	
}
