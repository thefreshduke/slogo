package turtle;
import java.io.File;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import View.BorderStyle;
import View.Pen;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

/**
 * Backend Turtle class that has ability to modify its position (location and orientation).
 *    
 */
public class Turtle extends ImageView{
	private Position myPosition;
	private Pen myPen;
	private Stack<Position> myPastPositions;
	/**
	 * The Turtle takes a Position object (Composition technique) which encapsulates the data concerning the turtle's movement. 
	 * @param position - Position object that stores location information about the Turtle
	 */
	public Turtle(Position position, Image image) {
		super(image);		
		myPosition = position;
		myPen=new Pen();
		myPastPositions=new Stack<Position>();
	}
	
	/**
	 * Moves horizontally
	 * @param xIncrement - horizontal movement increment
	 */
	private void moveHorizontal(double xIncrement) {
		myPosition.moveHorizontal(xIncrement);

	}

	/**
	 * Moves the vertically 
	 * @param yIncrement - vertical movement increment
	 */
	private void moveVertical(double yIncrement) {
		myPosition.moveVertical(yIncrement);
	}

	public void move(double xPos, double yPos) {
		moveHorizontal(xPos);
		moveVertical(yPos);
		myPastPositions.push(new Position(myPosition.getXPos(), myPosition.getYPos(), myPosition.getRotate()));	}

	/**
	 * Move amount specified at current heading
	 * @param increment - straight-line distance to be moved
	 */
	public void move(double increment) {
		myPosition.move(increment);
		myPastPositions.push(new Position(myPosition.getXPos(), myPosition.getYPos(), myPosition.getRotate()));
	}

	/**
	 * Rotates by increment specified
	 * @param rotateIncrement - degrees of rotation
	 */
	public void rotate(double rotateIncrement) {
		myPosition.rotate(rotateIncrement);
	}

	/**
	 * @return List of x-coordinate positions
	 */
	public List<Double> getXPosList() {
		return myPosition.getXPosList();
	}

	/**
	 * @return List of y-coordinate positions
	 */
	public List<Double> getYPosList() {
		return myPosition.getYPosList();
	}

	/**
	 * 
	 * @return List of angle orientations
	 */
	public List<Double> getRotateList() {
		return myPosition.getRotateList();
	}

	/**
	 * @return Current angle orientation
	 */
	
	public double getOrientation() {
		return myPosition.getRotate();
	}
	/**
	 * @return Current x-coordinate
	 */
	public double getXPos() {
		return myPosition.getXPos();	
	}
	/**
	 * @return Current y-coordinate
	 */
	public double getYPos() {
		return myPosition.getYPos();
	}
	
	public void setXPos(double xPos) {
		myPosition.setXPos(xPos);
		
	}
	
	public void setYPos(double yPos) {
		myPosition.setYPos(yPos);
	}
	
	public void setRotation (double rotateVal) {
		myPosition.setRotation(rotateVal);
		
	}
	
	/*
	 * Pen stuff
	 */
	public void setPenColor(String color){
		myPen.setColor(color);
	}
	public void setPenBorderStyle(BorderStyle style){
		myPen.setBorderStyle(style);
	}
	public void setPenWidth(Number thickness){
		myPen.changeThickness(thickness);
	}
	public Line penUndo(){
		return myPen.undo();
	}
	public Pen getPen(){
		return myPen;
	}
	
	//could return null
	public Position undo(){
		if (myPastPositions.size()<=1){
			JOptionPane.showMessageDialog(null, "CANT UNDO");
			return null;
		}
		else{
			Position delete=myPastPositions.pop();
			myPosition=myPastPositions.peek();
			return delete;
		}
	}
	

}