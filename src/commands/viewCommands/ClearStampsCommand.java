package commands.viewCommands;

import backendExceptions.BackendException;

import commands.ViewQuery;
import commands.information.BaseGridContainer;

public class ClearStampsCommand extends ViewQuery {
	private static final String CLEAR_STAMPS = "clearStamps";

	public ClearStampsCommand (String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected double onExecute () throws BackendException {
		BaseGridContainer grid = getGridContainer();
		grid.updateDisplayOptions(CLEAR_STAMPS);
		// Would need to have the updateDisplayOptions return a double, currently does not. 
		// Could be worked-around with a special case function, but not done as this would 
		// compromise design. 
		return 1;
	}
}