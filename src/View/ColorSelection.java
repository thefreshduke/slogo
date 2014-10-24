package View;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.imageio.IIOException;
import javax.swing.JOptionPane;

import javafx.scene.control.MenuBar;

public class ColorSelection {

	private SlogoView myView;
	private ArrayList<String> myColors = new ArrayList<>();


	public ColorSelection(String s){
		try {
			Properties prop = new Properties();
			InputStream stream = getClass().getClassLoader().getResourceAsStream("./resources/Colors.Properties");
			prop.load(stream);
			for(Object color : prop.keySet()){
				myColors.add(prop.getProperty((String) color)); 
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Color File not Found, using default colors");
			myColors.add("BLACK");
			myColors.add("WHITE");
		}

	}
	public Collection<String> getAvailableColors(){
		return myColors;
	}
	public void addColor(){
		//myColors.add(e)
	}
	public void removeColor(){
		
	}
	

	public MenuBar getPenColorMenuBar(){
		MenuBar mBar = new MenuBar();
		MenuTemplate m = new MenuTemplate("Pen Color");
		for(String s : myColors){
			m.addMenuItem(s, event -> myView.setPenColor(s));
		}
		mBar.getMenus().add(m);
		mBar.setPrefSize(150, 25);

		return mBar;
	}

}


