package commands.turtleCommands;

import java.util.ArrayList;
import java.util.List;

import commandParser.CommandFactory;
import commands.BaseCommand;
import backendExceptions.BackendException;

public abstract class MultipleTurtleCommand extends TurtleCommand {
	private static final int EVEN_NUMBER_CHECKER = 2;
	private static final String CONSTANT_INDICATOR = "constant";
	private static final String INVALID_ERROR_MESSAGE = "Malformed input for Turtle IDs";
	private BaseCommand myInternalCommand;
	private List<Integer> myTempActiveTurtleIDs;
	private String[] myTurtleIDs;
	
	public MultipleTurtleCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected void parseArguments (String userInput) throws BackendException {
		String[] splitInput = splitByInnerListCommand(userInput);
		String innerInput = splitInput[0];
		myTurtleIDs = innerInput.split(COMMAND_SEPARATOR);

		if ((myTurtleIDs.length == 1) && (myTurtleIDs[0].equals(""))) {
			throw new BackendException(null, "Invalid syntax for ID");
		}
		myTempActiveTurtleIDs = new ArrayList<>();
		String strTurtleID = "";
		int turtleID = 0;
		for (int i = 0; i < myTurtleIDs.length; i++) {
			strTurtleID = myTurtleIDs[i];
			if (isEven(i) && !strTurtleID.equals(CONSTANT_INDICATOR)) {
				throw new BackendException(null, INVALID_ERROR_MESSAGE);
			} else if(!isEven(i)){
				 turtleID = Integer.parseInt(strTurtleID);
				if (turtleID < 0) {
					throw new BackendException(null,
							"Invalid Turtle ID: negative value");
				}
				myTempActiveTurtleIDs.add(turtleID);
			}
		}

		String[] splitCommandLeftover = splitByInnerListCommand(splitInput[1]);
		String commandActions = splitCommandLeftover[0];
		setInternalCommand(CommandFactory.createCommand(commandActions, false));
		setLeftoverCommands(splitCommandLeftover[1]);
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

	protected int findMin (List<Integer> list) {
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

	public List<Integer> getFutureActiveTurtleIDs() {
		return myTempActiveTurtleIDs;
	}

	public void setActiveTurtleIDs(List<Integer> tempActiveTurtleIDs) {
		myTempActiveTurtleIDs = tempActiveTurtleIDs;
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
}
