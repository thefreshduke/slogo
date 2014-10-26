package commands.viewCommands;

import java.util.ArrayList;
import java.util.List;

import backendExceptions.BackendException;
import commands.ViewCommand;
import commands.information.BaseGridContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class SetBackgroundCommand extends ViewCommand {
	private static final String SET_BACKGROUND_COLOR = "backgroundColor";

	public SetBackgroundCommand (String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected double onExecute () throws BackendException {
		BaseGridContainer grid = getGridContainer();
		double bgIndex = getExpressionList()[0].execute();
		List<Double> bgIndexList = new ArrayList<Double>();
		bgIndexList.add(bgIndex);
		grid.updateDisplayOptions(SET_BACKGROUND_COLOR, bgIndexList);
		return bgIndex;
	}

	@Override
	protected int getArgumentCount () {
		return 1;
	}
}
