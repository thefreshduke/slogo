package View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

public class Grid extends GridPane {
	final int rows=10;
	final int columns=10;
	public Grid(int height, int width, KeyFrame frame){
		
		this.setPrefSize(width,height);
		Timeline time=new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		time.getKeyFrames().add(frame);
		this.makeGrid(height, width);
		//this.createStyle();
	}
	public void makeGrid(int height, int width){
		
		for (int i=0; i<rows; i++){
			this.getRowConstraints().add(new RowConstraints(height/rows));
		}
		for (int i=0; i<columns; i++){
			this.getColumnConstraints().add(new ColumnConstraints(width/columns));
		}
		for (int i=0; i<rows; i++){
			for (int b=0; b<columns; b++){
				Pane a=new Pane();
				a.setStyle("-fx-background-color:BLACK; -fx-border-color: WHITE");
				this.add(a, b, i);
			}
		}
	}
}
	
	

