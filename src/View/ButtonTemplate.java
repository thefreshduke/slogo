package View;

import GUIFunctions.GUIFunction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class ButtonTemplate extends Button implements UserObjects{
	private final int textSize=10;
	private final int offSet=2;
	/**
	 * Constructor
	 * @param s				Label for the Button	
	 * @param handler		Event for the Button to react upon
	 */
	public ButtonTemplate(String s, double x, double y, GUIFunction myFunction){
		this(s,x,y,myFunction,75,55);
	}
	public ButtonTemplate(String s, double x, double y, GUIFunction myFunction, int width, int height){
		this(s,x,y,event->myFunction.doAction(),width,height);
	}
	
	public ButtonTemplate(String s, double x, double y, EventHandler myEvent, int width, int height){
		this.relocate(x, y);
		this.setText(s);
		this.addEvent(myEvent);
		this.setPrefSize(width, height);
		this.addEventHandler(MouseEvent.MOUSE_ENTERED, event->actionOnMouseHover());
		this.addEventHandler(MouseEvent.MOUSE_EXITED,  event->actionOnMouseExit());
		setStyle(textSize);
	}
	@Override
	public void addEvent(EventHandler<ActionEvent> handler) {
		this.setOnAction(handler);
	}
	public void setStyle(int text){
		setTextFill(Paint.valueOf("WHITE"));
		setStyle("-fx-background-color: BLACK; -fx-border-color: WHITE; -fx-font-size:"+text);
	}
	public void actionOnMouseHover(){
		setStyle(textSize+offSet);
	}
	public void actionOnMouseExit(){
		setStyle(textSize-offSet);
	}


}
