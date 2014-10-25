package commands.viewCommands;

import backendExceptions.BackendException;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.turtleCommands.TurtleCommand;

public class ClearScreenCommand extends TurtleCommand {

	private static final String CLEAR_GUI = "clear";

	public ClearScreenCommand(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {
		BaseGridContainer grid = getGridContainer();
		BaseTurtleContainer turtle = getTurtleContainer();
		double distanceTraveled = turtle.setPosition(0, 0);
		grid.updateDisplayOptions(CLEAR_GUI);
		grid.update(turtle.getActiveTurtles());
		return distanceTraveled;
	}

	@Override
	protected int getArgumentCount() {
		return 0;
	}
}