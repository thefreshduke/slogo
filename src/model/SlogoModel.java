package model;

import commandParser.CommandFactory;
import commands.BaseCommand;
import communicator.IVariableContainer;
import communicator.MapBasedVariableContainer;
import javafx.scene.image.Image;
import turtle.Position;
import turtle.Turtle;

public class SlogoModel {
	private static final String INITIAL_TURTLE_SPRITE = "bowser.png";
	private Turtle myTurtle;
	private IVariableContainer myVariableContainer;

	public void initializeModel () {
		initializeTurtle();
		myVariableContainer = new MapBasedVariableContainer();
	}

	private void initializeTurtle() {
		Image image = new Image(INITIAL_TURTLE_SPRITE);
		myTurtle = new Turtle(new Position(0, 0), image);
		myTurtle.setFitWidth(60);
		myTurtle.setPreserveRatio(true);
		myTurtle.setSmooth(true);
	}	

	public void hardSetTurtle (double x, double y, double orientationAngle) {
		myTurtle.setXPos(x);
		myTurtle.setYPos(y);
		myTurtle.setRotation(orientationAngle);
	}

	public Turtle getTurtle() {
		return myTurtle;
	}
	
	public IVariableContainer getMyVariableContainer() {
		return myVariableContainer;
	}
	
	public BaseCommand createInitialCommand(String input) {
		return CommandFactory.createCommand(input, false);
		
	}
}