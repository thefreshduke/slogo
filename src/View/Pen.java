package View;

import java.awt.List;
import java.util.Stack;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

//why do you not use arraylist
public class Pen extends Line{
	Stack<Line>myLines;
	String myColor;
	BorderStyle myStyle;
	
	public Pen(){
		myLines=new Stack<Line>();
		myColor="BLACK";
		myStyle=new SolidBorderStyle();
		
	}
	public void changeThickness(Number a){
		this.setStyle("-fx-border-width: a");
	}
	public void changeLineStyle(){
		this.setStyle("fx-border-");
	}
	public Line drawLine(int startX, int startY, int endX, int endY){
		Line myLine=new Line(startX, startY, endX, endY);
		myLine.setStroke(Paint.valueOf(myColor));
		myLine.getStrokeDashArray().addAll(myStyle.getStyle());
		myLines.push(myLine);
		return myLines.peek();
	}
	public void setColor(String color){
		myColor=color;
	}
	public void setBorderStyle(BorderStyle style){
		myStyle=style;
	}
	


}
