package commands.turtleCommands;

import java.util.ArrayList;
import java.util.List;

import backendExceptions.BackendException;

import commandParser.CommandFactory;
import commands.BaseCommand;
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
		storeFutureActiveIDs(innerInput);
		String[] splitCommandLeftover = splitByInnerListCommand(splitInput[1]);
		String commandActions = splitCommandLeftover[0];
		setInternalCommand(CommandFactory.createCommand(commandActions, false));
		setLeftoverCommands(splitCommandLeftover[1]);
	}
}
