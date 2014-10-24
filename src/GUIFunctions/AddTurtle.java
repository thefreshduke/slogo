package GUIFunctions;

import View.GUIFunction;
import View.SingleGrid;
import View.SlogoView;
import javafx.scene.image.Image;
import turtle.Position;
import turtle.Turtle;

public class AddTurtle implements GUIFunction{
	private static int ID;
	private SingleGrid myGrid;
	private Turtle myTurtle;
	private SlogoView myView;
	public AddTurtle(SingleGrid grid, SlogoView view){
		myGrid=grid;
		myTurtle=null;
		myView=view;
		ID=0;
		
	}
	@Override
	public void doAction() {
		Image image = new Image("bowser.png");
		ID++;
		//Turtle myTurtle = new Turtle(new Position(0, 0), image, ID);
		myTurtle.setFitWidth(60);
		myTurtle.setPreserveRatio(true);
		myTurtle.setSmooth(true);
		myGrid.addTurtle(myTurtle);
	}
	@Override
	public void doAction(Number newVal) {
		// TODO Auto-generated method stub
		
	}
}

