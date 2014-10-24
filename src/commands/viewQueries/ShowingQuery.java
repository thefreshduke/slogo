package commands.viewQueries;

import turtle.Turtle;
import backendExceptions.BackendException;
import commands.ViewCommand;

public class ShowingQuery extends ViewCommand {

	public ShowingQuery(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double execute() throws BackendException {
//		return turtleIsShowing ? 1 : 0; //return 1 if turtle showing, 0 if not
		System.out.println("Is turtle showing?");
		return 0;
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
