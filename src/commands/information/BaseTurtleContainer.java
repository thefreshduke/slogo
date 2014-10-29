package commands.information;

import java.util.Collection;

import javafx.scene.shape.Line;
import turtle.Position;
import turtle.Turtle;
import GUIFunctions.BorderStyle;
import View.Pen;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 *         The Base Turtle Container contains all the turtles in each grid and
 *         defines the allowed behaviors for each container
 */
public abstract class BaseTurtleContainer implements ITurtleBehavior,
		IInformationContainer {

	/**
	 * Removes a specific turtle by its ID.
	 * 
	 * @param turtleID
	 *            ID of turtle to remove.
	 */
	public abstract void removeTurtle(int turtleID);

	/**
	 * Adds a turtle to the container;
	 * 
	 * @param turtle
	 *            Turtle to add.
	 * @param isActive
	 *            Whether it is active or not
	 */
	public abstract void addTurtle(Turtle turtle, boolean isActive);

	/**
	 * Gets all turtles in the container
	 * 
	 * @return All turtles in the container
	 */
	public abstract Collection<Turtle> getAllTurtles();

	/**
	 * Gets all active turtles by their IDs.
	 * 
	 * @return IDs of all active turtles.
	 */
	public abstract Collection<Integer> getActiveTurtlesByID();

	/**
	 * Hard sets the active turtles to be the ones passed in. Active turtles
	 * from before will be set as inactive.
	 * 
	 * @param turtleIDs
	 */
	public abstract void hardSetActiveTurtles(Collection<Integer> turtleIDs);

	/**
	 * Gets all active turtles
	 * @return All active turtles
	 */
	public abstract Collection<Turtle> getActiveTurtles();

	/**
	 * Gets all active turtles by their IDs
	 * @return IDs of all active turtles
	 */
	public abstract Collection<Integer> getAllTurtlesByID();

	
	/**
	 * Sets all turtles in the container as inactive.
	 */
	public abstract void clear();

	private interface TurtleActionWithResult {
		public Object execute(Turtle turtle);
	}

	
	/**
	 * Performs specified action on all active turtles. 
	 * @param action Action to be performed
	 * @return Return value of action
	 */
	private Object loopOverTurtle(TurtleActionWithResult action) {
		Object result = null;
		for (Turtle turtle : getActiveTurtles()) {
			result = action.execute(turtle);
		}
		return result;
	}

	@Override
	public void rotate(double rotateIncrement) {
		loopOverTurtle(turtle -> {
			turtle.rotate(rotateIncrement);
			return rotateIncrement;
		});
	}

	@Override
	public void move(double xIncrement, double yIncrement) {
		loopOverTurtle(turtle -> {
			turtle.move(xIncrement, yIncrement);
			return null;
		});
	}

	public double setHeading(double absHeading) {
		return (Double) loopOverTurtle(turtle -> turtle
				.setHeading(absHeading));
	}

	public double towardsPosition(double newXPos, double newYPos) {
		return (Double) loopOverTurtle(turtle -> turtle
				.towardsPosition(newXPos, newYPos));
	}

	public double setPosition(double newXPos, double newYPos) {
		return (Double) loopOverTurtle(turtle -> turtle.setPosition(
				newXPos, newYPos));
	}

	public void moveTowardsHeading(double increment) {
		loopOverTurtle(turtle -> {
			turtle.moveTowardsHeading(increment);
			return increment;
		});
	}

	public double getOrientation() {
		return (Double) loopOverTurtle(turtle -> turtle
				.getOrientation());
	}

	public double getXPos() {
		return (Double) loopOverTurtle(turtle -> turtle.getXPos());
	}

	public double getYPos() {
		return (Double) loopOverTurtle(turtle -> turtle.getYPos());
	}

	public void setXPos(double xPos) {
		loopOverTurtle(turtle -> {
			turtle.setXPos(xPos);
			return xPos;
		});
	}

	public void setYPos(double yPos) {
		loopOverTurtle(turtle -> {
			turtle.setYPos(yPos);
			return yPos;
		});
	}

	public void setRotation(double rotateVal) {
		loopOverTurtle(turtle -> {
			turtle.setRotation(rotateVal);
			return rotateVal;
		});
	}

	public void setPenColor(String color) {
		loopOverTurtle(turtle -> {
			turtle.setPenColor(color);
			return color;
		});
	}

	public void setPenBorderStyle(BorderStyle style) {
		loopOverTurtle(turtle -> {
			turtle.setPenBorderStyle(style);
			return style;
		});
	}

	public void setPenWidth(Number thickness) {
		loopOverTurtle(turtle -> {
			turtle.setPenWidth(thickness);
			return thickness;
		});
	}

	public Line penUndo() {
		return (Line) loopOverTurtle(turtle -> turtle.penUndo());
	}

	public Pen getPen() {
		return (Pen) loopOverTurtle(turtle -> turtle.getPen());
	}

	public Position undo() {
		return (Position) loopOverTurtle(turtle -> turtle.undo());
	}

	@Override
	public void setVisibility(boolean isVisible) {
		loopOverTurtle(turtle -> {
			turtle.setVisibility(isVisible);
			return null;
		});
	}

	public abstract void setTurtleAsActive(int turtleID);

}