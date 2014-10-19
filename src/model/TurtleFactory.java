package model;
import java.util.ArrayList;
import java.util.List;

import turtle.Position;
import turtle.Turtle;
import javafx.scene.image.Image;

public class TurtleFactory {
	
	private List<Turtle> myActiveTurtles = new ArrayList<Turtle>();
	
	public void createTurtle(Image image) {
		Turtle turtle = new Turtle(new Position(0, 0), image);
		turtle.setID(myActiveTurtles.size()-1);
		myActiveTurtles.add(turtle);	
	}
	
	public List<Turtle> getActiveTurtles() {
		return myActiveTurtles;
	}	
}