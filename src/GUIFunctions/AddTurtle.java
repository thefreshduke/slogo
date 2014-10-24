package GUIFunctions;

import View.SingleGrid;
import View.SlogoView;
import javafx.scene.image.Image;
import turtle.Position;
import turtle.Turtle;

public class AddTurtle extends Add{
	private static int ID;
	private Turtle myTurtle;
	public AddTurtle(){
		myTurtle=null;
		ID=0;
		
	}
	@Override
	public void doAction() {
		
	}
	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object addAction() {
		Image image = new Image("bowser.png");
		ID++;
		Turtle myTurtle = new Turtle(new Position(0, 0), image);
		myTurtle.setID(ID);
		myTurtle.setFitWidth(60);
		myTurtle.setPreserveRatio(true);
		myTurtle.setSmooth(true);
		return myTurtle;
	}
}

