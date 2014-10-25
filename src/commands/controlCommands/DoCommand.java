package commands.controlCommands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.BaseVariableContainer;

public class DoCommand extends ControlCommand {
	private String myVariableName;
	private BaseCommand myLimitCommand;
	private BaseCommand myInternalCommand;

	public DoCommand (String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected double onExecute () throws BackendException {
		BaseVariableContainer variableContainer = getVariableContainer();
		double returnValue = 0;
		double expressionResult = myLimitCommand.execute();

		boolean varExistPreviously = false;
		BaseCommand oldCommand = null;
		if (variableContainer.containsVariable(myVariableName)) {
			oldCommand = variableContainer.getValue(myVariableName);
			varExistPreviously = true;
		}

		if (expressionResult < 1) {
			returnValue = 0;
		}
		else {
			int repetitionCount = (int) expressionResult;

			for (int i = 1; i <= repetitionCount; i++) {
				variableContainer.addVariable(myVariableName, i);
				returnValue = myInternalCommand.execute();
			}
		}

		if (varExistPreviously) {
			variableContainer.addVariable(myVariableName, oldCommand);
		} else {
			variableContainer.popOffVariable(myVariableName);
		}
		return returnValue;
	}

	@Override
	protected void parseArguments (String userInput) {
		String[] splitInput = splitByInnerListCommand(userInput);

		String [] variableNameContents = splitInput[0].split(VARIABLE_INDICATOR);
		if (variableNameContents.length < 2 ) {
			// TODO throw exception invalid # of arguments
		}
		myVariableName =  variableNameContents[1].trim().split(COMMAND_SEPARATOR)[0];

		if (myVariableName.equals("")) {
			// TODO throw exception no variable name provided
		}
		String  variableExpression = splitInput[0].split(VARIABLE_INDICATOR)[1].trim();
		String variableLimit = variableExpression.replace(myVariableName, "").trim();
		myLimitCommand = CommandFactory.createCommand(variableLimit, true);

		// Now matching the second set of braces to get command
		String[] secondSplitInput = splitByInnerListCommand(splitInput[1]);
		String innerCommand = secondSplitInput[0];
		myInternalCommand = CommandFactory.createCommand(innerCommand, false);
		setLeftoverCommands(secondSplitInput[1]);
	}
}