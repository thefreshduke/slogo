package commands;

import backendExceptions.BackendException;
import commands.turtleCommands.TurtleCommand;

public abstract class TurtleQuery extends TurtleCommand {

	public TurtleQuery(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getArgumentCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}