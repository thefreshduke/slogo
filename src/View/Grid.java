package View;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import turtle.Turtle;
import GUIFunctions.GUIFunction;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public abstract class Grid extends Pane {
	protected int myID;
	protected HashMap<String, Class> myMap=new HashMap<String, Class>();
	protected EventHandler<KeyEvent> myEvent;
	public final static String DEFAULT_RESOURCE_SOURCE="/resources/GUIFunctionsMap";
	
	public int getID(){
		return myID;
	}
	public abstract Turtle addTurtle(Turtle myTurtle);
	public void setMap (HashMap setMyMap) {
		myMap = setMyMap;
	}
	public abstract Turtle addTurtle();
	public abstract void updateGUI(String myFunction);
	public abstract void updateGUI(String myFunction, List<? extends Number> myNumber);
	public abstract void sendErrorMessage(String s);
	public abstract void update(Collection<Turtle> activatedTurtles);
}
