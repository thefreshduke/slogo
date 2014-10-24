package commands;

import commands.turtleCommands.TurtleCommand;

import backendExceptions.BackendException;

public abstract class ViewQuery extends TurtleCommand {

	public ViewQuery(String command, boolean isExpression)
			throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected int getArgumentCount() {
		return 0;
	}
}
