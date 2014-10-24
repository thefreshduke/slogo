package commands.viewCommands;

import backendExceptions.BackendException;
import commands.ViewCommand;
import commands.information.BaseGridContainer;

public class SetBackgroundCommand extends ViewCommand {

	//TODO: Add the GUI function to call
	private static final String SET_BACKGROUND_IMAGE = "";

	public SetBackgroundCommand(String command, boolean isExpression)
			throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {
		BaseGridContainer grid = getGridContainer();
		double bgIndex = getExpressionList()[0].execute();
		grid.updateDisplayOptions(SET_BACKGROUND_IMAGE, bgIndex);
		return bgIndex;
	}

	@Override
	protected int getArgumentCount() {
		return 1;
	}

}
