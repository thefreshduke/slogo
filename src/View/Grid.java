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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;


public class Grid extends Pane {
	private String backgroundColor = "WHITE";
	public int myHeight;
	public int myWidth;
	private ImageView myImageView;
	private HashSet<Line> myGridLines=new HashSet<Line>();
	private HashSet<Turtle> activeTurtles=new HashSet<Turtle>();
	private HashSet<Turtle> allTurtles=new HashSet<Turtle>();


	public Grid(int height, int width, KeyFrame frame, Turtle turtle){//Turtle turtle){
		this.setPrefSize(width,height);
		myHeight=height;
		myWidth=width;
		Timeline time=new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		time.getKeyFrames().add(frame);
		setBackgroundColor(backgroundColor);

	}
	public Collection<Turtle> getAllTurtles(){
		return allTurtles;
	}
	public void addTurtle(Turtle t){
		t.move(myWidth/2,myHeight/2);
		t.getPen().setInitialPosition(t.getXPos(), t.getYPos());
		moveTurtle(t);
		allTurtles.add(t);
		activeTurtles.add(t);
		getChildren().add(t);

	}

	public void setBackgroundColor(String color){
		backgroundColor = color;
		this.getChildren().remove(myImageView);
		setStyle("-fx-background-color: "+backgroundColor);

	}
	public void moveTurtle(Turtle t){
		t.relocate(t.getXPos(), t.getYPos());
		t.rotate(t.getOrientation());
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
			getChildren().add(active.getPen().drawLine(active.getXPos(), active.getYPos()));
			activeTurtles.add(active);
		}	
	}
	
	public Collection<Turtle> getActiveTurtles(){
		return activeTurtles;
	}
	public Collection<Pen> getAllPens(){
		return getPens(allTurtles);
	}
	public Collection<Pen> getActivePens(){
		return getPens(activeTurtles);
	}
	private Collection<Pen> getPens(Collection<Turtle> myTurtles){
		Collection<Pen> pens=new ArrayList<Pen>();
		for (Turtle t: myTurtles){
			pens.add(t.getPen());
		}
		return pens;
	}
}

