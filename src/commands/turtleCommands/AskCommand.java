package commands.turtleCommands;

import java.util.ArrayList;
import java.util.List;

import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;

public class AskCommand extends MultipleTurtleCommand {

	public AskCommand (String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected double onExecute () throws BackendException {
		BaseTurtleContainer turtle = getTurtleContainer();
		List<Integer> myCurrentActiveTurtleIDs = new ArrayList<>(turtle
				.getActiveTurtlesByID());
		BaseCommand internalCommand = getInternalCommand();
		addFutureActiveTurtleTurtles();
		double result = internalCommand.execute();
		turtle.setActiveTurtles(myCurrentActiveTurtleIDs);

		return result;
	}

	@Override
	protected void parseArguments (String userInput) throws BackendException {
		String[] splitInput = splitByInnerListCommand(userInput);
		String innerInput = splitInput[0];
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
			} else if(!isEven(i)){
				turtleID = Integer.parseInt(strTurtleID);
				if (turtleID < 0) {
					throw new BackendException(null,
							INVALID_TURTLE_ID_NEGATIVE_VALUE);
				}
				myFutureActiveTurtleIDs.add(turtleID);
			}
		}

		String[] splitCommandLeftover = splitByInnerListCommand(splitInput[1]);
		String commandActions = splitCommandLeftover[0];
		setInternalCommand(CommandFactory.createCommand(commandActions, false));
		setLeftoverCommands(splitCommandLeftover[1]);
	}

}
