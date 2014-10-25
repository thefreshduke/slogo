package commands.turtleCommands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.BaseTurtleContainer;
import backendExceptions.BackendException;

public class AskCommand extends TurtleCommand {

	private static final int EVEN_NUMBER_CHECKER = 2;
	private static final String CONSTANT_INDICATOR = "constant";
	private static final String INVALID_ERROR_MESSAGE = "Malformed input for Turtle IDs";
    private BaseCommand myInternalCommand;
    private LinkedHashSet<Integer> myCurrentActiveTurtleIDs;
    private LinkedHashSet<Integer> myTempActiveTurtleIDs;


			public AskCommand(String userInput, boolean isExpression)
					throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {
		
		
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		String[] splitInput = splitByInnerListCommand(userInput);
		String innerInput = splitInput[0];
		String [] turtleIDs = innerInput.split(COMMAND_SEPARATOR);
		myTempActiveTurtleIDs = new ArrayList<Integer>();
		for (int i = 0; i < turtleIDs.length; i++) {
			String turtleID = turtleIDs[i];
			if (isEven(i) && !turtleID.equals(CONSTANT_INDICATOR)) {
				throw new BackendException(null, INVALID_ERROR_MESSAGE);
			} else {
				myTempActiveTurtleIDs.add(i);
			}
		}
		BaseTurtleContainer turtle = getTurtleContainer();
		myCurrentActiveTurtleIDs = (LinkedHashSet<Integer>) turtle.getActiveTurtlesByID();
		String commandActions = splitInput[1];
		myInternalCommand = CommandFactory.createCommand(commandActions, false);
        setLeftoverCommands(myInternalCommand.getLeftoverString());
		

		//TODO: Figure out how to execute  BaseTurtleContainer turtle = getTurtleContainer();
	}


	private boolean isEven (int number) {
		return number % EVEN_NUMBER_CHECKER == 0;
	}

	@Override
	protected int getArgumentCount() {
		return 2;
	}
	//have the turtles execute commands
	//send turtles back to duke
}
