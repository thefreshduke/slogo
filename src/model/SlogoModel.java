package model;

import java.util.List;

import View.Grid;
import commandParser.CommandFactory;
import commands.BaseCommand;
import communicator.IVariableContainer;
import javafx.scene.image.Image;
import turtle.Turtle;

public class SlogoModel {
	private static final String INITIAL_TURTLE_SPRITE = "bowser.png";
	private TurtleFactory myTurtleFactory;
	private CommandWrapper myCommandWrapper;
	
	public void initializeModel (Grid grid) {
		myTurtleFactory = new TurtleFactory();
		addTurtle(new Image(INITIAL_TURTLE_SPRITE));
		initializeCommandWrapper(grid);
	}	

	private void initializeCommandWrapper(Grid grid) {
		myCommandWrapper = new CommandWrapper();
		myCommandWrapper.initializeWrapper(grid, myTurtleFactory.findTurtle(0));
	}

	public void hardSetTurtle (double x, double y, double orientationAngle) {
		/*myTurtle.setXPos(x);
		myTurtle.setYPos(y);
		myTurtle.setRotation(orientationAngle);*/
	}

	public void addTurtle(Image image) {
		myTurtleFactory.createTurtle(image);
	}
	
	public List<Turtle> getActiveTurtles() {
		return myTurtleFactory.getActiveTurtles();
	}

	public BaseCommand createInitialCommand(String input) {
		return CommandFactory.createCommand(input, false);
	}
	
	public IVariableContainer getVariableContainer() {
		return myCommandWrapper.getVariableContainer();
	}

	public Turtle findTurtle(int ID) {
		return myTurtleFactory.findTurtle(ID);

	}

	public CommandWrapper getCommandWrapper() {
		return myCommandWrapper;
	}
}