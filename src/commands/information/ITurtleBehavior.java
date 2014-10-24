package commands.information;

import javafx.scene.shape.Line;
import turtle.Position;
import GUIFunctions.BorderStyle;
import View.Pen;

public interface ITurtleBehavior {

	public void rotate(double rotateIncrement);

	public void move(double xIncrement,  double yIncrement);

	public double setHeading(double absHeading);

	public double towardsPosition(double newXPos, double newYPos);

	public double setPosition(double newXPos, double newYPos);

	public void moveTowardsHeading(double increment);

	public double getOrientation();

	public double getXPos();

	public double getYPos();

	public void setXPos(double xPos);

	public void setYPos(double yPos);

	public void setRotation (double rotateVal);

	public void setPenColor(String color);

	public void setPenBorderStyle(BorderStyle style);

	public void setPenWidth(Number thickness);

	public Line penUndo();

	public Pen getPen();

	public Position undo();
}


