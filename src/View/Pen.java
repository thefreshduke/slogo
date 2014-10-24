package View;

import java.awt.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import GUIFunctions.BorderStyle;
import GUIFunctions.SolidBorderStyle;
import turtle.Position;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

//why do you not use arraylist
public class Pen extends Line{
	Stack<Line>myLines;
	String myColor;
	BorderStyle myStyle;
	int thickness;
	boolean penDown;
	double startX;
	double startY;

	public Pen(){
		myLines=new Stack<Line>();
		myColor="BLACK";
		myStyle=new SolidBorderStyle();
		penDown=true;
		thickness=100;

	}

	public void changeThickness(Number a){
		this.setStyle("-fx-border-width: "+a);
	}
	public void changeLineStyle(Object[] myDashArray){
		this.getStrokeDashArray().addAll((Double[])myDashArray);
	}
	public void setInitialPosition(Double x, Double y){
		startX=x;
		startY=y;
	}
	public void setColor(String color){
		myColor=color;
	}
	public void setBorderStyle(BorderStyle style){
		myStyle=style;
	}
	public Line undo(){
		if (myLines.size()==0){
			JOptionPane.showMessageDialog(null,"CANT UNDO");
			return null;
		}
		else{
			return myLines.pop();
		}
	}
	public Line drawLine(double xPos, double yPos) {
		if (penDown){
			if (myLines.size()==0){
				Line myLine=new Line(startX, startY, xPos, yPos);
				myLines.push(myLine);
				return myLines.peek();
			}
			else{
				Line myLine=new Line(myLines.peek().getEndX(), myLines.peek().getEndY(), xPos, yPos);
				myLine.setStroke(Paint.valueOf(myColor));
				myLine.getStrokeDashArray().addAll(myStyle.getStyle());
				myLines.push(myLine);
				return myLines.peek();
			}
		}
		else
			return null;

	}
	public void setPenDown(boolean down){
		penDown=down;
	}



}
