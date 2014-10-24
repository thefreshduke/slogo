package View;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import GUIFunctions.GUIFunction;
import javafx.scene.layout.Pane;

public abstract class Grid extends Pane {
	protected int ID;
	protected HashMap<String, Class> myMap=new HashMap<String, Class>();
	public final static String DEFAULT_RESOURCE_SOURCE="/resources/GUIFunctionsMap";
	
	//need factory with static int
	public void Grid(){
		//ID=(int) (Math.random()*);
		makeMap();
		
	}
	public int getID(){
		return ID;
	}
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
}
