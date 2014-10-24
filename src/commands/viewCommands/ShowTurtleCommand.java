package commands.viewCommands;

import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commands.ViewCommand;
import commands.information.IVariableContainer;

public class ShowTurtleCommand extends ViewCommand {

	public ShowTurtleCommand(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double execute(Grid grid, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		System.out.println("Show Turtle");
		return 1;
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
