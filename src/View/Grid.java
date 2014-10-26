package View;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import turtle.Turtle;
import GUIFunctions.GUIFunction;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public abstract class Grid extends Pane {
	protected int myID;
	protected HashMap<String, Class> myMap=new HashMap<String, Class>();
	protected EventHandler<KeyEvent> myEvent;
	public final static String DEFAULT_RESOURCE_SOURCE="/resources/GUIFunctionsMap";
	
	//need factory with static int
	public void Grid(int ID){
		myID=ID;
		makeMap();
		
	}
	public int getID(){
		return myID;
	}
	public abstract Turtle addTurtle(Turtle myTurtle);
	public void setMap (HashMap setMyMap) {
		myMap = setMyMap;
	}
	public abstract Turtle addTurtle();
	public void makeMap(){
		ResourceBundle myBundle=ResourceBundle.getBundle(DEFAULT_RESOURCE_SOURCE);
		Enumeration myKeys=myBundle.getKeys();
		while (myKeys.hasMoreElements()){
			String key=(String) myKeys.nextElement();
			String myFunctionClass=myBundle.getString(key);
			try {
				Class<? extends GUIFunction> guiClass = (Class<? extends GUIFunction>) Class.forName(myFunctionClass);
				//guiClass.getConstructor(Grid this);
				myMap.put(key, Class.forName(myFunctionClass));
			} catch (ClassNotFoundException e) {
				//JOptionPane.showMessageDialog();
			}
			
		}
		
	}
	public abstract void setPalette(Double myRed, Double myGreen, Double myBlue);
	public abstract void updateGUI(String myFunction);
	public abstract void updateGUI(String myFunction, List<? extends Number> myNumber);
	public abstract void sendErrorMessage(String s);
	public abstract void update(Collection<Turtle> activatedTurtles);
}
