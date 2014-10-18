package commands.viewCommands;

import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commands.ViewCommand;
import communicator.IVariableContainer;

public class HeadingCommand extends ViewCommand {

	public HeadingCommand(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double execute(Grid grid, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
//		turtle.setRotate(turtle.getRotate() + ???); // turtle heading isn't updating when i do rotate commands?
		System.out.println("Heading: " + turtle.getRotate());
		return turtle.getRotate();
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
