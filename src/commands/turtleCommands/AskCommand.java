package commands.turtleCommands;

import java.util.ArrayList;
import java.util.List;

import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;

public class AskCommand extends MultipleTurtleCommand {

	private static final String MULTIPLE_ACTIVE_GRID_MESSAGE = "More than one grid is active";

	public AskCommand (String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected double onExecute () throws BackendException {

		BaseTurtleContainer turtle = getTurtleContainer();
		List<Integer> myAllTurtlesID = new ArrayList<>(turtle
				.getAllTurtlesByID());
		List<Integer> myCurrentActiveTurtleIDs = new ArrayList<>(turtle
				.getActiveTurtlesByID());

		BaseGridContainer grid = getGridContainer();
		List<Grid> allGrids = (List<Grid>)grid.getActiveGrids();
		if (grid.getActiveGrids().size() != 1) {
			throw new BackendException(null, MULTIPLE_ACTIVE_GRID_MESSAGE);
		}
		Grid activeGrid = allGrids.get(0);

		int minID = findMin(getFutureActiveTurtleIDs());
		int maxID = findMax(getFutureActiveTurtleIDs());
		
		if (getFutureActiveTurtleIDs().size() != 1) {
			
		}

		for (int i = minID; i <= maxID; i++) {
			if (!myAllTurtlesID.contains(i)) {
				Turtle newTurtle = activeGrid.addTurtle();
				turtle.addTurtle(newTurtle, false);
			}
		}
		turtle.setActiveTurtles(getFutureActiveTurtleIDs());
		double result = getInternalCommand().execute();
		turtle.setActiveTurtles(myCurrentActiveTurtleIDs);

		return result;
	}
}
