package View;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import turtle.Turtle;
import communicator.BaseController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Grid extends Pane {
	private String backgroundColor = "WHITE";
	public int myHeight;
	public int myWidth;
	private int translate=50;
	private ImageView myImageView;
	private HashSet<Line> myGridLines=new HashSet<Line>();
	private ArrayList<Turtle> activeTurtles=new ArrayList<Turtle>();
	private ArrayList<Turtle> allTurtles=new ArrayList<Turtle>();
	
	
	public Grid(int height, int width, KeyFrame frame, Turtle turtle){//Turtle turtle){
		this.setPrefSize(width,height);
		myHeight=height/translate;
		myHeight=myHeight*translate;
		myWidth=width/translate;
		myWidth=myWidth*translate;
		Timeline time=new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		myImageView=new ImageView();
		getChildren().add(myImageView);
		time.getKeyFrames().add(frame);
		makeGridLines();
		setBackgroundColor(backgroundColor);
		
	}
	public Collection<Turtle> getAllTurtles(){
		return allTurtles;
	}
	public void addTurtle(Turtle t){
		t.setXPos(myWidth/2);
		t.setYPos(myWidth/2);
		moveTurtle(t);
		allTurtles.add(t);
		getChildren().add(t);
		
	}

	public void setBackgroundColor(String color){
		backgroundColor = color;
		this.getChildren().remove(myImageView);
		setStyle("-fx-background-color: "+backgroundColor);
		
	}


	public void toggleRefGrid(boolean b){
		if (b){
			makeGridLines();
		}
		else{
			this.getChildren().removeAll(myGridLines);
			myGridLines.clear();
		}
	}

	private void makeGridLines(){
		for (int i=0; i<myHeight; i+=translate){
			for (int j=0; j<myWidth; j+=translate){
				drawGridLine(i, j);
			}
		}
	}

	
	private void drawGridLine(int y, int x){
		Line verticalGridLine=new Line(x, 0, x, myHeight);
		verticalGridLine.setStroke(Paint.valueOf("GREY"));
		verticalGridLine.setStyle("-fx-fill: GREY");
		Line horizontalGridLine=new Line(0, y, myWidth, y);
		horizontalGridLine.setStroke(Paint.valueOf("GREY"));
		this.getChildren().addAll(verticalGridLine, horizontalGridLine);
		myGridLines.add(verticalGridLine);
		myGridLines.add(horizontalGridLine);
	}


	public void clear(){
		this.getChildren().removeAll();
	}

	
	public void moveTurtle(Turtle t){
		t.relocate(t.getXPos(), t.getYPos());
	}
	
	
	
	
	/**
	 * Takes in the coordinates (x,y) from the controller and pops the last coordinate from the stack to call move(int x, int y)
	 * @param x		x location on the Grid
	 * @param y		y location on the Grid
	 */
	public void update(Collection<Turtle> activatedTurtles){
		activeTurtles.clear();
		for (Turtle active: activatedTurtles){
			moveTurtle(active);
			active.getPen().drawLine(active.getXPos(), active.getYPos());
			activeTurtles.add(active);
		}	
	}
	public Collection<Turtle> getActiveTurtles(){
		return activeTurtles;
	}
	public Collection<Pen> getActivePens(){
		Collection<Pen> pens=new ArrayList<Pen>();
		for (Turtle t: activeTurtles)
			pens.add(t.getPen());
		return pens;
	}
}

