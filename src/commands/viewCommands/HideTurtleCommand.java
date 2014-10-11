package commands.viewCommands;

import turtle.Turtle;
import View.SlogoView;
import backendExceptions.BackendException;
import commands.ViewCommand;
import communicator.IVariableContainer;

public class HideTurtleCommand extends ViewCommand {

	public HideTurtleCommand(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		System.out.println("Hide Turtle");
		return 0;
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
