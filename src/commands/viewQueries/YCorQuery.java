package commands.viewQueries;

import backendExceptions.BackendException;
import commands.TurtleQuery;
import commands.information.BaseTurtleContainer;

public class YCorQuery extends TurtleQuery {

	public YCorQuery(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {
		BaseTurtleContainer turtle = getTurtleContainer();
		double yCoordinate = turtle.getYPos();
		return yCoordinate;
	}
}
