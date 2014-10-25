package GUIFunctions;

import java.util.List;

import View.GridTracker;
import View.SingleGrid;
import View.SlogoView;
import javafx.scene.image.Image;
import turtle.Position;
import turtle.Turtle;

public class AddTurtle extends Add{
	private Turtle myTurtle;
	private GridTracker myGrid;
	public AddTurtle(GridTracker grid){
		myTurtle=null;		
		myGrid=grid;
	}
	@Override
	public void doAction() {
		
	}
	@Override
	public void doAction(List<? extends Number> newVal) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object addAction() {
		Image image = new Image("bowser.png");
		Turtle myTurtle = new Turtle(new Position(0, 0), image);
		myTurtle.setID(myGrid.getActiveGrid().getAllTurtles().size());
		myTurtle.setFitWidth(60);
		myTurtle.setPreserveRatio(true);
		myTurtle.setSmooth(true);
		return myTurtle;
	}
}

