package View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Grid extends GridPane {
	private static final String REFERENCE_GRID_COLOR = "GREY";
	final int rows=10;
	final int columns=10;

	private String backgroundColor = "BLACK";
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
				a.setStyle("-fx-background-color:" + backgroundColor + "; -fx-border-color: " + REFERENCE_GRID_COLOR);
				this.add(a, b, i);
			}
		}
	}

	public void setBackgroundColor(String color){
		backgroundColor = color;
		for(Node n :this.getChildren()){
			n.setStyle("-fx-background-color:" + backgroundColor + "; -fx-border-color: "+ REFERENCE_GRID_COLOR);
		}
	}

	public void toggleRefGrid(boolean b){

		for(Node n :this.getChildren()){
			if(b){
				n.setStyle("-fx-background-color:" + backgroundColor + "; -fx-border-color: "+ backgroundColor);

			}
			else{
				n.setStyle("-fx-background-color:" + backgroundColor + "; -fx-border-color: " + REFERENCE_GRID_COLOR);

			}
		}

	}
}



