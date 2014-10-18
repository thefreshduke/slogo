import java.util.List;
import java.util.Stack;

import View.BorderStyle;
import View.Pen;

/**
 * Backend Turtle class that has ability to modify its position (location and orientation).
 *    
 */
public class Turtle {
	
	// I know you mentioned no instance variables in the API, but without this this class won't 
	// compile without errors. The move methods on the Turtle call the corresponding methods 
	// on the Position object.
	private Position myPosition;
	private Pen myPen;
	private Stack<Position> myPoints;

	/**
	 * The Turtle takes a Position object (Composition technique) which encapsulates the data concerning the turtle's movement. 
	 * @param position - Position object that stores location information about the Turtle
	 */
	public Turtle(Position position) {
		myPosition = position;
		myPoints=new Stack<Position>();
		//myPoints.add(new Position (0, 0 ))

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
	public double getRotate() {
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
	
	
	public void setPenColor(String color){
		myPen.setColor(color);
	}
	public void setPenBorderStyle(BorderStyle style){
		myPen.setBorderStyle(style);
	}
	public void setPenWidth(Number thickness){
		myPen.changeThickness(thickness);
	}

}