package commands.turtleCommands;

import java.util.List;

import turtle.Turtle;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import View.Grid;
import backendExceptions.BackendException;

public class TellCommand extends MultipleTurtleCommand {

	public TellCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {
		BaseTurtleContainer turtle = getTurtleContainer();
		List<Integer> myAllTurtlesID = (List<Integer>)turtle
				.getAllTurtlesByID();

		BaseGridContainer grid = getGridContainer();
		List<Grid> allGrids = (List<Grid>)grid.getActiveGrids();
		if (grid.getActiveGrids().size() != 1) {
			throw new BackendException(null, "More than one grid is active");
		}
		Grid activeGrid = allGrids.get(0);

		int minID = findMin(getActiveTurtleIDs());
		int maxID = findMax(getActiveTurtleIDs());

		for (int i = minID; i <= maxID; i++) {
			if (!myAllTurtlesID.contains(i)) {
				Turtle newTurtle = activeGrid.addTurtle();
				turtle.addTurtle(newTurtle, false);
			}
		}
		turtle.setActiveTurtles(getActiveTurtleIDs());
		double result = getInternalCommand().execute();

		return result;
	}

}
