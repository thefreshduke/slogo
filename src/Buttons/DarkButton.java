
//this entire file is a part of my masterpiece
//Petra Ronald

package Buttons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import View.UserObjects;
import GUIFunctions.GUIFunction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class DarkButton extends Button implements UserObjects{
	private int  textSize;
	private final int offSet;
	private Properties myProperties;
	/**
	 * Constructor
	 * @param s				Label for the Button	
	 * @param handler		Event for the Button to react upon
	 */
	public DarkButton(String name, double x, double y, GUIFunction myFunction){
		loadInResources();
		setText(name);
		relocate(x, y);
		textSize=Integer.parseInt(myProperties.getProperty("textSize"));
		offSet=Integer.parseInt(myProperties.getProperty("effectOffSet"));
		addEvent(event->myFunction.doAction());
		addEventHandler(MouseEvent.MOUSE_ENTERED, event->actionOnMouseHover());
		addEventHandler(MouseEvent.MOUSE_EXITED,  event->actionOnMouseExit());
		setStyle(textSize);
	}

	@Override
	public void addEvent(EventHandler<ActionEvent> handler) {
		this.setOnAction(handler);
	}

	private void setStyle(int text){
		setPrefSize(Integer.parseInt(myProperties.getProperty("myWidth")), 
				Integer.parseInt(myProperties.getProperty("myHeight")));
		setTextFill(Paint.valueOf(myProperties.getProperty("textColor")));
		setStyle("-fx-background-color: "+myProperties.getProperty("backgroundColor")+
				"; -fx-border-color: "+myProperties.getProperty("borderColor") + "; -fx-font-size:" +text);
	}

	private void actionOnMouseHover(){
		setStyle(textSize+offSet);
	}

	private void actionOnMouseExit(){
		setStyle(textSize-offSet);
	}
	private void loadInResources(){
		myProperties=new Properties();
		InputStream stream = getClass().getClassLoader().getResourceAsStream("./resources/DarkButton"+".Properties");
		try {
			myProperties.load(stream);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Missing Resource File");
		}
	}
}