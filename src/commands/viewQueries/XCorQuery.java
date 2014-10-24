package commands.viewQueries;

import turtle.Turtle;
import backendExceptions.BackendException;
import commands.ViewCommand;

public class XCorQuery extends ViewCommand {

	public XCorQuery(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
    protected double onExecute() throws BackendException {
		System.out.println("XCOR: " + turtle.getXPos()); //doesn't work for some reason???
		return turtle.getXPos();
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
