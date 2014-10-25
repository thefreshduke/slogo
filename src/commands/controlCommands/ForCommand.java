package commands.controlCommands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.BaseVariableContainer;

public class ForCommand extends ControlCommand {
	private String myVariableName;
	private BaseCommand myStartCommand;
	private BaseCommand myEndCommand;
	private BaseCommand myIncrementCommand;
	private BaseCommand myInternalCommand;

	public ForCommand (String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected double onExecute () throws BackendException {
		BaseVariableContainer variableContainer = getVariableContainer();
		double returnValue = 0;
		int startValue = (int) myStartCommand.execute();
		int stopValue = (int) myEndCommand.execute();
		int incrementValue = (int) myIncrementCommand.execute();
		boolean varExistPreviously = false;
		BaseCommand oldCommand = null;
		if (variableContainer.containsVariable(myVariableName)) {
			oldCommand = variableContainer.getValue(myVariableName);
			varExistPreviously = true;
		}
		for (int i = startValue; i < stopValue; i += incrementValue) {
			variableContainer.addVariable(myVariableName, i);
			returnValue = myInternalCommand.execute();
		}
		if (varExistPreviously) {
			variableContainer.addVariable(myVariableName, oldCommand);
		} else {
			variableContainer.popOffVariable(myVariableName);
		}
		return returnValue;
	}

	@Override
	protected void parseArguments (String userInput) throws BackendException{

		String[] splitInput = splitByInnerListCommand(userInput);
		String innerInput = splitInput[0];

		String[] variableNameContents = innerInput.split(VARIABLE_INDICATOR);
		if (variableNameContents.length < 2) {
			// TODO throw exception invalid # of arguments
		}

		myVariableName = variableNameContents[1].trim().split(COMMAND_SEPARATOR)[0];

		if (myVariableName.equals("")) {
			// TODO throw exception no variable name provided
		}

		myStartCommand =
				CommandFactory.createCommand(variableNameContents[1].trim()
						.split(COMMAND_SEPARATOR, 2)[1].trim(), true);
		myEndCommand = CommandFactory.createCommand(myStartCommand.getLeftoverString(), true);
		myIncrementCommand = CommandFactory.createCommand(myEndCommand.getLeftoverString(), true);

		// Now matching the second set of braces to get internal commands
		String[] splitSecondInput = splitByInnerListCommand(splitInput[1]);
		myInternalCommand = CommandFactory.createCommand(splitSecondInput[0], false);

		setLeftoverCommands(splitSecondInput[1]);
	}
}
