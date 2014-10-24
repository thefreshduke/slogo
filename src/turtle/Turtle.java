package turtle;
import java.util.ArrayList;
import java.util.List;



import commands.information.ITurtleBehavior;



import java.util.Stack;

import javax.swing.JOptionPane;

import GUIFunctions.BorderStyle;
import View.Pen;
import View.TurtleMovement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Line;

/**
 * Backend Turtle class that has ability to modify its position (location and orientation).
 */
public class Turtle extends ImageView implements ITurtleBehavior {
	private Position myPosition;
	private Pen myPen;
	private Stack<Position> myPastPositions;
	private int myID;



	private int velocity;
	

	/**
	 * The Turtle takes a Position object (Composition technique) which encapsulates the data concerning the turtle's movement. 
	 * @param position - Position object that stores location information about the Turtle
	 */
	public Turtle(Position position, Image image) {
		super(image);		
		myPosition = position;
		myPen=new Pen();
		myPastPositions=new Stack<Position>();
		velocity=5;
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

	@Override
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
	@Override
	public void rotate(double rotateIncrement) {
		myPosition.rotate(rotateIncrement);
	}



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

	public void setID (int ID) {
		myID = ID;
	}

	public int getID() {
		return myID;
	}
	public void move (KeyCode e){
		Direction myDirection=new Direction(e);
		Position myTempPosition=myDirection.move(myPosition, velocity);
		if (myTempPosition!=null){
			myPosition=myTempPosition;
			myPastPositions.add(myPosition);
		}
			
	}

	@Override
	public double setHeading(double absHeading) {
		double rotationDegrees = Math.abs(myPosition.getRotate() - absHeading);
		myPosition.setRotation(rotationDegrees);
		return rotationDegrees;
	}

	@Override
	public double towards(double x, double y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setXY(double x, double y) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}