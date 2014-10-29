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
public class SetPaletteCommand extends ViewCommand {
	private static final String SET_PALETTE = "setPalette";

	public SetPaletteCommand(String command, boolean isExpression)
			throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected int getArgumentCount() {
		return 4;
	}

	@Override
	protected double onExecute() throws BackendException {
		double paletteIndex = getExpressionList()[0].execute();
		double red = getExpressionList()[1].execute();
		double green = getExpressionList()[2].execute();
		double blue = getExpressionList()[3].execute();
		List<Double> paletteOptions = new ArrayList<Double>();
		paletteOptions.add(paletteIndex);
		paletteOptions.add(red);
		paletteOptions.add(green);
		paletteOptions.add(blue);
		BaseGridContainer grid = getGridContainer();
		grid.updateDisplayOptions(SET_PALETTE, paletteOptions);
		return paletteIndex;
	}






}
