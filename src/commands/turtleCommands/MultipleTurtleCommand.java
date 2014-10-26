package commands.turtleCommands;

import java.util.ArrayList;
import java.util.List;

import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commands.BaseCommand;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;

public abstract class MultipleTurtleCommand extends TurtleCommand {
	private static final int EVEN_NUMBER_CHECKER = 2;
	protected static final String CONSTANT_INDICATOR = "constant";
	protected static final String INVALID_ERROR_MESSAGE = "Malformed input for Turtle IDs";
	protected static final String NO_SINGLE_ACTIVE_GRID = "Exactly one grid is not active";
	protected static final String INVALID_SYNTAX_FOR_ID = "Invalid syntax for ID";
	protected static final String INVALID_TURTLE_ID_NEGATIVE_VALUE = "Invalid Turtle ID: negative value";
	protected BaseCommand myInternalCommand;
	protected List<Integer> myFutureActiveTurtleIDs;
	protected String[] myTurtleIDs;


	public MultipleTurtleCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}

	protected int findMax (List<Integer> list) {
		int maxValue = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) > maxValue) {
				maxValue = list.get(i);
			}
		}
		return maxValue;
	}

	protected boolean isEven(int number) {
		return number % EVEN_NUMBER_CHECKER == 0;
	}

	public List<Integer> getFutureActiveTurtleIDs() {
		return myFutureActiveTurtleIDs;
	}

	public void setFutureActiveTurtleIDs(List<Integer> tempActiveTurtleIDs) {
		myFutureActiveTurtleIDs = tempActiveTurtleIDs;
	}

	public BaseCommand getInternalCommand() {
		return myInternalCommand;
	}

	public void setInternalCommand(BaseCommand internalCommand) {
		myInternalCommand = internalCommand;
	}

	@Override
	protected int getArgumentCount() {
		return 0;
	}

	protected void addFutureActiveTurtleTurtles() throws BackendException {
		BaseTurtleContainer turtle = getTurtleContainer();
		List<Integer> myAllTurtlesID = new ArrayList<>(turtle
				.getAllTurtlesByID());

		int maxID = findMax(myFutureActiveTurtleIDs);

		BaseGridContainer grid = getGridContainer();
		List<Grid> allGrids = (List<Grid>)grid.getActiveGrids();
		if (allGrids.size() != 1) {
			throw new BackendException(null, NO_SINGLE_ACTIVE_GRID);
		}

		Grid activeGrid = allGrids.get(0);

		for (int i = 0; i <= maxID; i++) {
			if (!myAllTurtlesID.contains(i)) {
				Turtle newTurtle = activeGrid.addTurtle();
				turtle.addTurtle(newTurtle, false);
			}
		}
		turtle.setActiveTurtles(getFutureActiveTurtleIDs());
	}

}
