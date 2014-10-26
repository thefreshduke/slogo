package View;

import java.awt.Color;
import java.awt.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import GUIFunctions.BorderStyle;
import GUIFunctions.SolidBorderStyle;
import turtle.Position;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;

public class Pen extends Line{
	private Stack<Line>myLines;
	private String myColor;
	private BorderStyle myStyle;
	private int myThickness;
	private boolean penDown;
	private double startX;
	private double startY;

	public Pen(){
		myLines=new Stack<Line>();
		myColor="000000";
		myStyle=new SolidBorderStyle();
		penDown=true;
		myThickness=1;

	}
	public String getPenColor(){
		return myColor;
	}

	public void changeThickness(Number howThick){
		myThickness=howThick.intValue();
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
		if (myLines.size()==0){
			Line myLine=new Line(startX, startY, xPos, yPos);
			setLineStyle(myLine);
			return myLines.peek();
		}
		else{
			Line myLine=new Line(myLines.peek().getEndX(), myLines.peek().getEndY(), xPos, yPos);
			setLineStyle(myLine);
			return myLines.peek();
		}
	}
	/**
	 * determines appearance of line such as color and thickness
	 * @param myLine
	 */
	private void setLineStyle(Line myLine){
		myLine.setStyle("-fx-stroke: #"+myColor);
		myLine.setStrokeWidth(myThickness);
		myLine.getStrokeDashArray().clear();
		myLine.getStrokeDashArray().addAll(myStyle.getStyle(myThickness));
		myLine.setVisible(penDown);
		myLines.push(myLine);
	}
	
	public void setPenDown(boolean down){
		penDown=down;
	}
	public boolean getPenDown(){
		return penDown;
	}



}
