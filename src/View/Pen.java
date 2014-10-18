package View;

import java.awt.List;
import java.util.Stack;

import turtle.Position;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

//why do you not use arraylist
public class Pen extends Line{
	Stack<Line>myLines;
	String myColor;
	BorderStyle myStyle;
	boolean penDown;

	public Pen(Position initial){
		myLines=new Stack<Line>();
		myLines.push(new Line(initial.getXPos(), initial.getYPos(), initial.getXPos(), initial.getYPos()));
		myColor="BLACK";
		myStyle=new SolidBorderStyle();
		penDown=true;

	}
	
	public void changeThickness(Number a){
		this.setStyle("-fx-border-width: a");
	}
	public void changeLineStyle(){
		this.setStyle("fx-border-");
	}

	public void setColor(String color){
		myColor=color;
	}
	public void setBorderStyle(BorderStyle style){
		myStyle=style;
	}
	public Line undo(){
		return myLines.pop();
	}
	public Line drawLine(double xPos, double yPos) {
		if (penDown){
			Line myLine=new Line(myLines.peek().getEndX(), myLines.peek().getEndX(), xPos, yPos);
			myLine.setStroke(Paint.valueOf(myColor));
			myLine.getStrokeDashArray().addAll(myStyle.getStyle());
			myLines.push(myLine);
			return myLines.peek();
		}
		else
			return null;

	}
	public void setPenDown(boolean down){
		penDown=down;
	}



}
