package commands.viewQueries;

import turtle.Turtle;
import backendExceptions.BackendException;
import commands.ViewCommand;

public class YCorQuery extends ViewCommand {

	public YCorQuery(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
    protected double onExecute() throws BackendException {
		System.out.println("YCOR: " + turtle.getYPos()); //doesn't work for some reason???
		return turtle.getYPos();
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
