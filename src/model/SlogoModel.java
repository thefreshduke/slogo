package model;

import java.util.List;

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
	private TurtleFactory myTurtleFactory;
	public void initializeModel () {
		myVariableContainer = new MapBasedVariableContainer();
		initializeTurtle(new Image(INITIAL_TURTLE_SPRITE));
	}	

	public void hardSetTurtle (double x, double y, double orientationAngle) {
		myTurtle.setXPos(x);
		myTurtle.setYPos(y);
		myTurtle.setRotation(orientationAngle);
	}

	public void initializeTurtle(Image image) {
		myTurtleFactory = new TurtleFactory();
		myTurtleFactory.createTurtle(image);
	}
	public List<Turtle> getActiveTurtles() {
		return myTurtleFactory.getActiveTurtles();
	}

	public IVariableContainer getMyVariableContainer() {
		return myVariableContainer;
	}

	public BaseCommand createInitialCommand(String input) {
		return CommandFactory.createCommand(input, false);

	}
	
	public Turtle findTurtle(int ID) {
		return myTurtleFactory.findTurtle(ID);
	}
}