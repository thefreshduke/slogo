package turtle;
import java.util.ArrayList;
import java.util.List;

/**
 * Position object that stores and manipulates basic location (x-, y- coordinates) and rotation
 * orientation (degrees).  Can be extended to incorporate a z-coordinate for a 3D rendering.
 */
public class Position {
	private double myXPos;
	private double myYPos;
	private double myRotate;

	/**
	 * List that holds the set of all x-coordinates positions
	 */
	private List<Double> myXPosList;
	/**
	 * List that holds the set of y-coordinates positions
	 */
	private List<Double> myYPosList;

	/**
	 * List that holds the set of angle orientation positions
	 */
	private List<Double> myRotateList;

	/**
	 * No argument constructor
	 */
	public Position() {};
	
	/**
	 * Constructor that sets the initial x-, and y- coordinates
	 * @param xPos -  initial x-coordinate
	 * @param yPos - initial y-coordinate
	 */
	public Position(double xPos, double yPos) {
		myXPosList = new ArrayList<Double>();
		myYPosList = new ArrayList<Double>();
		myRotateList = new ArrayList<Double>();
		myXPos = xPos;
		myXPosList.add(myXPos);
		myYPos = yPos;
		myYPosList.add(myYPos);
	}

	/**
	 * Alternate constructor that takes an additional angle orientation  (degrees) parameter 
	 * @param xPos - initial x-coordinate
	 * @param yPos - initial y-coordinate
	 * @param rotatePos - initial orientation (degrees)
	 */
	public Position(double xPos, double yPos, double rotatePos) {
		this(xPos, yPos);
		myRotate = rotatePos;
		myRotateList.add(myRotate);
	}

	/**
	 * Move horizontally
	 * @param xIncrement - horizontal movement increment
	 */
	public void moveHorizontal(double xIncrement) {
		myXPos+= xIncrement;
		myXPosList.add(myXPos);

	}

	/**
	 * Move vertically 
	 * @param yIncrement - vertical movement increment
	 */
	public void moveVertical(double yIncrement) {
		myYPos+= yIncrement;
		myYPosList.add(myYPos);
	}

	/**
	 * Move amount specified at current heading 
	 * @param increment - straight-line distance to be moved
	 */
	public void move(double increment) {
		moveHorizontal(increment*(Math.cos(Math.toRadians(myRotate))));
		moveVertical(increment*(Math.sin(Math.toRadians(myRotate))));
	}


	/**
	 * Rotate by specified increment
	 * @param rotateIncrement - rotation increment
	 */
	public void rotate(double rotateIncrement) {
		myRotate+=rotateIncrement;
		myRotateList.add(rotateIncrement);
	}

	/**
	 * @return List of x-coordinates
	 */
	public List<Double> getXPosList() {
		return myXPosList;
	}

	/**
	 * @return List of y-coordinates
	 */
	public List<Double> getYPosList() {
		return myYPosList;
	}

	/**
	 * 
	 * @return List of angle orientations
	 */
	public List<Double> getRotateList() {
		return myRotateList;
	}

	/**
	 * @return Current rotation angle
	 */
	public double getRotate() {
		return myRotate;
	}

	/**
	 * @return Current x-coordinate
	 */
	public double getXPos() {
		return myXPos;
	}

	/**
	 * @return Current y-coordinate
	 */
	public double getYPos() {
		return myYPos;
	}
	
	//ZZZ Change/implement!!!
	@Override
	public boolean equals(Object o) {
		return false;
	}
	
	/*@Override 
	public int hashCode() {
		return 0;
		
	}*/
	
	public void setXPos(double xPos) {
		myXPos = xPos;
		
	}
	
	public void setYPos(double yPos) {
		myYPos = yPos;
	}
	
	public void setRotation (double rotateVal) {
		myRotate = rotateVal;		
	}
}