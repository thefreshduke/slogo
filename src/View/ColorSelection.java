package View;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;

import javax.imageio.IIOException;
import javax.swing.JOptionPane;

import GUIFunctions.BackgroundColor;
import GUIFunctions.ColorFunction;
import GUIFunctions.GUIFunction;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class ColorSelection extends Pane{

	private SlogoView myView;
	private ArrayList<String> myColors = new ArrayList<>();
	private HashSet<ColorLabel> myLabels=new HashSet();
	private GridTracker myGrids;

	public ColorSelection (GridTracker grid){
		myGrids=grid;
		
		myLabels.add(new ColorLabel(0, 0, 80, 20, "BackgroundColor", new BackgroundColor(myGrids)));
		myLabels.add(new ColorLabel(100, 0, 80, 20, "Pen Color", new PenColor(myGrids)));
		
		this.relocate(0,250);
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
		
		this.setPrefSize(200, 200);
		this.getChildren().addAll(myLabels);
		this.getChildren().add(myColorPane());
		
	}
	public Collection<String> getAvailableColors(){
		return myColors;
	}
	public void addColor(){
		//myColors.add
	}
	public void removeColor(){
		
	}
	private HBox myColorPane(){
		HBox myBox=new HBox();
		for(String color: myColors){
			Circle myCircle=new Circle();
			myCircle.setFill(Paint.valueOf(color));
			myCircle.setOnMouseClicked(event->doColorEvent(color));
			myCircle.setRadius(10);
			myBox.getChildren().add(myCircle);	
		}
		myBox.relocate(30,50);
		return myBox;
	
	}
	private void doColorEvent(String color){
		for (ColorLabel myLabel: myLabels){
			myLabel.isActive(color);
		}
	}
	


	

}


