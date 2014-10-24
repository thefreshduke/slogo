package commands.viewCommands;

import turtle.Turtle;
import backendExceptions.BackendException;
import commands.ViewCommand;
import commands.information.BaseGridContainer;

public class ShowTurtleCommand extends ViewCommand {

	//TODO: GUI Function name to show the turtle
	private static final String SHOW_TURTLE = "";

	public ShowTurtleCommand(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {
		BaseGridContainer grid = getGridContainer();
		grid.updateDisplayOptions(SHOW_TURTLE);
		return 1;
	}

	@Override
	protected int getArgumentCount() {
		return 0;
	}
}
