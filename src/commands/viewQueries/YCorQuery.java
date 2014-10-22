package commands.viewQueries;

import turtle.Turtle;
import View.SlogoView;
import backendExceptions.BackendException;
import commands.ViewCommand;
import communicator.IVariableContainer;

public class YCorQuery extends ViewCommand {

	public YCorQuery(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		System.out.println("YCOR: " + turtle.getYPos()); //doesn't work for some reason???
		return turtle.getYPos();
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
