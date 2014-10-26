package View;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import GUIFunctions.BackgroundColor;
import GUIFunctions.PenColor;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ColorSelection extends Pane{
	private ArrayList<String> myColors = new ArrayList<>();
	private HashSet<ColorLabel> myLabels=new HashSet<ColorLabel>();
	private GridTracker myGrids;
	private HBox colorBox=new HBox();

	public ColorSelection (GridTracker grid){
		myGrids=grid;
		myLabels.add(new ColorLabel(10, 0, 80, 20, "BackgroundColor", new BackgroundColor(myGrids, this)));
		myLabels.add(new ColorLabel(100, 0, 80, 20, "Pen Color", new PenColor(myGrids, this)));
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
			myColors.add("FFFFFF");
			myColors.add("000000");
		}
		this.setPrefSize(200, 200);
		this.getChildren().addAll(myLabels);
		this.getChildren().add(colorBox);
		myColorPane();
	}
	public List<String> getAvailableColors(){
		return myColors;
	}
	public void myColorPane(){
		int myWidth=50;
		this.getChildren().remove(colorBox);
		colorBox=new HBox();
		for(String color: myColors){
			Circle myCircle=new Circle();
			myCircle.setStyle("-fx-fill: #"+color);
			myCircle.setOnMouseClicked(event->doColorEvent(color));
			myCircle.setRadius(myWidth/myColors.size());
			colorBox.getChildren().add(myCircle);	
		}
		colorBox.relocate(10,50);
		this.getChildren().add(colorBox);
	}
	private void doColorEvent(String color){
		for (ColorLabel myLabel: myLabels){
			myLabel.isActive(color);
		}
	}
	public void setColor(int myIndex, String colorLowerCase){
		String color=colorLowerCase.toUpperCase();
		if (myColors.contains(color.toUpperCase())){
			switchPosition(myColors.indexOf(color), color, myIndex, myColors.get(myIndex));
		}
		else{
			myColors.add(color);
			switchPosition(myColors.indexOf(color), color, myIndex, myColors.get(myIndex));
		}
		myColorPane();
	}
	public void switchPosition(int firstIndex, String firstString, int secondIndex, String secondString){
		myColors.set(secondIndex, firstString);
		myColors.set(firstIndex, secondString);
		for (String color: myColors){
			System.out.println(color);
		}
	}
}


