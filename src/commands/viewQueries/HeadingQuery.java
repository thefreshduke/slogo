package commands.viewQueries;

import turtle.Turtle;
import backendExceptions.BackendException;
import commands.TurtleQuery;
import commands.ViewCommand;
import commands.ViewQuery;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.turtleCommands.TurtleCommand;

public class HeadingQuery extends TurtleQuery {

	public HeadingQuery(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}
	
	@Override
	protected double onExecute() throws BackendException {
		BaseTurtleContainer turtle = getTurtleContainer();
		double currentHeading = turtle.getOrientation();
		return currentHeading;
	}
}
