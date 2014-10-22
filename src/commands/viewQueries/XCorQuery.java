package commands.viewQueries;

import turtle.Turtle;
import View.SlogoView;
import backendExceptions.BackendException;
import commands.ViewCommand;
import commands.information.IVariableContainer;

public class XCorQuery extends ViewCommand {

	public XCorQuery(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
		System.out.println("XCOR: " + turtle.getXPos()); //doesn't work for some reason???
		return turtle.getXPos();
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
