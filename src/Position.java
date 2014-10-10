import java.util.List;

/**
 * Position object that stores and manipulates basic location (x-, y- coordinates) and rotation
 * orientation (degrees).  Can be extended to incorporate a z-coordinate for a 3D rendering.
 */
public class Position {
	
	/**
	 * No argument constructor
	 */
	public Position() {
		
	};
	
	/**
	 * Constructor that sets the initial x-, and y- coordinates
	 * @param xPos -  initial x-coordinate
	 * @param yPos - initial y-coordinate
	 */
	public Position(double xPos, double yPos) {
	
	}

	/**
	 * Alternate constructor that takes an additional angle orientation  (degrees) parameter 
	 * @param xPos - initial x-coordinate
	 * @param yPos - initial y-coordinate
	 * @param rotatePos - initial orientation (degrees)
	 */
	public Position(double xPos, double yPos, double rotatePos) {
		this(xPos, yPos);
	}

	/**
	 * Move horizontally
	 * @param xIncrement - horizontal movement increment
	 */
	public void moveHorizontal(double xIncrement) {
	

	}

	/**
	 * Move vertically 
	 * @param yIncrement - vertical movement increment
	 */
	public void moveVertical(double yIncrement) {

	}


	/**
	 * Move specified amount in the horizontal and vertical directions
	 * @param xIncrement
	 * @param yIncrement
	 */
	public void move(double xIncrement,  double yIncrement) {
		moveHorizontal(xIncrement);
		moveVertical(yIncrement);
	}

	/**
	 * Rotate by specified increment
	 * @param rotateIncrement - rotation increment
	 */
	public void rotate(double rotateIncrement) {

	}

	/**
	 * @return List of x-coordinates
	 */
	public List<Double> getXPosList() {
		return null;
	}

	/**
	 * @return List of y-coordinates
	 */
	public List<Double> getYPosList() {
		return null;
	}

	/**
	 * 
	 * @return List of angle orientations
	 */
	public List<Double> getRotateList() {
		return null;
	}

	/**
	 * @return Current rotation angle
	 */
	public double getRotate() {
		return 0.0;
	}

	/**
	 * @return Current x-coordinate
	 */
	public double getXPos() {
		return 0.0;
	}

	/**
	 * @return Current y-coordinate
	 */
	public double getYPos() {
		return 0.0;
	}
}