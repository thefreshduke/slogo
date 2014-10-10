package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;


public class ButtonTemplate extends Button implements UserObjects{

	/**
	 * Constructor
	 * @param s				Label for the Button	
	 * @param handler		Event for the Button to react upon
	 */
	public ButtonTemplate(String s, int x, int y, EventHandler<ActionEvent> handler){
		this.relocate(x, y);
		this.setText(s);
		this.addEvent(handler);
		setStyle();
		this.setPrefSize(75, 55);
	}
	
	public ButtonTemplate(String s, int x, int y, EventHandler<ActionEvent> handler, int width, int height){
		this.relocate(x, y);
		this.setText(s);
		this.addEvent(handler);
		this.setPrefSize(width, height);
		setStyle();
	}

	@Override
	public void addEvent(EventHandler<ActionEvent> handler) {
		this.setOnAction(handler);
		
	}
	public void setStyle(){
		setTextFill(Paint.valueOf("WHITE"));
		setStyle("-fx-background-color: BLACK; -fx-border-color: WHITE");
	}


}
