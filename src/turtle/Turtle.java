package turtle;
import java.util.List;

import javafx.scene.image.ImageView;

/**
 * Backend Turtle class that has ability to modify its position (location and orientation).
 *    
 */
public class Turtle extends ImageView {
	private Position myPosition;

	/**
	 * The Turtle takes a Position object (Composition technique) which encapsulates the data concerning the turtle's movement. 
	 * @param position - Position object that stores location information about the Turtle
	 */
	public Turtle(Position position) {
		myPosition = position;
	}
	
	/**
	 * Moves horizontally
	 * @param xIncrement - horizontal movement increment
	 */
	public void moveHorizontal(double xIncrement) {
		myPosition.moveHorizontal(xIncrement);

	}

	/**
	 * Moves the vertically 
	 * @param yIncrement - vertical movement increment
	 */
	public void moveVertical(double yIncrement) {
		myPosition.moveVertical(yIncrement);
	}


	/**
	 * Move a specified amount in the horizontal and vertical 
	 * directions
	 * @param xIncrement
	 * @param yIncrement
	 */
	public void move(double xIncrement,  double yIncrement) {
		myPosition.move(xIncrement, yIncrement);
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
	
	public double getRotation() {
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

}