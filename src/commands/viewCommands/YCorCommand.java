package commands.viewCommands;

import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commands.ViewCommand;
import communicator.IVariableContainer;

public class YCorCommand extends ViewCommand {

	public YCorCommand(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double execute(Grid grid, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		System.out.println("YCOR: " + turtle.getYPos()); //doesn't work for some reason???
		return turtle.getYPos();
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
