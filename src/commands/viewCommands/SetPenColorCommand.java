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
public class SetPenColorCommand extends ViewCommand {
	private static final String SET_PEN_COLOR = "penColor";

	public SetPenColorCommand (String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected double onExecute () throws BackendException {
		BaseGridContainer grid = getGridContainer();
		double penColorIndex = getExpressionList()[0].execute();
		List<Double> penColorList = new ArrayList<Double>();
		penColorList.add(penColorIndex);

		grid.updateDisplayOptions(SET_PEN_COLOR, penColorList);
		return penColorIndex;
	}

	@Override
	protected int getArgumentCount () {
		return 1;
	}

}
