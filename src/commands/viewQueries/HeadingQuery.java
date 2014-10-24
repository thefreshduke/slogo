package commands.viewQueries;

import turtle.Turtle;
import backendExceptions.BackendException;
import commands.ViewCommand;

public class HeadingQuery extends ViewCommand {

	public HeadingQuery(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double execute() throws BackendException {
//		turtle.setRotate(turtle.getRotate() + ???); // turtle heading isn't updating when i do rotate commands?
		System.out.println("Heading: " + turtle.getRotate());
		return turtle.getRotate();
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
