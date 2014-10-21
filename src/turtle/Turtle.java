package turtle;
import java.util.List;

import commands.information.ITurtleBehavior;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Backend Turtle class that has ability to modify its position (location and orientation).
 */
public class Turtle extends ImageView implements ITurtleBehavior {
	private Position myPosition;
	private int myID;

	/**
	 * The Turtle takes a Position object (Composition technique) which encapsulates the data concerning the turtle's movement. 
	 * @param position - Position object that stores location information about the Turtle
	 */
	public Turtle(Position position, Image image) {
		super(image);		
		myPosition = position;
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
	}

	/**
	 * Move amount specified at current heading
	 * @param increment - straight-line distance to be moved
	 */
	public void move(double increment) {
		myPosition.move(increment);
	}

	/**
	 * Rotates by increment specified
	 * @param rotateIncrement - degrees of rotation
	 */
	@Override
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

	public void setID (int ID) {
		myID = ID;
	}

	public int getID() {
		return myID;
	}
}