package commands.viewCommands;

import turtle.Turtle;
import backendExceptions.BackendException;
import commands.ViewCommand;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.turtleCommands.TurtleCommand;

public class ClearStampsCommand extends ViewCommand {

	//TODO change to include string name for grid function to clear stamps
	private static final String CLEAR_STAMPS = "clearStamps";

	public ClearStampsCommand(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {
		BaseGridContainer grid = getGridContainer();
		grid.updateDisplayOptions(CLEAR_STAMPS);
		// TODO is there something in particular that needs to be returned? Not clear from specs..
		return 0;
	}

	@Override
	protected int getArgumentCount() {
		return 0;
	}
}