package View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class Grid extends GridPane {
	
	public Grid(int height, int width, KeyFrame frame){
		
		this.setPrefHeight(height);
		this.setPrefWidth(width);
		Timeline time=new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		time.getKeyFrames().add(frame);
	}
	
	
}
