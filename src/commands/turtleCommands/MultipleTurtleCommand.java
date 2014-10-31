package commands.turtleCommands;

import java.util.ArrayList;
import java.util.List;

import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commands.BaseCommand;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
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


	public MultipleTurtleCommand (String userInput, boolean isExpression)
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

	protected boolean isEven (int number) {
		return number % EVEN_NUMBER_CHECKER == 0;
	}

	public List<Integer> getFutureActiveTurtleIDs () {
		return myFutureActiveTurtleIDs;
	}

	public void setFutureActiveTurtleIDs (List<Integer> tempActiveTurtleIDs) {
		myFutureActiveTurtleIDs = tempActiveTurtleIDs;
	}

	public BaseCommand getInternalCommand () {
		return myInternalCommand;
	}

	public void setInternalCommand (BaseCommand internalCommand) {
		myInternalCommand = internalCommand;
	}

	@Override
	protected int getExpressionCount () {
		return 0;
	}

	protected void addFutureActiveTurtleTurtles () throws BackendException {
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
		turtle.hardSetActiveTurtles(getFutureActiveTurtleIDs());
	}
	
	protected void storeFutureActiveIDs (String innerInput)
			throws BackendException {
		myTurtleIDs = innerInput.split(COMMAND_SEPARATOR);

		if ((myTurtleIDs.length == 1) && (myTurtleIDs[0].equals(""))) {
			throw new BackendException(null, INVALID_SYNTAX_FOR_ID);
		}
		myFutureActiveTurtleIDs = new ArrayList<>();
		String strTurtleID = "";
		int turtleID = 0;
		for (int i = 0; i < myTurtleIDs.length; i++) {
			strTurtleID = myTurtleIDs[i];
			if (isEven(i) && !strTurtleID.equals(CONSTANT_INDICATOR)) {
				throw new BackendException(null, INVALID_ERROR_MESSAGE);
			}
			else if (!isEven(i)) {
				turtleID = Integer.parseInt(strTurtleID);
				if (turtleID < 0) {
					throw new BackendException(null,
							INVALID_TURTLE_ID_NEGATIVE_VALUE);
				}
				myFutureActiveTurtleIDs.add(turtleID);
			}
		}
	}
}
