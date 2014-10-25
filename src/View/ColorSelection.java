package View;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.imageio.IIOException;
import javax.swing.JOptionPane;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ColorSelection extends Pane{

	private SlogoView myView;
	private ArrayList<String> myColors = new ArrayList<>();
	private Label myBackgroundLabel=new Label("Background Color");
	private Label myPenLabel=new Label("Pen Color");
	private GridTracker myGrids;

	public ColorSelection(GridTracker grid){
		myGrids=grid;
		this.setPrefSize(200, 300);
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
		myBackgroundLabel.setPrefSize(100, 50);
		myBackgroundLabel.setPrefSize(100, 50);
		myBackgroundLabel.relocate(0, 0);
		myBackgroundLabel.relocate(100, 0);
		this.getChildren().add(myBackgroundLabel);
		this.getChildren().add(myPenLabel);
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
			myCircle.setOnMouseClicked(event->myGrids.getActiveGrid().setBackgroundColor(color));
			myCircle.setRadius(10);
			myBox.getChildren().add(myCircle);	
		}
		return myBox;
	
	}
	//st pallette 0 rgb


	

}


