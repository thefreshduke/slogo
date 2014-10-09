package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


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
		this.setPrefSize(75, 75);
	}
	
	public ButtonTemplate(String s, int x, int y, EventHandler<ActionEvent> handler, int width, int height){
		this.relocate(x, y);
		this.setText(s);
		this.addEvent(handler);
		this.setPrefSize(width, height);
	}

	@Override
	public void addEvent(EventHandler<ActionEvent> handler) {
		this.setOnAction(handler);
		
	}


}
