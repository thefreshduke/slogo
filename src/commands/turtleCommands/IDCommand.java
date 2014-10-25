package commands.turtleCommands;

import turtle.Turtle;
import backendExceptions.BackendException;
import commands.TurtleQuery;
import commands.information.BaseTurtleContainer;

public class IDCommand extends TurtleQuery{

	public IDCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {
		BaseTurtleContainer turtle = getTurtleContainer();
		Turtle lastActiveTurtle = turtle.getActiveTurtles().get(turtle.getActiveTurtles().size()-1);
		return lastActiveTurtle.getID();
	}


}
