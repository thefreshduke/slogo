package commands.controlCommands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;

public class RepeatCommand extends ControlCommand {

	private BaseCommand myExpression;
	private BaseCommand myInternalCommand;

	public RepeatCommand (String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected double onExecute () throws BackendException {
		double returnValue = 0;
		double expressionResult = myExpression.execute();
		if (expressionResult < 1) {
			returnValue = 0;
		}
		else {
			int repetitionCount = (int) expressionResult;
			for (int i = 0; i < repetitionCount; i++) {
				returnValue = myInternalCommand.execute();
			}
		}
		return returnValue;
	}

	@Override
	protected void parseArguments (String userInput) {
		myExpression = CommandFactory.createCommand(userInput, true);
		String leftOver = new String(myExpression.getLeftoverString().trim());
		String[] splitCommand = splitByInnerListCommand(leftOver);
		String internalCommand = splitCommand[0];
		myInternalCommand = CommandFactory.createCommand(internalCommand, false);
		setLeftoverCommands(splitCommand[1]);
	}
}
