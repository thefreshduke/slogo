package commands.viewCommands;

import turtle.Turtle;
import backendExceptions.BackendException;
import commands.ViewCommand;
import commands.information.BaseGridContainer;
import commands.turtleCommands.TurtleCommand;

public class PenUpCommand extends ViewCommand {

	private static final String PEN_UP = "penUp";

	public PenUpCommand(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {
		BaseGridContainer grid = getGridContainer();
		grid.updateDisplayOptions(PEN_UP);
		return 0;
	}

	@Override
	protected int getArgumentCount() {
		return 0;
	}
}