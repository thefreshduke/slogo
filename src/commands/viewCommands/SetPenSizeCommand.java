package commands.viewCommands;

import java.util.ArrayList;
import java.util.List;

import backendExceptions.BackendException;
import commands.ViewCommand;
import commands.information.BaseGridContainer;

public class SetPenSizeCommand extends ViewCommand {
	private static final String SET_PEN_SIZE = "penThickness";
	
	public SetPenSizeCommand (String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected double onExecute () throws BackendException {
		BaseGridContainer grid = getGridContainer();
		double penSizeIndex = getExpressionList()[0].execute();
		List<Double> penSizeList = new ArrayList<Double>();
		penSizeList.add(penSizeIndex);
		grid.updateDisplayOptions(SET_PEN_SIZE, penSizeList);
		return penSizeIndex;
	}

	@Override
	protected int getArgumentCount () {
		return 1;
	}

}
