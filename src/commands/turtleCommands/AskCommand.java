package commands.turtleCommands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import turtle.Turtle;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import View.Grid;
import backendExceptions.BackendException;

public class AskCommand extends TurtleCommand {

	private static final int EVEN_NUMBER_CHECKER = 2;
	private static final String CONSTANT_INDICATOR = "constant";
	private static final String INVALID_ERROR_MESSAGE = "Malformed input for Turtle IDs";
	private BaseCommand myInternalCommand;
	private List<Integer> myCurrentActiveTurtleIDs;
	private List<Integer> myTempActiveTurtleIDs;
	private String [] myTurtleIDs;


	public AskCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {

		if ((turtleIDs.length == 1) && (turtleIDs[0].equals(""))) {
			throw new BackendException(null, "Invalid syntax for ID");
		}

		myTempActiveTurtleIDs = new ArrayList<Integer>();
		String turtleID = "";
		for (int i = 0; i < turtleIDs.length; i++) {
			turtleID = turtleIDs[i];
			if (isEven(i) && !turtleID.equals(CONSTANT_INDICATOR)) {
				throw new BackendException(null, INVALID_ERROR_MESSAGE);
			} else {
				if (Integer.parseInt(turtleID) < 0) {
					throw new BackendException(null, "Invalid Turtle ID: negative value");
				} 
				myTempActiveTurtleIDs.add(i);
			}
		}
		BaseTurtleContainer turtle = getTurtleContainer();
		myCurrentActiveTurtleIDs = (List<Integer>) turtle.getActiveTurtlesByID();
		String commandActions = splitInput[1];
		myInternalCommand = CommandFactory.createCommand(commandActions, false);

		List<Integer> myAllTurtlesID = (List<Integer>) turtle.getAllTurtlesByID();

		BaseGridContainer grid = getGridContainer();
		List<Grid> allGrids = (List<Grid>) grid.getActiveGrids();
		if (grid.getActiveGrids().size() != 1) {
			throw new BackendException(null, "More than one grid is active");
		}
		Grid activeGrid = allGrids.get(0);

		int minID = findMin(myCurrentActiveTurtleIDs);
		int maxID = findMax(myCurrentActiveTurtleIDs);

		for (int i = minID; i <  maxID; i++) {
			if (!myAllTurtlesID.contains(i)) {
				Turtle newTurtle = activeGrid.addTurtle();
				if (myTempActiveTurtleIDs.contains(i)) {
					turtle.addTurtle(newTurtle, true);
				} else {
					turtle.addTurtle(newTurtle, false);
				}
			}
		}	

	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		String[] splitInput = splitByInnerListCommand(userInput);
		String innerInput = splitInput[0];
		myTurtleIDs = innerInput.split(COMMAND_SEPARATOR);

		if ((myTurtleIDs.length == 1) && (myTurtleIDs[0].equals(""))) {
			throw new BackendException(null, "Invalid syntax for ID");
		}

		String turtleID = "";
		for (int i = 0; i < myTurtleIDs.length; i++) {
			turtleID = myTurtleIDs[i];
			if (isEven(i) && !turtleID.equals(CONSTANT_INDICATOR)) {
				throw new BackendException(null, INVALID_ERROR_MESSAGE);
			} else {
				if (Integer.parseInt(turtleID) < 0) {
					throw new BackendException(null, "Invalid Turtle ID: negative value");
				} 
				myTempActiveTurtleIDs.add(i);
			}
		}

		String commandActions = splitInput[1];
		myInternalCommand = CommandFactory.createCommand(commandActions, false);
		setLeftoverCommands(myInternalCommand.getLeftoverString());
	}

	private int findMax(List<Integer> list) {
		int maxValue = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) > maxValue) {
				maxValue = list.get(i);
			}
		}
		return maxValue;
	}


	private int findMin(List<Integer> list) {
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < minValue) {
				minValue = list.get(i);
			}
		}
		return minValue;
	}

	private boolean isEven (int number) {
		return number % EVEN_NUMBER_CHECKER == 0;
	}

	@Override
	protected int getArgumentCount() {
		return 2;
	}
}
