package commands.turtleCommands;

import backendExceptions.BackendException;
import commands.TurtleQuery;
import commands.information.BaseTurtleContainer;

public class NumberOfTurtlesCommand extends TurtleQuery {

	public NumberOfTurtlesCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {
		BaseTurtleContainer turtle = getTurtleContainer();
		double numTurtles = turtle.getAllTurtles().size();
		return numTurtles;
	}
}
