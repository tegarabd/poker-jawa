package com.example.pokerjawa.view.component;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class PlayAfterOneSecond {

	public PlayAfterOneSecond(Executor executor) {
		PauseTransition delay = new PauseTransition(Duration.seconds(1));
		delay.setOnFinished(event -> {
			executor.execute();
		});
		delay.play();
	}

	public interface Executor {
		public void execute();
	}

}
