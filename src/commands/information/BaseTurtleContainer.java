package commands.information;

import java.util.Collection;
import javafx.scene.shape.Line;
import GUIFunctions.BorderStyle;
import View.Pen;
import turtle.Position;
import turtle.Turtle;


public abstract class BaseTurtleContainer implements ITurtleBehavior, IInformationContainer {

	public abstract void removeTurtle (int turtleID);

	public abstract void addTurtle (Turtle turtle, boolean isActive);

	public abstract Collection<Turtle> getAllTurtles ();

	public abstract Collection<Integer> getActiveTurtlesByID ();

	public abstract void setActiveTurtles (Collection<Integer> turtleIDs);

	public abstract Collection<Turtle> getActiveTurtles ();

	private interface TurtleActionWithResult {
		public Object execute (Turtle turtle);
	}

	private Object loopOverTurtleWithResult (TurtleActionWithResult action) {
		Object result = null;
		for (Turtle turtle : getActiveTurtles()) {
			result = action.execute(turtle);
		}
		return result;
	}

	@Override
	public void rotate (double rotateIncrement) {
		loopOverTurtleWithResult(turtle -> {
			turtle.rotate(rotateIncrement);
			return rotateIncrement;
		});
	}

	@Override
	public void move (double xIncrement, double yIncrement) {
		loopOverTurtleWithResult(turtle -> {
			turtle.move(xIncrement, yIncrement);
			return null;
		});
	}

	public double setHeading (double absHeading) {
		return (Double) loopOverTurtleWithResult(turtle -> turtle.setHeading(absHeading));
	}

	public double towardsPosition (double newXPos, double newYPos) {
		return (Double) loopOverTurtleWithResult(turtle -> turtle.towardsPosition(newXPos, newYPos));
	}

	public double setPosition (double newXPos, double newYPos) {
		return (Double) loopOverTurtleWithResult(turtle -> turtle.setPosition(newXPos, newYPos));
	}

	public void moveTowardsHeading (double increment) {
		loopOverTurtleWithResult(turtle -> {
			turtle.moveTowardsHeading(increment);
			return increment;
		});
	}

	public double getOrientation () {
		return (Double) loopOverTurtleWithResult(turtle -> turtle.getOrientation());
	}

	public double getXPos () {
		return (Double) loopOverTurtleWithResult(turtle -> turtle.getXPos());
	}

	public double getYPos () {
		return (Double) loopOverTurtleWithResult(turtle -> turtle.getYPos());
	}

	public void setXPos (double xPos) {
		loopOverTurtleWithResult(turtle -> {
			turtle.setXPos(xPos);
			return xPos;
		});
	}

	public void setYPos (double yPos) {
		loopOverTurtleWithResult(turtle -> {
			turtle.setYPos(yPos);
			return yPos;
		});
	}

	public void setRotation (double rotateVal) {
		loopOverTurtleWithResult(turtle -> {
			turtle.setRotation(rotateVal);
			return rotateVal;
		});
	}

	public void setPenColor (String color) {
		loopOverTurtleWithResult(turtle -> {
			turtle.setPenColor(color);
			return color;
		});
	}

	public void setPenBorderStyle (BorderStyle style) {
		loopOverTurtleWithResult(turtle -> {
			turtle.setPenBorderStyle(style);
			return style;
		});
	}

	public void setPenWidth (Number thickness) {
		loopOverTurtleWithResult(turtle -> { 
			turtle.setPenWidth(thickness);
			return thickness;
		});
	}

	public Line penUndo () {
		return (Line) loopOverTurtleWithResult(turtle -> turtle.penUndo());
	}

	public Pen getPen () {
		return (Pen) loopOverTurtleWithResult(turtle -> turtle.getPen());
	}

	public Position undo (){
		return (Position) loopOverTurtleWithResult(turtle -> turtle.undo());
	}
}
