package com.example.pokerjawa.view.constant;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class BackgroundConstant {

	public static final Background PRIMARY = new Background(
			new BackgroundFill(Color.SEAGREEN, CornerRadii.EMPTY, Insets.EMPTY));

	public static final Background PRIMARY_GRADIENT = new Background(new BackgroundFill(
			new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
					new Stop[] { new Stop(0, Color.SEAGREEN), new Stop(1, Color.DARKGREEN) }),
			CornerRadii.EMPTY, Insets.EMPTY));

	public static final Background WHITE = new Background(
			new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY));

	public static final Background WHITE_CORNER_RADIUS = new Background(
			new BackgroundFill(Color.WHITESMOKE, new CornerRadii(10), Insets.EMPTY));

	public static final Background BLACK_OVERLAY = new Background(
			new BackgroundFill(new Color(0, 0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY));
}
